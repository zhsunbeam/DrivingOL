package cn.marssky.dtfweb.dao;

import cn.marssky.dtfweb.entities.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ThreequestionDao {
    // 按机动车类型查询科目三视频数量
    int countByavatarType( @Param("avatarType") String avatarType);

    //按机动车类型查询科目三题库 编号，图片，标题
    List<Question> findByavatarType(@Param("avatarType") String avatarType);

    //<按id查询机动车的相关数据
    Question findById(@Param("id")Long id);

    //分组查询机动车类型
    List<String> GroupByAvatarType();
}
