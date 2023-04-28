package com.iu.base.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}

	//list
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv, Pager pager) throws Exception{
		
		List<BoardVO> ar = qnaService.getList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = qnaService.getDetail(qnaVO);
		
		mv.addObject("boardVO", boardVO);
		mv.setViewName("board/detail");                                                                                                    
		
		return mv;
	}
	
	@GetMapping("reply")
	public ModelAndView setReplyAdd(QnaVO qnaVO, ModelAndView mv) throws Exception{
		mv.setViewName("board/reply");
		return mv;
	}
	
	@PostMapping("reply")
	public ModelAndView setReplyAdd(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = qnaService.setReplyAdd(qnaVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	//add
	@GetMapping("add")
	public ModelAndView setInsert(@ModelAttribute BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/add");
		return mv;
	}
		
	@PostMapping("add")
	public ModelAndView setInsert(QnaVO qnaVO,MultipartFile[] boardFiles) throws Exception{
		for(MultipartFile multipartFile:boardFiles) {
			log.info("OriginalName: {} Size: {}", multipartFile.getOriginalFilename(), multipartFile.getSize());
			}
		int result = qnaService.setInsert(qnaVO, boardFiles);
		//boardFiles는 jsp에서 온 파라미터 이름과 동일하게 하려고, Service는 어떤 이름이 와도 상관X
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:./list");
		return mv;
	}
}
