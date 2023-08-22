package simple.rpg.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.rpg.tracker.entity.PlayCharacter;

public interface PlayCharacterDao extends JpaRepository<PlayCharacter, Long> {

}
