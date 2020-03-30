package cn.marssky.dtf.controller.controller;

import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.dto.ResponseDto;
import cn.marssky.dtf.controller.utils.PublicUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@Validated
public class WebAccountController {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //注册
    @PostMapping("/signup")

    @ApiOperation("注册，需要传入phone、name、captcha、bizId、encryptedPassword")
    public ResponseDto signup(@Validated({AdminUsersDto.Signup.class, Default.class})
                                     AdminUsersDto adminUsersDto){
        String url="http://localhost:9000/account-svc/v1/account/signup";
        //调用公有类的publicUtil方法发送请求
        return PublicUtil.publicUtil(restTemplateBuilder,url,adminUsersDto);
    }

    //发送短信
    @PostMapping("/send_sms")
    @ApiOperation("发送信息,state为0时代表发送注册验证码，state为1时代表发送重置密码验证码")
    public ResponseDto sendSes(@RequestParam @Pattern(regexp = "-?[0-1]\\d*",message = "状态码错误") String state,
                          @RequestParam @Pattern(regexp ="^\\d{11}$",message = "手机号码填写错误") String phone){
        RestTemplate restTemplate=restTemplateBuilder.build();
        String url="http://localhost:9000/account-svc/v1/account/send_sms";
        Map<String,String> map=new HashMap<>();
        map.put("state",state);
        map.put("phone",phone);
        ResponseDto result=restTemplate.postForObject(url,map,ResponseDto.class);
        return result;
    }

    //登录
    @PostMapping("/login")
    @ApiOperation("登录，需要传入phone、encryptedPassword")
    public ResponseDto login(@Validated({AdminUsersDto.Login.class,Default.class})
                                         AdminUsersDto adminUsersDto){
        String url="http://localhost:9000/account-svc/v1/account/login";
        //调用公有类的publicUtil方法发送请求
        return PublicUtil.publicUtil(restTemplateBuilder,url,adminUsersDto);
    }

    //效验验证码
    @PostMapping("/examine_captcha")
    @ApiOperation("效验验证码，需要传入phone、captcha、bizId")
    public ResponseDto examineCaptcha(@Validated({AdminUsersDto.ExamineCaptcha.class,Default.class})
                                                  AdminUsersDto adminUsersDto){
        String url="http://localhost:9000/account-svc/v1/account/examine_captcha";
        //调用公有类的publicUtil方法发送请求
        return PublicUtil.publicUtil(restTemplateBuilder,url,adminUsersDto);
    }

    //忘记密码
    @PostMapping("/forget_password")
    @ApiOperation("忘记密码，需要传入phone、encryptedPassword")
    public ResponseDto forgetPassword(@Validated({AdminUsersDto.ForgetPassword.class,Default.class}) AdminUsersDto adminUsersDto){
        String url="http://localhost:9000/account-svc/v1/account/forget_password";
        //调用公有类的publicUtil方法发送请求
        return PublicUtil.publicUtil(restTemplateBuilder,url,adminUsersDto);
    }
}
