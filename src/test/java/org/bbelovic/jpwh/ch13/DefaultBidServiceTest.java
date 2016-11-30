package org.bbelovic.jpwh.ch13;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DefaultBidServiceTest {
    @Test
    public void testMaxPrice() {
        DefaultBidService bidService = new DefaultBidService();
        Bid maxBid = bidService.getMaxBid(3);
        Assert.assertEquals(BigDecimal.valueOf(50.0), maxBid.getPrice());
    }

}
