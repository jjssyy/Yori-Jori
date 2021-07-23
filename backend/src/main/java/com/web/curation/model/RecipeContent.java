package com.web.curation.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RecipeContent {
	private int idx;
	private String title;
	private Timestamp regdate;
	private String id;
//	private List<RecipeSingleContent> content;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public List<RecipeSingleContent> getContent() {
//		return content;
//	}
//	public void setContent(List<RecipeSingleContent> content) {
//		this.content = content;
//	}
	
	
}
