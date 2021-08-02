package com.web.curation.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.SaveComment;
import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.dao.FeedDao;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeRecipeInfo(RecipeInfo recipeInfo) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeInfo(recipeInfo);
	}

	@Override
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeContent(recipeContent);
	}

	@Override
	public Integer getRecipe_idx(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipe_idx(map);
	}
	
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

	@Override
	public int writeComment(SaveComment comment) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeComment(comment);
	}

}
