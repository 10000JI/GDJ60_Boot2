package com.iu.base.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	//회원 정보
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	//회원 가입
	public int setJoin(MemberVO memberVO) throws Exception;
	
	//권한 디폴트 MEMBER
	public int setRoleAdd(MemberVO memberVO) throws Exception;
}
