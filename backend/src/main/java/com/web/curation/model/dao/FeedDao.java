package com.web.curation.model.dao;

import java.util.List;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.UserVO;

public interface FeedDao {
	public List<RecipeSingleContent> getRecipeContents(String recipe_idx) throws Exception;

	public List<RecipeContent> getLatestFeed(String id) throws Exception;
	
	public List<RecipeContent> getAllRecipes(String id) throws Exception;
}
