package cn.marssky.account.client;

import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "account-service", path = "/v1/account", url = "http://localhost:9000/account-svc")
public interface AccountClient {

//    @PostMapping(path = "/create")
//    GenericAccountResponse createAccount(@RequestBody @Validated CreateAccountRequest account);

    //转发发送短信的请求
    @PostMapping(path = "/send_sms")
    ResponseDto sendSms(@RequestBody Map<String,String> map);

    //转发登录的请求
    @PostMapping(path="/login")
    ResponseDto login(@RequestBody AdminUsersDto adminUsersDto);

    //转发注册的请求
    @PostMapping(path="/signup")
    ResponseDto signup(@RequestBody AdminUsersDto adminUsersDto);

    //转发忘记密码的请求
    @PostMapping(path = "/forget_password")
    ResponseDto forgetPassword(@RequestBody AdminUsersDto adminUsersDto);

    //转发效验验证码的请求
    @PostMapping("/examine_captcha")
    ResponseDto examineCaptcha(@RequestBody AdminUsersDto adminUsersDto);
}