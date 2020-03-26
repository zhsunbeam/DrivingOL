package cn.marssky.account.dto;

import cn.marssky.account.validator.NameValid;
import lombok.Getter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
public class SVCAdminUsersDto {

  private long id;
  private String phone;
  private String email;
  @NameValid(message = "昵称已存在")
  private String name;
  private String captcha;
  private String bizId;
  private String encryptedPassword;
  private String resetPasswordToken;
  private Timestamp resetPasswordSentAt;
  private Timestamp rememberCreatedAt;
  private String createdAt;
  private String updatedAt;

  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

  public void setEncryptedPassword() throws NoSuchAlgorithmException {
    MessageDigest messageDigest=MessageDigest.getInstance("MD5");
    messageDigest.update(this.encryptedPassword.getBytes());
    this.encryptedPassword=new BigInteger(1,messageDigest.digest()).toString(16);
  }

  public void setCreatedAt(){
    this.createdAt=simpleDateFormat.format(new Date());
  }

  public void setUpdatedAt(){
    this.updatedAt=simpleDateFormat.format(new Date());
  }

}
