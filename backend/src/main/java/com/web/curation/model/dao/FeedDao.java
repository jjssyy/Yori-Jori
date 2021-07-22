package com.web.curation.model.dao;

import java.util.List;

import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.UserVO;

public interface FeedDao {
	public List<RecipeSingleContent> getRecipeContents(String recipe_idx) throws Exception;
}
