package com.ssafy.happy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happy.model.dao.UserDao;
import com.ssafy.happy.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public int regist(User user) {
		return userDao.insert(user);
	}

	@Override
	public int modify(User user) {
		return userDao.update(user);
	}

	@Override
	public int remove(String userid) {
		return userDao.delete(userid);
	}

	@Override
	public User findUserByUserid(String userid) {
		return userDao.selectByUserid(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("authtoken", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToekn(String userid) {
		return userDao.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("authtoekn", null);	//토큰 null로 초기화
		userDao.deleteRefreshToken(map);
	}

}
