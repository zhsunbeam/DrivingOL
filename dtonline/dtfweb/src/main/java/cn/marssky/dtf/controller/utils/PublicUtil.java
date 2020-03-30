package cn.marssky.dtf.controller.utils;


import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.dto.ResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class PublicUtil {

    //controller层的重复代码，代替controller层发送请求
    public static ResponseDto publicUtil(RestTemplateBuilder restTemplateBuilder, String url, AdminUsersDto adminUsersDto){
        RestTemplate restTemplate=restTemplateBuilder.build();
        HttpEntity<AdminUsersDto> httpEntity=new HttpEntity(adminUsersDto);
        return restTemplate.postForObject(url,httpEntity, ResponseDto.class);
    }
}
