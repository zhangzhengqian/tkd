package com.lc.zy.common.search;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class QueryLog {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryLog.class);	
	
    public QueryLog(String schema, String q, String[] fq,Map<String,String> params) {
        super();
        this.schema = schema;
        this.q = q;
        this.fq = fq;
        this.params = params;
    }
    public QueryLog(String schema, String q, String[] fq,Map<String,String> params,List<SortClause> sortList) {
        super();
        this.schema = schema;
        this.q = q;
        this.fq = fq;
        this.params = params;
        this.sortList = sortList;
    }
    
	
	public void writeLog(){
		Gson g = new Gson();
		String j = g.toJson(this);
		logger.info(j);
	}
	
    String schema = null;
	String q = null;
	String[] fq = null;
	Map<String,String> params = null;
	List<SortClause> sortList = null;
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String[] getFq() {
		return fq;
	}
	public void setFq(String[] fq) {
		this.fq = fq;
	}

    public Map<String, String> getParams() {
        return params;
    }
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public List<SortClause> getSortList() {
        return sortList;
    }
    public void setSortList(List<SortClause> sortList) {
        this.sortList = sortList;
    }

}
