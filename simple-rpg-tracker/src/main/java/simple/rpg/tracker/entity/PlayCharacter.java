package simple.rpg.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PlayCharacter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long characterId;
	
	@EqualsAndHashCode.Exclude
	private String name;
	@EqualsAndHashCode.Exclude
	private String race;
	@EqualsAndHashCode.Exclude
	private int age;
	@EqualsAndHashCode.Exclude
	private int level;
	@EqualsAndHashCode.Exclude
	private Long experiencePoints;
	@EqualsAndHashCode.Exclude
	private int hitPoints;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "player_id", nullable = false)
	private Player player;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "play_character_classes",
			joinColumns = @JoinColumn(name = "character_id"),
			inverseJoinColumns = @JoinColumn(name = "class_id")
	)
	private Set<Classes> classes = new HashSet<>();
}
