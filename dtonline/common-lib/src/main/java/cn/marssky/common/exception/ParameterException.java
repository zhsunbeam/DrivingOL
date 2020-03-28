package cn.marssky.common.exception;

import lombok.Getter;

@Getter
//自定义的参数异常
public class ParameterException extends RuntimeException {

    private String mes;

    public ParameterException(String message){
        super(message);
        this.mes=message;
    }
}
