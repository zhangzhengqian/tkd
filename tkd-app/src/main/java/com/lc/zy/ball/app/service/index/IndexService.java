package com.lc.zy.ball.app.service.index;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.domain.oa.mapper.TrainVideoMapper;
import com.lc.zy.ball.domain.oa.mapper.videoGroupMapper;
import com.lc.zy.ball.domain.oa.po.Carousel;
import com.lc.zy.ball.domain.oa.po.TrainVideo;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * <app首页接口>
 * @author sl
 *
 */
@Service("index")
@Auth
public class IndexService {

	private static Logger logger = LoggerFactory.getLogger(IndexService.class);

	@Autowired
	private IndexRepository indexRepository;
	
	@Autowired
	private videoGroupMapper videoGroupMapper1;
	
	@Autowired
	private TrainVideoMapper trainVideoMapper;
	
	
	/**
	 * 
	 * <获取轮播图列表><功能具体实现>
	 *
	 * @create：2016年12月21日 下午5:09:01
	 * @author：zzq
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success getCarousel(ClientRequest request) {
		String sn = request.getSn();
        // video
        String carouselType = AppRequestUtil.getParameter(request, "type");
		try {
            if (StringUtils.isEmpty(carouselType)) {
                carouselType = "";
            }
			List<Carousel> carouselsList = indexRepository.getCarousel(carouselType);
			return new Success(sn, true, new KeyValueEntity("carouselsList", carouselsList));
		} catch (Exception e) {
			logger.error("carouselsList_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
	
	/**
	 *
	 * <获取视频集子集视频信息><功能具体实现>
	 *
	 * @create：2016/12/22 下午5:10
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success getVideoGroup(ClientRequest request){
		String sn = request.getSn();
		int page = AppRequestUtil.getParameterInteger(request, "begin");
		int size = AppRequestUtil.getParameterInteger(request, "size");
		String parentId = AppRequestUtil.getParameter(request, "parentId");
		try {
			List<TrainVideo> list = indexRepository.trainVideos(page, size, parentId);
			return new Success(sn, true, new KeyValueEntity("videoList", list));
		} catch (Exception e) {
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
	

    /**
     *
     * <获取道馆城市><功能具体实现>
     *
     * @create：16/9/12 16:25
     * @author：sl
     * @param request
     * @return com.lc.zy.common.bean.Success
     */
    @Auth(false)
    public Success getCitySigned(ClientRequest request) {
        String sn = request.getSn();
        try {
            List<Map<String, Object>> cityList = indexRepository.getCitySigned();
            return new Success(sn, true, new KeyValueEntity("cityList", cityList));
        } catch (Exception e) {
            logger.error("getCitySigned_exception:" + sn, e);
            return new Success(sn, false, new Reason("cityList_exception", e.getMessage()));
        }
    }
}
