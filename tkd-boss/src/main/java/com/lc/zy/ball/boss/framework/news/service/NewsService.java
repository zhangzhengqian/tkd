package com.lc.zy.ball.boss.framework.news.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.lc.zy.ball.boss.framework.news.vo.NewsVo;
import com.lc.zy.ball.common.data.pageable.Page;
import com.lc.zy.ball.common.data.pageable.PageImpl;
import com.lc.zy.ball.common.data.pageable.PageRequest;
import com.lc.zy.ball.domain.oa.mapper.NewsMapper;
import com.lc.zy.ball.domain.oa.po.News;
import com.lc.zy.ball.domain.oa.po.NewsCriteria;
import com.lc.zy.common.data.Criterias;
import com.lc.zy.common.util.DateUtils;
import com.lc.zy.common.util.UUID;

@Service
@Transactional(readOnly = true)
public class NewsService extends AbstractCacheService {
	private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

	@Resource(name = "oaJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NewsMapper newsMapper;

	/**
	 * 
	 * <新闻资讯list><功能具体实现>
	 *
	 * @create：2016年5月4日 下午9:08:16
	 * @author：sl
	 * @param pageRequest
	 * @param searchParams
	 * @return
	 */
	public Page<NewsVo> findNewsList(PageRequest pageRequest, Map<String, Object> searchParams) {
		int total = 0;
		List<NewsVo> newsVos = new ArrayList<NewsVo>();
		try {
			NewsCriteria newsCriteria = new NewsCriteria();
			NewsCriteria.Criteria criteria = newsCriteria.createCriteria();
			Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
			newsCriteria.setMysqlLength(pageRequest.getPageSize());
			newsCriteria.setMysqlOffset(pageRequest.getOffset());
			newsCriteria.setOrderByClause("ct asc");
			//展示联盟新闻  绑定道馆id是null
			criteria.andStatiumidIsNull();
			Criterias.bySearchFilter(criteria, filters.values());
			total = newsMapper.countByExample(newsCriteria);
			List<News> newsList = newsMapper.selectByExample(newsCriteria);
			for (News news : newsList) {
				NewsVo newsVo = new NewsVo();
				BeanUtils.copyProperties(news, newsVo);
				if (news.getImages() != null) {
					String[] images = news.getImages().split("__");
					newsVo.setImage(images[0]);
				}
				newsVos.add(newsVo);
			}
		} catch (Exception e) {
			logger.debug("新闻资讯list:{}", e.getMessage());
		}
		return new PageImpl<NewsVo>(newsVos, pageRequest, total);
	}

	/**
	 * 
	 * <新闻资讯保存><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:21:47
	 * @author：sl
	 * @param myForm
	 */
	@Transactional(readOnly = false)
	public void save(NewsVo myForm) {
		try {
			// date
			Date now = new Date();
			// userId
			String uId = SessionUtil.currentUserId();
			News news = new News();
			news.setImages(myForm.getImages());
			news.setTitle(myForm.getTitle());
			news.setUrl(myForm.getUrl());
			//类型
			news.setType(myForm.getType());
			if (StringUtils.isEmpty(myForm.getId())) {// 新增
				// id
				String id = UUID.get();
				news.setId(id);
				// ct
				news.setCt(now);
				// cb
				news.setCb(uId);
				// et
				news.setEt(now);
				// eb
				news.setEb(uId);
				try {
					this.insertSelective(news, id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {// 修改
				// id
				news.setId(myForm.getId());
				// et
				news.setEt(now);
				// eb
				news.setEb(uId);
				this.updateByPrimaryKeySelective(news, news.getId());
			}
		} catch (Exception e) {
			logger.debug("新闻资讯保存:{}", e.getMessage());
		}
	}

	/**
	 * 
	 * <根据id获取新闻资讯信息><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:26:30
	 * @author：sl
	 * @param id
	 * @return
	 */
	public News newsById(String id) {
		News news = new News();
		try {
			news = this.selectByPrimaryKey(News.class, id);
		} catch (Exception e) {
			logger.debug("根据id获取新闻资讯信息:{}", e.getMessage());
		}
		return news;
	}

	/**
	 * 
	 * <删除新闻资讯><功能具体实现>
	 *
	 * @create：2016年5月5日 下午3:31:53
	 * @author：sl
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		try {
			this.deleteByPrimaryKey(News.class, id);
		} catch (Exception e) {
			logger.debug("删除新闻资讯:{}", e.getMessage());
		}
	}

}
