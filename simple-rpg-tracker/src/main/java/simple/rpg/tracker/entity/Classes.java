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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Classes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classesId;
	
	@EqualsAndHashCode.Exclude
	private String name;
	@EqualsAndHashCode.Exclude
	private String weapons;
	@EqualsAndHashCode.Exclude
	private String abilities;
	@EqualsAndHashCode.Exclude
	private String magic;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "classes")
	private Set<PlayCharacter> characters = new HashSet<>();
	
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@ManyToMany(cascade = CascadeType.PERSIST)
//	@JoinTable(
//			name = "play_character_classes",
//			joinColumns = @JoinColumn(name = "class_id"),
//			inverseJoinColumns = @JoinColumn(name = "character_id")
//	)
//	private Set<PlayCharacter> characters = new HashSet<>();
}
