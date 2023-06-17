package com.backtoback.media.video.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HighLightMessageDto implements Serializable {

  private Long gameSeq;

  private List<HighLightPosition> highLightPositionList;

  @Override
  public String toString() {
    return "HighLightMessageDto{" +
            "gameSeq=" + gameSeq +
            ", highLightPositionList=" + highLightPositionList +
            '}';
  }
}
