package com.lc.zy.ball.app.service.statium;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.statium.bean.ClassDetalVo;
import com.lc.zy.ball.app.service.statium.bean.ClassInfoVo;
import com.lc.zy.ball.app.service.statium.bean.ClassListVo;
import com.lc.zy.ball.app.service.statium.bean.StatiumInfoVo;
import com.lc.zy.ball.domain.oa.mapper.StatiumInfosMapper;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.ball.domain.oa.po.StatiumInfos;
import com.lc.zy.ball.domain.oa.po.StatiumInfosCriteria;
import com.lc.zy.ball.solr.StatiumBean;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.search.PageBean;
import com.lc.zy.common.util.AppRequestUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * 道馆模块
 *
 * @author sl
 */
@Service("statium")
public class StatiumService {

    private static Logger logger = LoggerFactory.getLogger(StatiumService.class);

    @Resource(name = "configs")
    private Map<String, String> configs = null;

    private StatiumRepository statiumRepository = null;
    
    @Autowired
    private StatiumInfosMapper statiumInfosMapper;

    @Autowired
    public StatiumService(StatiumRepository statiumRepository) {
        super();
        this.statiumRepository = statiumRepository;
    }

    /**
     * <查询道馆><功能具体实现>
     *
     * @param request
     * @return
     * @create：2016年4月26日 上午10:42:46
     * @author：sl
     */
    @Auth(false)
    public Success search(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        String version = request.getVersion();
        String channel = request.getChannel();
        try {
            String type = AppRequestUtil.getParameter(request, "type"); // 请求类型map：地图附近的场馆列表list：场馆列表
            String province = AppRequestUtil.getParameter(request, "province");
            String city = AppRequestUtil.getParameter(request, "city");
            String area = AppRequestUtil.getParameter(request, "area");
            Integer recommend = AppRequestUtil.getParameterInteger(request, "recommend"); // 推荐
            String keyword = AppRequestUtil.getParameter(request, "keyword");
            Double lng = AppRequestUtil.getParameterDouble(request, "lng");
            Double lat = AppRequestUtil.getParameterDouble(request, "lat");
            Double radius = AppRequestUtil.getParameterDouble(request, "radius");
            Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
            Integer size = AppRequestUtil.getParameterInteger(request, "size");
            if (lng <= 0.0) {
                lng = 116.403651;
            }
            if (lat <= 0.0) {
                lat = 39.916105;
            }
            if (begin < 0) {
                begin = 0;
            }
            if (size <= 0) {
                size = 10;
            }
            if (StringUtils.isEmpty(type)) {
                type = "list";
            }
            logger.debug("lng={} , lat={}  ,radius={} , begin={} , size={}, version={} ,channel = {}", lng, lat, radius,
                    begin, size, version, channel);
            PageBean<StatiumBean> page = statiumRepository.search(token, type, province, city, area, lng, lat, radius,
                    keyword, recommend, begin, size, version, channel);
            return new Success(sn, true, new KeyValueEntity("statiumBean", page));
        } catch (Exception e) {
            logger.error("statiumList_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }

    /**
     * <道馆详情><功能具体实现>
     *
     * @param request
     * @return
     * @create：2016年4月26日 上午11:29:00
     * @author：sl
     */
    @Auth(false)
    public Success detail(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        try {
            String statiumId = AppRequestUtil.getParameter(request, "statiumId");
            logger.debug("statiumId={} ", statiumId);
            if (StringUtils.isEmpty(statiumId)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "道馆id不能为空"));
            }
            //modify by zzq 2016-12-2 针对未审核的道馆  不能进入道馆详情页面
            StatiumInfosCriteria statiumInfosCriteria = new StatiumInfosCriteria();
            statiumInfosCriteria.createCriteria().andDgIdEqualTo(Integer.valueOf(statiumId));
            List<StatiumInfos> dgList = statiumInfosMapper.selectByExample(statiumInfosCriteria);
            String flag = "false";
            if(dgList!=null){
            	String status = dgList.get(0).getStatus().toString();
            	if(status!=null){
            		if(status.equals(Constants.StatiumStatus.STATUS_SUCCESS)){
            			flag = "true";
            		}
            	}
            }
            StatiumInfoVo statiumInfoVo = statiumRepository.detail(statiumId, token);
            return new Success(sn, true, new KeyValueEntity("statiumInfo", statiumInfoVo).append("flag",flag));
        } catch (Exception e) {
            logger.error("statiumDetail_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }

    /**
     * <根据课程id获取课程详情><功能具体实现>
     *
     * @param request
     * @return
     * @create：2016年4月26日 下午10:24:01
     * @author：sl
     */
    @Auth(false)
    public Success classInfo(ClientRequest request) {
        String sn = request.getSn();
        try {
            String classId = AppRequestUtil.getParameter(request, "classId");
            logger.debug("classId={} ", classId);
            if (StringUtils.isEmpty(classId)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "课程id不能为空"));
            }
            List<ClassInfoVo> classInfoVos = statiumRepository.classInfoVos(classId);
            return new Success(sn, true, new KeyValueEntity("classInfo", classInfoVos));
        } catch (Exception e) {
            logger.error("statiumDetail_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }

    }

    /**
     * <课时详情><功能具体实现>
     *
     * @param request
     * @return
     * @create：2016年4月27日 下午2:15:50
     * @author：sl
     */
    @Auth
    public Success classDetail(ClientRequest request) {
        String sn = request.getSn();
        String token = request.getToken();
        // version
        String version = request.getVersion();
        String channel = request.getChannel();
        logger.debug("version:{}", version);
        logger.debug("channel:{}", channel);
        try {
            String classInfoId = AppRequestUtil.getParameter(request, "classInfoId");
            logger.debug("classInfoId={} ", classInfoId);
            if (StringUtils.isEmpty(classInfoId)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "课时id不能为空"));
            }
            ClassDetalVo classDetalVo = statiumRepository.classDetail(classInfoId, token);
            // 针对ios
//            Double _version = Double.parseDouble(version);
//            Double iosVersion = 2.02D;
//            logger.debug("version:{}", version);
//            if (iosVersion.compareTo(_version) == 0 && "ios".equalsIgnoreCase(channel)) {
//                int price = classDetalVo.getPrice();
//                int disCount = classDetalVo.getDiscountPrice();
//                classDetalVo.setPrice(disCount);
//                classDetalVo.setDiscountPrice(price);
//            }
            return new Success(sn, true, new KeyValueEntity("classDetail", classDetalVo));
        } catch (Exception e) {
            logger.error("statiumDetail_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }

    }

    /**
     * <根据道馆id获取大课/私教><功能具体实现>
     *
     * @param request
     * @return
     * @create：2016年4月28日 下午2:55:55
     * @author：sl
     */
    @Auth(false)
    public Success coachList(ClientRequest request) {
        String sn = request.getSn();
        try {
            String statiumId = AppRequestUtil.getParameter(request, "statiumId");
            String type = AppRequestUtil.getParameter(request, "type");
            logger.debug("statiumId={}, type={} ", statiumId, type);
            if (StringUtils.isEmpty(statiumId)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "道馆id不能为空"));
            }
            List<Coach> coachs = statiumRepository.coachList(statiumId, type);
            return new Success(sn, true, new KeyValueEntity("coachs", coachs));
        } catch (Exception e) {
            logger.error("statiumDetail_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }

    /**
     * <根据日期获取课程list><功能具体实现>
     *
     * @param request
     * @return com.lc.zy.common.bean.Success
     * @create：2016-07-24 11:10:27
     * @author：sl
     */
    @Auth(false)
    public Success classListInfo(ClientRequest request) {
        String sn = request.getSn();
        try {
            // 道馆id
            String statiumId = AppRequestUtil.getParameter(request, "statiumId");
            // 课程日期
            String classDate = AppRequestUtil.getParameter(request, "classDate");
            logger.debug("statiumId={},classDate={}", statiumId, classDate);
            // 判断道馆id是否为空
            if (StringUtils.isEmpty(statiumId)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "道馆id不能为空"));
            }
            // 判断课程日期是否为空
            if (StringUtils.isEmpty(classDate)) {
                return new Success(sn, false, new Reason(Constants.ERROR, "课程日期不能为空"));
            }
            // 课程详情
            List<ClassListVo> classListVos = statiumRepository.classListByDate(statiumId, classDate);
            return new Success(sn, true, new KeyValueEntity("classListVos", classListVos));
        } catch (Exception e) {
            logger.error("classListInfo_exception:" + sn, e);
            return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
        }
    }
}
