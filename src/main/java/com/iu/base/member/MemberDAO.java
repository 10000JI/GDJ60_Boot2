package com.iu.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	//회원 정보
	public MemberVO getLogin(MemberVO memberVO) throws Exception;
	
	//회원 가입
	public int setJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRole(Map<String, Object> map) throws Exception;
	
	//회원 리스트
	public List<MemberVO> getList() throws Exception;
}
