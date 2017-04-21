package com.lc.zy.ball.boss.framework.statium.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.statium.vo.StatiumInfosVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.PmwInfoimgMapper;
import com.lc.zy.ball.domain.oa.mapper.PmwMemberMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.PmwInfoimg;
import com.lc.zy.ball.domain.oa.po.PmwInfoimgCriteria;
import com.lc.zy.ball.domain.oa.po.PmwMember;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class StatiumInfoService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory
			.getLogger(StatiumInfoService.class);

	@Autowired
	private StatiumInfosMapper statiumInfosMapper;

	@Autowired
	private PmwMemberMapper pmwMemberMapper;

	@Autowired
	private PmwInfoimgMapper pmwInfoimgMapper;

	@Autowired
	private QueueProducer queueProducer = null;

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTempalte;

	/**
	 * 
	 * <根据道馆id获取道馆信息><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:15:50
	 * @author：sl
	 * @param id
	 * @return
	 */
	public StatiumInfos getStatiumInfosByDgid(Integer id) {
		StatiumInfos statiumInfos = new StatiumInfos();
		try {
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			StatiumInfosCriteria.Criteria statiumInfosCa = statiumInfosCriteria
					.createCriteria();
			statiumInfosCa.andDgIdEqualTo(id);
			List<StatiumInfos> statiumInfoList = statiumInfosMapper
					.selectByExample(statiumInfosCriteria);
			if (statiumInfoList.size() > 0) {
				statiumInfos = statiumInfoList.get(0);
			} else {// 道馆明细表不存在道馆信息，pmw老表查询
				setStatiumInfos(id, statiumInfos);
			}
		} catch (Exception e) {
			logger.debug("根据道馆id获取道馆信息:{}", e.getMessage());
		}
		return statiumInfos;
	}

	/**
	 * 
	 * <道馆信息保存><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:33:48
	 * @author：sl
	 * @param myForm
	 */
	@Transactional(readOnly = false)
	public void save(StatiumInfosVo myForm) {
		try {
			StatiumInfos statiumInfos = new StatiumInfos();
			BeanUtils.copyProperties(myForm, statiumInfos);
			String id = statiumInfos.getId();
			// date
			Date now = new Date();
			// userId
			String uId = SessionUtil.currentUserId();
			if (StringUtils.isBlank(id)) {
				// id
				id = UUID.get();
				statiumInfos.setId(id);
				// ct
				statiumInfos.setCt(now);
				// cb
				statiumInfos.setCb(uId);
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				this.insertSelective(statiumInfos, id);
			} else {
				// et
				statiumInfos.setEt(now);
				// eb
				statiumInfos.setEb(uId);
				this.updateByPrimaryKeySelective(statiumInfos, id);
			}
			// MQ同步道馆信息
			queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, myForm.getDgId()
					.toString());
		} catch (Exception e) {
			logger.debug("道馆信息保存", e.getMessage());
		}
	}

	/**
	 * 
	 * <道馆明细表不存在道馆信息，pmw老表查询><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:28:56
	 * @author：sl
	 * @param dgId
	 * @param statiumInfos
	 */
	private void setStatiumInfos(Integer dgId, StatiumInfos statiumInfos) {
		try {
			PmwMember pmwMember = pmwMemberMapper.selectByPrimaryKey(dgId);
			statiumInfos.setDgId(dgId);
			statiumInfos.setDgName(pmwMember.getA2());
			statiumInfos.setAddress(pmwMember.getA3());
			statiumInfos.setContact(pmwMember.getCnname());
			statiumInfos.setTel(pmwMember.getA5());
			// 根据道馆Id获取原数据库中的道馆地图信息
			PmwInfoimgCriteria pmwInfoimgCriteria = new PmwInfoimgCriteria();
			PmwInfoimgCriteria.Criteria infoCriteria = pmwInfoimgCriteria
					.createCriteria();
			infoCriteria.andHyIdEqualTo(dgId);
			List<PmwInfoimg> pmwInfoImgs = pmwInfoimgMapper
					.selectByExample(pmwInfoimgCriteria);
			if (!pmwInfoImgs.isEmpty() && pmwInfoImgs.size() > 0) {
				PmwInfoimg pmwInfoImg = pmwInfoImgs.get(0);
				statiumInfos.setLng(Double.valueOf(pmwInfoImg.getLng()));
				statiumInfos.setLat(Double.valueOf(pmwInfoImg.getLat()));
			}
		} catch (Exception e) {
			logger.debug("道馆明细表不存在道馆信息，pmw老表查询:{}", e.getMessage());
		}

	}

	/**
	 * 
	 * <获取道馆list><功能具体实现>
	 *
	 * @create：2016年5月3日 下午2:22:10
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatiumInfosVo> list(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumInfosVo> voList = new ArrayList<StatiumInfosVo>();
		try {
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
			statiumInfosCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumInfosCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumInfosCriteria.Criteria cri = statiumInfosCriteria
					.createCriteria();

			Criterias.bySearchFilter(cri, filters.values());
			statiumInfosCriteria.setOrderByClause("et desc");
			total = statiumInfosMapper.countByExample(statiumInfosCriteria);
			List<StatiumInfos> list = statiumInfosMapper
					.selectByExample(statiumInfosCriteria);
			for (StatiumInfos sc : list) {
				StatiumInfosVo InfosVo = new StatiumInfosVo();
				ConvertStatiumVo(InfosVo, sc);
				voList.add(InfosVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取道馆list(StatiumClassService--list)" + e.getMessage());
		}
		return new PageImpl<StatiumInfosVo>(voList, pageRequest, total);
	}

	private void ConvertStatiumVo(StatiumInfosVo InfosVo, StatiumInfos sc) {
		BeanUtils.copyProperties(sc, InfosVo);
		/*
		 * classVo.setFlPrice(sc.getPrice() / 100F); if(sc.getPrice()==0F){
		 * classVo.setFlDiscount(null); }else{
		 * classVo.setFlDiscount(sc.getDiscount() / 100F); }
		 * if(sc.getDiscountPrice()==0F){ classVo.setFlDiscountPrice(null);
		 * }else{ classVo.setFlDiscountPrice(sc.getDiscountPrice() / 100F); }
		 * if(sc.getLimitPrice()==0F){ classVo.setFlLimitPrice(null); }else{
		 * classVo.setFlLimitPrice(sc.getLimitPrice() / 100F); }
		 */
		InfosVo.setA3(sc.getAddress());
		InfosVo.setA5(sc.getTel());
		InfosVo.setA2(sc.getDgName());
		InfosVo.setCnname(sc.getContact());

	}

}
