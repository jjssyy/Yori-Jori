package com.web.curation.model.dao;

import java.util.Map;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.Recipeitem;

public interface FeedDao {
	
	public int writeRecipeInfo(Map map) throws Exception;
	public Integer getRecipe_idx(Map map) throws Exception;
	public int writeRecipeContent(RecipeContent recipeContent) throws Exception;
	
}
