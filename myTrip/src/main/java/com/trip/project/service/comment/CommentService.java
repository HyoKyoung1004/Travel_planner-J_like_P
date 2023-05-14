package com.trip.project.service.comment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.trip.project.dto.comment.CommentDto;
import com.trip.project.dto.comment.CommentFileDto;
import com.trip.project.dto.comment.CommentFileRepository;
import com.trip.project.dto.comment.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	CommentFileRepository commentFileRepo;
	
	@Value("${file.path}")
	private String uploadPath;

	
	//댓글 입력, 파일도 같이 저장한다. 
	@Transactional
	public boolean  insert(CommentDto comment, MultipartFile[] uploadedfiles) throws IllegalStateException, IOException {
		
		//먼저 comment를 저장한다. 
		System.out.println("DB에 들어가기 전 comment : "+comment); 
		int result =commentRepo.insert(comment);
		System.out.println("DB에 들어간 후 comment : "+comment); 

		
		System.out.println("filePat: "+uploadPath);
		
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadPath + File.separator + today;

		File folder = new File(saveFolder);
		if (!folder.exists())
			folder.mkdirs();
		
		List<CommentFileDto> fileInfos = new ArrayList<CommentFileDto>();
		int fileResult=0;
		
		
		if(uploadedfiles !=null) {
		for (MultipartFile mfile : uploadedfiles) {
			CommentFileDto commentFile = new CommentFileDto();
			String originalFileName = mfile.getOriginalFilename();
			
			if (!originalFileName.isEmpty()) {
				
				String saveFileName = UUID.randomUUID().toString()
						+ originalFileName.substring(originalFileName.lastIndexOf('.'));
				commentFile.setSaveFolder(today);
				commentFile.setOriginalFileName(originalFileName);
				commentFile.setSaveFileName(saveFileName);
				commentFile.setCommentId(comment.getCommentId());
				mfile.transferTo(new File(folder, saveFileName));
				fileResult+=commentFileRepo.insert(commentFile);

			}
			fileInfos.add(commentFile);
			}
		}
		
		
		comment.setFileInfo(fileInfos);
		if(result==1 && fileResult==fileInfos.size())
			return true;
		else return false;
	}
	
	
	public List<CommentDto> selectList(int contentId){
		List<CommentDto> commentList =  commentRepo.selectList(contentId);
		for(CommentDto comment : commentList) {
			comment.setFileInfo(selectFileList(comment.getCommentId()));
		}
		return commentList;
	}
	
	
	public List<CommentFileDto> selectFileList(long commentId){
		if(commentFileRepo.countList(commentId)>0)
			return commentFileRepo.selectList(commentId);
		return new ArrayList<CommentFileDto>();
	}


	

}
