README file for the Sprite System
Author: Drew Sternesky, Jimmy Mu, Marcus Molchany
Date Started: September 13, 2010
Last Updated: November 15, 2010

Utilized the CPS 108 class site, specifically:
http://www.cs.duke.edu/courses/cps108/fall10/assign/03_engine/
http://www.cs.duke.edu/courses/cps108/fall10/resources/conventions/documentation.php
http://www.cs.duke.edu/courses/cps108/fall10/resources/conventions/design.php

Utilized readings resource page http://www.cs.duke.edu/courses/cps108/fall10/resources/ 

Utilized the Javadoc page http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html 


Description

The purpose of this package was to provide functionality that the Golden T Sprite was lacking. 
Originally the package contained a class titled GameEntitySprite, but this has been renamed to
Sprite and is now located in vooga.engine.core. 

The vooga.engine.core.Sprite class can represent any renderable object in the game 
environment. The vooga.engine.core.Sprite class extends the 
com.golden.gamedev.object.Sprite class but provides two additional useful
features: the option to switch between renderable images and the capacity to contain 
any attributes in the form of a Stat. 

Like the Golden T Sprite class, the Vooga Sprite class supports update and render
behaviors for a Sprite object. In addition, the Sprite class contains all the images
and attributes that a Sprite object needs. The functionalities supported by the 
Sprite class include rendering image animation, setting Sprite location and 
velocity, switching to a different image, and allows for the updating of Sprite 
attributes. 

Resource files required by the project (including format of non-standard files)

	Golden T Game Engine requires the Java Archive file golden_0_2_3.jar

Known bugs, crashes, or problems with the project's functionality

	There are no bugs that we have found yet in our project.

Highlights any extra features included in the project

	See High-Level documentation for more information about features.