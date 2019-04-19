package com.timebusker.conf;

import com.timebusker.utils.SequenceIdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @DESC:CommonConfig:系统通用配置信息
 * @author:timebusker
 * @date:2019/4/10
 */
@Configuration
public class CommonConfig {

    /**
     * 统一注入单例SequenceIdUtil
     *
     * @return
     */
    @Bean
    public SequenceIdUtil sequenceIdUtil() {
        SequenceIdUtil util = new SequenceIdUtil(0, 1);
        return util;
    }
}
