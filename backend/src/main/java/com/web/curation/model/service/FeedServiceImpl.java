package com.web.curation.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.Recipeitem;
import com.web.curation.model.dao.FeedDao;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeRecipeInfo(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeInfo(map);
	}

	@Override
	public int writeRecipeContent(RecipeContent recipeContent) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeContent(recipeContent);
	}

	@Override
	public Integer getRecipe_idx(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipe_idx(map);
	}

}
