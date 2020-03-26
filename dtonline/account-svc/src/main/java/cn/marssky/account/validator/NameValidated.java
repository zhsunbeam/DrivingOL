package cn.marssky.account.validator;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.account.exception.NameException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidated implements ConstraintValidator<NameValid,String> {

    @Autowired
    AdminUsersDao adminUsersDao;

    @Override
    public void initialize(NameValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if(adminUsersDao.soleValid(null,name)>0){
            try {
                throw new NameException("昵称已存在");
            } catch (NameException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
