package org.bbelovic.jpwh.ch13;

import org.bbelovic.jpwh.Branch;
import org.bbelovic.jpwh.PersistenceTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class DynamicEagerFetchTest extends PersistenceTest {

    @Test
    public void
    shouldSelectBranchAndTreeUsingDynamicEagerFetch() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Object result = entityManager.createQuery("select b from Branch b join fetch b.tree where b.id=:id")
                .setParameter("id", 1L)
                .getSingleResult();
        Assert.assertTrue(result instanceof Branch);
        Branch b = (Branch) result;
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void shouldSelectOnlyBranch() {
        EntityManagerFactory managerFactory = getEntityManagerFactory();
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Branch branch = entityManager.find(Branch.class, 1L);


        transaction.commit();
        entityManager.close();
    }
}
