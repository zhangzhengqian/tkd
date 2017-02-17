package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.List;

/**
* PmwInfoimgCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-23 18:50:05
*/
public class PmwInfoimgCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public PmwInfoimgCriteria() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSiteidIsNull() {
            addCriterion("siteid is null");
            return (Criteria) this;
        }

        public Criteria andSiteidIsNotNull() {
            addCriterion("siteid is not null");
            return (Criteria) this;
        }

        public Criteria andSiteidEqualTo(Integer value) {
            addCriterion("siteid =", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotEqualTo(Integer value) {
            addCriterion("siteid <>", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidGreaterThan(Integer value) {
            addCriterion("siteid >", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidGreaterThanOrEqualTo(Integer value) {
            addCriterion("siteid >=", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidLessThan(Integer value) {
            addCriterion("siteid <", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidLessThanOrEqualTo(Integer value) {
            addCriterion("siteid <=", value, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidIn(List<Integer> values) {
            addCriterion("siteid in", values, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotIn(List<Integer> values) {
            addCriterion("siteid not in", values, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidBetween(Integer value1, Integer value2) {
            addCriterion("siteid between", value1, value2, "siteid");
            return (Criteria) this;
        }

        public Criteria andSiteidNotBetween(Integer value1, Integer value2) {
            addCriterion("siteid not between", value1, value2, "siteid");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("classid is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("classid is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("classid =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("classid <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("classid >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classid >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("classid <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("classid <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("classid in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("classid not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("classid between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("classid not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentstrIsNull() {
            addCriterion("parentstr is null");
            return (Criteria) this;
        }

        public Criteria andParentstrIsNotNull() {
            addCriterion("parentstr is not null");
            return (Criteria) this;
        }

        public Criteria andParentstrEqualTo(String value) {
            addCriterion("parentstr =", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrNotEqualTo(String value) {
            addCriterion("parentstr <>", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrGreaterThan(String value) {
            addCriterion("parentstr >", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrGreaterThanOrEqualTo(String value) {
            addCriterion("parentstr >=", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrLessThan(String value) {
            addCriterion("parentstr <", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrLessThanOrEqualTo(String value) {
            addCriterion("parentstr <=", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrLike(String value) {
            addCriterion("parentstr like", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrNotLike(String value) {
            addCriterion("parentstr not like", value, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrIn(List<String> values) {
            addCriterion("parentstr in", values, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrNotIn(List<String> values) {
            addCriterion("parentstr not in", values, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrBetween(String value1, String value2) {
            addCriterion("parentstr between", value1, value2, "parentstr");
            return (Criteria) this;
        }

        public Criteria andParentstrNotBetween(String value1, String value2) {
            addCriterion("parentstr not between", value1, value2, "parentstr");
            return (Criteria) this;
        }

        public Criteria andMainidIsNull() {
            addCriterion("mainid is null");
            return (Criteria) this;
        }

        public Criteria andMainidIsNotNull() {
            addCriterion("mainid is not null");
            return (Criteria) this;
        }

        public Criteria andMainidEqualTo(Integer value) {
            addCriterion("mainid =", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidNotEqualTo(Integer value) {
            addCriterion("mainid <>", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidGreaterThan(Integer value) {
            addCriterion("mainid >", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mainid >=", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidLessThan(Integer value) {
            addCriterion("mainid <", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidLessThanOrEqualTo(Integer value) {
            addCriterion("mainid <=", value, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidIn(List<Integer> values) {
            addCriterion("mainid in", values, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidNotIn(List<Integer> values) {
            addCriterion("mainid not in", values, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidBetween(Integer value1, Integer value2) {
            addCriterion("mainid between", value1, value2, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainidNotBetween(Integer value1, Integer value2) {
            addCriterion("mainid not between", value1, value2, "mainid");
            return (Criteria) this;
        }

        public Criteria andMainpidIsNull() {
            addCriterion("mainpid is null");
            return (Criteria) this;
        }

        public Criteria andMainpidIsNotNull() {
            addCriterion("mainpid is not null");
            return (Criteria) this;
        }

        public Criteria andMainpidEqualTo(Integer value) {
            addCriterion("mainpid =", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidNotEqualTo(Integer value) {
            addCriterion("mainpid <>", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidGreaterThan(Integer value) {
            addCriterion("mainpid >", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mainpid >=", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidLessThan(Integer value) {
            addCriterion("mainpid <", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidLessThanOrEqualTo(Integer value) {
            addCriterion("mainpid <=", value, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidIn(List<Integer> values) {
            addCriterion("mainpid in", values, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidNotIn(List<Integer> values) {
            addCriterion("mainpid not in", values, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidBetween(Integer value1, Integer value2) {
            addCriterion("mainpid between", value1, value2, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpidNotBetween(Integer value1, Integer value2) {
            addCriterion("mainpid not between", value1, value2, "mainpid");
            return (Criteria) this;
        }

        public Criteria andMainpstrIsNull() {
            addCriterion("mainpstr is null");
            return (Criteria) this;
        }

        public Criteria andMainpstrIsNotNull() {
            addCriterion("mainpstr is not null");
            return (Criteria) this;
        }

        public Criteria andMainpstrEqualTo(String value) {
            addCriterion("mainpstr =", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrNotEqualTo(String value) {
            addCriterion("mainpstr <>", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrGreaterThan(String value) {
            addCriterion("mainpstr >", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrGreaterThanOrEqualTo(String value) {
            addCriterion("mainpstr >=", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrLessThan(String value) {
            addCriterion("mainpstr <", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrLessThanOrEqualTo(String value) {
            addCriterion("mainpstr <=", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrLike(String value) {
            addCriterion("mainpstr like", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrNotLike(String value) {
            addCriterion("mainpstr not like", value, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrIn(List<String> values) {
            addCriterion("mainpstr in", values, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrNotIn(List<String> values) {
            addCriterion("mainpstr not in", values, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrBetween(String value1, String value2) {
            addCriterion("mainpstr between", value1, value2, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andMainpstrNotBetween(String value1, String value2) {
            addCriterion("mainpstr not between", value1, value2, "mainpstr");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andColorvalIsNull() {
            addCriterion("colorval is null");
            return (Criteria) this;
        }

        public Criteria andColorvalIsNotNull() {
            addCriterion("colorval is not null");
            return (Criteria) this;
        }

        public Criteria andColorvalEqualTo(String value) {
            addCriterion("colorval =", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalNotEqualTo(String value) {
            addCriterion("colorval <>", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalGreaterThan(String value) {
            addCriterion("colorval >", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalGreaterThanOrEqualTo(String value) {
            addCriterion("colorval >=", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalLessThan(String value) {
            addCriterion("colorval <", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalLessThanOrEqualTo(String value) {
            addCriterion("colorval <=", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalLike(String value) {
            addCriterion("colorval like", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalNotLike(String value) {
            addCriterion("colorval not like", value, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalIn(List<String> values) {
            addCriterion("colorval in", values, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalNotIn(List<String> values) {
            addCriterion("colorval not in", values, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalBetween(String value1, String value2) {
            addCriterion("colorval between", value1, value2, "colorval");
            return (Criteria) this;
        }

        public Criteria andColorvalNotBetween(String value1, String value2) {
            addCriterion("colorval not between", value1, value2, "colorval");
            return (Criteria) this;
        }

        public Criteria andBoldvalIsNull() {
            addCriterion("boldval is null");
            return (Criteria) this;
        }

        public Criteria andBoldvalIsNotNull() {
            addCriterion("boldval is not null");
            return (Criteria) this;
        }

        public Criteria andBoldvalEqualTo(String value) {
            addCriterion("boldval =", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalNotEqualTo(String value) {
            addCriterion("boldval <>", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalGreaterThan(String value) {
            addCriterion("boldval >", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalGreaterThanOrEqualTo(String value) {
            addCriterion("boldval >=", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalLessThan(String value) {
            addCriterion("boldval <", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalLessThanOrEqualTo(String value) {
            addCriterion("boldval <=", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalLike(String value) {
            addCriterion("boldval like", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalNotLike(String value) {
            addCriterion("boldval not like", value, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalIn(List<String> values) {
            addCriterion("boldval in", values, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalNotIn(List<String> values) {
            addCriterion("boldval not in", values, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalBetween(String value1, String value2) {
            addCriterion("boldval between", value1, value2, "boldval");
            return (Criteria) this;
        }

        public Criteria andBoldvalNotBetween(String value1, String value2) {
            addCriterion("boldval not between", value1, value2, "boldval");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andLinkurlIsNull() {
            addCriterion("linkurl is null");
            return (Criteria) this;
        }

        public Criteria andLinkurlIsNotNull() {
            addCriterion("linkurl is not null");
            return (Criteria) this;
        }

        public Criteria andLinkurlEqualTo(String value) {
            addCriterion("linkurl =", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlNotEqualTo(String value) {
            addCriterion("linkurl <>", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlGreaterThan(String value) {
            addCriterion("linkurl >", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlGreaterThanOrEqualTo(String value) {
            addCriterion("linkurl >=", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlLessThan(String value) {
            addCriterion("linkurl <", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlLessThanOrEqualTo(String value) {
            addCriterion("linkurl <=", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlLike(String value) {
            addCriterion("linkurl like", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlNotLike(String value) {
            addCriterion("linkurl not like", value, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlIn(List<String> values) {
            addCriterion("linkurl in", values, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlNotIn(List<String> values) {
            addCriterion("linkurl not in", values, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlBetween(String value1, String value2) {
            addCriterion("linkurl between", value1, value2, "linkurl");
            return (Criteria) this;
        }

        public Criteria andLinkurlNotBetween(String value1, String value2) {
            addCriterion("linkurl not between", value1, value2, "linkurl");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andPicurlIsNull() {
            addCriterion("picurl is null");
            return (Criteria) this;
        }

        public Criteria andPicurlIsNotNull() {
            addCriterion("picurl is not null");
            return (Criteria) this;
        }

        public Criteria andPicurlEqualTo(String value) {
            addCriterion("picurl =", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotEqualTo(String value) {
            addCriterion("picurl <>", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThan(String value) {
            addCriterion("picurl >", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlGreaterThanOrEqualTo(String value) {
            addCriterion("picurl >=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThan(String value) {
            addCriterion("picurl <", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLessThanOrEqualTo(String value) {
            addCriterion("picurl <=", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlLike(String value) {
            addCriterion("picurl like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotLike(String value) {
            addCriterion("picurl not like", value, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlIn(List<String> values) {
            addCriterion("picurl in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotIn(List<String> values) {
            addCriterion("picurl not in", values, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlBetween(String value1, String value2) {
            addCriterion("picurl between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicurlNotBetween(String value1, String value2) {
            addCriterion("picurl not between", value1, value2, "picurl");
            return (Criteria) this;
        }

        public Criteria andPicarrIsNull() {
            addCriterion("picarr is null");
            return (Criteria) this;
        }

        public Criteria andPicarrIsNotNull() {
            addCriterion("picarr is not null");
            return (Criteria) this;
        }

        public Criteria andPicarrEqualTo(String value) {
            addCriterion("picarr =", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrNotEqualTo(String value) {
            addCriterion("picarr <>", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrGreaterThan(String value) {
            addCriterion("picarr >", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrGreaterThanOrEqualTo(String value) {
            addCriterion("picarr >=", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrLessThan(String value) {
            addCriterion("picarr <", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrLessThanOrEqualTo(String value) {
            addCriterion("picarr <=", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrLike(String value) {
            addCriterion("picarr like", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrNotLike(String value) {
            addCriterion("picarr not like", value, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrIn(List<String> values) {
            addCriterion("picarr in", values, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrNotIn(List<String> values) {
            addCriterion("picarr not in", values, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrBetween(String value1, String value2) {
            addCriterion("picarr between", value1, value2, "picarr");
            return (Criteria) this;
        }

        public Criteria andPicarrNotBetween(String value1, String value2) {
            addCriterion("picarr not between", value1, value2, "picarr");
            return (Criteria) this;
        }

        public Criteria andHitsIsNull() {
            addCriterion("hits is null");
            return (Criteria) this;
        }

        public Criteria andHitsIsNotNull() {
            addCriterion("hits is not null");
            return (Criteria) this;
        }

        public Criteria andHitsEqualTo(Integer value) {
            addCriterion("hits =", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotEqualTo(Integer value) {
            addCriterion("hits <>", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThan(Integer value) {
            addCriterion("hits >", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hits >=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThan(Integer value) {
            addCriterion("hits <", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThanOrEqualTo(Integer value) {
            addCriterion("hits <=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsIn(List<Integer> values) {
            addCriterion("hits in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotIn(List<Integer> values) {
            addCriterion("hits not in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsBetween(Integer value1, Integer value2) {
            addCriterion("hits between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotBetween(Integer value1, Integer value2) {
            addCriterion("hits not between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andPosttimeIsNull() {
            addCriterion("posttime is null");
            return (Criteria) this;
        }

        public Criteria andPosttimeIsNotNull() {
            addCriterion("posttime is not null");
            return (Criteria) this;
        }

        public Criteria andPosttimeEqualTo(Integer value) {
            addCriterion("posttime =", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotEqualTo(Integer value) {
            addCriterion("posttime <>", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeGreaterThan(Integer value) {
            addCriterion("posttime >", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("posttime >=", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeLessThan(Integer value) {
            addCriterion("posttime <", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeLessThanOrEqualTo(Integer value) {
            addCriterion("posttime <=", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeIn(List<Integer> values) {
            addCriterion("posttime in", values, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotIn(List<Integer> values) {
            addCriterion("posttime not in", values, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeBetween(Integer value1, Integer value2) {
            addCriterion("posttime between", value1, value2, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("posttime not between", value1, value2, "posttime");
            return (Criteria) this;
        }

        public Criteria andCheckinfoIsNull() {
            addCriterion("checkinfo is null");
            return (Criteria) this;
        }

        public Criteria andCheckinfoIsNotNull() {
            addCriterion("checkinfo is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinfoEqualTo(String value) {
            addCriterion("checkinfo =", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoNotEqualTo(String value) {
            addCriterion("checkinfo <>", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoGreaterThan(String value) {
            addCriterion("checkinfo >", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoGreaterThanOrEqualTo(String value) {
            addCriterion("checkinfo >=", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoLessThan(String value) {
            addCriterion("checkinfo <", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoLessThanOrEqualTo(String value) {
            addCriterion("checkinfo <=", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoLike(String value) {
            addCriterion("checkinfo like", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoNotLike(String value) {
            addCriterion("checkinfo not like", value, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoIn(List<String> values) {
            addCriterion("checkinfo in", values, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoNotIn(List<String> values) {
            addCriterion("checkinfo not in", values, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoBetween(String value1, String value2) {
            addCriterion("checkinfo between", value1, value2, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andCheckinfoNotBetween(String value1, String value2) {
            addCriterion("checkinfo not between", value1, value2, "checkinfo");
            return (Criteria) this;
        }

        public Criteria andDelstateIsNull() {
            addCriterion("delstate is null");
            return (Criteria) this;
        }

        public Criteria andDelstateIsNotNull() {
            addCriterion("delstate is not null");
            return (Criteria) this;
        }

        public Criteria andDelstateEqualTo(String value) {
            addCriterion("delstate =", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateNotEqualTo(String value) {
            addCriterion("delstate <>", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateGreaterThan(String value) {
            addCriterion("delstate >", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateGreaterThanOrEqualTo(String value) {
            addCriterion("delstate >=", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateLessThan(String value) {
            addCriterion("delstate <", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateLessThanOrEqualTo(String value) {
            addCriterion("delstate <=", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateLike(String value) {
            addCriterion("delstate like", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateNotLike(String value) {
            addCriterion("delstate not like", value, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateIn(List<String> values) {
            addCriterion("delstate in", values, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateNotIn(List<String> values) {
            addCriterion("delstate not in", values, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateBetween(String value1, String value2) {
            addCriterion("delstate between", value1, value2, "delstate");
            return (Criteria) this;
        }

        public Criteria andDelstateNotBetween(String value1, String value2) {
            addCriterion("delstate not between", value1, value2, "delstate");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNull() {
            addCriterion("deltime is null");
            return (Criteria) this;
        }

        public Criteria andDeltimeIsNotNull() {
            addCriterion("deltime is not null");
            return (Criteria) this;
        }

        public Criteria andDeltimeEqualTo(Integer value) {
            addCriterion("deltime =", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotEqualTo(Integer value) {
            addCriterion("deltime <>", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThan(Integer value) {
            addCriterion("deltime >", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deltime >=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThan(Integer value) {
            addCriterion("deltime <", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeLessThanOrEqualTo(Integer value) {
            addCriterion("deltime <=", value, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeIn(List<Integer> values) {
            addCriterion("deltime in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotIn(List<Integer> values) {
            addCriterion("deltime not in", values, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeBetween(Integer value1, Integer value2) {
            addCriterion("deltime between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andDeltimeNotBetween(Integer value1, Integer value2) {
            addCriterion("deltime not between", value1, value2, "deltime");
            return (Criteria) this;
        }

        public Criteria andB1IsNull() {
            addCriterion("b1 is null");
            return (Criteria) this;
        }

        public Criteria andB1IsNotNull() {
            addCriterion("b1 is not null");
            return (Criteria) this;
        }

        public Criteria andB1EqualTo(String value) {
            addCriterion("b1 =", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1NotEqualTo(String value) {
            addCriterion("b1 <>", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1GreaterThan(String value) {
            addCriterion("b1 >", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1GreaterThanOrEqualTo(String value) {
            addCriterion("b1 >=", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1LessThan(String value) {
            addCriterion("b1 <", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1LessThanOrEqualTo(String value) {
            addCriterion("b1 <=", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1Like(String value) {
            addCriterion("b1 like", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1NotLike(String value) {
            addCriterion("b1 not like", value, "b1");
            return (Criteria) this;
        }

        public Criteria andB1In(List<String> values) {
            addCriterion("b1 in", values, "b1");
            return (Criteria) this;
        }

        public Criteria andB1NotIn(List<String> values) {
            addCriterion("b1 not in", values, "b1");
            return (Criteria) this;
        }

        public Criteria andB1Between(String value1, String value2) {
            addCriterion("b1 between", value1, value2, "b1");
            return (Criteria) this;
        }

        public Criteria andB1NotBetween(String value1, String value2) {
            addCriterion("b1 not between", value1, value2, "b1");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(String value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(String value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(String value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(String value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(String value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(String value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLike(String value) {
            addCriterion("lng like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotLike(String value) {
            addCriterion("lng not like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<String> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<String> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(String value1, String value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(String value1, String value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andShengIsNull() {
            addCriterion("sheng is null");
            return (Criteria) this;
        }

        public Criteria andShengIsNotNull() {
            addCriterion("sheng is not null");
            return (Criteria) this;
        }

        public Criteria andShengEqualTo(String value) {
            addCriterion("sheng =", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengNotEqualTo(String value) {
            addCriterion("sheng <>", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengGreaterThan(String value) {
            addCriterion("sheng >", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengGreaterThanOrEqualTo(String value) {
            addCriterion("sheng >=", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengLessThan(String value) {
            addCriterion("sheng <", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengLessThanOrEqualTo(String value) {
            addCriterion("sheng <=", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengLike(String value) {
            addCriterion("sheng like", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengNotLike(String value) {
            addCriterion("sheng not like", value, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengIn(List<String> values) {
            addCriterion("sheng in", values, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengNotIn(List<String> values) {
            addCriterion("sheng not in", values, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengBetween(String value1, String value2) {
            addCriterion("sheng between", value1, value2, "sheng");
            return (Criteria) this;
        }

        public Criteria andShengNotBetween(String value1, String value2) {
            addCriterion("sheng not between", value1, value2, "sheng");
            return (Criteria) this;
        }

        public Criteria andDqIsNull() {
            addCriterion("dq is null");
            return (Criteria) this;
        }

        public Criteria andDqIsNotNull() {
            addCriterion("dq is not null");
            return (Criteria) this;
        }

        public Criteria andDqEqualTo(String value) {
            addCriterion("dq =", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqNotEqualTo(String value) {
            addCriterion("dq <>", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqGreaterThan(String value) {
            addCriterion("dq >", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqGreaterThanOrEqualTo(String value) {
            addCriterion("dq >=", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqLessThan(String value) {
            addCriterion("dq <", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqLessThanOrEqualTo(String value) {
            addCriterion("dq <=", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqLike(String value) {
            addCriterion("dq like", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqNotLike(String value) {
            addCriterion("dq not like", value, "dq");
            return (Criteria) this;
        }

        public Criteria andDqIn(List<String> values) {
            addCriterion("dq in", values, "dq");
            return (Criteria) this;
        }

        public Criteria andDqNotIn(List<String> values) {
            addCriterion("dq not in", values, "dq");
            return (Criteria) this;
        }

        public Criteria andDqBetween(String value1, String value2) {
            addCriterion("dq between", value1, value2, "dq");
            return (Criteria) this;
        }

        public Criteria andDqNotBetween(String value1, String value2) {
            addCriterion("dq not between", value1, value2, "dq");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andDizhiIsNull() {
            addCriterion("dizhi is null");
            return (Criteria) this;
        }

        public Criteria andDizhiIsNotNull() {
            addCriterion("dizhi is not null");
            return (Criteria) this;
        }

        public Criteria andDizhiEqualTo(String value) {
            addCriterion("dizhi =", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotEqualTo(String value) {
            addCriterion("dizhi <>", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiGreaterThan(String value) {
            addCriterion("dizhi >", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiGreaterThanOrEqualTo(String value) {
            addCriterion("dizhi >=", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLessThan(String value) {
            addCriterion("dizhi <", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLessThanOrEqualTo(String value) {
            addCriterion("dizhi <=", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiLike(String value) {
            addCriterion("dizhi like", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotLike(String value) {
            addCriterion("dizhi not like", value, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiIn(List<String> values) {
            addCriterion("dizhi in", values, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotIn(List<String> values) {
            addCriterion("dizhi not in", values, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiBetween(String value1, String value2) {
            addCriterion("dizhi between", value1, value2, "dizhi");
            return (Criteria) this;
        }

        public Criteria andDizhiNotBetween(String value1, String value2) {
            addCriterion("dizhi not between", value1, value2, "dizhi");
            return (Criteria) this;
        }

        public Criteria andShiIsNull() {
            addCriterion("shi is null");
            return (Criteria) this;
        }

        public Criteria andShiIsNotNull() {
            addCriterion("shi is not null");
            return (Criteria) this;
        }

        public Criteria andShiEqualTo(String value) {
            addCriterion("shi =", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiNotEqualTo(String value) {
            addCriterion("shi <>", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiGreaterThan(String value) {
            addCriterion("shi >", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiGreaterThanOrEqualTo(String value) {
            addCriterion("shi >=", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiLessThan(String value) {
            addCriterion("shi <", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiLessThanOrEqualTo(String value) {
            addCriterion("shi <=", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiLike(String value) {
            addCriterion("shi like", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiNotLike(String value) {
            addCriterion("shi not like", value, "shi");
            return (Criteria) this;
        }

        public Criteria andShiIn(List<String> values) {
            addCriterion("shi in", values, "shi");
            return (Criteria) this;
        }

        public Criteria andShiNotIn(List<String> values) {
            addCriterion("shi not in", values, "shi");
            return (Criteria) this;
        }

        public Criteria andShiBetween(String value1, String value2) {
            addCriterion("shi between", value1, value2, "shi");
            return (Criteria) this;
        }

        public Criteria andShiNotBetween(String value1, String value2) {
            addCriterion("shi not between", value1, value2, "shi");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andHydjIsNull() {
            addCriterion("hydj is null");
            return (Criteria) this;
        }

        public Criteria andHydjIsNotNull() {
            addCriterion("hydj is not null");
            return (Criteria) this;
        }

        public Criteria andHydjEqualTo(String value) {
            addCriterion("hydj =", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjNotEqualTo(String value) {
            addCriterion("hydj <>", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjGreaterThan(String value) {
            addCriterion("hydj >", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjGreaterThanOrEqualTo(String value) {
            addCriterion("hydj >=", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjLessThan(String value) {
            addCriterion("hydj <", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjLessThanOrEqualTo(String value) {
            addCriterion("hydj <=", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjLike(String value) {
            addCriterion("hydj like", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjNotLike(String value) {
            addCriterion("hydj not like", value, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjIn(List<String> values) {
            addCriterion("hydj in", values, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjNotIn(List<String> values) {
            addCriterion("hydj not in", values, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjBetween(String value1, String value2) {
            addCriterion("hydj between", value1, value2, "hydj");
            return (Criteria) this;
        }

        public Criteria andHydjNotBetween(String value1, String value2) {
            addCriterion("hydj not between", value1, value2, "hydj");
            return (Criteria) this;
        }

        public Criteria andB2IsNull() {
            addCriterion("b2 is null");
            return (Criteria) this;
        }

        public Criteria andB2IsNotNull() {
            addCriterion("b2 is not null");
            return (Criteria) this;
        }

        public Criteria andB2EqualTo(String value) {
            addCriterion("b2 =", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2NotEqualTo(String value) {
            addCriterion("b2 <>", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2GreaterThan(String value) {
            addCriterion("b2 >", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2GreaterThanOrEqualTo(String value) {
            addCriterion("b2 >=", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2LessThan(String value) {
            addCriterion("b2 <", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2LessThanOrEqualTo(String value) {
            addCriterion("b2 <=", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2Like(String value) {
            addCriterion("b2 like", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2NotLike(String value) {
            addCriterion("b2 not like", value, "b2");
            return (Criteria) this;
        }

        public Criteria andB2In(List<String> values) {
            addCriterion("b2 in", values, "b2");
            return (Criteria) this;
        }

        public Criteria andB2NotIn(List<String> values) {
            addCriterion("b2 not in", values, "b2");
            return (Criteria) this;
        }

        public Criteria andB2Between(String value1, String value2) {
            addCriterion("b2 between", value1, value2, "b2");
            return (Criteria) this;
        }

        public Criteria andB2NotBetween(String value1, String value2) {
            addCriterion("b2 not between", value1, value2, "b2");
            return (Criteria) this;
        }

        public Criteria andHyIdIsNull() {
            addCriterion("hy_id is null");
            return (Criteria) this;
        }

        public Criteria andHyIdIsNotNull() {
            addCriterion("hy_id is not null");
            return (Criteria) this;
        }

        public Criteria andHyIdEqualTo(Integer value) {
            addCriterion("hy_id =", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotEqualTo(Integer value) {
            addCriterion("hy_id <>", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdGreaterThan(Integer value) {
            addCriterion("hy_id >", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hy_id >=", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdLessThan(Integer value) {
            addCriterion("hy_id <", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdLessThanOrEqualTo(Integer value) {
            addCriterion("hy_id <=", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdIn(List<Integer> values) {
            addCriterion("hy_id in", values, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotIn(List<Integer> values) {
            addCriterion("hy_id not in", values, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdBetween(Integer value1, Integer value2) {
            addCriterion("hy_id between", value1, value2, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hy_id not between", value1, value2, "hyId");
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