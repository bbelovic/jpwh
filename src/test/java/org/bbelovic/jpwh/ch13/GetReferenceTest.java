package org.bbelovic.jpwh.ch13;

import org.bbelovic.jpwh.Branch;
import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Set;

public class GetReferenceTest extends PersistenceTest {
    @Test
    public void testGetReference() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Branch reference = entityManager.getReference(Branch.class, 1L);
//        Branch reference = entityManager.find(Branch.class, 1L);
        Assert.assertEquals(1L, reference.getId().longValue());
        boolean loaded = Persistence.getPersistenceUtil().isLoaded(reference);
        boolean treeLoaded = Persistence.getPersistenceUtil().isLoaded(reference.getTree());
        Tree tree = reference.getTree();
        Assert.assertEquals("tree1", tree.getName());

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testGetReferenceNativeSession() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Session session = entityManager.unwrap(Session.class);
        Branch reference = session.getReference(Branch.class, 1L);
        Assert.assertEquals(1L, reference.getId().longValue());
        Tree tree = reference.getTree();
        Assert.assertEquals("tree1", tree.getName());

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void
    shouldLoadTreeUsingGetReference() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Tree treeReference = entityManager.getReference(Tree.class, 1L);
        treeReference.getName();
        Set<Branch> branches = treeReference.getBranches();
        branches.size();


//        Assert.assertEquals(1L, treeReference.getId().longValue());
//        Assert.assertEquals("tree1", treeReference.getName());

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void
    shouldLoadBranchUsingGetReference() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Branch branchReference = entityManager.getReference(Branch.class, 1L);
        branchReference.getName();
        Tree tree = branchReference.getTree();
        System.out.println("loading a tree");
        tree.getName();
        tree.getBranches().size();


        transaction.commit();
        entityManager.close();
    }
}
