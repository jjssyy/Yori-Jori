package com.web.curation.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.UserVO;
import com.web.curation.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	SqlSession sqlsession;

	@Override
	public boolean join(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).join(map);
	}

	@Override
	public UserVO login(Map<String, String> map) throws Exception {
		
		if(map.get("email") == null || map.get("password") == null) return null;
		
		return sqlsession.getMapper(UserDao.class).login(map);
	}

}
