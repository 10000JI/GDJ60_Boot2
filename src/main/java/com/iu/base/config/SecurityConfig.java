package com.iu.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.iu.base.member.MemberService;
import com.iu.base.member.MemberSocialService;
import com.iu.base.security.UserLoginFailHandler;
import com.iu.base.security.UserLogoutSucessHandler;
import com.iu.base.security.UserSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserLogoutSucessHandler logoutSucessHandler;
	
	@Autowired
	private MemberSocialService memberSocialService;
	
	@Bean
	//public 을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/js/**")
				.antMatchers("/css/**")
				.antMatchers("/favicon/**");
	}
	
	@Bean
	SecurityFilterChain fiterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
				.cors()
				.and()
				.csrf()
				.disable()
			.authorizeRequests()
				//URL과 권한매칭
				.antMatchers("/").permitAll() //루트만 허용
				.antMatchers("/member/join").permitAll()
				.antMatchers("/notice/add").hasRole("MEMBER")
				.antMatchers("/notice/update").hasRole("ADMIN") //Role를 가진 사람만 허용 
				.antMatchers("/notice/delete").hasRole("ADMIN")
				.antMatchers("/notice/*").permitAll() //순서 중요 => notice/add로 먼저 걸러준다
				.antMatchers("/admin/**").hasRole("ADMIN") 
				.antMatchers("/qna/add").hasAnyRole("ADMIN","MANAGER","MEMBER")
				.antMatchers("/qna/list").permitAll()
				//ADMIN 을 가지거나 MANAGER, MEMBER를 가진 사람만 허용 => 회원 한명당 하나의 ROLE를 가질때
				//.anyRequest().authenticated()
				//그외 나머지는 로그인 해야 볼수 있음 (authenticated = 인증)
				.anyRequest().permitAll()
				//테스트할땐 모두 걸려버리니까 permit 마지막에 주자
				.and()
			.formLogin() //로그인 폼 인증 설정
				.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고 개발자가 만든 폼요청 URL
//				.usernameParameter("userName") //id 파라미터는 username이지만, 개발자가 다른 파라미터 이름을 사용할 때
//				.defaultSuccessUrl("/") //인증에 성공할 경우 요청할 URL
				.successHandler(new UserSuccessHandler()) //UserSuccessHandler 객체 생성 (로그인 성공시)
//				.failureUrl("/member/login") //인증에 실패했을 경우 요청할 URL
				.failureHandler(new UserLoginFailHandler()) //UserLoginFailHandler 객체 생성 (로그인 실패시)
				.permitAll()
				.and()
			.logout() //로그아웃 폼 인증 설정
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/")
				.logoutSuccessHandler(logoutSucessHandler)//UserLogoutSucessHandler 객체 생성 (로그아웃 성공시)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.oauth2Login()
				.userInfoEndpoint()
				.userService(memberSocialService);
//			.sessionManagement()
//				.maximumSessions(1) //최대 허용 가능한 세션 수 => -1: 무제한, 1:한명만 접속
//				.maxSessionsPreventsLogin(false); //false: 이전 사용자 세션 만료, true: 새로운 사용자 인증 실패
				
				
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
