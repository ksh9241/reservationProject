package com.service.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingLogInController {

	@GetMapping("bookinglogin")
	public String bookingLogIn() {
		return "bookinglogin";
	}
	
}
