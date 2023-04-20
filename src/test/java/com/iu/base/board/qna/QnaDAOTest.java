package com.iu.base.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.util.Pager;

@SpringBootTest
class QnaDAOTest {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	//@Test
	void setListTest() throws Exception {
		Pager pager = new Pager();
		pager.makeStartRow();
		pager.makeNum(1L);
		List<BoardVO> ar = qnaDAO.getList(pager);
		assertNotNull(ar.size());
	}
	
	@Test
		void setInsertTest() throws Exception{
			BoardVO boardVO = new QnaVO();
			
			boardVO.setWriter("iu");
			boardVO.setTitle("Title Test");
			boardVO.setContents("Contents Test");
			
			int result = qnaDAO.setInsert(boardVO);
			assertEquals(1, result);
		}

}
