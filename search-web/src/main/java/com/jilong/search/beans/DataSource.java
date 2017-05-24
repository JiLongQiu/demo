package com.jilong.search.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jilong.search.util.json.JsonUtil;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jilong.qiu
 * @date 2017/4/28.
 */
@Configuration
public class DataSource {

    @Resource
    private TransportClient client;

    @Bean
    public TransportClient esClient() throws UnknownHostException {
        Condition cd = new ReentrantLock().newCondition();
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("123.57.72.26"), 9300));
        return client;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return JsonUtil.getMapper();
    }

    @PreDestroy
    private void preDestroy() {
        client.close();
    }

}
