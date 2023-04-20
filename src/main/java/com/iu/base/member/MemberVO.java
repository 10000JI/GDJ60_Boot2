package com.iu.base.member;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
	private String userName;
	private String passWord;
	private String name;
	private String email;
	private Date birth;
	private boolean enabled;
	
	private List<RoleVO> roleVOs;
}
