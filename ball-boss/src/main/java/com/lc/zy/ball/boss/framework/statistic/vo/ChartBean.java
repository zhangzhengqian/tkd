package com.lc.zy.ball.boss.framework.statistic.vo;

import java.util.ArrayList;
import java.util.List;

import com.lc.zy.common.util.MyGson;

public class ChartBean {
	// {
	// labels : [ "January", "February", "March", "April", "May",
	// "June","July" ],
	// datasets : [ {
	// fillColor : "rgba(151,187,205,0.5)",
	// strokeColor : "rgba(151,187,205,1)",
	// pointColor : "rgba(151,187,205,1)",
	// pointStrokeColor : "#fff",
	// data : [ 28, 48, 40, 19, 96, 27, 100 ]
	// } ]
	// }
	public ChartBean() {
	}
	
	public ChartBean(List<String> labels, List<Dataset> datasets) {
		super();
		this.labels = labels;
		this.datasets = datasets;
	}

	private List<String> labels = new ArrayList<String>();


	private List<Dataset> datasets = new ArrayList<Dataset>();

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	@Override
	public String toString() {
		return MyGson.getInstance().toJson(this);
	}

}
