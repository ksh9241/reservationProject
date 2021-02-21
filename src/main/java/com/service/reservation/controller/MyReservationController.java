package com.service.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.reservation.dto.DisplayInfo;
import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.service.DisplayInfoService;
import com.service.reservation.service.ReservationService;

@Controller
public class MyReservationController {
	@Autowired
	ReservationService reservationSvc;
	
	@Autowired
	DisplayInfoService displayInfoSvc;
	
	@GetMapping("myreservation")
	public String myReservation(@RequestParam String resrv_email,Model model,HttpSession session) {
		session.setAttribute("email", resrv_email);
		return "myreservation";
	}
	
	@ResponseBody
	@GetMapping("reservations")
	public Map<String,Object> reservationsByEmail(@RequestParam String email){
		List<ReservationInfo>list = reservationSvc.reservationsByEmail(email);
		for(int i=0;i<list.size();i++) {
			DisplayInfo displayInfo = displayInfoSvc.displayInfoById(list.get(i).getDisplayInfoId());
			list.get(i).setDisplayInfo(displayInfo);
		}
		int size = list.size();
		
		Map<String,Object> map = new HashMap<>();
		map.put("reservations", list);
		map.put("size",size);
		return map;
	}
}
