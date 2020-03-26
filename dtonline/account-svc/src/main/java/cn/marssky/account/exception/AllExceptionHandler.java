package cn.marssky.account.exception;

import cn.marssky.account.dto.ResponseDto;
import org.apache.ibatis.annotations.Results;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class,})
    @ResponseBody
    public ResponseDto handle(Exception e) {
        return new ResponseDto("403",e.toString(),null,false);
    }

}
