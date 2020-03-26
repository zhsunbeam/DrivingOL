package cn.marssky.dtf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"cn.marssky.video.client","cn.marssky.account.client"})
@SpringBootApplication
public class DtfWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DtfWebApplication.class, args);
    }
}
