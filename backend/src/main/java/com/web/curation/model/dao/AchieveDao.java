package com.web.curation.model.dao;

import java.util.List;
import java.util.Map;

import com.web.curation.model.Achieve;
import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;

import com.web.curation.model.SaveRecipeContent;

public interface AchieveDao {
	
	List<Achieve> getachievemasterlist() throws Exception;
	
	List<Achieve> getachieveslavelist() throws Exception;
	
	
}