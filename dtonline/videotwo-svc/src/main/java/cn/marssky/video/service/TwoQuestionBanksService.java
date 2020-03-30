package cn.marssky.video.service;

import cn.marssky.video.dto.TwoQuestionBanksDto;
import java.util.List;

/**
 * 科目二题库(TwoQuestionBanks)表服务接口
 *
 * @author makejava
 * @since 2020-03-25 16:33:01
 */
public interface TwoQuestionBanksService {

    /**
     * 通过ID查询单条数据
     *
     * @return 实例对象
     */
    List<TwoQuestionBanksDto> queryAll();


}