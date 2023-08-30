package simple.rpg.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import simple.rpg.tracker.entity.Player;
import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.entity.Classes;
import simple.rpg.tracker.controller.model.ClassesData;
import simple.rpg.tracker.dao.ClassesDao;

public class RpgTrackerTestSupport {
	
	private static final String PLAYER_TABLE = "player";
	
	@Autowired
	private ClassesDao classesDao;

	// @formatter:off
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
}
