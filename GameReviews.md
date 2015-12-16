# Introduction #

For each game listed below, your team should review its own game and the next game alphabetically. Your review should focus on how the game can be improved (or simplified) by using the VOOGA (or GTGE) APIs. The comments should generally be constructive in helping us to build a consensus API platform on which to build more complex games. Your review should include the entire game, not just the one class that extends Game.


## Cyberion ##

Our game, Cyberion, is a very simple and fun game in which you control a ship that tries to stay alive by either shooting the enemy ships down or simply avoiding the enemy ships and their "bullets".  There are bonuses you can recieve to increase your firepower to make it easier to shoot the enemy ships.

Our main method (other than update and render) in the main game class is initResources.  This method then calls various other methods which set sprites, collision detection, and event managers.  We created a collision group and manager for each possible collision using Golden T's built-in collision manager.

One thing we can improve on is making the code more dynamic.  For example, we hard-code each image file path.  In general, we have many sections with almost identical code, such as creating a collision manager for each collision type.  Another thing is using more of the Vooga API.  Right now we are only using the Event Manager API.

-Cyberion Team

The keyboard control class that’s part of the player/control systems API in vooga can improve how this game handles interactions between external inputs, specifically key presses, and the objects/players in the game. Currently, the game uses the event and event manager to control the interactions between the keyboard input and the player’s movement and actions, as well as the interactions among different objects in the game, namely the player ship, the enemy ships, and the shots fired by both groups. While this system works for the game mechanics, it takes a lot of work in setting up the foundations for the interactions. In this version of the game, each event is created individually and has two separate entities, an event listener class and an event class. These classes don’t provide any extra functionality except for passing objects and information around as intermediaries. And for each action to take place, the playerShip class much check for which key is pressed and then trigger the corresponding event. For example, the move method in the playerShip class checks for each of the arrowkeys, sets the position of the ship with a +/- 5 offset, and then send the new location information to trigger the playerMove event. This can be done easily by simply updating the position of the player without interacting with the event system at all. This entire algorithm can be simplified by using the keyboard control system, which associates a key to call a specific action in a class. So instead of checking for each event in all the object classes and writing listeners and actions for each interaction that could take place, the keyboard control calls a method corresponding to an action for each of the object, such as firing a bullet or moving the player ship. This way, all the controls can be set up in the main game and all the actual game mechanics can be written in the respective object classes rather than being scattered around the listeners and events. This makes the game much lest complicated and more readable since all the algorithms that have anything to do with an object will be in that object class.

This game can also be improved using the overlay system and the player class in the vooga API. The player class would make it easy to interact with the player’s health and score since each playerSprite created already has a set of functionalities built-in. Currently, this game has no way of keeping track of lives or the score of the game; the game just goes on forever. It would be nice to have displays of the number of lives and the player’s score and maybe even the number of kills somewhere unobtrusive on the game screen to show the state of the game. This can be set up using the overlay system in the vooga API by creating objects to hold such information and updating the information every time the game updates.

The game’s code is generally very readable. However, there are some general comments we would like to make. It’s nice to use constants for numbers like the width and height of the game world and the default spawn location of the player. Also, there’s a lot of repeated code in StarGroup.java. It can be improved by writing a separate method that adds each star with the parameters passed in by function calls.

Overall, it’s a very fun game. Like the explosion sound. What happened to the background music?

-Zombieland

Cyberion Review 2
Currently, the cyberion game produces a file not found exception for the file imageList.txt, which is caused by the harde coded path of the file on line 180:                     Resources.loadFile("trunk/src/vooga/games/cyberion/Resources/imageList.txt");
This would cause an error for anyone whose vooga directory is setup or named differently. It would be best if the Cyberion group could make the file paths relative instead of absolute path from the top level.

The group has moved all the resource handling into the maingamestate class.They used the resources system to load the images necessary for the game from a text file that lists the locations of the images. As mentioned above, the file paths would be much more flexible if they are relative to the local directory (“resources/image.png”) rather than being the absolute path from the top level directory. The group also used the keyboard control API under the vooga player/item system to handle external inputs to the game. Other than that, much of the game mechanics remained the same, using events, states, the GoldenT collision system, and th player/item api.

Much of the code in the main game class are commented out. They should be removed since they no longer have any value remaining in the main game class.

-Zombieland

## Doodle Jump ##

Overall we think we did a good job on our game, especially since it was only our first iteration.  The major things we have to fix are more labor intensive like writing longer, more comprehensive, accurate level files rather than serious faults with our code or logic behind the game.  The game graphically looks very good and we incorporated a lot of the iOS version of DoodleJump into our game in a short period of time.

