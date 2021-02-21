package com.service.reservation.dao;

import static com.service.reservation.dao.ReservationDaoSql.RESERVATIONS_BY_EMAIL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.reservation.dto.ReservationInfo;
import com.service.reservation.dto.ReservationPrice;

import static com.service.reservation.dao.ReservationDaoSql.*;

@Repository
public class ReservationDao {
	NamedParameterJdbcTemplate jdbc;
	SimpleJdbcInsert reservationInfoInsert;
	SimpleJdbcInsert reservationInfoPriceInsert;
	RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	
	public ReservationDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
		reservationInfoInsert = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
		reservationInfoPriceInsert =  new SimpleJdbcInsert(dataSource).withTableName("reservation_info_price").usingGeneratedKeyColumns("id");
	}
	
	public List<ReservationInfo> reservationsByEmail(String email){
		Map<String,Object> map = new HashMap<>();
		map.put("email", email);
		return jdbc.query(RESERVATIONS_BY_EMAIL, map,rowMapper);
	}

	@Transactional
	public int reservationInfoInsert(ReservationInfo info) {
		Map<String,Object> map = new HashMap<>();
		map.put("product_id", info.getProductId());
		map.put("display_info_id", info.getDisplayInfoId());
		map.put("reservation_name", info.getReservationName());
		map.put("reservation_tel", info.getReservationTelephone());
		map.put("reservation_email", info.getReservationEmail());
		map.put("reservation_date", info.getReservationDate());
		map.put("cancel_flag", info.getCancelYn());
		return reservationInfoInsert.executeAndReturnKey(map).intValue();
	}
	
	public int reservationInfoPriceInsert(ReservationPrice price) {
		Map<String,Object> map = new HashMap<>();
		map.put("reservation_info_id", price.getReservationInfoId());
		map.put("product_price_id", price.getProductPriceId());
		map.put("count", price.getCount());
		return reservationInfoPriceInsert.executeAndReturnKey(map).intValue();
	}

	public int cancelEditByReservationInfoId(String reservationInfoId) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", reservationInfoId);
		return jdbc.update(RESERVATION_EDIT_BY_ID, map);
	}
}
