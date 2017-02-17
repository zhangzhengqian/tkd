package com.lc.zy.message.consumer.solr;

import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.ball.solr.StatiumBean;
import com.lc.zy.common.Constants;
import com.lc.zy.common.search.Searcher;
import com.lc.zy.common.util.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 全文检索 solrj 的封装
 * 
 * @author sl
 */
@Component
public class StatiumSolr {

    private static final Logger logger = LoggerFactory.getLogger(StatiumSolr.class);
    
    @Autowired
    private StatiumInfosMapper statiumInfosMapper = null;

    @Autowired
    private StatiumClassMapper statiumClassMapper = null;

    // solr每次同步数量
    private static Integer number = 10;

    private Collection<StatiumBean> docs;

    private HttpSolrServer server;

    private UpdateResponse ur;

    public static final String SOLR_URL = SpringUtils.getBean("solrServer").toString() + "core0/";

    /**
     * 插入或更新索引
     * 
     * @param statiumBeanList
     * @throws Exception
     */
    public void setDocs(Collection<StatiumBean> statiumBeanList) throws Exception {
        server = new HttpSolrServer(SOLR_URL);
        logger.debug("SOLR_URL={}", SOLR_URL);
        for (StatiumBean bean : statiumBeanList) {
            logger.debug("bean={}\n...", bean);
            break;
        }
        server.setRequestWriter(new BinaryRequestWriter());
        ur = server.addBeans(statiumBeanList.iterator());
        ur = server.commit();
        String rtn = ur.toString();
        logger.debug("solr 同步返回值 {}", rtn);
    }

    /**
     * 删除索引
     * 
     * @param restUrl rest服务地址
     * @param id 要删除的id集合
     * @throws Exception
     */
    public void delDocs(String restUrl, String... id) throws Exception {
        HttpSolrServer server = new HttpSolrServer(restUrl);
        List<String> ids = Arrays.asList(id);
        UpdateResponse ur = server.deleteById(ids);
        logger.debug(ur.toString());
        ur = server.commit();
        logger.debug(ur.toString());
    }

