package com.lc.zy.ball.boss.framework.vip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.vip.vo.VipCardLogVo;
import com.lc.zy.ball.boss.framework.vip.vo.VipCardVo;
import com.lc.zy.ball.boss.framework.vip.vo.VipLogVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.mapper.VipCardMapper;
import com.lc.zy.ball.domain.oa.mapper.VipLogMapper;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;
import com.lc.zy.ball.domain.oa.po.OrderItem;
import com.lc.zy.ball.domain.oa.po.OrderItemCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.User;
import com.lc.zy.ball.domain.oa.po.VipCard;
import com.lc.zy.ball.domain.oa.po.VipCardCriteria;
import com.lc.zy.ball.domain.oa.po.VipLog;
import com.lc.zy.ball.domain.oa.po.VipLogCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class VipCardService extends AbstractCacheService {
    private static final Logger logger = LoggerFactory.getLogger(VipCardService.class);

    @Autowired
    private VipCardMapper vipCardMapper;

    @Autowired
    private VipLogMapper vipLogMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 
     * <球馆会员卡列表><功能具体实现>
     *
     * @create：2015年12月8日 下午14:34:26
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @param isPage 是否分页
     * @return
     * @throws Exception
     */
    public Page<VipCardVo> find(PageRequest pageable, Map<String, Object> searchParams, boolean isPage)
            throws Exception {
        logger.debug(searchParams.toString());
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        VipCardCriteria vipCardCriteria = new VipCardCriteria();
        VipCardCriteria.Criteria cri = vipCardCriteria.createCriteria();
        if (isPage) {
            vipCardCriteria.setMysqlLength(pageable.getPageSize());
            vipCardCriteria.setMysqlOffset(pageable.getOffset());
        }
        Criterias.bySearchFilter(cri, filters.values());
        vipCardCriteria.setOrderByClause("et desc");
        int total = vipCardMapper.countByExample(vipCardCriteria);
        List<VipCard> vipCardList = vipCardMapper.selectByExample(vipCardCriteria);
        List<VipCardVo> vipCardVoList = new ArrayList<>();
        VipCardVo vipCardVo = null;
        for (VipCard vipCard : vipCardList) {
            vipCardVo = new VipCardVo();
            BeanUtils.copyProperties(vipCard, vipCardVo);
            StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, vipCard.getStatiumId());
            vipCardVo.setStatiumName(statiumDetail.getName());
            vipCardVoList.add(vipCardVo);
        }
        return new PageImpl<>(vipCardVoList, pageable, total);
    }

    /**
     * 
     * <保存会员卡信息><功能具体实现>
     *
     * @create：2015年12月8日 下午2:49:01
     * @author： CYY
     * @param vipCardVo
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void save(VipCardVo vipCardVo) throws Exception {
        VipCard vipCard = new VipCard();
        BeanUtils.copyProperties(vipCardVo, vipCard);
        Date now = new Date();
        User currentUser = SessionUtil.currentUser();
        vipCard.setAmount(vipCardVo.getAmount() * 100);
        vipCard.setBalance(vipCardVo.getBalance() * 100);
        vipCard.setCt(now);
        vipCard.setEt(now);
        vipCard.setCb(currentUser.getUserId());
        vipCard.setEb(currentUser.getUserId());
        vipCard.setOrgCode(currentUser.getOrgCode());
        vipCard.setId(UUID.get());
        this.insertSelective(vipCard, vipCard.getId());
    }

    /**
     * 
     * <回去会员卡信息详情><功能具体实现>
     *
     * @create：2015年12月8日 下午2:49:01
     * @author： CYY
     * @param vipCardId 会员卡id
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public VipCardVo detail(String vipCardId) throws Exception {
        VipCardVo vipCardVo = new VipCardVo();
        VipCard vipCard = this.selectByPrimaryKey(VipCard.class, vipCardId);
        BeanUtils.copyProperties(vipCard, vipCardVo);
        StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, vipCard.getStatiumId());
        vipCardVo.setStatiumName(statiumDetail.getName());
        return vipCardVo;
    }

    /**
     * 
     * <球馆会员卡日志列表><功能具体实现>
     *
     * @create：2015年12月8日 下午14:34:26
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @param isPage
     * @return
     */
    public Page<VipCardLogVo> findVipCardLogList(PageRequest pageable, Map<String, Object> searchParams,
            VipCardVo vipCardVo, Boolean isPage) {
        logger.debug(searchParams.toString());
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        OrderCriteria orderCriteria = new OrderCriteria();
        OrderCriteria.Criteria cri = orderCriteria.createCriteria();
        cri.andStatiumIdEqualTo(vipCardVo.getStatiumId());
        cri.andStatusEqualTo("ORDER_PLAYING"); // 交易完成的单子才会扣除会员卡的金额
        if (isPage) {
            orderCriteria.setMysqlLength(pageable.getPageSize());
            orderCriteria.setMysqlOffset(pageable.getOffset());
        }
        Criterias.bySearchFilter(cri, filters.values());
        orderCriteria.setOrderByClause("ct desc");
        int total = orderMapper.countByExample(orderCriteria);
        List<Order> orderList = orderMapper.selectByExample(orderCriteria);
        List<VipCardLogVo> vipCardVoList = new ArrayList<>();
        VipCardLogVo vipCardLogVo = null;
        OrderItemCriteria orderItemCriteria = null;
        OrderItemCriteria.Criteria cri1 = null;
        Integer costPrice = 0;
        Integer totalCostPrice = 0, totalRevenue = 0;
        for (Order order : orderList) {
            costPrice = 0;
            vipCardLogVo = new VipCardLogVo();
            BeanUtils.copyProperties(vipCardVo, vipCardLogVo);
            orderItemCriteria = new OrderItemCriteria();
            cri1 = orderItemCriteria.createCriteria();
            cri1.andOrderIdEqualTo(order.getId());
            List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemCriteria);
            for (OrderItem item : orderItemList) {
                costPrice += item.getCostPrice();
            }
            if (order.getOrdersType() == 0) {
                vipCardLogVo.setEvent("订场");
            } else if (order.getOrdersType() == 3) {
                vipCardLogVo.setEvent("约球");
            }
            vipCardLogVo.setOrderId(order.getId());
            vipCardLogVo.setRevenue(order.getFinalFee());
            vipCardLogVo.setExpenditure(costPrice);
            vipCardLogVo.setProfit(vipCardLogVo.getRevenue() - costPrice);
            vipCardVoList.add(vipCardLogVo);
            totalCostPrice += costPrice;
            totalRevenue += order.getFinalFee();
        }
        vipCardVo.setRevenue(totalRevenue);
        vipCardVo.setExpenditure(totalCostPrice);
        vipCardVo.setProfit(totalRevenue - totalCostPrice);
        return new PageImpl<>(vipCardVoList, pageable, total);
    }

    /**
     * 
     * <充值记录><功能具体实现>
     *
     * @create：2015年12月9日 下午5:19:42
     * @author： CYY
     * @param vipCardId
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false)
    public void saveRecharge(VipLogVo vipLogVo) throws Exception {
        Date now = new Date();
        User currentUser = SessionUtil.currentUser();
        VipCard vipCard = this.selectByPrimaryKey(VipCard.class, vipLogVo.getVipCardId());
        VipLog vipLog = new VipLog();
        Integer rechargeAmount = vipLogVo.getRechargeAmount() * 100;
        vipCard.setAmount(vipCard.getAmount() + rechargeAmount);
        vipLog.setBalance(vipCard.getBalance());
        vipCard.setBalance(vipCard.getBalance() + rechargeAmount);
        vipCard.setEt(now);
        vipCard.setEb(currentUser.getUserId());
        this.updateByPrimaryKeySelective(vipCard, vipCard.getId());
        vipLog.setCardNumber(vipCard.getCardNumber());
        vipLog.setCardName(vipCard.getName());
        vipLog.setCb(currentUser.getUserId());
        vipLog.setCt(now);
        vipLog.setId(UUID.get());
        vipLog.setRechargeAmount(rechargeAmount);
        vipLog.setRechargePerson(vipLogVo.getRechargePerson());
        vipLog.setVipCardId(vipLogVo.getVipCardId());
        vipLog.setStatiumId(vipCard.getStatiumId());
        StatiumDetail statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, vipCard.getStatiumId());
        vipLog.setStatiumName(statiumDetail.getName());
        vipLogMapper.insertSelective(vipLog);
    }

    /**
     * 
     * <充值记录列表><功能具体实现>
     *
     * @create：2015年12月8日 下午14:34:26
     * @author： CYY
     * @param pageable
     * @param searchParams
     * @return
     * @throws Exception
     */
    public Page<VipLogVo> findViplogList(PageRequest pageable, Map<String, Object> searchParams, boolean isPage)
            throws Exception {
        logger.debug(searchParams.toString());
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        VipLogCriteria vipLogCriteria = new VipLogCriteria();
        VipLogCriteria.Criteria cri = vipLogCriteria.createCriteria();
        if (isPage) {
            vipLogCriteria.setMysqlLength(pageable.getPageSize());
            vipLogCriteria.setMysqlOffset(pageable.getOffset());
        }
        Criterias.bySearchFilter(cri, filters.values());
        vipLogCriteria.setOrderByClause("ct desc");
        int total = vipLogMapper.countByExample(vipLogCriteria);
        List<VipLog> vipCardList = vipLogMapper.selectByExample(vipLogCriteria);
        List<VipLogVo> vipLogVoList = new ArrayList<>();
        VipLogVo vipLogVo = null;
        for (VipLog vipLog : vipCardList) {
            vipLogVo = new VipLogVo();
            BeanUtils.copyProperties(vipLog, vipLogVo);
            vipLogVoList.add(vipLogVo);
        }
        return new PageImpl<>(vipLogVoList, pageable, total);
    }
}
