package com.web.curation.model.service;

import java.util.Map;

import com.web.curation.model.Changepw;
import com.web.curation.model.UserVO;

public interface UserService {
	
	public boolean join(UserVO user) throws Exception;
	
	public UserVO login(Map map) throws Exception;
	
	public String checkid(Map map) throws Exception;
	
	public String checknickname(Map map) throws Exception;
	
	public boolean updateuser(UserVO user) throws Exception;

	public boolean updatepw(Changepw changepw) throws Exception;
	
	public String oldpw(Changepw changepw) throws Exception;
	
	public void emailsend(String email, String pass) throws Exception;
	
	public boolean findpw(Map map) throws Exception;
	
	public UserVO userInfo(String id) throws Exception;
	
	public Integer countfollowing(String id) throws Exception;
	public Integer countfollower(String id) throws Exception;

}
