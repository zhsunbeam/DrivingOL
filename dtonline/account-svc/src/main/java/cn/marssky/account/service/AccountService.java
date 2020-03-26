package cn.marssky.account.service;

import cn.marssky.account.dao.AdminUsersDao;
import cn.marssky.account.dto.AccountDto;
import cn.marssky.account.dto.ResponseDto;
import cn.marssky.account.dto.SVCAdminUsersDto;
import cn.marssky.account.model.Account;
import cn.marssky.account.util.SmsUtil;
import cn.marssky.common.error.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Map;

@Service
public class AccountService {

//    @Autowired
//    AccountDao accountDao;

    @Autowired
    AdminUsersDao adminUsersDao;

    @Autowired
    SmsUtil smsUtil;

    private final ModelMapper modelMapper=null;

//    public int createAccount(String name, String email, String phoneNumber) {
//        return accountDao.createAccount();
//    }

    //注册
    public ResponseDto signup(SVCAdminUsersDto adminUsersDto){
        if (!adminUsersDto.getCaptcha().equals(smsUtil.querySendDetails(adminUsersDto.getPhone(),adminUsersDto.getBizId()))){
            return new ResponseDto("500","验证码错误",null,false);
        }
        adminUsersDto.setUpdatedAt();
        adminUsersDto.setCreatedAt();
        int res=adminUsersDao.signup(adminUsersDto);
        if (res>0){
            return new ResponseDto("200","注册成功",null,true);
        }
        return new ResponseDto("500","系统繁忙，请稍后再试",null,false);
    }

    //效验验证码
    public ResponseDto examineCaptcha(SVCAdminUsersDto adminUsersDto){
        if(adminUsersDto.getCaptcha().
                equals(smsUtil.querySendDetails(adminUsersDto.getPhone(), adminUsersDto.getBizId()))){
            return new ResponseDto("200","验证码正确",null,true);
        }
        return new ResponseDto("500","验证码错误",null,false);
    }


    //登录
    public ResponseDto login(SVCAdminUsersDto adminUsersDto){
        String result=adminUsersDao.login(adminUsersDto);
        if (result!=null){
            return new ResponseDto("200","登录成功",result,true);
        }
        return new ResponseDto("500","用户名或密码错误",null,false);
    }

    //发送短信
    public ResponseDto sendSms(Map<String,String> map){
        String result=smsUtil.sendSms(map.get("state"),map.get("phone"));
        return new ResponseDto("200","发送成功",result,true);
    }

    //忘记密码
    public ResponseDto forgetPassword(SVCAdminUsersDto adminUsersDto){
        adminUsersDto.setUpdatedAt();
        if(adminUsersDao.forgetPassword(adminUsersDto)>0){
            return new ResponseDto("200","修改密码成功",null,true);
        }
        return new ResponseDto("500","系统繁忙，请稍后再试",null,false);
    }

    public AccountDto getOrCreate(String name, String email, String phoneNumber) {
        // 检查用户是否已注册过, 需要调用DAO判断
        Account existingAccount = null;
        if (StringUtils.hasText(email)) {
            existingAccount = new Account();//accountDao.findAccountByEmail(email);
        }
        // 检查用户是否注册过，使用电话号码为参数 需要调用DAO判断
        if (existingAccount == null && StringUtils.hasText(phoneNumber)) {
            existingAccount = new Account();//accountDao.findAccountByPhoneNumber(phoneNumber);
        }

        //如果查询得到，则返回对象,返回前需要将dto与dmo转换
        if (existingAccount != null) {
            return this.convertToDto(existingAccount);
        }
        return this.create(name, email, phoneNumber);
    }

    //DTO和DMO之前相互转换
    private AccountDto convertToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private Account convertToModel(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }



    //创建会员账号
    public AccountDto create(String name, String email, String phoneNumber) {
        if (StringUtils.hasText(email)) {
            // 检查是否存在
            Account foundAccount = new Account();//accountDao.findAccountByEmail(email);
            if (foundAccount != null) {
                throw new ServiceException("用户已存在，请重新输入Email");
            }
        }
        if (StringUtils.hasText(phoneNumber)) {
            Account foundAccount = new Account();//accountDao.findAccountByPhoneNumber(phoneNumber);
            if (foundAccount != null) {
                throw new ServiceException("用户已存在，请重新输入电话号码");
            }
        }

        // 名字，邮箱，电话不能为空
        if (name == null) {
            name = "";
        }
        if (email == null) {
            email = "";
        }
        if (phoneNumber == null) {
            phoneNumber = "";
        }
        //根据传入的数据创建Account对象
        Account account = Account.builder()
                .email(email).name(name).phoneNumber(phoneNumber)
                .build();
        account.setPhotoUrl(account.getEmail());
        account.setMemberSince(Instant.now());

        try {
            //创建会员
            //accountDao.save(account);
        } catch (Exception ex) {
            String errMsg = "不能创建会员";
            //此处记录日志
            throw new ServiceException(errMsg, ex);
        }


        if (StringUtils.hasText(email)) {
            //此处应该发送邮箱验证

        }


        AccountDto accountDto = this.convertToDto(account);
        return accountDto;
    }
}
