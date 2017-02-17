package com.lc.zy.ball.domain.oa.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* PmwMemberCriteria 条件查询类.
* 
* @author sl
* @version v1.0
* @copy pet
* @date 2016-04-23 19:01:14
*/
public class PmwMemberCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public PmwMemberCriteria() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
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

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andCnnameIsNull() {
            addCriterion("cnname is null");
            return (Criteria) this;
        }

        public Criteria andCnnameIsNotNull() {
            addCriterion("cnname is not null");
            return (Criteria) this;
        }

        public Criteria andCnnameEqualTo(String value) {
            addCriterion("cnname =", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameNotEqualTo(String value) {
            addCriterion("cnname <>", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameGreaterThan(String value) {
            addCriterion("cnname >", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameGreaterThanOrEqualTo(String value) {
            addCriterion("cnname >=", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameLessThan(String value) {
            addCriterion("cnname <", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameLessThanOrEqualTo(String value) {
            addCriterion("cnname <=", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameLike(String value) {
            addCriterion("cnname like", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameNotLike(String value) {
            addCriterion("cnname not like", value, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameIn(List<String> values) {
            addCriterion("cnname in", values, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameNotIn(List<String> values) {
            addCriterion("cnname not in", values, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameBetween(String value1, String value2) {
            addCriterion("cnname between", value1, value2, "cnname");
            return (Criteria) this;
        }

        public Criteria andCnnameNotBetween(String value1, String value2) {
            addCriterion("cnname not between", value1, value2, "cnname");
            return (Criteria) this;
        }

        public Criteria andAdd1IsNull() {
            addCriterion("add1 is null");
            return (Criteria) this;
        }

        public Criteria andAdd1IsNotNull() {
            addCriterion("add1 is not null");
            return (Criteria) this;
        }

        public Criteria andAdd1EqualTo(String value) {
            addCriterion("add1 =", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1NotEqualTo(String value) {
            addCriterion("add1 <>", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1GreaterThan(String value) {
            addCriterion("add1 >", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1GreaterThanOrEqualTo(String value) {
            addCriterion("add1 >=", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1LessThan(String value) {
            addCriterion("add1 <", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1LessThanOrEqualTo(String value) {
            addCriterion("add1 <=", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1Like(String value) {
            addCriterion("add1 like", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1NotLike(String value) {
            addCriterion("add1 not like", value, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1In(List<String> values) {
            addCriterion("add1 in", values, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1NotIn(List<String> values) {
            addCriterion("add1 not in", values, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1Between(String value1, String value2) {
            addCriterion("add1 between", value1, value2, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd1NotBetween(String value1, String value2) {
            addCriterion("add1 not between", value1, value2, "add1");
            return (Criteria) this;
        }

        public Criteria andAdd2IsNull() {
            addCriterion("add2 is null");
            return (Criteria) this;
        }

        public Criteria andAdd2IsNotNull() {
            addCriterion("add2 is not null");
            return (Criteria) this;
        }

        public Criteria andAdd2EqualTo(String value) {
            addCriterion("add2 =", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2NotEqualTo(String value) {
            addCriterion("add2 <>", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2GreaterThan(String value) {
            addCriterion("add2 >", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2GreaterThanOrEqualTo(String value) {
            addCriterion("add2 >=", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2LessThan(String value) {
            addCriterion("add2 <", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2LessThanOrEqualTo(String value) {
            addCriterion("add2 <=", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2Like(String value) {
            addCriterion("add2 like", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2NotLike(String value) {
            addCriterion("add2 not like", value, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2In(List<String> values) {
            addCriterion("add2 in", values, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2NotIn(List<String> values) {
            addCriterion("add2 not in", values, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2Between(String value1, String value2) {
            addCriterion("add2 between", value1, value2, "add2");
            return (Criteria) this;
        }

        public Criteria andAdd2NotBetween(String value1, String value2) {
            addCriterion("add2 not between", value1, value2, "add2");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
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

        public Criteria andSexEqualTo(Byte value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Byte value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Byte value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Byte value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Byte value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Byte value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Byte> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Byte> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Byte value1, Byte value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Byte value1, Byte value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthtypeIsNull() {
            addCriterion("birthtype is null");
            return (Criteria) this;
        }

        public Criteria andBirthtypeIsNotNull() {
            addCriterion("birthtype is not null");
            return (Criteria) this;
        }

        public Criteria andBirthtypeEqualTo(Byte value) {
            addCriterion("birthtype =", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeNotEqualTo(Byte value) {
            addCriterion("birthtype <>", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeGreaterThan(Byte value) {
            addCriterion("birthtype >", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("birthtype >=", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeLessThan(Byte value) {
            addCriterion("birthtype <", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeLessThanOrEqualTo(Byte value) {
            addCriterion("birthtype <=", value, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeIn(List<Byte> values) {
            addCriterion("birthtype in", values, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeNotIn(List<Byte> values) {
            addCriterion("birthtype not in", values, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeBetween(Byte value1, Byte value2) {
            addCriterion("birthtype between", value1, value2, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("birthtype not between", value1, value2, "birthtype");
            return (Criteria) this;
        }

        public Criteria andBirthYearIsNull() {
            addCriterion("birth_year is null");
            return (Criteria) this;
        }

        public Criteria andBirthYearIsNotNull() {
            addCriterion("birth_year is not null");
            return (Criteria) this;
        }

        public Criteria andBirthYearEqualTo(String value) {
            addCriterion("birth_year =", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearNotEqualTo(String value) {
            addCriterion("birth_year <>", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearGreaterThan(String value) {
            addCriterion("birth_year >", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearGreaterThanOrEqualTo(String value) {
            addCriterion("birth_year >=", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearLessThan(String value) {
            addCriterion("birth_year <", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearLessThanOrEqualTo(String value) {
            addCriterion("birth_year <=", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearLike(String value) {
            addCriterion("birth_year like", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearNotLike(String value) {
            addCriterion("birth_year not like", value, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearIn(List<String> values) {
            addCriterion("birth_year in", values, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearNotIn(List<String> values) {
            addCriterion("birth_year not in", values, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearBetween(String value1, String value2) {
            addCriterion("birth_year between", value1, value2, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthYearNotBetween(String value1, String value2) {
            addCriterion("birth_year not between", value1, value2, "birthYear");
            return (Criteria) this;
        }

        public Criteria andBirthMonthIsNull() {
            addCriterion("birth_month is null");
            return (Criteria) this;
        }

        public Criteria andBirthMonthIsNotNull() {
            addCriterion("birth_month is not null");
            return (Criteria) this;
        }

        public Criteria andBirthMonthEqualTo(String value) {
            addCriterion("birth_month =", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthNotEqualTo(String value) {
            addCriterion("birth_month <>", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthGreaterThan(String value) {
            addCriterion("birth_month >", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthGreaterThanOrEqualTo(String value) {
            addCriterion("birth_month >=", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthLessThan(String value) {
            addCriterion("birth_month <", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthLessThanOrEqualTo(String value) {
            addCriterion("birth_month <=", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthLike(String value) {
            addCriterion("birth_month like", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthNotLike(String value) {
            addCriterion("birth_month not like", value, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthIn(List<String> values) {
            addCriterion("birth_month in", values, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthNotIn(List<String> values) {
            addCriterion("birth_month not in", values, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthBetween(String value1, String value2) {
            addCriterion("birth_month between", value1, value2, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthMonthNotBetween(String value1, String value2) {
            addCriterion("birth_month not between", value1, value2, "birthMonth");
            return (Criteria) this;
        }

        public Criteria andBirthDayIsNull() {
            addCriterion("birth_day is null");
            return (Criteria) this;
        }

        public Criteria andBirthDayIsNotNull() {
            addCriterion("birth_day is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDayEqualTo(String value) {
            addCriterion("birth_day =", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotEqualTo(String value) {
            addCriterion("birth_day <>", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayGreaterThan(String value) {
            addCriterion("birth_day >", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayGreaterThanOrEqualTo(String value) {
            addCriterion("birth_day >=", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLessThan(String value) {
            addCriterion("birth_day <", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLessThanOrEqualTo(String value) {
            addCriterion("birth_day <=", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLike(String value) {
            addCriterion("birth_day like", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotLike(String value) {
            addCriterion("birth_day not like", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayIn(List<String> values) {
            addCriterion("birth_day in", values, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotIn(List<String> values) {
            addCriterion("birth_day not in", values, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayBetween(String value1, String value2) {
            addCriterion("birth_day between", value1, value2, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotBetween(String value1, String value2) {
            addCriterion("birth_day not between", value1, value2, "birthDay");
            return (Criteria) this;
        }

        public Criteria andAstroIsNull() {
            addCriterion("astro is null");
            return (Criteria) this;
        }

        public Criteria andAstroIsNotNull() {
            addCriterion("astro is not null");
            return (Criteria) this;
        }

        public Criteria andAstroEqualTo(String value) {
            addCriterion("astro =", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroNotEqualTo(String value) {
            addCriterion("astro <>", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroGreaterThan(String value) {
            addCriterion("astro >", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroGreaterThanOrEqualTo(String value) {
            addCriterion("astro >=", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroLessThan(String value) {
            addCriterion("astro <", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroLessThanOrEqualTo(String value) {
            addCriterion("astro <=", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroLike(String value) {
            addCriterion("astro like", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroNotLike(String value) {
            addCriterion("astro not like", value, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroIn(List<String> values) {
            addCriterion("astro in", values, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroNotIn(List<String> values) {
            addCriterion("astro not in", values, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroBetween(String value1, String value2) {
            addCriterion("astro between", value1, value2, "astro");
            return (Criteria) this;
        }

        public Criteria andAstroNotBetween(String value1, String value2) {
            addCriterion("astro not between", value1, value2, "astro");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNull() {
            addCriterion("bloodtype is null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIsNotNull() {
            addCriterion("bloodtype is not null");
            return (Criteria) this;
        }

        public Criteria andBloodtypeEqualTo(Byte value) {
            addCriterion("bloodtype =", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotEqualTo(Byte value) {
            addCriterion("bloodtype <>", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThan(Byte value) {
            addCriterion("bloodtype >", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("bloodtype >=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThan(Byte value) {
            addCriterion("bloodtype <", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeLessThanOrEqualTo(Byte value) {
            addCriterion("bloodtype <=", value, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeIn(List<Byte> values) {
            addCriterion("bloodtype in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotIn(List<Byte> values) {
            addCriterion("bloodtype not in", values, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeBetween(Byte value1, Byte value2) {
            addCriterion("bloodtype between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andBloodtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("bloodtype not between", value1, value2, "bloodtype");
            return (Criteria) this;
        }

        public Criteria andTradeIsNull() {
            addCriterion("trade is null");
            return (Criteria) this;
        }

        public Criteria andTradeIsNotNull() {
            addCriterion("trade is not null");
            return (Criteria) this;
        }

        public Criteria andTradeEqualTo(String value) {
            addCriterion("trade =", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotEqualTo(String value) {
            addCriterion("trade <>", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThan(String value) {
            addCriterion("trade >", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThanOrEqualTo(String value) {
            addCriterion("trade >=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThan(String value) {
            addCriterion("trade <", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThanOrEqualTo(String value) {
            addCriterion("trade <=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLike(String value) {
            addCriterion("trade like", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotLike(String value) {
            addCriterion("trade not like", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeIn(List<String> values) {
            addCriterion("trade in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotIn(List<String> values) {
            addCriterion("trade not in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeBetween(String value1, String value2) {
            addCriterion("trade between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotBetween(String value1, String value2) {
            addCriterion("trade not between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andLiveProvIsNull() {
            addCriterion("live_prov is null");
            return (Criteria) this;
        }

        public Criteria andLiveProvIsNotNull() {
            addCriterion("live_prov is not null");
            return (Criteria) this;
        }

        public Criteria andLiveProvEqualTo(String value) {
            addCriterion("live_prov =", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvNotEqualTo(String value) {
            addCriterion("live_prov <>", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvGreaterThan(String value) {
            addCriterion("live_prov >", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvGreaterThanOrEqualTo(String value) {
            addCriterion("live_prov >=", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvLessThan(String value) {
            addCriterion("live_prov <", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvLessThanOrEqualTo(String value) {
            addCriterion("live_prov <=", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvLike(String value) {
            addCriterion("live_prov like", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvNotLike(String value) {
            addCriterion("live_prov not like", value, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvIn(List<String> values) {
            addCriterion("live_prov in", values, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvNotIn(List<String> values) {
            addCriterion("live_prov not in", values, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvBetween(String value1, String value2) {
            addCriterion("live_prov between", value1, value2, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveProvNotBetween(String value1, String value2) {
            addCriterion("live_prov not between", value1, value2, "liveProv");
            return (Criteria) this;
        }

        public Criteria andLiveCityIsNull() {
            addCriterion("live_city is null");
            return (Criteria) this;
        }

        public Criteria andLiveCityIsNotNull() {
            addCriterion("live_city is not null");
            return (Criteria) this;
        }

        public Criteria andLiveCityEqualTo(String value) {
            addCriterion("live_city =", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityNotEqualTo(String value) {
            addCriterion("live_city <>", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityGreaterThan(String value) {
            addCriterion("live_city >", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityGreaterThanOrEqualTo(String value) {
            addCriterion("live_city >=", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityLessThan(String value) {
            addCriterion("live_city <", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityLessThanOrEqualTo(String value) {
            addCriterion("live_city <=", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityLike(String value) {
            addCriterion("live_city like", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityNotLike(String value) {
            addCriterion("live_city not like", value, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityIn(List<String> values) {
            addCriterion("live_city in", values, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityNotIn(List<String> values) {
            addCriterion("live_city not in", values, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityBetween(String value1, String value2) {
            addCriterion("live_city between", value1, value2, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCityNotBetween(String value1, String value2) {
            addCriterion("live_city not between", value1, value2, "liveCity");
            return (Criteria) this;
        }

        public Criteria andLiveCountryIsNull() {
            addCriterion("live_country is null");
            return (Criteria) this;
        }

        public Criteria andLiveCountryIsNotNull() {
            addCriterion("live_country is not null");
            return (Criteria) this;
        }

        public Criteria andLiveCountryEqualTo(String value) {
            addCriterion("live_country =", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryNotEqualTo(String value) {
            addCriterion("live_country <>", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryGreaterThan(String value) {
            addCriterion("live_country >", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryGreaterThanOrEqualTo(String value) {
            addCriterion("live_country >=", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryLessThan(String value) {
            addCriterion("live_country <", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryLessThanOrEqualTo(String value) {
            addCriterion("live_country <=", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryLike(String value) {
            addCriterion("live_country like", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryNotLike(String value) {
            addCriterion("live_country not like", value, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryIn(List<String> values) {
            addCriterion("live_country in", values, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryNotIn(List<String> values) {
            addCriterion("live_country not in", values, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryBetween(String value1, String value2) {
            addCriterion("live_country between", value1, value2, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andLiveCountryNotBetween(String value1, String value2) {
            addCriterion("live_country not between", value1, value2, "liveCountry");
            return (Criteria) this;
        }

        public Criteria andHomeProvIsNull() {
            addCriterion("home_prov is null");
            return (Criteria) this;
        }

        public Criteria andHomeProvIsNotNull() {
            addCriterion("home_prov is not null");
            return (Criteria) this;
        }

        public Criteria andHomeProvEqualTo(String value) {
            addCriterion("home_prov =", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvNotEqualTo(String value) {
            addCriterion("home_prov <>", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvGreaterThan(String value) {
            addCriterion("home_prov >", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvGreaterThanOrEqualTo(String value) {
            addCriterion("home_prov >=", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvLessThan(String value) {
            addCriterion("home_prov <", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvLessThanOrEqualTo(String value) {
            addCriterion("home_prov <=", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvLike(String value) {
            addCriterion("home_prov like", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvNotLike(String value) {
            addCriterion("home_prov not like", value, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvIn(List<String> values) {
            addCriterion("home_prov in", values, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvNotIn(List<String> values) {
            addCriterion("home_prov not in", values, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvBetween(String value1, String value2) {
            addCriterion("home_prov between", value1, value2, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeProvNotBetween(String value1, String value2) {
            addCriterion("home_prov not between", value1, value2, "homeProv");
            return (Criteria) this;
        }

        public Criteria andHomeCityIsNull() {
            addCriterion("home_city is null");
            return (Criteria) this;
        }

        public Criteria andHomeCityIsNotNull() {
            addCriterion("home_city is not null");
            return (Criteria) this;
        }

        public Criteria andHomeCityEqualTo(String value) {
            addCriterion("home_city =", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityNotEqualTo(String value) {
            addCriterion("home_city <>", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityGreaterThan(String value) {
            addCriterion("home_city >", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityGreaterThanOrEqualTo(String value) {
            addCriterion("home_city >=", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityLessThan(String value) {
            addCriterion("home_city <", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityLessThanOrEqualTo(String value) {
            addCriterion("home_city <=", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityLike(String value) {
            addCriterion("home_city like", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityNotLike(String value) {
            addCriterion("home_city not like", value, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityIn(List<String> values) {
            addCriterion("home_city in", values, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityNotIn(List<String> values) {
            addCriterion("home_city not in", values, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityBetween(String value1, String value2) {
            addCriterion("home_city between", value1, value2, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCityNotBetween(String value1, String value2) {
            addCriterion("home_city not between", value1, value2, "homeCity");
            return (Criteria) this;
        }

        public Criteria andHomeCountryIsNull() {
            addCriterion("home_country is null");
            return (Criteria) this;
        }

        public Criteria andHomeCountryIsNotNull() {
            addCriterion("home_country is not null");
            return (Criteria) this;
        }

        public Criteria andHomeCountryEqualTo(String value) {
            addCriterion("home_country =", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryNotEqualTo(String value) {
            addCriterion("home_country <>", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryGreaterThan(String value) {
            addCriterion("home_country >", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryGreaterThanOrEqualTo(String value) {
            addCriterion("home_country >=", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryLessThan(String value) {
            addCriterion("home_country <", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryLessThanOrEqualTo(String value) {
            addCriterion("home_country <=", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryLike(String value) {
            addCriterion("home_country like", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryNotLike(String value) {
            addCriterion("home_country not like", value, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryIn(List<String> values) {
            addCriterion("home_country in", values, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryNotIn(List<String> values) {
            addCriterion("home_country not in", values, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryBetween(String value1, String value2) {
            addCriterion("home_country between", value1, value2, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andHomeCountryNotBetween(String value1, String value2) {
            addCriterion("home_country not between", value1, value2, "homeCountry");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNull() {
            addCriterion("cardtype is null");
            return (Criteria) this;
        }

        public Criteria andCardtypeIsNotNull() {
            addCriterion("cardtype is not null");
            return (Criteria) this;
        }

        public Criteria andCardtypeEqualTo(Byte value) {
            addCriterion("cardtype =", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotEqualTo(Byte value) {
            addCriterion("cardtype <>", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThan(Byte value) {
            addCriterion("cardtype >", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("cardtype >=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThan(Byte value) {
            addCriterion("cardtype <", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeLessThanOrEqualTo(Byte value) {
            addCriterion("cardtype <=", value, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeIn(List<Byte> values) {
            addCriterion("cardtype in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotIn(List<Byte> values) {
            addCriterion("cardtype not in", values, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeBetween(Byte value1, Byte value2) {
            addCriterion("cardtype between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("cardtype not between", value1, value2, "cardtype");
            return (Criteria) this;
        }

        public Criteria andCardnumIsNull() {
            addCriterion("cardnum is null");
            return (Criteria) this;
        }

        public Criteria andCardnumIsNotNull() {
            addCriterion("cardnum is not null");
            return (Criteria) this;
        }

        public Criteria andCardnumEqualTo(String value) {
            addCriterion("cardnum =", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotEqualTo(String value) {
            addCriterion("cardnum <>", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumGreaterThan(String value) {
            addCriterion("cardnum >", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumGreaterThanOrEqualTo(String value) {
            addCriterion("cardnum >=", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLessThan(String value) {
            addCriterion("cardnum <", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLessThanOrEqualTo(String value) {
            addCriterion("cardnum <=", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumLike(String value) {
            addCriterion("cardnum like", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotLike(String value) {
            addCriterion("cardnum not like", value, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumIn(List<String> values) {
            addCriterion("cardnum in", values, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotIn(List<String> values) {
            addCriterion("cardnum not in", values, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumBetween(String value1, String value2) {
            addCriterion("cardnum between", value1, value2, "cardnum");
            return (Criteria) this;
        }

        public Criteria andCardnumNotBetween(String value1, String value2) {
            addCriterion("cardnum not between", value1, value2, "cardnum");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("intro not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andQqnumIsNull() {
            addCriterion("qqnum is null");
            return (Criteria) this;
        }

        public Criteria andQqnumIsNotNull() {
            addCriterion("qqnum is not null");
            return (Criteria) this;
        }

        public Criteria andQqnumEqualTo(String value) {
            addCriterion("qqnum =", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumNotEqualTo(String value) {
            addCriterion("qqnum <>", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumGreaterThan(String value) {
            addCriterion("qqnum >", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumGreaterThanOrEqualTo(String value) {
            addCriterion("qqnum >=", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumLessThan(String value) {
            addCriterion("qqnum <", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumLessThanOrEqualTo(String value) {
            addCriterion("qqnum <=", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumLike(String value) {
            addCriterion("qqnum like", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumNotLike(String value) {
            addCriterion("qqnum not like", value, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumIn(List<String> values) {
            addCriterion("qqnum in", values, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumNotIn(List<String> values) {
            addCriterion("qqnum not in", values, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumBetween(String value1, String value2) {
            addCriterion("qqnum between", value1, value2, "qqnum");
            return (Criteria) this;
        }

        public Criteria andQqnumNotBetween(String value1, String value2) {
            addCriterion("qqnum not between", value1, value2, "qqnum");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andAddressProvIsNull() {
            addCriterion("address_prov is null");
            return (Criteria) this;
        }

        public Criteria andAddressProvIsNotNull() {
            addCriterion("address_prov is not null");
            return (Criteria) this;
        }

        public Criteria andAddressProvEqualTo(String value) {
            addCriterion("address_prov =", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvNotEqualTo(String value) {
            addCriterion("address_prov <>", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvGreaterThan(String value) {
            addCriterion("address_prov >", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvGreaterThanOrEqualTo(String value) {
            addCriterion("address_prov >=", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvLessThan(String value) {
            addCriterion("address_prov <", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvLessThanOrEqualTo(String value) {
            addCriterion("address_prov <=", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvLike(String value) {
            addCriterion("address_prov like", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvNotLike(String value) {
            addCriterion("address_prov not like", value, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvIn(List<String> values) {
            addCriterion("address_prov in", values, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvNotIn(List<String> values) {
            addCriterion("address_prov not in", values, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvBetween(String value1, String value2) {
            addCriterion("address_prov between", value1, value2, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressProvNotBetween(String value1, String value2) {
            addCriterion("address_prov not between", value1, value2, "addressProv");
            return (Criteria) this;
        }

        public Criteria andAddressCityIsNull() {
            addCriterion("address_city is null");
            return (Criteria) this;
        }

        public Criteria andAddressCityIsNotNull() {
            addCriterion("address_city is not null");
            return (Criteria) this;
        }

        public Criteria andAddressCityEqualTo(String value) {
            addCriterion("address_city =", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityNotEqualTo(String value) {
            addCriterion("address_city <>", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityGreaterThan(String value) {
            addCriterion("address_city >", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityGreaterThanOrEqualTo(String value) {
            addCriterion("address_city >=", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityLessThan(String value) {
            addCriterion("address_city <", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityLessThanOrEqualTo(String value) {
            addCriterion("address_city <=", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityLike(String value) {
            addCriterion("address_city like", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityNotLike(String value) {
            addCriterion("address_city not like", value, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityIn(List<String> values) {
            addCriterion("address_city in", values, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityNotIn(List<String> values) {
            addCriterion("address_city not in", values, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityBetween(String value1, String value2) {
            addCriterion("address_city between", value1, value2, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCityNotBetween(String value1, String value2) {
            addCriterion("address_city not between", value1, value2, "addressCity");
            return (Criteria) this;
        }

        public Criteria andAddressCountryIsNull() {
            addCriterion("address_country is null");
            return (Criteria) this;
        }

        public Criteria andAddressCountryIsNotNull() {
            addCriterion("address_country is not null");
            return (Criteria) this;
        }

        public Criteria andAddressCountryEqualTo(String value) {
            addCriterion("address_country =", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryNotEqualTo(String value) {
            addCriterion("address_country <>", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryGreaterThan(String value) {
            addCriterion("address_country >", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryGreaterThanOrEqualTo(String value) {
            addCriterion("address_country >=", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryLessThan(String value) {
            addCriterion("address_country <", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryLessThanOrEqualTo(String value) {
            addCriterion("address_country <=", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryLike(String value) {
            addCriterion("address_country like", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryNotLike(String value) {
            addCriterion("address_country not like", value, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryIn(List<String> values) {
            addCriterion("address_country in", values, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryNotIn(List<String> values) {
            addCriterion("address_country not in", values, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryBetween(String value1, String value2) {
            addCriterion("address_country between", value1, value2, "addressCountry");
            return (Criteria) this;
        }

        public Criteria andAddressCountryNotBetween(String value1, String value2) {
            addCriterion("address_country not between", value1, value2, "addressCountry");
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

        public Criteria andZipcodeIsNull() {
            addCriterion("zipcode is null");
            return (Criteria) this;
        }

        public Criteria andZipcodeIsNotNull() {
            addCriterion("zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andZipcodeEqualTo(String value) {
            addCriterion("zipcode =", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotEqualTo(String value) {
            addCriterion("zipcode <>", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThan(String value) {
            addCriterion("zipcode >", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("zipcode >=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThan(String value) {
            addCriterion("zipcode <", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLessThanOrEqualTo(String value) {
            addCriterion("zipcode <=", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeLike(String value) {
            addCriterion("zipcode like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotLike(String value) {
            addCriterion("zipcode not like", value, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeIn(List<String> values) {
            addCriterion("zipcode in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotIn(List<String> values) {
            addCriterion("zipcode not in", values, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeBetween(String value1, String value2) {
            addCriterion("zipcode between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andZipcodeNotBetween(String value1, String value2) {
            addCriterion("zipcode not between", value1, value2, "zipcode");
            return (Criteria) this;
        }

        public Criteria andEnteruserIsNull() {
            addCriterion("enteruser is null");
            return (Criteria) this;
        }

        public Criteria andEnteruserIsNotNull() {
            addCriterion("enteruser is not null");
            return (Criteria) this;
        }

        public Criteria andEnteruserEqualTo(String value) {
            addCriterion("enteruser =", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserNotEqualTo(String value) {
            addCriterion("enteruser <>", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserGreaterThan(String value) {
            addCriterion("enteruser >", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserGreaterThanOrEqualTo(String value) {
            addCriterion("enteruser >=", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserLessThan(String value) {
            addCriterion("enteruser <", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserLessThanOrEqualTo(String value) {
            addCriterion("enteruser <=", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserLike(String value) {
            addCriterion("enteruser like", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserNotLike(String value) {
            addCriterion("enteruser not like", value, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserIn(List<String> values) {
            addCriterion("enteruser in", values, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserNotIn(List<String> values) {
            addCriterion("enteruser not in", values, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserBetween(String value1, String value2) {
            addCriterion("enteruser between", value1, value2, "enteruser");
            return (Criteria) this;
        }

        public Criteria andEnteruserNotBetween(String value1, String value2) {
            addCriterion("enteruser not between", value1, value2, "enteruser");
            return (Criteria) this;
        }

        public Criteria andExpvalIsNull() {
            addCriterion("expval is null");
            return (Criteria) this;
        }

        public Criteria andExpvalIsNotNull() {
            addCriterion("expval is not null");
            return (Criteria) this;
        }

        public Criteria andExpvalEqualTo(Integer value) {
            addCriterion("expval =", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalNotEqualTo(Integer value) {
            addCriterion("expval <>", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalGreaterThan(Integer value) {
            addCriterion("expval >", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalGreaterThanOrEqualTo(Integer value) {
            addCriterion("expval >=", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalLessThan(Integer value) {
            addCriterion("expval <", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalLessThanOrEqualTo(Integer value) {
            addCriterion("expval <=", value, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalIn(List<Integer> values) {
            addCriterion("expval in", values, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalNotIn(List<Integer> values) {
            addCriterion("expval not in", values, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalBetween(Integer value1, Integer value2) {
            addCriterion("expval between", value1, value2, "expval");
            return (Criteria) this;
        }

        public Criteria andExpvalNotBetween(Integer value1, Integer value2) {
            addCriterion("expval not between", value1, value2, "expval");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNull() {
            addCriterion("regtime is null");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNotNull() {
            addCriterion("regtime is not null");
            return (Criteria) this;
        }

        public Criteria andRegtimeEqualTo(Integer value) {
            addCriterion("regtime =", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotEqualTo(Integer value) {
            addCriterion("regtime <>", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThan(Integer value) {
            addCriterion("regtime >", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("regtime >=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThan(Integer value) {
            addCriterion("regtime <", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThanOrEqualTo(Integer value) {
            addCriterion("regtime <=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeIn(List<Integer> values) {
            addCriterion("regtime in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotIn(List<Integer> values) {
            addCriterion("regtime not in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeBetween(Integer value1, Integer value2) {
            addCriterion("regtime between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("regtime not between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegipIsNull() {
            addCriterion("regip is null");
            return (Criteria) this;
        }

        public Criteria andRegipIsNotNull() {
            addCriterion("regip is not null");
            return (Criteria) this;
        }

        public Criteria andRegipEqualTo(String value) {
            addCriterion("regip =", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotEqualTo(String value) {
            addCriterion("regip <>", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThan(String value) {
            addCriterion("regip >", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipGreaterThanOrEqualTo(String value) {
            addCriterion("regip >=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThan(String value) {
            addCriterion("regip <", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLessThanOrEqualTo(String value) {
            addCriterion("regip <=", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipLike(String value) {
            addCriterion("regip like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotLike(String value) {
            addCriterion("regip not like", value, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipIn(List<String> values) {
            addCriterion("regip in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotIn(List<String> values) {
            addCriterion("regip not in", values, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipBetween(String value1, String value2) {
            addCriterion("regip between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andRegipNotBetween(String value1, String value2) {
            addCriterion("regip not between", value1, value2, "regip");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNull() {
            addCriterion("logintime is null");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNotNull() {
            addCriterion("logintime is not null");
            return (Criteria) this;
        }

        public Criteria andLogintimeEqualTo(Integer value) {
            addCriterion("logintime =", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotEqualTo(Integer value) {
            addCriterion("logintime <>", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThan(Integer value) {
            addCriterion("logintime >", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("logintime >=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThan(Integer value) {
            addCriterion("logintime <", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThanOrEqualTo(Integer value) {
            addCriterion("logintime <=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeIn(List<Integer> values) {
            addCriterion("logintime in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotIn(List<Integer> values) {
            addCriterion("logintime not in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeBetween(Integer value1, Integer value2) {
            addCriterion("logintime between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotBetween(Integer value1, Integer value2) {
            addCriterion("logintime not between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLoginipIsNull() {
            addCriterion("loginip is null");
            return (Criteria) this;
        }

        public Criteria andLoginipIsNotNull() {
            addCriterion("loginip is not null");
            return (Criteria) this;
        }

        public Criteria andLoginipEqualTo(String value) {
            addCriterion("loginip =", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipNotEqualTo(String value) {
            addCriterion("loginip <>", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipGreaterThan(String value) {
            addCriterion("loginip >", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipGreaterThanOrEqualTo(String value) {
            addCriterion("loginip >=", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipLessThan(String value) {
            addCriterion("loginip <", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipLessThanOrEqualTo(String value) {
            addCriterion("loginip <=", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipLike(String value) {
            addCriterion("loginip like", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipNotLike(String value) {
            addCriterion("loginip not like", value, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipIn(List<String> values) {
            addCriterion("loginip in", values, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipNotIn(List<String> values) {
            addCriterion("loginip not in", values, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipBetween(String value1, String value2) {
            addCriterion("loginip between", value1, value2, "loginip");
            return (Criteria) this;
        }

        public Criteria andLoginipNotBetween(String value1, String value2) {
            addCriterion("loginip not between", value1, value2, "loginip");
            return (Criteria) this;
        }

        public Criteria andHaoduanIsNull() {
            addCriterion("haoduan is null");
            return (Criteria) this;
        }

        public Criteria andHaoduanIsNotNull() {
            addCriterion("haoduan is not null");
            return (Criteria) this;
        }

        public Criteria andHaoduanEqualTo(Integer value) {
            addCriterion("haoduan =", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanNotEqualTo(Integer value) {
            addCriterion("haoduan <>", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanGreaterThan(Integer value) {
            addCriterion("haoduan >", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanGreaterThanOrEqualTo(Integer value) {
            addCriterion("haoduan >=", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanLessThan(Integer value) {
            addCriterion("haoduan <", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanLessThanOrEqualTo(Integer value) {
            addCriterion("haoduan <=", value, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanIn(List<Integer> values) {
            addCriterion("haoduan in", values, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanNotIn(List<Integer> values) {
            addCriterion("haoduan not in", values, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanBetween(Integer value1, Integer value2) {
            addCriterion("haoduan between", value1, value2, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHaoduanNotBetween(Integer value1, Integer value2) {
            addCriterion("haoduan not between", value1, value2, "haoduan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanIsNull() {
            addCriterion("huiyuan is null");
            return (Criteria) this;
        }

        public Criteria andHuiyuanIsNotNull() {
            addCriterion("huiyuan is not null");
            return (Criteria) this;
        }

        public Criteria andHuiyuanEqualTo(String value) {
            addCriterion("huiyuan =", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanNotEqualTo(String value) {
            addCriterion("huiyuan <>", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanGreaterThan(String value) {
            addCriterion("huiyuan >", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanGreaterThanOrEqualTo(String value) {
            addCriterion("huiyuan >=", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanLessThan(String value) {
            addCriterion("huiyuan <", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanLessThanOrEqualTo(String value) {
            addCriterion("huiyuan <=", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanLike(String value) {
            addCriterion("huiyuan like", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanNotLike(String value) {
            addCriterion("huiyuan not like", value, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanIn(List<String> values) {
            addCriterion("huiyuan in", values, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanNotIn(List<String> values) {
            addCriterion("huiyuan not in", values, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanBetween(String value1, String value2) {
            addCriterion("huiyuan between", value1, value2, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andHuiyuanNotBetween(String value1, String value2) {
            addCriterion("huiyuan not between", value1, value2, "huiyuan");
            return (Criteria) this;
        }

        public Criteria andAboutusIsNull() {
            addCriterion("aboutus is null");
            return (Criteria) this;
        }

        public Criteria andAboutusIsNotNull() {
            addCriterion("aboutus is not null");
            return (Criteria) this;
        }

        public Criteria andAboutusEqualTo(String value) {
            addCriterion("aboutus =", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusNotEqualTo(String value) {
            addCriterion("aboutus <>", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusGreaterThan(String value) {
            addCriterion("aboutus >", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusGreaterThanOrEqualTo(String value) {
            addCriterion("aboutus >=", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusLessThan(String value) {
            addCriterion("aboutus <", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusLessThanOrEqualTo(String value) {
            addCriterion("aboutus <=", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusLike(String value) {
            addCriterion("aboutus like", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusNotLike(String value) {
            addCriterion("aboutus not like", value, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusIn(List<String> values) {
            addCriterion("aboutus in", values, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusNotIn(List<String> values) {
            addCriterion("aboutus not in", values, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusBetween(String value1, String value2) {
            addCriterion("aboutus between", value1, value2, "aboutus");
            return (Criteria) this;
        }

        public Criteria andAboutusNotBetween(String value1, String value2) {
            addCriterion("aboutus not between", value1, value2, "aboutus");
            return (Criteria) this;
        }

        public Criteria andContactusIsNull() {
            addCriterion("contactus is null");
            return (Criteria) this;
        }

        public Criteria andContactusIsNotNull() {
            addCriterion("contactus is not null");
            return (Criteria) this;
        }

        public Criteria andContactusEqualTo(String value) {
            addCriterion("contactus =", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusNotEqualTo(String value) {
            addCriterion("contactus <>", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusGreaterThan(String value) {
            addCriterion("contactus >", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusGreaterThanOrEqualTo(String value) {
            addCriterion("contactus >=", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusLessThan(String value) {
            addCriterion("contactus <", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusLessThanOrEqualTo(String value) {
            addCriterion("contactus <=", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusLike(String value) {
            addCriterion("contactus like", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusNotLike(String value) {
            addCriterion("contactus not like", value, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusIn(List<String> values) {
            addCriterion("contactus in", values, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusNotIn(List<String> values) {
            addCriterion("contactus not in", values, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusBetween(String value1, String value2) {
            addCriterion("contactus between", value1, value2, "contactus");
            return (Criteria) this;
        }

        public Criteria andContactusNotBetween(String value1, String value2) {
            addCriterion("contactus not between", value1, value2, "contactus");
            return (Criteria) this;
        }

        public Criteria andA1IsNull() {
            addCriterion("a1 is null");
            return (Criteria) this;
        }

        public Criteria andA1IsNotNull() {
            addCriterion("a1 is not null");
            return (Criteria) this;
        }

        public Criteria andA1EqualTo(String value) {
            addCriterion("a1 =", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1NotEqualTo(String value) {
            addCriterion("a1 <>", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1GreaterThan(String value) {
            addCriterion("a1 >", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1GreaterThanOrEqualTo(String value) {
            addCriterion("a1 >=", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1LessThan(String value) {
            addCriterion("a1 <", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1LessThanOrEqualTo(String value) {
            addCriterion("a1 <=", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1Like(String value) {
            addCriterion("a1 like", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1NotLike(String value) {
            addCriterion("a1 not like", value, "a1");
            return (Criteria) this;
        }

        public Criteria andA1In(List<String> values) {
            addCriterion("a1 in", values, "a1");
            return (Criteria) this;
        }

        public Criteria andA1NotIn(List<String> values) {
            addCriterion("a1 not in", values, "a1");
            return (Criteria) this;
        }

        public Criteria andA1Between(String value1, String value2) {
            addCriterion("a1 between", value1, value2, "a1");
            return (Criteria) this;
        }

        public Criteria andA1NotBetween(String value1, String value2) {
            addCriterion("a1 not between", value1, value2, "a1");
            return (Criteria) this;
        }

        public Criteria andA2IsNull() {
            addCriterion("a2 is null");
            return (Criteria) this;
        }

        public Criteria andA2IsNotNull() {
            addCriterion("a2 is not null");
            return (Criteria) this;
        }

        public Criteria andA2EqualTo(String value) {
            addCriterion("a2 =", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2NotEqualTo(String value) {
            addCriterion("a2 <>", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2GreaterThan(String value) {
            addCriterion("a2 >", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2GreaterThanOrEqualTo(String value) {
            addCriterion("a2 >=", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2LessThan(String value) {
            addCriterion("a2 <", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2LessThanOrEqualTo(String value) {
            addCriterion("a2 <=", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2Like(String value) {
            addCriterion("a2 like", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2NotLike(String value) {
            addCriterion("a2 not like", value, "a2");
            return (Criteria) this;
        }

        public Criteria andA2In(List<String> values) {
            addCriterion("a2 in", values, "a2");
            return (Criteria) this;
        }

        public Criteria andA2NotIn(List<String> values) {
            addCriterion("a2 not in", values, "a2");
            return (Criteria) this;
        }

        public Criteria andA2Between(String value1, String value2) {
            addCriterion("a2 between", value1, value2, "a2");
            return (Criteria) this;
        }

        public Criteria andA2NotBetween(String value1, String value2) {
            addCriterion("a2 not between", value1, value2, "a2");
            return (Criteria) this;
        }

        public Criteria andA3IsNull() {
            addCriterion("a3 is null");
            return (Criteria) this;
        }

        public Criteria andA3IsNotNull() {
            addCriterion("a3 is not null");
            return (Criteria) this;
        }

        public Criteria andA3EqualTo(String value) {
            addCriterion("a3 =", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3NotEqualTo(String value) {
            addCriterion("a3 <>", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3GreaterThan(String value) {
            addCriterion("a3 >", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3GreaterThanOrEqualTo(String value) {
            addCriterion("a3 >=", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3LessThan(String value) {
            addCriterion("a3 <", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3LessThanOrEqualTo(String value) {
            addCriterion("a3 <=", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3Like(String value) {
            addCriterion("a3 like", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3NotLike(String value) {
            addCriterion("a3 not like", value, "a3");
            return (Criteria) this;
        }

        public Criteria andA3In(List<String> values) {
            addCriterion("a3 in", values, "a3");
            return (Criteria) this;
        }

        public Criteria andA3NotIn(List<String> values) {
            addCriterion("a3 not in", values, "a3");
            return (Criteria) this;
        }

        public Criteria andA3Between(String value1, String value2) {
            addCriterion("a3 between", value1, value2, "a3");
            return (Criteria) this;
        }

        public Criteria andA3NotBetween(String value1, String value2) {
            addCriterion("a3 not between", value1, value2, "a3");
            return (Criteria) this;
        }

        public Criteria andA4IsNull() {
            addCriterion("a4 is null");
            return (Criteria) this;
        }

        public Criteria andA4IsNotNull() {
            addCriterion("a4 is not null");
            return (Criteria) this;
        }

        public Criteria andA4EqualTo(String value) {
            addCriterion("a4 =", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4NotEqualTo(String value) {
            addCriterion("a4 <>", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4GreaterThan(String value) {
            addCriterion("a4 >", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4GreaterThanOrEqualTo(String value) {
            addCriterion("a4 >=", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4LessThan(String value) {
            addCriterion("a4 <", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4LessThanOrEqualTo(String value) {
            addCriterion("a4 <=", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4Like(String value) {
            addCriterion("a4 like", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4NotLike(String value) {
            addCriterion("a4 not like", value, "a4");
            return (Criteria) this;
        }

        public Criteria andA4In(List<String> values) {
            addCriterion("a4 in", values, "a4");
            return (Criteria) this;
        }

        public Criteria andA4NotIn(List<String> values) {
            addCriterion("a4 not in", values, "a4");
            return (Criteria) this;
        }

        public Criteria andA4Between(String value1, String value2) {
            addCriterion("a4 between", value1, value2, "a4");
            return (Criteria) this;
        }

        public Criteria andA4NotBetween(String value1, String value2) {
            addCriterion("a4 not between", value1, value2, "a4");
            return (Criteria) this;
        }

        public Criteria andA5IsNull() {
            addCriterion("a5 is null");
            return (Criteria) this;
        }

        public Criteria andA5IsNotNull() {
            addCriterion("a5 is not null");
            return (Criteria) this;
        }

        public Criteria andA5EqualTo(String value) {
            addCriterion("a5 =", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5NotEqualTo(String value) {
            addCriterion("a5 <>", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5GreaterThan(String value) {
            addCriterion("a5 >", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5GreaterThanOrEqualTo(String value) {
            addCriterion("a5 >=", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5LessThan(String value) {
            addCriterion("a5 <", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5LessThanOrEqualTo(String value) {
            addCriterion("a5 <=", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5Like(String value) {
            addCriterion("a5 like", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5NotLike(String value) {
            addCriterion("a5 not like", value, "a5");
            return (Criteria) this;
        }

        public Criteria andA5In(List<String> values) {
            addCriterion("a5 in", values, "a5");
            return (Criteria) this;
        }

        public Criteria andA5NotIn(List<String> values) {
            addCriterion("a5 not in", values, "a5");
            return (Criteria) this;
        }

        public Criteria andA5Between(String value1, String value2) {
            addCriterion("a5 between", value1, value2, "a5");
            return (Criteria) this;
        }

        public Criteria andA5NotBetween(String value1, String value2) {
            addCriterion("a5 not between", value1, value2, "a5");
            return (Criteria) this;
        }

        public Criteria andA6IsNull() {
            addCriterion("a6 is null");
            return (Criteria) this;
        }

        public Criteria andA6IsNotNull() {
            addCriterion("a6 is not null");
            return (Criteria) this;
        }

        public Criteria andA6EqualTo(String value) {
            addCriterion("a6 =", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6NotEqualTo(String value) {
            addCriterion("a6 <>", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6GreaterThan(String value) {
            addCriterion("a6 >", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6GreaterThanOrEqualTo(String value) {
            addCriterion("a6 >=", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6LessThan(String value) {
            addCriterion("a6 <", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6LessThanOrEqualTo(String value) {
            addCriterion("a6 <=", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6Like(String value) {
            addCriterion("a6 like", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6NotLike(String value) {
            addCriterion("a6 not like", value, "a6");
            return (Criteria) this;
        }

        public Criteria andA6In(List<String> values) {
            addCriterion("a6 in", values, "a6");
            return (Criteria) this;
        }

        public Criteria andA6NotIn(List<String> values) {
            addCriterion("a6 not in", values, "a6");
            return (Criteria) this;
        }

        public Criteria andA6Between(String value1, String value2) {
            addCriterion("a6 between", value1, value2, "a6");
            return (Criteria) this;
        }

        public Criteria andA6NotBetween(String value1, String value2) {
            addCriterion("a6 not between", value1, value2, "a6");
            return (Criteria) this;
        }

        public Criteria andA7IsNull() {
            addCriterion("a7 is null");
            return (Criteria) this;
        }

        public Criteria andA7IsNotNull() {
            addCriterion("a7 is not null");
            return (Criteria) this;
        }

        public Criteria andA7EqualTo(String value) {
            addCriterion("a7 =", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7NotEqualTo(String value) {
            addCriterion("a7 <>", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7GreaterThan(String value) {
            addCriterion("a7 >", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7GreaterThanOrEqualTo(String value) {
            addCriterion("a7 >=", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7LessThan(String value) {
            addCriterion("a7 <", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7LessThanOrEqualTo(String value) {
            addCriterion("a7 <=", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7Like(String value) {
            addCriterion("a7 like", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7NotLike(String value) {
            addCriterion("a7 not like", value, "a7");
            return (Criteria) this;
        }

        public Criteria andA7In(List<String> values) {
            addCriterion("a7 in", values, "a7");
            return (Criteria) this;
        }

        public Criteria andA7NotIn(List<String> values) {
            addCriterion("a7 not in", values, "a7");
            return (Criteria) this;
        }

        public Criteria andA7Between(String value1, String value2) {
            addCriterion("a7 between", value1, value2, "a7");
            return (Criteria) this;
        }

        public Criteria andA7NotBetween(String value1, String value2) {
            addCriterion("a7 not between", value1, value2, "a7");
            return (Criteria) this;
        }

        public Criteria andA8IsNull() {
            addCriterion("a8 is null");
            return (Criteria) this;
        }

        public Criteria andA8IsNotNull() {
            addCriterion("a8 is not null");
            return (Criteria) this;
        }

        public Criteria andA8EqualTo(String value) {
            addCriterion("a8 =", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8NotEqualTo(String value) {
            addCriterion("a8 <>", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8GreaterThan(String value) {
            addCriterion("a8 >", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8GreaterThanOrEqualTo(String value) {
            addCriterion("a8 >=", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8LessThan(String value) {
            addCriterion("a8 <", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8LessThanOrEqualTo(String value) {
            addCriterion("a8 <=", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8Like(String value) {
            addCriterion("a8 like", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8NotLike(String value) {
            addCriterion("a8 not like", value, "a8");
            return (Criteria) this;
        }

        public Criteria andA8In(List<String> values) {
            addCriterion("a8 in", values, "a8");
            return (Criteria) this;
        }

        public Criteria andA8NotIn(List<String> values) {
            addCriterion("a8 not in", values, "a8");
            return (Criteria) this;
        }

        public Criteria andA8Between(String value1, String value2) {
            addCriterion("a8 between", value1, value2, "a8");
            return (Criteria) this;
        }

        public Criteria andA8NotBetween(String value1, String value2) {
            addCriterion("a8 not between", value1, value2, "a8");
            return (Criteria) this;
        }

        public Criteria andA9IsNull() {
            addCriterion("a9 is null");
            return (Criteria) this;
        }

        public Criteria andA9IsNotNull() {
            addCriterion("a9 is not null");
            return (Criteria) this;
        }

        public Criteria andA9EqualTo(String value) {
            addCriterion("a9 =", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9NotEqualTo(String value) {
            addCriterion("a9 <>", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9GreaterThan(String value) {
            addCriterion("a9 >", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9GreaterThanOrEqualTo(String value) {
            addCriterion("a9 >=", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9LessThan(String value) {
            addCriterion("a9 <", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9LessThanOrEqualTo(String value) {
            addCriterion("a9 <=", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9Like(String value) {
            addCriterion("a9 like", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9NotLike(String value) {
            addCriterion("a9 not like", value, "a9");
            return (Criteria) this;
        }

        public Criteria andA9In(List<String> values) {
            addCriterion("a9 in", values, "a9");
            return (Criteria) this;
        }

        public Criteria andA9NotIn(List<String> values) {
            addCriterion("a9 not in", values, "a9");
            return (Criteria) this;
        }

        public Criteria andA9Between(String value1, String value2) {
            addCriterion("a9 between", value1, value2, "a9");
            return (Criteria) this;
        }

        public Criteria andA9NotBetween(String value1, String value2) {
            addCriterion("a9 not between", value1, value2, "a9");
            return (Criteria) this;
        }

        public Criteria andA10IsNull() {
            addCriterion("a10 is null");
            return (Criteria) this;
        }

        public Criteria andA10IsNotNull() {
            addCriterion("a10 is not null");
            return (Criteria) this;
        }

        public Criteria andA10EqualTo(String value) {
            addCriterion("a10 =", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10NotEqualTo(String value) {
            addCriterion("a10 <>", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10GreaterThan(String value) {
            addCriterion("a10 >", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10GreaterThanOrEqualTo(String value) {
            addCriterion("a10 >=", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10LessThan(String value) {
            addCriterion("a10 <", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10LessThanOrEqualTo(String value) {
            addCriterion("a10 <=", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10Like(String value) {
            addCriterion("a10 like", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10NotLike(String value) {
            addCriterion("a10 not like", value, "a10");
            return (Criteria) this;
        }

        public Criteria andA10In(List<String> values) {
            addCriterion("a10 in", values, "a10");
            return (Criteria) this;
        }

        public Criteria andA10NotIn(List<String> values) {
            addCriterion("a10 not in", values, "a10");
            return (Criteria) this;
        }

        public Criteria andA10Between(String value1, String value2) {
            addCriterion("a10 between", value1, value2, "a10");
            return (Criteria) this;
        }

        public Criteria andA10NotBetween(String value1, String value2) {
            addCriterion("a10 not between", value1, value2, "a10");
            return (Criteria) this;
        }

        public Criteria andA11IsNull() {
            addCriterion("a11 is null");
            return (Criteria) this;
        }

        public Criteria andA11IsNotNull() {
            addCriterion("a11 is not null");
            return (Criteria) this;
        }

        public Criteria andA11EqualTo(String value) {
            addCriterion("a11 =", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11NotEqualTo(String value) {
            addCriterion("a11 <>", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11GreaterThan(String value) {
            addCriterion("a11 >", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11GreaterThanOrEqualTo(String value) {
            addCriterion("a11 >=", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11LessThan(String value) {
            addCriterion("a11 <", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11LessThanOrEqualTo(String value) {
            addCriterion("a11 <=", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11Like(String value) {
            addCriterion("a11 like", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11NotLike(String value) {
            addCriterion("a11 not like", value, "a11");
            return (Criteria) this;
        }

        public Criteria andA11In(List<String> values) {
            addCriterion("a11 in", values, "a11");
            return (Criteria) this;
        }

        public Criteria andA11NotIn(List<String> values) {
            addCriterion("a11 not in", values, "a11");
            return (Criteria) this;
        }

        public Criteria andA11Between(String value1, String value2) {
            addCriterion("a11 between", value1, value2, "a11");
            return (Criteria) this;
        }

        public Criteria andA11NotBetween(String value1, String value2) {
            addCriterion("a11 not between", value1, value2, "a11");
            return (Criteria) this;
        }

        public Criteria andTrainnumsIsNull() {
            addCriterion("trainnums is null");
            return (Criteria) this;
        }

        public Criteria andTrainnumsIsNotNull() {
            addCriterion("trainnums is not null");
            return (Criteria) this;
        }

        public Criteria andTrainnumsEqualTo(String value) {
            addCriterion("trainnums =", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsNotEqualTo(String value) {
            addCriterion("trainnums <>", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsGreaterThan(String value) {
            addCriterion("trainnums >", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsGreaterThanOrEqualTo(String value) {
            addCriterion("trainnums >=", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsLessThan(String value) {
            addCriterion("trainnums <", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsLessThanOrEqualTo(String value) {
            addCriterion("trainnums <=", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsLike(String value) {
            addCriterion("trainnums like", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsNotLike(String value) {
            addCriterion("trainnums not like", value, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsIn(List<String> values) {
            addCriterion("trainnums in", values, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsNotIn(List<String> values) {
            addCriterion("trainnums not in", values, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsBetween(String value1, String value2) {
            addCriterion("trainnums between", value1, value2, "trainnums");
            return (Criteria) this;
        }

        public Criteria andTrainnumsNotBetween(String value1, String value2) {
            addCriterion("trainnums not between", value1, value2, "trainnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsIsNull() {
            addCriterion("studentnums is null");
            return (Criteria) this;
        }

        public Criteria andStudentnumsIsNotNull() {
            addCriterion("studentnums is not null");
            return (Criteria) this;
        }

        public Criteria andStudentnumsEqualTo(String value) {
            addCriterion("studentnums =", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsNotEqualTo(String value) {
            addCriterion("studentnums <>", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsGreaterThan(String value) {
            addCriterion("studentnums >", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsGreaterThanOrEqualTo(String value) {
            addCriterion("studentnums >=", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsLessThan(String value) {
            addCriterion("studentnums <", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsLessThanOrEqualTo(String value) {
            addCriterion("studentnums <=", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsLike(String value) {
            addCriterion("studentnums like", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsNotLike(String value) {
            addCriterion("studentnums not like", value, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsIn(List<String> values) {
            addCriterion("studentnums in", values, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsNotIn(List<String> values) {
            addCriterion("studentnums not in", values, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsBetween(String value1, String value2) {
            addCriterion("studentnums between", value1, value2, "studentnums");
            return (Criteria) this;
        }

        public Criteria andStudentnumsNotBetween(String value1, String value2) {
            addCriterion("studentnums not between", value1, value2, "studentnums");
            return (Criteria) this;
        }

        public Criteria andYyzzIsNull() {
            addCriterion("yyzz is null");
            return (Criteria) this;
        }

        public Criteria andYyzzIsNotNull() {
            addCriterion("yyzz is not null");
            return (Criteria) this;
        }

        public Criteria andYyzzEqualTo(String value) {
            addCriterion("yyzz =", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzNotEqualTo(String value) {
            addCriterion("yyzz <>", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzGreaterThan(String value) {
            addCriterion("yyzz >", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzGreaterThanOrEqualTo(String value) {
            addCriterion("yyzz >=", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzLessThan(String value) {
            addCriterion("yyzz <", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzLessThanOrEqualTo(String value) {
            addCriterion("yyzz <=", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzLike(String value) {
            addCriterion("yyzz like", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzNotLike(String value) {
            addCriterion("yyzz not like", value, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzIn(List<String> values) {
            addCriterion("yyzz in", values, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzNotIn(List<String> values) {
            addCriterion("yyzz not in", values, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzBetween(String value1, String value2) {
            addCriterion("yyzz between", value1, value2, "yyzz");
            return (Criteria) this;
        }

        public Criteria andYyzzNotBetween(String value1, String value2) {
            addCriterion("yyzz not between", value1, value2, "yyzz");
            return (Criteria) this;
        }

        public Criteria andSwdjzhIsNull() {
            addCriterion("swdjzh is null");
            return (Criteria) this;
        }

        public Criteria andSwdjzhIsNotNull() {
            addCriterion("swdjzh is not null");
            return (Criteria) this;
        }

        public Criteria andSwdjzhEqualTo(String value) {
            addCriterion("swdjzh =", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhNotEqualTo(String value) {
            addCriterion("swdjzh <>", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhGreaterThan(String value) {
            addCriterion("swdjzh >", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhGreaterThanOrEqualTo(String value) {
            addCriterion("swdjzh >=", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhLessThan(String value) {
            addCriterion("swdjzh <", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhLessThanOrEqualTo(String value) {
            addCriterion("swdjzh <=", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhLike(String value) {
            addCriterion("swdjzh like", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhNotLike(String value) {
            addCriterion("swdjzh not like", value, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhIn(List<String> values) {
            addCriterion("swdjzh in", values, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhNotIn(List<String> values) {
            addCriterion("swdjzh not in", values, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhBetween(String value1, String value2) {
            addCriterion("swdjzh between", value1, value2, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andSwdjzhNotBetween(String value1, String value2) {
            addCriterion("swdjzh not between", value1, value2, "swdjzh");
            return (Criteria) this;
        }

        public Criteria andTrainnameIsNull() {
            addCriterion("trainname is null");
            return (Criteria) this;
        }

        public Criteria andTrainnameIsNotNull() {
            addCriterion("trainname is not null");
            return (Criteria) this;
        }

        public Criteria andTrainnameEqualTo(String value) {
            addCriterion("trainname =", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameNotEqualTo(String value) {
            addCriterion("trainname <>", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameGreaterThan(String value) {
            addCriterion("trainname >", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameGreaterThanOrEqualTo(String value) {
            addCriterion("trainname >=", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameLessThan(String value) {
            addCriterion("trainname <", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameLessThanOrEqualTo(String value) {
            addCriterion("trainname <=", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameLike(String value) {
            addCriterion("trainname like", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameNotLike(String value) {
            addCriterion("trainname not like", value, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameIn(List<String> values) {
            addCriterion("trainname in", values, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameNotIn(List<String> values) {
            addCriterion("trainname not in", values, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameBetween(String value1, String value2) {
            addCriterion("trainname between", value1, value2, "trainname");
            return (Criteria) this;
        }

        public Criteria andTrainnameNotBetween(String value1, String value2) {
            addCriterion("trainname not between", value1, value2, "trainname");
            return (Criteria) this;
        }

        public Criteria andTraintelIsNull() {
            addCriterion("traintel is null");
            return (Criteria) this;
        }

        public Criteria andTraintelIsNotNull() {
            addCriterion("traintel is not null");
            return (Criteria) this;
        }

        public Criteria andTraintelEqualTo(String value) {
            addCriterion("traintel =", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelNotEqualTo(String value) {
            addCriterion("traintel <>", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelGreaterThan(String value) {
            addCriterion("traintel >", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelGreaterThanOrEqualTo(String value) {
            addCriterion("traintel >=", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelLessThan(String value) {
            addCriterion("traintel <", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelLessThanOrEqualTo(String value) {
            addCriterion("traintel <=", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelLike(String value) {
            addCriterion("traintel like", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelNotLike(String value) {
            addCriterion("traintel not like", value, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelIn(List<String> values) {
            addCriterion("traintel in", values, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelNotIn(List<String> values) {
            addCriterion("traintel not in", values, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelBetween(String value1, String value2) {
            addCriterion("traintel between", value1, value2, "traintel");
            return (Criteria) this;
        }

        public Criteria andTraintelNotBetween(String value1, String value2) {
            addCriterion("traintel not between", value1, value2, "traintel");
            return (Criteria) this;
        }

        public Criteria andIdnumsIsNull() {
            addCriterion("idnums is null");
            return (Criteria) this;
        }

        public Criteria andIdnumsIsNotNull() {
            addCriterion("idnums is not null");
            return (Criteria) this;
        }

        public Criteria andIdnumsEqualTo(String value) {
            addCriterion("idnums =", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsNotEqualTo(String value) {
            addCriterion("idnums <>", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsGreaterThan(String value) {
            addCriterion("idnums >", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsGreaterThanOrEqualTo(String value) {
            addCriterion("idnums >=", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsLessThan(String value) {
            addCriterion("idnums <", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsLessThanOrEqualTo(String value) {
            addCriterion("idnums <=", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsLike(String value) {
            addCriterion("idnums like", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsNotLike(String value) {
            addCriterion("idnums not like", value, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsIn(List<String> values) {
            addCriterion("idnums in", values, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsNotIn(List<String> values) {
            addCriterion("idnums not in", values, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsBetween(String value1, String value2) {
            addCriterion("idnums between", value1, value2, "idnums");
            return (Criteria) this;
        }

        public Criteria andIdnumsNotBetween(String value1, String value2) {
            addCriterion("idnums not between", value1, value2, "idnums");
            return (Criteria) this;
        }

        public Criteria andXydwIsNull() {
            addCriterion("xydw is null");
            return (Criteria) this;
        }

        public Criteria andXydwIsNotNull() {
            addCriterion("xydw is not null");
            return (Criteria) this;
        }

        public Criteria andXydwEqualTo(String value) {
            addCriterion("xydw =", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwNotEqualTo(String value) {
            addCriterion("xydw <>", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwGreaterThan(String value) {
            addCriterion("xydw >", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwGreaterThanOrEqualTo(String value) {
            addCriterion("xydw >=", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwLessThan(String value) {
            addCriterion("xydw <", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwLessThanOrEqualTo(String value) {
            addCriterion("xydw <=", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwLike(String value) {
            addCriterion("xydw like", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwNotLike(String value) {
            addCriterion("xydw not like", value, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwIn(List<String> values) {
            addCriterion("xydw in", values, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwNotIn(List<String> values) {
            addCriterion("xydw not in", values, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwBetween(String value1, String value2) {
            addCriterion("xydw between", value1, value2, "xydw");
            return (Criteria) this;
        }

        public Criteria andXydwNotBetween(String value1, String value2) {
            addCriterion("xydw not between", value1, value2, "xydw");
            return (Criteria) this;
        }

        public Criteria andTrainlevelIsNull() {
            addCriterion("trainlevel is null");
            return (Criteria) this;
        }

        public Criteria andTrainlevelIsNotNull() {
            addCriterion("trainlevel is not null");
            return (Criteria) this;
        }

        public Criteria andTrainlevelEqualTo(String value) {
            addCriterion("trainlevel =", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelNotEqualTo(String value) {
            addCriterion("trainlevel <>", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelGreaterThan(String value) {
            addCriterion("trainlevel >", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelGreaterThanOrEqualTo(String value) {
            addCriterion("trainlevel >=", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelLessThan(String value) {
            addCriterion("trainlevel <", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelLessThanOrEqualTo(String value) {
            addCriterion("trainlevel <=", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelLike(String value) {
            addCriterion("trainlevel like", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelNotLike(String value) {
            addCriterion("trainlevel not like", value, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelIn(List<String> values) {
            addCriterion("trainlevel in", values, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelNotIn(List<String> values) {
            addCriterion("trainlevel not in", values, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelBetween(String value1, String value2) {
            addCriterion("trainlevel between", value1, value2, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andTrainlevelNotBetween(String value1, String value2) {
            addCriterion("trainlevel not between", value1, value2, "trainlevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelIsNull() {
            addCriterion("refereelevel is null");
            return (Criteria) this;
        }

        public Criteria andRefereelevelIsNotNull() {
            addCriterion("refereelevel is not null");
            return (Criteria) this;
        }

        public Criteria andRefereelevelEqualTo(String value) {
            addCriterion("refereelevel =", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelNotEqualTo(String value) {
            addCriterion("refereelevel <>", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelGreaterThan(String value) {
            addCriterion("refereelevel >", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelGreaterThanOrEqualTo(String value) {
            addCriterion("refereelevel >=", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelLessThan(String value) {
            addCriterion("refereelevel <", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelLessThanOrEqualTo(String value) {
            addCriterion("refereelevel <=", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelLike(String value) {
            addCriterion("refereelevel like", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelNotLike(String value) {
            addCriterion("refereelevel not like", value, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelIn(List<String> values) {
            addCriterion("refereelevel in", values, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelNotIn(List<String> values) {
            addCriterion("refereelevel not in", values, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelBetween(String value1, String value2) {
            addCriterion("refereelevel between", value1, value2, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andRefereelevelNotBetween(String value1, String value2) {
            addCriterion("refereelevel not between", value1, value2, "refereelevel");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleIsNull() {
            addCriterion("train_people is null");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleIsNotNull() {
            addCriterion("train_people is not null");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleEqualTo(String value) {
            addCriterion("train_people =", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleNotEqualTo(String value) {
            addCriterion("train_people <>", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleGreaterThan(String value) {
            addCriterion("train_people >", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("train_people >=", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleLessThan(String value) {
            addCriterion("train_people <", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleLessThanOrEqualTo(String value) {
            addCriterion("train_people <=", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleLike(String value) {
            addCriterion("train_people like", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleNotLike(String value) {
            addCriterion("train_people not like", value, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleIn(List<String> values) {
            addCriterion("train_people in", values, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleNotIn(List<String> values) {
            addCriterion("train_people not in", values, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleBetween(String value1, String value2) {
            addCriterion("train_people between", value1, value2, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainPeopleNotBetween(String value1, String value2) {
            addCriterion("train_people not between", value1, value2, "trainPeople");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceIsNull() {
            addCriterion("train_space is null");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceIsNotNull() {
            addCriterion("train_space is not null");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceEqualTo(String value) {
            addCriterion("train_space =", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceNotEqualTo(String value) {
            addCriterion("train_space <>", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceGreaterThan(String value) {
            addCriterion("train_space >", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceGreaterThanOrEqualTo(String value) {
            addCriterion("train_space >=", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceLessThan(String value) {
            addCriterion("train_space <", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceLessThanOrEqualTo(String value) {
            addCriterion("train_space <=", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceLike(String value) {
            addCriterion("train_space like", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceNotLike(String value) {
            addCriterion("train_space not like", value, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceIn(List<String> values) {
            addCriterion("train_space in", values, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceNotIn(List<String> values) {
            addCriterion("train_space not in", values, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceBetween(String value1, String value2) {
            addCriterion("train_space between", value1, value2, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainSpaceNotBetween(String value1, String value2) {
            addCriterion("train_space not between", value1, value2, "trainSpace");
            return (Criteria) this;
        }

        public Criteria andTrainKindIsNull() {
            addCriterion("train_kind is null");
            return (Criteria) this;
        }

        public Criteria andTrainKindIsNotNull() {
            addCriterion("train_kind is not null");
            return (Criteria) this;
        }

        public Criteria andTrainKindEqualTo(String value) {
            addCriterion("train_kind =", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotEqualTo(String value) {
            addCriterion("train_kind <>", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindGreaterThan(String value) {
            addCriterion("train_kind >", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindGreaterThanOrEqualTo(String value) {
            addCriterion("train_kind >=", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLessThan(String value) {
            addCriterion("train_kind <", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLessThanOrEqualTo(String value) {
            addCriterion("train_kind <=", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindLike(String value) {
            addCriterion("train_kind like", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotLike(String value) {
            addCriterion("train_kind not like", value, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindIn(List<String> values) {
            addCriterion("train_kind in", values, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotIn(List<String> values) {
            addCriterion("train_kind not in", values, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindBetween(String value1, String value2) {
            addCriterion("train_kind between", value1, value2, "trainKind");
            return (Criteria) this;
        }

        public Criteria andTrainKindNotBetween(String value1, String value2) {
            addCriterion("train_kind not between", value1, value2, "trainKind");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNull() {
            addCriterion("question is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIsNotNull() {
            addCriterion("question is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionEqualTo(String value) {
            addCriterion("question =", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotEqualTo(String value) {
            addCriterion("question <>", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThan(String value) {
            addCriterion("question >", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("question >=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThan(String value) {
            addCriterion("question <", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLessThanOrEqualTo(String value) {
            addCriterion("question <=", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionLike(String value) {
            addCriterion("question like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotLike(String value) {
            addCriterion("question not like", value, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionIn(List<String> values) {
            addCriterion("question in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotIn(List<String> values) {
            addCriterion("question not in", values, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionBetween(String value1, String value2) {
            addCriterion("question between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andQuestionNotBetween(String value1, String value2) {
            addCriterion("question not between", value1, value2, "question");
            return (Criteria) this;
        }

        public Criteria andEnnameIsNull() {
            addCriterion("enname is null");
            return (Criteria) this;
        }

        public Criteria andEnnameIsNotNull() {
            addCriterion("enname is not null");
            return (Criteria) this;
        }

        public Criteria andEnnameEqualTo(String value) {
            addCriterion("enname =", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameNotEqualTo(String value) {
            addCriterion("enname <>", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameGreaterThan(String value) {
            addCriterion("enname >", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameGreaterThanOrEqualTo(String value) {
            addCriterion("enname >=", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameLessThan(String value) {
            addCriterion("enname <", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameLessThanOrEqualTo(String value) {
            addCriterion("enname <=", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameLike(String value) {
            addCriterion("enname like", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameNotLike(String value) {
            addCriterion("enname not like", value, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameIn(List<String> values) {
            addCriterion("enname in", values, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameNotIn(List<String> values) {
            addCriterion("enname not in", values, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameBetween(String value1, String value2) {
            addCriterion("enname between", value1, value2, "enname");
            return (Criteria) this;
        }

        public Criteria andEnnameNotBetween(String value1, String value2) {
            addCriterion("enname not between", value1, value2, "enname");
            return (Criteria) this;
        }

        public Criteria andUserMoneyIsNull() {
            addCriterion("user_money is null");
            return (Criteria) this;
        }

        public Criteria andUserMoneyIsNotNull() {
            addCriterion("user_money is not null");
            return (Criteria) this;
        }

        public Criteria andUserMoneyEqualTo(BigDecimal value) {
            addCriterion("user_money =", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyNotEqualTo(BigDecimal value) {
            addCriterion("user_money <>", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyGreaterThan(BigDecimal value) {
            addCriterion("user_money >", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("user_money >=", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyLessThan(BigDecimal value) {
            addCriterion("user_money <", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("user_money <=", value, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyIn(List<BigDecimal> values) {
            addCriterion("user_money in", values, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyNotIn(List<BigDecimal> values) {
            addCriterion("user_money not in", values, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("user_money between", value1, value2, "userMoney");
            return (Criteria) this;
        }

        public Criteria andUserMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("user_money not between", value1, value2, "userMoney");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerIsNull() {
            addCriterion("frozen_moner is null");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerIsNotNull() {
            addCriterion("frozen_moner is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerEqualTo(Long value) {
            addCriterion("frozen_moner =", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerNotEqualTo(Long value) {
            addCriterion("frozen_moner <>", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerGreaterThan(Long value) {
            addCriterion("frozen_moner >", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerGreaterThanOrEqualTo(Long value) {
            addCriterion("frozen_moner >=", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerLessThan(Long value) {
            addCriterion("frozen_moner <", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerLessThanOrEqualTo(Long value) {
            addCriterion("frozen_moner <=", value, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerIn(List<Long> values) {
            addCriterion("frozen_moner in", values, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerNotIn(List<Long> values) {
            addCriterion("frozen_moner not in", values, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerBetween(Long value1, Long value2) {
            addCriterion("frozen_moner between", value1, value2, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andFrozenMonerNotBetween(Long value1, Long value2) {
            addCriterion("frozen_moner not between", value1, value2, "frozenMoner");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintIsNull() {
            addCriterion("checkuser_tinyint is null");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintIsNotNull() {
            addCriterion("checkuser_tinyint is not null");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintEqualTo(Integer value) {
            addCriterion("checkuser_tinyint =", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintNotEqualTo(Integer value) {
            addCriterion("checkuser_tinyint <>", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintGreaterThan(Integer value) {
            addCriterion("checkuser_tinyint >", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkuser_tinyint >=", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintLessThan(Integer value) {
            addCriterion("checkuser_tinyint <", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintLessThanOrEqualTo(Integer value) {
            addCriterion("checkuser_tinyint <=", value, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintIn(List<Integer> values) {
            addCriterion("checkuser_tinyint in", values, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintNotIn(List<Integer> values) {
            addCriterion("checkuser_tinyint not in", values, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintBetween(Integer value1, Integer value2) {
            addCriterion("checkuser_tinyint between", value1, value2, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andCheckuserTinyintNotBetween(Integer value1, Integer value2) {
            addCriterion("checkuser_tinyint not between", value1, value2, "checkuserTinyint");
            return (Criteria) this;
        }

        public Criteria andLoginTypeIsNull() {
            addCriterion("login_type is null");
            return (Criteria) this;
        }

        public Criteria andLoginTypeIsNotNull() {
            addCriterion("login_type is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTypeEqualTo(Integer value) {
            addCriterion("login_type =", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeNotEqualTo(Integer value) {
            addCriterion("login_type <>", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeGreaterThan(Integer value) {
            addCriterion("login_type >", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_type >=", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeLessThan(Integer value) {
            addCriterion("login_type <", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeLessThanOrEqualTo(Integer value) {
            addCriterion("login_type <=", value, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeIn(List<Integer> values) {
            addCriterion("login_type in", values, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeNotIn(List<Integer> values) {
            addCriterion("login_type not in", values, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeBetween(Integer value1, Integer value2) {
            addCriterion("login_type between", value1, value2, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("login_type not between", value1, value2, "loginType");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkIsNull() {
            addCriterion("login_type_ck is null");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkIsNotNull() {
            addCriterion("login_type_ck is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkEqualTo(Integer value) {
            addCriterion("login_type_ck =", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkNotEqualTo(Integer value) {
            addCriterion("login_type_ck <>", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkGreaterThan(Integer value) {
            addCriterion("login_type_ck >", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_type_ck >=", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkLessThan(Integer value) {
            addCriterion("login_type_ck <", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkLessThanOrEqualTo(Integer value) {
            addCriterion("login_type_ck <=", value, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkIn(List<Integer> values) {
            addCriterion("login_type_ck in", values, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkNotIn(List<Integer> values) {
            addCriterion("login_type_ck not in", values, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkBetween(Integer value1, Integer value2) {
            addCriterion("login_type_ck between", value1, value2, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andLoginTypeCkNotBetween(Integer value1, Integer value2) {
            addCriterion("login_type_ck not between", value1, value2, "loginTypeCk");
            return (Criteria) this;
        }

        public Criteria andZwpjIsNull() {
            addCriterion("zwpj is null");
            return (Criteria) this;
        }

        public Criteria andZwpjIsNotNull() {
            addCriterion("zwpj is not null");
            return (Criteria) this;
        }

        public Criteria andZwpjEqualTo(String value) {
            addCriterion("zwpj =", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjNotEqualTo(String value) {
            addCriterion("zwpj <>", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjGreaterThan(String value) {
            addCriterion("zwpj >", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjGreaterThanOrEqualTo(String value) {
            addCriterion("zwpj >=", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjLessThan(String value) {
            addCriterion("zwpj <", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjLessThanOrEqualTo(String value) {
            addCriterion("zwpj <=", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjLike(String value) {
            addCriterion("zwpj like", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjNotLike(String value) {
            addCriterion("zwpj not like", value, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjIn(List<String> values) {
            addCriterion("zwpj in", values, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjNotIn(List<String> values) {
            addCriterion("zwpj not in", values, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjBetween(String value1, String value2) {
            addCriterion("zwpj between", value1, value2, "zwpj");
            return (Criteria) this;
        }

        public Criteria andZwpjNotBetween(String value1, String value2) {
            addCriterion("zwpj not between", value1, value2, "zwpj");
            return (Criteria) this;
        }

        public Criteria andKtqzIsNull() {
            addCriterion("ktqz is null");
            return (Criteria) this;
        }

        public Criteria andKtqzIsNotNull() {
            addCriterion("ktqz is not null");
            return (Criteria) this;
        }

        public Criteria andKtqzEqualTo(Byte value) {
            addCriterion("ktqz =", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzNotEqualTo(Byte value) {
            addCriterion("ktqz <>", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzGreaterThan(Byte value) {
            addCriterion("ktqz >", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzGreaterThanOrEqualTo(Byte value) {
            addCriterion("ktqz >=", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzLessThan(Byte value) {
            addCriterion("ktqz <", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzLessThanOrEqualTo(Byte value) {
            addCriterion("ktqz <=", value, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzIn(List<Byte> values) {
            addCriterion("ktqz in", values, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzNotIn(List<Byte> values) {
            addCriterion("ktqz not in", values, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzBetween(Byte value1, Byte value2) {
            addCriterion("ktqz between", value1, value2, "ktqz");
            return (Criteria) this;
        }

        public Criteria andKtqzNotBetween(Byte value1, Byte value2) {
            addCriterion("ktqz not between", value1, value2, "ktqz");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdIsNull() {
            addCriterion("daoguan_id is null");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdIsNotNull() {
            addCriterion("daoguan_id is not null");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdEqualTo(Integer value) {
            addCriterion("daoguan_id =", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdNotEqualTo(Integer value) {
            addCriterion("daoguan_id <>", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdGreaterThan(Integer value) {
            addCriterion("daoguan_id >", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("daoguan_id >=", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdLessThan(Integer value) {
            addCriterion("daoguan_id <", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdLessThanOrEqualTo(Integer value) {
            addCriterion("daoguan_id <=", value, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdIn(List<Integer> values) {
            addCriterion("daoguan_id in", values, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdNotIn(List<Integer> values) {
            addCriterion("daoguan_id not in", values, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdBetween(Integer value1, Integer value2) {
            addCriterion("daoguan_id between", value1, value2, "daoguanId");
            return (Criteria) this;
        }

        public Criteria andDaoguanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("daoguan_id not between", value1, value2, "daoguanId");
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

        public Criteria andPstrIsNull() {
            addCriterion("pstr is null");
            return (Criteria) this;
        }

        public Criteria andPstrIsNotNull() {
            addCriterion("pstr is not null");
            return (Criteria) this;
        }

        public Criteria andPstrEqualTo(String value) {
            addCriterion("pstr =", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrNotEqualTo(String value) {
            addCriterion("pstr <>", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrGreaterThan(String value) {
            addCriterion("pstr >", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrGreaterThanOrEqualTo(String value) {
            addCriterion("pstr >=", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrLessThan(String value) {
            addCriterion("pstr <", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrLessThanOrEqualTo(String value) {
            addCriterion("pstr <=", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrLike(String value) {
            addCriterion("pstr like", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrNotLike(String value) {
            addCriterion("pstr not like", value, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrIn(List<String> values) {
            addCriterion("pstr in", values, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrNotIn(List<String> values) {
            addCriterion("pstr not in", values, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrBetween(String value1, String value2) {
            addCriterion("pstr between", value1, value2, "pstr");
            return (Criteria) this;
        }

        public Criteria andPstrNotBetween(String value1, String value2) {
            addCriterion("pstr not between", value1, value2, "pstr");
            return (Criteria) this;
        }

        public Criteria andInfotypeIsNull() {
            addCriterion("infotype is null");
            return (Criteria) this;
        }

        public Criteria andInfotypeIsNotNull() {
            addCriterion("infotype is not null");
            return (Criteria) this;
        }

        public Criteria andInfotypeEqualTo(Integer value) {
            addCriterion("infotype =", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeNotEqualTo(Integer value) {
            addCriterion("infotype <>", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeGreaterThan(Integer value) {
            addCriterion("infotype >", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("infotype >=", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeLessThan(Integer value) {
            addCriterion("infotype <", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeLessThanOrEqualTo(Integer value) {
            addCriterion("infotype <=", value, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeIn(List<Integer> values) {
            addCriterion("infotype in", values, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeNotIn(List<Integer> values) {
            addCriterion("infotype not in", values, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeBetween(Integer value1, Integer value2) {
            addCriterion("infotype between", value1, value2, "infotype");
            return (Criteria) this;
        }

        public Criteria andInfotypeNotBetween(Integer value1, Integer value2) {
            addCriterion("infotype not between", value1, value2, "infotype");
            return (Criteria) this;
        }

        public Criteria andGldaoguanIsNull() {
            addCriterion("gldaoguan is null");
            return (Criteria) this;
        }

        public Criteria andGldaoguanIsNotNull() {
            addCriterion("gldaoguan is not null");
            return (Criteria) this;
        }

        public Criteria andGldaoguanEqualTo(String value) {
            addCriterion("gldaoguan =", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanNotEqualTo(String value) {
            addCriterion("gldaoguan <>", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanGreaterThan(String value) {
            addCriterion("gldaoguan >", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanGreaterThanOrEqualTo(String value) {
            addCriterion("gldaoguan >=", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanLessThan(String value) {
            addCriterion("gldaoguan <", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanLessThanOrEqualTo(String value) {
            addCriterion("gldaoguan <=", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanLike(String value) {
            addCriterion("gldaoguan like", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanNotLike(String value) {
            addCriterion("gldaoguan not like", value, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanIn(List<String> values) {
            addCriterion("gldaoguan in", values, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanNotIn(List<String> values) {
            addCriterion("gldaoguan not in", values, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanBetween(String value1, String value2) {
            addCriterion("gldaoguan between", value1, value2, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andGldaoguanNotBetween(String value1, String value2) {
            addCriterion("gldaoguan not between", value1, value2, "gldaoguan");
            return (Criteria) this;
        }

        public Criteria andKqFlIsNull() {
            addCriterion("kq_fl is null");
            return (Criteria) this;
        }

        public Criteria andKqFlIsNotNull() {
            addCriterion("kq_fl is not null");
            return (Criteria) this;
        }

        public Criteria andKqFlEqualTo(Integer value) {
            addCriterion("kq_fl =", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlNotEqualTo(Integer value) {
            addCriterion("kq_fl <>", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlGreaterThan(Integer value) {
            addCriterion("kq_fl >", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlGreaterThanOrEqualTo(Integer value) {
            addCriterion("kq_fl >=", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlLessThan(Integer value) {
            addCriterion("kq_fl <", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlLessThanOrEqualTo(Integer value) {
            addCriterion("kq_fl <=", value, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlIn(List<Integer> values) {
            addCriterion("kq_fl in", values, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlNotIn(List<Integer> values) {
            addCriterion("kq_fl not in", values, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlBetween(Integer value1, Integer value2) {
            addCriterion("kq_fl between", value1, value2, "kqFl");
            return (Criteria) this;
        }

        public Criteria andKqFlNotBetween(Integer value1, Integer value2) {
            addCriterion("kq_fl not between", value1, value2, "kqFl");
            return (Criteria) this;
        }

        public Criteria andFlMoneyIsNull() {
            addCriterion("fl_money is null");
            return (Criteria) this;
        }

        public Criteria andFlMoneyIsNotNull() {
            addCriterion("fl_money is not null");
            return (Criteria) this;
        }

        public Criteria andFlMoneyEqualTo(BigDecimal value) {
            addCriterion("fl_money =", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyNotEqualTo(BigDecimal value) {
            addCriterion("fl_money <>", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyGreaterThan(BigDecimal value) {
            addCriterion("fl_money >", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fl_money >=", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyLessThan(BigDecimal value) {
            addCriterion("fl_money <", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fl_money <=", value, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyIn(List<BigDecimal> values) {
            addCriterion("fl_money in", values, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyNotIn(List<BigDecimal> values) {
            addCriterion("fl_money not in", values, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fl_money between", value1, value2, "flMoney");
            return (Criteria) this;
        }

        public Criteria andFlMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fl_money not between", value1, value2, "flMoney");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNull() {
            addCriterion("bank_card is null");
            return (Criteria) this;
        }

        public Criteria andBankCardIsNotNull() {
            addCriterion("bank_card is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardEqualTo(String value) {
            addCriterion("bank_card =", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotEqualTo(String value) {
            addCriterion("bank_card <>", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThan(String value) {
            addCriterion("bank_card >", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card >=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThan(String value) {
            addCriterion("bank_card <", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLessThanOrEqualTo(String value) {
            addCriterion("bank_card <=", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardLike(String value) {
            addCriterion("bank_card like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotLike(String value) {
            addCriterion("bank_card not like", value, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardIn(List<String> values) {
            addCriterion("bank_card in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotIn(List<String> values) {
            addCriterion("bank_card not in", values, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardBetween(String value1, String value2) {
            addCriterion("bank_card between", value1, value2, "bankCard");
            return (Criteria) this;
        }

        public Criteria andBankCardNotBetween(String value1, String value2) {
            addCriterion("bank_card not between", value1, value2, "bankCard");
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