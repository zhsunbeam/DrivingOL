package cn.marssky.dtf.controller;

import cn.marssky.account.client.AccountClient;
import cn.marssky.account.dto.CreateAccountRequest;
import cn.marssky.account.dto.GenericAccountResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SignController {

    @Autowired
    AccountClient accountClient;


    /*****************/
    /*        注释   */
    /*****************/

    @RequestMapping("/signIn")
    @ResponseBody
    public String singIn(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "email") String email,
                                         @RequestParam(value = "password") String password) {
        System.out.println("登录");

        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setEmail(email);
        accountRequest.setPassword(password);
        System.out.println(this.accountClient);
        GenericAccountResponse response = null;

        try {
            response = this.accountClient.createAccount(accountRequest);
        }
        catch(Exception e) {
            String errMsg = "创建会员失败";
        }

        //返回的字符串根据上面业务逻辑确定

        return "";
    }
}
