package com.lc.zy.ball.boss.framework.mall.service;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.Sequence;
import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.pay.alipay.config.AlipayConfig;
import com.lc.zy.ball.boss.common.pay.alipay.util.AlipaySubmit;
import com.lc.zy.ball.boss.common.pay.alipay.util.UtilDate;
import com.lc.zy.ball.boss.framework.mall.vo.CrmMallActivityVo;
import com.lc.zy.ball.boss.framework.mall.vo.CrmMallOrderItemVo;
import com.lc.zy.ball.boss.framework.mall.vo.CrmMallOrderVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.cache.AbstractCacheService;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.bean.OrdeNotifyrMessage;
import com.lc.zy.common.util.*;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.util.payUtils.PayParameter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: liangsh
 * Date: 2016/9/12
 * Time: 17:51
 */
@Service
@Transactional(readOnly = true)
public class MallService extends AbstractCacheService{

    private static Logger logger = LoggerFactory.getLogger(MallService.class);

    @Autowired
    private CrmMallOrderMapper crmMallOrderMapper;

    @Autowired
    private CrmMallActivityMapper crmMallActivityMapper;

    @Autowired
    private CrmMallOrderItemMapper crmMallOrderItemMapper;

    @Autowired
    private CrmMallActivityGoodsMapper crmMallActivityGoodsMapper;

    @Autowired
    private CrmMallGoodsMapper crmMallGoodsMapper;

