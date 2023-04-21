package com.iu.base.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * 로그인 유무를 판별하는 Interceptors
 *
 */

@Slf4j
@Component
public class MemberCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("=====Interceptor 실행=====");
		HttpSession session=request.getSession();
		Object memberVO = session.getAttribute("member"); 
		//null인지 아닌지만 알아보려고 하므로 Object로 받자
		
		if(memberVO != null) {
			return true;
		} else {
			//redirect 방식
			//response.sendRedirect("/member/login");
			
			//forwarding방식
			request.setAttribute("message", "로그인이 필요합니다.");
			request.setAttribute("url", "/member/login");
			//model = request의 setAttribute
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			//return이 false 이므로 ModelAndView 못쓰므로 옛날 방식으로 보내줘야 함			
			view.forward(request, response);
			return false; 
		}
	}

}
