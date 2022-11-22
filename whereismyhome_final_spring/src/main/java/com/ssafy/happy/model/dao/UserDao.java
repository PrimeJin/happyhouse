package com.ssafy.happy.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happy.model.dto.User;

@Mapper
public interface UserDao {
	public int insert(User user);
	public int update(User user);
	public int delete(String userid);
	public User selectByUserid(String userid);
	
	public void saveRefreshToken(Map<String, String> map);
	public Object getRefreshToken(String userid);
	public void deleteRefreshToken(Map<String, String> map);
}
