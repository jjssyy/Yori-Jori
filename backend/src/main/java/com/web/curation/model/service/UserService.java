package com.web.curation.model.service;

import java.util.Map;

import com.web.curation.model.UserVO;

public interface UserService {
	
	public boolean join(UserVO user) throws Exception;
	
	public String checkid(Map map) throws Exception;
	
	public String checknickname(Map map) throws Exception;

}
