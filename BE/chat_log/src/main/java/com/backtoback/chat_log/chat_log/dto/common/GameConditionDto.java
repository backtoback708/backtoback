<<<<<<<< HEAD:BE/business/src/main/java/com/backtoback/business/common/dto/GameConditionDto.java
package com.backtoback.business.common.dto;
========
package com.backtoback.chat_log.chat_log.dto.common;
>>>>>>>> 602eb45664a022fc158607d9befefcdc5a6dface:BE/chat_log/src/main/java/com/backtoback/chat_log/chat_log/dto/common/GameConditionDto.java

import java.io.Serializable;

import com.backtoback.business.game.domain.GameActiveType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameConditionDto implements Serializable {

	private Long gameSeq;
	private GameActiveType gameActiveType;
	private Long mediaStartTime;

	@Builder
	public GameConditionDto(Long gameSeq, GameActiveType gameActiveType, Long mediaStartTime) {
		this.gameSeq = gameSeq;
		this.gameActiveType = gameActiveType;
		this.mediaStartTime = mediaStartTime;
	}

	@Override
	public String toString() {
		return "GameConditionDto{" +
			"gameSeq=" + gameSeq +
			", gameActiveType=" + gameActiveType +
			", mediaStartTime=" + mediaStartTime +
			'}';
	}

}