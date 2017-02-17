package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.List;

/**
* OrderBillItemCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 17:43:14
*/
public class OrderBillItemCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public OrderBillItemCriteria() {
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

        public Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdIsNull() {
            addCriterion("order_bill_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdIsNotNull() {
            addCriterion("order_bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdEqualTo(String value) {
            addCriterion("order_bill_id =", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdNotEqualTo(String value) {
            addCriterion("order_bill_id <>", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdGreaterThan(String value) {
            addCriterion("order_bill_id >", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_bill_id >=", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdLessThan(String value) {
            addCriterion("order_bill_id <", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdLessThanOrEqualTo(String value) {
            addCriterion("order_bill_id <=", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdLike(String value) {
            addCriterion("order_bill_id like", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdNotLike(String value) {
            addCriterion("order_bill_id not like", value, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdIn(List<String> values) {
            addCriterion("order_bill_id in", values, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdNotIn(List<String> values) {
            addCriterion("order_bill_id not in", values, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdBetween(String value1, String value2) {
            addCriterion("order_bill_id between", value1, value2, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andOrderBillIdNotBetween(String value1, String value2) {
            addCriterion("order_bill_id not between", value1, value2, "orderBillId");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andFinalFeeIsNull() {
            addCriterion("final_fee is null");
            return (Criteria) this;
        }

        public Criteria andFinalFeeIsNotNull() {
            addCriterion("final_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFinalFeeEqualTo(Integer value) {
            addCriterion("final_fee =", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeNotEqualTo(Integer value) {
            addCriterion("final_fee <>", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeGreaterThan(Integer value) {
            addCriterion("final_fee >", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("final_fee >=", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeLessThan(Integer value) {
            addCriterion("final_fee <", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeLessThanOrEqualTo(Integer value) {
            addCriterion("final_fee <=", value, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeIn(List<Integer> values) {
            addCriterion("final_fee in", values, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeNotIn(List<Integer> values) {
            addCriterion("final_fee not in", values, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeBetween(Integer value1, Integer value2) {
            addCriterion("final_fee between", value1, value2, "finalFee");
            return (Criteria) this;
        }

        public Criteria andFinalFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("final_fee not between", value1, value2, "finalFee");
            return (Criteria) this;
        }

        public Criteria andSubAmountIsNull() {
            addCriterion("sub_amount is null");
            return (Criteria) this;
        }

        public Criteria andSubAmountIsNotNull() {
            addCriterion("sub_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSubAmountEqualTo(Integer value) {
            addCriterion("sub_amount =", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountNotEqualTo(Integer value) {
            addCriterion("sub_amount <>", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountGreaterThan(Integer value) {
            addCriterion("sub_amount >", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_amount >=", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountLessThan(Integer value) {
            addCriterion("sub_amount <", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountLessThanOrEqualTo(Integer value) {
            addCriterion("sub_amount <=", value, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountIn(List<Integer> values) {
            addCriterion("sub_amount in", values, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountNotIn(List<Integer> values) {
            addCriterion("sub_amount not in", values, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountBetween(Integer value1, Integer value2) {
            addCriterion("sub_amount between", value1, value2, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_amount not between", value1, value2, "subAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIsNull() {
            addCriterion("subsidy_amount is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIsNotNull() {
            addCriterion("subsidy_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountEqualTo(Integer value) {
            addCriterion("subsidy_amount =", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotEqualTo(Integer value) {
            addCriterion("subsidy_amount <>", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThan(Integer value) {
            addCriterion("subsidy_amount >", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsidy_amount >=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThan(Integer value) {
            addCriterion("subsidy_amount <", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountLessThanOrEqualTo(Integer value) {
            addCriterion("subsidy_amount <=", value, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountIn(List<Integer> values) {
            addCriterion("subsidy_amount in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotIn(List<Integer> values) {
            addCriterion("subsidy_amount not in", values, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountBetween(Integer value1, Integer value2) {
            addCriterion("subsidy_amount between", value1, value2, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("subsidy_amount not between", value1, value2, "subsidyAmount");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIsNull() {
            addCriterion("total_fee is null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIsNotNull() {
            addCriterion("total_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeEqualTo(Integer value) {
            addCriterion("total_fee =", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotEqualTo(Integer value) {
            addCriterion("total_fee <>", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThan(Integer value) {
            addCriterion("total_fee >", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_fee >=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThan(Integer value) {
            addCriterion("total_fee <", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThanOrEqualTo(Integer value) {
            addCriterion("total_fee <=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIn(List<Integer> values) {
            addCriterion("total_fee in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotIn(List<Integer> values) {
            addCriterion("total_fee not in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeBetween(Integer value1, Integer value2) {
            addCriterion("total_fee between", value1, value2, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("total_fee not between", value1, value2, "totalFee");
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