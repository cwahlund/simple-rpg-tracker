package simple.rpg.tracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.function.IntPredicate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import simple.rpg.tracker.SimpleRpgTrackerApplication;
import simple.rpg.tracker.controller.model.ClassesData;
import simple.rpg.tracker.controller.model.PlayCharacterData;
import simple.rpg.tracker.controller.model.PlayerData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = SimpleRpgTrackerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
class RpgTrackerControllerTest extends RpgTrackerTestSupport {

	@Test
	void testCreatePlayer() {
		// Given: A player request is made
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
		// Given: A player exists and
		PlayerData player = insertPlayer(buildInsertPlayer(1));
		PlayerData expected = buildInsertPlayer(1);
		
		// When: the player is retrieved by player ID
		PlayerData actual = retrievePlayerById(player.getPlayerId());
		
		// Then: the actual player is equal to the expected player.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testRetrieveAllPlayers() {
		// Given: Two players exist
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
		// Given: A class exists
		ClassesData cls = insertClass(buildInsertClass(1));
		ClassesData expected = buildInsertClass(1);
		
		// When: the class is retrieved by player ID
		ClassesData actual = retrieveClassById(cls.getClassId());
		
		// Then: the actual class is equal to the expected class.
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllClasses() {
		// Given: Two classes exist
		List<ClassesData> expected = insertTwoClasses();
		
		// When: all classes are retrieved
		List<ClassesData> actual = retrieveAllClasses();
		
		// Then: the retrieved classes are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testCreateCharacter() {
		// Given: A character can be added
		insertCharacter(1);
		PlayCharacterData expected = buildCharacter(1);
		
		// When: the character is retrieved from the character table
		PlayCharacterData actual = retrieveCharacter(1L);
		
		// Then: the character returned is the one that is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the character table.
		assertThat(rowsInCharacterTable()).isOne();
	}

	@Test
	void testRetrievePlayCharacterById() {
		// Given: A character exists and
		insertCharacter(1);
		PlayCharacterData expected = buildCharacter(1);
		
		// When: the character is retrieved by character ID
		PlayCharacterData actual = retrieveCharacterById(expected.getCharacterId());
		
		// Then: the actual character is equal to the expected character.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testRetrieveAllCharacters() {
		// Given: Two players exist
		List<PlayCharacterData> expected = insertTwoCharacters();
		
		// When: all players are retrieved
		List<PlayCharacterData> actual = retrieveAllCharacters();
		
		// Then: the retrieved players are the same as expected.
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void testUpdateCharacter() {
		// Given: A character and an update request
		insertCharacter(1);
		PlayCharacterData expected = buildUpdateCharacter();
		
		// When: the character is updated
		PlayCharacterData actual = updateCharacter(expected);
		
		// Then: the character is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the character table.
		assertThat(rowsInCharacterTable()).isOne();
	}
	
	@Test
	void testDeleteCharacter() {
		// Given: There is one character
		insertCharacter(1);
		PlayCharacterData character = buildCharacter(1);
		Long characterId = character.getCharacterId();
		
		// When: you delete the character
		deleteCharacter(characterId);
		
		// Then: there are no character rows.
		assertThat(rowsInCharacterTable()).isZero();
	}
	
	@Test
	void testDeletePlayerWithCharacters() {
		// Given: A player and two characters
		PlayerData player = insertPlayer(buildInsertPlayer(1));
		Long playerId = player.getPlayerId();
		
		insertCharacterWithExistingPlayer(1);
		insertCharacterWithExistingPlayer(2);
		
		linkCharacterClass(1);
		linkCharacterClass(2);
		
		assertThat(rowsInPlayerTable()).isOne();
		assertThat(rowsInCharacterTable()).isEqualTo(2);
		assertThat(rowsInCharacterClassesTable()).isEqualTo(2);
		int classesRows = rowsInClassesTable();
		
		// When: the player is deleted
		
		deletePlayer(playerId);
		
		// Then: there are no player, play_character, or play_character_classes rows
		assertThat(rowsInPlayerTable()).isZero();
		assertThat(rowsInCharacterTable()).isZero();
		assertThat(rowsInCharacterClassesTable()).isZero();
		
		// And: the number of classes rows has not changed.
		assertThat(rowsInClassesTable()).isEqualTo(classesRows);
	}

}
