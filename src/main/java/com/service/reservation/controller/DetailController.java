package com.service.reservation.controller;

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
import com.service.reservation.dto.CommentImage;
import com.service.reservation.dto.Detail;
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
		model.addAttribute("id",id);
		return "detail";
	}
	
	public float averageScore(Detail detail) {
		float averageScore = 0;
		for(int i=0;i<detail.getComments().size();i++) {
			averageScore+=detail.getComments().get(i).getScore();
			List<CommentImage> commentImages = commentImageSvc.commentImgByCommentId(detail.getComments().get(i).getCommentId());
			if(commentImages!=null) {
				detail.getComments().get(i).setCommentImages(commentImages);
			}
		}
		averageScore = averageScore / detail.getComments().size();
		return averageScore;
	}
	
	@ResponseBody
	@GetMapping("products/{displayInfoId}")
	public Detail detailById(@PathVariable("displayInfoId")String displayInfoId) {
		Detail detail = new Detail();
		detail.setDisplayInfo(displayInfoSvc.displayInfoById(Integer.parseInt(displayInfoId)));
		detail.setProductImages(productImageSvc.productsImageById(Integer.parseInt(displayInfoId)));
		detail.setDisplayInfoImage(displayInfoImageSvc.displayInfoImageById(Integer.parseInt(displayInfoId)));
		detail.setProductPrices(productpriceSvc.productPriceById(Integer.parseInt(displayInfoId))); 
		detail.setComments(commentSvc.commentsById(Integer.parseInt(displayInfoId)));
		detail.setAverageScore(averageScore(detail));
		
		Map<String,Object> map = new HashMap<>();
		map.put("data", detail);
		
		return detail;
	}
}
