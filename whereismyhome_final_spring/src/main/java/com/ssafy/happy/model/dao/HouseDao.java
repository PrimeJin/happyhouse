package com.ssafy.happy.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happy.model.dto.House;
import com.ssafy.happy.model.dto.SearchList;
import com.ssafy.happy.model.dto.SidoGugunCode;

@Mapper
public interface HouseDao {
	List<SidoGugunCode> getSido() throws SQLException;
	List<SidoGugunCode> getGugunInSido(String sido) throws SQLException;
	List<House> getDongInGugun(String gugun) throws SQLException;
	List<House> getAptInDong(String dong) throws SQLException;
	List<SearchList> getSearchList(String keyword) throws SQLException;
}
