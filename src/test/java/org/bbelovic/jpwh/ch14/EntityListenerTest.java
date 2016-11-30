package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class EntityListenerTest extends PersistenceTest {
    @Test
    public void testEntityListenerCallbacks() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Tree tree = entityManager.find(Tree.class, 2L);
        tx.commit();
        entityManager.close();
    }

    @Test
    public void testEntityListenerCallbacks2() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Tree tree = new Tree();
        tree.setName("dummy tree name");
        entityManager.persist(tree);

        tx.commit();
        entityManager.close();
    }
}
