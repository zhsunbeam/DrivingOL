package cn.marssky.account.validator;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.common.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

public class SendSmsValidated implements ConstraintValidator<SendSmsValid, Map<String,String> > {

    @Autowired
    AdminUsersDao adminUsersDao;

    @Override
    public void initialize(SendSmsValid constraintAnnotation) {
    }

    //注解实现方法
    @Override
    public boolean isValid(Map<String,String> map, ConstraintValidatorContext constraintValidatorContext) {
        //调用dao层方法查询手机号码是否已存在
        int result=adminUsersDao.soleValid(map.get("phone"),null);
        //如果状态码为0且结果大于0，说明该手机号码已存在不能再发送注册验证码，并抛出自定义的参数异常
        if("0".equals(map.get("state"))&&result>0){
                throw new ParameterException("该手机号码已存在");
        }
        //如果状态码为1且结果小于0，说明该手机号码不存在，不能发送忘记密码验证码，并抛出自定义的参数异常
        if("1".equals(map.get("state"))&&result==0){
                throw new ParameterException("该手机号码并没有注册");
        }
        return true;
    }
}
