package com.iu.base.board;

import java.util.List;

public interface BoardService {
	//글리스트 조회
	public List<BoardVO> getSelect() throws Exception;
	
	//글하나 조회(detail)
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	//글쓰기
	public int setInsert(BoardVO boardVO) throws Exception;
	
	//글수정
	public int setUpdate(BoardVO boardVO) throws Exception;
	
	//글삭제
	public int setDelete(BoardVO boardVO) throws Exception;
	
}
