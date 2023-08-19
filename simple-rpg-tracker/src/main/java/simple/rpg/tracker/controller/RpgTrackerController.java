package simple.rpg.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
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
}
