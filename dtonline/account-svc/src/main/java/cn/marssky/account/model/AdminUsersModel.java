package cn.marssky.account.model;


public class AdminUsersModel {

  private long id;
  private String email;
  private String name;
  private String encryptedPassword;
  private String resetPasswordToken;
  private java.sql.Timestamp resetPasswordSentAt;
  private java.sql.Timestamp rememberCreatedAt;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;

}
