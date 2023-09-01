package simple.rpg.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import simple.rpg.tracker.controller.model.ClassesData;
import simple.rpg.tracker.controller.model.PlayCharacterData;
import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.dao.ClassesDao;
import simple.rpg.tracker.entity.Classes;
import simple.rpg.tracker.entity.Player;

public class RpgTrackerTestSupport {
	
	private static final String PLAYER_TABLE = "player";
	private static final String PLAY_CHARACTER_TABLE = "play_character";
	
	@Autowired
	private ClassesDao classesDao;

	// @formatter:off
	private static final String INSERT_CHARACTER_1_SQL = """
			INSERT INTO play_character
			(player_id, name, race, age, level, experience_points, hit_points)
			VALUES(1, 'Ug', 'Half-Orc', 17, 1, 120, 14)
			""";
	
	private static final String INSERT_CHARACTER_2_SQL = """
			INSERT INTO play_character
			(player_id, name, race, age, level, experience_points, hit_points)
			VALUES(2, 'Bobbo', 'Halfling', 28, 1, 150, 9)
			""";
	
//	private static final String LINK_CLASS1 = """
//			INSERT INTO play_character_classes
//			(character_id, class_id)
//			VALUES (1, 2)
//			""";
//	
//	private static final String LINK_CLASS2 = """
//			INSERT INTO play_character_classes
//			(character_id, class_id)
//			VALUES (2, 1)
//			""";
	
	private PlayerData insertPlayer1 = new PlayerData(
			1L,
			"Cosmo",
			"Spacely",
			"555-555-5555",
			"spacely@test.com"
	);
	
	private PlayerData insertPlayer2 = new PlayerData(
			2L,
			"Spencer",
			"Cogswell",
			"555-555-1212",
			"cogswell@test.com"
	);
	
	private PlayerData updatePlayer1 = new PlayerData(
			1L,
			"George",
			"Jetson",
			"555-212-1212",
			"gjetson@test.com"
	);
	
	private ClassesData insertClass1 = new ClassesData(
			1L,
			"Fighter",
			"Simple, Martial",
			"Action Surge",
			"None"
	);
	
	private ClassesData insertClass2 = new ClassesData(
			2L,
			"Barbarian",
			"Simple, Martial",
			"Rage",
			"None"
	);
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RpgTrackerController rpgTrackerController;
	
	protected PlayerData buildInsertPlayer(int which) {
		return which == 1 ? insertPlayer1 : insertPlayer2;
	}
	
	protected int rowsInPlayerTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAYER_TABLE);
	}

	protected PlayerData insertPlayer(PlayerData playerData) {
		Player player = playerData.toPlayer();
		PlayerData clone =  new PlayerData(player);
		
		clone.setPlayerId(null);
		return rpgTrackerController.createPlayer(clone);
	}
	

	protected PlayerData retrievePlayerById(Long playerId) {
		return rpgTrackerController.retrievePlayerById(playerId);
	}
	
	protected List<PlayerData> retrieveAllPlayers() {
		return rpgTrackerController.retrieveAllPlayers();
	}

	protected List<PlayerData> insertTwoPlayers() {
		PlayerData player1 = insertPlayer(buildInsertPlayer(1));
		PlayerData player2 = insertPlayer(buildInsertPlayer(2));
		
		return List.of(player1, player2);
	}	

	protected PlayerData buildUpdatePlayer() {
		return updatePlayer1;
	}
	
	protected PlayerData updatePlayer(PlayerData playerData) {
		return rpgTrackerController.updatePlayer(playerData.getPlayerId(), playerData);
	}
	
	protected void deletePlayer(Long playerId) {
		rpgTrackerController.deletePlayer(playerId);
	}
	
	protected ClassesData buildInsertClass(int which) {
		return which == 1 ? insertClass1 : insertClass2;
	}
	
	protected ClassesData insertClass(ClassesData classData) {
		Classes cls = classData.toClasses();
		ClassesData clone =  new ClassesData(cls);
		
		clone.setClassId(null);
		
		Classes dbPlayer = classesDao.save(cls);
		return new ClassesData(dbPlayer);
	}
	
	protected ClassesData retrieveClassById(Long classId) {
		return rpgTrackerController.retrieveClassById(classId);
	}
	
	protected List<ClassesData> insertTwoClasses() {
		ClassesData class1 = insertClass(buildInsertClass(1));
		ClassesData class2 = insertClass(buildInsertClass(2));
		
		return List.of(class1, class2);
	}
	
	protected List<ClassesData> retrieveAllClasses() {
		return rpgTrackerController.retrieveAllClasses();
	}

	protected void insertCharacter(int which) {
		insertPlayer(buildInsertPlayer(1));
		insertPlayer(buildInsertPlayer(2));
		String characterSql = which == 1 ? INSERT_CHARACTER_1_SQL : INSERT_CHARACTER_2_SQL;
		jdbcTemplate.update(characterSql);
	}

	protected PlayCharacterData buildCharacter(int which) {
		PlayCharacterData character = new PlayCharacterData();
		
		Long characterId;
		PlayerData playerData;
		Player player;
		String name;
		String race;
		int age;
		int level;
		Long experiencePoints;
		int hitPoints;
		
		if (which == 1) {
			characterId = 1L;
			playerData = buildInsertPlayer(1);
			player = playerData.toPlayer();
			name = "Ug";
			race = "Half-Orc";
			age = 17;
			level = 1;
			experiencePoints = 120L;
			hitPoints = 14;
		} else {
			characterId = 2L;
			playerData = buildInsertPlayer(2);
			player = playerData.toPlayer();
			name = "Bobbo";
			race = "Halfling";
			age = 28;
			level = 1;
			experiencePoints = 150L;
			hitPoints = 9;
		}
		
		character.setCharacterId(characterId);
		character.setPlayer(player);
		character.setName(name);
		character.setRace(race);
		character.setAge(age);
		character.setLevel(level);
		character.setExperiencePoints(experiencePoints);
		character.setHitPoints(hitPoints);
		
		return character;
	}
	
	protected PlayCharacterData retrieveCharacter(Long which) {
		PlayCharacterData pcd = rpgTrackerController.retrieveCharacterById(which);
		
		PlayerData playerData = buildInsertPlayer(1);
		Player player = playerData.toPlayer();
		pcd.setPlayer(player);
		
		return pcd;
	}
	
	protected int rowsInCharacterTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, PLAY_CHARACTER_TABLE);
	}
}
