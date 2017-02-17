package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* StatiumClassCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-07-28 11:56:38
*/
public class StatiumClassCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public StatiumClassCriteria() {
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

        public Criteria andClassTitleIsNull() {
            addCriterion("class_title is null");
            return (Criteria) this;
        }

        public Criteria andClassTitleIsNotNull() {
            addCriterion("class_title is not null");
            return (Criteria) this;
        }

        public Criteria andClassTitleEqualTo(String value) {
            addCriterion("class_title =", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleNotEqualTo(String value) {
            addCriterion("class_title <>", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleGreaterThan(String value) {
            addCriterion("class_title >", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleGreaterThanOrEqualTo(String value) {
            addCriterion("class_title >=", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleLessThan(String value) {
            addCriterion("class_title <", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleLessThanOrEqualTo(String value) {
            addCriterion("class_title <=", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleLike(String value) {
            addCriterion("class_title like", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleNotLike(String value) {
            addCriterion("class_title not like", value, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleIn(List<String> values) {
            addCriterion("class_title in", values, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleNotIn(List<String> values) {
            addCriterion("class_title not in", values, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleBetween(String value1, String value2) {
            addCriterion("class_title between", value1, value2, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassTitleNotBetween(String value1, String value2) {
            addCriterion("class_title not between", value1, value2, "classTitle");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceIsNull() {
            addCriterion("class_introduce is null");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceIsNotNull() {
            addCriterion("class_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceEqualTo(String value) {
            addCriterion("class_introduce =", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceNotEqualTo(String value) {
            addCriterion("class_introduce <>", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceGreaterThan(String value) {
            addCriterion("class_introduce >", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("class_introduce >=", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceLessThan(String value) {
            addCriterion("class_introduce <", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceLessThanOrEqualTo(String value) {
            addCriterion("class_introduce <=", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceLike(String value) {
            addCriterion("class_introduce like", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceNotLike(String value) {
            addCriterion("class_introduce not like", value, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceIn(List<String> values) {
            addCriterion("class_introduce in", values, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceNotIn(List<String> values) {
            addCriterion("class_introduce not in", values, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceBetween(String value1, String value2) {
            addCriterion("class_introduce between", value1, value2, "classIntroduce");
            return (Criteria) this;
        }

        public Criteria andClassIntroduceNotBetween(String value1, String value2) {
            addCriterion("class_introduce not between", value1, value2, "classIntroduce");
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

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discount_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discount_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(Integer value) {
            addCriterion("discount_price =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(Integer value) {
            addCriterion("discount_price <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(Integer value) {
            addCriterion("discount_price >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_price >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(Integer value) {
            addCriterion("discount_price <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(Integer value) {
            addCriterion("discount_price <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<Integer> values) {
            addCriterion("discount_price in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<Integer> values) {
            addCriterion("discount_price not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(Integer value1, Integer value2) {
            addCriterion("discount_price between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_price not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIsNull() {
            addCriterion("limit_price is null");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIsNotNull() {
            addCriterion("limit_price is not null");
            return (Criteria) this;
        }

        public Criteria andLimitPriceEqualTo(Integer value) {
            addCriterion("limit_price =", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotEqualTo(Integer value) {
            addCriterion("limit_price <>", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceGreaterThan(Integer value) {
            addCriterion("limit_price >", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_price >=", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceLessThan(Integer value) {
            addCriterion("limit_price <", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceLessThanOrEqualTo(Integer value) {
            addCriterion("limit_price <=", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIn(List<Integer> values) {
            addCriterion("limit_price in", values, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotIn(List<Integer> values) {
            addCriterion("limit_price not in", values, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceBetween(Integer value1, Integer value2) {
            addCriterion("limit_price between", value1, value2, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_price not between", value1, value2, "limitPrice");
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

        public Criteria andMinPeopleIsNull() {
            addCriterion("min_people is null");
            return (Criteria) this;
        }

        public Criteria andMinPeopleIsNotNull() {
            addCriterion("min_people is not null");
            return (Criteria) this;
        }

        public Criteria andMinPeopleEqualTo(Integer value) {
            addCriterion("min_people =", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleNotEqualTo(Integer value) {
            addCriterion("min_people <>", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleGreaterThan(Integer value) {
            addCriterion("min_people >", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_people >=", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleLessThan(Integer value) {
            addCriterion("min_people <", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("min_people <=", value, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleIn(List<Integer> values) {
            addCriterion("min_people in", values, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleNotIn(List<Integer> values) {
            addCriterion("min_people not in", values, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleBetween(Integer value1, Integer value2) {
            addCriterion("min_people between", value1, value2, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMinPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("min_people not between", value1, value2, "minPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleIsNull() {
            addCriterion("max_people is null");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleIsNotNull() {
            addCriterion("max_people is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleEqualTo(Integer value) {
            addCriterion("max_people =", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleNotEqualTo(Integer value) {
            addCriterion("max_people <>", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleGreaterThan(Integer value) {
            addCriterion("max_people >", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_people >=", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleLessThan(Integer value) {
            addCriterion("max_people <", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("max_people <=", value, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleIn(List<Integer> values) {
            addCriterion("max_people in", values, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleNotIn(List<Integer> values) {
            addCriterion("max_people not in", values, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleBetween(Integer value1, Integer value2) {
            addCriterion("max_people between", value1, value2, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andMaxPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("max_people not between", value1, value2, "maxPeople");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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