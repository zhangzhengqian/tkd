package com.lc.zy.ball.boss.framework.statiumClass.service;

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
import com.lc.zy.ball.boss.framework.statiumClass.vo.StatiumClassVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumClassMapper;
import com.lc.zy.ball.domain.oa.po.StatiumClass;
import com.lc.zy.ball.domain.oa.po.StatiumClassCriteria;
import com.lc.zy.ball.domain.oa.po.StatiumClassInfoCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.mq.QueueNames;
import com.lc.zy.common.mq.QueueProducer;
import com.lc.zy.common.util.UUID;

/**
 * 2016 0425 10:00 flq 道馆课程设置Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class StatiumClassService extends AbstractCacheService {

	@Autowired
	private StatiumClassMapper statiumClassMapper;

	@Autowired
	private StatiumClassInfoMapper statiumClassInfoMapper;
	
	@Autowired
	private QueueProducer queueProducer = null;

	private Logger logger = LoggerFactory.getLogger(StatiumClassService.class);

	/**
	 * 
	 * <道馆class list><功能具体实现>
	 *
	 * @create：2016年5月3日 下午3:48:22
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<StatiumClassVo> list(PageRequest pageRequest,
			Map<String, Object> searchParams) {
		int total = 0;
		List<StatiumClassVo> voList = new ArrayList<StatiumClassVo>();
		try {
			Map<String, SearchFilter> filters = SearchFilter
					.parse(searchParams);
			StatiumClassCriteria statiumClassCriteria = new StatiumClassCriteria();
			statiumClassCriteria.setMysqlLength(pageRequest.getPageSize());
			statiumClassCriteria.setMysqlOffset(pageRequest.getOffset());
			StatiumClassCriteria.Criteria cri = statiumClassCriteria
					.createCriteria();

			Criterias.bySearchFilter(cri, filters.values());
			statiumClassCriteria.setOrderByClause("et desc");
			total = statiumClassMapper.countByExample(statiumClassCriteria);
			List<StatiumClass> list = statiumClassMapper
					.selectByExample(statiumClassCriteria);
			for (StatiumClass sc : list) {
				StatiumClassVo classVo = new StatiumClassVo();
				ConvertStatiumClassVo(classVo, sc);
				voList.add(classVo);
			}
		} catch (Exception e) {
			logger.error("道馆class list:{}" + e.getMessage());
		}
		return new PageImpl<>(voList, pageRequest, total);
	}

	/**
	 * 
	 * <课程保存><功能具体实现>
	 *
	 * @create：2016年5月3日 下午4:46:19
	 * @author：sl
	 * @param myForm
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(StatiumClassVo myForm) {
		String id = "";
		// date
		Date now = new Date();
		// userId
		String uId = SessionUtil.currentUserId();
		try {
			StatiumClass statiumClass = new StatiumClass();
			BeanUtils.copyProperties(myForm, statiumClass);
			if (myForm.getId().equals("")) {
				id = UUID.get();
				statiumClass.setId(id);
				// ct
				statiumClass.setCt(now);
				// cb
				statiumClass.setCb(uId);
				// et
				statiumClass.setEt(now);
				// eb
				statiumClass.setEb(uId);
				this.insertSelective(statiumClass, myForm.getId());
			} else {
				// et
				statiumClass.setEt(now);
				// eb
				statiumClass.setEb(uId);
				this.updateByPrimaryKeySelective(statiumClass, myForm.getId());
			}
			// MQ同步道馆信息
			queueProducer.push(QueueNames.SYNC_STATIUM_QUEUE, myForm.getStatiumId()
					.toString());
		} catch (Exception e) {
			logger.debug("课程保存:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <删除课程><功能具体实现>
	 *
	 * @create：2016年5月4日 下午1:59:22
	 * @author：sl
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean deleteClassInfos(String id) {
		boolean flag = false;
		try {
			// 删除课时
			StatiumClassInfoCriteria statiumClassInfoCriteria = new StatiumClassInfoCriteria();
			StatiumClassInfoCriteria.Criteria cri = statiumClassInfoCriteria
					.createCriteria();
			cri.andClassIdEqualTo(id);
			statiumClassInfoMapper.deleteByExample(statiumClassInfoCriteria);
			// 删除课程
			Integer delCount = this.deleteByPrimaryKey(StatiumClass.class, id);
			if (delCount == 1) {
				flag = true;
			}
		} catch (Exception e) {
			logger.debug("删除课程:{}", e.getMessage());
		}
		return flag;
	}

	/**
	 * 
	 * <课程详情><功能具体实现>
	 *
	 * @create：2016年5月4日 下午2:03:48
	 * @author：sl
	 * @param id
	 * @return
	 */
	public StatiumClassVo getStatiumClassById(String id) {
		StatiumClassVo statiumClassVo = new StatiumClassVo();
		try {
			StatiumClass sc = statiumClassMapper.selectByPrimaryKey(id);
			ConvertStatiumClassVo(statiumClassVo, sc);
		} catch (Exception e) {
			logger.debug("课程详情:{}", e.getMessage());
		}
		return statiumClassVo;
	}

	/**
	 * 
	 * <课程价格处理><功能具体实现>
	 *
	 * @create：2016年5月3日 下午5:52:22
	 * @author：sl
	 * @param classVo
	 * @param sc
	 */
	private void ConvertStatiumClassVo(StatiumClassVo classVo, StatiumClass sc) {
		try {
			BeanUtils.copyProperties(sc, classVo);
			classVo.setFlPrice(sc.getPrice() / 100F);
			if (sc.getDiscount() != null) {
				classVo.setFlDiscount(sc.getDiscount());
			}
			if (sc.getDiscountPrice() != null) {
				classVo.setFlDiscountPrice(sc.getDiscountPrice() / 100F);
			}
		} catch (Exception e) {
			logger.debug("课程价格处理:{}", e.getMessage());
		}
	}
}
