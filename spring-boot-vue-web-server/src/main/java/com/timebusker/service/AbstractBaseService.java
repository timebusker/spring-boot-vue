package com.timebusker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @DESC:AbstractBaseService
 * @author:timebusker
 * @date:2019/3/15
 */
@Service
public abstract class AbstractBaseService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


}
