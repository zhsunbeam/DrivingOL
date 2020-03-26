package cn.marssky.dtfweb.controller;

import cn.marssky.dtfweb.entities.Question;
import cn.marssky.dtfweb.entities.QuestionResult;
import cn.marssky.dtfweb.service.ThreequestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class ThreequestionController {
    @Resource
    private ThreequestionService threequestionService;

    @GetMapping("/countByavatarType/{avatarType}")
    public QuestionResult countByavatarType(@PathVariable("avatarType") String avatarType){
        log.info("机动车类型"+avatarType);
       QuestionResult questionResult =  threequestionService.countByavatarType(avatarType);
        log.info("视频总数"+questionResult.toString());
        return questionResult;
    }

    @GetMapping("/findByavatarType/{avatarType}")
    public QuestionResult findByavatarType(@PathVariable("avatarType") String avatarType){
        log.info("机动车类型"+avatarType);
        QuestionResult questionResult =  threequestionService.findByavatarType(avatarType);
        log.info(questionResult.toString());
        return questionResult;
    }

    @GetMapping("/findById/{id}")
    public QuestionResult findById(@PathVariable("id" )Long id){
        log.info("视频编号为"+id);
        QuestionResult questionResult = threequestionService.findById(id);
        log.info("查询结果为"+questionResult.getData());
        return questionResult;
    };

    @GetMapping("/GroupByAvatarType")
    public QuestionResult GroupByAvatarType(){
        QuestionResult questionResult  =  threequestionService.GroupByAvatarType();
        log.info("汽车类型" + questionResult.getData());
        return questionResult;
    };


}
