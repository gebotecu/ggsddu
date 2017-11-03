package com.example.ggsddu.bean;


public class ViewHolderTestListViewBean {
	private String id; //节目集ID
	private String name; //节目集名�?
	private String h_picurl; //横海�?
	private String v_picurl; //竖海�?
	private String grade; //评分
	private String objectType; //内容类型
	private String playUrl; //播放地址
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getH_picurl() {
		return h_picurl;
	}
	public void setH_picurl(String h_picurl) {
		this.h_picurl = h_picurl;
	}
	public String getV_picurl() {
		return v_picurl;
	}
	public void setV_picurl(String v_picurl) {
		this.v_picurl = v_picurl;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getPlayUrl() {
		return playUrl;
	}
	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	@Override
	public String toString() {
		return "RelationRecommendInfo [id=" + id + ", name=" + name
				+ ", h_picurl=" + h_picurl + ", v_picurl=" + v_picurl
				+ ", grade=" + grade + ", objectType=" + objectType
				+ ", playUrl=" + playUrl + "]";
	}
	
	
	
}
