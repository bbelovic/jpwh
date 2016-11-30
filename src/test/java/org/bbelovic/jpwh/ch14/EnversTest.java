package org.bbelovic.jpwh.ch14;

import org.bbelovic.jpwh.Branch;
import org.bbelovic.jpwh.PersistenceTest;
import org.bbelovic.jpwh.Tree;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EnversTest extends PersistenceTest {
    @Test
    public void test() {
        EntityManagerFactory managerFactory = getEntityManagerFactory();
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Branch b = new Branch();
        b.setName("branch 1");
        Tree tree = new Tree();
        tree.setConifer(1);
        tree.setName("new conifer tree");
        b.setTree(tree);
        tree.getBranches().add(b);
        entityManager.persist(tree);
        entityManager.persist(b);
        transaction.commit();
        entityManager.close();

    }

    @Test
    public void
    testReadEntityAtRevision() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        AuditQuery auditQuery = auditReader.createQuery()
                .forEntitiesAtRevision(Tree.class, 7);
        auditQuery.add(AuditEntity.property("name").eq("XXXX-empty2-new branch"));
        List resultList = auditQuery.getResultList();
        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void
    testReadingRevisions2() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Tree.class, false, false);
        List resultList = auditQuery.getResultList();
        resultList.stream()
                .forEach(each->System.out.println(Arrays.toString((Object[])each)));
    }

    @Test
    public void
    testReadingRevisions() {
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        Date revDate5 = new Date(1480195708667L);
        Number revisionNumberForDate = auditReader.getRevisionNumberForDate(revDate5);
        Assert.assertEquals(5, revisionNumberForDate.intValue());
        Date actualRevisionDate = auditReader.getRevisionDate(5);
        Assert.assertEquals(revDate5, actualRevisionDate);
    }
}
