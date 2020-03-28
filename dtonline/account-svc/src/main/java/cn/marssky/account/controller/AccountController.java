package cn.marssky.account.controller;

import cn.marssky.account.service.AccountService;
import cn.marssky.account.validator.NameValid;
import cn.marssky.account.validator.SendSmsValid;
import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@RestController
@RequestMapping("/v1/account")
@Validated
public class AccountController {

    @Autowired
    AccountService accountService;


    //注册
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody @NameValid AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        return accountService.signup(adminUsersDto);
    }

    //登录
    @PostMapping("/login")
    public ResponseDto login(@RequestBody AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        return accountService.login(adminUsersDto);
    }

    //效验验证码
    @PostMapping("/examine_captcha")
    public ResponseDto examineCaptcha(@RequestBody AdminUsersDto adminUsersDto){
        return accountService.examineCaptcha(adminUsersDto);
    }

    //忘记密码
    @PostMapping("/forget_password")
    public ResponseDto forgetPassword(@RequestBody AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        return accountService.forgetPassword(adminUsersDto);
    }

    //发送短信

    @PostMapping("/send_sms")
    public ResponseDto sendSms(@RequestBody @SendSmsValid Map<String,String>map){
        return accountService.sendSms(map);
    }
}

