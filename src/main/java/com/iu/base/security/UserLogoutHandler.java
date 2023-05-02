package com.iu.base.security;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserLogoutHandler implements LogoutHandler {
	//로그인한 사용자의 정보를 가져와야함 
	//userLogoutSucessHandeler에서는 진행되면 사용자정보 지워져서..
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		this.logoutAll();
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void logoutAll() {
		MemberVO memberVO = (MemberVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.error("회원번호 ::: {}",memberVO.getAttributes().get("id"));
		//1. RestTemplate 객체 생성
		RestTemplate restTemplate = new RestTemplate();
		
		//2. Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","KakaoAK "+adminKey);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//3. Parameter 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getAttributes().get("id").toString());
		
		//4. 2,3을 포함한 요청 객체 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params,headers);
		
		//5. 요청 전송 결과 처리
		String id = restTemplate.postForObject("https://kapi.kakao.com/v1/user/logout", request, String.class);
		log.error("logout Result : {}",id);
	}
	
}
