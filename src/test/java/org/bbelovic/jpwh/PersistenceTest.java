package org.bbelovic.jpwh;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceTest {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("templatePU");

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
