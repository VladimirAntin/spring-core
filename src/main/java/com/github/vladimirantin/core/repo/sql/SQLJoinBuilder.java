package com.github.vladimirantin.core.repo.sql;

import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 23.11.2019
 * Time: 22:37
 */
public final class SQLJoinBuilder extends LinkedHashSet<String> {

    private enum JoinType{RIGHT, LEFT}

    public SQLJoinBuilder() {
        super();
    }

    public SQLJoinBuilder lefJoin(String path, String alias) {
        join(path, alias, JoinType.LEFT);
        return this;
    }

    public SQLJoinBuilder rightJoin(String path, String alias) {
        join(path, alias, JoinType.RIGHT);
        return this;
    }

    public SQLJoinBuilder lefJoinFetch(String path, String alias) {
        joinFetch(path, alias, JoinType.LEFT);
        return this;
    }

    public SQLJoinBuilder rightJoinFetch(String path, String alias) {
        joinFetch(path, alias, JoinType.RIGHT);
        return this;
    }

    private void join(String path, String alias, JoinType join){
        this.add(String.format("%s join %s %s", join, path, alias));
    }

    private void joinFetch(String path, String alias, JoinType join){
        this.add(String.format("%s join fetch %s %s", join, path, alias));
    }

    public String getJoinClause() {
        return String.join(" ", this);
    }


}
