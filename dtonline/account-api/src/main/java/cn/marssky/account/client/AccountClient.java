package cn.marssky.account.client;

import cn.marssky.account.dto.CreateAccountRequest;
import cn.marssky.account.dto.GenericAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service", path = "/v1/account", url = "http://localhost:9000/account-svc")
public interface AccountClient {

    @PostMapping(path = "/create")
    GenericAccountResponse createAccount(@RequestBody @Validated CreateAccountRequest account);
}