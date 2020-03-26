package cn.marssky.account.dao;

import cn.marssky.account.dto.SVCAdminUsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminUsersDao {

    //手机号码或昵称的查询
    int soleValid(@Param("phone") String phone, @Param("name") String name);

    //注册
    int signup(SVCAdminUsersDto adminUsersDto);

    //登录
    String login(SVCAdminUsersDto adminUsersDto);

    //忘记密码
    int forgetPassword(SVCAdminUsersDto adminUsersDto);
}
