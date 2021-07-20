package com.web.curation.model.dao;

import java.util.Map;

import com.web.curation.model.UserVO;

public interface UserDao {
	
	public boolean join(UserVO user) throws Exception;
	
	public UserVO login(Map map) throws Exception;
	
	public String checkid(Map map) throws Exception;
	
	public String checknickname(Map map) throws Exception;
	
	public boolean updateuser(UserVO user) throws Exception;
	
	public boolean updatepw(Map map) throws Exception;
	
	public String oldpw(Map map) throws Exception;

}
