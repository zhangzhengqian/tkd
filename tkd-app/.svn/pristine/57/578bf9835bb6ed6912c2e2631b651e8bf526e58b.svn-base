package com.lc.zy.ball.app.service.coach;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.coach.bean.CoachVo;
import com.lc.zy.ball.domain.oa.po.Coach;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.AppRequestUtil;

/**
 * 教练模块
 * 
 * @author sl
 */
@Service("coach")
public class CoachService {

	private static Logger logger = LoggerFactory.getLogger(CoachService.class);

	@Resource(name = "configs")
	private Map<String, String> configs = null;

	private CoachRepository coachRepository;

	@Autowired
	public CoachService(CoachRepository coachRepository) {
		super();
		this.coachRepository = coachRepository;
	}

	@Auth(false)
	public Success coachs(ClientRequest request) {
		String sn = request.getSn();
		try {
			// 城市
			String city = AppRequestUtil.getParameter(request, "city");
			// 教练类别
			String type = AppRequestUtil.getParameter(request, "type");
			// 分页
			Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
			Integer size = AppRequestUtil.getParameterInteger(request, "size");
			if (begin == null || begin == -1) {
				begin = 0;
			}
			if (size == null || size == -1 || size == 0) {
				size = 10;
			}
			logger.debug(" begin={} , size={}", begin, size);
			List<Coach> list = coachRepository.getCoachList(begin, size, type, city);
			return new Success(sn, true, new KeyValueEntity("list", list));
		} catch (Exception e) {
			logger.error("coachs_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
	
	/**
	 * 
	 * <教练详情><功能具体实现>
	 *
	 * @create：2016年4月28日 下午4:12:33
	 * @author：sl
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success coachInfo(ClientRequest request) {
		String sn = request.getSn();
		try {
			// 教练id
			String coachId = AppRequestUtil.getParameter(request, "coachId");
			if (StringUtils.isEmpty(coachId)) {
				return new Success(sn, false, new Reason(Constants.ERROR, "教练id不能为空"));
			}
			logger.debug(" coachId={}", coachId);
			CoachVo coachVo = coachRepository.getCoachInfo(coachId);
			return new Success(sn, true, new KeyValueEntity("coachInfo", coachVo));
		} catch (Exception e) {
			logger.error("coachInfo_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
}
