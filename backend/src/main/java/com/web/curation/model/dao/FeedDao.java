package com.web.curation.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.curation.model.CommentFromDB;
import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeSingleContent;
import com.web.curation.model.SaveComment;
import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.SaveRecipeitem;

public interface FeedDao {
	
	public int writeRecipeInfo(RecipeInfo recipeInfo) throws Exception;
	
	public Integer getRecipe_idx(Map map) throws Exception;
	
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception;
	
	public List<RecipeSingleContent> getRecipeContents(String recipe_idx) throws Exception;

	public List<RecipeContent> getLatestFeed(String id) throws Exception;
	
	public List<RecipeContent> getAllRecipes(String id) throws Exception;
	
	public int writeComment(SaveComment comment) throws Exception;
	
	public int updateComment(SaveComment comment) throws Exception;
	
	public int deleteComment(int idx) throws Exception;
	
	public List<CommentFromDB> getCommentList(int content_idx) throws Exception;
	
	public int getLikeCount(int comment_idx) throws Exception;
	
	public int checkLike(Map map) throws Exception;
	
	public int likeComment(Map map) throws Exception;
	
	public int cancelLikeComment(Map map) throws Exception;

	public int writeHashtag(HashMap<String, Object> hash);
	
	public List<Integer> getLikedPosts(String user_id) throws Exception;
	
	public SaveRecipeitem getSingleRecipe(int recipe_idx) throws Exception;
	
	public int getLikeNum(int recipe_idx) throws Exception;
}
