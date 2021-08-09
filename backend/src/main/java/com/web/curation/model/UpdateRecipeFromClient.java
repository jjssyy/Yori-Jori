package com.web.curation.model;

import java.util.List;

public class UpdateRecipeFromClient {
	private int recipe_idx;
	private String title;

	private List<Integer> content_idx; //-1이면 삽입, 아니면 수정
	private List<String> img;
	private List<String> des;
	private List<String> thumbnail;
	private List<Integer> content_order;
	private List<Integer> deleteContents; //삭제한 content들의 idx들
	
	public List<Integer> getDeleteContents() {
		return deleteContents;
	}
	public void setDeleteContents(List<Integer> deleteContents) {
		this.deleteContents = deleteContents;
	}
	public int getRecipe_idx() {
		return recipe_idx;
	}
	public void setRecipe_idx(int recipe_idx) {
		this.recipe_idx = recipe_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getContent_idx() {
		return content_idx;
	}
	public void setContent_idx(List<Integer> content_idx) {
		this.content_idx = content_idx;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public List<String> getDes() {
		return des;
	}
	public void setDes(List<String> des) {
		this.des = des;
	}
	public List<String> getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(List<String> thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<Integer> getContent_order() {
		return content_order;
	}
	public void setContent_order(List<Integer> content_order) {
		this.content_order = content_order;
	}
}
