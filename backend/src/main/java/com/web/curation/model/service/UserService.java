package com.web.curation.model.service;

import java.util.Map;

import com.web.curation.model.UserVO;

public interface UserService {
	
	public boolean join(Map map) throws Exception;

	public UserVO login(Map<String, String> map) throws Exception;
}
