DROP TABLE IF EXISTS character_classes;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS play_character;
DROP TABLE IF EXISTS player;

CREATE TABLE player (
	player_id int NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(256) NOT NULL,
	last_name VARCHAR(256) NOT NULL,
	phone VARCHAR(30),
	email VARCHAR(320),
	PRIMARY KEY (player_id)
);

CREATE TABLE play_character (
	character_id int NOT NULL AUTO_INCREMENT,
	player_id int NULL,
	name VARCHAR(256) NOT NULL,
	race VARCHAR(64) NOT NULL,
	age int,
	level int NOT NULL,
	experience_points int,
	hit_points int,
	PRIMARY KEY (character_id),
	FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE CASCADE
);

CREATE TABLE classes (
	class_id int NOT NULL AUTO_INCREMENT,
	name VARCHAR(256) NOT NULL,
	weapons VARCHAR(256),
	abilities VARCHAR(1024),
	magic VARCHAR(256),
	PRIMARY KEY (class_id)
);

CREATE TABLE character_classes (
	character_id int NOT NULL,
	class_id int NOT NULL,
	FOREIGN KEY (character_id) REFERENCES play_character (character_id) ON DELETE CASCADE,
	FOREIGN KEY (class_id) REFERENCES classes (class_id) ON DELETE CASCADE
);