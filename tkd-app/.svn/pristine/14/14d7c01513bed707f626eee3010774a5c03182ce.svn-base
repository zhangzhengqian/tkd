package com.lc.zy.ball.app.service.example;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lc.zy.ball.app.controller.HubController;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.search.PageBean;
import com.lc.zy.common.search.Searcher;
import com.lc.zy.common.util.MyGson;

/**
 * 例子程序
 * 
 * @author liangc
 */
// 指定 service 的名字，即 { "service":"foo" ... }
@Service("searchDemo")
//此注解标明，这个service的方法，默认是需要验证 token 有效性的
//如果在这个类中某些方法需要跳过验证，则在方法上增加此注解并指定值为 false，见 bar 方法
@Auth
public class SearchService {

	private static Logger logger = LoggerFactory.getLogger(HubController.class);
	Gson gson = MyGson.getInstance() ;
	/**
	 * 所有的接口方法，都用统一的输入参数 ClientRequest 其中的 ClientRequest.params 为具体的业务参数
	 * 本例中，我们假设 bar 方法需要参数 name 和 age 成功时返回 hello world
	 * 
	 * @param body
	 * @return "hello world"
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	// 被调用的 method ，即 { "service":"foo","method":"bar" ... }
	@Auth(false)
	public Success bar(ClientRequest request) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		String sn = request.getSn();
		// 假设 name 必填
		Map<String,String> params = new HashMap<String,String>();
		//坐标在哪个属性上
		params.put("sfield", "geo");
		//具体的坐标值
		params.put("pt", "39.988197,116.382351");
		//搜索范围，单位 km
		params.put("d", "10");
		try {
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
			return new Success(sn, true, new KeyValueEntity("page", page));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return new Success(sn, true, new KeyValueEntity("bar", "hello world"));
	}

}