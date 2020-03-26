package cn.marssky.account.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AdminUsersDto {

  //注册效验组
  public interface Signup{};

  //登录效验组
  public interface Login{};

  //效验验证码验证组
  public interface ExamineCaptcha{};

  //忘记密码验证组
  public interface ForgetPassword{};

  private long id;

  @NotEmpty(groups = {Signup.class,Login.class,ExamineCaptcha.class,ForgetPassword.class},message = "手机号码不能为空")
  @Pattern(regexp = "^\\d{11}$",message = "手机号码格式不正确")
  private String phone;

  private String email;

  @NotEmpty(groups = Signup.class,message = "昵称不能为空")
  @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$",message = "昵称填写错误")
  @Size(min = 3,max = 10,message = "昵称填写错误")
  private String name;

  @NotEmpty(groups = {Signup.class,ExamineCaptcha.class},message = "验证码不能为空")
  @Pattern(regexp = "^\\d{6}$",message = "验证码填写错误")
  private String captcha;

  @NotEmpty(groups = {Signup.class,ExamineCaptcha.class},message = "短信流水号不能为空")
  private String bizId;

  @NotEmpty(groups = {Signup.class,Login.class,ForgetPassword.class},message = "密码不能为空")
  @Pattern(regexp ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,15}$",message = "密码填写错误")
  private String encryptedPassword;

  private String resetPasswordToken;
  private String resetPasswordSentAt;
  private String rememberCreatedAt;
  private String createdAt;
  private String updatedAt;

}
