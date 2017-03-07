package com.lc.zy.ball.boss.framework.publishhtml.vo;

import java.util.ArrayList;
import java.util.List;

public class FileInfo{
	

	private String name;
	
	private String path;
	
	private List<FileInfo> children = new ArrayList<FileInfo>();


	public List<FileInfo> getChildren() {
		return children;
	}

	public void setChildren(List<FileInfo> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
