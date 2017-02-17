package com.lc.zy.ball.app.service.news;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.news.bean.NewsVo;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻资讯模块
 * 
 * @author sl
 */
@Service("news")
public class NewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsService.class);

	private NewsRepository newsRepository = null;

	@Autowired
	public NewsService(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}

	/**
	 * 
	 * <新闻资讯list><功能具体实现>
	 *
	 * @create：2016年5月4日 下午9:21:38
	 * @author：sl
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success newsList(ClientRequest request) {
		String sn = request.getSn();
        try {
        	// 分页
			Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
			Integer size = AppRequestUtil.getParameterInteger(request, "size");
			Integer type = AppRequestUtil.getParameterInteger(request, "type");
			if (begin == null || begin == -1) {
				begin = 0;
			}
			if (size == null || size == -1 || size == 0) {
				size = 10;
			}
			logger.debug(" begin={} , size={}", begin, size);
			List<NewsVo> list = newsRepository.getNewsList(begin, size, type);
			return new Success(sn, true, new KeyValueEntity("list", list));
        } catch (Exception e) {
            logger.error("statiumDetail_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
	}
}
