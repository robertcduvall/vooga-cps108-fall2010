High Level Engine - README

1. names of all people who worked on the project

Drew Sternesky
Jimmy Mu
Marcus Molchany

2. date you started, date you finished, and an estimate of the number of hours worked on the project

Date Started: September 13, 2010
Date Finished: September 27, 2010

3. each person's role in developing the project

Each person contributed to the brainstorming session where the original project concept was formed. For the extensive part of the project, we worked together and wrote the GameEntitySprite, PlayerSprite, and the ItemSprite classes.  

Drew Sternesky: Provided the initial files that the project was built on/around. Wrote the BallToBrickCollision class and the BallToPaddle Collider class. Wrote the class LifeItem and SlowItem. Designed IPlayerController interface. 

Jimmy Mu: Designed the Controller files including the classes KeyboardController, MovementKeys and MouseController. Wrote the WallBoundsManager class.

Marcus Molchany: Wrote the ItemToPaddleCollision Class

4. any books, papers, or online resources that you used in developing the project

Utilized the CPS 108 class site, specifically:
http://www.cs.duke.edu/courses/cps108/fall10/assign/03_engine/
http://www.cs.duke.edu/courses/cps108/fall10/resources/conventions/documentation.php
http://www.cs.duke.edu/courses/cps108/fall10/resources/conventions/design.php

Utilized readings resource page http://www.cs.duke.edu/courses/cps108/fall10/resources/ 

Utlized the Javadoc page http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html 


5. files used to start the project (the class containing main) 

	This project was based on Drew Sternesky and Brent Sodman’s game BreakerPong. We took their original idea and decided to create an abstract model that would describe all item and player system. The GameEntity class, the Player class, the Item class, and the BasicCollisionManager supported by Golden T are all used to start the main class, BreakerPong. 



6. any data or resource files required by the project (including format of non-standard files)

	Golden T Game Engine requires the Java Archive file golden_0_2_3.jar

7. any known bugs, crashes, or problems with the project's functionality

	There are no bugs that we have found yet in our project.

8. highlights any extra features included in the project

	See High-Level documentation for more information about features.