Code-wise, we could definitely use a little more of the class’ APIs but we also didn’t do that bad of a job in that respect initially.  We used the Player API extensively and we also made use of the Overlay and GameState APIs.  In our next iteration we could probably make use of the Resources API as well as maybe the Level API if they take our suggestions and look at the DoodleLevel class in our game and see how we can reconcile our two implementations of levels.  A lot of what determines how we use the APIs in the future is how much better the class make them.  We don’t think we neglected anything that would have made our game a lot better in any respect.  There’s also some code cleanup/commenting/documenting that we will do in our next version.

Game-wise, the one thing we really need to do is make our level files much more robust.  The game at the end of the day is only as good as the levels we play in and right now they’re pretty short and basic.  The game will seem much, much better after we improve our levels.  While we do have some clear improvements we have to make for the next version, our initial version of Doodlejump we think was a success and shows the potential of the vooga game engine. -- (Doodle Jump)

We would review Doodle Jump very similarly to our game (Cyberion).  It is a good game with the same general principals of initialization and collision detection.  Also, just like ours, they need to worry about making the code more dynamic and removing repetitive code and method calls.  They did use more APIs than us, but could still probably incorporate more, especially once they are improved.

-Cyberion Team

Doodle Jump is a very appealing and entertaining game that is very similar to the actual iPhone/iPod app.  The only problems I can see in the actual playing of the game is 1) trampolines are placed randomly on the screen instead of directly on top of blocks and 2) it is very hard to shoot a monster because it seems like you can only shoot directly up with the space bar, although there may be a way to aim that we are not aware of.

To follow up on the comments made previously, it seems like they have added one more vooga API, game state.  This is certainly an improvement, but more integration is probably necessary if we want to move away from Golden T's 'game' eventually.  It also seems like the overall principals they are using have stayed the same, which is not a bad thing.  They did a great job improving on the general refactoring, especially making the program more readable by splitting up the extremely long and wordy initResources method into multiple other methods.

The use/addition of game states is very apparent.  Throughout the code, you can see the gamestates set up and used, and it is very nice to see in the actual game itself.  It still seems like there is some repetitive code, especially with collision detection, but that is probably because the class decided to stick with Golden T for that.  A problem that still exists for Doodle Jump and most groups is importing images and files with hard-coded strings.

Overall, it seems like Doodle Jump is a good game that has made many improvements, but there is always ways to keep improving.

- Cyberion Team Update



In the revised and refactored version of Doodle Jump most of the changes were made inside of DoodleGame. Blocks of code that took up several lines but were only instantiating one object got moved into a method to reduce the size of the code in the constructor, initResources() and update(long).

In the DoodleGame constructor makeOverlayStrings() contains all of the code to create and instantiate the scoreString, startString and winString. Previously the initResources() method was very bulky and looked unorganized. This code was simplified into four methods: initStates(), initSpriteGroups(), initDoodle(), and initCollisions(Level). initStates() contains all of the code needed to initialize the GameState variables, initSpriteGroups contains all of the code to initialize the SpriteGroup variables, initDoodle contains all of the code to initialize the DoodleSprite variable and initCollisions contains all of the code to initialize the CollisionManager variables.

The update(long) method was revised to use GameStates to control functionality in the game instead of using integers to keep track of what state the game was in. This method was refactored by making a method for each state the game was in that would appropriately activate or deactivate the GameState variables. These methods were resetGame(), playGame(), gameOver() and pauseGame(). A method updateGame(long) was also implemented to reduce the amount of code within update(long).

The render(Graphics2D) method was changed to render the background first if the current GameState was play or last if the current GameState was startMenu or pauseMenu. The OverlayStrings were also rendered based on the current GameState in this method.

Several bug fixes were made to the instance variables in DoodleGame() and inside of update(long) and render(Graphics2D). Previously integers were used to keep track of the variable score. The change to Stat(Integer) reduced code in DoodleGame() and made updating OverlayStrings much easier. In update(long) there was difficulty checking to see if a level was completed. Before passScore was being compared to the OverlayString value, in the revised version passScore is compared against the Stat(Integer) score. Also in initResources() passScore and score are set to 0 which fixed a bug when the game was reset and levels could not be completed because passScore was always greater than score. Previously in render(Graphics2D) the start menu screen and pause menu screen were being rendered behind the sprites in the game. In the revised version the render calls for these backgrounds were being made after the PlayField was being rendered when appropriate.

DoodleSprite was revised by changing a line in render to call “game.gameOver()” if doodle died which made the GameStates and update(long) method in DoodleGame function properly.. -- (DoodleJump Revised Review)

