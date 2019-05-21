package com.stackroute.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.stackroute.zuulservice.filter.JwtFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registationBean  = new FilterRegistrationBean();
		registationBean.setFilter(new JwtFilter());
		registationBean.addUrlPatterns("/usertrackservice/api/v1/usertrackservice/user/*");
		return registationBean;
	}

}
