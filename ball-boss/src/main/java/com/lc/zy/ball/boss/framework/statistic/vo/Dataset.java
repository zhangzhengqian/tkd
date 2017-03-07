package com.lc.zy.ball.boss.framework.statistic.vo;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
	
//	{
//	labels : [ "January", "February", "March", "April", "May", "June","July" ],
//	datasets : [  {
//		fillColor : "rgba(151,187,205,0.5)",
//		strokeColor : "rgba(151,187,205,1)",
//		pointColor : "rgba(151,187,205,1)",
//		pointStrokeColor : "#fff",
//		data : [ 28, 48, 40, 19, 96, 27, 100 ]
//	} ]
//}

	public Dataset(List<Integer> data) {
		super();
		this.data = data;
	}
	//#9F79EE
	//private String fillColor =  "rgba(151,187,255,0.5)";
	private String fillColor =  "rgba(255,255,255,0.1)";
	private String strokeColor =  "rgba(151,187,255,1)";
	private String pointColor =  "rgba(151,187,255,1)";
	private String pointStrokeColor = "#fff";
	private String pointHighlightFill = "#fff";
	private String pointHighlightStroke = null;
	
	private String highlightFill = null;
	private String highlightStroke = null;
	
	private String label = null;
	
	private List<Integer> data = new ArrayList<Integer>();
	
	public String getHighlightFill() {
		return highlightFill;
	}
	public void setHighlightFill(String highlightFill) {
		this.highlightFill = highlightFill;
	}
	public String getHighlightStroke() {
		return highlightStroke;
	}
	public void setHighlightStroke(String highlightStroke) {
		this.highlightStroke = highlightStroke;
	}
	public String getPointHighlightFill() {
		return pointHighlightFill;
	}
	public void setPointHighlightFill(String pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPointHighlightStroke() {
		return pointHighlightStroke;
	}
	public void setPointHighlightStroke(String pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public String getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}
	public String getPointColor() {
		return pointColor;
	}
	public void setPointColor(String pointColor) {
		this.pointColor = pointColor;
	}
	public String getPointStrokeColor() {
		return pointStrokeColor;
	}
	public void setPointStrokeColor(String pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	
}