## Galaxy Invaders ##

Our game, GalaxyInvaders, initially did not use many of the APIs from VOOGA, as we found it simpler (and faster) to implement their functionalities ourselves. We are going to change this in refactoring, and replace our custom-built Level class, for example, with an extension of the Levels API. The APIs we did use, however, we found to be quite useful, including the Player and Overlay groups’, which fit in with our game pretty well. Clearly, our next step is to replace our code with as much API code as possible, including using the Events API more extensively, and the Resources API to handle all of our images and possibly our level files.

One of the APIs we do not really envision a need for is the Collisions API, as collisions are handled automatically by the GTGE. We, along with most groups, implemented them directly via GoldenT and they worked quite well. While we understand that this API could in theory provide easier functionality, in its current state it seems to require even more code (such as extending Collidable) than the GTGE built-in detection.

Our code is still somewhat rough. The proprietary level system currently has file paths hard-coded in, which is not extensible at all. We had some difficulties with the Overlay API; first, I had to extend OverlayStat to change its color, and second, I wasn’t able to get the OverlayManager working properly so it was impossible to position each Overlay on the screen. This is why there is an OverlayStat for the player score but just a regular drawString() for the player lives. Finally, we need to make greater use of resources. Some of our int variables are constants, but nearly all of our Strings are not, which makes their modification more difficult than it should be.

SECOND REVIEW
The revised version of our game, GalaxyInvaders, implements many more of the APIs from VOOGA, as opposed to before when we implemented some of their functionalities ourselves. We replaced our custom-built Level class, for example, with extensions of the LevelFactory and LevelManager classes from the Levels API. Our LevelFactory still reads files very similar to our original level files but now conforms to the interface provided, allowing us to use use it with the pre-built LevelManager class. This also helped us by eliminating the hard-coded level file paths from the code and abstracting them to a file. We drastically improved and simplified our use of the Overlay API, by using an overlays.xml file and abstracting out the stats and text. We found the Overlays class a little confusing at first, but with the help of the example we were able to get it running without much trouble. Another API we utilized was the Resources API, which we implemented to take care of all of our images and resources.
> One API which really helped us improve functionality was the GameState API, which allowed us to create pause, play and game over states. While we chose to use our own rather than the ones made already, the GameStateManager and the API in general were very useful in toggling back and forth between states.
> The only API we did not utilize was the Events API, which we did not see a need for in our code. If we run into a situation where it would help us though, we would be confident in implementing it.
> Another thing we did to improve our code was refactoring the public methods in GalaxyInvaders into collections of smaller helper methods that greatly improve the code organization and readability.This helped us avoid duplicating code, and made our code much more readable, especially from the main class.  In addition, we pulled as many constant numbers from our classes to make the values easy to modify as well and the code clearer. Overall, we managed to successfully implement all but one of the APIs, although we could still make more improvement, including formatting our level files (so that they are more easily readable to a human, rather than just a list of numbers) and making it more concise. We could also make better use of the Control system from the Player API, to simplify our listener structure and make the controls more easily changed.

--GalaxyInvaders team

Can’t run game.  Even changing the input for level11.txt, etc. generates an error as those files are not in the vooga repository.  This group needs to add the proper resources correctly (level files, images, etc.) and then fix whatever hard coded file names they have in their code.  -- (Doodle Jump)

After changing the hardcoded pathname for level1.txt, level2.txt and level3.txt the game correctly ran on each of our computers. This is a fun game that accurately emulates the classic Galaxy Invaders. The items that added health were unique, it would be a nice addition to have power ups that altered the shots coming from the ship. Also it was nice that as the level increased the difficulty of the aliens and the number of aliens changed. Something other than a black background would be something to add, maybe even different backgrounds for different levels.

In your code it was clever to separate the initialization of enemies and blocks into two different methods. This adds to the readability of the code and if you ever need to add new enemies/blocks it will be a simple fix. We would suggest that you try to utilize Golden T’s PlayField class, and attach each Background, Sprite and SpriteGroup to it so that you can simplify your update and render methods to “playfield.update()/playfield.render()”. Also the moveLeft and moveRight methods should really be moved into the PlayerSprite class for your ship to take full advantage of that API. The StatInt was a good choice for keeping track of your players score. -- (Doodle Jump)

The refactored version of Galaxy Invaders is well refactored and runs smoothly.  The levels are no longer hardcoded and all of the code is very well commented and organized.  Thus, it was easy to follow and determine what code did what in each class/method/call.  One of the more specific aspects of the Galaxy Invaders code which I enjoyed was use of both the gamestate and the level (in the form of level factory) API’s.  The game itself has a nice menu which states controls as well as a nice pause menu and a restart function.  All of these features make it easy to understand and play.

