package simple.rpg.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.rpg.tracker.entity.Player;

public interface PlayerDao extends JpaRepository<Player, Long> {

}
