package com.web.curation.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.AchieveListFromDB;
import com.web.curation.model.CommentFromDB;
import com.web.curation.model.FeedRecipe;
import com.web.curation.model.HashtagVO;
import com.web.curation.model.RecipeContent;
import com.web.curation.model.RecipeInfo;
import com.web.curation.model.RecipeInfoFromDB;
import com.web.curation.model.RecipeDetailFromDB;
import com.web.curation.model.SaveComment;
import com.web.curation.model.SaveRecipeContent;
import com.web.curation.model.SaveRecipeitem;
import com.web.curation.model.UpdateComment;
import com.web.curation.model.UpdateRecipeContentToDB;
import com.web.curation.model.dao.FeedDao;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeRecipeInfo(RecipeInfo recipeInfo) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeInfo(recipeInfo);
	}

	@Override
	public int writeRecipeContent(SaveRecipeContent recipeContent) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeRecipeContent(recipeContent);
	}

	@Override
	public Integer getRecipe_idx(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipe_idx(map);
	}
	
	@Override
	public List<RecipeDetailFromDB> getRecipeContents(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipeContents(recipe_idx);
	}

	@Override
	public List<FeedRecipe> getLatestFeed(String id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLatestFeed(id);
	}
	
	@Override
	public List<RecipeContent> getAllRecipes(String id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getAllRecipes(id);
	}
	@Override
	public List<Integer> getLikedPosts(String user_id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLikedPosts(user_id);
	}

	@Override
	public SaveRecipeitem getSingleRecipe(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getSingleRecipe(recipe_idx);
	}

//	@Override
//	public int writeHashtags(HashMap<String, Object> hash) {
//		return sqlSession.getMapper(FeedDao.class).writeHashtag(hash);
	public int getLikeNum(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLikeNum(recipe_idx);
	}

	public int writeComment(SaveComment comment) throws Exception {
		return sqlSession.getMapper(FeedDao.class).writeComment(comment);
	}

	@Override
	public int updateComment(UpdateComment comment) throws Exception {
		return sqlSession.getMapper(FeedDao.class).updateComment(comment);
	}

	@Override
	public int deleteComment(int idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).deleteComment(idx);
	}

	@Override
	public List<CommentFromDB> getCommentList(int content_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getCommentList(content_idx);
	}

	@Override
	public int getLikeCountComment(int comment_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLikeCountComment(comment_idx);
	}

	@Override
	public int checkLikeComment(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).checkLikeComment(map);
	}

	@Override
	public int likeComment(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).likeComment(map);
	}

	@Override
	public int cancelLikeComment(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).cancelLikeComment(map);
	}

	@Override
	public int deleteRecipe(int idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).deleteRecipe(idx);
	}

	@Override
	public int getLikeCountRecipe(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getLikeCountRecipe(recipe_idx);
	}

	@Override
	public int checkLikeRecipe(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).checkLikeRecipe(map);
	}

	@Override
	public RecipeInfoFromDB getRecipeInfo(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getRecipeInfo(recipe_idx);
	}

	@Override
	public int likeRecipe(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).likeRecipe(map);
	}

	@Override
	public int cancelLikeRecipe(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).cancelLikeRecipe(map);
	}

	@Override
	public int updateRecipeInfo(Map map) throws Exception {
		return sqlSession.getMapper(FeedDao.class).updateRecipeInfo(map);
	}

	@Override
	public int deleteRecipeContent(int content_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).deleteRecipeContent(content_idx);
	}

	@Override
	public int updateRecipeContent(UpdateRecipeContentToDB recipeContent) throws Exception {
		return sqlSession.getMapper(FeedDao.class).updateRecipeContent(recipeContent);
	}
	

	@Override
	public List<Integer> getMasterCount(String id) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getMasterCount(id);
	}

	@Override
	public int writeHashtags(HashMap<String, Object> hash) {
		
		return sqlSession.getMapper(FeedDao.class).writeHashtags(hash);
	}

	@Override
	public List<AchieveListFromDB> getAchieveListForRecipe() throws Exception {
		return sqlSession.getMapper(FeedDao.class).getAchieveListForRecipe();
	}

	@Override
	public List<RecipeContent> popularPosts() throws Exception {
		return sqlSession.getMapper(FeedDao.class).popularPosts();
	public List<HashtagVO> getHashtag(int recipe_idx) throws Exception {
		return sqlSession.getMapper(FeedDao.class).getHashtag(recipe_idx);
	}

}
