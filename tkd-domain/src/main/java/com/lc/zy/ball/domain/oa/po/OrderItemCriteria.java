package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
* OrderItemCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-11-18 16:13:54
*/
public class OrderItemCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public OrderItemCriteria() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("class_id like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("class_id not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdIsNull() {
            addCriterion("class_info_id is null");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdIsNotNull() {
            addCriterion("class_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdEqualTo(String value) {
            addCriterion("class_info_id =", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdNotEqualTo(String value) {
            addCriterion("class_info_id <>", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdGreaterThan(String value) {
            addCriterion("class_info_id >", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_info_id >=", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdLessThan(String value) {
            addCriterion("class_info_id <", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdLessThanOrEqualTo(String value) {
            addCriterion("class_info_id <=", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdLike(String value) {
            addCriterion("class_info_id like", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdNotLike(String value) {
            addCriterion("class_info_id not like", value, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdIn(List<String> values) {
            addCriterion("class_info_id in", values, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdNotIn(List<String> values) {
            addCriterion("class_info_id not in", values, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdBetween(String value1, String value2) {
            addCriterion("class_info_id between", value1, value2, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassInfoIdNotBetween(String value1, String value2) {
            addCriterion("class_info_id not between", value1, value2, "classInfoId");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(String value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(String value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(String value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(String value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(String value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(String value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLike(String value) {
            addCriterion("activity_id like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotLike(String value) {
            addCriterion("activity_id not like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<String> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<String> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(String value1, String value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(String value1, String value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNull() {
            addCriterion("sign_date is null");
            return (Criteria) this;
        }

        public Criteria andSignDateIsNotNull() {
            addCriterion("sign_date is not null");
            return (Criteria) this;
        }

        public Criteria andSignDateEqualTo(Date value) {
            addCriterionForJDBCDate("sign_date =", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("sign_date <>", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThan(Date value) {
            addCriterionForJDBCDate("sign_date >", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sign_date >=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThan(Date value) {
            addCriterionForJDBCDate("sign_date <", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sign_date <=", value, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateIn(List<Date> values) {
            addCriterionForJDBCDate("sign_date in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("sign_date not in", values, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sign_date between", value1, value2, "signDate");
            return (Criteria) this;
        }

        public Criteria andSignDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sign_date not between", value1, value2, "signDate");
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

        public Criteria andWaitTimeIsNull() {
            addCriterion("wait_time is null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIsNotNull() {
            addCriterion("wait_time is not null");
            return (Criteria) this;
        }

        public Criteria andWaitTimeEqualTo(Integer value) {
            addCriterion("wait_time =", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotEqualTo(Integer value) {
            addCriterion("wait_time <>", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThan(Integer value) {
            addCriterion("wait_time >", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("wait_time >=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThan(Integer value) {
            addCriterion("wait_time <", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("wait_time <=", value, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeIn(List<Integer> values) {
            addCriterion("wait_time in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotIn(List<Integer> values) {
            addCriterion("wait_time not in", values, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeBetween(Integer value1, Integer value2) {
            addCriterion("wait_time between", value1, value2, "waitTime");
            return (Criteria) this;
        }

        public Criteria andWaitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("wait_time not between", value1, value2, "waitTime");
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

        public Criteria andOrdersTypeIsNull() {
            addCriterion("orders_type is null");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeIsNotNull() {
            addCriterion("orders_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeEqualTo(String value) {
            addCriterion("orders_type =", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeNotEqualTo(String value) {
            addCriterion("orders_type <>", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeGreaterThan(String value) {
            addCriterion("orders_type >", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeGreaterThanOrEqualTo(String value) {
            addCriterion("orders_type >=", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeLessThan(String value) {
            addCriterion("orders_type <", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeLessThanOrEqualTo(String value) {
            addCriterion("orders_type <=", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeLike(String value) {
            addCriterion("orders_type like", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeNotLike(String value) {
            addCriterion("orders_type not like", value, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeIn(List<String> values) {
            addCriterion("orders_type in", values, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeNotIn(List<String> values) {
            addCriterion("orders_type not in", values, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeBetween(String value1, String value2) {
            addCriterion("orders_type between", value1, value2, "ordersType");
            return (Criteria) this;
        }

        public Criteria andOrdersTypeNotBetween(String value1, String value2) {
            addCriterion("orders_type not between", value1, value2, "ordersType");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(Integer value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(Integer value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(Integer value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(Integer value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(Integer value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<Integer> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<Integer> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(Integer value1, Integer value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCoachIdIsNull() {
            addCriterion("coach_id is null");
            return (Criteria) this;
        }

        public Criteria andCoachIdIsNotNull() {
            addCriterion("coach_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoachIdEqualTo(String value) {
            addCriterion("coach_id =", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdNotEqualTo(String value) {
            addCriterion("coach_id <>", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdGreaterThan(String value) {
            addCriterion("coach_id >", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdGreaterThanOrEqualTo(String value) {
            addCriterion("coach_id >=", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdLessThan(String value) {
            addCriterion("coach_id <", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdLessThanOrEqualTo(String value) {
            addCriterion("coach_id <=", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdLike(String value) {
            addCriterion("coach_id like", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdNotLike(String value) {
            addCriterion("coach_id not like", value, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdIn(List<String> values) {
            addCriterion("coach_id in", values, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdNotIn(List<String> values) {
            addCriterion("coach_id not in", values, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdBetween(String value1, String value2) {
            addCriterion("coach_id between", value1, value2, "coachId");
            return (Criteria) this;
        }

        public Criteria andCoachIdNotBetween(String value1, String value2) {
            addCriterion("coach_id not between", value1, value2, "coachId");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActiveDateIsNull() {
            addCriterion("active_date is null");
            return (Criteria) this;
        }

        public Criteria andActiveDateIsNotNull() {
            addCriterion("active_date is not null");
            return (Criteria) this;
        }

        public Criteria andActiveDateEqualTo(Date value) {
            addCriterion("active_date =", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateNotEqualTo(Date value) {
            addCriterion("active_date <>", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateGreaterThan(Date value) {
            addCriterion("active_date >", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("active_date >=", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateLessThan(Date value) {
            addCriterion("active_date <", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateLessThanOrEqualTo(Date value) {
            addCriterion("active_date <=", value, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateIn(List<Date> values) {
            addCriterion("active_date in", values, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateNotIn(List<Date> values) {
            addCriterion("active_date not in", values, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateBetween(Date value1, Date value2) {
            addCriterion("active_date between", value1, value2, "activeDate");
            return (Criteria) this;
        }

        public Criteria andActiveDateNotBetween(Date value1, Date value2) {
            addCriterion("active_date not between", value1, value2, "activeDate");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("card_type is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("card_type is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(Integer value) {
            addCriterion("card_type =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(Integer value) {
            addCriterion("card_type <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(Integer value) {
            addCriterion("card_type >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_type >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(Integer value) {
            addCriterion("card_type <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(Integer value) {
            addCriterion("card_type <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<Integer> values) {
            addCriterion("card_type in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<Integer> values) {
            addCriterion("card_type not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(Integer value1, Integer value2) {
            addCriterion("card_type between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("card_type not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNull() {
            addCriterion("card_status is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNotNull() {
            addCriterion("card_status is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusEqualTo(Integer value) {
            addCriterion("card_status =", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotEqualTo(Integer value) {
            addCriterion("card_status <>", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThan(Integer value) {
            addCriterion("card_status >", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_status >=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThan(Integer value) {
            addCriterion("card_status <", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThanOrEqualTo(Integer value) {
            addCriterion("card_status <=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIn(List<Integer> values) {
            addCriterion("card_status in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotIn(List<Integer> values) {
            addCriterion("card_status not in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusBetween(Integer value1, Integer value2) {
            addCriterion("card_status between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("card_status not between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("card_id like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("card_id not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateIsNull() {
            addCriterion("card_buy_date is null");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateIsNotNull() {
            addCriterion("card_buy_date is not null");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateEqualTo(Date value) {
            addCriterion("card_buy_date =", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateNotEqualTo(Date value) {
            addCriterion("card_buy_date <>", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateGreaterThan(Date value) {
            addCriterion("card_buy_date >", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("card_buy_date >=", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateLessThan(Date value) {
            addCriterion("card_buy_date <", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateLessThanOrEqualTo(Date value) {
            addCriterion("card_buy_date <=", value, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateIn(List<Date> values) {
            addCriterion("card_buy_date in", values, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateNotIn(List<Date> values) {
            addCriterion("card_buy_date not in", values, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateBetween(Date value1, Date value2) {
            addCriterion("card_buy_date between", value1, value2, "cardBuyDate");
            return (Criteria) this;
        }

        public Criteria andCardBuyDateNotBetween(Date value1, Date value2) {
            addCriterion("card_buy_date not between", value1, value2, "cardBuyDate");
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