package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	
	private static final String NULL = null;

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private MailManager mailManager;
	
	//@Scheduled(cron = "40 * * * * *")
	public void test() throws Exception {
		
		int result = memberDAO.setEnabledUpdate();
		
		log.error("=====휴먼계정으로 바뀌었습니다======");
		
	}
	
	//@Scheduled(cron = "20 * * * * *")
	public void writer() throws Exception{
		List<MemberVO> ar =  memberDAO.getBirthList();
//		StringBuffer sb = new StringBuffer();
//		sb.append("오늘은 ");
//		
//		for (MemberVO memberVO : ar) {
//			sb.append(memberVO.getName());
//			sb.append(", ");
//		}
//		
//		sb.append(" 생일 입니다. 생일빵~ ");
//		
//		NoticeVO noticeVO = new NoticeVO();
//		noticeVO.setTitle("생축");
//		noticeVO.setWriter("admin");
//		noticeVO.setContents(sb.toString());
//		int result = noticeDAO.setInsert(noticeVO);
//		log.error("=====공지사항이 올라왔습니다======");
		
		for(MemberVO memberVO:ar) {
			mailManager.send(memberVO.getEmail(), "생일축하","<h1>축하해요</h1>");
		}
		log.error("=====메일이 도착했습니다======");
	}
}
