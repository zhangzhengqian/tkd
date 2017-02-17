package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.List;

/**
* OrganizationCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-15 18:07:50
*/
public class OrganizationCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public OrganizationCriteria() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
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

        public Criteria andSeqIsNull() {
            addCriterion("seq is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("seq is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(Integer value) {
            addCriterion("seq =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(Integer value) {
            addCriterion("seq <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(Integer value) {
            addCriterion("seq >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("seq >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(Integer value) {
            addCriterion("seq <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(Integer value) {
            addCriterion("seq <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<Integer> values) {
            addCriterion("seq in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<Integer> values) {
            addCriterion("seq not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(Integer value1, Integer value2) {
            addCriterion("seq between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("seq not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andPartnerIsNull() {
            addCriterion("partner is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIsNotNull() {
            addCriterion("partner is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerEqualTo(String value) {
            addCriterion("partner =", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotEqualTo(String value) {
            addCriterion("partner <>", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerGreaterThan(String value) {
            addCriterion("partner >", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerGreaterThanOrEqualTo(String value) {
            addCriterion("partner >=", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLessThan(String value) {
            addCriterion("partner <", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLessThanOrEqualTo(String value) {
            addCriterion("partner <=", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerLike(String value) {
            addCriterion("partner like", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotLike(String value) {
            addCriterion("partner not like", value, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerIn(List<String> values) {
            addCriterion("partner in", values, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotIn(List<String> values) {
            addCriterion("partner not in", values, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerBetween(String value1, String value2) {
            addCriterion("partner between", value1, value2, "partner");
            return (Criteria) this;
        }

        public Criteria andPartnerNotBetween(String value1, String value2) {
            addCriterion("partner not between", value1, value2, "partner");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(String value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(String value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(String value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(String value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(String value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(String value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLike(String value) {
            addCriterion("seller_id like", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotLike(String value) {
            addCriterion("seller_id not like", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<String> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<String> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(String value1, String value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(String value1, String value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNull() {
            addCriterion("wx_appid is null");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNotNull() {
            addCriterion("wx_appid is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppidEqualTo(String value) {
            addCriterion("wx_appid =", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotEqualTo(String value) {
            addCriterion("wx_appid <>", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThan(String value) {
            addCriterion("wx_appid >", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_appid >=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThan(String value) {
            addCriterion("wx_appid <", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThanOrEqualTo(String value) {
            addCriterion("wx_appid <=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLike(String value) {
            addCriterion("wx_appid like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotLike(String value) {
            addCriterion("wx_appid not like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidIn(List<String> values) {
            addCriterion("wx_appid in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotIn(List<String> values) {
            addCriterion("wx_appid not in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidBetween(String value1, String value2) {
            addCriterion("wx_appid between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotBetween(String value1, String value2) {
            addCriterion("wx_appid not between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxMchIdIsNull() {
            addCriterion("wx_mch_id is null");
            return (Criteria) this;
        }

        public Criteria andWxMchIdIsNotNull() {
            addCriterion("wx_mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxMchIdEqualTo(String value) {
            addCriterion("wx_mch_id =", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdNotEqualTo(String value) {
            addCriterion("wx_mch_id <>", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdGreaterThan(String value) {
            addCriterion("wx_mch_id >", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("wx_mch_id >=", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdLessThan(String value) {
            addCriterion("wx_mch_id <", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdLessThanOrEqualTo(String value) {
            addCriterion("wx_mch_id <=", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdLike(String value) {
            addCriterion("wx_mch_id like", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdNotLike(String value) {
            addCriterion("wx_mch_id not like", value, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdIn(List<String> values) {
            addCriterion("wx_mch_id in", values, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdNotIn(List<String> values) {
            addCriterion("wx_mch_id not in", values, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdBetween(String value1, String value2) {
            addCriterion("wx_mch_id between", value1, value2, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andWxMchIdNotBetween(String value1, String value2) {
            addCriterion("wx_mch_id not between", value1, value2, "wxMchId");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNull() {
            addCriterion("private_key is null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNotNull() {
            addCriterion("private_key is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyEqualTo(String value) {
            addCriterion("private_key =", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotEqualTo(String value) {
            addCriterion("private_key <>", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThan(String value) {
            addCriterion("private_key >", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("private_key >=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThan(String value) {
            addCriterion("private_key <", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThanOrEqualTo(String value) {
            addCriterion("private_key <=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLike(String value) {
            addCriterion("private_key like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotLike(String value) {
            addCriterion("private_key not like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIn(List<String> values) {
            addCriterion("private_key in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotIn(List<String> values) {
            addCriterion("private_key not in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyBetween(String value1, String value2) {
            addCriterion("private_key between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotBetween(String value1, String value2) {
            addCriterion("private_key not between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyIsNull() {
            addCriterion("ali_public_key is null");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyIsNotNull() {
            addCriterion("ali_public_key is not null");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyEqualTo(String value) {
            addCriterion("ali_public_key =", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyNotEqualTo(String value) {
            addCriterion("ali_public_key <>", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyGreaterThan(String value) {
            addCriterion("ali_public_key >", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ali_public_key >=", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyLessThan(String value) {
            addCriterion("ali_public_key <", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyLessThanOrEqualTo(String value) {
            addCriterion("ali_public_key <=", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyLike(String value) {
            addCriterion("ali_public_key like", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyNotLike(String value) {
            addCriterion("ali_public_key not like", value, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyIn(List<String> values) {
            addCriterion("ali_public_key in", values, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyNotIn(List<String> values) {
            addCriterion("ali_public_key not in", values, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyBetween(String value1, String value2) {
            addCriterion("ali_public_key between", value1, value2, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andAliPublicKeyNotBetween(String value1, String value2) {
            addCriterion("ali_public_key not between", value1, value2, "aliPublicKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyIsNull() {
            addCriterion("partner_key is null");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyIsNotNull() {
            addCriterion("partner_key is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyEqualTo(String value) {
            addCriterion("partner_key =", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyNotEqualTo(String value) {
            addCriterion("partner_key <>", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyGreaterThan(String value) {
            addCriterion("partner_key >", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyGreaterThanOrEqualTo(String value) {
            addCriterion("partner_key >=", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyLessThan(String value) {
            addCriterion("partner_key <", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyLessThanOrEqualTo(String value) {
            addCriterion("partner_key <=", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyLike(String value) {
            addCriterion("partner_key like", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyNotLike(String value) {
            addCriterion("partner_key not like", value, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyIn(List<String> values) {
            addCriterion("partner_key in", values, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyNotIn(List<String> values) {
            addCriterion("partner_key not in", values, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyBetween(String value1, String value2) {
            addCriterion("partner_key between", value1, value2, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andPartnerKeyNotBetween(String value1, String value2) {
            addCriterion("partner_key not between", value1, value2, "partnerKey");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNull() {
            addCriterion("app_key is null");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNotNull() {
            addCriterion("app_key is not null");
            return (Criteria) this;
        }

        public Criteria andAppKeyEqualTo(String value) {
            addCriterion("app_key =", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotEqualTo(String value) {
            addCriterion("app_key <>", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThan(String value) {
            addCriterion("app_key >", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThanOrEqualTo(String value) {
            addCriterion("app_key >=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThan(String value) {
            addCriterion("app_key <", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThanOrEqualTo(String value) {
            addCriterion("app_key <=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLike(String value) {
            addCriterion("app_key like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotLike(String value) {
            addCriterion("app_key not like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyIn(List<String> values) {
            addCriterion("app_key in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotIn(List<String> values) {
            addCriterion("app_key not in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyBetween(String value1, String value2) {
            addCriterion("app_key between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotBetween(String value1, String value2) {
            addCriterion("app_key not between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyIsNull() {
            addCriterion("wx_app_key is null");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyIsNotNull() {
            addCriterion("wx_app_key is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyEqualTo(String value) {
            addCriterion("wx_app_key =", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyNotEqualTo(String value) {
            addCriterion("wx_app_key <>", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyGreaterThan(String value) {
            addCriterion("wx_app_key >", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyGreaterThanOrEqualTo(String value) {
            addCriterion("wx_app_key >=", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyLessThan(String value) {
            addCriterion("wx_app_key <", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyLessThanOrEqualTo(String value) {
            addCriterion("wx_app_key <=", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyLike(String value) {
            addCriterion("wx_app_key like", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyNotLike(String value) {
            addCriterion("wx_app_key not like", value, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyIn(List<String> values) {
            addCriterion("wx_app_key in", values, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyNotIn(List<String> values) {
            addCriterion("wx_app_key not in", values, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyBetween(String value1, String value2) {
            addCriterion("wx_app_key between", value1, value2, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxAppKeyNotBetween(String value1, String value2) {
            addCriterion("wx_app_key not between", value1, value2, "wxAppKey");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathIsNull() {
            addCriterion("wx_certLocal_path is null");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathIsNotNull() {
            addCriterion("wx_certLocal_path is not null");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathEqualTo(String value) {
            addCriterion("wx_certLocal_path =", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathNotEqualTo(String value) {
            addCriterion("wx_certLocal_path <>", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathGreaterThan(String value) {
            addCriterion("wx_certLocal_path >", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathGreaterThanOrEqualTo(String value) {
            addCriterion("wx_certLocal_path >=", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathLessThan(String value) {
            addCriterion("wx_certLocal_path <", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathLessThanOrEqualTo(String value) {
            addCriterion("wx_certLocal_path <=", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathLike(String value) {
            addCriterion("wx_certLocal_path like", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathNotLike(String value) {
            addCriterion("wx_certLocal_path not like", value, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathIn(List<String> values) {
            addCriterion("wx_certLocal_path in", values, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathNotIn(List<String> values) {
            addCriterion("wx_certLocal_path not in", values, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathBetween(String value1, String value2) {
            addCriterion("wx_certLocal_path between", value1, value2, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertlocalPathNotBetween(String value1, String value2) {
            addCriterion("wx_certLocal_path not between", value1, value2, "wxCertlocalPath");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordIsNull() {
            addCriterion("wx_cert_password is null");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordIsNotNull() {
            addCriterion("wx_cert_password is not null");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordEqualTo(String value) {
            addCriterion("wx_cert_password =", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordNotEqualTo(String value) {
            addCriterion("wx_cert_password <>", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordGreaterThan(String value) {
            addCriterion("wx_cert_password >", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("wx_cert_password >=", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordLessThan(String value) {
            addCriterion("wx_cert_password <", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordLessThanOrEqualTo(String value) {
            addCriterion("wx_cert_password <=", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordLike(String value) {
            addCriterion("wx_cert_password like", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordNotLike(String value) {
            addCriterion("wx_cert_password not like", value, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordIn(List<String> values) {
            addCriterion("wx_cert_password in", values, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordNotIn(List<String> values) {
            addCriterion("wx_cert_password not in", values, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordBetween(String value1, String value2) {
            addCriterion("wx_cert_password between", value1, value2, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andWxCertPasswordNotBetween(String value1, String value2) {
            addCriterion("wx_cert_password not between", value1, value2, "wxCertPassword");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathIsNull() {
            addCriterion("certLocal_path is null");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathIsNotNull() {
            addCriterion("certLocal_path is not null");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathEqualTo(String value) {
            addCriterion("certLocal_path =", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathNotEqualTo(String value) {
            addCriterion("certLocal_path <>", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathGreaterThan(String value) {
            addCriterion("certLocal_path >", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathGreaterThanOrEqualTo(String value) {
            addCriterion("certLocal_path >=", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathLessThan(String value) {
            addCriterion("certLocal_path <", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathLessThanOrEqualTo(String value) {
            addCriterion("certLocal_path <=", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathLike(String value) {
            addCriterion("certLocal_path like", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathNotLike(String value) {
            addCriterion("certLocal_path not like", value, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathIn(List<String> values) {
            addCriterion("certLocal_path in", values, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathNotIn(List<String> values) {
            addCriterion("certLocal_path not in", values, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathBetween(String value1, String value2) {
            addCriterion("certLocal_path between", value1, value2, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertlocalPathNotBetween(String value1, String value2) {
            addCriterion("certLocal_path not between", value1, value2, "certlocalPath");
            return (Criteria) this;
        }

        public Criteria andCertPasswordIsNull() {
            addCriterion("cert_password is null");
            return (Criteria) this;
        }

        public Criteria andCertPasswordIsNotNull() {
            addCriterion("cert_password is not null");
            return (Criteria) this;
        }

        public Criteria andCertPasswordEqualTo(String value) {
            addCriterion("cert_password =", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordNotEqualTo(String value) {
            addCriterion("cert_password <>", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordGreaterThan(String value) {
            addCriterion("cert_password >", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("cert_password >=", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordLessThan(String value) {
            addCriterion("cert_password <", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordLessThanOrEqualTo(String value) {
            addCriterion("cert_password <=", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordLike(String value) {
            addCriterion("cert_password like", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordNotLike(String value) {
            addCriterion("cert_password not like", value, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordIn(List<String> values) {
            addCriterion("cert_password in", values, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordNotIn(List<String> values) {
            addCriterion("cert_password not in", values, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordBetween(String value1, String value2) {
            addCriterion("cert_password between", value1, value2, "certPassword");
            return (Criteria) this;
        }

        public Criteria andCertPasswordNotBetween(String value1, String value2) {
            addCriterion("cert_password not between", value1, value2, "certPassword");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretIsNull() {
            addCriterion("wx_app_secret is null");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretIsNotNull() {
            addCriterion("wx_app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretEqualTo(String value) {
            addCriterion("wx_app_secret =", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretNotEqualTo(String value) {
            addCriterion("wx_app_secret <>", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretGreaterThan(String value) {
            addCriterion("wx_app_secret >", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("wx_app_secret >=", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretLessThan(String value) {
            addCriterion("wx_app_secret <", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretLessThanOrEqualTo(String value) {
            addCriterion("wx_app_secret <=", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretLike(String value) {
            addCriterion("wx_app_secret like", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretNotLike(String value) {
            addCriterion("wx_app_secret not like", value, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretIn(List<String> values) {
            addCriterion("wx_app_secret in", values, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretNotIn(List<String> values) {
            addCriterion("wx_app_secret not in", values, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretBetween(String value1, String value2) {
            addCriterion("wx_app_secret between", value1, value2, "wxAppSecret");
            return (Criteria) this;
        }

        public Criteria andWxAppSecretNotBetween(String value1, String value2) {
            addCriterion("wx_app_secret not between", value1, value2, "wxAppSecret");
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