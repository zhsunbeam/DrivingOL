package cn.marssky.dtfweb.service;

import cn.marssky.dtfweb.entities.Question;
import cn.marssky.dtfweb.entities.QuestionResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreequestionService {
    // 按机动车类型查询科目三视频数量
    QuestionResult countByavatarType(@Param("avatarType") String avatarType);

    //按机动车类型查询科目三题库 编号，图片，标题
    QuestionResult findByavatarType(@Param("avatarType") String avatarType);

    //<按id查询机动车的相关数据
    QuestionResult findById(@Param("id")Long id);

    //分组查询机动车类型
    QuestionResult GroupByAvatarType();


}
