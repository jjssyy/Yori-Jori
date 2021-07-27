package com.web.curation.model.service;

import java.util.Map;

import com.web.curation.model.SaveRecipeContent;

public interface FeedService {
	public int writeRecipeInfo(Map map) throws Exception;
	public Integer getRecipe_idx(Map map) throws Exception;
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception;
}
