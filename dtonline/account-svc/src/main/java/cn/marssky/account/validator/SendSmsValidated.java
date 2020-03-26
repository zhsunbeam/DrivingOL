package cn.marssky.account.validator;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.account.exception.PhoneException;
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

    @Override
    public boolean isValid(Map<String,String> map, ConstraintValidatorContext constraintValidatorContext) {
        int result=adminUsersDao.soleValid(map.get("phone"),null);
        if("0".equals(map.get("state"))&&result>0){
            try {
                throw new PhoneException("该手机号码已存在");
            } catch (PhoneException e) {
                e.printStackTrace();
            }
            return false;
        }
        if("1".equals(map.get("state"))&&result==0){
            try {
                throw new PhoneException("该手机号码并没有注册");
            } catch (PhoneException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
