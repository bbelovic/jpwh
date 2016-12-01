package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.Forest;
import org.bbelovic.jpwh.PersistenceTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CascadeTest extends PersistenceTest {
    @Test
    public void testCascadeRefresh() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Forest forest = entityManager.find(Forest.class, 1L);
        Assert.assertEquals(9, forest.getTrees().size());
        System.out.println("@@@ refresh @@@");
        entityManager.refresh(forest);
        Assert.assertEquals(9, forest.getTrees().size());
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }
}
