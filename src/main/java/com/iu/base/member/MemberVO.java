package com.iu.base.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO implements UserDetails, OAuth2User{
	//OAuth2User은 소셜 로그인에서 사용하는 인터페이스
	@NotBlank
	private String username;
	@NotBlank
	@Size(min = 8, max=10)
	private String password;
	
	private String passWordCheck;
	@NotBlank
	private String name;
	@Email
	private String email;
	@Future
	private Date birth;
	private Date lastTime;
	private boolean enabled;
	
	private List<RoleVO> roleVOs;
	
	//OAuth2User, token 정보 저장
	private Map<String,Object> attributes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//? (무슨 타입일진 모르겠지만) GrantedAuthority 타입을 상속받겠다
		//Authority는 권한에 대한 것
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO:roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		
		return authorities;
	}

//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		password(pw) 반환
//		return null;
//	}

//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		username(id) 반환
//		return null;
//	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		// 계정의 만료 여부
		// true : 만료가 안됨
		// false: 만료 됨, 로그인 안됨
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		// 계정의 잠김 여부
		// true : 잠기지 않음
		// false: 잠김
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		// password 만료 여부
		// true : 만료 안됨
		// false: 만료 됨, 로그인 안됨
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		// 계정 사용 여부
		// true = 1: 계정 활성화
		// false = 0: 계정 비활성화, 로그인 안됨 
		return this.enabled;
	}

	
	
}
