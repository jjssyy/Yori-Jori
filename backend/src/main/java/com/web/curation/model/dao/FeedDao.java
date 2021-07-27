package com.web.curation.model.dao;

import java.util.Map;

import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.SaveRecipeitem;

public interface FeedDao {
	
	public int writeRecipeInfo(Map map) throws Exception;
	public Integer getRecipe_idx(Map map) throws Exception;
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception;
	
}
