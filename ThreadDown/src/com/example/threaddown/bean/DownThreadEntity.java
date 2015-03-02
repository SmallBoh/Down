package com.example.threaddown.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DownThreadEntity implements Serializable {
	private String url;
	private String progress;
	private String resultText;
	private boolean loading;
	
	public boolean isLoading() {
		return loading;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	public DownThreadEntity(String url) {
		super();
		this.url = url;
	}

	public DownThreadEntity(String url, String progress, String resultText) {
		super();
		this.url = url;
		this.progress = progress;
		this.resultText = resultText;
	}

	public DownThreadEntity() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	@Override
	public String toString() {
		return "DownThreadEntity [url=" + url + ", progress=" + progress
				+ ", resultText=" + resultText + "]";
	}

}
