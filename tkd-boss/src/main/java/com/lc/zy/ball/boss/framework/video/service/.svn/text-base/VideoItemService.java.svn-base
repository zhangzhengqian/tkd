package com.lc.zy.ball.boss.framework.video.service;

import com.lc.zy.ball.boss.common.SessionUtil;
import com.lc.zy.ball.boss.common.service.AbstractCacheService;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.TrainVideoMapper;
import com.lc.zy.ball.domain.oa.po.TrainVideo;
import com.lc.zy.ball.domain.oa.po.TrainVideoCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VideoItemService extends AbstractCacheService{
	
	private static Logger logger = LoggerFactory.getLogger(VideoItemService.class);
	
	@Autowired
	private TrainVideoMapper trainVideoMapper;
	
	
	/**
	 * 
	 * <视频列表展示><功能具体实现>
	 *
	 * @create：2016年12月22日 上午9:48:42
	 * @author：zzq
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<TrainVideo> findCarouselList(PageRequest pageRequest, Map<String, Object> searchParams,String parentId) {
		int total = 0;
		List<TrainVideo> list = new ArrayList<TrainVideo>();
		try {
			TrainVideoCriteria trainVideoCriteria = new TrainVideoCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			trainVideoCriteria.setMysqlLength(pageRequest.getPageSize());
			trainVideoCriteria.setMysqlOffset(pageRequest.getOffset());
			TrainVideoCriteria.Criteria criteria = trainVideoCriteria.createCriteria();
			trainVideoCriteria.setOrderByClause("sort asc");
			criteria.andParentIdEqualTo(parentId);
			Criterias.bySearchFilter(criteria, filters.values());
			total = trainVideoMapper.countByExample(trainVideoCriteria);
			list = trainVideoMapper.selectByExample(trainVideoCriteria);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取轮播图list(CarouselManagerService--findCarouselList)" + e.getMessage());
		}
		return new PageImpl<>(list, pageRequest, total);
	}
	
	/**
	 * 
	 * <更新视频集><功能具体实现>
	 *
	 * @create：2016年12月22日 上午9:53:52
	 * @author：zzq
	 * @param trainVideo
	 */
	public void updateVideo(TrainVideo trainVideo){
		
		try {
			this.updateByPrimaryKeySelective(trainVideo, trainVideo.getId());
		} catch (Exception e) {
			logger.error("更新trainVideo"+e.getMessage());
		}
	}
	
	/**
	 * 
	 * <保存视频集><功能具体实现>
	 *
	 * @create：2016年12月22日 上午9:56:05
	 * @author：zzq
	 * @param trainVideo
	 */
	public void insertVideo(TrainVideo trainVideo){
		try {
			this.insertSelective(trainVideo, trainVideo.getId());
		} catch (Exception e) {
			logger.error("保存视频集"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * <查询视频><功能具体实现>
	 *
	 * @create：2016年12月22日 上午11:02:26
	 * @author：zzq
	 * @param id
	 * @return
	 */
	public TrainVideo selectById(String id){
		TrainVideo trainVideo = null;
		try {
			trainVideo = this.selectByPrimaryKey(TrainVideo.class,id);
		} catch (Exception e) {
			logger.error("主键查询trainVideo"+e.getMessage());
		}
		return trainVideo;
	}
	
	/**
	 * 
	 * <删除><功能具体实现>
	 *
	 * @create：2016年12月22日 上午11:03:59
	 * @author：zzq
	 * @param id
	 */
	public void deleteVideoById(String id){
		try {
			this.deleteByPrimaryKey(TrainVideo.class, id);
		} catch (Exception e) {
			logger.error("删除视频");
		}
	}
	
	/**
	 *
	 * <saveOrUpdate视频集item><功能具体实现>
	 *
	 * @create：2017/1/1 上午9:23
	 * @author：sl
	 * @param myForm
	 * @return void
	 */
	@Transactional(readOnly = false)
    public void saveOrUpdate(TrainVideo myForm) {
		try {
			myForm.setEt(new Date());
			myForm.setEb(SessionUtil.currentUserId());
			// 判断是update还是new
			if (StringUtils.isEmpty(myForm.getId())) {
				myForm.setId(UUID.get());
				myForm.setCt(new Date());
				myForm.setCb(SessionUtil.currentUserId());
				// save
				this.insertSelective(myForm, myForm.getId());
			} else {
				// update
				this.updateByPrimaryKeySelective(myForm, myForm.getId());
			}

		} catch (Exception e) {
			logger.debug("saveOrUpdate视频集item {}", e.getMessage());
			throw new RuntimeException("服务器异常");
		}
    }

	/**
	 *
	 * <根据id获取视频集parentId><功能具体实现>
	 *
	 * @create：2017/1/1 上午9:35
	 * @author：sl
	 * @param id
	 * @return java.lang.String
	 */
	public String getItemParentId(String id) {
		String parentId = "";
		try {
			TrainVideo trainVideo = this.selectByPrimaryKey(TrainVideo.class, id);
			parentId = trainVideo.getParentId();
		} catch (Exception e) {
			logger.debug("根据id获取视频集parentId", e.getMessage());
		}
		return parentId;
	}
}
