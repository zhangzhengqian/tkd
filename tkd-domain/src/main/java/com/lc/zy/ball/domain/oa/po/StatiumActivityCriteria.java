package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* StatiumActivityCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public class StatiumActivityCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public StatiumActivityCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * @param mysqlOffset 
	 *            指定返回记录行的偏移量<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlOffset(int mysqlOffset) {
        this.mysqlOffset=mysqlOffset;
    }

    /**
     * @param mysqlLength 
	 *            指定返回记录行的最大数目<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public Integer getMysqlOffset() {
        return mysqlOffset;
    }

    /**
     * @param mysqlOffset 
	 *            指定返回记录行的偏移量<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlLength(int mysqlLength) {
        this.mysqlLength=mysqlLength;
    }

    /**
     * @param mysqlLength 
	 *            指定返回记录行的最大数目<br>
	 *            mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public Integer getMysqlLength() {
        return mysqlLength;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStatiumIdIsNull() {
            addCriterion("statium_id is null");
            return (Criteria) this;
        }

        public Criteria andStatiumIdIsNotNull() {
            addCriterion("statium_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatiumIdEqualTo(Integer value) {
            addCriterion("statium_id =", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotEqualTo(Integer value) {
            addCriterion("statium_id <>", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdGreaterThan(Integer value) {
            addCriterion("statium_id >", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("statium_id >=", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdLessThan(Integer value) {
            addCriterion("statium_id <", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdLessThanOrEqualTo(Integer value) {
            addCriterion("statium_id <=", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdIn(List<Integer> values) {
            addCriterion("statium_id in", values, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotIn(List<Integer> values) {
            addCriterion("statium_id not in", values, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdBetween(Integer value1, Integer value2) {
            addCriterion("statium_id between", value1, value2, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotBetween(Integer value1, Integer value2) {
            addCriterion("statium_id not between", value1, value2, "statiumId");
            return (Criteria) this;
        }

        public Criteria andActivityTopicIsNull() {
            addCriterion("activity_topic is null");
            return (Criteria) this;
        }

        public Criteria andActivityTopicIsNotNull() {
            addCriterion("activity_topic is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTopicEqualTo(String value) {
            addCriterion("activity_topic =", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicNotEqualTo(String value) {
            addCriterion("activity_topic <>", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicGreaterThan(String value) {
            addCriterion("activity_topic >", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicGreaterThanOrEqualTo(String value) {
            addCriterion("activity_topic >=", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicLessThan(String value) {
            addCriterion("activity_topic <", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicLessThanOrEqualTo(String value) {
            addCriterion("activity_topic <=", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicLike(String value) {
            addCriterion("activity_topic like", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicNotLike(String value) {
            addCriterion("activity_topic not like", value, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicIn(List<String> values) {
            addCriterion("activity_topic in", values, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicNotIn(List<String> values) {
            addCriterion("activity_topic not in", values, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicBetween(String value1, String value2) {
            addCriterion("activity_topic between", value1, value2, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andActivityTopicNotBetween(String value1, String value2) {
            addCriterion("activity_topic not between", value1, value2, "activityTopic");
            return (Criteria) this;
        }

        public Criteria andDgNameIsNull() {
            addCriterion("dg_name is null");
            return (Criteria) this;
        }

        public Criteria andDgNameIsNotNull() {
            addCriterion("dg_name is not null");
            return (Criteria) this;
        }

        public Criteria andDgNameEqualTo(String value) {
            addCriterion("dg_name =", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameNotEqualTo(String value) {
            addCriterion("dg_name <>", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameGreaterThan(String value) {
            addCriterion("dg_name >", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameGreaterThanOrEqualTo(String value) {
            addCriterion("dg_name >=", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameLessThan(String value) {
            addCriterion("dg_name <", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameLessThanOrEqualTo(String value) {
            addCriterion("dg_name <=", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameLike(String value) {
            addCriterion("dg_name like", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameNotLike(String value) {
            addCriterion("dg_name not like", value, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameIn(List<String> values) {
            addCriterion("dg_name in", values, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameNotIn(List<String> values) {
            addCriterion("dg_name not in", values, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameBetween(String value1, String value2) {
            addCriterion("dg_name between", value1, value2, "dgName");
            return (Criteria) this;
        }

        public Criteria andDgNameNotBetween(String value1, String value2) {
            addCriterion("dg_name not between", value1, value2, "dgName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("start_time like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("start_time not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("end_time like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("end_time not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceIsNull() {
            addCriterion("host_introduce is null");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceIsNotNull() {
            addCriterion("host_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceEqualTo(String value) {
            addCriterion("host_introduce =", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceNotEqualTo(String value) {
            addCriterion("host_introduce <>", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceGreaterThan(String value) {
            addCriterion("host_introduce >", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("host_introduce >=", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceLessThan(String value) {
            addCriterion("host_introduce <", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceLessThanOrEqualTo(String value) {
            addCriterion("host_introduce <=", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceLike(String value) {
            addCriterion("host_introduce like", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceNotLike(String value) {
            addCriterion("host_introduce not like", value, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceIn(List<String> values) {
            addCriterion("host_introduce in", values, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceNotIn(List<String> values) {
            addCriterion("host_introduce not in", values, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceBetween(String value1, String value2) {
            addCriterion("host_introduce between", value1, value2, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andHostIntroduceNotBetween(String value1, String value2) {
            addCriterion("host_introduce not between", value1, value2, "hostIntroduce");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Integer value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Integer value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Integer value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Integer value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Integer value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Integer> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Integer> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Integer value1, Integer value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceIsNull() {
            addCriterion("activity_introduce is null");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceIsNotNull() {
            addCriterion("activity_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceEqualTo(String value) {
            addCriterion("activity_introduce =", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceNotEqualTo(String value) {
            addCriterion("activity_introduce <>", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceGreaterThan(String value) {
            addCriterion("activity_introduce >", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("activity_introduce >=", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceLessThan(String value) {
            addCriterion("activity_introduce <", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceLessThanOrEqualTo(String value) {
            addCriterion("activity_introduce <=", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceLike(String value) {
            addCriterion("activity_introduce like", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceNotLike(String value) {
            addCriterion("activity_introduce not like", value, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceIn(List<String> values) {
            addCriterion("activity_introduce in", values, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceNotIn(List<String> values) {
            addCriterion("activity_introduce not in", values, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceBetween(String value1, String value2) {
            addCriterion("activity_introduce between", value1, value2, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andActivityIntroduceNotBetween(String value1, String value2) {
            addCriterion("activity_introduce not between", value1, value2, "activityIntroduce");
            return (Criteria) this;
        }

        public Criteria andCtIsNull() {
            addCriterion("ct is null");
            return (Criteria) this;
        }

        public Criteria andCtIsNotNull() {
            addCriterion("ct is not null");
            return (Criteria) this;
        }

        public Criteria andCtEqualTo(Date value) {
            addCriterion("ct =", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotEqualTo(Date value) {
            addCriterion("ct <>", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThan(Date value) {
            addCriterion("ct >", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThanOrEqualTo(Date value) {
            addCriterion("ct >=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThan(Date value) {
            addCriterion("ct <", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThanOrEqualTo(Date value) {
            addCriterion("ct <=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtIn(List<Date> values) {
            addCriterion("ct in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotIn(List<Date> values) {
            addCriterion("ct not in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtBetween(Date value1, Date value2) {
            addCriterion("ct between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotBetween(Date value1, Date value2) {
            addCriterion("ct not between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCbIsNull() {
            addCriterion("cb is null");
            return (Criteria) this;
        }

        public Criteria andCbIsNotNull() {
            addCriterion("cb is not null");
            return (Criteria) this;
        }

        public Criteria andCbEqualTo(String value) {
            addCriterion("cb =", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotEqualTo(String value) {
            addCriterion("cb <>", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbGreaterThan(String value) {
            addCriterion("cb >", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbGreaterThanOrEqualTo(String value) {
            addCriterion("cb >=", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLessThan(String value) {
            addCriterion("cb <", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLessThanOrEqualTo(String value) {
            addCriterion("cb <=", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLike(String value) {
            addCriterion("cb like", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotLike(String value) {
            addCriterion("cb not like", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbIn(List<String> values) {
            addCriterion("cb in", values, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotIn(List<String> values) {
            addCriterion("cb not in", values, "cb");
            return (Criteria) this;
        }

        public Criteria andCbBetween(String value1, String value2) {
            addCriterion("cb between", value1, value2, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotBetween(String value1, String value2) {
            addCriterion("cb not between", value1, value2, "cb");
            return (Criteria) this;
        }

        public Criteria andEtIsNull() {
            addCriterion("et is null");
            return (Criteria) this;
        }

        public Criteria andEtIsNotNull() {
            addCriterion("et is not null");
            return (Criteria) this;
        }

        public Criteria andEtEqualTo(Date value) {
            addCriterion("et =", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtNotEqualTo(Date value) {
            addCriterion("et <>", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtGreaterThan(Date value) {
            addCriterion("et >", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtGreaterThanOrEqualTo(Date value) {
            addCriterion("et >=", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtLessThan(Date value) {
            addCriterion("et <", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtLessThanOrEqualTo(Date value) {
            addCriterion("et <=", value, "et");
            return (Criteria) this;
        }

        public Criteria andEtIn(List<Date> values) {
            addCriterion("et in", values, "et");
            return (Criteria) this;
        }

        public Criteria andEtNotIn(List<Date> values) {
            addCriterion("et not in", values, "et");
            return (Criteria) this;
        }

        public Criteria andEtBetween(Date value1, Date value2) {
            addCriterion("et between", value1, value2, "et");
            return (Criteria) this;
        }

        public Criteria andEtNotBetween(Date value1, Date value2) {
            addCriterion("et not between", value1, value2, "et");
            return (Criteria) this;
        }

        public Criteria andEbIsNull() {
            addCriterion("eb is null");
            return (Criteria) this;
        }

        public Criteria andEbIsNotNull() {
            addCriterion("eb is not null");
            return (Criteria) this;
        }

        public Criteria andEbEqualTo(String value) {
            addCriterion("eb =", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbNotEqualTo(String value) {
            addCriterion("eb <>", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbGreaterThan(String value) {
            addCriterion("eb >", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbGreaterThanOrEqualTo(String value) {
            addCriterion("eb >=", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbLessThan(String value) {
            addCriterion("eb <", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbLessThanOrEqualTo(String value) {
            addCriterion("eb <=", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbLike(String value) {
            addCriterion("eb like", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbNotLike(String value) {
            addCriterion("eb not like", value, "eb");
            return (Criteria) this;
        }

        public Criteria andEbIn(List<String> values) {
            addCriterion("eb in", values, "eb");
            return (Criteria) this;
        }

        public Criteria andEbNotIn(List<String> values) {
            addCriterion("eb not in", values, "eb");
            return (Criteria) this;
        }

        public Criteria andEbBetween(String value1, String value2) {
            addCriterion("eb between", value1, value2, "eb");
            return (Criteria) this;
        }

        public Criteria andEbNotBetween(String value1, String value2) {
            addCriterion("eb not between", value1, value2, "eb");
            return (Criteria) this;
        }

        public Criteria andExpiryDataIsNull() {
            addCriterion("expiry_data is null");
            return (Criteria) this;
        }

        public Criteria andExpiryDataIsNotNull() {
            addCriterion("expiry_data is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryDataEqualTo(String value) {
            addCriterion("expiry_data =", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataNotEqualTo(String value) {
            addCriterion("expiry_data <>", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataGreaterThan(String value) {
            addCriterion("expiry_data >", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataGreaterThanOrEqualTo(String value) {
            addCriterion("expiry_data >=", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataLessThan(String value) {
            addCriterion("expiry_data <", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataLessThanOrEqualTo(String value) {
            addCriterion("expiry_data <=", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataLike(String value) {
            addCriterion("expiry_data like", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataNotLike(String value) {
            addCriterion("expiry_data not like", value, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataIn(List<String> values) {
            addCriterion("expiry_data in", values, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataNotIn(List<String> values) {
            addCriterion("expiry_data not in", values, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataBetween(String value1, String value2) {
            addCriterion("expiry_data between", value1, value2, "expiryData");
            return (Criteria) this;
        }

        public Criteria andExpiryDataNotBetween(String value1, String value2) {
            addCriterion("expiry_data not between", value1, value2, "expiryData");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNull() {
            addCriterion("host_name is null");
            return (Criteria) this;
        }

        public Criteria andHostNameIsNotNull() {
            addCriterion("host_name is not null");
            return (Criteria) this;
        }

        public Criteria andHostNameEqualTo(String value) {
            addCriterion("host_name =", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotEqualTo(String value) {
            addCriterion("host_name <>", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThan(String value) {
            addCriterion("host_name >", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameGreaterThanOrEqualTo(String value) {
            addCriterion("host_name >=", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLessThan(String value) {
            addCriterion("host_name <", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLessThanOrEqualTo(String value) {
            addCriterion("host_name <=", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameLike(String value) {
            addCriterion("host_name like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotLike(String value) {
            addCriterion("host_name not like", value, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameIn(List<String> values) {
            addCriterion("host_name in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotIn(List<String> values) {
            addCriterion("host_name not in", values, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameBetween(String value1, String value2) {
            addCriterion("host_name between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andHostNameNotBetween(String value1, String value2) {
            addCriterion("host_name not between", value1, value2, "hostName");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNull() {
            addCriterion("expiry_type is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNotNull() {
            addCriterion("expiry_type is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeEqualTo(Integer value) {
            addCriterion("expiry_type =", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotEqualTo(Integer value) {
            addCriterion("expiry_type <>", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThan(Integer value) {
            addCriterion("expiry_type >", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("expiry_type >=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThan(Integer value) {
            addCriterion("expiry_type <", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("expiry_type <=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIn(List<Integer> values) {
            addCriterion("expiry_type in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotIn(List<Integer> values) {
            addCriterion("expiry_type not in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeBetween(Integer value1, Integer value2) {
            addCriterion("expiry_type between", value1, value2, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("expiry_type not between", value1, value2, "expiryType");
            return (Criteria) this;
        }

        public Criteria andFreeIsNull() {
            addCriterion("free is null");
            return (Criteria) this;
        }

        public Criteria andFreeIsNotNull() {
            addCriterion("free is not null");
            return (Criteria) this;
        }

        public Criteria andFreeEqualTo(Integer value) {
            addCriterion("free =", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeNotEqualTo(Integer value) {
            addCriterion("free <>", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeGreaterThan(Integer value) {
            addCriterion("free >", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("free >=", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeLessThan(Integer value) {
            addCriterion("free <", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeLessThanOrEqualTo(Integer value) {
            addCriterion("free <=", value, "free");
            return (Criteria) this;
        }

        public Criteria andFreeIn(List<Integer> values) {
            addCriterion("free in", values, "free");
            return (Criteria) this;
        }

        public Criteria andFreeNotIn(List<Integer> values) {
            addCriterion("free not in", values, "free");
            return (Criteria) this;
        }

        public Criteria andFreeBetween(Integer value1, Integer value2) {
            addCriterion("free between", value1, value2, "free");
            return (Criteria) this;
        }

        public Criteria andFreeNotBetween(Integer value1, Integer value2) {
            addCriterion("free not between", value1, value2, "free");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}