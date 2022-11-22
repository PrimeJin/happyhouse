package com.ssafy.happy.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happy.model.dto.BoardParameterDto;
import com.ssafy.happy.model.dto.Qna;

@Mapper
public interface QnaDao {
	public int writeQna(Qna qna);
	public List<Qna> listQna(BoardParameterDto boardParameterDto);
	public int getTotalCount(BoardParameterDto boardParameterDto);
	public Qna getQna(int qnano);
	
	public void updateHit(int qnano);
	public int modifyQna(Qna qna);
	public int deleteQna(int qnano);

}
