package com.stackroute.zuulservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpresponse = (HttpServletResponse) response;
		final String authHeader = httpRequest.getHeader("authorization");
		
		if("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			httpresponse.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(httpRequest, httpresponse);
		}
		
		else {
			if(authHeader==null || !authHeader.startsWith("Bearer") ) {
				throw new ServletException("Token not found");
			}
			
			final String token = authHeader.substring(7);
			final Claims claim  = Jwts.parser().setSigningKey("secretKey")
					.parseClaimsJws(token).getBody();
			request.setAttribute("claim", claim);
			chain.doFilter(httpRequest, httpresponse);
		}
		
	}

}

