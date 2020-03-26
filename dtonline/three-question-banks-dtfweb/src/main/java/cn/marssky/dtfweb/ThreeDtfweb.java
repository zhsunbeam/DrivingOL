package cn.marssky.dtfweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

//科目三 主启动类
//ThreeDtfweb 科目三题库前台缩写

@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
public class ThreeDtfweb {
    public static void main(String[] args) {
        SpringApplication.run(ThreeDtfweb.class,args);
    }
}
