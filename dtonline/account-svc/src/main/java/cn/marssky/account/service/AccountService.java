package cn.marssky.account.service;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.common.Utils.ResponseUtil;
import cn.marssky.common.dto.AdminUsersDto;
import cn.marssky.common.dto.ResponseDto;
import cn.marssky.account.util.SmsUtil;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class AccountService {

    //创建日志对象
    Logger logger=Logger.getLogger(AccountService.class);

    //创建日期格式对象
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");

    //自动注入dao层对象
    @Autowired
    AdminUsersDao adminUsersDao;

    //自动注入短信工具对象
    @Autowired
    SmsUtil smsUtil;

    private final ModelMapper modelMapper=null;

//    public int createAccount(String name, String email, String phoneNumber) {
//        return accountDao.createAccount();
//    }

    //注册
    public ResponseDto signup(AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        //判断验证码是否正确，如果错误返回响应对象
        if (!adminUsersDto.getCaptcha().equals(smsUtil.querySendDetails(adminUsersDto.getPhone(),adminUsersDto.getBizId()))){
            return new ResponseDto(400,"验证码错误",null,false);
        }
        //给密码加密
        adminUsersDto.setEncryptedPassword();
        //设置账号修改日期
        adminUsersDto.setUpdatedAt(simpleDateFormat.format(new Date()));
        //设置账号创建日期
        adminUsersDto.setCreatedAt(simpleDateFormat.format(new Date()));
        //调用dao层注册方法，并返回结果，如果结果大于0记录日志后返回成功响应对象
        if (adminUsersDao.signup(adminUsersDto)>0){
            logger.info("手机号码为:"+adminUsersDto.getPhone()+"的用户注册成功");
            return new ResponseDto(200,"注册成功",null,true);
        }
        logger.error("手机号码为:"+adminUsersDto.getPhone()+"的用户由于未知原因注册失败");
        return ResponseUtil.createErrorResponseDto();
    }

    //效验验证码
    public ResponseDto examineCaptcha(AdminUsersDto adminUsersDto){
        //效验验证码是否正确，如果正确则返回成功响应对象
        if(adminUsersDto.getCaptcha().
                equals(smsUtil.querySendDetails(adminUsersDto.getPhone(), adminUsersDto.getBizId()))){
            return new ResponseDto(200,"验证码正确",null,true);
        }
        return new ResponseDto(400,"验证码错误",null,false);
    }


    //登录
    public ResponseDto login(AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        //给密码加密
        adminUsersDto.setEncryptedPassword();
        //调用dao层方法，返回结果
        String result=adminUsersDao.login(adminUsersDto);
        //判断结果是否为空，如果不为空记录日志后返回成功响应对象
        if (result!=null){
            logger.info("手机号码为:"+adminUsersDto.getPhone()+"的用户登录成功");
            return new ResponseDto(200,"登录成功",result,true);
        }
        logger.info("手机号码为:"+adminUsersDto.getPhone()+"的用户由于用户名或密码错误登陆失败");
        return new ResponseDto(400,"用户名或密码错误",null,false);
    }

    //发送短信
    public ResponseDto sendSms(Map<String,String> map){
        //调用短信工具发送短信，并记录日志
        String result=smsUtil.sendSms(map.get("state"),map.get("phone"));
        if ("0".equals(map.get("state"))){
            logger.info("向手机号码为:"+map.get("phone")+"的用户送了一条注册短信");
        }else if ("1".equals(map.get("state"))){
            logger.info("向手机号码为:"+map.get("phone")+"的用户送了一条修改短信");
        }
        return new ResponseDto(200,"发送成功",result,true);
    }

    //忘记密码
    public ResponseDto forgetPassword(AdminUsersDto adminUsersDto) throws NoSuchAlgorithmException {
        //给密码加密
        adminUsersDto.setEncryptedPassword();
        //设置账号修改日期
        adminUsersDto.setUpdatedAt(simpleDateFormat.format(new Date()));
        //调用dao层修改密码并返回结果，判断结果是否大于0，如果大于0记录日志后返回成功响应对象
        if(adminUsersDao.forgetPassword(adminUsersDto)>0){
            logger.info("手机号码为:"+adminUsersDto.getPhone()+"的用户修改了密码");
            return new ResponseDto(200,"修改密码成功",null,true);
        }
        logger.error("手机号码为:"+adminUsersDto.getPhone()+"的用户由于未知原因修改密码失败");
        return ResponseUtil.createErrorResponseDto();
    }

}
