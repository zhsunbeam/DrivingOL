package cn.marssky.account.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.PARAMETER)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NameValidated.class)
//定义一个UserDto对象的userName属性验证唯一的注解，作用于方法的参数，保留在服务器运行时，实现类为NameValidated
public @interface NameValid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
