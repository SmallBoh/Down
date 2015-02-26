package com.example.threaddown.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DownThreadEntity implements Serializable {
	private String url;
	//private String progress;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	/*public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public DownThreadEntity(String url, String progress) {
		super();
		this.url = url;
		this.progress = progress;
	}*/

	public DownThreadEntity(String url) {
		super();
		this.url = url;
	}

	public DownThreadEntity() {
		super();
	}

	@Override
	public String toString() {
		return "DownThreadEntity [url=" + url + "]";
	}
	
/*	@Override
	public String toString() {
		return "DownThreadEntity [url=" + url + ", progress=" + progress + "]";
	}*/

}
