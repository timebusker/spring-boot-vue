package com.timebusker.service;

import com.timebusker.utils.SequenceIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESC:AbstractBaseService
 * @author:timebusker
 * @date:2019/3/15
 */
public abstract class AbstractBaseService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected SequenceIdUtil sequenceId;
}
