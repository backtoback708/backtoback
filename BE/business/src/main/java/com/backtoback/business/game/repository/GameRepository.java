package com.backtoback.business.game.repository;


import com.backtoback.business.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, GameCustomRepository{

	public Game findByGameSeq(Long gameSeq);
}
