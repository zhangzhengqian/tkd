package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.List;

/**
* DicCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class DicCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public DicCriteria() {
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

        public Criteria andDicIdIsNull() {
            addCriterion("DIC_ID is null");
            return (Criteria) this;
        }

        public Criteria andDicIdIsNotNull() {
            addCriterion("DIC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDicIdEqualTo(String value) {
            addCriterion("DIC_ID =", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdNotEqualTo(String value) {
            addCriterion("DIC_ID <>", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdGreaterThan(String value) {
            addCriterion("DIC_ID >", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_ID >=", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdLessThan(String value) {
            addCriterion("DIC_ID <", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdLessThanOrEqualTo(String value) {
            addCriterion("DIC_ID <=", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdLike(String value) {
            addCriterion("DIC_ID like", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdNotLike(String value) {
            addCriterion("DIC_ID not like", value, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdIn(List<String> values) {
            addCriterion("DIC_ID in", values, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdNotIn(List<String> values) {
            addCriterion("DIC_ID not in", values, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdBetween(String value1, String value2) {
            addCriterion("DIC_ID between", value1, value2, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicIdNotBetween(String value1, String value2) {
            addCriterion("DIC_ID not between", value1, value2, "dicId");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNull() {
            addCriterion("DIC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDicCodeIsNotNull() {
            addCriterion("DIC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDicCodeEqualTo(String value) {
            addCriterion("DIC_CODE =", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotEqualTo(String value) {
            addCriterion("DIC_CODE <>", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThan(String value) {
            addCriterion("DIC_CODE >", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_CODE >=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThan(String value) {
            addCriterion("DIC_CODE <", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLessThanOrEqualTo(String value) {
            addCriterion("DIC_CODE <=", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeLike(String value) {
            addCriterion("DIC_CODE like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotLike(String value) {
            addCriterion("DIC_CODE not like", value, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeIn(List<String> values) {
            addCriterion("DIC_CODE in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotIn(List<String> values) {
            addCriterion("DIC_CODE not in", values, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeBetween(String value1, String value2) {
            addCriterion("DIC_CODE between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicCodeNotBetween(String value1, String value2) {
            addCriterion("DIC_CODE not between", value1, value2, "dicCode");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNull() {
            addCriterion("DIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNotNull() {
            addCriterion("DIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDicNameEqualTo(String value) {
            addCriterion("DIC_NAME =", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotEqualTo(String value) {
            addCriterion("DIC_NAME <>", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThan(String value) {
            addCriterion("DIC_NAME >", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_NAME >=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThan(String value) {
            addCriterion("DIC_NAME <", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThanOrEqualTo(String value) {
            addCriterion("DIC_NAME <=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLike(String value) {
            addCriterion("DIC_NAME like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotLike(String value) {
            addCriterion("DIC_NAME not like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameIn(List<String> values) {
            addCriterion("DIC_NAME in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotIn(List<String> values) {
            addCriterion("DIC_NAME not in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameBetween(String value1, String value2) {
            addCriterion("DIC_NAME between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotBetween(String value1, String value2) {
            addCriterion("DIC_NAME not between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andSeqNumIsNull() {
            addCriterion("SEQ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSeqNumIsNotNull() {
            addCriterion("SEQ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNumEqualTo(Long value) {
            addCriterion("SEQ_NUM =", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumNotEqualTo(Long value) {
            addCriterion("SEQ_NUM <>", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumGreaterThan(Long value) {
            addCriterion("SEQ_NUM >", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumGreaterThanOrEqualTo(Long value) {
            addCriterion("SEQ_NUM >=", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumLessThan(Long value) {
            addCriterion("SEQ_NUM <", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumLessThanOrEqualTo(Long value) {
            addCriterion("SEQ_NUM <=", value, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumIn(List<Long> values) {
            addCriterion("SEQ_NUM in", values, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumNotIn(List<Long> values) {
            addCriterion("SEQ_NUM not in", values, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumBetween(Long value1, Long value2) {
            addCriterion("SEQ_NUM between", value1, value2, "seqNum");
            return (Criteria) this;
        }

        public Criteria andSeqNumNotBetween(Long value1, Long value2) {
            addCriterion("SEQ_NUM not between", value1, value2, "seqNum");
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