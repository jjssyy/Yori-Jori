package com.web.curation.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.Achieve;
import com.web.curation.model.UserVO;
import com.web.curation.model.dao.AchieveDao;
import com.web.curation.model.dao.SearchDao;

@Service
public class AchieveServiceImpl implements AchieveService {

	@Autowired
	SqlSession sqlsession;

	@Override
	public List<Achieve> getachievemasterlist() throws Exception {
		
		return sqlsession.getMapper(AchieveDao.class).getachievemasterlist();
	}

	@Override
	public List<Achieve> getachieveslavelist() throws Exception {
		
		return sqlsession.getMapper(AchieveDao.class).getachieveslavelist();
	}

	@Override
	public List<Integer> getMasterCount() throws Exception {
		return sqlsession.getMapper(AchieveDao.class).getMasterCount();
	}

}