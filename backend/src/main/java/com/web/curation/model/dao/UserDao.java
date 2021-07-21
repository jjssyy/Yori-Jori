package com.web.curation.model.dao;

import java.util.List;
import java.util.Map;

import com.web.curation.model.Changepw;
import com.web.curation.model.UserVO;

public interface UserDao {
	

	public boolean join(Map map) throws Exception;
	
	public UserVO login(Map map) throws Exception;

	public boolean join(UserVO user) throws Exception;
	
	public String checkid(Map map) throws Exception;
	
	public String checknickname(Map map) throws Exception;
	
	public boolean updateuser(UserVO user) throws Exception;

	public boolean updatepw(Changepw changepw) throws Exception;
	
	public String oldpw(Changepw changepw) throws Exception;
	
	public boolean findpw(Map map) throws Exception;
	
	public UserVO userInfo(String id) throws Exception;
	
	public Integer countfollowing(String id) throws Exception;
	public Integer countfollower(String id) throws Exception;
	
	public List<String> followinglist(String id) throws Exception;
	public List<String> followerlist(String id) throws Exception;
	
	public int followingdelete(Map map) throws Exception;
	public int followerdelete(Map map) throws Exception;
	
}
