package cn.marssky.common.async;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 线程池上下文切换 装饰器
 */

public class ContextCopyingDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        return () -> {
            try {
                //将请求上下文传递给新的线程，返回后恢复上下文
                RequestContextHolder.setRequestAttributes(context);
                runnable.run();
            }
            finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
