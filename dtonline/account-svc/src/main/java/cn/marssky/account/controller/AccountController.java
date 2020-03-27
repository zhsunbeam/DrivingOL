package cn.marssky.account.controller;

import cn.marssky.account.dto.AccountDto;
import cn.marssky.account.dto.CreateAccountRequest;
import cn.marssky.account.dto.GenericAccountResponse;
import cn.marssky.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    Environment env;

    @Autowired
    AccountService accountService;

   //传JSON对象需要@RequestBody


    @RequestMapping("/create")
    public GenericAccountResponse createAccount( CreateAccountRequest accountDto) {

        log.info("调用账户服务模块的createAccount方法来注册");

        //调用服务层

        //log.info(accountDto.getEmail());
        //测试
        AccountDto adto = new AccountDto();
        adto.setName("jack");
        adto.setEmail("5687@qq.com");
        GenericAccountResponse genericAccountResponse = new GenericAccountResponse(adto);
        return genericAccountResponse;
    }


}

