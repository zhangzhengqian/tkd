package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
* CrmTurnCardLogCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-12-13 10:04:58
*/
public class CrmTurnCardLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public CrmTurnCardLogCriteria() {
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

        public Criteria andOldUserIdIsNull() {
            addCriterion("old_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOldUserIdIsNotNull() {
            addCriterion("old_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldUserIdEqualTo(String value) {
            addCriterion("old_user_id =", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotEqualTo(String value) {
            addCriterion("old_user_id <>", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdGreaterThan(String value) {
            addCriterion("old_user_id >", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("old_user_id >=", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdLessThan(String value) {
            addCriterion("old_user_id <", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdLessThanOrEqualTo(String value) {
            addCriterion("old_user_id <=", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdLike(String value) {
            addCriterion("old_user_id like", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotLike(String value) {
            addCriterion("old_user_id not like", value, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdIn(List<String> values) {
            addCriterion("old_user_id in", values, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotIn(List<String> values) {
            addCriterion("old_user_id not in", values, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdBetween(String value1, String value2) {
            addCriterion("old_user_id between", value1, value2, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldUserIdNotBetween(String value1, String value2) {
            addCriterion("old_user_id not between", value1, value2, "oldUserId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdIsNull() {
            addCriterion("old_account_id is null");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdIsNotNull() {
            addCriterion("old_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdEqualTo(String value) {
            addCriterion("old_account_id =", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdNotEqualTo(String value) {
            addCriterion("old_account_id <>", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdGreaterThan(String value) {
            addCriterion("old_account_id >", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("old_account_id >=", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdLessThan(String value) {
            addCriterion("old_account_id <", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdLessThanOrEqualTo(String value) {
            addCriterion("old_account_id <=", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdLike(String value) {
            addCriterion("old_account_id like", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdNotLike(String value) {
            addCriterion("old_account_id not like", value, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdIn(List<String> values) {
            addCriterion("old_account_id in", values, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdNotIn(List<String> values) {
            addCriterion("old_account_id not in", values, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdBetween(String value1, String value2) {
            addCriterion("old_account_id between", value1, value2, "oldAccountId");
            return (Criteria) this;
        }

        public Criteria andOldAccountIdNotBetween(String value1, String value2) {
            addCriterion("old_account_id not between", value1, value2, "oldAccountId");
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

        public Criteria andCardBalanceIsNull() {
            addCriterion("card_balance is null");
            return (Criteria) this;
        }

        public Criteria andCardBalanceIsNotNull() {
            addCriterion("card_balance is not null");
            return (Criteria) this;
        }

        public Criteria andCardBalanceEqualTo(Integer value) {
            addCriterion("card_balance =", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceNotEqualTo(Integer value) {
            addCriterion("card_balance <>", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceGreaterThan(Integer value) {
            addCriterion("card_balance >", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_balance >=", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceLessThan(Integer value) {
            addCriterion("card_balance <", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("card_balance <=", value, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceIn(List<Integer> values) {
            addCriterion("card_balance in", values, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceNotIn(List<Integer> values) {
            addCriterion("card_balance not in", values, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceBetween(Integer value1, Integer value2) {
            addCriterion("card_balance between", value1, value2, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andCardBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("card_balance not between", value1, value2, "cardBalance");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andCardCostIsNull() {
            addCriterion("card_cost is null");
            return (Criteria) this;
        }

        public Criteria andCardCostIsNotNull() {
            addCriterion("card_cost is not null");
            return (Criteria) this;
        }

        public Criteria andCardCostEqualTo(Integer value) {
            addCriterion("card_cost =", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostNotEqualTo(Integer value) {
            addCriterion("card_cost <>", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostGreaterThan(Integer value) {
            addCriterion("card_cost >", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_cost >=", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostLessThan(Integer value) {
            addCriterion("card_cost <", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostLessThanOrEqualTo(Integer value) {
            addCriterion("card_cost <=", value, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostIn(List<Integer> values) {
            addCriterion("card_cost in", values, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostNotIn(List<Integer> values) {
            addCriterion("card_cost not in", values, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostBetween(Integer value1, Integer value2) {
            addCriterion("card_cost between", value1, value2, "cardCost");
            return (Criteria) this;
        }

        public Criteria andCardCostNotBetween(Integer value1, Integer value2) {
            addCriterion("card_cost not between", value1, value2, "cardCost");
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

        public Criteria andNewUserIdIsNull() {
            addCriterion("new_user_id is null");
            return (Criteria) this;
        }

        public Criteria andNewUserIdIsNotNull() {
            addCriterion("new_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewUserIdEqualTo(String value) {
            addCriterion("new_user_id =", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdNotEqualTo(String value) {
            addCriterion("new_user_id <>", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdGreaterThan(String value) {
            addCriterion("new_user_id >", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("new_user_id >=", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdLessThan(String value) {
            addCriterion("new_user_id <", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdLessThanOrEqualTo(String value) {
            addCriterion("new_user_id <=", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdLike(String value) {
            addCriterion("new_user_id like", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdNotLike(String value) {
            addCriterion("new_user_id not like", value, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdIn(List<String> values) {
            addCriterion("new_user_id in", values, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdNotIn(List<String> values) {
            addCriterion("new_user_id not in", values, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdBetween(String value1, String value2) {
            addCriterion("new_user_id between", value1, value2, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewUserIdNotBetween(String value1, String value2) {
            addCriterion("new_user_id not between", value1, value2, "newUserId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdIsNull() {
            addCriterion("new_account_id is null");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdIsNotNull() {
            addCriterion("new_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdEqualTo(String value) {
            addCriterion("new_account_id =", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdNotEqualTo(String value) {
            addCriterion("new_account_id <>", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdGreaterThan(String value) {
            addCriterion("new_account_id >", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("new_account_id >=", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdLessThan(String value) {
            addCriterion("new_account_id <", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdLessThanOrEqualTo(String value) {
            addCriterion("new_account_id <=", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdLike(String value) {
            addCriterion("new_account_id like", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdNotLike(String value) {
            addCriterion("new_account_id not like", value, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdIn(List<String> values) {
            addCriterion("new_account_id in", values, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdNotIn(List<String> values) {
            addCriterion("new_account_id not in", values, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdBetween(String value1, String value2) {
            addCriterion("new_account_id between", value1, value2, "newAccountId");
            return (Criteria) this;
        }

        public Criteria andNewAccountIdNotBetween(String value1, String value2) {
            addCriterion("new_account_id not between", value1, value2, "newAccountId");
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

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "startDate");
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

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
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