package simple.rpg.tracker.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import simple.rpg.tracker.controller.model.PlayCharacterData;
import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.dao.PlayCharacterDao;
import simple.rpg.tracker.dao.PlayerDao;
import simple.rpg.tracker.entity.PlayCharacter;
import simple.rpg.tracker.entity.Player;

@Service
public class RpgTrackerService {

	@Autowired
	private PlayerDao playerDao;
	
	@Transactional(readOnly = false)
	public PlayerData savePlayer(PlayerData playerData) {
		Player player = playerData.toPlayer();
		Player dbPlayer = playerDao.save(player);
		
		return new PlayerData(dbPlayer);
	}

	@Transactional(readOnly = true)
	public PlayerData retrievePlayerById(Long playerId) {
		Player player = findPlayerById(playerId);
		return new PlayerData(player);
	}
	
	public Player findPlayerById(Long playerId) {
		return playerDao.findById(playerId)
				.orElseThrow(() -> new NoSuchElementException("Player with ID=" + playerId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public List<PlayerData> retrieveAllPlayers() {
		List<Player> playerEntities = playerDao.findAll();
		List<PlayerData> playerDtos = new LinkedList<>();
		
		for (Player player : playerEntities) {
			PlayerData playerData = new PlayerData(player);
			playerDtos.add(playerData);
		}
		
		return playerDtos;
	}

	@Transactional(readOnly = false)
	public void deletePlayer(Long playerId) {
		Player player = findPlayerById(playerId);
		playerDao.delete(player);
	}
	
	@Autowired
	private PlayCharacterDao playCharacterDao;

	@Transactional(readOnly = false)
	public PlayCharacterData saveCharacter(PlayCharacterData playCharacterData) {
		PlayCharacter playCharacter = playCharacterData.toPlayCharacter();
		Player player = findPlayerById(playCharacter.getPlayer().getPlayerId());
		
		playCharacter.setPlayer(player);
		
		PlayCharacter dbCharacter = playCharacterDao.save(playCharacter);
		
		return new PlayCharacterData(dbCharacter);
	}

	@Transactional(readOnly = true)
	public PlayCharacterData retrieveCharacterById(Long characterId) {
		PlayCharacter character = findCharacterById(characterId);
		return new PlayCharacterData(character);
	}
	
	public PlayCharacter findCharacterById(Long characterId) {
		return playCharacterDao.findById(characterId)
				.orElseThrow(() -> new NoSuchElementException("Character with ID=" + characterId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public List<PlayCharacterData> retrieveAllCharacters() {
		List<PlayCharacter> characterEntities = playCharacterDao.findAll();
		List<PlayCharacterData> characterDtos = new LinkedList<>();
		
		for (PlayCharacter character : characterEntities) {
			PlayCharacterData playerData = new PlayCharacterData(character);
			characterDtos.add(playerData);
		}
		
		return characterDtos;
	}

}
