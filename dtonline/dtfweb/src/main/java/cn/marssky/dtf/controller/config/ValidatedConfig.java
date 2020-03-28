package cn.marssky.dtf.controller.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatedConfig {

    //设置效验模式为快速效验模式，即一个信息效验失败则直接返回
    @Bean
    public Validator validatorFactory(){
        ValidatorFactory validatorFactory= Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        return validator;
    }
}
