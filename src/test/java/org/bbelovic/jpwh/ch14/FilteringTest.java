package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.PersistenceTest;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class FilteringTest extends PersistenceTest {
    @Test
    public void
    testSimpleFilter() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Filter filter = entityManager.unwrap(Session.class)
                .enableFilter("coniferBranch");
        filter.setParameter("conifer", 0);
        List resultList = entityManager.createQuery("select b from Branch b")
                .getResultList();
        Assert.assertEquals(8, resultList.size());

    }
}
