package cn.marssky.account.dao;

import cn.marssky.common.dto.AdminUsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminUsersDao {

    //手机号码或昵称的查询
    int soleValid(@Param("phone") String phone, @Param("name") String name);

    //注册
    int signup(AdminUsersDto adminUsersDto);

    //登录
    String login(AdminUsersDto adminUsersDto);

    //忘记密码
    int forgetPassword(AdminUsersDto adminUsersDto);

    String getName();
}
