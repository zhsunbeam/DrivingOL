package cn.marssky.video.dao;

import cn.marssky.video.model.TwoQuestionBanks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 科目二题库(TwoQuestionBanks)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-25 16:33:01
 */
@Mapper
@Repository
public interface TwoQuestionBanksDao {

    /**
     * 查询所有数据
     *
     * @return 实例对象
     */
    List<TwoQuestionBanks> queryAll();


}