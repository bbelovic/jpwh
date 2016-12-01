package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.Forest;
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

    @Test
    public void
    testCollectionFilter() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.unwrap(Session.class)
                .enableFilter("limitByLumberJack")
                .setParameter("name", "lumberjack two");
        List resultList = entityManager.createQuery("select f from Forest f where id = :id")
                .setParameter("id", 1L)
                .getResultList();
        Assert.assertEquals(1, resultList.size());
        Forest forest = (Forest) resultList.get(0);
        Assert.assertEquals(3, forest.getTrees().size());
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
