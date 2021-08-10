package com.web.curation.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.curation.model.CommentFromDB;
import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeInfoFromDB;
import com.web.curation.model.RecipeDetailFromDB;
import com.web.curation.model.SaveComment;
import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.SaveRecipeitem;
import com.web.curation.model.UpdateComment;
import com.web.curation.model.UpdateRecipeContentToDB;

public interface FeedDao {
	
	public int writeRecipeInfo(RecipeInfo recipeInfo) throws Exception;
	
	public Integer getRecipe_idx(Map map) throws Exception;
	
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception;
	
	public List<RecipeDetailFromDB> getRecipeContents(int recipe_idx) throws Exception;

	public List<RecipeContent> getLatestFeed(String id) throws Exception;
	
	public List<RecipeContent> getAllRecipes(String id) throws Exception;

	public int writeHashtag(HashMap<String, Object> hash);
	
	public List<Integer> getLikedPosts(String user_id) throws Exception;
	
	public SaveRecipeitem getSingleRecipe(int recipe_idx) throws Exception;
	
	public int getLikeNum(int recipe_idx) throws Exception;
	
	public int writeComment(SaveComment comment) throws Exception;
	
	public int updateComment(UpdateComment comment) throws Exception;
	
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
	
	public int likeRecipe(Map map) throws Exception;
	
	public int cancelLikeRecipe(Map map) throws Exception;
	
	public int writeHashtags(HashMap<String, Object> hash);

	public List<Integer> getMasterCount(String id) throws Exception;
	
	public int updateRecipeInfo(Map map) throws Exception;
	
	public int deleteRecipeContent(int content_idx) throws Exception;
	
	public int updateRecipeContent(UpdateRecipeContentToDB recipeContent) throws Exception;
}
