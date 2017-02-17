package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* StatiumInfosCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-10-14 11:31:29
*/
public class StatiumInfosCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public StatiumInfosCriteria() {
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andLngEqualTo(Double value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(Double value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(Double value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(Double value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(Double value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(Double value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<Double> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<Double> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(Double value1, Double value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(Double value1, Double value2) {
            addCriterion("lng not between", value1, value2, "lng");
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

        public Criteria andLatEqualTo(Double value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(Double value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(Double value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(Double value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(Double value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(Double value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<Double> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<Double> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(Double value1, Double value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(Double value1, Double value2) {
            addCriterion("lat not between", value1, value2, "lat");
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

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
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

        public Criteria andSalecountIsNull() {
            addCriterion("salecount is null");
            return (Criteria) this;
        }

        public Criteria andSalecountIsNotNull() {
            addCriterion("salecount is not null");
            return (Criteria) this;
        }

        public Criteria andSalecountEqualTo(Integer value) {
            addCriterion("salecount =", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountNotEqualTo(Integer value) {
            addCriterion("salecount <>", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountGreaterThan(Integer value) {
            addCriterion("salecount >", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("salecount >=", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountLessThan(Integer value) {
            addCriterion("salecount <", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountLessThanOrEqualTo(Integer value) {
            addCriterion("salecount <=", value, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountIn(List<Integer> values) {
            addCriterion("salecount in", values, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountNotIn(List<Integer> values) {
            addCriterion("salecount not in", values, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountBetween(Integer value1, Integer value2) {
            addCriterion("salecount between", value1, value2, "salecount");
            return (Criteria) this;
        }

        public Criteria andSalecountNotBetween(Integer value1, Integer value2) {
            addCriterion("salecount not between", value1, value2, "salecount");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNull() {
            addCriterion("starttime is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeIsNotNull() {
            addCriterion("starttime is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeEqualTo(String value) {
            addCriterion("starttime =", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotEqualTo(String value) {
            addCriterion("starttime <>", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThan(String value) {
            addCriterion("starttime >", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("starttime >=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThan(String value) {
            addCriterion("starttime <", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLessThanOrEqualTo(String value) {
            addCriterion("starttime <=", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeLike(String value) {
            addCriterion("starttime like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotLike(String value) {
            addCriterion("starttime not like", value, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeIn(List<String> values) {
            addCriterion("starttime in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotIn(List<String> values) {
            addCriterion("starttime not in", values, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeBetween(String value1, String value2) {
            addCriterion("starttime between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andStarttimeNotBetween(String value1, String value2) {
            addCriterion("starttime not between", value1, value2, "starttime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(String value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(String value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(String value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(String value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(String value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(String value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLike(String value) {
            addCriterion("endtime like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotLike(String value) {
            addCriterion("endtime not like", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<String> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<String> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(String value1, String value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(String value1, String value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
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

        public Criteria andRecommendIsNull() {
            addCriterion("recommend is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNotNull() {
            addCriterion("recommend is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendEqualTo(Integer value) {
            addCriterion("recommend =", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotEqualTo(Integer value) {
            addCriterion("recommend <>", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThan(Integer value) {
            addCriterion("recommend >", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommend >=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThan(Integer value) {
            addCriterion("recommend <", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThanOrEqualTo(Integer value) {
            addCriterion("recommend <=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendIn(List<Integer> values) {
            addCriterion("recommend in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotIn(List<Integer> values) {
            addCriterion("recommend not in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendBetween(Integer value1, Integer value2) {
            addCriterion("recommend between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotBetween(Integer value1, Integer value2) {
            addCriterion("recommend not between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andServiceTelIsNull() {
            addCriterion("service_tel is null");
            return (Criteria) this;
        }

        public Criteria andServiceTelIsNotNull() {
            addCriterion("service_tel is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTelEqualTo(String value) {
            addCriterion("service_tel =", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelNotEqualTo(String value) {
            addCriterion("service_tel <>", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelGreaterThan(String value) {
            addCriterion("service_tel >", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelGreaterThanOrEqualTo(String value) {
            addCriterion("service_tel >=", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelLessThan(String value) {
            addCriterion("service_tel <", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelLessThanOrEqualTo(String value) {
            addCriterion("service_tel <=", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelLike(String value) {
            addCriterion("service_tel like", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelNotLike(String value) {
            addCriterion("service_tel not like", value, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelIn(List<String> values) {
            addCriterion("service_tel in", values, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelNotIn(List<String> values) {
            addCriterion("service_tel not in", values, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelBetween(String value1, String value2) {
            addCriterion("service_tel between", value1, value2, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andServiceTelNotBetween(String value1, String value2) {
            addCriterion("service_tel not between", value1, value2, "serviceTel");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("bank_account_name =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("bank_account_name <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("bank_account_name >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_name >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("bank_account_name <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_name <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("bank_account_name like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("bank_account_name not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("bank_account_name in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("bank_account_name not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("bank_account_name between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("bank_account_name not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNull() {
            addCriterion("bank_account_no is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNotNull() {
            addCriterion("bank_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoEqualTo(String value) {
            addCriterion("bank_account_no =", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotEqualTo(String value) {
            addCriterion("bank_account_no <>", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThan(String value) {
            addCriterion("bank_account_no >", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_no >=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThan(String value) {
            addCriterion("bank_account_no <", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThanOrEqualTo(String value) {
            addCriterion("bank_account_no <=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLike(String value) {
            addCriterion("bank_account_no like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotLike(String value) {
            addCriterion("bank_account_no not like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIn(List<String> values) {
            addCriterion("bank_account_no in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotIn(List<String> values) {
            addCriterion("bank_account_no not in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoBetween(String value1, String value2) {
            addCriterion("bank_account_no between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotBetween(String value1, String value2) {
            addCriterion("bank_account_no not between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameIsNull() {
            addCriterion("bank_account_branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameIsNotNull() {
            addCriterion("bank_account_branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameEqualTo(String value) {
            addCriterion("bank_account_branch_name =", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameNotEqualTo(String value) {
            addCriterion("bank_account_branch_name <>", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameGreaterThan(String value) {
            addCriterion("bank_account_branch_name >", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_branch_name >=", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameLessThan(String value) {
            addCriterion("bank_account_branch_name <", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_branch_name <=", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameLike(String value) {
            addCriterion("bank_account_branch_name like", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameNotLike(String value) {
            addCriterion("bank_account_branch_name not like", value, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameIn(List<String> values) {
            addCriterion("bank_account_branch_name in", values, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameNotIn(List<String> values) {
            addCriterion("bank_account_branch_name not in", values, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameBetween(String value1, String value2) {
            addCriterion("bank_account_branch_name between", value1, value2, "bankAccountBranchName");
            return (Criteria) this;
        }

        public Criteria andBankAccountBranchNameNotBetween(String value1, String value2) {
            addCriterion("bank_account_branch_name not between", value1, value2, "bankAccountBranchName");
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

        public Criteria andQrCodeIsNull() {
            addCriterion("qr_code is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("qr_code is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("qr_code =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("qr_code <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("qr_code >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("qr_code <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("qr_code <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("qr_code like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("qr_code not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("qr_code in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("qr_code not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("qr_code between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("qr_code not between", value1, value2, "qrCode");
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