As for the actual game, Galaxy Invaders is smooth and is a good representation of the original.  It was interesting to have the invaders move by blocks instead of a smooth transition (it actually makes the game a bit harder as bullets that you fire if not in the correct line will always miss).  Also, bullets which you fire can go through the 3 blockades which offer protection.  This might be a glitch but makes the game a little cheap (even if the blockade has limited HP).  Another thing is that the game ends early at times and finishes with a game over screen making it impossible to beat.  Even though only 3 levels are present, it would have been a nice touch to make a congratulatory screen at the end.

A couple things to consider for future development would be expansion of upgrades and levels.  As is, it is pretty simple (albeit fun) game that ends abruptly; at times even without killing all the enemies or dying.  There is also no reward system nor variation in enemies.  A shop to upgrade your defenses would have added some personalization as well as flavor to the game. -- (DoodleJump Revised Review)

## Grandius ##

Possible ideas to consider:

-remove constructor in main Game class

-remove repetition in updateScreenSprites(): make SpriteGroup and int (number in the sprite list of the level) parameters instead.

-in updateEnemies(), split some of the for loops into separate methods. for the keyevents, make a method that calls in the SpriteGroup and a double to add to thespeed. that way it will take away the repetitive for-loops. have a different method for each arrow key.

-separate loadGrandiusLevel() into different methods

-BlackHole and Missile could extend ItemSprites, by using the act() method whenever they swallow enemies or make a hit and passing the max hits constants into the constructor.

-the game state control is implemented with an integer switch; seems like using the GameState API could simplify this

SECOND REVIEW

After the Grandius refactoring, they implemented some other APIs that they had not originally (including LevelFactory and GameStates).
Otherwise, to make the code more easily readable, this team could continue to break the longer methods into even smaller ones (especially initResources()) and remove some of the duplicated or similar code (including instances like the key presses in updateEntities(), in updateCashOnCollision and updateScoreOnCollision, in buildShoppingLevelState and buildLevelCompleteState), firing Beams in Reacher). They can just call gameState.addGroup() which adds parameter to both update and render groups in buildPlayState() in order to reduce the length.
Some issues that still exist:
-when ship is hit, if it is hit by something long it takes away all of its lives because it tries to respawn it in the same spot. something like a ship invisibility timer (like, nothing can hit you for 5 seconds after you respawn) would fix this. also seems that the collisions between player and large projectiles are imprecise (collides even when images don’t appear to be colliding).
-Overlays seem to not display on second level
-currently, there is no way to restart the game

--GalaxyInvaders team

The game itself is quite simple, though a little difficult to get used to in the beginning.  The movement of the screen relative to the player takes some getting used to, but other than that, it’s a pretty challenging, yet very playable game.  Also, the shopping round in between levels is a fun concept.

The Collisions package in Grandius does not utilize the VOOGA collision API, and it also treats each combination of collision differently using different classes of collisions.  There’s a decent amount of repeated code within each of these collision classes, and though handling the various types of collisions is quite complex, perhaps the different collision classes could have been combined more efficiently.

Grandius makes pretty good use of some of the other API’s available in VOOGA and even creates a custom Level class for Grandius (which almost makes the Level API’s value questionable).  It maybe could have used more of the tools in VOOGA since it only used 4 of the packages (Level, overlay, player, and resource).  The overall look and playability of the game is appealing and fun.
--Grandius team

## Jumper ##
The game play is pretty simple and easy to figure out.  Perhaps a short screen with instructions and game objectives at the beginning would be useful for those not familiar with this type of game.  But that’s not a huge issue since it’s quite simple, and it’s a pretty fun game.  However, the game can get pretty mindless and repetitive after about a minute of playing it.

Jumper uses a few of the VOOGA API’s, but it follows the same trends as some other groups in choosing which API’s to actually use (such as resource and overlay).  It does create a JumperGameState class which is never used (or at least, it’s been commented out).

There are some segments of repeated code.  For instance, the createNewBlocks() method in the main class has a huge if-tree with a lot of lines that look similar.  I think this could have been reduced by a lot.  As mentioned in class, a lot of the hard-coded String values, especially the repeated ones, could have been stored as private constant variables.Another instance of repeated code is the goRight and goLeft methods of DoodleSprite class. Every line in these methods is exactly the same, so these could have been combined into one method, and the parameter passed to this method would decide whether to accelerate the Doodle to the right or to the left.

