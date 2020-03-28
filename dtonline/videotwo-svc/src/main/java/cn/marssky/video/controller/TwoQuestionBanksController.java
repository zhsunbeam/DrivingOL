package cn.marssky.video.controller;

import cn.marssky.video.dto.TwoQuestionBanksDto;
import cn.marssky.video.service.TwoQuestionBanksService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 科目二题库(TwoQuestionBanks)表控制层
 *
 * @author makejava
 * @since 2020-03-25 16:33:01
 */
@RestController
@RequestMapping("twoQuestionBanks")
public class TwoQuestionBanksController {
    /**
     * 服务对象
     */
    @Resource
    private TwoQuestionBanksService twoQuestionBanksService;

    /**
     * 通过所有数据
     *
     * @return 单条数据
     */
    @GetMapping("selectAll")
    public List<TwoQuestionBanksDto> selectAll() {
        return this.twoQuestionBanksService.queryAll();
    }
}