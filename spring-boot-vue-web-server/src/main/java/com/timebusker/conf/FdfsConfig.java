package com.timebusker.conf;

import org.csource.fastdfs.ClientGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @DESC:FdfsConfig:FastDFS配置加载
 * @author:timebusker
 * @date:2019/4/2
 */

/**
 * 解决jmx重复注册bean的问题
 */
@Configuration
public class FdfsConfig {

    private final static Logger logger = LoggerFactory.getLogger(FdfsConfig.class);

    @Value("${fastdfs.connect_timeout_in_seconds}")
    private String connectTimeout;

    @Value("${fastdfs.network_timeout_in_seconds}")
    private String networkTimeout;

    @Value("${fastdfs.charset}")
    private String charset;

    @Value("${fastdfs.http_tracker_http_port}")
    private String trackerHttpPort;

    @Value("${fastdfs.http_anti_steal_token}")
    private String antiStealToken;

    @Value("${fastdfs.http_secret_key}")
    private String secretKey;

    @Value("${fastdfs.tracker_servers}")
    private String trackerServers;

    @Bean
    public void initFastDFS() {
        Properties props = new Properties();
        props.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, connectTimeout);
        props.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, networkTimeout);
        props.put(ClientGlobal.PROP_KEY_CHARSET, charset);
        props.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, trackerHttpPort);
        props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, antiStealToken);
        props.put(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, secretKey);
        props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackerServers);
        try {
            ClientGlobal.initByProperties(props);
        } catch (Exception e) {
            logger.error("初始化FastDFS客户端失败：" + e.getMessage());
        }
    }
}
