package com.iu.base.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db에서 꺼낸 로그인정보가지고 service
		log.error("==================Spring Security Login==================");
		log.error("=================={}==================", username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return memberVO;
	}

	//패스워드가 일치하는지 검증하는 메서드 + 이메일 중복 체크 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;
		//false : error가 없음, 검증 성공
		//true : error가 있음, 검증 실패
		
		//1. annotation의 검증 결과
		result = bindingResult.hasErrors();
		
		//2. password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPassWordCheck())) {
			result = true;
			bindingResult.rejectValue("passWordCheck", "member.password.notEqual");
		}
		
		//3. ID 중복 검사
		MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		 if(checkMember != null) {
			 result=true;
			 bindingResult.rejectValue("username", "member.userName.notEqual");
		 }
		
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
		return memberDAO.getLogin(memberVO);
	}
	
	public int setJoin(MemberVO memberVO) throws Exception{
		//memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
	
	//로그아웃할때 현재 시간
	public int setLastTimeUpdate(MemberVO memberVO) throws Exception{
		return memberDAO.setLastTimeUpdate(memberVO);
	}
	
	//비밀번호 재발급 받을때, 아이디 이메일 체크 하는 창
	public boolean idEmailCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception{
			boolean result = false;
			//false : 이메일 전송해야됨
			//true :  이메일 전송하면 안됨
			
			//1. annotation의 검증 결과
			result = bindingResult.hasErrors();
			
			//2. 아이디 이메일 체크
			MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
			MemberVO checkEamil = memberDAO.emailCheck(memberVO);
			 if(checkMember != null && checkEamil != null) {
				 result=true;
				 bindingResult.rejectValue("email", "member.checkEmail.check");
			 }
			
			return result;
		}
	
	public String generatePassword() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String password = "";

        Random random = new Random();

        // 6글자의 비밀번호를 생성합니다.
        for (int i = 0; i < 6; i++) {
            // 랜덤하게 알파벳 또는 숫자를 선택합니다.
            boolean isLetter = random.nextBoolean();
            if (isLetter) {
                // 알파벳인 경우
                int index = random.nextInt(letters.length());
                password += letters.charAt(index);
            } else {
                // 숫자인 경우
                int index = random.nextInt(numbers.length());
                password += numbers.charAt(index);
            }
        }

        return password;
    }
	public int setPasswordUpdate(MemberVO memberVO) throws Exception{
		return memberDAO.setPasswordUpdate(memberVO);
	}
}
