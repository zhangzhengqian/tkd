package com.lc.zy.ball.app.service.activity;

import com.lc.zy.ball.app.common.Constants;
import com.lc.zy.ball.app.service.activity.bean.StatiumActivityMemberVo;
import com.lc.zy.ball.app.service.activity.bean.StatiumActivityVo;
import com.lc.zy.ball.domain.oa.po.OaContestActivity;
import com.lc.zy.ball.domain.oa.po.StatiumActivityMember;
import com.lc.zy.common.bean.*;
import com.lc.zy.common.util.AppRequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * <app首页接口>
 * @author sl
 *
 */
@Service("activity")
@Auth
public class ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityService.class);

	private ActivityRepository activityRepository = null;

	@Autowired
	public ActivityService(ActivityRepository activityRepository){
		super();
		this.activityRepository = activityRepository;
	}


	/**
	 *
	 * <活动详情><功能具体实现>
	 *
	 * @create：2016-08-02 14:48:57
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success statiumActivityDetail(ClientRequest request){
		String sn = request.getSn();
		String token = request.getToken();
		try {
			String sActivityId = AppRequestUtil.getParameter(request, "sActivityId");
			logger.debug("sActivityId={} ", sActivityId);
			StatiumActivityVo statiumActivitieVos = activityRepository.getStatiumActivityDetail(sActivityId, token);
			return new Success(sn, true, new KeyValueEntity("statiumActivityDetail", statiumActivitieVos));
		} catch (Exception e) {
			logger.error("createOrder_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, e.getMessage()));
		}
	}

	/**
	 *
	 * <活动报名人员详情><功能具体实现>
	 *
	 * @create：2016-08-02 15:51:10
	 * @author：sl
	 * @param request
	 * @return com.lc.zy.common.bean.Success
	 */
	@Auth(false)
	public Success statiumActivityMember(ClientRequest request){
		String sn = request.getSn();
		try {
			String sActivityId = AppRequestUtil.getParameter(request, "sActivityId");
			Integer begin = AppRequestUtil.getParameterInteger(request, "begin");
			Integer size = AppRequestUtil.getParameterInteger(request, "size");
			if (size == null) {
				size = 10;
			}
			if (begin == null) {
				begin = 0;
			}
			logger.debug("sActivityId={} begin={} size={}", sActivityId, begin, size);
			List<StatiumActivityMember> statiumActivityMemberList = activityRepository.getSActivityMemberList(sActivityId, "", begin, size);

			List<StatiumActivityMemberVo> statiumMembers = activityRepository.getSsouserByMember(statiumActivityMemberList);
			return new Success(sn, true, new KeyValueEntity("activityMember", statiumMembers));
		} catch (Exception e) {
			logger.error("createOrder_exception:" + sn, e);
			return new Success(sn, false, new Reason(Constants.SYSTEM_KEY, e.getMessage()));
		}
	}
	
	/**
	 * 
	 * <赛事活动列表><功能具体实现>
	 *
	 * @create：2017年2月13日 下午5:50:47
	 * @author：zzq
	 * @param request
	 * @return
	 */
	@Auth(false)
	public Success getContestActivity(ClientRequest request){
		String sn = request.getSn();
		try {
			List<OaContestActivity> list = activityRepository.getContestActivityList();
			return new Success(sn,true,new KeyValueEntity("activityList", list));
		} catch (Exception e) {
			logger.error("getContestActivity_error"+e.getMessage());
			return new Success(sn, false, new KeyValueEntity(Constants.SYSTEM_KEY, Constants.SYSTEM_VALUE));
		}
	}
	
	
}
