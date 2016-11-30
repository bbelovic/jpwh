package org.bbelovic.jpwh.ch14;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Iterator;

public class LoggingInterceptor extends EmptyInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.info("onSave entity=[{}], id=[{}], state=[{}], propertyNames=[{}], types=[{}]",
                entity, id, state, propertyNames, types);
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        logger.info("onFlushDirty entity=[{}], id=[{}], currentState=[{}], previousState=[{}], propertyNames=[{}], types=[{}]",
                entity, id, currentState, previousState, propertyNames, types);
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        StringBuilder stringBuilder = new StringBuilder();
        while (entities.hasNext()) {
            stringBuilder.append(entities.next());
        }
        logger.info("postFlush flushed entities [{}]", stringBuilder);
        super.postFlush(entities);
    }
}
