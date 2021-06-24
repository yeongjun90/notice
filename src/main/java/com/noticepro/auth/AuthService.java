package com.noticepro.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noticepro.mapper.authMapper;
import com.noticepro.models.UserVO;

@Service
public class AuthService {

	@Autowired
	private authMapper authMapper;

	public Map login_check(UserVO userVO, HttpSession session) {
		Map ret = new HashMap();
		int c = authMapper.login_check(userVO);
		try {
			if (c == 1) {
				UserVO user = authMapper.user(userVO);
				session.setAttribute("userinfo", user);
				ret.put("result", "ok");
			} else {
				ret.put("result", "fail");
			}
		} catch (Exception e) {
			ret.put("result", "fail");
			e.printStackTrace();
		}

		return ret;
	}
}