    /**
     * 
     * <同步场馆信息><功能具体实现>
     *
     * @create：2015年8月25日 下午3:12:08
     * @author： sl
     * @param statiumId
     * @throws Exception
     */
    public void startium(String statiumId) throws Exception {
        // statiumId == all 则删除全部数据再同步
        if (statiumId.equals(Constants.ALL)) {
            // Searcher.delAll(SOLR_URL);
            statiumId = null;
        }
        // 根据场馆id获取场馆信息
        List<StatiumInfos> startiumList = this.selectStatiumDetail(statiumId);
        if (startiumList.isEmpty()) {
        	Searcher.delDocs(SOLR_URL, statiumId);
        } else {
        	docs = new ArrayList<StatiumBean>();
        	StatiumInfos statium = null;
        	for (int j = 0; j < startiumList.size(); j++) {
        		statium = startiumList.get(j);
        		StatiumBean statiumBean = new StatiumBean();
        		
        		// 不同步经度为空、纬度为空、经度不在-90~90、纬度不再 -180 ~ 180
        		if (statium.getLat() == null || statium.getLng() == null || statium.getLat() > 90 || statium.getLat() < -90
        				|| statium.getLng() > 180 || statium.getLng() < -180) {
        			Searcher.delDocs(SOLR_URL, statium.getId());
        			continue;
        		}
        		// 经纬度
        		statiumBean.setLat(statium.getLat());
        		statiumBean.setLng(statium.getLng());
        		statiumBean.setGeo(statiumBean.getLat() + "," + statiumBean.getLng());
        		
        		// 场馆id
        		statiumBean.setId(statiumId);
        		// 场馆地址
        		statiumBean.setAddress(statium.getAddress());
        		// 地区
        		statiumBean.setArea(statium.getArea());
        		statiumBean.setCity(statium.getProvince() + statium.getCity());
        		
        		// 场馆照片
        		String photo = null;
        		if (StringUtils.isNotEmpty(statium.getPhotos())) {
        			photo = statium.getPhotos().replace("__", ";");
        			statiumBean.setPhoto_list(photo);
        		}
        		// 公告
        		statiumBean.setIntroduce(statium.getRemark());
        		// 名称
        		statiumBean.setName(statium.getDgName());
        		// 电话
        		statiumBean.setPhone(statium.getTel());
        		// 价格
        		StatiumClassCriteria statiumClassCriteria = new StatiumClassCriteria();
        		StatiumClassCriteria.Criteria classCri = statiumClassCriteria.createCriteria();
        		classCri.andStatiumIdEqualTo(Integer.valueOf(statiumId));
        		statiumClassCriteria.setOrderByClause("discount_price asc");
        		List<StatiumClass> statiumClasses = statiumClassMapper.selectByExample(statiumClassCriteria);
        		if (!statiumClasses.isEmpty()) {
        			// 原价
        			String price = String.valueOf(statiumClasses.get(0).getPrice() / 100);
        			statiumBean.setStatiumPrice(price);
        			// 最低价
        			String minPrice = String.valueOf(statiumClasses.get(0).getDiscountPrice() / 100);
        			statiumBean.setMinPrice(minPrice);
        			// 折扣价
        			String discountPrice = String.valueOf(statiumClasses.get(0).getDiscountPrice() / 100);
        			statiumBean.setDiscountPrice(discountPrice);
        			// 限时价
        			// String limitPrice =
        			// String.valueOf(statiumClasses.get(0).getLimitPrice()/100);
        			// statiumBean.setLimitPrice(limitPrice);
        			// 折扣
        			String discount = String.valueOf(statiumClasses.get(0).getDiscount());
        			statiumBean.setDiscount(discount);
        		}
                // 推荐
                statiumBean.setRecommend(statium.getRecommend());
        		
        		// 场馆评星
        		Integer score = 10;
        		BigDecimal decimal1 = new BigDecimal(score); // 场馆评星总分
        		BigDecimal decimal2 = new BigDecimal(2);
        		decimal1 = decimal1.divide(decimal2);
        		Double grade = decimal1.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue(); // 四舍五入，保留一位
        		// 球场评分平均分
        		statiumBean.setComment(grade);
                // 审核状态
                statiumBean.setStatus(statium.getStatus());
        		// statiumBean.setSales(ordersCounter.fetch(statium.getId()));
        		logger.debug("标志位 ====> " + statiumBean.getId() + "====> " + statiumBean.getName());
        		logger.debug(statiumBean.toString());
        		docs.add(statiumBean);
        		if (docs.size() == number || startiumList.size() == (j + 1)) {
        			setDocs(docs);
        			docs = new ArrayList<StatiumBean>();
        		}
        	}
        }
        logger.debug("同步结束================");
    }


    /**
     * 
     * <优化solr索引><功能具体实现>
     * 
     * @throws IOException
     * @throws SolrServerException
     * @create：2015年8月25日 下午3:51:31
     * @author： sl
     */
    public void optimize() throws SolrServerException, IOException {
        HttpSolrServer server = new HttpSolrServer(SOLR_URL);
        server.optimize();
    }

    /**
     * 
     * <查询场馆信息><功能具体实现>
     *
     * @create：2015年8月25日 下午3:11:18
     * @author： sl
     * @param statiumId
     * @return
     */
    public List<StatiumInfos> selectStatiumDetail(String statiumId) {
    	StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
    	StatiumInfosCriteria.Criteria criteria = statiumInfosCriteria.createCriteria();
        if (StringUtils.isNotEmpty(statiumId)) {
            criteria.andDgIdEqualTo(Integer.valueOf(statiumId));
        }
        statiumInfosCriteria.setOrderByClause("et desc");
        List<StatiumInfos> list = statiumInfosMapper.selectByExample(statiumInfosCriteria);
        return list;
    }

}
