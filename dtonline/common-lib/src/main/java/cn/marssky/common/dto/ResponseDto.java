package cn.marssky.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    //状态码
    private int code;

    //信息
    private String message;

    //具体内容
    private String date;

    //是否成功
    private Boolean succeed;

}
