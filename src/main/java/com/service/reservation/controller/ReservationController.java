package com.service.reservation.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.dto.ReservationParam;
import com.service.reservation.dto.ReservationPrice;
import com.service.reservation.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	ReservationService reservationSvc;
	
	
	@GetMapping("reservation")
	public String reservation(@RequestParam String id,Model model) {
		model.addAttribute("id",id);
		return "reserve";
	}
	
	public int setReservation(ReservationParam params) {
		List<ReservationPrice> price = new ArrayList<>();
		int n=0;
		Date d = new Date();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		ReservationInfo info = new ReservationInfo();
		info.setDisplayInfoId(params.getDisplayInfoId());
		info.setProductId(params.getProductId());
		info.setReservationName(params.getReservationName());
		info.setReservationTelephone(params.getReservationTelephone());
		info.setReservationEmail(params.getReservationEmail());
		info.setReservationDate(params.getReservationYearMonthDay());
		info.setCreateDate(time);
		info.setModifyDate(time);
		
		int id = reservationSvc.insertReservationInfo(info);
		
		if(id>0) {
			for(int i=0;i<params.getPrices().size();i++) {
				params.getPrices().get(i).setReservationInfoId(id);
				price.add(params.getPrices().get(i));
				n = reservationSvc.insertReservationInfoPrice(price.get(i));
			}
		}
		return n;
	}
	
	@ResponseBody
	@PostMapping("reservations")
	public Map<String,Object> insertReservation(@RequestBody ReservationParam reservation) {
		Map<String,Object> map = new HashMap<>();
		if(setReservation(reservation)>0) {
			map.put("email", reservation.getReservationEmail());
		}
		return map;
	}
	
	@ResponseBody
	@PutMapping("reservations/{reservationInfoId}")
	public int myReservationEdit(@PathVariable("reservationInfoId") String reservationInfoId) {
		return reservationSvc.editCancelByReservationInfoId(reservationInfoId); 
	}
	
	@ResponseBody
	@GetMapping("eamilByReservationInfo")
	public Map<String,String> emailByReservationInfo(@RequestParam String email){
		List<ReservationInfo> list = reservationSvc.reservationsByEmail(email);
		Map<String, String> map = new HashMap<>();
		if(list.size()>0) {
			map.put("name", list.get(0).getReservationName());
			map.put("tel", list.get(0).getReservationTelephone());
			map.put("email", list.get(0).getReservationEmail());
		}else {
			map.put("email",email);
		}
		return map;
	}
}
