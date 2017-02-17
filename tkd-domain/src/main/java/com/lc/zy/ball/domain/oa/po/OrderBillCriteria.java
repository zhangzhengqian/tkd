package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* OrderBillCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-08-31 14:12:50
*/
public class OrderBillCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public OrderBillCriteria() {
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

        public Criteria andBillIdIsNull() {
            addCriterion("bill_id is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(String value) {
            addCriterion("bill_id =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(String value) {
            addCriterion("bill_id <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(String value) {
            addCriterion("bill_id >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(String value) {
            addCriterion("bill_id >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(String value) {
            addCriterion("bill_id <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(String value) {
            addCriterion("bill_id <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLike(String value) {
            addCriterion("bill_id like", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotLike(String value) {
            addCriterion("bill_id not like", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<String> values) {
            addCriterion("bill_id in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<String> values) {
            addCriterion("bill_id not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(String value1, String value2) {
            addCriterion("bill_id between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(String value1, String value2) {
            addCriterion("bill_id not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andStatiumNameIsNull() {
            addCriterion("statium_name is null");
            return (Criteria) this;
        }

        public Criteria andStatiumNameIsNotNull() {
            addCriterion("statium_name is not null");
            return (Criteria) this;
        }

        public Criteria andStatiumNameEqualTo(String value) {
            addCriterion("statium_name =", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameNotEqualTo(String value) {
            addCriterion("statium_name <>", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameGreaterThan(String value) {
            addCriterion("statium_name >", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameGreaterThanOrEqualTo(String value) {
            addCriterion("statium_name >=", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameLessThan(String value) {
            addCriterion("statium_name <", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameLessThanOrEqualTo(String value) {
            addCriterion("statium_name <=", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameLike(String value) {
            addCriterion("statium_name like", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameNotLike(String value) {
            addCriterion("statium_name not like", value, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameIn(List<String> values) {
            addCriterion("statium_name in", values, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameNotIn(List<String> values) {
            addCriterion("statium_name not in", values, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameBetween(String value1, String value2) {
            addCriterion("statium_name between", value1, value2, "statiumName");
            return (Criteria) this;
        }

        public Criteria andStatiumNameNotBetween(String value1, String value2) {
            addCriterion("statium_name not between", value1, value2, "statiumName");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("begin_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Integer value) {
            addCriterion("begin_date =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Integer value) {
            addCriterion("begin_date <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Integer value) {
            addCriterion("begin_date >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("begin_date >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Integer value) {
            addCriterion("begin_date <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Integer value) {
            addCriterion("begin_date <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Integer> values) {
            addCriterion("begin_date in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Integer> values) {
            addCriterion("begin_date not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Integer value1, Integer value2) {
            addCriterion("begin_date between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Integer value1, Integer value2) {
            addCriterion("begin_date not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Integer value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Integer value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Integer value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Integer value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Integer value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Integer> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Integer> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Integer value1, Integer value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Integer value1, Integer value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Integer value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Integer value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Integer value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Integer value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Integer value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Integer> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Integer> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Integer value1, Integer value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNull() {
            addCriterion("subsidy is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyIsNotNull() {
            addCriterion("subsidy is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyEqualTo(Integer value) {
            addCriterion("subsidy =", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotEqualTo(Integer value) {
            addCriterion("subsidy <>", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThan(Integer value) {
            addCriterion("subsidy >", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsidy >=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThan(Integer value) {
            addCriterion("subsidy <", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyLessThanOrEqualTo(Integer value) {
            addCriterion("subsidy <=", value, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyIn(List<Integer> values) {
            addCriterion("subsidy in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotIn(List<Integer> values) {
            addCriterion("subsidy not in", values, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyBetween(Integer value1, Integer value2) {
            addCriterion("subsidy between", value1, value2, "subsidy");
            return (Criteria) this;
        }

        public Criteria andSubsidyNotBetween(Integer value1, Integer value2) {
            addCriterion("subsidy not between", value1, value2, "subsidy");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
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

        public Criteria andStatiumIdEqualTo(String value) {
            addCriterion("statium_id =", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotEqualTo(String value) {
            addCriterion("statium_id <>", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdGreaterThan(String value) {
            addCriterion("statium_id >", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdGreaterThanOrEqualTo(String value) {
            addCriterion("statium_id >=", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdLessThan(String value) {
            addCriterion("statium_id <", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdLessThanOrEqualTo(String value) {
            addCriterion("statium_id <=", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdLike(String value) {
            addCriterion("statium_id like", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotLike(String value) {
            addCriterion("statium_id not like", value, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdIn(List<String> values) {
            addCriterion("statium_id in", values, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotIn(List<String> values) {
            addCriterion("statium_id not in", values, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdBetween(String value1, String value2) {
            addCriterion("statium_id between", value1, value2, "statiumId");
            return (Criteria) this;
        }

        public Criteria andStatiumIdNotBetween(String value1, String value2) {
            addCriterion("statium_id not between", value1, value2, "statiumId");
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

        public Criteria andSportTypeIsNull() {
            addCriterion("sport_type is null");
            return (Criteria) this;
        }

        public Criteria andSportTypeIsNotNull() {
            addCriterion("sport_type is not null");
            return (Criteria) this;
        }

        public Criteria andSportTypeEqualTo(String value) {
            addCriterion("sport_type =", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotEqualTo(String value) {
            addCriterion("sport_type <>", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThan(String value) {
            addCriterion("sport_type >", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sport_type >=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThan(String value) {
            addCriterion("sport_type <", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLessThanOrEqualTo(String value) {
            addCriterion("sport_type <=", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeLike(String value) {
            addCriterion("sport_type like", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotLike(String value) {
            addCriterion("sport_type not like", value, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeIn(List<String> values) {
            addCriterion("sport_type in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotIn(List<String> values) {
            addCriterion("sport_type not in", values, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeBetween(String value1, String value2) {
            addCriterion("sport_type between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andSportTypeNotBetween(String value1, String value2) {
            addCriterion("sport_type not between", value1, value2, "sportType");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("bank_Account_Name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("bank_Account_Name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("bank_Account_Name =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("bank_Account_Name <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("bank_Account_Name >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_Account_Name >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("bank_Account_Name <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("bank_Account_Name <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("bank_Account_Name like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("bank_Account_Name not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("bank_Account_Name in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("bank_Account_Name not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("bank_Account_Name between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("bank_Account_Name not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoIsNull() {
            addCriterion("bankAccountNo is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoIsNotNull() {
            addCriterion("bankAccountNo is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoEqualTo(String value) {
            addCriterion("bankAccountNo =", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoNotEqualTo(String value) {
            addCriterion("bankAccountNo <>", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoGreaterThan(String value) {
            addCriterion("bankAccountNo >", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoGreaterThanOrEqualTo(String value) {
            addCriterion("bankAccountNo >=", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoLessThan(String value) {
            addCriterion("bankAccountNo <", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoLessThanOrEqualTo(String value) {
            addCriterion("bankAccountNo <=", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoLike(String value) {
            addCriterion("bankAccountNo like", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoNotLike(String value) {
            addCriterion("bankAccountNo not like", value, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoIn(List<String> values) {
            addCriterion("bankAccountNo in", values, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoNotIn(List<String> values) {
            addCriterion("bankAccountNo not in", values, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoBetween(String value1, String value2) {
            addCriterion("bankAccountNo between", value1, value2, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andBankaccountnoNotBetween(String value1, String value2) {
            addCriterion("bankAccountNo not between", value1, value2, "bankaccountno");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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