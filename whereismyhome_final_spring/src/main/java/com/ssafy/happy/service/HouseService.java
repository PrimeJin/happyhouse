package com.ssafy.happy.service;

import java.util.List;

import com.ssafy.happy.model.dto.House;
import com.ssafy.happy.model.dto.SearchList;
import com.ssafy.happy.model.dto.SidoGugunCode;

public interface HouseService {
	List<SidoGugunCode> getSido() throws Exception;
	List<SidoGugunCode> getGugunInSido(String sido) throws Exception;
	List<House> getDongInGugun(String gugun) throws Exception;
	List<House> getAptInDong(String dong) throws Exception;
	List<SearchList> getSearchList(String keyword) throws Exception;
}
