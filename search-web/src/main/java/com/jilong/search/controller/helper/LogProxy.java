package com.jilong.search.controller.helper;

import com.google.common.collect.Lists;
import com.jilong.search.util.json.JsonUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jilong.qiu
 * @date 2016/12/29.
 */
public class LogProxy implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogProxy.class);

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = mi.proceed();
        long cost = System.currentTimeMillis() - start;
        List<Object> args = Lists.newArrayList(mi.getArguments())
                .stream()
                .filter(item -> item.getClass().getName().startsWith("com.jskz.stats.operation.flow"))
                .collect(Collectors.toList());
        log.info("method [{}] args [{}] cost [{}] ms",
                mi.getMethod(), JsonUtil.getMapper().writeValueAsString(args), cost);
        return result;
    }

}
