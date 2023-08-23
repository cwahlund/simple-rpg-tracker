package simple.rpg.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import simple.rpg.tracker.entity.Classes;
import simple.rpg.tracker.entity.PlayCharacter;
import simple.rpg.tracker.entity.Player;

@Data
@NoArgsConstructor
public class PlayCharacterData {
	private Long characterId;
	
	private Long playerId;
	private String name;
	private String race;
	private int age;
	private int level;
	private Long experiencePoints;
	private int hitPoints;
	
	Player player;
	
	private Set<ClassesData> classes = new HashSet<>();
	
	public PlayCharacterData (PlayCharacter character) {
		this.characterId = character.getCharacterId();
		//this.playerId = character.getPlayerId();
		//this.player = character.getPlayerFromId(playerId);
		this.name = character.getName();
		this.race = character.getRace();
		this.age = character.getAge();
		this.level = character.getLevel();
		this.experiencePoints = character.getExperiencePoints();
		this.hitPoints = character.getHitPoints();
	
		for(Classes cl : character.getClasses()) {
			this.classes.add(new ClassesData(cl));
		}
	}
	
	public PlayCharacter toPlayCharacter() {
		PlayCharacter character = new PlayCharacter();
		
		character.setCharacterId(characterId);
		character.setPlayer(player);
		character.setName(name);
		character.setRace(race);
		character.setAge(age);
		character.setLevel(level);
		character.setExperiencePoints(experiencePoints);
		character.setHitPoints(hitPoints);
		
		for(ClassesData classData : classes) {
			character.getClasses().add(classData.toClasses());
		}
		
		return character;
	}
	
//	private Player getPlayerFromId(Long playerId) {
//		Player player = 
//	}
}