Even though Block Id’s have been created for each type of block (normal, spring, not broken and jetpack), when writing the DoodleToBlockCollision class, these id’s are not used. Instead, the numbers assigned to these blocks are used as such. Using these hard-coded numbers would be something we want to avoid. We would also like to change these block id’s to be constants as opposed to variables since the id associated with a particular block type will never change.
--Grandius team

> - REVIEW II -
Jumper now just goes straight into game play with no instructions, and there’s a new jump feature in the game which I only know about because it was mentioned in class.  I still think it would’ve been useful to have a short instruction screen at the beginning (even if it was just to let players know they can jump).

Relatively, the game as a whole does a pretty good job using vooga’s API’s.  I like that it uses the new Overlay xml parsing feature, and due to the nature of the game, I can see how implementing a LevelManager / Factory could be considered useless.  I think the game could’ve utilized the Player and Item Sprites in the player.control API, such as having DoodleSprite extend PlayerSprite and making the jetpack an ItemSprite.  Also, the extension of GameState is a little unclear to me.  I feel that the game did not need a subclass of GameState to get the functionality it provides.

The code would benefit from use of the ResourcesBeta class, which has functionality to allow for setting a default path from which to access files. This way, in the resourcelist.txt file, “resources/” would not need to be typed before every filename, since it is common among them.

Random numbers are used in the createNewBlocks() method, and it may be beneficial to the group to utilize the resource package’s Randomizer class to implement new and possibly more advanced functionality. If multiplayer is ever considered, the Randomizer class could assist by providing a way for both players to experience the same game progression (which blocks appear when, bonuses, etc). The game makes good use of the GameClock class to maintain a timer for the jetpack.

The game still has instances of repeated code in the Jumper class. The huge if-else block could have been handled better by replacing it by an appropriate method.

A few magic numbers exist in the code, and a lot of the integer and double values could have been moved to a file. In terms of general look of the code, there is at least one method that is not currently used - getClockTime() - and there are comments throughout that could be removed.

--Grandius Team

## MarioClone ##
**Review 1**

The design of our game began as a platformer similar to Mario.  It would have levels that consist of tiles that have different traits.  The player would walk in one dimension and have the ability to jump and crouch as well.  Most enemies would attack the player as he or she progresses through the level by either touching the player from the top, bottom, or side, or by firing a projectile.  The player could kill an enemy by jumping on it.  A boss battle would begin upon the completion of one or more levels.  A boss would be a special type of enemy that requires a different strategy to defeat than merely jumping on top of it.

By the time the game was due, not all of these features were able to be implemented.  Means to create a level's map were created via the TileMap class.  A TileMap consists of a list of Tile objects, each of which contains a position, an image or images, and a behavior.  Tiles are subclasses of Golden T's Sprite class, so they implement Collidable and are, therefore, able to interact with other sprites.  Three different types of Tile were implemented so far, an indestructible tile that does nothing on collisions, a breakable one that disappears after being hit from below by the player, and a mutating one that cycles between images on these upward collisions.  A map can be loaded from a text file that uses different characters as stand-ins for different tile types.

Much of the time spent developing MarioClone went into sorting out collision detection bugs that have still not been resolved.  At some point, it was necessary to use the elements we had already created to yield a playable game.  As we hadn't dealt with issues like adding multiple levels, having levels that scroll (i.e., levels can be larger than one screen), and (most majorly) having collisions with the sides of tiles work, we made a single-screen game in which a player aims to kill enemies as they are generated at some time interval.  Each enemy and the player begin with a health of 100.  Each time the player jumps on an enemy, he or she deals the enemy 50 damage, and each time an enemy runs into a player, the player is dealt 10 damage.  The game is won if no enemies remain.  The player loses if he or she dies.

On its own, this type of game can be very fun if implemented correctly.  Obviously, it is necessary for collisions to work well before the game can function.  Other improvements would be adding health indicators for the player and for the enemies.  Multiple stages can be added to add variety.  Currently, tile behavior does not have an effect on gameplay. Relying of this behavior to complete the game would be a good use of our TileMap infrastructure.

--MarioClone team

**Review 2**

Since the first review was written, the functionality of our game has been increased enormously.  The most important change in gameplay was the addition of scrolling to levels so that the player's movement and the tile map are not restricted to just one screen of play.  Also, there now exist three different levels through which one must progress before winning the game.

Collisions in MarioClone were greatly improved via the overriding of Golden T's CollisionManager revertPosition and getCollisionSide methods.  While this still does not work perfectly, it is a large step towards playability.

