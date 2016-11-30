package org.bbelovic.jpwh.ch13;

import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;

public class EntityGraphTest extends PersistenceTest {
    @Test
    public void test() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Map<String, Object> props = new HashMap<>();
        props.put("javax.persistence.loadgraph", entityManager.getEntityGraph("Tree"));
        Tree tree = entityManager.find(Tree.class, 1L, props);
        tx.commit();
        entityManager.close();

    }
}
