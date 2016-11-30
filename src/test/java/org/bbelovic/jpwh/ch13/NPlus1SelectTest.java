package org.bbelovic.jpwh.ch13;

import org.bbelovic.jpwh.Branch;
import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by bbelovic on 8.11.16.
 */
public class NPlus1SelectTest extends PersistenceTest {

    @Test
    public void testSelectNPlus1() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
//        Tree tree = entityManager.find(Tree.class, 1L);
        List resultList = entityManager.createQuery("select b from Branch b").getResultList();
        for (Object each: resultList) {
            ((Branch) each).getTree().getName();
        }
        tx.commit();
        entityManager.close();
    }

    @Test
    public void extraLazyCollectionTest() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Tree tree = entityManager.find(Tree.class, 1L);
        tree.getBranches().size();
        entityManager.getTransaction().commit();
    }
}
