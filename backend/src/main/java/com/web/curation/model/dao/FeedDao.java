package com.web.curation.model.dao;

import java.util.List;
import java.util.Map;

import com.web.curation.model.CommentFromDB;
import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeInfoFromDB;
import com.web.curation.model.RecipeDetailFromDB;
import com.web.curation.model.SaveComment;
import com.web.curation.model.SaveRecipeContent;

public interface FeedDao {
	
	public int writeRecipeInfo(RecipeInfo recipeInfo) throws Exception;
	
	public Integer getRecipe_idx(Map map) throws Exception;
	
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception;
	
	public List<RecipeDetailFromDB> getRecipeContents(int recipe_idx) throws Exception;

	public List<RecipeContent> getLatestFeed(String id) throws Exception;
	
	public List<RecipeContent> getAllRecipes(String id) throws Exception;
	
	public int writeComment(SaveComment comment) throws Exception;
	
	public int updateComment(SaveComment comment) throws Exception;
	
	public int deleteComment(int idx) throws Exception;
	
	public List<CommentFromDB> getCommentList(int content_idx) throws Exception;
	
	public int getLikeCountComment(int comment_idx) throws Exception;
	
	public int checkLikeComment(Map map) throws Exception;
	
	public int likeComment(Map map) throws Exception;
	
	public int cancelLikeComment(Map map) throws Exception;
	
	public int deleteRecipe(int idx) throws Exception;
	
	public int getLikeCountRecipe(int recipe_idx) throws Exception;

	public int checkLikeRecipe(Map map) throws Exception;
	
	public RecipeInfoFromDB getRecipeInfo(int recipe_idx) throws Exception;
	
}
