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
	public UserVO getuser(Map map) {
		
		return sqlsession.getMapper(UserDao.class).getuser(map);
	}

}
