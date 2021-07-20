package com.web.curation.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.Changepw;
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

	@Override
	public UserVO login(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).login(map);
	}

	@Override
	public boolean updateuser(UserVO user) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).updateuser(user);
	}

	@Override

	public boolean updatepw(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).updatepw(map);
	}

	@Override
	public String oldpw(Map map) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).oldpw(map);
	}

	public boolean updatepw(Changepw changepw) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).updatepw(changepw);
	}

	@Override
	public String oldpw(Changepw changepw) throws Exception {
		
		return sqlsession.getMapper(UserDao.class).oldpw(changepw);
	}



}
