package simple.rpg.tracker.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import simple.rpg.tracker.entity.Classes;

@Data
@NoArgsConstructor
public class ClassesData {
	private Long classesId;
	
	private String name;
	private String weapons;
	private String abilities;
	private String magic;

	public ClassesData(Classes classes) {
		this.classesId = classes.getClassesId();
		this.name = classes.getName();
		this.weapons = classes.getWeapons();
		this.abilities = classes.getAbilities();
		this.magic = classes.getMagic();
	}
	
	public Classes toClasses() {
		Classes classes = new Classes();
		
		classes.setClassesId(classesId);
		classes.setName(name);
		classes.setWeapons(weapons);
		classes.setAbilities(abilities);
		classes.setMagic(magic);
		
		return classes;
	}
}