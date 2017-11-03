package com.example.ggsddu.bean;

import java.io.Serializable;

public class DownloadFileInfo implements Serializable{

	private int id;
	private String url;
	private long length;
	private String name;
	
	public DownloadFileInfo(int id,String url,long length,String name) {
		this.id = id;
		this.url = url;
		this.length = length;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DownloadFileInfo [id=" + id + ", url=" + url + ", length="
				+ length + ", name=" + name + "]";
	}
	
	
	
	
}
