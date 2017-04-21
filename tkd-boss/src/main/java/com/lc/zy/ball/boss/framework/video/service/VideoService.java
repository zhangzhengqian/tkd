package com.lc.zy.ball.boss.framework.video.service;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.TrainVideoMapper;
import com.lc.zy.ball.domain.oa.mapper.videoGroupMapper;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.ball.domain.oa.po.videoGroupCriteria.Criteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class VideoService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(VideoService.class);
	
	@Autowired
	private videoGroupMapper groupMapper;

	@Autowired
	private TrainVideoMapper trainVideoMapper = null;
	
	
	/**
	 * 
	 * <视频集列表展示实现><功能具体实现>
	 *
	 * @create：2016年12月23日 上午9:35:19
	 * @author：zzq
	 * @param request
	 * @return
	 */
	public Page<videoGroup> list(PageRequest request,Map<String,Object> searchParams){
		videoGroupCriteria criteria = new videoGroupCriteria();
		Criteria c1 = criteria.createCriteria();
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		criteria.setMysqlLength(request.getPageSize());
		criteria.setMysqlOffset(request.getOffset());
		criteria.setOrderByClause("ct desc");
		Criterias.bySearchFilter(c1, filter.values());
		List<videoGroup> list = groupMapper.selectByExample(criteria);
		for(videoGroup group:list){
			String cb = group.getCb();
			if(StringUtils.isNotEmpty(cb)){
				try {
					User user = this.selectByPrimaryKey(User.class, cb);
					if(user!=null){
						group.setCb(user.getNickname());
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("查询用户");
				}
				
			}
		}
		int toatal = groupMapper.countByExample(criteria);
		return new PageImpl<>(list, request, toatal);
		
	}
	
	/**
	 * 
	 * <删除视频集><功能具体实现>
	 *
	 * @create：2016年12月23日 上午11:11:11
	 * @author：zzq
	 * @param group
	 */
	@Transactional(readOnly=false)
	public boolean deleteGroup(videoGroup group){
		boolean flag = false;
		try {
			int delCount = this.deleteByPrimaryKey(videoGroup.class, group.getId());
			// 删除视频集item
			deleteVideoItem(group.getId());
			if(delCount == 1){
				flag = true;
			}
		} catch (Exception e) {
			logger.error("删除视频集");
		}
		return flag;
	}

	/**
	 *
	 * <删除视频集Item><功能具体实现>
	 *
	 * @create：2017/1/1 上午10:49
	 * @author：sl
	 * @param id
	 * @return void
	 */
	@Transactional(readOnly = false)
	private void deleteVideoItem(String id) {
		try {
			TrainVideoCriteria trainVideoCriteria = new TrainVideoCriteria();
			TrainVideoCriteria.Criteria criteria = trainVideoCriteria.createCriteria();
			criteria.andParentIdEqualTo(id);
			trainVideoMapper.deleteByExample(trainVideoCriteria);
		} catch (Exception e) {
			logger.debug("删除视频集Item {}", e.getMessage());
		}
	}

	/**
	 * 
	 * <主键查询><功能具体实现>
	 *
	 * @create：2016年12月23日 上午11:13:10
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public videoGroup selectGroup(String id){
		videoGroup group = null;
		try {
			group = this.selectByPrimaryKey(videoGroup.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("主键查询group"+e.getMessage());
		}
		return group;
	}
	
	/**
	 *
	 * <saveOrUpdate视频集><功能具体实现>
	 *
	 * @create：2017/1/1 上午10:08
	 * @author：sl
	 * @param group
	 * @return void
	 */
	@Transactional(readOnly = false)
    public void saveOrUpdate(videoGroup group) {
		try {
			Date date = new Date();
			group.setEt(date);
			group.setEb(SessionUtil.currentUserId());
			if (StringUtils.isEmpty(group.getId())) {
				group.setId(UUID.get());
				group.setCt(date);
				group.setCb(SessionUtil.currentUserId());
				// save
				this.insertSelective(group, group.getId());
			} else {
				// update
				this.updateByPrimaryKeySelective(group, group.getId());
			}
		} catch (Exception e) {
			logger.debug("saveOrUpdate视频集 {}", e.getMessage());
			throw new RuntimeException("服务器异常");
		}
    }
}
