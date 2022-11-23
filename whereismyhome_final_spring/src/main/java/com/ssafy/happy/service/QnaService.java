package com.ssafy.happy.service;

import java.util.List;

import com.ssafy.happy.model.dto.BoardParameterDto;
import com.ssafy.happy.model.dto.Qna;
import com.ssafy.happy.model.dto.QnaReply;
import com.ssafy.happy.util.PageNavigation;

public interface QnaService {
	public int writeQna(Qna qna);
	public List<Qna> listQna(BoardParameterDto boardParameterDto);
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto);
	
	
	public Qna getQna(int qnano);
	public void updateHit(int qnano);
	public int modifyQna(Qna qna);
	public int deleteQna(int qnano);

	public int writeQnaReply(QnaReply qnaReply);
	public List<QnaReply> listQnaReply(int qnano);
}
