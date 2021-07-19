package com.web.curation.model.dao;

import java.util.Map;

import com.web.curation.model.UserVO;

public interface UserDao {
	
	public boolean join(Map map) throws Exception;
	
	public UserVO login(Map map) throws Exception;
}
