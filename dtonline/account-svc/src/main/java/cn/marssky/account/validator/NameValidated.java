package cn.marssky.account.validator;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidated implements ConstraintValidator<NameValid, AdminUsersDto> {

    @Autowired
    AdminUsersDao adminUsersDao;

    @Override
    public void initialize(NameValid constraintAnnotation) {
    }

    //注解实现方法
    @Override
    public boolean isValid(AdminUsersDto adminUsersDto, ConstraintValidatorContext constraintValidatorContext) {
        //调用dao层方法，返回查询的结果，如果结果大于1，则抛出自定义的参数异常，否则返回true
        if(adminUsersDao.soleValid(null,adminUsersDto.getName())>0){
                throw new ParameterException("昵称已存在");
        }
        return true;
    }
}
