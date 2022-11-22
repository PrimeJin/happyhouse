package com.ssafy.happy.service;

import com.ssafy.happy.model.dto.User;

public interface UserService {
	public int regist(User user);
	public int modify(User user);
	public int remove(String userid);
	public User findUserByUserid(String userid);
	
	public void saveRefreshToken(String userid, String refreshToken);
	public Object getRefreshToekn(String userid);
	public void deleteRefreshToken(String userid);
}
