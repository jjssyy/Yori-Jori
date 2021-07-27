package com.web.curation.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class SaveRecipeitem {
	private String title;
	private String id;
	private List<MultipartFile> img;
	private List<String> des;
	private List<String> thumbnail;

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

	public List<MultipartFile> getImg() {
		return img;
	}

	public void setImg(List<MultipartFile> img) {
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

}
