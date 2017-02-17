package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* CoachCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-09-05 14:49:02
*/
public class CoachCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public CoachCriteria() {
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

        public Criteria andDgIdIsNull() {
            addCriterion("dg_id is null");
            return (Criteria) this;
        }

        public Criteria andDgIdIsNotNull() {
            addCriterion("dg_id is not null");
            return (Criteria) this;
        }

        public Criteria andDgIdEqualTo(Integer value) {
            addCriterion("dg_id =", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdNotEqualTo(Integer value) {
            addCriterion("dg_id <>", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdGreaterThan(Integer value) {
            addCriterion("dg_id >", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dg_id >=", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdLessThan(Integer value) {
            addCriterion("dg_id <", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdLessThanOrEqualTo(Integer value) {
            addCriterion("dg_id <=", value, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdIn(List<Integer> values) {
            addCriterion("dg_id in", values, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdNotIn(List<Integer> values) {
            addCriterion("dg_id not in", values, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdBetween(Integer value1, Integer value2) {
            addCriterion("dg_id between", value1, value2, "dgId");
            return (Criteria) this;
        }

        public Criteria andDgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dg_id not between", value1, value2, "dgId");
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

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNickIsNull() {
            addCriterion("nick is null");
            return (Criteria) this;
        }

        public Criteria andNickIsNotNull() {
            addCriterion("nick is not null");
            return (Criteria) this;
        }

        public Criteria andNickEqualTo(String value) {
            addCriterion("nick =", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotEqualTo(String value) {
            addCriterion("nick <>", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThan(String value) {
            addCriterion("nick >", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickGreaterThanOrEqualTo(String value) {
            addCriterion("nick >=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThan(String value) {
            addCriterion("nick <", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLessThanOrEqualTo(String value) {
            addCriterion("nick <=", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickLike(String value) {
            addCriterion("nick like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotLike(String value) {
            addCriterion("nick not like", value, "nick");
            return (Criteria) this;
        }

        public Criteria andNickIn(List<String> values) {
            addCriterion("nick in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotIn(List<String> values) {
            addCriterion("nick not in", values, "nick");
            return (Criteria) this;
        }

        public Criteria andNickBetween(String value1, String value2) {
            addCriterion("nick between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andNickNotBetween(String value1, String value2) {
            addCriterion("nick not between", value1, value2, "nick");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPhotosIsNull() {
            addCriterion("photos is null");
            return (Criteria) this;
        }

        public Criteria andPhotosIsNotNull() {
            addCriterion("photos is not null");
            return (Criteria) this;
        }

        public Criteria andPhotosEqualTo(String value) {
            addCriterion("photos =", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotEqualTo(String value) {
            addCriterion("photos <>", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosGreaterThan(String value) {
            addCriterion("photos >", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosGreaterThanOrEqualTo(String value) {
            addCriterion("photos >=", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLessThan(String value) {
            addCriterion("photos <", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLessThanOrEqualTo(String value) {
            addCriterion("photos <=", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosLike(String value) {
            addCriterion("photos like", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotLike(String value) {
            addCriterion("photos not like", value, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosIn(List<String> values) {
            addCriterion("photos in", values, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotIn(List<String> values) {
            addCriterion("photos not in", values, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosBetween(String value1, String value2) {
            addCriterion("photos between", value1, value2, "photos");
            return (Criteria) this;
        }

        public Criteria andPhotosNotBetween(String value1, String value2) {
            addCriterion("photos not between", value1, value2, "photos");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andDanlevelIsNull() {
            addCriterion("danlevel is null");
            return (Criteria) this;
        }

        public Criteria andDanlevelIsNotNull() {
            addCriterion("danlevel is not null");
            return (Criteria) this;
        }

        public Criteria andDanlevelEqualTo(String value) {
            addCriterion("danlevel =", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelNotEqualTo(String value) {
            addCriterion("danlevel <>", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelGreaterThan(String value) {
            addCriterion("danlevel >", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelGreaterThanOrEqualTo(String value) {
            addCriterion("danlevel >=", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelLessThan(String value) {
            addCriterion("danlevel <", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelLessThanOrEqualTo(String value) {
            addCriterion("danlevel <=", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelLike(String value) {
            addCriterion("danlevel like", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelNotLike(String value) {
            addCriterion("danlevel not like", value, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelIn(List<String> values) {
            addCriterion("danlevel in", values, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelNotIn(List<String> values) {
            addCriterion("danlevel not in", values, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelBetween(String value1, String value2) {
            addCriterion("danlevel between", value1, value2, "danlevel");
            return (Criteria) this;
        }

        public Criteria andDanlevelNotBetween(String value1, String value2) {
            addCriterion("danlevel not between", value1, value2, "danlevel");
            return (Criteria) this;
        }

        public Criteria andTeachyearIsNull() {
            addCriterion("teachyear is null");
            return (Criteria) this;
        }

        public Criteria andTeachyearIsNotNull() {
            addCriterion("teachyear is not null");
            return (Criteria) this;
        }

        public Criteria andTeachyearEqualTo(Integer value) {
            addCriterion("teachyear =", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearNotEqualTo(Integer value) {
            addCriterion("teachyear <>", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearGreaterThan(Integer value) {
            addCriterion("teachyear >", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearGreaterThanOrEqualTo(Integer value) {
            addCriterion("teachyear >=", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearLessThan(Integer value) {
            addCriterion("teachyear <", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearLessThanOrEqualTo(Integer value) {
            addCriterion("teachyear <=", value, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearIn(List<Integer> values) {
            addCriterion("teachyear in", values, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearNotIn(List<Integer> values) {
            addCriterion("teachyear not in", values, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearBetween(Integer value1, Integer value2) {
            addCriterion("teachyear between", value1, value2, "teachyear");
            return (Criteria) this;
        }

        public Criteria andTeachyearNotBetween(Integer value1, Integer value2) {
            addCriterion("teachyear not between", value1, value2, "teachyear");
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

        public Criteria andIscardIsNull() {
            addCriterion("iscard is null");
            return (Criteria) this;
        }

        public Criteria andIscardIsNotNull() {
            addCriterion("iscard is not null");
            return (Criteria) this;
        }

        public Criteria andIscardEqualTo(String value) {
            addCriterion("iscard =", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardNotEqualTo(String value) {
            addCriterion("iscard <>", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardGreaterThan(String value) {
            addCriterion("iscard >", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardGreaterThanOrEqualTo(String value) {
            addCriterion("iscard >=", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardLessThan(String value) {
            addCriterion("iscard <", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardLessThanOrEqualTo(String value) {
            addCriterion("iscard <=", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardLike(String value) {
            addCriterion("iscard like", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardNotLike(String value) {
            addCriterion("iscard not like", value, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardIn(List<String> values) {
            addCriterion("iscard in", values, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardNotIn(List<String> values) {
            addCriterion("iscard not in", values, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardBetween(String value1, String value2) {
            addCriterion("iscard between", value1, value2, "iscard");
            return (Criteria) this;
        }

        public Criteria andIscardNotBetween(String value1, String value2) {
            addCriterion("iscard not between", value1, value2, "iscard");
            return (Criteria) this;
        }

        public Criteria andIsdanIsNull() {
            addCriterion("isdan is null");
            return (Criteria) this;
        }

        public Criteria andIsdanIsNotNull() {
            addCriterion("isdan is not null");
            return (Criteria) this;
        }

        public Criteria andIsdanEqualTo(String value) {
            addCriterion("isdan =", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanNotEqualTo(String value) {
            addCriterion("isdan <>", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanGreaterThan(String value) {
            addCriterion("isdan >", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanGreaterThanOrEqualTo(String value) {
            addCriterion("isdan >=", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanLessThan(String value) {
            addCriterion("isdan <", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanLessThanOrEqualTo(String value) {
            addCriterion("isdan <=", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanLike(String value) {
            addCriterion("isdan like", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanNotLike(String value) {
            addCriterion("isdan not like", value, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanIn(List<String> values) {
            addCriterion("isdan in", values, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanNotIn(List<String> values) {
            addCriterion("isdan not in", values, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanBetween(String value1, String value2) {
            addCriterion("isdan between", value1, value2, "isdan");
            return (Criteria) this;
        }

        public Criteria andIsdanNotBetween(String value1, String value2) {
            addCriterion("isdan not between", value1, value2, "isdan");
            return (Criteria) this;
        }

        public Criteria andHelfexperIsNull() {
            addCriterion("helfexper is null");
            return (Criteria) this;
        }

        public Criteria andHelfexperIsNotNull() {
            addCriterion("helfexper is not null");
            return (Criteria) this;
        }

        public Criteria andHelfexperEqualTo(String value) {
            addCriterion("helfexper =", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperNotEqualTo(String value) {
            addCriterion("helfexper <>", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperGreaterThan(String value) {
            addCriterion("helfexper >", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperGreaterThanOrEqualTo(String value) {
            addCriterion("helfexper >=", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperLessThan(String value) {
            addCriterion("helfexper <", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperLessThanOrEqualTo(String value) {
            addCriterion("helfexper <=", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperLike(String value) {
            addCriterion("helfexper like", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperNotLike(String value) {
            addCriterion("helfexper not like", value, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperIn(List<String> values) {
            addCriterion("helfexper in", values, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperNotIn(List<String> values) {
            addCriterion("helfexper not in", values, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperBetween(String value1, String value2) {
            addCriterion("helfexper between", value1, value2, "helfexper");
            return (Criteria) this;
        }

        public Criteria andHelfexperNotBetween(String value1, String value2) {
            addCriterion("helfexper not between", value1, value2, "helfexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperIsNull() {
            addCriterion("tearchexper is null");
            return (Criteria) this;
        }

        public Criteria andTearchexperIsNotNull() {
            addCriterion("tearchexper is not null");
            return (Criteria) this;
        }

        public Criteria andTearchexperEqualTo(String value) {
            addCriterion("tearchexper =", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperNotEqualTo(String value) {
            addCriterion("tearchexper <>", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperGreaterThan(String value) {
            addCriterion("tearchexper >", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperGreaterThanOrEqualTo(String value) {
            addCriterion("tearchexper >=", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperLessThan(String value) {
            addCriterion("tearchexper <", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperLessThanOrEqualTo(String value) {
            addCriterion("tearchexper <=", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperLike(String value) {
            addCriterion("tearchexper like", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperNotLike(String value) {
            addCriterion("tearchexper not like", value, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperIn(List<String> values) {
            addCriterion("tearchexper in", values, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperNotIn(List<String> values) {
            addCriterion("tearchexper not in", values, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperBetween(String value1, String value2) {
            addCriterion("tearchexper between", value1, value2, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andTearchexperNotBetween(String value1, String value2) {
            addCriterion("tearchexper not between", value1, value2, "tearchexper");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andStudentsIsNull() {
            addCriterion("students is null");
            return (Criteria) this;
        }

        public Criteria andStudentsIsNotNull() {
            addCriterion("students is not null");
            return (Criteria) this;
        }

        public Criteria andStudentsEqualTo(Integer value) {
            addCriterion("students =", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsNotEqualTo(Integer value) {
            addCriterion("students <>", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsGreaterThan(Integer value) {
            addCriterion("students >", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsGreaterThanOrEqualTo(Integer value) {
            addCriterion("students >=", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsLessThan(Integer value) {
            addCriterion("students <", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsLessThanOrEqualTo(Integer value) {
            addCriterion("students <=", value, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsIn(List<Integer> values) {
            addCriterion("students in", values, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsNotIn(List<Integer> values) {
            addCriterion("students not in", values, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsBetween(Integer value1, Integer value2) {
            addCriterion("students between", value1, value2, "students");
            return (Criteria) this;
        }

        public Criteria andStudentsNotBetween(Integer value1, Integer value2) {
            addCriterion("students not between", value1, value2, "students");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Float value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Float value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Float value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Float value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Float value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Float value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Float> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Float> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Float value1, Float value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Float value1, Float value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
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

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
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

        public Criteria andCoachNoIsNull() {
            addCriterion("coach_no is null");
            return (Criteria) this;
        }

        public Criteria andCoachNoIsNotNull() {
            addCriterion("coach_no is not null");
            return (Criteria) this;
        }

        public Criteria andCoachNoEqualTo(String value) {
            addCriterion("coach_no =", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoNotEqualTo(String value) {
            addCriterion("coach_no <>", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoGreaterThan(String value) {
            addCriterion("coach_no >", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoGreaterThanOrEqualTo(String value) {
            addCriterion("coach_no >=", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoLessThan(String value) {
            addCriterion("coach_no <", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoLessThanOrEqualTo(String value) {
            addCriterion("coach_no <=", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoLike(String value) {
            addCriterion("coach_no like", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoNotLike(String value) {
            addCriterion("coach_no not like", value, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoIn(List<String> values) {
            addCriterion("coach_no in", values, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoNotIn(List<String> values) {
            addCriterion("coach_no not in", values, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoBetween(String value1, String value2) {
            addCriterion("coach_no between", value1, value2, "coachNo");
            return (Criteria) this;
        }

        public Criteria andCoachNoNotBetween(String value1, String value2) {
            addCriterion("coach_no not between", value1, value2, "coachNo");
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

        public Criteria andActiveStateIsNull() {
            addCriterion("active_state is null");
            return (Criteria) this;
        }

        public Criteria andActiveStateIsNotNull() {
            addCriterion("active_state is not null");
            return (Criteria) this;
        }

        public Criteria andActiveStateEqualTo(Integer value) {
            addCriterion("active_state =", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotEqualTo(Integer value) {
            addCriterion("active_state <>", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThan(Integer value) {
            addCriterion("active_state >", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_state >=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThan(Integer value) {
            addCriterion("active_state <", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateLessThanOrEqualTo(Integer value) {
            addCriterion("active_state <=", value, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateIn(List<Integer> values) {
            addCriterion("active_state in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotIn(List<Integer> values) {
            addCriterion("active_state not in", values, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateBetween(Integer value1, Integer value2) {
            addCriterion("active_state between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andActiveStateNotBetween(Integer value1, Integer value2) {
            addCriterion("active_state not between", value1, value2, "activeState");
            return (Criteria) this;
        }

        public Criteria andCertListIsNull() {
            addCriterion("cert_list is null");
            return (Criteria) this;
        }

        public Criteria andCertListIsNotNull() {
            addCriterion("cert_list is not null");
            return (Criteria) this;
        }

        public Criteria andCertListEqualTo(String value) {
            addCriterion("cert_list =", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListNotEqualTo(String value) {
            addCriterion("cert_list <>", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListGreaterThan(String value) {
            addCriterion("cert_list >", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListGreaterThanOrEqualTo(String value) {
            addCriterion("cert_list >=", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListLessThan(String value) {
            addCriterion("cert_list <", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListLessThanOrEqualTo(String value) {
            addCriterion("cert_list <=", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListLike(String value) {
            addCriterion("cert_list like", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListNotLike(String value) {
            addCriterion("cert_list not like", value, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListIn(List<String> values) {
            addCriterion("cert_list in", values, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListNotIn(List<String> values) {
            addCriterion("cert_list not in", values, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListBetween(String value1, String value2) {
            addCriterion("cert_list between", value1, value2, "certList");
            return (Criteria) this;
        }

        public Criteria andCertListNotBetween(String value1, String value2) {
            addCriterion("cert_list not between", value1, value2, "certList");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNull() {
            addCriterion("areacode is null");
            return (Criteria) this;
        }

        public Criteria andAreacodeIsNotNull() {
            addCriterion("areacode is not null");
            return (Criteria) this;
        }

        public Criteria andAreacodeEqualTo(String value) {
            addCriterion("areacode =", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotEqualTo(String value) {
            addCriterion("areacode <>", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThan(String value) {
            addCriterion("areacode >", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeGreaterThanOrEqualTo(String value) {
            addCriterion("areacode >=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThan(String value) {
            addCriterion("areacode <", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLessThanOrEqualTo(String value) {
            addCriterion("areacode <=", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeLike(String value) {
            addCriterion("areacode like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotLike(String value) {
            addCriterion("areacode not like", value, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeIn(List<String> values) {
            addCriterion("areacode in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotIn(List<String> values) {
            addCriterion("areacode not in", values, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeBetween(String value1, String value2) {
            addCriterion("areacode between", value1, value2, "areacode");
            return (Criteria) this;
        }

        public Criteria andAreacodeNotBetween(String value1, String value2) {
            addCriterion("areacode not between", value1, value2, "areacode");
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