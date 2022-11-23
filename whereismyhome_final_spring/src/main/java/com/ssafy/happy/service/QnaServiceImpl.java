package com.ssafy.happy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happy.model.dao.QnaDao;
import com.ssafy.happy.model.dto.BoardParameterDto;
import com.ssafy.happy.model.dto.Qna;
import com.ssafy.happy.model.dto.QnaReply;
import com.ssafy.happy.util.PageNavigation;

@Service
public class QnaServiceImpl implements QnaService {

	private QnaDao qnaDao;
	
	public QnaServiceImpl(QnaDao qnaDao) {
		this.qnaDao = qnaDao;
	}
	
	@Override
	public int writeQna(Qna qna) {
		return qnaDao.writeQna(qna);
	}

	@Override
	public List<Qna> listQna(BoardParameterDto boardParameterDto) {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return qnaDao.listQna(boardParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		
		int totalCount = qnaDao.getTotalCount(boardParameterDto);

		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public Qna getQna(int qnano) {
		return qnaDao.getQna(qnano);
	}

	@Override
	public void updateHit(int qnano) {
		qnaDao.updateHit(qnano);
	}

	@Override
	public int modifyQna(Qna qna) {
		return qnaDao.modifyQna(qna);
	}

	@Override
	public int deleteQna(int qnano) {
		return qnaDao.deleteQna(qnano);
	}

	@Override
	public int writeQnaReply(QnaReply qnaReply) {
		return qnaDao.writeQnaReply(qnaReply);
	}

	@Override
	public List<QnaReply> listQnaReply(int qnano) {
		return qnaDao.listQnaReply(qnano);
	}
	
}
