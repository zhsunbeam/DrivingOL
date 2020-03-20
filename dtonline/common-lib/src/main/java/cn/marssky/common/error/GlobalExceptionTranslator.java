package cn.marssky.common.error;

import lombok.extern.slf4j.Slf4j;
import cn.marssky.common.api.BaseResponse;
import cn.marssky.common.api.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler(ServiceException.class)
    public BaseResponse errorHandler(ServiceException e) {
        log.error("服务内部异常", e);
        return BaseResponse.builder().code(ResultCode.PARAM_BIND_ERROR)
                .message(e.getMessage()).build();
    }
}
