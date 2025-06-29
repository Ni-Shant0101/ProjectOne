package com.solutions.crm.Jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.solutions.crm.beans.Role;
import com.solutions.crm.beans.Users;

import io.jsonwebtoken.Claims;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String accessToken = getAccessToken(request);

		if (!jwtUtil.validateAccessToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		setAuthenticationContext(accessToken, request);
		filterChain.doFilter(request, response);
	}
	
	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(accessToken);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private UserDetails getUserDetails(String accessToken) {
		Users userDetails = new Users();
		Claims claims = jwtUtil.parseClaims(accessToken);

		String claimRoles = (String) claims.get("roles");

		// System.out.println("claimRoles: "+ claimRoles);

		claimRoles = claimRoles.replace("[", "").replace("]", "");
		String[] roleNames = claimRoles.split(",");

		for (String aRoleName : roleNames) {
			userDetails.addRole(new Role(aRoleName));
		}

		String subject = (String) claims.get(Claims.SUBJECT);

		String[] subjectArray = subject.split(",");

		userDetails.setId(Integer.parseInt(subjectArray[0]));
		userDetails.setUsername(subjectArray[1]);

		return userDetails;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		// System.out.println("Authorization header: " + header);

		if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
			return false;
		}
		return true;
	}

	private String getAccessToken(HttpServletRequest request) {

		String header = request.getHeader("Authorization");
		String token = header.split(" ")[1].trim();

		// System.out.println("Access Token: "+token);
		return token;
	}
}
