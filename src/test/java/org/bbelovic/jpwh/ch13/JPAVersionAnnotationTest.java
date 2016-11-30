package org.bbelovic.jpwh.ch13;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JPAVersionAnnotationTest {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @Test
    public void test() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Item item = new Item();
        item.setName("First item");
        entityManager.persist(item);
        tx.commit();
    }

    @Test
    public void testUpdateVersion() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();


        Item item = entityManager.find(Item.class, 2L);
        item.setName("name changed");

        tx.commit();
        System.out.println("after commit");
    }

    @Test
    public void testLoadBids() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Bid bid = entityManager.find(Bid.class, 1L);
        Item item = bid.getItem();
        Assert.assertEquals("dummy item", item.getName());
        transaction.commit();
    }

    @Test
    public void testLoadItems() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List list = entityManager.createQuery("select i from Item i where i.name = :name")
                .setLockMode(LockModeType.OPTIMISTIC)
                .setParameter("name", "item x")
                .getResultList();
        Assert.assertEquals(3, list.size());
        transaction.commit();
        entityManager.close();
    }


    @After
    public void tearDown() {
        entityManagerFactory.close();
    }
}
