package cn.marssky.account.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SendSmsValidated.class)
// 定义一个Map参数验证的注解判断发送短信的信息是否合理，
// 作用于方法的参数，保留在服务器运行时，实现类为SendSmsValidated
public @interface SendSmsValid {
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
