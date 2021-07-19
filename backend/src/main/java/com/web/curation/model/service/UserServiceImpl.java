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
	public boolean join(UserVO user) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).join(user);
	}

	@Override
	public String checkid(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).checkid(map);
	}
	
	@Override
	public String checknickname(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).checknickname(map);
	}

}