A system by which tiles are able to spawn items was created, as was an ItemTile class to place such tiles in a MarioClone level.  So far, one item has been made, the GravityItem.  This item, when picked up my Mario, changes the coefficient of gravity (relative to a reasonable default) that he experiences to a specified value.

New in-game graphics were designed to make the game more aesthetically appealing to the player.  Now, Mario and enemies have underlying animated sprites that display the characters walking while their horizontal velocities are nonzero.

States in MarioClone are now handled by VOOGA GameState instances, of which a GameStateManager keeps track.  We created three state classes, MainMenuState, GamePlayState, and EndGameState.  In the main MarioClone class, instances of each of these are created (1 for each of MainMenuState and GamePlayState, and 2 for EndGameState for win and lose outcomes).

A MarioLevel class was created to contain all of the information that one level of the game requires, such as a MarioPlayField and overlays.  While this approach works, efforts will be made in the coming days to transition to VOOGA's LevelFactory API.  In the long run, this will aid in extensibility.

Once a game is complete, a game over screen is displayed.  This screen either congratulates the player for winning or disparages him or her for losing.  It also displays a high score list with the highest five scores recorded and the times at which they were recorded.  This was done using the VOOGA HighScoreHandler API.

Support for cheat codes is in progress.  Currently, a player can type "GRVTY" at any point during gameplay in order to change Mario's gravity coefficient to .2 and "NORM" to change it back to 1.  Support for more cheat codes and the names of their respective callback methods to be loaded from a file will be available soon.

--MarioClone team

When playing MarioClone, I was never really clear about the objective or progression of the game.  It seemed as if this was a cool idea for a game that never truly materialized.

In terms of the actual code, I am curious as to why this group used conventional Playfields for its “menu”, “end”, and “win” fields.  This seems like the perfect scenario for GameStates, because this would allow an easier—and cleaner—transition from state to state.  This change could cut down on a ton of repeated code, particularly in the initResources() method.  Also, this would eliminate the need for a “myGameState” variable as well as the “MAIN\_MENU”, “GAME\_PLAY”, “GAME\_OVER”, and “GAME\_WIN” constants, which all serve as messy constants that the State system was designed to eliminate.

I am not sure what the line “distribute = true” means.

All instance variables should consistently use (or not use) the “my” prefix.

It seems a bit sloppy/awkward to have a variable named “playfield” followed by the initialization of three Playfield objects.

The MarioPlayField extension of PlayField does not seem to have any additional function beyond the loading of tiles.  It seems like this could just be done to a regular playfield in the main game’s update/render methods

I am unclear as to what a TileMap is, although perhaps this is just my own oversight (but some comments might have helped).

There are a bunch of hardcoded values in the render method that would mess everything up if the size of the board changed.

Some javadoc comments before methods and classes might have been helpful.

--Second Review--

The MarioClone game does not currently compile, so it is difficult to test the gameplay of this game, although I would certainly applaud this group for their ambitious game design.

An actual MarioPlayField is set up which does not seem to add much beyond the Playfield class which it extends.

There is a “BetterCollisionGroup” class.  We agreed in class to only use the Golden T collision system.

There is a hardcoded string for the font setup.  As we discussed in class, this should probably be corrected.

One big improvement to the game code was the introduction of game state classes, which makes this code way better.  Also, the introduction of javadoc comments is really helpful to understanding the code.


## Tower Defense ##

The Tower Defense game has the basic premise of killing the enemies before they finish the path.  Our twist on the game was that you could never win.  The goal was to obtain a high score before your self-esteem was inevitably destroyed.

We used the ResourcesBeta class from the Resources API to load all of our images from a file at initialization. These images are then accessed at run time by the Overlay, individual Towers, and enemies. Access is simple due to the static nature of the ResourcesBeta class.

The static Event Manager class was just implemented and it allowed simplification of the code and a more reasonable distribution of responsibilities. For instance we eliminated the requirement to pass the Game to every class that had to interact with Sprite Groups. Now classes which add to Sprite Groups simply fire an event and then the Sprite is added to the Sprite Group elsewhere.

The control of the player is done using two Control subclasses, one which handles keyboard input and one which handles mouse input. This was done in a handful of lines during initialization instead of setting up a complex system of listeners in the update step. Our game does not require handling of simultaneous controls (such as moving and shooting) so the Control class was sufficient for our needs.

The way the PlayerCursor manages what Tower it is building has been overhauled. Towers are now arranged in a better hierarchy. This allows PlayerCursor to find qualities about the subclasses of th current towers using Tower methods instead of reflection which looked at static methods.

We also used the new overlay creator to create our overlays in an xml file.  It turned the initOverlay method from about 100 lines to about 7.

