package simple.rpg.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.rpg.tracker.entity.Classes;

public interface ClassesDao extends JpaRepository<Classes, Long> {

}
