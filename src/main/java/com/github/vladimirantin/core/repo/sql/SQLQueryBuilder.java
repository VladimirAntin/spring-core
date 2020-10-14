package com.github.vladimirantin.core.repo.sql;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 23.11.2019
 * Time: 22:08
 */
public final class SQLQueryBuilder extends LinkedHashMap<String, Object> {

    private StringBuilder whereClause;

    public SQLQueryBuilder() {
        super();
        whereClause = new StringBuilder();
    }

    private SQLQueryBuilder standardQuery(String dbValue, Object value, String query) {
        String key = String.format("__%s",this.values().size() + 1);
        this.whereClause.append(String.format(query, dbValue, key));
        this.put(key, value);
        return this;
    }

    public SQLQueryBuilder startingWith(String dbValue, String value){
        return this.standardQuery(dbValue, value+"%", "%s like :%s");
    }

    public SQLQueryBuilder endingWith(String dbValue, String value){
        return this.standardQuery(dbValue, "%"+value, "%s like :%s");
    }

    public SQLQueryBuilder contains(String dbValue, String value){
        return this.standardQuery(dbValue, "%"+value+"%", "%s like :%s");
    }

    public SQLQueryBuilder equal(String dbValue, Object value){
        return this.standardQuery(dbValue, value, "%s=:%s");
    }

    public SQLQueryBuilder moreThan(String dbValue, Object value){
        return this.standardQuery(dbValue, value, "%s>:%s");
    }

    public SQLQueryBuilder lessThan(String dbValue, Object value){
        return this.standardQuery(dbValue, value, "%s<:%s");
    }

    public SQLQueryBuilder moreOrEquals(String dbValue, Object value){
        return this.standardQuery(dbValue, value, "%s>=:%s");
    }

    public SQLQueryBuilder lessOrEquals(String dbValue, Object value){
        return this.standardQuery(dbValue, value, "%s<=:%s");
    }

    public SQLQueryBuilder in(String dbValue, Collection<Object> values){
        return this.standardQuery(dbValue, values, "%s in :%s");
    }

    public SQLQueryBuilder notIn(String dbValue, Collection<Object> values){
        return this.standardQuery(dbValue, values, "%s not in :%s");
    }

    public SQLQueryBuilder in(String dbValue, Object... values){
        return this.in(dbValue, Arrays.asList(values));
    }

    public SQLQueryBuilder notIn(String dbValue, Object... values){
        return this.notIn(dbValue, Arrays.asList(values));
    }

    public SQLQueryBuilder between(String dbValue, Object start, Object end){
        String key = String.format("__%s",this.values().size() + 1);
        String key2 = String.format("__%s",this.values().size() + 2);
        this.whereClause.append(String.format("%s between :%s and :%s", dbValue, key, key2));
        this.put(key, start);
        this.put(key2, end);
        return this;
    }

    public SQLQueryBuilder and() {
        this.whereClause.append(" and ");
        return this;
    }

    public SQLQueryBuilder or() {
        this.whereClause.append(" or ");
        return this;
    }

    public String getWhereQuery() {
        String retVal = this.whereClause.toString().trim();
        if (retVal.endsWith("or") || retVal.endsWith("and")) {
            retVal = retVal.substring(0, retVal.length() - 3).trim();
        }
        if (retVal.isEmpty()) {
            return "";
        } else {
            return String.format("where %s", retVal);
        }
    }

}
