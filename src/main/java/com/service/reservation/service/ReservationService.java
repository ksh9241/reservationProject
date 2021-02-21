package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.dto.ReservationPrice;

public interface ReservationService {
	List<ReservationInfo> reservationsByEmail(String email);
	
	int reservationInfoInsert(ReservationInfo info);

	int reservationInfoPriceInsert(ReservationPrice price);

	int cancelEditByReservationInfoId(String reservationInfoId);
}
