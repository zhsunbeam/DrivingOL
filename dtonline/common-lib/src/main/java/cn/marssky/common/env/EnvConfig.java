package cn.marssky.common.env;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class EnvConfig {
    private String name;
    private String externalApex;
    private String internalApex;
    private boolean debug;
    private String scheme;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Map<String, EnvConfig> map;

    static {
        //存储所有环境配置信息
        map = new HashMap<String, EnvConfig>();

        //开发环境配置
        EnvConfig envConfig = EnvConfig.builder().name(EnvConstant.ENV_DEV)
                .debug(true)
                .externalApex("sakila-v1.local")
                .internalApex(EnvConstant.ENV_DEV)
                .scheme("http")
                .build();
        //存储到map中
        map.put(EnvConstant.ENV_DEV, envConfig);

        //测试环境配置
        envConfig = EnvConfig.builder()
                .name(EnvConstant.ENV_TEST)
                .externalApex("sakila-v1.local")
                .internalApex(EnvConstant.ENV_TEST)
                .debug(true)
                .scheme("http")
                .build();

        map.put(EnvConstant.ENV_TEST, envConfig);

        //准生产环境
        envConfig = EnvConfig.builder().name(EnvConstant.ENV_UAT)
                .debug(false)
                .externalApex("marssky.cn")
                .internalApex(EnvConstant.ENV_UAT)
                .scheme("https")
                .build();

        map.put(EnvConstant.ENV_UAT, envConfig);

        //生产环境
        envConfig = EnvConfig.builder().name(EnvConstant.ENV_PROD)
                .debug(false)
                .externalApex("marssky.cn")
                .internalApex(EnvConstant.ENV_PROD)
                .scheme("https")
                .build();
        map.put(EnvConstant.ENV_PROD, envConfig);

    }


    /**
     *  提供一个公有方法返回环境配置对象
     */
    public static EnvConfig getEnvConfig(String env) {
        EnvConfig envConfig = map.get(env);
        if(envConfig == null) {
            envConfig = map.get(EnvConstant.ENV_DEV);
        }
        return envConfig;
    }
}
