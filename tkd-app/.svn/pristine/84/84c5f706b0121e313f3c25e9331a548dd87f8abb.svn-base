package com.lc.zy.ball.app.common;

import com.lc.zy.ball.domain.oa.mapper.*;
import com.lc.zy.ball.domain.oa.po.*;
import com.lc.zy.common.bean.CacheKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sl on 16/7/29.
 */
@Repository
public class CheckUtils extends AbstractCacheService implements CacheKeys {

    private static Logger logger = LoggerFactory.getLogger(CheckUtils.class);

    private StatiumActivityMemberMapper statiumActivityMemberMapper = null;

    private StatiumClassMemberMapper statiumClassMemberMapper = null;

    private OrderMapper orderMapper = null;

    private StatiumCommentMapper statiumCommentMapper = null;


    @Autowired
    public CheckUtils(StatiumActivityMemberMapper statiumActivityMemberMapper, StatiumClassMemberMapper statiumClassMemberMapper, OrderMapper orderMapper, StatiumCommentMapper statiumCommentMapper) {
        super();
        this.statiumActivityMemberMapper = statiumActivityMemberMapper;
        this.statiumClassMemberMapper = statiumClassMemberMapper;
        this.orderMapper = orderMapper;
        this.statiumCommentMapper = statiumCommentMapper;
    }

    public CheckUtils(){

    }

    /**
     * <校验是否可以报名><功能具体实现>
     *
     * @param classInfoId
     * @return java.lang.Boolean
     * @create：2016-07-24 14:49:04
     * @author：sl
     */
    public boolean checkClass(String classInfoId) {
        boolean signFlag = false;
        try {
            StatiumClassInfo classInfo = this.selectByPrimaryKey(StatiumClassInfo.class, classInfoId);
            int num = 0;
            if (classInfo != null) {
                StatiumClass sClass = this.selectByPrimaryKey(StatiumClass.class, classInfo.getClassId());
                num = sClass.getMaxPeople();
            }
            if (num - signNum(classInfoId) > 0) {
                signFlag = true;
            }
        } catch (Exception e) {
            logger.debug("校验是否可以报名", e.getMessage());
        }
        return signFlag;
    }

    /**
     * <获取道馆课时报名人数><功能具体实现>
     *
     * @param classInfoId
     * @return int
     * @create：2016-07-19 19:31:43
     * @author：sl
     */
    public int signNum(String classInfoId) {
        int num = 0;
        try {
            StatiumClassMemberCriteria criteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria cri = criteria.createCriteria();
            cri.andClassInfoIdEqualTo(classInfoId);
            num = statiumClassMemberMapper.countByExample(criteria);
        } catch (Exception e) {
            logger.debug("获取道馆课时报名人数: {}", e.getMessage());
        }
        return num;
    }

    /**
     *
     * <校验活动是否已经报名><功能具体实现>
     *
     * @create：2016-07-29 15:13:22
     * @author：sl
     * @param activityId
     * @param userId
     * @return boolean
     */
    public boolean checkActivitySign(String activityId, String userId) {
        boolean flag = true;
        try {
            StatiumActivityMemberCriteria statiumActivityMemberCriteria = new StatiumActivityMemberCriteria();
            StatiumActivityMemberCriteria.Criteria criteria = statiumActivityMemberCriteria.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andActivityIdEqualTo(activityId);
            List<StatiumActivityMember> statiumActivityMembers = statiumActivityMemberMapper.selectByExample(statiumActivityMemberCriteria);
            if (statiumActivityMembers.size() > 0) {
                flag = false;
            }
        } catch (Exception e) {
            logger.debug("校验活动是否已经报名", e.getMessage());
        }
        return flag;
    }

    /**
     *
     * <校验课程是否已经报名><功能具体实现>
     *
     * @create：2016-07-29 15:01:32
     * @author：sl
     * @param classInfoId
     * @param userId
     * @return boolean
     */
    public boolean checkClassSign(String classInfoId, String userId) {
        boolean flag = true;
        try {
            StatiumClassMemberCriteria statiumClassMemberCriteria = new StatiumClassMemberCriteria();
            StatiumClassMemberCriteria.Criteria criteria = statiumClassMemberCriteria.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andClassInfoIdEqualTo(classInfoId);
            List<StatiumClassMember> statiumClassMembers = statiumClassMemberMapper.selectByExample(statiumClassMemberCriteria);
            if (statiumClassMembers.size() > 0) {
                flag = false;
            }
        } catch (Exception e) {
            logger.debug("校验课程是否已经报名", e.getMessage());
        }
        return flag;
    }

