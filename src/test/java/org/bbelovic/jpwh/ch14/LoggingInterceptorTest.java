package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class LoggingInterceptorTest extends PersistenceTest {
    @Test
    public void testInterceptor() {
        EntityManagerFactory managerFactory = getEntityManagerFactory();
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Tree tree = entityManager.find(Tree.class, 2L);
        tree.setName("tree interceptor");
        entityManager.flush();
        tx.commit();
        entityManager.close();
    }
}
