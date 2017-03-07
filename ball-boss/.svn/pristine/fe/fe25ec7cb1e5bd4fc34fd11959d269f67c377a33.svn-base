package com.lc.zy.ball.boss.framework.orders.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jasypt.commons.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.Constants;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.web.FunctionTag;
import com.lc.zy.ball.boss.framework.orders.vo.PayLogVo;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.PayLogMapper;
import com.lc.zy.ball.domain.oa.mapper.SsoUserMapper;
import com.lc.zy.ball.domain.oa.po.PayLog;
import com.lc.zy.ball.domain.oa.po.PayLogCriteria;
import com.lc.zy.ball.domain.oa.po.SsoUser;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.FreeMarkerUtils;

@Component
@Transactional(readOnly = true)
public class PayLogService extends AbstractCacheService {
    @Autowired
    private SsoUserMapper ssoUserMapper;
    @Autowired
    private PayLogMapper payLogMapper;
    
    @Resource(name="oaJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 页面查询对象.
     * 
     * @param searchParams 查询条件.
     * @param page 分页页号, 基于0.
     * @param size 分页大小.
     * @param isPage 是否分页显示.
     * @return 分页数据.
     * @throws Exception
     */
    public Map<String,Object> find(Map<String, Object> searchParams, int page, int size, boolean isPage, boolean isHasCount)
            throws Exception {
    	Map<String,Object> res = new HashMap<String, Object>();
    	Map<String, Object> root = new HashMap<String, Object>();
        PageRequest pageable = new PageRequest(page, size);

        PayLogCriteria cc = new PayLogCriteria();
        if (isPage) {
            cc.setMysqlOffset(pageable.getOffset());
            cc.setMysqlLength(pageable.getPageSize());
        }
        PayLogCriteria.Criteria cri = cc.createCriteria();
        Map<String, SearchFilter> filters = null;

        if (searchParams != null) {
            filters = SearchFilter.parse(searchParams);
            Criterias.bySearchFilter(cri, filters.values());
            
            if(searchParams.get("GTE_createTime") != null){
            	if(searchParams.get("GTE_createTime") instanceof Date){
            		root.put("GTE_createTime", sdf.format(searchParams.get("GTE_createTime")));
            	}
            }
            
            if(searchParams.get("LTE_createTime") != null){
            	if(searchParams.get("LTE_createTime") instanceof Date){
            		root.put("LTE_createTime", sdf.format(searchParams.get("LTE_createTime")));
            	}
            }
            
            if(searchParams.get("EQ_status") != null){
            	if(StringUtils.isNotBlank((String)searchParams.get("EQ_status"))){
            		root.put("EQ_status", (String)searchParams.get("EQ_status"));
            	}
            }
        }
        cc.setOrderByClause("create_time desc");
        int total = 0;
        if (isHasCount) {
            total = payLogMapper.countByExample(cc);
        }
        
        String sql = FreeMarkerUtils.format("/template/order/bill_list_sum_money_message.ftl", root);
        Map<String,Object> map = jdbcTemplate.queryForMap(sql);
        List<PayLog> list = payLogMapper.selectByExample(cc);

        ArrayList<PayLogVo> arrayList = new ArrayList<PayLogVo>();
        if (list != null) {
            for (PayLog c : list) {
            	PayLogVo vo = new PayLogVo();
                BeanUtils.copyProperties(c, vo);
                vo.setTradeNoStr(vo.getTradeNo()+" ");
                String payTypeStr = "";
                switch (vo.getPayType()){
                    case 1:
                        payTypeStr = "支付宝付款";
                        break;
                    case 2:
                        payTypeStr = "微信付款";
                        break;
                    case 3:
                        payTypeStr = "公众平台付款";
                        break;
                    case 4:
                        payTypeStr = "球友圈付款";
                        break;
                    case 5:
                        payTypeStr = "账户支付";
                        break;
                    case 6:
                        payTypeStr = "运动基金支付";
                        break;
                    case 9:
                        payTypeStr = "招行支付";
                        break;
                    case 11:
                        payTypeStr = "京东支付";
                        break;
                }
                vo.setPayTypeStr(payTypeStr);
                if (CommonUtils.isNotEmpty(vo.getUserId())) {
                    SsoUser user = this.selectByPrimaryKey(SsoUser.class, vo.getUserId());
                    if(user==null){
                    	User user_ = this.selectByPrimaryKey(User.class, vo.getUserId());
                    	vo.setUserNickName(user_.getNickname());
                    	user = new SsoUser();
                    	user.setNickName(user_.getNickname());
                    	vo.setSsoUser(user);
                    }else{
                    	vo.setSsoUser(user);
                    	vo.setUserNickName(user.getNickName());
                    }
                }
                arrayList.add(vo);
                if(Constants.PayStatus.TRADE_SUCCESS.equals(vo.getStatus())){
                	vo.setStatusStr("订单付款");
                }else if(Constants.PayStatus.REFUND_SUCCESS.equals(vo.getStatus())){
                	vo.setStatusStr("订单退款");
                }else{
                	vo.setStatusStr("");
                }
                
                vo.setFeeStr(FunctionTag.formatMoney(vo.getFee()));
                vo.setFinalFeeStr(FunctionTag.formatMoney(vo.getFinalFee()));
            }
        }
        res.put("data", new PageImpl<>(arrayList, pageable, total));
        res.put("sum", map);
        return res;
    }

    /**
     * 根据主键查询对象.
     * 
     * @param id
     * @return 订单.
     * @throws Exception
     */
    public PayLog getPayLog(String id) throws Exception {
        return this.selectByPrimaryKey(PayLog.class, id);
    }
}
