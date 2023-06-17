package com.backtoback.media.game.service;


import com.backtoback.media.game.domain.Game;
import com.backtoback.media.game.domain.GameActiveType;
import com.backtoback.media.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    @Override
    public List<Game> getAllTodayGame() {
        return gameRepository.getAllTodayGame();
    }

    @Override
    public void setGameActive(Long gameSeq, GameActiveType gameActiveType) {
        Optional<Game> gameOptional = gameRepository.findById(gameSeq);

        if(gameOptional.isPresent()){
            Game game = gameOptional.get();
            game.setGameActiveType(gameActiveType);
        }

    }
}
