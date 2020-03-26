package cn.marssky.account.controller;

import cn.marssky.account.dto.*;
import cn.marssky.account.service.AccountService;
import cn.marssky.account.validator.SendSmsValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


@RestController
@RequestMapping("/v1/account")
@Validated
public class AccountController {
    @Autowired
    Environment env;

    @Autowired
    AccountService accountService;

   //传JSON对象需要@RequestBody
    @RequestMapping("/create")
    public GenericAccountResponse createAccount( CreateAccountRequest accountDto) {
//        log.info("调用账户服务模块的createAccount方法来注册");
        //调用服务层
        //log.info(accountDto.getEmail());
        //测试
        AccountDto adto = new AccountDto();
        adto.setName("jack");
        adto.setEmail("5687@qq.com");
        GenericAccountResponse genericAccountResponse = new GenericAccountResponse(adto);
        return genericAccountResponse;
    }

    //注册
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody @Valid SVCAdminUsersDto adminUsersDto){
        try {
            adminUsersDto.setEncryptedPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseDto("500","系统繁忙，请稍后再试",null,false);
        }
        return accountService.signup(adminUsersDto);
    }

    //登录
    @PostMapping("/login")
    public ResponseDto login(@RequestBody SVCAdminUsersDto adminUsersDto){
        try {
            adminUsersDto.setEncryptedPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseDto("500","系统繁忙，请稍后再试",null,false);
        }
        return accountService.login(adminUsersDto);
    }

    //效验验证码
    @PostMapping("/examine_captcha")
    public ResponseDto examineCaptcha(@RequestBody SVCAdminUsersDto adminUsersDto){
        return accountService.examineCaptcha(adminUsersDto);
    }

    //忘记密码
    @PostMapping("/forget_password")
    public ResponseDto forgetPassword(@RequestBody SVCAdminUsersDto adminUsersDto){
        try {
            adminUsersDto.setEncryptedPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseDto("500","系统繁忙，请稍后再试",null,false);
        }
        return accountService.forgetPassword(adminUsersDto);
    }

    //发送短信

    @PostMapping("/send_sms")
    public ResponseDto sendSms(@RequestBody @SendSmsValid Map<String,String>map){
        return accountService.sendSms(map);
    }
}

