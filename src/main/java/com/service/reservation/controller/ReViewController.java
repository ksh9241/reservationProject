package com.service.reservation.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.reservation.dto.CommentResponse;
import com.service.reservation.service.ReViewService;

@Controller
public class ReViewController {
	
	@Autowired
	ReViewService reViewSvc;

	@GetMapping("review")
	public String reVioew(@RequestParam String id,Model model) {
		model.addAttribute("id",id);
		return "review";
	}
	
	@GetMapping("reviewWrite")
	public String reviewWrite(HttpSession session,@RequestParam String reservationInfoId,@RequestParam String productId,Model model) {
		model.addAttribute("reservationInfoId",reservationInfoId);
		model.addAttribute("productId",productId);
		return "reviewWrite";
	}
	
	@ResponseBody
	@PostMapping("reservations/{reservationInfoId}/comments")
	public Map<String,Object> reviewWriteEnd(@PathVariable("reservationInfoId")String reservationInfoId,@ModelAttribute CommentResponse data,
			HttpServletRequest req,HttpSession session) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		Map<String,Object> fileMap = new HashMap<>();
		int fileId=0;
		
		Map<String,Object> commentMap = new HashMap<>();
		commentMap.put("product_id", data.getProductId());
		commentMap.put("reservation_info_id", data.getReservationInfoId());
		commentMap.put("score", data.getScore());
		commentMap.put("comment", data.getComment());
		commentMap.put("create_date", time);
		commentMap.put("modify_date", time);
		
		int commentId = reViewSvc.reservationInfoUserCommentInsert(commentMap);
		
		//파일 저장할 절대경로 얻기
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/img");
		//String upDir = "C:\\myjava\\workspace\\myproject\\src\\main\\webapp\\img";
		
		//System.out.println(upDir);
		
		//저장할 디렉토리가 존재히자 않는다면 디렉토리를 생성한다.
		File dir = new File(upDir);
		if(!dir.exists()) {
			System.out.println("디렉토리 생성 중...");
			dir.mkdirs();
		}
		
		//이미지 파일이 존재한다면
		if(data.getImageData()!=null) {
			String fileName = data.getImageData().getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String saveFileName = uuid+"_"+fileName; 
			
			//파일 업로드
			try {
				data.getImageData().transferTo(new File(upDir,saveFileName));
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			//file_info 테이블에 insert할 Map 정의
			fileMap.put("file_name", fileName);
			fileMap.put("save_file_name", saveFileName);
			fileMap.put("content_type", data.getImageData().getContentType());
			fileMap.put("create_date", time);
			fileMap.put("modify_date", time);
			fileMap.put("delete_flag", 0);
			
			fileId = reViewSvc.fileInfoInsert(fileMap);
		}
		
		if(fileId>0) {
			Map<String,Object> commentImageMap = new HashMap<>();
			commentImageMap.put("reservation_info_id", data.getReservationInfoId());
			commentImageMap.put("reservation_user_comment_id", commentId);
			commentImageMap.put("file_id", fileId);
			
			reViewSvc.reservationInfoUserCommentImageInsert(commentImageMap);
		}
		
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("email", session.getAttribute("email"));
		return result;
	}
}
