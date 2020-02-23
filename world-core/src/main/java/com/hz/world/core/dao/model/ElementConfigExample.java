package com.hz.world.core.dao.model;

import java.util.ArrayList;
import java.util.List;

public class ElementConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer mysqlOffset;

    private Integer mysqlLength;

    public ElementConfigExample() {
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

        public Criteria andCostGrowthIsNull() {
            addCriterion("cost_growth is null");
            return (Criteria) this;
        }

        public Criteria andCostGrowthIsNotNull() {
            addCriterion("cost_growth is not null");
            return (Criteria) this;
        }

        public Criteria andCostGrowthEqualTo(String value) {
            addCriterion("cost_growth =", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthNotEqualTo(String value) {
            addCriterion("cost_growth <>", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthGreaterThan(String value) {
            addCriterion("cost_growth >", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthGreaterThanOrEqualTo(String value) {
            addCriterion("cost_growth >=", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthLessThan(String value) {
            addCriterion("cost_growth <", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthLessThanOrEqualTo(String value) {
            addCriterion("cost_growth <=", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthLike(String value) {
            addCriterion("cost_growth like", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthNotLike(String value) {
            addCriterion("cost_growth not like", value, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthIn(List<String> values) {
            addCriterion("cost_growth in", values, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthNotIn(List<String> values) {
            addCriterion("cost_growth not in", values, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthBetween(String value1, String value2) {
            addCriterion("cost_growth between", value1, value2, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andCostGrowthNotBetween(String value1, String value2) {
            addCriterion("cost_growth not between", value1, value2, "costGrowth");
            return (Criteria) this;
        }

        public Criteria andInitialOutputIsNull() {
            addCriterion("initial_output is null");
            return (Criteria) this;
        }

        public Criteria andInitialOutputIsNotNull() {
            addCriterion("initial_output is not null");
            return (Criteria) this;
        }

        public Criteria andInitialOutputEqualTo(String value) {
            addCriterion("initial_output =", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputNotEqualTo(String value) {
            addCriterion("initial_output <>", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputGreaterThan(String value) {
            addCriterion("initial_output >", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputGreaterThanOrEqualTo(String value) {
            addCriterion("initial_output >=", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputLessThan(String value) {
            addCriterion("initial_output <", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputLessThanOrEqualTo(String value) {
            addCriterion("initial_output <=", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputLike(String value) {
            addCriterion("initial_output like", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputNotLike(String value) {
            addCriterion("initial_output not like", value, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputIn(List<String> values) {
            addCriterion("initial_output in", values, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputNotIn(List<String> values) {
            addCriterion("initial_output not in", values, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputBetween(String value1, String value2) {
            addCriterion("initial_output between", value1, value2, "initialOutput");
            return (Criteria) this;
        }

        public Criteria andInitialOutputNotBetween(String value1, String value2) {
            addCriterion("initial_output not between", value1, value2, "initialOutput");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return this;
        }

        public Criteria andInitialCostLikeInsensitive(String value) {
            addCriterion("upper(initial_cost) like", value.toUpperCase(), "initialCost");
            return this;
        }

        public Criteria andCostGrowthLikeInsensitive(String value) {
            addCriterion("upper(cost_growth) like", value.toUpperCase(), "costGrowth");
            return this;
        }

        public Criteria andInitialOutputLikeInsensitive(String value) {
            addCriterion("upper(initial_output) like", value.toUpperCase(), "initialOutput");
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