    @Resource(name = "oaJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CrmMallOrderLogMapper crmMallOrderLogMapper;

    @Autowired
    private PayParameter payParameter;

    @Autowired
    private PayLogMapper payLogMapper;


    /**
     * <订单列表></>
     * @param pageable
     * @param searchParams
     * @return
     */
    public Page<CrmMallOrderVo> find(PageRequest pageable, Map<String, Object> searchParams) throws Exception{
        Object status = searchParams.get("EQ_status");
        CrmMallOrderCriteria crmMallOrderCriteria = new CrmMallOrderCriteria();
        CrmMallOrderCriteria.Criteria cri = crmMallOrderCriteria.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Criterias.bySearchFilter(cri, filters.values());
        crmMallOrderCriteria.setMysqlLength(pageable.getPageSize());
        crmMallOrderCriteria.setMysqlOffset(pageable.getOffset());
        if (status == null || CommonUtils.isEmpty((String)status)) {
            cri.andStatusNotIn(Arrays.asList(0,4));
        }
        crmMallOrderCriteria.setOrderByClause("ct desc");
        int total = crmMallOrderMapper.countByExample(crmMallOrderCriteria);
        List<CrmMallOrder> list = crmMallOrderMapper.selectByExample(crmMallOrderCriteria);
        List<CrmMallOrderVo> voList = new ArrayList<CrmMallOrderVo>();
        for (CrmMallOrder order :list) {
            CrmMallOrderVo vo = new CrmMallOrderVo();
            BeanUtils.copyProperties(order,vo);
            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
            vo.setFeeView(df.format(new BigDecimal(order.getFee()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue()));
            StatiumDetail statium = this.selectByPrimaryKey(StatiumDetail.class,order.getStatiumId());
            if(statium !=null) {
                vo.setStatiumName(statium.getName());
            }
            voList.add(vo);
        }
        return new PageImpl<>(voList, pageable, total);
    }

    /**
     * <订单详情></>
     * @param id
     * @return
     */
    public CrmMallOrderVo orderView(String id)throws Exception{
        CrmMallOrder order = this.selectByPrimaryKey(CrmMallOrder.class,id);
        CrmMallOrderVo orderVo = new CrmMallOrderVo();
        BeanUtils.copyProperties(order,orderVo);
        if(order != null){
            CrmMallOrderItemCriteria itemsC = new CrmMallOrderItemCriteria();
            CrmMallOrderItemCriteria.Criteria itemsCri = itemsC.createCriteria();
            itemsCri.andOrderIdEqualTo(id);
            itemsC.setOrderByClause("id desc");
            int total = crmMallOrderItemMapper.countByExample(itemsC);
            if(total > 0){
                List<CrmMallOrderItem> items = crmMallOrderItemMapper.selectByExample(itemsC);
                List<CrmMallOrderItemVo> itemsVo = new ArrayList<CrmMallOrderItemVo>();
                for (CrmMallOrderItem item:items) {
                    CrmMallOrderItemVo itemVo = new CrmMallOrderItemVo();
                    BeanUtils.copyProperties(item,itemVo);
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                    itemVo.setFeeView(df.format(new BigDecimal(item.getPrice()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue()));
                    CrmMallGoods goods = this.selectByPrimaryKey(CrmMallGoods.class,itemVo.getGoodId());
                    if(goods != null){
                        itemVo.setPhoto(goods.getPhoto());
                    }
                    itemsVo.add(itemVo);
                }
                orderVo.setItems(itemsVo);
            }
            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("0.00");
            orderVo.setFeeView(df.format(new BigDecimal(order.getFee()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue()));
            String shTime = order.getShTime();
            Calendar shCal = Calendar.getInstance();
            shCal.setTime(DateUtil.parse(shTime, "yyyy-MM-dd HH:mm:ss",null));
            shCal.add(Calendar.DAY_OF_MONTH, -1);
            Calendar now = Calendar.getInstance();
            if(now.compareTo(shCal)>0&&orderVo.getDelayNum()>=1){
            	orderVo.setDelayable(true);
            }else{
            	orderVo.setDelayable(false);
            }
        }
        
        return orderVo;
    }
    public  List<CrmMallOrderLog> orderLogs(String id)throws Exception{
        CrmMallOrderLogCriteria c = new CrmMallOrderLogCriteria();
        CrmMallOrderLogCriteria.Criteria cri = c.createCriteria();
        cri.andOrderIdEqualTo(id);
        c.setOrderByClause("ct desc");
        if(crmMallOrderLogMapper.countByExample(c) > 0){
            return  crmMallOrderLogMapper.selectByExample(c);
        }else{
            return new ArrayList<CrmMallOrderLog>();
        }
    }

    /**
     * 申请退款
     *
     * @param orderId
     * @param reason
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void applyRefund(String orderId, String reason) throws Exception {
        CrmMallOrder order = this.selectByPrimaryKey(CrmMallOrder.class, orderId);
       if(order != null){
           // 修改订单
           order.setStatus(6);  //更为退款中
           order.setEt(new Date());
           order.setEb(SessionUtil.currentUserId());
           order.setReason(reason);
           this.updateByPrimaryKeySelective(order, orderId);
           CrmMallOrderLog log = new CrmMallOrderLog();
           log.setId(UUID.get());
           log.setCt(new Date());
           log.setAction("0000001");
           log.setComment("退款中");
           log.setOrderId(orderId);
           log.setUserId(SessionUtil.currentUserId());
           this.insertSelective(log,log.getId());
       }
    }
    
    @Transactional(readOnly = false)
    public String delayOrder(String id) throws Exception {
		CrmMallOrder order = this.selectByPrimaryKey(CrmMallOrder.class,id);
		if(order.getStatus()!=2){
			throw new Exception();
		}
		String shTime = order.getShTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.parse(shTime, "yyyy-MM-dd HH:mm:ss", null));
		cal.add(Calendar.DAY_OF_MONTH, 2);
		order.setShTime(DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
		order.setDelayNum(order.getDelayNum()+1);
		order.setEt(new Date());
		order.setEb(SessionUtil.currentUserId());
		this.updateByPrimaryKeySelective(order,order.getId());
		return order.getShTime();
	}

    /**
     * 确认退款
     *
     * @param id
     */
    @Transactional(readOnly = false)
    public Map<String, Object> confirmRefund(String id) {
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.Result.RESULT, false);
        boolean b = false;
        CrmMallOrder order = null;
        String comment = "";
        // 批次号
        String batch_no = "";
        try {
            order = this.selectByPrimaryKey(CrmMallOrder.class,id);
            if (order == null) {
                comment = "退款失败，订单不存在！";
            } else {
                if (order.getStatus() != 6 ) {
                    comment = "退款失败，订单不是退款中的订单！";
                } else {
                    // 获取组织账户信息
                    String orgCode = "master";
                    Organization organization = payParameter.payKeys(orgCode);
                    // 支付宝退款
                    StringBuffer detail = new StringBuffer();
                    List<CrmMallOrder> orderList = new ArrayList<CrmMallOrder>();
                    orderList.add(order);
                    for (CrmMallOrder or : orderList) {
                        detail.append(or.getNumber());
                        detail.append("^");
                        detail.append((or.getFee() / 100.0D));
                        if (StringUtils.isEmpty(or.getReason())) {
                            detail.append("^协商退款#");
                        } else {
                            detail.append("^");
                            detail.append(or.getReason());
                            detail.append("#");
                        }
                    }
                    // 退款详细数据
                    String detailData = detail.substring(0, detail.length() - 1);
                    batch_no = Sequence.getId();
                    Map<String, Object> res = alipayRefund(orderList, detailData, batch_no, organization);
                    if (res == null || res.get(Constants.Result.RESULT) == null || false == (Boolean) res.get(Constants.Result.RESULT)) {
                        comment = "退款失败，支付宝退款失败！";
                    } else {
                        result.put(Constants.Result.RESULT, true);
                        b = true;
                    }
                }
            }
        } catch (Exception e) {
            comment = "退款失败，操作发生异常！";
            logger.error("退款失败，操作发生异常！订单ID：" + order.getId(), e);
        } finally {
            result.put(Constants.Result.REASON, comment);
            if (order != null) {
                if (b) {
                    returnOrderLog(order, "退款成功！", true, batch_no,true);
                    try {
                        order.setStatus(5);
                        order.setEt(new Date());
                        this.updateByPrimaryKeySelective(order, order.getId());
                    } catch (Exception e) {
                        logger.error("退款时，退款成功后修改订单状态失败！订单ID：" + order.getId(), e);
                    }
                } else {
                    returnOrderLog(order, comment, false, batch_no,false);
                }
            }
        }
        return result;
    }


    /**
     * 支付宝退款
     *
     * @param orderList
     * @param detail
     * @return
     */
    public Map<String, Object> alipayRefund(List<CrmMallOrder> orderList, String detail, String batch_no, Organization organization) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 退款当天日期
            String refund_date = UtilDate.getDateFormatter();
            // 退款笔数
            String batch_num = orderList.size() + "";
            logger.debug("detail_data {}", detail);
            // 把请求参数打包成数组
            Map<String, String> sParaTemp = new HashMap<String, String>();
            // sParaTemp.put("service",
            // "refund_fastpay_by_platform_pwd"); //
            // 有密退款
            sParaTemp.put("service", "refund_fastpay_by_platform_nopwd"); // 无密退款
//			sParaTemp.put("partner", AlipayConfig.partner);
            sParaTemp.put("partner", organization.getPartner());
            sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//			sParaTemp.put("seller_user_id", AlipayConfig.partner);
            sParaTemp.put("seller_user_id", organization.getPartner());
            sParaTemp.put("refund_date", refund_date);
            sParaTemp.put("batch_no", batch_no);
            sParaTemp.put("batch_num", batch_num);
            sParaTemp.put("detail_data", detail);
            sParaTemp.put("private_key", organization.getPrivateKey());
            logger.debug("sParaTemp {}",sParaTemp.toString());
            String data = AlipaySubmit.postRequest(sParaTemp);
            Map<String, String> resultMap = CommonOAUtils.converteToMap(data);
            if ("T".equalsIgnoreCase(resultMap.get("is_success"))) {
                result.put(Constants.Result.RESULT, true);
            } else {
                result.put(Constants.Result.RESULT, false);
                result.put(Constants.Result.REASON, resultMap.get("error"));
                logger.debug("alipayRefund pay error,the error is {}", resultMap.get("error"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void returnOrderLog(CrmMallOrder order, String comment, boolean isPayLog, String batch_no,boolean payLogStatus) {
        // 记录订单日志
        CrmMallOrderLog log = new CrmMallOrderLog();
        log.setId(UUID.get());
        log.setCt(new Date());
        log.setOrderId(order.getId());
        log.setUserId(SessionUtil.currentUserId());
        log.setAction("0000010");
        log.setComment("退款成功");
        crmMallOrderLogMapper.insert(log);

        if (isPayLog) {// 是否记录支付日志 成功才记录
            // 记录支付日志
            PayLog payLog = new PayLog();
            payLog.setId(UUID.get());
            payLog.setTradeNo(order.getTradeNo());
            payLog.setOrderId(order.getId());
            payLog.setUserId(SessionUtil.currentUserId());
            if ( payLogStatus) {
                payLog.setFee(-order.getFee());
            } else {
                payLog.setFee(order.getFee());
            }
            payLog.setStatus("0");
            payLog.setCreateTime(new Date());
            payLog.setOutTradeNo(batch_no);
            payLog.setPayType(order.getPayType());
            payLogMapper.insertSelective(payLog);
        }
    }


    /**
     * <活动列表></>
     * @param pageable
     * @param searchParams
     * @return
     */
    public Page<CrmMallActivityVo> findActivity(PageRequest pageable, Map<String, Object> searchParams)throws Exception{
        CrmMallActivityCriteria crmMallActivityCriteria = new CrmMallActivityCriteria();
        CrmMallActivityCriteria.Criteria cri = crmMallActivityCriteria.createCriteria();
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Criterias.bySearchFilter(cri, filters.values());
        crmMallActivityCriteria.setMysqlLength(pageable.getPageSize());
        crmMallActivityCriteria.setMysqlOffset(pageable.getOffset());
        crmMallActivityCriteria.setOrderByClause("ct desc");
        cri.andDeleteFlagEqualTo(0);
        int total = crmMallActivityMapper.countByExample(crmMallActivityCriteria);
        List<CrmMallActivityVo> voList =  new ArrayList<CrmMallActivityVo>();
        if(total > 0 ) {
            List<CrmMallActivity> list = crmMallActivityMapper.selectByExample(crmMallActivityCriteria);
            for (CrmMallActivity activity:list) {
                CrmMallActivityVo vo = new CrmMallActivityVo();
                BeanUtils.copyProperties(activity,vo);
                //获取活动时间
                CrmMallActivityGoodsCriteria activityGoodsC  = new CrmMallActivityGoodsCriteria();
                CrmMallActivityGoodsCriteria.Criteria activityGoodsCri = activityGoodsC.createCriteria();
                activityGoodsCri.andActivityIdEqualTo(activity.getId());
                activityGoodsCri.andDeleteFlagEqualTo(0);
                if(crmMallActivityGoodsMapper.countByExample(activityGoodsC) > 0) {
                    List<CrmMallActivityGoods> activityGoodsList = crmMallActivityGoodsMapper.selectByExample(activityGoodsC);
                    vo.setStartTime(activityGoodsList.get(0).getStartTime());
                    vo.setEndTime(activityGoodsList.get(0).getEndTime());
                    String nowDay = DateUtil.nowDateString();
                    int start = Integer.valueOf(vo.getStartTime().replace("-",""));
                    int end = Integer.valueOf(vo.getEndTime().replace("-",""));
                    int now = Integer.valueOf(nowDay.replace("-",""));
                    if(  start > now ){
                        vo.setStatusStr("未开始");
                    }else if(start <= now && end >= now){
                        vo.setStatusStr("进行中");
                    }else{
                        vo.setStatusStr("过期");
                    }
                    List<CrmMallGoods> goodsList = new ArrayList<CrmMallGoods>();
                    for (CrmMallActivityGoods activityGoods:activityGoodsList) {
                        CrmMallGoods goods = this.selectByPrimaryKey(CrmMallGoods.class,activityGoods.getGoogId());
                        goodsList.add(goods);
                    }
                    vo.setCrmMallgoodsLength(goodsList.size());
                    vo.setCrmMallGoodsList(goodsList);
                }
                voList.add(vo);
            }
        }
        return new PageImpl<>(voList, pageable, total);
    }

    /**
     * <保存活动></>
     * @param activityVo
     */
    @Transactional(readOnly = false)
    public void saveOrUpdateActivity(CrmMallActivityVo activityVo)throws Exception{
        String activityGoodsGoodIds = activityVo.getGoodsId();
        String tmpGoodIds = activityVo.getGoodsId().replace(",","");
        if((activityGoodsGoodIds.contains(",") || StringUtils.isNotEmpty(activityGoodsGoodIds)) && StringUtils.isNotEmpty(tmpGoodIds)){
            String[] goodIds = activityGoodsGoodIds.split(",");
            CrmMallActivity activity = new CrmMallActivity();

            Boolean isUpdate = false;
            if(activityVo.getRuleType() == 0 &&  StringUtils.isNotEmpty(activityVo.getGoodId())) {
                CrmMallGoods zsGoods = this.selectByPrimaryKey(CrmMallGoods.class, activityVo.getGoodId());
                activityVo.setGoodsName(zsGoods.getName());
            }else{
                activityVo.setBuyAmount(activityVo.getBuyAmount1());
                activityVo.setGiveAmount(activityVo.getGiveAmount1());
            }
            BeanUtils.copyProperties(activityVo,activity);
            if(StringUtils.isEmpty(activity.getId())) {
                activity.setId(UUID.get());
                activity.setCt(new Date());
                activity.setCb(SessionUtil.currentUserId());
                activity.setDeleteFlag(0);
                this.insertSelective(activity,activity.getId());
            }else{
                isUpdate = true;
                activity.setEt(new Date());
                activity.setEb(SessionUtil.currentUserId());
                this.updateByPrimaryKeySelective(activity,activity.getId());
            }
            if(isUpdate) {
                //获取原有CrmMallActivityGoods集合
                CrmMallActivityGoodsCriteria c = new CrmMallActivityGoodsCriteria();
                CrmMallActivityGoodsCriteria.Criteria cri = c.createCriteria();
                cri.andActivityIdEqualTo(activity.getId());
                cri.andDeleteFlagEqualTo(0);
                int activityGoodsTotal = crmMallActivityGoodsMapper.countByExample(c);
                List<CrmMallActivityGoods> activityGoodsList=  crmMallActivityGoodsMapper.selectByExample(c);
                StringBuilder oldGoodIds = new StringBuilder("");
                Map<String,CrmMallActivityGoods> oldGoodIdMap = new HashMap<String,CrmMallActivityGoods>();
                for (CrmMallActivityGoods oldGoods : activityGoodsList) {
                   if(StringUtils.isNotEmpty(oldGoodIds.toString())){
                       oldGoodIds.append(",");
                   }
                    oldGoodIds.append(oldGoods.getGoogId());
                    oldGoodIdMap.put(oldGoods.getGoogId(),oldGoods);
                }
                for (String goodId:goodIds) {
                    if(StringUtils.isNotEmpty(goodId)) {
                        CrmMallActivityGoods activityGoods = new CrmMallActivityGoods();
                        activityGoods.setActivityId(activity.getId());
                        activityGoods.setGoogId(goodId);
                        activityGoods.setStartTime(activityVo.getStartTime());
                        activityGoods.setEndTime(activityVo.getEndTime());
                        if (oldGoodIds.toString().contains(goodId)) {
                        	activityGoods.setId(oldGoodIdMap.get(goodId).getId());
                            this.updateByPrimaryKeySelective(activityGoods, activityGoods.getId());
                            oldGoodIdMap.remove(goodId);
                        } else {
                            activityGoods.setId(UUID.get());
                            activityGoods.setDeleteFlag(0);
                            this.insertSelective(activityGoods, activityGoods.getId());
                        }
                    }
                }
                //删除页面移除的商品
                if(oldGoodIdMap.size() > 0){
                    Iterator it = oldGoodIdMap.keySet().iterator();
                    while(it.hasNext()){
                        CrmMallActivityGoods delActivtyGood = oldGoodIdMap.get(String.valueOf(it.next()));
                        delActivtyGood.setDeleteFlag(1);
                        this.updateByPrimaryKeySelective(delActivtyGood,delActivtyGood.getId());
                    }
                }
            }else {
                for (String goodId:goodIds) {
                    CrmMallActivityGoods activityGoods = new CrmMallActivityGoods();
                    activityGoods.setActivityId(activity.getId());
                    activityGoods.setGoogId(goodId);
                    activityGoods.setStartTime(activityVo.getStartTime());
                    activityGoods.setEndTime(activityVo.getEndTime());
                    activityGoods.setId(UUID.get());
                    activityGoods.setDeleteFlag(0);
                    this.insertSelective(activityGoods, activityGoods.getId());
                }
            }
        }
    }

    /**
     * <获取活动详情></>
     * @param id
     * @return
     * @throws Exception
     */
    public CrmMallActivityVo getByActivityId(String id)throws Exception{
        CrmMallActivityVo vo = new CrmMallActivityVo();
        CrmMallActivity activity = this.selectByPrimaryKey(CrmMallActivity.class,id);
        if(activity != null){
            BeanUtils.copyProperties(activity,vo);
            CrmMallActivityGoodsCriteria c = new CrmMallActivityGoodsCriteria();
            CrmMallActivityGoodsCriteria.Criteria cri = c.createCriteria();
            cri.andActivityIdEqualTo(activity.getId());
            cri.andDeleteFlagEqualTo(0);
            List<CrmMallActivityGoods> activityGoodsList = new ArrayList<CrmMallActivityGoods>();
            if(crmMallActivityGoodsMapper.countByExample(c) > 0){
                activityGoodsList = crmMallActivityGoodsMapper.selectByExample(c);
                List<CrmMallGoods> goodsList = new ArrayList<CrmMallGoods>();
                StringBuilder goodIds = new StringBuilder("");
                for(CrmMallActivityGoods activityGoods:activityGoodsList){
                    CrmMallGoods goods = this.selectByPrimaryKey(CrmMallGoods.class,activityGoods.getGoogId());
                    if(goods != null) {
                        goodsList.add(goods);
                        if(StringUtils.isNotEmpty(goodIds.toString())){goodIds.append(",");}
                        goodIds.append(goods.getId());
                    }
                }
                vo.setGoodsId(goodIds.toString());
                vo.setCrmMallGoodsList(goodsList);
                vo.setStartTime(activityGoodsList.get(0).getStartTime());
                vo.setEndTime(activityGoodsList.get(0).getEndTime());
            }
        }
        return vo;
    }

    public   List<CrmMallGoods> allGoods(int type,String goodsId,String delGoodsId)throws Exception{
        List<CrmMallGoods> goodsList = new ArrayList<CrmMallGoods>();
        CrmMallGoodsCriteria c = new CrmMallGoodsCriteria();
        CrmMallGoodsCriteria.Criteria cri = c.createCriteria();
        cri.andStatusEqualTo(0);
        List<String> goodIdList = new ArrayList<String>();
        List<String> goodsIds = new ArrayList<String>();
        if(StringUtils.isNotEmpty(goodsId) ) {
            if(goodsId.contains(",")) {
                String[] goodsIdArray = goodsId.split(",");
                for (String id:goodsIdArray){
                        if(StringUtils.isNotEmpty(id)){
                            goodsIds.add(id);
                            goodIdList.add(id);
                        }
                }
            }else {
                goodsIds.add(goodsId);
                goodIdList.add(goodsId);
            }
        }
        if(type == 1){
            Map<String, Object> root = new HashMap<String, Object>();
            String sqlList =  FreeMarkerUtils.format("/template/mall/activity_goods.ftl", root);
            List<Map<String,Object>> goodsIdMapList = jdbcTemplate.queryForList(sqlList);

            for (Map<String, Object> map : goodsIdMapList) {
                String goodId = String.valueOf(map.get("goog_id"));
                if(!goodsId.contains(goodId)) {
                    if(StringUtils.isNotEmpty(delGoodsId) && !delGoodsId.contains(goodId)) {
                        goodIdList.add(goodId);
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(goodIdList)) {
                //没参活动的商品
                    cri.andIdNotIn(goodIdList);
            }
        }else if(CollectionUtils.isNotEmpty(goodsIds)){
            cri.andIdNotIn(goodsIds);
        }
        if(crmMallGoodsMapper.countByExample(c)>0){
            goodsList = crmMallGoodsMapper.selectByExample(c);
        }
        return  goodsList;
    }

    @Transactional(readOnly = false)
    public void delByActivityId(String id)throws Exception{
        CrmMallActivity activity = this.selectByPrimaryKey(CrmMallActivity.class,id);
        if(activity != null){
            activity.setDeleteFlag(1);
            activity.setEb(SessionUtil.currentUserId());
            activity.setEt(new Date());
            this.updateByPrimaryKeySelective(activity,activity.getId());
            CrmMallActivityGoodsCriteria c = new CrmMallActivityGoodsCriteria();
            CrmMallActivityGoodsCriteria.Criteria cri = c.createCriteria();
            cri.andActivityIdEqualTo(activity.getId());
            cri.andDeleteFlagEqualTo(0);
            List<CrmMallActivityGoods> activityGoodsList = new ArrayList<CrmMallActivityGoods>();
            if(crmMallActivityGoodsMapper.countByExample(c) > 0){
                activityGoodsList = crmMallActivityGoodsMapper.selectByExample(c);
                for(CrmMallActivityGoods activityGoods:activityGoodsList){
                     activityGoods.setDeleteFlag(1);
                     this.updateByPrimaryKeySelective(activityGoods,activityGoods.getId());
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	String shTime = "2016-09-30 15:37:00";
        Calendar shCal = Calendar.getInstance();
        shCal.setTime(DateUtil.parse(shTime, "yyyy-MM-dd HH:mm:ss",null));
        Calendar now = Calendar.getInstance();
        shCal.add(Calendar.DAY_OF_MONTH, -1);
        if(now.compareTo(shCal)>0){
        	System.out.println("可以延期");
        }
	}

}
