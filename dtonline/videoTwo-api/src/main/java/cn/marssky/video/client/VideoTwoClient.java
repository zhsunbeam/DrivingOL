package cn.marssky.video.client;

import cn.marssky.video.dto.TwoQuestionBanksDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "videoTwo-service", path = "/twoQuestionBanks", url = "http://localhost:9000/video-svc")
public interface VideoTwoClient {
    @GetMapping(path = "/selectAll")
    List<TwoQuestionBanksDto> selectAll();
}
