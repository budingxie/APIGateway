package com.py.agw.chains.support;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description：泛化调用
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/1/13
 */
@Component
public class GenericInvokeDubbo {

    private Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new ConcurrentHashMap<>();

    @Resource
    private ApplicationConfig application;

    @Resource
    private RegistryConfig registry;

    public Object invokeDubbo(
            String interfaceClass,
            String methodName,
            String[] invokeParamTypes,
            Object[] invokeParams) {

        ReferenceConfigCache cache = null;
        ReferenceConfig<GenericService> referenceConfig = null;
        try {
            referenceConfig = referenceConfigMap.get(interfaceClass);
            if (referenceConfig == null) {
                referenceConfig = createReferenceConfig(interfaceClass);
                referenceConfigMap.put(interfaceClass, referenceConfig);
            }
            cache = ReferenceConfigCache.getCache();
            GenericService genericService = cache.get(referenceConfig);
            return genericService.$invoke(methodName, invokeParamTypes, invokeParams);
        } catch (IllegalStateException e) {
            referenceConfigMap.remove(interfaceClass);
            if (cache != null) {
                cache.destroy(referenceConfig);
            }
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReferenceConfig<GenericService> createReferenceConfig(String interfaceClass) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(application);
        referenceConfig.setRegistry(registry);
        referenceConfig.setConsumer(getConsumerConfig());
        referenceConfig.setGeneric(true);
        // referenceConfig.setVersion();
        referenceConfig.setInterface(interfaceClass);
        return referenceConfig;
    }

    private ConsumerConfig getConsumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(5000);
        consumerConfig.setRetries(2);
        return consumerConfig;
    }

}
