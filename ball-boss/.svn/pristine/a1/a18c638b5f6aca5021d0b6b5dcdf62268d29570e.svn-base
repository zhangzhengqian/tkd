package com.lc.zy.ball.boss.framework.activationCode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.ActivationCodeInfoMapper;
import com.lc.zy.ball.domain.oa.mapper.ActivationCodeMapper;
import com.lc.zy.ball.domain.oa.mapper.StatiumDetailMapper;
import com.lc.zy.ball.domain.oa.po.ActivationCode;
import com.lc.zy.ball.domain.oa.po.ActivationCodeCriteria;
import com.lc.zy.ball.domain.oa.po.ActivationCodeInfo;
import com.lc.zy.ball.domain.oa.po.ActivationCodeInfoCriteria;
import com.lc.zy.ball.domain.oa.po.ActivationCodeInfoCriteria.Criteria;
import com.lc.zy.ball.domain.oa.po.StatiumDetail;
import com.lc.zy.ball.domain.oa.po.StatiumDetailCriteria;
import com.lc.zy.common.data.Criterias;

@Service
@Transactional(readOnly = true)
public class ActivationCodeService extends AbstractCacheService {

	private static final Logger logger = LoggerFactory.getLogger(ActivationCodeService.class);

	@Autowired
	ActivationCodeMapper activationCodeMapper = null;
	
	@Autowired
	ActivationCodeInfoMapper activationCodeInfoMapper = null;
	
	@Autowired
	StatiumDetailMapper statiumDetailMapper = null;

	/**
	 * 
	 * <获取活动码list><功能具体实现>
	 *
	 * @create：2016年2月23日 下午3:16:18
	 * @author： sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<ActivationCode> codeList(PageRequest pageRequest, Map<String, Object> searchParams) {
		List<ActivationCode> list = new ArrayList<ActivationCode>();
		int total = 0;
		try {
			// 查询条件
			ActivationCodeCriteria activationCodeCriteria = new ActivationCodeCriteria();
			ActivationCodeCriteria.Criteria criteria = activationCodeCriteria.createCriteria();
			if (searchParams != null) {
				Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
				Criterias.bySearchFilter(criteria, filters.values());
			}
			activationCodeCriteria.setMysqlLength(pageRequest.getPageSize());
			activationCodeCriteria.setMysqlOffset(pageRequest.getOffset());
			activationCodeCriteria.setOrderByClause("ct desc");
			// 获取活动码
			list = activationCodeMapper.selectByExample(activationCodeCriteria);
			total = activationCodeMapper.countByExample(activationCodeCriteria);
			// 判断活动码是否为空
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<ActivationCode>();
			}
		} catch (Exception e) {
			logger.debug("获取活动码list", e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * <保存活动码><功能具体实现>
	 *
	 * @create：2016年2月23日 下午3:37:22
	 * @author： sl
	 * @param activationCode
	 */
	@Transactional(readOnly = false)
	public void saveCode(ActivationCode activationCode) {
		try {
			this.insertSelective(activationCode, activationCode.getId());
		} catch (Exception e) {
			logger.debug("保存活动码", e.getMessage());
		}
	}

	/**
	 * 
	 * <根据id获取活动码信息><功能具体实现>
	 *
	 * @create：2016年2月23日 下午5:36:50
	 * @author： sl
	 * @param id
	 * @return
	 */
	public ActivationCode codeById(String id) {
		ActivationCode activationCode = new ActivationCode();
		try {
			activationCode = this.selectByPrimaryKey(ActivationCode.class, id);
		} catch (Exception e) {
			logger.debug("根据id获取活动码信息", e.getMessage());
		}
		return activationCode;
	}

	/**
	 * 
	 * <根据场馆id获取场馆信息><功能具体实现>
	 *
	 * @create：2016年2月23日 下午5:55:17
	 * @author： sl
	 * @param sid
	 * @return
	 */
	public StatiumDetail statiumInfoById(String sid) {
		StatiumDetail statiumDetail = new StatiumDetail();
		try {
			statiumDetail = this.selectByPrimaryKey(StatiumDetail.class, sid);
		} catch (Exception e) {
			logger.debug("根据场馆id获取场馆信息", e.getMessage());
		}
		return statiumDetail;
	}

	/**
	 * 
	 * <根据场馆id和活动码id获取场馆使用次数><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:05:01
	 * @author： sl
	 * @param sid
	 * @param id
	 * @return
	 */
	public int countCodeUseNum(String sid, String id) {
		int useNum = 0 ;
		try {
			ActivationCodeInfoCriteria activationCodeInfoCriteria = new ActivationCodeInfoCriteria();
			ActivationCodeInfoCriteria.Criteria criteria = activationCodeInfoCriteria.createCriteria();
			criteria.andCodeIdEqualTo(id);
			criteria.andUseStatiumEqualTo(sid);
			useNum = activationCodeInfoMapper.countByExample(activationCodeInfoCriteria);
		} catch (Exception e) {
			logger.debug("根据场馆id和活动码id获取场馆使用次数", e.getMessage());
		}
		return useNum;
	}

