package com.iu.base.board.notice;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardService;
import com.iu.base.board.BoardVO;
import com.iu.base.util.FileManager;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class NoticeService implements BoardService{

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Value("${app.upload.notice}")
	private String path;	
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		// TODO Auto-generated method stub
		pager.makeStartRow();
		pager.makeNum(noticeDAO.getTotalCount(pager));
		return noticeDAO.getList(pager);
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getDetail(boardVO);
	}
	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int setInsert(BoardVO boardVO,MultipartFile[] multipartFiles) throws Exception {
		int result = noticeDAO.setInsert(boardVO);
		log.error("Num============>{}",boardVO.getNum());
		
		Random random = new Random();
		int num = random.nextInt(1);
		
		if(num==0) {
			throw new Exception();
		}
		
		if(multipartFiles != null) {
			for(MultipartFile multipartFile:multipartFiles) {
				//조건식 또는 =>multipartFile.getSize() != 0 
				if(!multipartFile.isEmpty()) {
					String fileName = fileManager.saveFile(path, multipartFile);
					BoardFileVO boardFileVO = new BoardFileVO();
					boardFileVO.setFileName(fileName);
					boardFileVO.setOriName(multipartFile.getOriginalFilename());
					boardFileVO.setNum(boardVO.getNum());
					result = noticeDAO.setBoardFileAdd(boardFileVO);
				}
			}
		}
		return result; //noticeDAO.setInsert(boardVO);	
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setDelete(boardVO);
	}
	
	
}
