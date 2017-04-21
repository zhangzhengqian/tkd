package com.lc.zy.ball.boss.framework.coach.service;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.boss.framework.coach.vo.CoachVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.CoachMapper;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.CoachCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;
import com.lc.zy.common.zoo.SEQGenerate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class CoachService extends AbstractCacheService{
	private Logger logger = LoggerFactory.getLogger(CoachService.class);

	@Autowired
	private CoachMapper coachMapper;

	@Autowired
	private SEQGenerate seqGenerate;

	/**
	 * 
	 * <获取教练list><功能具体实现>
	 *
	 * @create：2016年5月12日 上午11:53:12
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<CoachVo> list(PageRequest pageRequest, Map<String, Object> searchParams) {
		List<CoachVo> voList = new ArrayList<CoachVo>();
		int total = 0;
		try {
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			CoachCriteria coachCriteria = new CoachCriteria();
			coachCriteria.setMysqlLength(pageRequest.getPageSize());
			coachCriteria.setMysqlOffset(pageRequest.getOffset());
			CoachCriteria.Criteria cri = coachCriteria.createCriteria();
			Criterias.bySearchFilter(cri, filters.values());
			coachCriteria.setOrderByClause("ct desc");
			total = coachMapper.countByExample(coachCriteria);
			List<Coach> list = coachMapper.selectByExample(coachCriteria);
			for (Coach info : list) {
				CoachVo vo = new CoachVo();
				BeanUtils.copyProperties(info, vo);
				voList.add(vo);
			}
		} catch (Exception e) {
			logger.debug("获取教练list:{}", e.getMessage());
		}
		return new PageImpl<>(voList, pageRequest, total);
	}

	/**
	 * 
	 * <根据教练id获取教练信息><功能具体实现>
	 *
	 * @create：2016年5月12日 上午11:56:22
	 * @author：sl
	 * @param id
	 * @return
	 */
	public Coach getCoachById(String id) {
		Coach coach = new Coach();
		try {
			CoachCriteria coachCriteria = new CoachCriteria();
			CoachCriteria.Criteria coachCa = coachCriteria.createCriteria();
			coachCa.andIdEqualTo((id).toString());
			List<Coach> coachlist = coachMapper.selectByExample(coachCriteria);
			if (coachlist.size() > 0) {
				coach = coachlist.get(0);
			}
		} catch (Exception e) {
			logger.debug("根据教练id获取教练信息:{}", e.getMessage());
		}
		return coach;
	}

	/**
	 * 
	 * <教练保存><功能具体实现>
	 *
	 * @create：2016年5月9日 下午4:28:22
	 * @author：wangp
	 * @param coach
	 * @return
	 */
	@SuppressWarnings("static-access")
	@Transactional(readOnly = false)
	public String save(CoachVo coach) {
		// modify ywl 2017-04-05 是程序睡眠300毫秒
		String id = "";
		try {
			Thread.currentThread().sleep(300);
			Coach myForm = new Coach();
			BeanUtils.copyProperties(coach, myForm);
			String uId = SessionUtil.currentUserId();
			if (coach.getId() == null || coach.getId().equals("")) {
				id = UUID.get();
				myForm.setId(id);
				// 设置编辑时间和创建时间
				myForm.setEt(new Date());
				myForm.setCt(new Date());
				myForm.setCb(uId);
				myForm.setEb(uId);
				// 教练号
				myForm.setCoachNo(seqGenerate.genQiuyouNumber());
				this.insertSelective(myForm, coach.getId());
			} else {
				// 设置编辑时间
				myForm.setEt(new Date());
				myForm.setEb(uId);
				this.updateByPrimaryKeySelective(myForm, coach.getId());
			}
		} catch (Exception e) {
			logger.debug("教练信息保存", e.getMessage());
		}
		return id;
	}

	/**
	 * 
	 * <删除教练><功能具体实现>
	 *
	 * @create：2016年5月12日 上午11:51:10
	 * @author：sl
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteCoachByid(String id) {
		try {
			this.deleteByPrimaryKey(Coach.class, id);
		} catch (Exception e) {
			logger.debug("删除教练:{}", e.getMessage());
		}
	}

}