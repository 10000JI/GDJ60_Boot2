package com.iu.base.board.qna;

import com.iu.base.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaVO extends BoardVO {
	public Long ref;
	public Long step;
	public Long depth;

}
