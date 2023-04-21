package com.iu.base.member;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("admin")
	public void getAdmin() throws Exception{
		
	}
	
	@GetMapping("myPage")
	public void getMyPage() throws Exception{
		
	}
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception{
		log.debug("======================== id중복체크 ========================");
		boolean check = true;
		List<MemberVO> ar= memberService.getList();
		for (MemberVO vo : ar) {
			if(memberVO.getUserName().equals(vo.getUserName())){
				check =false;
			}
		}
		return check; //0이면 중복 , 1이면 가입 가능
	}
	
	@GetMapping("pwDuplicateCheck")
	@ResponseBody
	public boolean pwDuplicateCheck(String passWord, String passWordCheck) {
		boolean check = false;
		if(passWord.equals(passWordCheck)) {
			check = true;
		}
		return check;
	}
	
	@GetMapping("join")
	public ModelAndView setJoin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView getLogout(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@GetMapping("login")
	public ModelAndView getLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
	}
	
	@PostMapping
	public ModelAndView getLogin(MemberVO memberVO, HttpSession session) throws Exception {
		ModelAndView mv =new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		mv.setViewName("redirect:./login");
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		return mv;
	}
	
}
