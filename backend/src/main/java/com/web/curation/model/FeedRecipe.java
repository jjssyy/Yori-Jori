package com.web.curation.model;

import java.sql.Timestamp;

public class FeedRecipe {
	private int idx;
	private String title;
	private Timestamp regdate;
	private String nickname;
	private String id;
	private String img;
	private int comment;
	
	//DB:recipe_like
	private int like; //좋아요 수
	private boolean likecheck; //내가 이 레시피에 좋아요를 눌렀는지 확인
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	
}
