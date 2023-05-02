package com.iu.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;

import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutSucessHandler implements LogoutSuccessHandler{
	
	@Autowired
	private MemberDAO memberDAO; 
	//개발자가 SecurityConfig 파일에서 new UserLogoutSucessHandler()로 직접 객체 생성히면 autowired는 먹지 X
	//스프링이 대신 해주는 기능이기 때문
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;

	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.error("=============logout success handler ================");
		log.error("============={}=============",memberDAO);
		
//		
		response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://ec2-13-124-47-151.ap-northeast-2.compute.amazonaws.com/member/socialLogout");
	}
}
