package com.iu.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.iu.base.interceptors.AdminCheckInterceptor;
import com.iu.base.interceptors.MemberCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Autowired
	private MemberCheckInterceptor memberCheckInterceptor;
	
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//등록 순서 감안해서 코드 작성
		
		registry.addInterceptor(localeChangeInterceptor)
					.addPathPatterns("/**");
		
//		registry.addInterceptor(memberCheckInterceptor)
//					.addPathPatterns("/member/myPage")
//					.addPathPatterns("/qna/*")
//					.excludePathPatterns("/qna/list")
//					.addPathPatterns("/member/admin")
//					.addPathPatterns("/notice/*")
//					.excludePathPatterns("/notice/list")
//					.excludePathPatterns("/notice/detail");
//		
//		registry.addInterceptor(adminCheckInterceptor)
//					.addPathPatterns("/member/admin")
//					.addPathPatterns("/notice/*")
//					.excludePathPatterns("/notice/list")
//					.excludePathPatterns("/notice/detail");
	}
}
