package com.lc.zy.ball.domain.oa.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* CrmUserCriteria 条件查询类.
* 
* @author liangc [cc14514@icloud.com]
* @version v1.0
* @copy pet
* @date 2015-09-18 17:04:57
*/
public class CrmUserCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer mysqlOffset;

    protected Integer mysqlLength;

    public CrmUserCriteria() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
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

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(String value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(String value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(String value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(String value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(String value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLike(String value) {
            addCriterion("CUST_ID like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotLike(String value) {
            addCriterion("CUST_ID not like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<String> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<String> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(String value1, String value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(String value1, String value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NICKNAME =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NICKNAME <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NICKNAME >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NICKNAME >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NICKNAME <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NICKNAME <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NICKNAME like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NICKNAME not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NICKNAME in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NICKNAME not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NICKNAME between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NICKNAME not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("REALNAME is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("REALNAME is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("REALNAME =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("REALNAME <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("REALNAME >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("REALNAME >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("REALNAME <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("REALNAME <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("REALNAME like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("REALNAME not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("REALNAME in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("REALNAME not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("REALNAME between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("REALNAME not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("LOGIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("LOGIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("LOGIN_NAME =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("LOGIN_NAME <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("LOGIN_NAME >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("LOGIN_NAME <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("LOGIN_NAME like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("LOGIN_NAME not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("LOGIN_NAME in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("LOGIN_NAME not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PASSWORD in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PASSWORD not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("SALT is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("SALT is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("SALT =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("SALT <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("SALT >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("SALT >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("SALT <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("SALT <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("SALT like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("SALT not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("SALT in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("SALT not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("SALT between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("SALT not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionIsNull() {
            addCriterion("PWD_QUESTION is null");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionIsNotNull() {
            addCriterion("PWD_QUESTION is not null");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionEqualTo(String value) {
            addCriterion("PWD_QUESTION =", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionNotEqualTo(String value) {
            addCriterion("PWD_QUESTION <>", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionGreaterThan(String value) {
            addCriterion("PWD_QUESTION >", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("PWD_QUESTION >=", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionLessThan(String value) {
            addCriterion("PWD_QUESTION <", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionLessThanOrEqualTo(String value) {
            addCriterion("PWD_QUESTION <=", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionLike(String value) {
            addCriterion("PWD_QUESTION like", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionNotLike(String value) {
            addCriterion("PWD_QUESTION not like", value, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionIn(List<String> values) {
            addCriterion("PWD_QUESTION in", values, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionNotIn(List<String> values) {
            addCriterion("PWD_QUESTION not in", values, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionBetween(String value1, String value2) {
            addCriterion("PWD_QUESTION between", value1, value2, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdQuestionNotBetween(String value1, String value2) {
            addCriterion("PWD_QUESTION not between", value1, value2, "pwdQuestion");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerIsNull() {
            addCriterion("PWD_ANSWER is null");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerIsNotNull() {
            addCriterion("PWD_ANSWER is not null");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerEqualTo(String value) {
            addCriterion("PWD_ANSWER =", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerNotEqualTo(String value) {
            addCriterion("PWD_ANSWER <>", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerGreaterThan(String value) {
            addCriterion("PWD_ANSWER >", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("PWD_ANSWER >=", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerLessThan(String value) {
            addCriterion("PWD_ANSWER <", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerLessThanOrEqualTo(String value) {
            addCriterion("PWD_ANSWER <=", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerLike(String value) {
            addCriterion("PWD_ANSWER like", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerNotLike(String value) {
            addCriterion("PWD_ANSWER not like", value, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerIn(List<String> values) {
            addCriterion("PWD_ANSWER in", values, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerNotIn(List<String> values) {
            addCriterion("PWD_ANSWER not in", values, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerBetween(String value1, String value2) {
            addCriterion("PWD_ANSWER between", value1, value2, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andPwdAnswerNotBetween(String value1, String value2) {
            addCriterion("PWD_ANSWER not between", value1, value2, "pwdAnswer");
            return (Criteria) this;
        }

        public Criteria andSecEmailIsNull() {
            addCriterion("SEC_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andSecEmailIsNotNull() {
            addCriterion("SEC_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSecEmailEqualTo(String value) {
            addCriterion("SEC_EMAIL =", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailNotEqualTo(String value) {
            addCriterion("SEC_EMAIL <>", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailGreaterThan(String value) {
            addCriterion("SEC_EMAIL >", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailGreaterThanOrEqualTo(String value) {
            addCriterion("SEC_EMAIL >=", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailLessThan(String value) {
            addCriterion("SEC_EMAIL <", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailLessThanOrEqualTo(String value) {
            addCriterion("SEC_EMAIL <=", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailLike(String value) {
            addCriterion("SEC_EMAIL like", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailNotLike(String value) {
            addCriterion("SEC_EMAIL not like", value, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailIn(List<String> values) {
            addCriterion("SEC_EMAIL in", values, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailNotIn(List<String> values) {
            addCriterion("SEC_EMAIL not in", values, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailBetween(String value1, String value2) {
            addCriterion("SEC_EMAIL between", value1, value2, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecEmailNotBetween(String value1, String value2) {
            addCriterion("SEC_EMAIL not between", value1, value2, "secEmail");
            return (Criteria) this;
        }

        public Criteria andSecMobileIsNull() {
            addCriterion("SEC_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andSecMobileIsNotNull() {
            addCriterion("SEC_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andSecMobileEqualTo(String value) {
            addCriterion("SEC_MOBILE =", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileNotEqualTo(String value) {
            addCriterion("SEC_MOBILE <>", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileGreaterThan(String value) {
            addCriterion("SEC_MOBILE >", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileGreaterThanOrEqualTo(String value) {
            addCriterion("SEC_MOBILE >=", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileLessThan(String value) {
            addCriterion("SEC_MOBILE <", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileLessThanOrEqualTo(String value) {
            addCriterion("SEC_MOBILE <=", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileLike(String value) {
            addCriterion("SEC_MOBILE like", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileNotLike(String value) {
            addCriterion("SEC_MOBILE not like", value, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileIn(List<String> values) {
            addCriterion("SEC_MOBILE in", values, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileNotIn(List<String> values) {
            addCriterion("SEC_MOBILE not in", values, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileBetween(String value1, String value2) {
            addCriterion("SEC_MOBILE between", value1, value2, "secMobile");
            return (Criteria) this;
        }

        public Criteria andSecMobileNotBetween(String value1, String value2) {
            addCriterion("SEC_MOBILE not between", value1, value2, "secMobile");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNull() {
            addCriterion("LAST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("LAST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("LAST_TIME =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("LAST_TIME <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("LAST_TIME >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_TIME >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("LAST_TIME <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_TIME <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("LAST_TIME in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("LAST_TIME not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_TIME between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_TIME not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastIpIsNull() {
            addCriterion("LAST_IP is null");
            return (Criteria) this;
        }

        public Criteria andLastIpIsNotNull() {
            addCriterion("LAST_IP is not null");
            return (Criteria) this;
        }

        public Criteria andLastIpEqualTo(String value) {
            addCriterion("LAST_IP =", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotEqualTo(String value) {
            addCriterion("LAST_IP <>", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpGreaterThan(String value) {
            addCriterion("LAST_IP >", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_IP >=", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLessThan(String value) {
            addCriterion("LAST_IP <", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLessThanOrEqualTo(String value) {
            addCriterion("LAST_IP <=", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLike(String value) {
            addCriterion("LAST_IP like", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotLike(String value) {
            addCriterion("LAST_IP not like", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpIn(List<String> values) {
            addCriterion("LAST_IP in", values, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotIn(List<String> values) {
            addCriterion("LAST_IP not in", values, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpBetween(String value1, String value2) {
            addCriterion("LAST_IP between", value1, value2, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotBetween(String value1, String value2) {
            addCriterion("LAST_IP not between", value1, value2, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIsNull() {
            addCriterion("LATEST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIsNotNull() {
            addCriterion("LATEST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLatestTimeEqualTo(Date value) {
            addCriterion("LATEST_TIME =", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotEqualTo(Date value) {
            addCriterion("LATEST_TIME <>", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeGreaterThan(Date value) {
            addCriterion("LATEST_TIME >", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LATEST_TIME >=", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeLessThan(Date value) {
            addCriterion("LATEST_TIME <", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeLessThanOrEqualTo(Date value) {
            addCriterion("LATEST_TIME <=", value, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeIn(List<Date> values) {
            addCriterion("LATEST_TIME in", values, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotIn(List<Date> values) {
            addCriterion("LATEST_TIME not in", values, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeBetween(Date value1, Date value2) {
            addCriterion("LATEST_TIME between", value1, value2, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestTimeNotBetween(Date value1, Date value2) {
            addCriterion("LATEST_TIME not between", value1, value2, "latestTime");
            return (Criteria) this;
        }

        public Criteria andLatestIpIsNull() {
            addCriterion("LATEST_IP is null");
            return (Criteria) this;
        }

        public Criteria andLatestIpIsNotNull() {
            addCriterion("LATEST_IP is not null");
            return (Criteria) this;
        }

        public Criteria andLatestIpEqualTo(String value) {
            addCriterion("LATEST_IP =", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpNotEqualTo(String value) {
            addCriterion("LATEST_IP <>", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpGreaterThan(String value) {
            addCriterion("LATEST_IP >", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpGreaterThanOrEqualTo(String value) {
            addCriterion("LATEST_IP >=", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpLessThan(String value) {
            addCriterion("LATEST_IP <", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpLessThanOrEqualTo(String value) {
            addCriterion("LATEST_IP <=", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpLike(String value) {
            addCriterion("LATEST_IP like", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpNotLike(String value) {
            addCriterion("LATEST_IP not like", value, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpIn(List<String> values) {
            addCriterion("LATEST_IP in", values, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpNotIn(List<String> values) {
            addCriterion("LATEST_IP not in", values, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpBetween(String value1, String value2) {
            addCriterion("LATEST_IP between", value1, value2, "latestIp");
            return (Criteria) this;
        }

        public Criteria andLatestIpNotBetween(String value1, String value2) {
            addCriterion("LATEST_IP not between", value1, value2, "latestIp");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCbIsNull() {
            addCriterion("CB is null");
            return (Criteria) this;
        }

        public Criteria andCbIsNotNull() {
            addCriterion("CB is not null");
            return (Criteria) this;
        }

        public Criteria andCbEqualTo(String value) {
            addCriterion("CB =", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotEqualTo(String value) {
            addCriterion("CB <>", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbGreaterThan(String value) {
            addCriterion("CB >", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbGreaterThanOrEqualTo(String value) {
            addCriterion("CB >=", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLessThan(String value) {
            addCriterion("CB <", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLessThanOrEqualTo(String value) {
            addCriterion("CB <=", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbLike(String value) {
            addCriterion("CB like", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotLike(String value) {
            addCriterion("CB not like", value, "cb");
            return (Criteria) this;
        }

        public Criteria andCbIn(List<String> values) {
            addCriterion("CB in", values, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotIn(List<String> values) {
            addCriterion("CB not in", values, "cb");
            return (Criteria) this;
        }

        public Criteria andCbBetween(String value1, String value2) {
            addCriterion("CB between", value1, value2, "cb");
            return (Criteria) this;
        }

        public Criteria andCbNotBetween(String value1, String value2) {
            addCriterion("CB not between", value1, value2, "cb");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Integer value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Integer value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Integer value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Integer value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Integer value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Integer> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Integer> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
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