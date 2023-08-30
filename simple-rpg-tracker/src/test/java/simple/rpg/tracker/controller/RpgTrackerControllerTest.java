package simple.rpg.tracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import simple.rpg.tracker.SimpleRpgTrackerApplication;
import simple.rpg.tracker.controller.model.ClassesData;
import simple.rpg.tracker.controller.model.PlayerData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = SimpleRpgTrackerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
class RpgTrackerControllerTest extends RpgTrackerTestSupport {

	@Test
	void testCreatePlayer() {
		// Given: A player request
		PlayerData request = buildInsertPlayer(1);
		PlayerData expected = buildInsertPlayer(1);
		
		// When: the player is added to the player table
		PlayerData actual = insertPlayer(request);
		
		// Then: the player returned is the one that is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the player table.
		assertThat(rowsInPlayerTable()).isOne();
	}
	
	@Test
	void testRetrievePlayerById() {
		// Given: A player
		PlayerData player = insertPlayer(buildInsertPlayer(1));
		PlayerData expected = buildInsertPlayer(1);
		
		// When: the player is retrieved by player ID
		PlayerData actual = retrievePlayerById(player.getPlayerId());
		
		// Then: the actual player is equal to the expected player.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testRetrieveAllPlayers() {
		// Given: Two players
		List<PlayerData> expected = insertTwoPlayers();
		
		// When: all players are retrieved
		List<PlayerData> actual = retrieveAllPlayers();
		
		// Then: the retrieved players are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testUpdatePlayer() {
		// Given: A player and an update request
		insertPlayer(buildInsertPlayer(1));
		PlayerData expected = buildUpdatePlayer();
		
		// When: the player is updated
		PlayerData actual = updatePlayer(expected);
		
		// Then: the player is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the player table.
		assertThat(rowsInPlayerTable()).isOne();
	}

	@Test
	void testDeletePlayer() {
		// Given: There is one player
		PlayerData player = insertPlayer(buildInsertPlayer(1));
		Long playerId = player.getPlayerId();
		
		// When: you delete the player
		deletePlayer(playerId);
		
		// Then: there are no player rows.
		assertThat(rowsInPlayerTable()).isZero();
	}
	
	@Test
	void testRetrieveClassById() {
		// Given: A player
		ClassesData cls = insertClass(buildInsertClass(1));
		ClassesData expected = buildInsertClass(1);
		
		// When: the player is retrieved by player ID
		ClassesData actual = retrieveClassById(cls.getClassId());
		
		// Then: the actual player is equal to the expected player.
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllClasses() {
		// Given: Two players
		List<ClassesData> expected = insertTwoClasses();
		
		// When: all players are retrieved
		List<ClassesData> actual = retrieveAllClasses();
		
		// Then: the retrieved players are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}
}
