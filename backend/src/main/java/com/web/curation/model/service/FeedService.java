package com.web.curation.model.service;

import java.util.List;

import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeSingleContent;

public interface FeedService {
	public List<RecipeSingleContent> getRecipeContents(String recipe_idx) throws Exception;
	public List<RecipeContent> getLatestFeed(String id) throws Exception;
	List<RecipeContent> getAllRecipes(String id) throws Exception;
}
