package com.noticepro.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noticepro.mapper.authMapper;
import com.noticepro.models.UserVO;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "login_check", method = RequestMethod.POST)
	public Map login_check(UserVO userVO, HttpSession session) {
		return authService.login_check(userVO, session);
	}
}
