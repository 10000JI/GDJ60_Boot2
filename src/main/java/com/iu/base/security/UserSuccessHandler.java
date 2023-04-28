package com.iu.base.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class UserSuccessHandler implements AuthenticationSuccessHandler{

   @Override
   public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
         Authentication authentication) throws IOException, ServletException {
      log.error("===============login 성공 후 실행 =======================");
      //로그인 성공했을때 쿠키를 만들어 사용자에게 보냄
      //해당 쿠키가 있으면 뿌려줌
      String remember = request.getParameter("remember");
      
      log.error("======{}========",remember);
      
      if(remember !=null && remember.equals("remember")) {
    	  //Principal 객체를 가져옴
    	  //MemberVO memberVO =(MemberVO)  authentication.getPrincipal();
    	  //Cookie cookie = new Cookie("remember",memberVO.getUsername());
    	  
    	  //Authentication의 userName을 그냥 뽑아올 수도 있음
    	  Cookie cookie = new Cookie("remember",authentication.getName());
    	  cookie.setMaxAge(60*60*24);
    	  //쿠키 유효시간 : 60*60*24=24시간
    	  response.addCookie(cookie);
      } else {
    	  Cookie[] cookies  =request.getCookies();
    	  for (Cookie cookie : cookies) {
			if(cookie.getName().equals("remember")) {
				cookie.setMaxAge(0);
				//쿠키 유효시간 : 0
				response.addCookie(cookie);
			}
		}
      }
      
      response.sendRedirect("/");
   }

   
   
}