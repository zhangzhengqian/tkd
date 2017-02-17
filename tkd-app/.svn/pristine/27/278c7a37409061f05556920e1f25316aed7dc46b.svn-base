package com.lc.zy.ball.app.service.news;

import com.lc.zy.ball.app.common.AbstractCacheService;
import com.lc.zy.ball.app.service.news.bean.NewsVo;
import com.lc.zy.ball.domain.oa.mapper.NewsMapper;
import com.lc.zy.ball.domain.oa.po.News;
import com.lc.zy.ball.domain.oa.po.NewsCriteria;
import com.lc.zy.common.bean.CacheKeys;
import com.lc.zy.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class NewsRepository extends AbstractCacheService implements CacheKeys {

	private static Logger logger = LoggerFactory.getLogger(NewsRepository.class);

	private NewsMapper newsMapper;
	public NewsRepository() {

	}

	@Autowired
	public NewsRepository(NewsMapper newsMapper) {
		super();
		this.newsMapper = newsMapper;
	}

	/**
	 * 
	 * <新闻资讯list><功能具体实现>
	 *
	 * @create：2016年5月4日 下午9:21:09
	 * @author：sl
	 * @param begin
	 * @param size
	 * @return
	 */
	public List<NewsVo> getNewsList(Integer begin, Integer size, Integer type) {
		List<NewsVo> newsList = new ArrayList<NewsVo>();
		try {
			NewsCriteria newsCriteria = new NewsCriteria();
			NewsCriteria.Criteria criteria = newsCriteria.createCriteria();
			criteria.andTypeEqualTo(type);
			newsCriteria.setMysqlLength(size);
			newsCriteria.setMysqlOffset(begin);
			newsCriteria.setOrderByClause("pub_date desc");
			List<News> news = newsMapper.selectByExample(newsCriteria);
			for (News n : news) {
				NewsVo newsVo = new NewsVo();
				n.setImages(n.getImages().replace("__", ";"));
				BeanUtils.copyProperties(n, newsVo);
				// 发布日期处理
				newsVo.setpDate(fomatePubDate(n.getPubDate()));
				newsList.add(newsVo);
			}
		} catch (Exception e) {
			logger.debug("新闻资讯list:{}", e.getMessage());
		}
		return newsList;
	}

	/**
	 * 
	 * <发布日期处理><功能具体实现>
	 *
	 * @create：2016年5月4日 下午9:42:28
	 * @author：sl
	 * @param pubDate
	 * @return
	 */
	private String fomatePubDate(Date pubDate) {
		String pDate = "";
		try {
			// 判断日期是否是当天
			if (DateUtils.isSameDate(pubDate, new Date())) {
				Map<String, Long> dateMap = DateUtils.getDatePoor(new Date(), pubDate);
				if (dateMap.get("hour").intValue() == 0) {
					pDate = String.valueOf(dateMap.get("min").intValue()) + "分钟前";
				} else {
					pDate = String.valueOf(dateMap.get("hour").intValue()) + "小时前";
				}
			} else {
				pDate = DateUtils.getChineseDate(pubDate, "y年M月d日");
			}
		} catch (Exception e) {
			logger.debug("发布日期处理:{}", e.getMessage());
		}
		return pDate;
	}
}
