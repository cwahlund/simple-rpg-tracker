package simple.rpg.tracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import simple.rpg.tracker.SimpleRpgTrackerApplication;
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
	void testRetrieveAllLocations() {
		// Given: Two locations
		List<PlayerData> expected = insertTwoLocations();
		
		// When: all locations are retrieved
		List<PlayerData> actual = retrieveAllLocations();
		
		// Then: the retrieved locations are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}
}