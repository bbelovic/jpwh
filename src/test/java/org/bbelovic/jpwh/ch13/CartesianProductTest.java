package org.bbelovic.jpwh.ch13;

import org.bbelovic.jpwh.PersistenceTest;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class CartesianProductTest extends PersistenceTest {
    @Test
    public void testCartesianProduct() {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Item item = entityManager.find(Item.class, 2L);

        tx.commit();
        entityManager.close();

    }
}
