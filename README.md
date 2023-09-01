# simple-rpg-tracker
 
A simple example role-playing game tracker for players and characters

## Purpose ##
This project was created as my final project for the Promineo back end bootcamp program.  The project is an example of using JPA to create needed REST methods for manipulation of a MySQL database.

----------
### Technologies ###
- [Java 17](https://www.java.com/en/)
- [Maven](https://maven.apache.org/)
- [Java Spring ](https://spring.io/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://www.mysql.com/)
- [DBeaver](https://dbeaver.io/)
- [JUnit 5](https://junit.org/junit5/)

Written with [Eclipse IDE](https://www.eclipse.org/ide/)

----------
This sample program illustrates a way to keep track of a simple role-playing game's players, their characters (with statistics), and the classes the characters are.  The players can have many characters in a one to many relationship, while the characters and classes can have multiple associations with a many to many relationship.  The ERD can be found in rpg.drawio.
![](https://screenrec.com/share/RSBeiDwsh6)

----------
All four CRUD operations are active on the players and characters table, as well as an auto-delete of characters when the players associated are deleted.  The Classes table represents core rules information and is read only.
## Contact Information ##
Since this is a final project for the course I will not be able to accept any code modifications at this time.  However, feedback and suggestions will be gracefully accepted.  :)

I can be reached at wahlund@icloud.com for any inquiries.
[LinkedIn profile.](https://www.linkedin.com/in/collinwahlund/)