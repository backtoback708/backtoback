package com.backtoback.business.game.dto.betting;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameResultRes {

    @ApiModelProperty(name = "Home팀 Sequence ID")
    private Long homeTeamSeq;

    @ApiModelProperty(name = "Away팀 Sequence ID")
    private Long awayTeamSeq;

    @ApiModelProperty(name = "이긴 팀 Sequence ID")
    private Long winTeamSeq;
}
