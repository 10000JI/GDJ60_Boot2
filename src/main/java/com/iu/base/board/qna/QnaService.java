package com.iu.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.FileManager;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Value("${app.upload.qna}")
	private String path;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeStartRow();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		return qnaDAO.getList(pager);
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.getDetail(boardVO);
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] multipartFiles) throws Exception {
		int result = qnaDAO.setInsert(boardVO);
		result = qnaDAO.setInsertStepUpdate((QnaVO) boardVO);
		if(multipartFiles != null) {
			for(MultipartFile multipartFile:multipartFiles) {
				//조건식 또는 =>multipartFile.getSize() != 0 
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = qnaDAO.setBoardFileAdd(boardFileVO);
				}
			}
		}
		return result;
	}
	
	public int setReplyAdd(QnaVO qnaVO) throws Exception{
		//QnaDTO
		//num : 부모의 글번호
		//writer,title,contents :답글로 입력한 값
		//ref: null
		//step: null
		//depth: null
		
		//1. 부모의 정보를 조회
		QnaVO  parent = (QnaVO) qnaDAO.getDetail(qnaVO);
		
		//ref: 부모의 ref
		qnaVO.setRef(parent.getRef());
		
		//step: 부모의 step+1
		qnaVO.setStep(parent.getStep()+1);
		
		//depth: 부모의 depth+1
		qnaVO.setDepth(parent.getDepth()+1);
		
		//2. Step을 update
		int result = qnaDAO.setStepUpdate(qnaVO);
		
		//3. 답글 insert
		result = qnaDAO.setReplyAdd(qnaVO);			
		return result;
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
