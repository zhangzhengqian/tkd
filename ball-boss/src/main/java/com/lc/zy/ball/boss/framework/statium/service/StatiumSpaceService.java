package com.lc.zy.ball.boss.framework.statium.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.common.web.FunctionTag;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumPriceTmplVo;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumSpaceVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.OrderItemMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumDetailMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumSpaceMapper;
import com.lc.zy.ball.domain.oa.po.SpacePriceCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumAuditLog;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumSpace;
import com.lc.zy.ball.domain.oa.po.StatiumSpaceCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumSpaceCriteria.Criteria;
import com.lc.zy.ball.domain.oa.po.StatiumSpacePriceCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.FreeMarkerUtils;
import com.lc.zy.common.util.MyGson;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class StatiumSpaceService extends AbstractCacheService {

	@Autowired
	private StatiumDetailMapper statiumDetailMapper;

	@Autowired
	private StatiumPriceTmplService statiumPriceTmplService;

	@Autowired
	private StatiumSpaceMapper statiumSpaceMapper;

	@Autowired
	private StatiumSpacePriceService statiumSpacePriceService;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private QueueProducer queueProducer;

	private static Logger logger = LoggerFactory.getLogger(StatiumSpaceService.class);

	/**
	 * 分页查询当前用户所在场馆的场地
	 * 
	 * @param searchParams
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public Page<StatiumSpace> find(PageRequest pageable, Map<String, Object> searchParams) throws Exception {
		StatiumSpaceCriteria rc = new StatiumSpaceCriteria();
		rc.setMysqlOffset(pageable.getOffset());
		rc.setMysqlLength(pageable.getPageSize());

		StatiumSpaceCriteria.Criteria cri = rc.createCriteria();
		if (searchParams != null) {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			Criterias.bySearchFilter(cri, filters.values());
		}
		rc.setOrderByClause("space_code desc ");
		int total = statiumSpaceMapper.countByExample(rc);
		List<StatiumSpace> list = statiumSpaceMapper.selectByExample(rc);
//		Collections.sort(list, new Comparator<StatiumSpace>() {
//			public int compare(StatiumSpace o1, StatiumSpace o2) {
//				Integer code1 = Integer.parseInt(o1.getSpaceCode().substring(1));
//				Integer code2 = Integer.parseInt(o2.getSpaceCode().substring(1));
//				return code1.compareTo(code2);
//			}
//		});
		return new PageImpl<>(list, pageable, total);
	}

	/**
	 * 
	 * <创建场地><功能具体实现>
	 *
	 * @create：2015年8月10日 上午11:39:56
	 * @author： CYY
	 * @param statium
	 * @param list
	 * @param spaceId
	 * @return
	 */
	@Transactional(readOnly = false)
	public void create(StatiumSpaceVo statiumSpace, List<StatiumPriceTmplVo> list) {
		try {
			// 创建场地
			StatiumSpaceCriteria sdc = new StatiumSpaceCriteria();
			Criteria cri = sdc.createCriteria();
			cri.andStatiumIdEqualTo(statiumSpace.getStatiumId());
			cri.andSportTypeEqualTo(statiumSpace.getSportType());
			sdc.setOrderByClause(" CONVERT(SUBSTRING(space_code,2,10),SIGNED) desc limit 1 ");
			List<StatiumSpace> maxStatiumSpaceList = statiumSpaceMapper.selectByExample(sdc);

			Date now = new Date();
			String userid = SessionUtil.currentUserId();
			String prefix = statiumSpace.getSportType().substring(0, 1);
			prefix = prefix.toUpperCase();
			String name = FunctionTag.getDicItem(statiumSpace.getSportType()).getItemName() + "场";
			String id = "";
			Integer offset = 0;
			if (maxStatiumSpaceList != null && maxStatiumSpaceList.size() > 0) {
				String spaceCode = maxStatiumSpaceList.get(0).getSpaceCode();
				offset = Integer.parseInt(spaceCode.substring(1));
			}
			for (int i = 0; i < statiumSpace.getSpaceNumber(); i++) {
				StatiumSpace space = new StatiumSpace();
				id = UUID.get();
				space.setId(id);
				space.setCt(now);
				space.setCb(userid);
				space.setEb(userid);
				space.setEt(now);
				space.setSpaceCode(prefix + (++offset));
				space.setStatiumId(statiumSpace.getStatiumId());
				space.setSportType(statiumSpace.getSportType());
				space.setSpaceName(name);
				space.setStatus(1); // 状态（ 0： 停用； 1： 启用）
				this.insertSelective(space, id);

				// 创建场地价格
				statiumSpacePriceService.create(list, space.getId(), statiumSpace.getSportType());
			}
			StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, statiumSpace.getStatiumId());
			StatiumDetail save_ = new StatiumDetail();
			save_.setStatus(0);
			save_.setId(detail.getId());
			this.updateByPrimaryKeySelective(save_, save_.getId());
            queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, detail.getId());
            createAuditLog(detail.getId(), userid, "提交审核", "创建场地信息");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * <添加或者更新场地信息><功能具体实现>
	 *
	 * @create：2015年8月10日 下午12:00:28
	 * @author： CYY
	 * @param po
	 * @param list
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public String update(StatiumSpace po, List<StatiumPriceTmplVo> list,String type) throws Exception {
		String id = po.getId();
		Date now = new Date();
		String userId = SessionUtil.currentUserId();
		if (StringUtils.isEmpty(id)) {
			// insert
			id = UUID.get();
			po.setId(id);
			po.setCt(now);
			po.setEt(now);
			po.setEb(userId);
			po.setCb(userId);
			this.insertSelective(po, id);
		} else {
			statiumSpacePriceService.create(list, po.getId(), po.getSportType());
			// update
			po.setEt(now);
			po.setEb(userId);
			this.updateByPrimaryKeySelective(po, po.getId());
		}
		if(!"batch".equals(type)){
			StatiumDetail detail = this.selectByPrimaryKey(StatiumDetail.class, po.getStatiumId());
			StatiumDetail save_ = new StatiumDetail();
			save_.setStatus(0);
			save_.setId(detail.getId());
			this.updateByPrimaryKeySelective(save_, save_.getId());
            queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, detail.getId());
            createAuditLog(detail.getId(), userId, "提交审核", "修改场地信息");
		}
		return id;
	}

	/**
	 * 
	 * <根据id获取场地信息><功能具体实现>
	 *
	 * @create：2015年8月6日 下午8:57:26
	 * @author： CYY
	 * @param statiumDetailCriteria
	 * @return
	 */
	public StatiumSpace findStatiumSpaceById(String id) {
		try {
			return this.selectByPrimaryKey(StatiumSpace.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * <单条删除场地><有未完成的订单时场地不可删除>
	 *
	 * @create：2015年10月29日 上午11:42:20
	 * @author： liangsh
	 * @param id
	 * @param statiumId
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean delete(String id, String statiumId) {
		try {
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("statiumId", statiumId);
			paras.put("spaceId", id);
			String sqlList = FreeMarkerUtils.format("/template/order/order_space.ftl", paras);
			logger.debug(sqlList);
			logger.debug(MyGson.getInstance().toJson(paras));
			List<Map<String, Object>> sapces = jdbcTemplate.queryForList(sqlList);
			if (CollectionUtils.isNotEmpty(sapces)) {
				return false;
			}else{
				StatiumSpacePriceCriteria statiumSpacePriceCriteria = new StatiumSpacePriceCriteria();
				StatiumSpacePriceCriteria.Criteria cri1 = statiumSpacePriceCriteria.createCriteria();
				cri1.andSpaceIdEqualTo(id);
				statiumSpacePriceService.deleteByExample(statiumSpacePriceCriteria);
				SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
				SpacePriceCriteria.Criteria cri2 = spacePriceCriteria.createCriteria();
				cri2.andSpaceIdEqualTo(id);
				statiumSpacePriceService.deleteBySpacePirceExample(spacePriceCriteria);
				this.deleteByPrimaryKey(StatiumSpace.class, id);
				return true;
			}
		} catch (Exception e) {
			logger.error("StatiumSpaceService delete_exception={}", e);
			throw new RuntimeException(e);
		}finally{
			StatiumDetail detail = new StatiumDetail();
			try {
				detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
			StatiumDetail save_ = new StatiumDetail();
			save_.setStatus(0);
			save_.setId(detail.getId());
			try {
				this.updateByPrimaryKeySelective(save_, save_.getId());
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
            queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, detail.getId());
			String userId = SessionUtil.currentUserId();
			try {
				createAuditLog(detail.getId(), userId, "提交审核", "删除场地信息");
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
		}
	}

	/**
	 * 
	 * <批量删除场地><功能具体实现>
	 *
	 * @create：2015年10月29日 上午11:41:57
	 * @author： liangsh
	 * @param ids
	 * @param statiumId
	 */
	@Transactional(readOnly = false)
	public void deleteAll(String[] ids, String statiumId) {
		try {
			for (String id : ids) {
				Map<String, Object> paras = new HashMap<String, Object>();
				paras.put("statiumId", statiumId);
				paras.put("spaceId", id);
				String sqlList = FreeMarkerUtils.format("/template/order/order_space.ftl", paras);
				logger.debug(sqlList);
				logger.debug(MyGson.getInstance().toJson(paras));
				List<Map<String, Object>> sapces = jdbcTemplate.queryForList(sqlList);
				if (CollectionUtils.isNotEmpty(sapces)) {
					continue;
				}
				StatiumSpacePriceCriteria statiumSpacePriceCriteria = new StatiumSpacePriceCriteria();
				StatiumSpacePriceCriteria.Criteria cri1 = statiumSpacePriceCriteria.createCriteria();
				cri1.andSpaceIdEqualTo(id);
				statiumSpacePriceService.deleteByExample(statiumSpacePriceCriteria);
				SpacePriceCriteria spacePriceCriteria = new SpacePriceCriteria();
				SpacePriceCriteria.Criteria cri2 = spacePriceCriteria.createCriteria();
				cri2.andSpaceIdEqualTo(id);
				statiumSpacePriceService.deleteBySpacePirceExample(spacePriceCriteria);
				this.deleteByPrimaryKey(StatiumSpace.class, id);
			}
		} catch (Exception e) {
			logger.error("StatiumSpaceService deleteAll_exception={}", e);
			throw new RuntimeException(e);
		}finally{
			StatiumDetail detail = new StatiumDetail();
			try {
				detail = this.selectByPrimaryKey(StatiumDetail.class, statiumId);
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
			StatiumDetail save_ = new StatiumDetail();
			save_.setStatus(0);
			save_.setId(detail.getId());
			try {
				this.updateByPrimaryKeySelective(save_, save_.getId());
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
			queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, detail.getId());
			String userId = SessionUtil.currentUserId();
			try {
				createAuditLog(detail.getId(), userId, "提交审核", "批量删除场地信息");
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
		}
	}
	
	/**
     * 场馆审核日志
     * 
     * @author liangc
     * @param cb
     * @param action
     * @param reason
     * @throws Exception
     */
    private void createAuditLog(String statiumId, String cb, String action, String reason) throws Exception {
        Date now = DateUtils.now();
        StatiumAuditLog statiumAuditLog = new StatiumAuditLog();
        statiumAuditLog.setId(UUID.get());
        statiumAuditLog.setCb(cb);
        statiumAuditLog.setCt(now);
        statiumAuditLog.setDescription(reason);
        statiumAuditLog.setAction(action);
        statiumAuditLog.setStatiumId(statiumId);
        super.insertSelective(statiumAuditLog, statiumAuditLog.getId());
    }


}
