package cn.marssky.dtf.controller;

import cn.marssky.video.client.VideoTwoClient;
import cn.marssky.video.dto.TwoQuestionBanksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoTwoClient videoTwoClient;
    @GetMapping("/queryTwoSubjectsVideo")
    public List<TwoQuestionBanksDto> queryAllTwoSubjectsVideo(){
        return this.videoTwoClient.selectAll();
    }
}
