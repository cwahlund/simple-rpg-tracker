package simple.rpg.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.entity.Player;

public class RpgTrackerTestSupport {
	
	private static final String PLAYER_TABLE = "player";

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
}
