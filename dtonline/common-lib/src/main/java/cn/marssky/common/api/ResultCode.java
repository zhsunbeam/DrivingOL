package cn.marssky.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(HttpServletResponse.SC_OK, "操作成功"),
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "错误的请求"),
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "消息无法读取"),
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数类型错误"),
    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数绑定错误"),
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数验证错误"),
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务内部错误"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到资源");

    final int code;
    final String message;


}
