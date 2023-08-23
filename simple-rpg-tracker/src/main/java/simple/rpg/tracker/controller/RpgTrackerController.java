package simple.rpg.tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import simple.rpg.tracker.controller.model.PlayCharacterData;
import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.service.RpgTrackerService;

@RestController
@RequestMapping("/rpgtracker")
@Slf4j
public class RpgTrackerController {
	@Autowired
	private RpgTrackerService rpgTrackerService;
	
	@PostMapping("/player")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlayerData createPlayer(@RequestBody PlayerData playerData) {
		log.info("Creating player {}", playerData);
		return rpgTrackerService.savePlayer(playerData);
	}
	
	@PutMapping("/player/{playerId}")
	public PlayerData updatePlayer(@PathVariable Long playerId, @RequestBody PlayerData playerData) {
		playerData.setPlayerId(playerId);
		log.info("Updating player {}", playerId);
		return rpgTrackerService.savePlayer(playerData);
	}
	
	@GetMapping("/player/{playerId}")
	public PlayerData retrievePlayerById(@PathVariable Long playerId) {
		log.info("Retrieving player with ID={}", playerId);
		return rpgTrackerService.retrievePlayerById(playerId);
	}
	
	@GetMapping("/player")
	public List<PlayerData> retrieveAllPlayers() {
		log.info("Retrieving all players");
		return rpgTrackerService.retrieveAllPlayers();
	}
	
	@DeleteMapping("/player/{playerId}")
	public Map<String, String> deletePlayer(@PathVariable Long playerId){
		log.info("Deleting player with ID=" + playerId + ".");
		rpgTrackerService.deletePlayer(playerId);
		
		return Map.of("message", "Player with ID=" + playerId + " was deleted successfully.");
	}
	
	@DeleteMapping("/player")
	public void deleteAllPlayers() {
		log.info("Attempting to delete all players");
		throw new UnsupportedOperationException("Deleting all players is not allowed.");
	}
	
	@PostMapping("/character")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlayCharacterData createPlayCharacter(@RequestBody PlayCharacterData playCharacterData) {
		log.info("Creating character {}", playCharacterData);
		return rpgTrackerService.saveCharacter(playCharacterData);
	}
	
	@PutMapping("/character/{characterId}")
	public PlayCharacterData updateCharacter(@PathVariable Long characterId, @RequestBody PlayCharacterData playCharacterData) {
		playCharacterData.setCharacterId(characterId);
		log.info("Updating character {}", characterId);
		return rpgTrackerService.saveCharacter(playCharacterData);
	}
}
