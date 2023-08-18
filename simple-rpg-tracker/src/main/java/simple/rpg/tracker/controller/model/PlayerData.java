package simple.rpg.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import simple.rpg.tracker.entity.PlayCharacter;
import simple.rpg.tracker.entity.Player;

@Data
@NoArgsConstructor
public class PlayerData {
	private Long playerId;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	private Set<PlayCharacterData> characters = new HashSet<>();
	
	public PlayerData(Player player) {
		this.playerId = player.getPlayerId();
		this.firstName = player.getFirstName();
		this.lastName = player.getLastName();
		this.phone = player.getPhone();
		this.email = player.getEmail();
		
		for(PlayCharacter playCharacter : player.getCharacters()) {
			this.characters.add(new PlayCharacterData(playCharacter));
		}
	}
	
	public PlayerData(
		Long playerId,
		String firstName,
		String lastName,
		String phone,
		String email	
	) {
		this.playerId = playerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}
	
	public Player toPlayer() {
		Player player = new Player();
		
		player.setPlayerId(playerId);
		player.setFirstName(firstName);
		player.setLastName(lastName);
		player.setPhone(phone);
		player.setEmail(email);
		
		for(PlayCharacterData character : characters) {
			player.getCharacters().add(character.toPlayCharacter());
		}
		
		return player;
	}
}