	/**
	 * 
	 * <获取活动码详情><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:22:42
	 * @author： sl
	 * @param pageRequest
	 * @param id
	 * @return
	 */
	public Page<ActivationCodeInfo> infoList(PageRequest pageRequest, String id) {
		List<ActivationCodeInfo> codeInfos = new ArrayList<ActivationCodeInfo>();
		int total = 0;
		try {
			ActivationCodeInfoCriteria activationCodeInfoCriteria = new ActivationCodeInfoCriteria();
			activationCodeInfoCriteria.setMysqlLength(pageRequest.getPageSize());
			activationCodeInfoCriteria.setMysqlOffset(pageRequest.getOffset());
			Criteria criteria = activationCodeInfoCriteria.createCriteria();
			criteria.andCodeIdEqualTo(id);
			codeInfos = activationCodeInfoMapper.selectByExample(activationCodeInfoCriteria);
			total = activationCodeInfoMapper.countByExample(activationCodeInfoCriteria);
		} catch (Exception e) {
			logger.debug("获取活动码详情", e.getMessage());
		}
		return new PageImpl<>(codeInfos, pageRequest, total);
	}

	/**
	 * 
	 * <保存code详情><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:32:51
	 * @author： sl
	 * @param activationCodeInfo
	 */
	@Transactional(readOnly = false)
	public void saveCodeInfo(ActivationCodeInfo activationCodeInfo) {
		try {
			this.insertSelective(activationCodeInfo, activationCodeInfo.getId());
		} catch (Exception e) {
			logger.debug("保存code详情", e.getMessage());
		}
	}

	/**
	 * 
	 * <根据活动码id获取活动码详情><功能具体实现>
	 *
	 * @create：2016年2月23日 下午6:49:22
	 * @author： sl
	 * @param id
	 * @return
	 */
	public List<ActivationCodeInfo> codeInfos(String id) {
		List<ActivationCodeInfo> activationCodeInfos = new ArrayList<ActivationCodeInfo>();
		try {
			ActivationCodeInfoCriteria activationCodeInfoCriteria = new ActivationCodeInfoCriteria();
			ActivationCodeInfoCriteria.Criteria criteria = activationCodeInfoCriteria.createCriteria();
			criteria.andCodeIdEqualTo(id);
			activationCodeInfos = activationCodeInfoMapper.selectByExample(activationCodeInfoCriteria);
		} catch (Exception e) {
			logger.debug("根据活动码id获取活动码详情", e.getMessage());
		}
		return activationCodeInfos;
	}

	/**
	 * 
	 * <删除活动码><功能具体实现>
	 *
	 * @create：2016年2月23日 下午7:46:17
	 * @author： sl
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteCodeById(String id) {
		try {
			this.deleteByPrimaryKey(ActivationCode.class, id);
			
			// 删除活动码详情
			ActivationCodeInfoCriteria activationCodeInfoCriteria = new ActivationCodeInfoCriteria();
			ActivationCodeInfoCriteria.Criteria criteria = activationCodeInfoCriteria.createCriteria();
			criteria.andCodeIdEqualTo(id);
			activationCodeInfoMapper.deleteByExample(activationCodeInfoCriteria);
		} catch (Exception e) {
			logger.debug("删除活动码", e.getMessage());
		}
	}

	/**
	 * 
	 * <获取场馆信息><功能具体实现>
	 *
	 * @create：2016年2月24日 下午2:33:34
	 * @author： sl
	 * @return
	 */
	public List<StatiumDetail> statiumDetails() {
		List<StatiumDetail> statiumDetails = new ArrayList<StatiumDetail>();
		try {
			StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
			statiumDetailCriteria.setMysqlLength(20);
			statiumDetailCriteria.setMysqlOffset(0);
			StatiumDetailCriteria.Criteria criteria = statiumDetailCriteria.createCriteria();
			criteria.andIsSignedEqualTo(1);
			criteria.andStatusEqualTo(1);
			statiumDetails = statiumDetailMapper.selectByExample(statiumDetailCriteria);
		} catch (Exception e) {
			logger.debug("获取场馆信息", e.getMessage());
		}
		return statiumDetails;
	}

	/**
	 * 
	 * <根据场馆名称获取场馆><功能具体实现>
	 *
	 * @create：2016年2月24日 下午3:56:29
	 * @author： sl
	 * @param name
	 * @return
	 */
	public List<StatiumDetail> statiumDetailsByName(String name) {
		List<StatiumDetail> statiumDetailas = new ArrayList<StatiumDetail>();
		try {
			StatiumDetailCriteria statiumDetailCriteria = new StatiumDetailCriteria();
			StatiumDetailCriteria.Criteria criteria = statiumDetailCriteria.createCriteria();
			if (StringUtils.isNotBlank(name)) {
				criteria.andNameLike("%" + name + "%");
			}
			criteria.andIsSignedEqualTo(1);
			criteria.andStatusEqualTo(1);
			statiumDetailas = statiumDetailMapper.selectByExample(statiumDetailCriteria);
		} catch (Exception e) {
			logger.debug("根据场馆名称获取场馆", e.getMessage());
		}
		return statiumDetailas;
	}

}
