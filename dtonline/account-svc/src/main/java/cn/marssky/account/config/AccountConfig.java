package cn.marssky.account.config;

import cn.marssky.common.async.ContextCopyingDecorator;
import cn.marssky.common.config.ExceptionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Import(value = {ExceptionConfig.class})
public class AccountConfig {
    //异步执行名称
    public static final String ASYNC_EXECUTOR_NAME = "asyncExecutor";

    //创建异步执行的线程池
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setTaskDecorator(new ContextCopyingDecorator());
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }
}
