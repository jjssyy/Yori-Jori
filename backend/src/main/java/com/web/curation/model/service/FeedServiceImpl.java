package com.web.curation.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.dao.FeedDao;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<RecipeSingleContent> getRecipeContents(String recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipeContents(recipe_idx);
	}

	@Override
	public List<RecipeContent> getLatestFeed(String id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLatestFeed(id);
	}
	
	@Override
	public List<RecipeContent> getAllRecipes(String id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getAllRecipes(id);
	}
}
