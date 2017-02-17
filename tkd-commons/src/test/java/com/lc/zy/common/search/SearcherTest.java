package com.lc.zy.common.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.junit.Test;

import com.google.gson.Gson;
import com.lc.zy.common.util.MyGson;

public class SearcherTest {

	////@Test
	public void setTest() throws Exception{
		StatiumBeanTest bean = new StatiumBeanTest();
		bean.setId("foo2");
		bean.setName("hello world");
		Map<String,Object> dmap = new HashMap<String,Object>();
		dmap.put("sport_0_min_price", 211);
		dmap.put("sport_1_min_price", 132);
		dmap.put("sport_2_min_price", 320);
		bean.setDmap(dmap);
		Searcher.setDocs(bean);

	}
	
	
	//@Test
	public void getTest() throws Exception {
		PageBean<StatiumBeanTest> page = Searcher.query(
				StatiumBeanTest.class, 
				new StatiumBeanTest().getRestUrl(), 
				//分页条件
				0, 100, 
				//q 参数
				"sport_0_min_price:101", 
				//fq 参数
				null,
				//fl 参数
				null,null,
				//指定按照距离 升序
				new ArrayList<SortClause>()
		);
		Gson g = MyGson.getInstance();
		System.out.println( g.toJson(page));
	}	
	
	/**
	 * 查找附近的场馆，
	 * 按照距离由远到近，
	 * 距离由solr返回
	 * @throws Exception
	 */
	////@Test
	public void geodistTest() throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		//坐标在哪个属性上
		params.put("sfield", "geo");
		//具体的坐标值
		params.put("pt", "39.988197,116.382351");
		//搜索范围，单位 km
		params.put("d", "10");
		PageBean<StatiumBeanTest> page = Searcher.query(
				StatiumBeanTest.class, 
				new StatiumBeanTest().getRestUrl(), 
				//分页条件
				0, 100, 
				//q 参数
				"*:*", 
				//fq 参数
				//这里使用了 geodist 函数，此函数需要 sfield 和 pt 作参数，用来计算距离
				new String[]{ "{!func}geodist()"} ,
				//fl 参数
				//这里指定返回的结果集，其中最重要的是 dist:geodist() ，表示 dist 这个字段需要返回并且值由 geodist() 函数填充
				//在 对应的 StatiumBeanTest 中，也要增加 dist 属性，类型 double ，并增加 @Field 注解
				//如果不使用solr 提供距离服务，则不必添加 dist 属性
				new String[]{"id","geo","sport_type","dist:geodist()"}, 
				params 
				//指定按照距离 升序
				,SortClause.create("geodist()", ORDER.asc)
		);
		Gson g = MyGson.getInstance();
		System.out.println( g.toJson(page));
	}
	
	////@Test
	public void geofiltTest() throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		//坐标在哪个属性上
		params.put("sfield", "geo");
		//具体的坐标值
		params.put("pt", "39.988197,116.382351");
		//搜索范围，单位 km
		params.put("d", "10");
		PageBean<StatiumBeanTest> page = Searcher.query(
				StatiumBeanTest.class, 
				new StatiumBeanTest().getRestUrl(), 
				//分页条件
				0, 100, 
				//q 参数
				"*:*", 
				//fq 参数
				new String[]{ "{!geofilt}"} ,
				//fl 参数
				new String[]{"id","geo","sport_type","dist:geodist()"}, 
				params 
				//指定按照距离 升序
				,SortClause.create("geodist()", ORDER.asc)
		);
		Gson g = MyGson.getInstance();
		System.out.println( g.toJson(page));
	}
//	//@Test
	public void outofmemTest() throws Exception{
		//{"schema":"StatiumBean","q":"sport_type:羽毛球","fq":["{!geofilt}"],"params":{"d":"5.0","indent":"true","sfield":"geo","pt":"39.987373,116.382623","wt":"json"}}
		Map<String,String> params = new HashMap<String,String>();
		//坐标在哪个属性上
		params.put("sfield", "geo");
		//具体的坐标值
		params.put("pt", "39.987373,116.382623");
		//搜索范围，单位 km
		params.put("d", "5.0");
		PageBean<StatiumBeanTest> page = Searcher.query(
				StatiumBeanTest.class, 
				new StatiumBeanTest().getRestUrl(), 
				//分页条件
				0, 100, 
				//q 参数
				"*:*", 
				//fq 参数
				new String[]{ "{!geofilt}"} ,
				//fl 参数
				null, 
				params 
				//指定按照距离 升序
				,SortClause.create("geodist()", ORDER.asc)
		);
		Gson g = MyGson.getInstance();
		System.out.println( g.toJson(page));
	}
}
