package com.web.curation.model;

import java.sql.Timestamp;

public class RecipeContent {
	private int idx;
	private String title;
	private Timestamp regdate;
	private String nickname;
	private String id;
	private String img;
	private String achieve_master;
	private String achieve_slave;
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
}
