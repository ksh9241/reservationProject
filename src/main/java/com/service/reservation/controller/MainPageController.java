package com.service.reservation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.reservation.dto.Category;
import com.service.reservation.dto.Product;
import com.service.reservation.dto.Promotion;
import com.service.reservation.service.CategoryService;
import com.service.reservation.service.ProductService;
import com.service.reservation.service.PromotionService;

@Controller
public class MainPageController {

	@Autowired
	CategoryService categorySvc;
	
	@Autowired
	PromotionService promotionSvc;
	
	@Autowired
	ProductService ProductSvc;
	
	@GetMapping("mainpage")
	public String mainPage(HttpServletResponse res) throws IOException{
		
		return "mainpage";
	}
	
	@ResponseBody
	@GetMapping("categoryList")
	public Map<String,Object> categoryList(){
		List<Category> category=categorySvc.categoryList();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("items", category);
		return map;
	}
	
	@ResponseBody
	@GetMapping("promotionImg")
	public Map<String,Object> promotionImg(){
		List<Promotion> promotion=promotionSvc.promotionImg();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("items", promotion);
		return map;
	}
	
	@ResponseBody
	@GetMapping("products")
	public Map<String,Object> mainProductListById(@RequestParam int categoryId,@RequestParam int start){
		Map<String, Object> map = itemsByCategoryId(categoryId, start);
		return map;
	}
	
	public Map<String,Object> itemsByCategoryId(int categoryId,int start){
		Map<String, Object> map = new HashMap<>();
		if(categoryId!=0) {
			List<Product> products = ProductSvc.productListByCategoryId(categoryId,start);
			List<Category> category = categorySvc.categoryList();
			map.put("totalCount", category.get(categoryId-1).getCount());
			map.put("items", products);
		}else {
			List<Product> products = ProductSvc.productListAll(start);
			List<Category> category = categorySvc.categoryList();
			int totalCount=0;
			for(int i=0;i<category.size();i++) {
				totalCount+= category.get(i).getCount();
			}
			map.put("totalCount", totalCount);
			map.put("items", products);
		}
		return map;
	}
}
