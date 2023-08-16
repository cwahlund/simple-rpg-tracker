package simple.rpg.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private String name;
	private String weapons;
	private String abilities;
	private String magic;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "classes")
	private Set<PlayCharacter> characters = new HashSet<>();
}
