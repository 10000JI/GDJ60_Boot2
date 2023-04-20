package com.iu.base.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.iu.base.board.BoardDAO;

@Mapper
public interface QnaDAO extends BoardDAO{
	
	public int setInsertStepUpdate(QnaVO qnaVO) throws Exception;
	
	public int setReplyAdd(QnaVO qnaVO) throws Exception;
	
	public int setStepUpdate(QnaVO qnaVO) throws Exception;
}
