package cn.marssky.account.client;

import cn.marssky.account.dto.AdminUsersDto;
import cn.marssky.account.dto.CreateAccountRequest;
import cn.marssky.account.dto.GenericAccountResponse;
import cn.marssky.account.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "account-service", path = "/v1/account", url = "http://localhost:9000/account-svc")
public interface AccountClient {

    @PostMapping(path = "/create")
    GenericAccountResponse createAccount(@RequestBody @Validated CreateAccountRequest account);

    @PostMapping(path = "/send_sms")
    ResponseDto sendSms(@RequestBody Map<String,String> map);

    @PostMapping(path="/login")
    ResponseDto login(@RequestBody AdminUsersDto adminUsersDto);

    @PostMapping(path="/signup")
    ResponseDto signup(@RequestBody AdminUsersDto adminUsersDto);

    @PostMapping(path = "/forget_password")
    ResponseDto forgetPassword(@RequestBody AdminUsersDto adminUsersDto);

    @PostMapping("/examine_captcha")
    ResponseDto examineCaptcha(@RequestBody AdminUsersDto adminUsersDto);
}