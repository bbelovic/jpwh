package org.bbelovic.jpwh.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;

import static javax.persistence.LockModeType.OPTIMISTIC_FORCE_INCREMENT;

public class DefaultItemService {

    private static final Logger log = LoggerFactory.getLogger(DefaultItemService.class);
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");

    public void updateItem(long id) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction tx = entityManager.getTransaction();
            tx.begin();
            Item item = entityManager.find(Item.class, id);
            item.setName("updated name - " + System.currentTimeMillis());
            entityManager.persist(item);
            tx.commit();
        } catch (Exception e) {
            log.error("Unable to persist updated entity", e);
        }
    }

    public void updateMaxBidForItem(Item item, Bid bid) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Item i = entityManager.find(Item.class, item.getId(), OPTIMISTIC_FORCE_INCREMENT);
        DefaultBidService bidService = new DefaultBidService();
        Bid maxBid = bidService.getMaxBid(i.getId());
        if (bid.getPrice().compareTo(maxBid.getPrice()) > 0) {
            bid.setItem(i);
            entityManager.persist(bid);
        }
        tx.commit();
        entityManager.close();
    }

    public Item getItemWithPessimisticLock(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.lock.timeout", 5000);
        Item item = entityManager.find(Item.class, id, LockModeType.PESSIMISTIC_WRITE, hints);
        tx.commit();
        entityManager.close();
        return item;
    }


}
