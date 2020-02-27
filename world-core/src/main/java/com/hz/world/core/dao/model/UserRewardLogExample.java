package com.hz.world.core.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRewardLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    public UserRewardLogExample() {
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
     * @param mysqlOffset 指定返回记录行的偏移量<br> mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlOffset(Integer mysqlOffset) {
        this.mysqlOffset = mysqlOffset;
    }

    public Integer getMysqlOffset() {
        return mysqlOffset;
    }

    /**
     * @param mysqlLength 指定返回记录行的最大数目<br> mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15
     */
    public void setMysqlLength(Integer mysqlLength) {
        this.mysqlLength = mysqlLength;
    }

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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNull() {
            addCriterion("reward_type is null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNotNull() {
            addCriterion("reward_type is not null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeEqualTo(Integer value) {
            addCriterion("reward_type =", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotEqualTo(Integer value) {
            addCriterion("reward_type <>", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThan(Integer value) {
            addCriterion("reward_type >", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_type >=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThan(Integer value) {
            addCriterion("reward_type <", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reward_type <=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIn(List<Integer> values) {
            addCriterion("reward_type in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotIn(List<Integer> values) {
            addCriterion("reward_type not in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeBetween(Integer value1, Integer value2) {
            addCriterion("reward_type between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_type not between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIsNull() {
            addCriterion("related_id is null");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIsNotNull() {
            addCriterion("related_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedIdEqualTo(Integer value) {
            addCriterion("related_id =", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotEqualTo(Integer value) {
            addCriterion("related_id <>", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdGreaterThan(Integer value) {
            addCriterion("related_id >", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("related_id >=", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdLessThan(Integer value) {
            addCriterion("related_id <", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdLessThanOrEqualTo(Integer value) {
            addCriterion("related_id <=", value, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdIn(List<Integer> values) {
            addCriterion("related_id in", values, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotIn(List<Integer> values) {
            addCriterion("related_id not in", values, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdBetween(Integer value1, Integer value2) {
            addCriterion("related_id between", value1, value2, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRelatedIdNotBetween(Integer value1, Integer value2) {
            addCriterion("related_id not between", value1, value2, "relatedId");
            return (Criteria) this;
        }

        public Criteria andRewardCountIsNull() {
            addCriterion("reward_count is null");
            return (Criteria) this;
        }

        public Criteria andRewardCountIsNotNull() {
            addCriterion("reward_count is not null");
            return (Criteria) this;
        }

        public Criteria andRewardCountEqualTo(String value) {
            addCriterion("reward_count =", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountNotEqualTo(String value) {
            addCriterion("reward_count <>", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountGreaterThan(String value) {
            addCriterion("reward_count >", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountGreaterThanOrEqualTo(String value) {
            addCriterion("reward_count >=", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountLessThan(String value) {
            addCriterion("reward_count <", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountLessThanOrEqualTo(String value) {
            addCriterion("reward_count <=", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountLike(String value) {
            addCriterion("reward_count like", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountNotLike(String value) {
            addCriterion("reward_count not like", value, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountIn(List<String> values) {
            addCriterion("reward_count in", values, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountNotIn(List<String> values) {
            addCriterion("reward_count not in", values, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountBetween(String value1, String value2) {
            addCriterion("reward_count between", value1, value2, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardCountNotBetween(String value1, String value2) {
            addCriterion("reward_count not between", value1, value2, "rewardCount");
            return (Criteria) this;
        }

        public Criteria andRewardMessageIsNull() {
            addCriterion("reward_message is null");
            return (Criteria) this;
        }

        public Criteria andRewardMessageIsNotNull() {
            addCriterion("reward_message is not null");
            return (Criteria) this;
        }

        public Criteria andRewardMessageEqualTo(String value) {
            addCriterion("reward_message =", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageNotEqualTo(String value) {
            addCriterion("reward_message <>", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageGreaterThan(String value) {
            addCriterion("reward_message >", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageGreaterThanOrEqualTo(String value) {
            addCriterion("reward_message >=", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageLessThan(String value) {
            addCriterion("reward_message <", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageLessThanOrEqualTo(String value) {
            addCriterion("reward_message <=", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageLike(String value) {
            addCriterion("reward_message like", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageNotLike(String value) {
            addCriterion("reward_message not like", value, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageIn(List<String> values) {
            addCriterion("reward_message in", values, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageNotIn(List<String> values) {
            addCriterion("reward_message not in", values, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageBetween(String value1, String value2) {
            addCriterion("reward_message between", value1, value2, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andRewardMessageNotBetween(String value1, String value2) {
            addCriterion("reward_message not between", value1, value2, "rewardMessage");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andRewardCountLikeInsensitive(String value) {
            addCriterion("upper(reward_count) like", value.toUpperCase(), "rewardCount");
            return this;
        }

        public Criteria andRewardMessageLikeInsensitive(String value) {
            addCriterion("upper(reward_message) like", value.toUpperCase(), "rewardMessage");
            return this;
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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}