package com.backtoback.media.video.scheduler;

import com.backtoback.media.game.domain.GameActiveType;
import com.backtoback.media.video.dto.HighLightMessageDto;
import com.backtoback.media.video.dto.HighLightPosition;
import com.backtoback.media.video.dto.MessageDto;
import com.backtoback.media.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Component
public class VideoScheduler {

    private final StreamBridge streamBridge;
    private final VideoService videoService;


//    @Scheduled(cron = "0 0 0 * * *")
//    public void midnightScheduler() {`
//        videoService.makeAllVideoRoom();
//    }
//
//    @Scheduled(cron = "0 0 18 ? * MON-FRI")
//    public void weekdayScheduler() {
//        videoService.startTodayGame();
//    }
//
//    @Scheduled(cron = "0 0 15 ? * SAT,SUN")
//    public void weekendScheduler() {
//        videoService.startTodayGame();
//    }


//    @Scheduled(initialDelay = 10000, fixedDelay = 300000)
//    public void make() {
//    log.info("make!!!!");
//        videoService.makeAllVideoRoom();
//    }

    @Scheduled(initialDelay = 80000, fixedDelay = 300000)
    public void startGame() {
        log.info("start TodayGame!!!!");
        videoService.startTodayGame();
    }

//    @Scheduled(initialDelay = 180000, fixedDelay = 300000)
//    public void makeHighLight() {
//        HighLightMessageDto highLightMessageDto = new HighLightMessageDto();
//        highLightMessageDto.setHighLightPositionList(new ArrayList<>());
//        highLightMessageDto.setGameSeq(1L);
//        highLightMessageDto.getHighLightPositionList().add(new HighLightPosition(10,15));
//        highLightMessageDto.getHighLightPositionList().add(new HighLightPosition(20,25));
//        highLightMessageDto.getHighLightPositionList().add(new HighLightPosition(30,35));
//
//        try {
//          videoService.makeHighLight(highLightMessageDto);
//        } catch (IOException e) {
//          throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//          throw new RuntimeException(e);
//        }
//
//        videoService.sendHighLight(1L);
//        videoService.deleteHighLight(1L);
//    }

}
