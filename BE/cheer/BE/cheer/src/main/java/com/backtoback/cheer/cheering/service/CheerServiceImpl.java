package com.backtoback.cheer.cheering.service;

import com.backtoback.cheer.cheering.dto.response.CheeringInfoRes;
import com.backtoback.cheer.common.exception.business.RedisNotFoundException;
import com.backtoback.cheer.game.domain.Game;
import com.backtoback.cheer.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.backtoback.cheer.common.exception.ErrorCode.REDIS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CheerServiceImpl implements CheerService{

    private final RedisTemplate<String, Integer> redisTemplate;

    private final GameService gameService;

    @Override
    public void readyToStartCheer() {

        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();

        // 현재 날짜인 경기 목록 불러오기
        List<Game> games = gameService.getListByGames(String.valueOf(LocalDate.now(ZoneId.of("Asia/Seoul"))));

        String homeKey, awayKey;

        // [Redis] 각 팀의 응원수 0 저장
        for(Game game : games) {

            homeKey = "cheer:game:" + game.getGameSeq() + ":team:" + game.getHomeTeam().getTeamSeq();
            awayKey = "cheer:game:" + game.getGameSeq() + ":team:" + game.getAwayTeam().getTeamSeq();

            // [Redis] 각 팀의 응원수 0 저장
            valueOperations.set(homeKey + ":count", 0);
            valueOperations.set(awayKey + ":count", 0);

            System.out.println("### readyToStartBetting ##############################################################");
            System.out.println(homeKey + ":count = " + valueOperations.get(homeKey + ":count"));
            System.out.println(awayKey + ":count = " + valueOperations.get(awayKey + ":count"));

            // [Redis] 만료기한
            redisTemplate.expire(homeKey + ":count", 24, TimeUnit.HOURS);
            redisTemplate.expire(awayKey + ":count", 24, TimeUnit.HOURS);
        }
    }

    @Override
    public CheeringInfoRes getCheeringInfo(Long gameSeq) {

        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();

        Game game = gameService.getGame(gameSeq);

        String homeKey = "cheer:game:" + game.getGameSeq() + ":team:" + game.getHomeTeam().getTeamSeq();
        String awayKey = "cheer:game:" + game.getGameSeq() + ":team:" + game.getAwayTeam().getTeamSeq();

        Integer homeCount = valueOperations.get(homeKey + ":count");
        Integer awayCount = valueOperations.get(awayKey + ":count");

        if(homeCount == null || awayCount == null) throw new RedisNotFoundException(
                "해당하는 key 정보가 Redis에 존재하지 않습니다.", REDIS_NOT_FOUND);

//        System.out.println("### readyToStartBetting ##############################################################");
//        System.out.println(homeKey + ":count = " + homeCount);
//        System.out.println(awayKey + ":count = " + awayCount);

        return CheeringInfoRes.builder()
                .homeCount(homeCount)
                .awayCount(awayCount)
                .build();
    }

    @Override
    public CheeringInfoRes startCheering(Long gameSeq, Long teamSeq) {

        ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();

        String key = "cheer:game:" + gameSeq + ":team:" + teamSeq;

        Integer count = valueOperations.get(key + ":count");

        if(count == null) throw new RedisNotFoundException("해당하는 key 정보가 Redis에 존재하지 않습니다.", REDIS_NOT_FOUND);

        valueOperations.set(key + ":count", count + 1);

        return getCheeringInfo(gameSeq);
    }
}
