package com.stackroute.favouriteservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.stackroute.favouriteservice.filter.JwtFilter;

@Configuration
public class BeanConfiguration {

	/*@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registationBean = new FilterRegistrationBean();
		registationBean.setFilter(new JwtFilter());
		registationBean.addUrlPatterns("/api/v1/favoriteservice/*");
		return registationBean;
	}*/

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

}
