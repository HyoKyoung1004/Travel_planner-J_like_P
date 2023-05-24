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
	
	private Long expiredMs = 1000*60*60l;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserFileRepository userfileRepository;
	
	public int join(UserDto user) throws Exception {
		if(userRepository.find(user)!=null ) {
			throw new Exception("이미 있는 회원입니다");
		}
		if(user.getUserAccount()=="") {
			return 0;
		}
		return userRepository.join(user);
	}
	public UserDto login(UserDto user) throws Exception {

		if(userRepository.findLogin(user)==null) {
			return null;
		}
		//UserDto userDto = userRepository.login(user);
		//System.out.println(userDto.getUserAccount());
		return userRepository.login(user);
	}
	public UserDto viewMypage(long userId) throws Exception {
		if(userRepository.findUserId(userId)==null) {
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
	public boolean updateUserInfo(Long userid, UserDto userDto) {
		if(userRepository.updateUserInfo(userid,userDto)) {
			return true;
		}
		return false;
	}

}
