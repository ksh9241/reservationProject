package com.service.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.service.reservation.controller","com.service.reservation.service","com.service.reservation.serviceimpl","com.service.reservation.dao"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	
	private int TEN_MEGA = 10 * 1024 * 1024;
	
	// 해당 url이 포함되어 들어오는 것들은 설정된 폴더에서 처리해 주게 명령하는 메소드
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}

	// default servlet handler를 사용하게 합니다. (매핑정보가 없는 url요청이 들어왔을 때 spring에서 처리하도록 해주게 함)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// 특정 url에 대한 처리를 컨트롤러 부분에서 path를 작성하지않고 처리하게 해주는 메소드('/'이 들어오면 '/index' 로 처리하라는 뜻)
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("mainpage");
	}

	// url의 prefix와 suffix를 지정해줌으로써 url이 간결해짐
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(TEN_MEGA);
		resolver.setMaxInMemorySize(TEN_MEGA);
		return resolver;
	}
}