The game states were extraordinarily helpful.  Although we did not implement there pause or menu game states, making our own was very easy.    Now that the game states uses sprite groups instead of collections of sprites it makes, also is helpful for changing between levels.  The only functionality that was not there was the ability to remove a sprite group from a game state.  In the end we decided to just add a method to the game state API which removes a sprite group from the state.

The only API we did not use was levels.  This was because currently the level is basically a sprite factory.  And at the beginning of every level we had no sprites on the board besides the overlays.



-- Tower Defense Team


I was very impressed with TowerDefense on the initial play-thru. I thought the game was entertaining, engaging, mostly bug-free, and humorous (You’ve got to love defending against Duvall’s incessant attacks against our self-esteem). I did, however encounter a bug with the ranges of some of the towers. I built a tower on the corner, and it only shot at things in the left/upper most portion of its radius, but wouldn’t hit targets to the right (even though they were still in range). In order to make the code more efficient, the group should focus on removing the hard-coding of file names in references of both images and fonts. There is also quite a bit of repetition in many different methods in the code. I think the team could use loops more effectively, as well as pass in certain arguments in order to get the desired result, other than hard coding each situation. Take a look at initOverlays, initPlayer, createPath, createEnemies, and begin. Each of these methods have some form of repeated code that could get refactored. Also, JavaDoc comments would be nice. Other than that, you guys have a very solid game – well done.

In particular, TowerDefense seems to make good use of the Overlay, Player, Resource, and Gamestate VOOGA APIs. However, it seems to ignore most of the others. For example the Level API in particular could be used to improve the complexity and length of the game.

–Cameron and Andrew (MarioClone)


## Tron ##

Our game is called TRON in which multiple players try to kill each other by blocking the others’ path. The players can turn around and proceed like in the Snake game, except that they leave walls  behind them wherever they go. A player is dead whenever it hits the wall built by other players or itself, or when it hits the boundary of the game space. The players can either be controlled by human or by a computer.

Our design flow was to implement the game first and then port it to the VOOGA APIs.
So right now it relies on GTGE to handle collision, player, resources and level. Our game makes use of the Collision Manager that GoldenT provides to handle the collisions, routinely update the player’s positions and determine the end of the game. Our game generates “random” levels with random-numbered, random-sized blocks. A new random level is generated whenever a level is finished (regardless of which player wins). Separate player objects are created for each player. The computer-controlled players implement the AI algorithms that controls their movement.

For the APIs in Vooga, the game only used the userInputEvent in event system to handle user input such as down, up ,right and left. The userInputEvent could be improved by allowing game developer to customize their keys. Using Overlay is also important because the game might allow player to choose whether it is one player or two player game. Since the game didn't use that much outside resouces such as pictures and sounds, it might not be necessary to use resources API.

Our next natural step would be to utilize more API’s to improve the succinctness and functionality of the game. Right now we are only using the event system, and we plan to at least make use of the player system, the level system and the collision manager. We will also improve our AI, probably creating different levels of AI for the computer-controlled player. We would also like to improve the graphics of the game. In the end, if we have time, we would implement a networked game where different human players can play from different computers vi networks. We would also need to add something like overlay or GUI in order for the player to choose game mode such as player vs PC and player vs player.


--Meng Li,Brent Sodman,Jiaqi Yan Tron Game

The Tron game could be improved by utilizing more API's to accomplish tasks. This would cut down on the amount of code they wrote themselves. For example, a lot of their bonuses could be generated by an Event handler instead of inside their MainGame class.

The game itself could do with instructions and a menu screen and other game elements. When running the game, it jumps straight into the game. When I first opened the game, I did not know what Tron was and had to discover the game out as I continued to play. I did not know when the game was over or how the game was played. I think the game could be improved signifigantly by adding these features. This is very easily done by using the States API which would allow a pause screen and a menu screen without disturbing the actual game or affecting any code already written.

Also, their Enemy control was good, but could be better if difficulty could be assessed and changed. I think this could be facilitated with use of the Level API. As each level's playfield is random but the difficulty of the opponent could change, Level API would help without cluttering the code. Also, I think the block generation could be improved by making sure that the blocks were not generated right in front of the player or the computer at the beginning of the game.

The Collision API suits this game as most of the game is based on collision, whether with objects, opponents, or bonuses. There were 3 dedicated classes to specific collisions, yet the handling of the collisions occurred within the actual game. By changing these classes to a Collision manager and making the objects Colliables, the code inside the main class would be much easier to read and adhere to the open/closed principle.

