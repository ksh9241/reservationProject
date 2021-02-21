package com.service.reservation.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.reservation.dto.Comment;
import com.service.reservation.dto.CommentImage;
import com.service.reservation.dto.DisplayInfo;
import com.service.reservation.dto.DisplayInfoImage;
import com.service.reservation.dto.ProductImage;
import com.service.reservation.dto.ProductPrice;
import com.service.reservation.service.CommentImageService;
import com.service.reservation.service.CommentService;
import com.service.reservation.service.DisplayInfoImageService;
import com.service.reservation.service.DisplayInfoService;
import com.service.reservation.service.ProductImageService;
import com.service.reservation.service.ProductPriceService;

@Controller
public class DetailController {

	@Autowired
	DisplayInfoService displayInfoSvc;
	
	@Autowired
	ProductImageService productImageSvc;
	
	@Autowired
	DisplayInfoImageService displayInfoImageSvc;
	
	@Autowired
	ProductPriceService productpriceSvc;
	
	@Autowired
	CommentService commentSvc;
	
	@Autowired
	CommentImageService commentImageSvc;
	
	@GetMapping("detail")
	public String detail(@RequestParam int id,Model model) throws JsonProcessingException {
		//DisplayInfo d = displayInfoSvc.displayInfoById(id);
		
		//ObjectMapper mapper = new ObjectMapper();
		//String jsonId = mapper.writeValueAsString(id);
		//System.out.println(id+"\r\n"+d);
		model.addAttribute("id",id);
		return "detail";
	}
	
	@ResponseBody
	@GetMapping("products/{displayInfoId}")
	public Map<String,Object> detailById(@PathVariable("displayInfoId")String displayInfoId) {
		DisplayInfo displayInfo = displayInfoSvc.displayInfoById(Integer.parseInt(displayInfoId));
		List<ProductImage> productImages = productImageSvc.productsImageById(Integer.parseInt(displayInfoId));
		DisplayInfoImage displayInfoImage = displayInfoImageSvc.displayInfoImageById(Integer.parseInt(displayInfoId));
		List<ProductPrice> productPrices = productpriceSvc.productPriceById(Integer.parseInt(displayInfoId));
		List<Comment> comments = commentSvc.commentsById(Integer.parseInt(displayInfoId));
		
		float averageScore = 0;
		for(int i=0;i<comments.size();i++) {
			//List<CommentImage>있는거 db에서 가져온 후 맞는 comments list에 담기
			averageScore+=comments.get(i).getScore();
			List<CommentImage> commentImages = commentImageSvc.commentImgByCommentId(comments.get(i).getCommentId());
			if(commentImages!=null) {
				comments.get(i).setCommentImages(commentImages);
			}
		}
		averageScore = averageScore / comments.size();
		
		
		
		Map<String,Object> map = new HashMap<>();
		map.put("displayInfo", displayInfo);
		map.put("productImages", productImages);
		map.put("displayInfoImage", displayInfoImage);
		map.put("comments", comments);
		map.put("averageScore", averageScore);
		map.put("productPrices", productPrices);
		
		
		return map;
	}
}
