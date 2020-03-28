package cn.marssky.common.Utils;


import cn.marssky.common.dto.ResponseDto;

public class ResponseUtil {

    //创建失败的响应对象
    public static ResponseDto createErrorResponseDto(){
        return new ResponseDto(500,"系统繁忙，请稍后再试",null,false);
    }

}
