package org.bbelovic.jpwh.ch13;

import org.junit.Test;

public class PessimisticLockExceptionTest {
    @Test
    public void test() {
        DefaultItemService itemService = new DefaultItemService();
        Item item = itemService.getItemWithPessimisticLock(2L);
    }


}
