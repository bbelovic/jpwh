package org.bbelovic.jpwh.ch12;

import org.bbelovic.jpwh.ch13.Item;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EagerFetchTest {
    @Test
    public void loadCollectionEagerlyTest() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Item item = entityManager.find(Item.class, 2L);
        tx.commit();
        Assert.assertEquals(2L, item.getId().longValue());


    }
}
