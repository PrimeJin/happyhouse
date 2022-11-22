package com.ssafy.happy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happy.model.dto.User;
import com.ssafy.happy.service.JwtService;
import com.ssafy.happy.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	private JwtService jwtService;
	
	public UserController(UserService userService, JwtService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/signUp")
	@ApiOperation(value = "사용자 회원가입", response = Integer.class)
	public ResponseEntity<?> signUp(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		
		int result = userService.regist(user);
		if(result == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	//회원정보 수정
	@PostMapping("/modify")
	@ApiOperation(value = "사용자 정보수정", response = Integer.class)
	public ResponseEntity<?> modify(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		
		int result = userService.modify(user);
		if(result == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	//회원정보 탈퇴
	@GetMapping("/delete/{userid}")
	@ApiOperation(value = "사용자 정보수정", response = Integer.class)
	public ResponseEntity<?> delete(@PathVariable String userid) {
		Map<String, Object> map = new HashMap<>();
		
		int result = userService.remove(userid);
		if(result == 1) {
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.ACCEPTED);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	//회원정보 조회
	@GetMapping("/info/{userid}")
	@ApiOperation(value = "{userid}에 해당하는 사용자 정보를 반환", response = User.class)
	public ResponseEntity<?> getInfo(@PathVariable String userid, HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<>();
		
		if(jwtService.checkToken(request.getHeader("access-token"))) {
			User user = userService.findUserByUserid(userid);
			map.put("userInfo", user);
			map.put("message", "success");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.ACCEPTED);
		} else {
			map.put("message", "fail");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//로그인
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		System.out.println("UserController login user>>>" + user);
		Map<String, Object> map = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.findUserByUserid(user.getUserid());
			System.out.println("====" + loginUser);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
				userService.saveRefreshToken(user.getUserid(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				map.put("access-token", accessToken);
				map.put("refresh-token", refreshToken);
				map.put("message", "success");
				status = HttpStatus.ACCEPTED;
			} else {
				map.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			map.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(map, status);
	}
	
	//로그아웃
	@GetMapping("/logout/{userid}")
	public ResponseEntity<Map<String, String>> logout(@PathVariable("userid") String userid) {
		Map<String, String> map = new HashMap<>();
		
		userService.deleteRefreshToken(userid);
		map.put("message", "success");
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
	}
	
	//access token 재발급
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody User user, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String authToken = request.getHeader("refresh-token");
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if(jwtService.checkToken(authToken)) {
			if(authToken.equals(userService.getRefreshToekn(user.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getUserid());
				
				map.put("access-token", accessToken);
				map.put("message", "success");
			}
		} else {
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String,Object>>(map, status);
	}
}
