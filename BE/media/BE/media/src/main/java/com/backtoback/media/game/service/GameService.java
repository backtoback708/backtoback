package com.backtoback.media.game.service;

import com.backtoback.media.game.domain.Game;
import com.backtoback.media.game.domain.GameActiveType;

import java.util.List;

public interface GameService {
  List<Game> getAllTodayGame();

  public void setGameActive(Long gameSeq, GameActiveType gameActiveType);
}
