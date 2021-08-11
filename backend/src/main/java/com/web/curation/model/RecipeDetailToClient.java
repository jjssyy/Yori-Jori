package com.web.curation.model;

import java.sql.Timestamp;
import java.util.List;

public class RecipeDetailToClient {
	//DB:recipe_content
	private List<RecipeDetailFromDB> recipe_contents;
	
	//DB:recipe_like
	private int like; //좋아요 수
	private boolean likecheck; //내가 이 레시피에 좋아요를 눌렀는지 확인
	
	//DB:recipe
	private String title;
	private Timestamp regdate;
	private String id;
	private String nickname;
	private String achieve_master;
	private String achieve_slave;
	
	public String getAchieve_master() {
		return achieve_master;
	}
	public void setAchieve_master(String achieve_master) {
		this.achieve_master = achieve_master;
	}
	public String getAchieve_slave() {
		return achieve_slave;
	}
	public void setAchieve_slave(String achieve_slave) {
		this.achieve_slave = achieve_slave;
	}
	public List<RecipeDetailFromDB> getRecipe_contents() {
		return recipe_contents;
	}
	public void setRecipe_contents(List<RecipeDetailFromDB> recipe_contents) {
		this.recipe_contents = recipe_contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public boolean isLikecheck() {
		return likecheck;
	}
	public void setLikecheck(boolean likecheck) {
		this.likecheck = likecheck;
	}
}
