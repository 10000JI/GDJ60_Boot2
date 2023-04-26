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
	
	//id중복체크
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	//유저리스트
	public List<MemberVO> getUserList() throws Exception;
	
	//로그아웃할때 현재 시간
	public int setLastTimeUpdate(MemberVO memberVO) throws Exception;
	
	public int setEnabledUpdate() throws Exception;
	
	public List<MemberVO> getBirthList() throws Exception;
}
