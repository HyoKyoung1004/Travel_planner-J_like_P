package com.trip.project.service.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.trip.project.dto.comment.CommentFileDto;
import com.trip.project.dto.user.UserDto;
import com.trip.project.dto.user.UserFileDto;
import com.trip.project.dto.user.UserFileRepository;
import com.trip.project.dto.user.UserRepository;

@Service
public class UserService {

	@Value("${file.path}")
	private String uploadPath;

	@Value("${jwt.secret}")
	private String secretkey;

	private Long expiredMs = 1000 * 60 * 60l;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserFileRepository userfileRepository;

	public int join(UserDto user) throws Exception {
		if (userRepository.find(user) != null) {
			throw new Exception("이미 있는 회원입니다");
		}
		if (user.getUserAccount() == "") {
			return 0;
		}
		return userRepository.join(user);
	}

	public UserDto login(UserDto user) throws Exception {

		if (userRepository.findLogin(user) == null) {
			return null;
		}
		// UserDto userDto = userRepository.login(user);
		// System.out.println(userDto.getUserAccount());
		return userRepository.login(user);
	}

	public UserDto viewMypage(long userId) throws Exception {
		if (userRepository.findUserId(userId) == null) {
			throw new Exception("잘못된 회원페이지입니다");
		}

		System.out.println("여기까진오냐?");
		UserDto user = userRepository.viewMypage(userId);
		return user;
	}

	public int deleteLike(long userId) {
		return userRepository.deleteLike(userId);
	}

	public long getUser(String userAccount) {

		return userRepository.getUser(userAccount);
	}

	public UserDto getUserOne(String userAccount) {

		return userRepository.getUserOne(userAccount);
	}

	@Transactional
	public boolean addImage(long userId, MultipartFile uploadedFile) throws IllegalStateException, IOException {
		System.out.println("DB에 들어간 후 comment: " + userId);
		System.out.println("filePath: " + uploadPath);

		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;

		File folder = new File(saveFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		if (uploadedFile != null && !uploadedFile.isEmpty()) {
			String originalFileName = uploadedFile.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				UserFileDto userFile = new UserFileDto();
				userFile.setSaveFolder(today);
				userFile.setUserId(userId);
				userFile.setOriginalFileName(originalFileName);
				userFile.setSaveFileName(saveFileName);
				uploadedFile.transferTo(new File(folder, saveFileName));
				userfileRepository.insert(userFile);
				return true;
			}
		}

		return false;
	}

	public void updateMemberInfo(int userid, UserDto userDto) {
		long userId = userid;
		System.out.println(userId);
		UserDto existingUser = userRepository.findUserDto(userId);
		existingUser.setUserEmail(userDto.getUserEmail());
		existingUser.setNickName(userDto.getNickName());
		existingUser.setUserPassword(userDto.getUserPassword());
		System.out.println(existingUser.toString());
		userRepository.updateMemberInfo(existingUser);

		System.out.println("??????" + existingUser.toString());
	}

	public UserFileDto getUserImage(int userId) {
		return userRepository.getUserImg(userId);
	}

	public void uploadUerImg(int userid, UserDto user, MultipartFile[] uploadedfiles) throws IllegalStateException, IOException {

		System.out.println("filePat: " + uploadPath);

		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;

		File folder = new File(saveFolder);
		if (!folder.exists())
			folder.mkdirs();

		List<UserFileDto> fileInfos = new ArrayList<UserFileDto>();
		int fileResult = 0;

		if (uploadedfiles != null) {
			
			//원래꺼 지우고 넣어야해!
			int n =userRepository.deleteUserImg(userid);
			System.out.println(n+"개 삭제 완료!");
			
			for (MultipartFile mfile : uploadedfiles) {
				System.out.println(mfile.getName());
				UserFileDto userFile = new UserFileDto();
				String originalFileName = mfile.getOriginalFilename();

				if (!originalFileName.isEmpty()) {

					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					userFile.setSaveFolder(today);
					userFile.setOriginalFileName(originalFileName);
					userFile.setSaveFileName(saveFileName);
					userFile.setUserId(userid);
					mfile.transferTo(new File(folder, saveFileName));
					fileResult += userRepository.insert(userFile);

				}
				fileInfos.add(userFile);
			}
		}
		
		System.out.println("파일 업로드 개수:" + fileResult);


	}

	public String getUserOneName(long userId) {
		return userRepository.getUserOneNickName(userId);
	}

}
