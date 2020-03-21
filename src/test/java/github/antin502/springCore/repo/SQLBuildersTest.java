package github.antin502.springCore.repo;

import com.github.antin502.core.repo.sql.SQLJoinBuilder;
import com.github.antin502.core.repo.sql.SQLQueryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 23.11.2019
 * Time: 23:03
 */
public class SQLBuildersTest {

    @Test
    public void sqlQueryBuilderWithParams() {
        SQLQueryBuilder queryBuilder = new SQLQueryBuilder();
        queryBuilder
                .startingWith("test.randomString", "random")
                .and()
                .contains("test.randomString2", "random contains string")
                .or()
                .lessOrEquals("test.testLong", 100L)
                .and()
                .between("test.testDate", LocalDate.of(2019,1,1), LocalDate.of(2019,2,2))
                .and()
                .in("test.collectionValues", "test1","test2", "test3");
        System.out.println(queryBuilder.getWhereQuery());
        for (String key : queryBuilder.keySet()) {
            System.out.println(String.format("%s=%s", key, queryBuilder.get(key)));
        }
        Assert.assertTrue(queryBuilder.getWhereQuery().contains("where"));
        Assert.assertTrue(queryBuilder.values().size()==6); //dates
    }

    @Test
    public void sqlQueryBuilderWithoutParams() {
        SQLQueryBuilder queryBuilder = new SQLQueryBuilder();
        Assert.assertFalse(queryBuilder.getWhereQuery().contains("where"));
        Assert.assertTrue(queryBuilder.values().size()==0);
    }

    @Test
    public void leftAndRightJoin() {
        SQLJoinBuilder joinBuilder = new SQLJoinBuilder();
        joinBuilder
                .lefJoin("test.randomLeft", "l")
                .lefJoin("test.randomLeft", "l")
                .rightJoin("test.randomRight", "r")
                .rightJoin("test.randomRight", "r")
                .lefJoinFetch("l.fetchRandom", "lf")
                .lefJoinFetch("l.fetchRandom", "lf")
                .rightJoinFetch("r.fetchRandom", "rf")
                .rightJoinFetch("r.fetchRandom", "rf");
        System.out.println(joinBuilder.getJoinClause());
        Assert.assertTrue(joinBuilder.getJoinClause().contains("LEFT") && joinBuilder.getJoinClause().contains("RIGHT") && joinBuilder.getJoinClause().contains("fetch"));
        Assert.assertTrue(joinBuilder.size()==4); //remove duplicates
    }
}
