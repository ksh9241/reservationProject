package com.service.reservation.service;

import java.util.List;

import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.dto.ReservationPrice;

public interface ReservationService {
	List<ReservationInfo> reservationsByEmail(String email);
	
	int insertReservationInfo(ReservationInfo info);

	int insertReservationInfoPrice(ReservationPrice price);

	int editCancelByReservationInfoId(String reservationInfoId);
}