    /**
     *
     * <校验活动是否可以报名><false:不可报名,true:可报名>
     *
     * @create：2016-07-27 23:54:42
     * @author：sl
     * @param activityId
     * @return boolean
     */
    public boolean checkActivity(String activityId) {
        Boolean signFlag = false;
        try {
            StatiumActivity statiumActivity = this.selectByPrimaryKey(StatiumActivity.class, activityId);
            int num = statiumActivity.getAmount();
            if (num - signActivityNum(activityId) > 0) {
                signFlag = true;
            }
        } catch (Exception e) {
            logger.debug("校验活动是否可以报名", e.getMessage());
        }
        return signFlag;
    }

    /**
     *
     * <获取活动报名人数><功能具体实现>
     *
     * @create：2016-07-27 23:53:56
     * @author：sl
     * @param activityId
     * @return int
     */
    public int signActivityNum(String activityId) {
        int num = 0;
        try {
            StatiumActivityMemberCriteria statiumActivityMemberCriteria = new StatiumActivityMemberCriteria();
            StatiumActivityMemberCriteria.Criteria criteria = statiumActivityMemberCriteria.createCriteria();
            criteria.andActivityIdEqualTo(activityId);
            num = statiumActivityMemberMapper.countByExample(statiumActivityMemberCriteria);
        } catch (Exception e) {
            logger.debug("获取活动报名人数", e.getMessage());
        }
        return num;
    }

    /**
     *
     * <判断用户是否有1元体验次数><功能具体实现>
     *
     * @create：2016-08-06 20:06:48
     * @author：sl
     * @param userId
     * @return int
     */
    public int checkIsExperience(String userId) {
        // 0:可体验 1:不可体验
        int isExperience = 0;
        try {
            SsoUser ssoUser = this.selectByPrimaryKey(SsoUser.class, userId);
            if (ssoUser.getExperience() > 0) {
                isExperience = 1;
            }
        } catch (Exception e) {
            logger.debug("判断用户是否有1元体验次数", e.getMessage());
        }
        return isExperience;
    }

    /**
     *
     * <判断用户是否有评论订单><功能具体实现>
     *
     * @create：
     * @author：sl
     * @return
     */
    public int checkIsComment(String userId, int statiumId){
        // 0:未评论 1:已评论
        int isComment = 0;
        try {
            OrderCriteria orderCriteria = new OrderCriteria();
            OrderCriteria.Criteria criteria = orderCriteria.createCriteria();
            criteria.andStatiumIdEqualTo(statiumId);
            criteria.andUserIdEqualTo(userId);
            criteria.andIsCommentEqualTo(0);
            criteria.andOrdersTypeEqualTo(Constants.OrderType.STATIUM);
            criteria.andStatusEqualTo(Constants.OrderStatus.BILLED);
            int num = orderMapper.countByExample(orderCriteria);
            if (num == 0) {
                isComment = 1;
            }
        } catch (Exception e) {
            logger.debug("判断用户是否有评论订单:{}", e.getMessage());
        }
        return isComment;
    }

    /**
     *
     * <判读用户评论类型><功能具体实现>
     *
     * @create：
     * @author：sl
     * @return
     */
    public int checkIsCommentType(String userId, int statiumId){
        // 0: 评论教练 1:评论道馆和教练
        int isCommentType = 0;
        try {
            StatiumCommentCriteria statiumCommentCriteria = new StatiumCommentCriteria();
            StatiumCommentCriteria.Criteria criteria = statiumCommentCriteria.createCriteria();
            criteria.andUidEqualTo(userId);
            criteria.andStatiumIdEqualTo(statiumId);
            int num = statiumCommentMapper.countByExample(statiumCommentCriteria);
            if (num == 0) {
                isCommentType = 1;
            }
        } catch (Exception e) {
            logger.debug("判读用户评论类型:{}", e.getMessage());
        }
        return isCommentType;
    }
}
