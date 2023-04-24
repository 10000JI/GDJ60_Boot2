package com.iu.base.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	//패스워드가 일치하는지 검증하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;
		//false : error가 없음, 검증 성공
		//true : error가 있음, 검증 실패
		
		//1. annotation의 검증 결과
		result = bindingResult.hasErrors();
		
		//2. password 일치 검증
		if(!memberVO.getPassWord().equals(memberVO.getPassWordCheck())) {
			result = true;
			bindingResult.rejectValue("passWordCheck", "member.password.notEqual");
		}
		
		//3. ID 중복 검사
		MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		 if(checkMember != null) {
			 result=true;
			 bindingResult.rejectValue("userName", "member.userName.notEqual");
		 }
		
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
		return memberDAO.getLogin(memberVO);
	}
	
	public int setJoin(MemberVO memberVO) throws Exception{
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRole(map);
		return result;
	}
	
	public List<MemberVO> getList() throws Exception{
		return memberDAO.getList();
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception{
		return memberDAO.idDuplicateCheck(memberVO);
	}

}