I agree with their implementation without a Resource Manager as there are essentially only 3 objects with limited behavior. In summary, I believe adding use of the Event, Collision, and State API's would greatly benefit this game. -- Tower Defense

It seems that the fixes weren't fixed. Blocks are still generated on the initial path of the two players. The AI difficulty does not increase after every game. There is still a glitch where you can go through the lines of another player including yourself.

Since the Collision API was removed, the Collision system seems to be the same and still has the flaw of going through another player. I assume this is due to the generation of the sprites.

I appreciate the switch to the resource manager even though there are only 2 players; as it makes the code much easier to read. But I feel like music should also be put inside the resource manager. There is a lot more use the Tron team could have obtained from the Resource class especially for the random generations.

There is still no pause screen or a menu screen. So there is no use of the states API which would help.

However, I do like the fact that they switched a lot of their Objects into Collections.

-- Second Review

## Zombieland ##

Overall the Zombieland game is nicely designed and it has pretty graphics and animations. However right now it is not using any of the API's. Therefore potentially it can integrate the game with more API's. Since this game already has a lot of pictures, it can put them in the resource manager. Also the level manager and player managers can be properly added. Another issue right now is that the zombies come towards the player in all directions, while the player can only shoot at straight up/down/left/right directions, which makes it very hard to actually shoot any zombies coming at the player. Hence it would be nice if the player could be able to turn around in any angle and shoot the zombies.

--Meng Li,Brent Sodman,Jiaqi Yan

RESPONSE:
The new version of the ZombieLand game has drastically improved and currently utilizes many of vooga API’s that we were not able to implement in the past. Currently, we are using the player/item api, the overlay api, Golden T collision api (now the official vooga collision api), the state api, and the control api inside the player/item api. For the player/item system, we utilized the map between states to animatedSprite to effectively change game states in resource to game interactions. For overlay api, we used the overlay classes to render strings and images, such as “Health:” and health bar, during the game play. We implemented the player’s control system by using the addInput method to store what the keyboard should listen to easier. The state system API was used to create a Pause state in which all the sprites on the screen would disappear and “Pause” would be displayed on the screen. For the most part, the State system took over for Golden T’s playfield, except for background and overlay display purposes.

We did not however use the resource manager because we had literal values inside our of resource files and we thought resource bundle was an easier solution for now (We have talked with members of the resource groups and we hope to combine resource bundle capability provided by Java with the resource handler class).

The API’s used in the game are:
1. Player/ Item System
2. Overlay System
3. Golden T’s collision system
4. Control
5. States

---


> There are several aspects of the ZombieLand game that can be optimized. First, instead of hardcoding the string name for the filepaths, it would be much easier if the resourceHandler class was used or if .properties files were used to obtain the filepath string associated with the location of image files. Second, we can also use file handlers (especially .properties file) to load specific integer values (i.e. location of the healthbar on the screen) from file. Third, some of the local variables (BufferedImage variables) should be contained inside the initResource() method because they are only used once. Fourth, spriteGroups can be added to the playfield. This can greatly simplify the update method because we can simply call from playfield.update() and playfield.render() and we should be able to take care of sprites.  The events and level packages were also not implemented, and they may be looked into for the refactor.

There are plenty of places where the code can be refactored. To make the initResources method make more sense, it would be useful to extract the separate functions into methods such as initPlayer or initOverlays to show what they are really doing. Also, adding the overlays to the playfield would reduce the need to update each of the overlays separately.

-Zombieland

RESPONSE:
In the ZombieLand game, we have made several improvements in addition the adopting vooga’s apis to make our game more readable and cleaner. For start, we completely switched to the use of resource bundle (a feature that is soon-to-be part of the Resource Beta package) to get rid of literals and filepaths to image locations. we also removed as many magic constants in the game as we could. In addition, we have modularized our methods our initResource() method by breaking it down into several of its component functions. Now, our initResource() method is a wrapper initialization method that takes care of overlay initialization, player initialization, image initializaiton, reflection initialization, and zombie initialization. For compacting and reducing global variables, we tried to get rid of as many global variables as we could. We decided to keep the bufferedImages arrays as global variables because we have a method called addZombie(), which would drastically clog up the game speed if we load each zombie every time. Instead, we decided to save a copy of the bufferedImages arrays as global variables. Doing so save use time and improved the overall game play performance. Similarly, for other parts of our classes, we have simplified many complex methods down, allowing readers of our code to better understand how the ZombieLand game works.

Several code improvements we have made include:
1. no hard coded image files locations
2. no magic constants
3. no excessive global variables
4. modularized methods
5. Improved readability of code via method renaming and comments