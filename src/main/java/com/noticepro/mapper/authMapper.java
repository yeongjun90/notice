package com.noticepro.mapper;

import org.apache.ibatis.annotations.Select;

import com.noticepro.models.UserVO;

public interface authMapper {

	@Select("select count(*) c from user where id=#{id} and pwd=#{pwd}")
	public int login_check(UserVO userVO);
	
	@Select("select user_id,id from user where id=#{id} and pwd=#{pwd}")
	public UserVO user(UserVO userVO);
}
