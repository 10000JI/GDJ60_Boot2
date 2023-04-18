package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;

@SpringBootTest
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void setInsertTest() throws Exception{
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setWriter("IU");
		boardVO.setTitle("Insert Test");
		boardVO.setContents("Insert Test");
		
		int result = noticeDAO.setInsert(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	void setUpdateTest() throws Exception{
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setTitle("점심시간");
		boardVO.setNum(2L);
		
		int result = noticeDAO.setUpdate(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	void setDeleteTest() throws Exception{
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setNum(2L);
		
		int result = noticeDAO.setDelete(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	void getDetailTest() throws Exception{
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setNum(1L);
		
		boardVO = noticeDAO.getDetail(boardVO);
		assertNotNull(boardVO);
	}
	
	//@Test
	void getSelectTest() throws Exception{
		List<BoardVO> ar = noticeDAO.getSelect();
		assertNotNull(ar.size());
	}
	
	@Test
	void getTotalCountTest() throws Exception{
		Long result = noticeDAO.getTotalCount();
		System.out.println(result);
		assertEquals(1, result);
	}
}
