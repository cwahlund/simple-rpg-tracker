package simple.rpg.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playerId;
	
	@EqualsAndHashCode.Exclude
	private String firstName;
	@EqualsAndHashCode.Exclude
	private String lastName;
	@EqualsAndHashCode.Exclude
	private String phone;
	@EqualsAndHashCode.Exclude
	private String email;
	
	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PlayCharacter> characters = new HashSet<>();
}
