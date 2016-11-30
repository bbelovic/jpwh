package org.bbelovic.jpwh.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class LoggingListener {
    private static final Logger logger = LoggerFactory.getLogger(LoggingListener.class);

    @PostLoad
    public void postLoad(Object entity) {
        logger.info("Post load called for entity [{}].", entity);
    }

    @PrePersist
    public void prePersist(Object entity) {
        logger.info("Pre persist called for entity [{}].", entity);
    }

    @PostPersist
    public void postPersist(Object entity) {
        logger.info("Post persist called for entity [{}].", entity);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        logger.info("Pre update called for entity [{}].", entity);
    }

    @PostUpdate
    public void postUpdate(Object entity) {
        logger.info("Post update called for entity [{}].", entity);
    }

    @PreRemove
    public void preRemove(Object entity) {
        logger.info("Pre remove called for entity [{}].", entity);
    }

    @PostRemove
    public void postRemove(Object entity) {
        logger.info("Post remove caleld for entity [{}].", entity);


    }




}
