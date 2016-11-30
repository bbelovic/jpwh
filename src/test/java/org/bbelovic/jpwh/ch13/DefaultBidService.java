package org.bbelovic.jpwh.ch13;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class DefaultBidService {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");

    public Bid getMaxBid(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        List list = entityManager.createQuery("select b from Bid b where b.item.id = :itemId")
                .setParameter("itemId", id)
                .getResultList();
        tx.commit();
        BigDecimal maxPrice = BigDecimal.ZERO;
        Bid maxBid = null;
        for (Object each: list) {
            if (((Bid)each).getPrice().compareTo(maxPrice) > 0) {
                maxPrice = ((Bid)each).getPrice();
                maxBid = ((Bid)each);
            }
        }
        return maxBid;
    }
}
