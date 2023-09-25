package com.zh.infrastructure.environment;

import com.zh.common.constant.KeyConstant;
import com.zh.common.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class EncryptEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String ENCRYPT_PREFIX = "enc_";
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        for (PropertySource<?> propertySource : environment.getPropertySources()) {

            if (propertySource instanceof OriginTrackedMapPropertySource){

                Map<String, OriginTrackedValue> map = (Map<String, OriginTrackedValue>) propertySource.getSource();
                Map<String, OriginTrackedValue> newMap = new HashMap<>(map.size());
                map.forEach(
                        (k,v) -> {
                            if (v.getValue().toString().startsWith(ENCRYPT_PREFIX)){

                                System.out.println("解密配置文件属性:" + k + " = " + v.getValue().toString().substring(ENCRYPT_PREFIX.length()));
                                String newStr = AesUtil.decode(v.getValue().toString().substring(ENCRYPT_PREFIX.length()), KeyConstant.CONFIGURATION_KEY);
                                OriginTrackedValue newValue = OriginTrackedValue.of(newStr,v.getOrigin());
                                newMap.put(k,newValue);
                            }else {

                                newMap.put(k,v);
                            }
                        }
                );
                OriginTrackedMapPropertySource newSource = new OriginTrackedMapPropertySource(propertySource.getName(),Collections.unmodifiableMap(newMap));
                environment.getPropertySources().replace(newSource.getName(),newSource);
            }
        }
    }
}
