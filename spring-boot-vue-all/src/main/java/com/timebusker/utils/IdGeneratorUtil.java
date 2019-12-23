package com.timebusker.utils;

import com.timebusker.utils.sequence.SequenceIdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @Description: TimeBuskerIdGenerator
 * @Author: Administrator
 * @Date: 2019/12/20 15:43
 **/
public class IdGeneratorUtil implements IdentifierGenerator {

    private static final SequenceIdUtil sequenceId = new SequenceIdUtil(1, 1);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return "M_" + sequenceId.nextId();
    }
}
