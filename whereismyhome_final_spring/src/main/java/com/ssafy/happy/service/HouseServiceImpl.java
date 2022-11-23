package com.ssafy.happy.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happy.model.dao.HouseDao;
import com.ssafy.happy.model.dto.House;
import com.ssafy.happy.model.dto.SearchList;
import com.ssafy.happy.model.dto.SidoGugunCode;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SidoGugunCode> getSido() throws Exception {
		return sqlSession.getMapper(HouseDao.class).getSido();
	}

	@Override
	public List<SidoGugunCode> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseDao.class).getGugunInSido(sido);
	}

	@Override
	public List<House> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseDao.class).getDongInGugun(gugun);
	}

	@Override
	public List<House> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseDao.class).getAptInDong(dong);
	}

	@Override
	public List<SearchList> getSearchList(String keyword) throws Exception {
		return sqlSession.getMapper(HouseDao.class).getSearchList(keyword);
	}

}
