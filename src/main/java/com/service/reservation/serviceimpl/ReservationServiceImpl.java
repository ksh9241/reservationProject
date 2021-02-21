package com.service.reservation.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.reservation.dao.ReservationDao;
import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.dto.ReservationPrice;
import com.service.reservation.service.ReservationService;
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public List<ReservationInfo> reservationsByEmail(String email) {
		return reservationDao.reservationsByEmail(email);
	}

	@Override
	public int reservationInfoInsert(ReservationInfo info) {
		return reservationDao.reservationInfoInsert(info);
	}

	@Override
	public int reservationInfoPriceInsert(ReservationPrice price) {
		return reservationDao.reservationInfoPriceInsert(price);
	}

	@Override
	public int cancelEditByReservationInfoId(String reservationInfoId) {
		return reservationDao.cancelEditByReservationInfoId(reservationInfoId);
	}

}
