package simple.rpg.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import simple.rpg.tracker.controller.model.PlayerData;
import simple.rpg.tracker.dao.PlayerDao;
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

}
