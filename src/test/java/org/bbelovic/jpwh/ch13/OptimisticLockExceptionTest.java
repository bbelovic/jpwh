package org.bbelovic.jpwh.ch13;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OptimisticLockExceptionTest {

    private final CountDownLatch startLatch = new CountDownLatch(1);
    private final CountDownLatch endLatch = new CountDownLatch(15);
    private final DefaultItemService itemService = new DefaultItemService();
    private final ExecutorService executorService = Executors.newFixedThreadPool(15);

    @Test
    public void test() {
        try {
            for (int i = 0; i < 15; i++) {
                executorService.submit(new Worker());
            }
            startLatch.countDown();
            System.out.println("Workers released..");
            endLatch.await();
            System.out.println("Workers done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final class Worker implements Runnable {
        @Override
        public void run() {
            try {
                startLatch.await();
                itemService.updateItem(3L);
                endLatch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Test
    public void testForceIncrement() {
        DefaultItemService itemService = new DefaultItemService();
        Item item = new Item();
        item.setId(3L);
        Bid bid = new Bid();
        bid.setPrice(BigDecimal.valueOf(90L));
        itemService.updateMaxBidForItem(item, bid);
    }
}
