package com.service.reservation.main;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.service.reservation.config.ApplicationConfig;

public class DataSourceTest {
	public static void main(String[] args) {
		ApplicationContext ac=new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DataSource ds=ac.getBean(DataSource.class);
		try (Connection con=ds.getConnection()){
			if(con!=null) {
				System.out.println("Connection 연결됨.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
