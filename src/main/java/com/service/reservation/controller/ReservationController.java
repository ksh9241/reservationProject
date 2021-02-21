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
	
	@ResponseBody
	@PostMapping("reservations")
	public Map<String,Object> insertReservation(@RequestBody ReservationParam reservation) {
		List<ReservationPrice> price = new ArrayList<>();
		int n=0;
		Date d = new Date();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		//존재하지 않는다면 reservation의 정보를 info에 담아서 새로운 reservationInfo 객체 생성 후 그 객체 id를 통해 reservation_info_price 객체 생성
			ReservationInfo info = new ReservationInfo();
			info.setDisplayInfoId(reservation.getDisplayInfoId());
			info.setProductId(reservation.getProductId());
			info.setReservationName(reservation.getReservationName());
			info.setReservationTelephone(reservation.getReservationTelephone());
			info.setReservationEmail(reservation.getReservationEmail());
			info.setReservationDate(reservation.getReservationYearMonthDay());
			info.setCreateDate(time);
			info.setModifyDate(time);
			
			int id = reservationSvc.reservationInfoInsert(info);
			
			if(id>0) {
				for(int i=0;i<reservation.getPrices().size();i++) {
					reservation.getPrices().get(i).setReservationInfoId(id);
					price.add(reservation.getPrices().get(i));
					n = reservationSvc.reservationInfoPriceInsert(price.get(i));
				}
			}
		
		Map<String,Object> map = new HashMap<>();
		// reservation_info_price 객체 생성을 성공적으로 마쳤다면 email을 보내준다.
		if(n>0) {
			map.put("email", reservation.getReservationEmail());
		}
		return map;
	}
	
	@ResponseBody
	@PutMapping("reservations/{reservationInfoId}")
	public int myReservationEdit(@PathVariable("reservationInfoId") String reservationInfoId) {
		System.out.println(reservationInfoId);
		return reservationSvc.cancelEditByReservationInfoId(reservationInfoId); 
	}
}
