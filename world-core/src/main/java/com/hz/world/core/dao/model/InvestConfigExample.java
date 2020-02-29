package com.hz.world.core.dao.model;

import java.util.ArrayList;
import java.util.List;

public class InvestConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    public InvestConfigExample() {
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

        public Criteria andElementIsNull() {
            addCriterion("element is null");
            return (Criteria) this;
        }

        public Criteria andElementIsNotNull() {
            addCriterion("element is not null");
            return (Criteria) this;
        }

        public Criteria andElementEqualTo(Integer value) {
            addCriterion("element =", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotEqualTo(Integer value) {
            addCriterion("element <>", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementGreaterThan(Integer value) {
            addCriterion("element >", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementGreaterThanOrEqualTo(Integer value) {
            addCriterion("element >=", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementLessThan(Integer value) {
            addCriterion("element <", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementLessThanOrEqualTo(Integer value) {
            addCriterion("element <=", value, "element");
            return (Criteria) this;
        }

        public Criteria andElementIn(List<Integer> values) {
            addCriterion("element in", values, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotIn(List<Integer> values) {
            addCriterion("element not in", values, "element");
            return (Criteria) this;
        }

        public Criteria andElementBetween(Integer value1, Integer value2) {
            addCriterion("element between", value1, value2, "element");
            return (Criteria) this;
        }

        public Criteria andElementNotBetween(Integer value1, Integer value2) {
            addCriterion("element not between", value1, value2, "element");
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

        public Criteria andParamIsNull() {
            addCriterion("param is null");
            return (Criteria) this;
        }

        public Criteria andParamIsNotNull() {
            addCriterion("param is not null");
            return (Criteria) this;
        }

        public Criteria andParamEqualTo(Integer value) {
            addCriterion("param =", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotEqualTo(Integer value) {
            addCriterion("param <>", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThan(Integer value) {
            addCriterion("param >", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThanOrEqualTo(Integer value) {
            addCriterion("param >=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThan(Integer value) {
            addCriterion("param <", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThanOrEqualTo(Integer value) {
            addCriterion("param <=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamIn(List<Integer> values) {
            addCriterion("param in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotIn(List<Integer> values) {
            addCriterion("param not in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamBetween(Integer value1, Integer value2) {
            addCriterion("param between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotBetween(Integer value1, Integer value2) {
            addCriterion("param not between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andInitialCostIsNull() {
            addCriterion("initial_cost is null");
            return (Criteria) this;
        }

        public Criteria andInitialCostIsNotNull() {
            addCriterion("initial_cost is not null");
            return (Criteria) this;
        }

        public Criteria andInitialCostEqualTo(String value) {
            addCriterion("initial_cost =", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostNotEqualTo(String value) {
            addCriterion("initial_cost <>", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostGreaterThan(String value) {
            addCriterion("initial_cost >", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostGreaterThanOrEqualTo(String value) {
            addCriterion("initial_cost >=", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostLessThan(String value) {
            addCriterion("initial_cost <", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostLessThanOrEqualTo(String value) {
            addCriterion("initial_cost <=", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostLike(String value) {
            addCriterion("initial_cost like", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostNotLike(String value) {
            addCriterion("initial_cost not like", value, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostIn(List<String> values) {
            addCriterion("initial_cost in", values, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostNotIn(List<String> values) {
            addCriterion("initial_cost not in", values, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostBetween(String value1, String value2) {
            addCriterion("initial_cost between", value1, value2, "initialCost");
            return (Criteria) this;
        }

        public Criteria andInitialCostNotBetween(String value1, String value2) {
            addCriterion("initial_cost not between", value1, value2, "initialCost");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andInitialCostLikeInsensitive(String value) {
            addCriterion("upper(initial_cost) like", value.toUpperCase(), "initialCost");
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