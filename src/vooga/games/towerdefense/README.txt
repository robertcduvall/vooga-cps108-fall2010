Tower Defense
by Justin Goldsmith, Derek Zhou, Daniel Koverman

Game Description:
The object of Tower Defense is to maintain your self esteem for as long as 
possible. Self esteem is reduced when an enemy makes it all the way to the 
end of the path. The only way to stop enemies is to place towers to attack 
them as they travel along the path.

API Use:
Core: (very helpful)
We use Game, PlayField, and BetterSprite from the core. Game allows us to initialize 
the game with a minimal amount of code. We no longer have an update or render method
in our DropThis class. PlayField is used as a container to hold 
almost all game elements and replaces the use of the GoldenT PlayField for our game. 
Likewise, BetterSprite replaces our use of the GoldenT Sprite class.

Control: (very helpful if we could get it working)
We use Control to handle the mouse input for controlling the player which would be 
very helpful. Unfortunately we were unable to get it to actually control the player
even though it runs without errors making trouble shooting difficult.

Event: (very helpful)
Events have taken over the responsibilities for making things happen in the game. They 
have allowed us to remove many dependencies within the game. Primarily, different 
objects no longer have to be aware of the PlayField they are in to add objects to it. 
For instance, Player can add Towers to the level by way of an event, the 
EnemyGenerator can add enemies to the level by way of an event, etc. Most of the logic 
for making the game work are held in events.

Factory: (very helpful, but appears to be broken)
We attempted to get the Factory to load the MainMenu which is effectively a 
collection of BetterSprites (the Buttons and Title) and a background. However, an 
error is preventing the actual creation of PlayFields from XML files. We have 
responded by commenting out the MainMenu GameState in the game to prevent the game 
from crashing, but leaving the infrastructure in place for when the LevelParser does 
work. Out other GameState, that PlayStat, we hardcoded how to load so that we would 
have something functional, although this will be switched to XML as well eventually.

Level: (one part is potentially helpful, rules we do not see a use for)
As a side effect of the update process, we only have one functional level so the
concept of switching between levels is not useful to us currently. The other concept 
represented in the Level API, rules, seems to us to be the same thing as event. Both 
event and rule has a trigger condition which is checked in the game loop and an 
action to perform is that condition is true. Where to use a rule versus an event 
seems to be completely arbitrary so the only rules we used were the GameWon and 
GameLost rules which could have easily been events instead.

Overlay:
The Overlays were used basically as before, they are simple to use and did not provide any problem.
Once we loaded the overlay xml with the overlayCreator, it returned a tracker.  We got the stats, and
the sprite groups from the tracker, and it worked as expected.


Resource: (very helpful)
We used the Resource API only for the Resource class which we use to hold all images,
most if not all numeric constants, all String file paths, and most if not all Strings.
It has been very helpful to have static access to all of these things. Randomizer and 
WorldClock both don't have use for the project as designed, but future game play 
improvements might use them. The database functionality will likely be useful once 
we get to the point where we have time to add things like player preferences rather 
than not having enought time to get the game to simply work.

State: (very helpful)
We currently have two states: play and main menu. Most of the initialization occurs
within these states. From our previous game, we know that when the full game is 
implemented state switching will be required to display a game over screen or restart 
from the main menu or pause. However, we currently don't have all the game states we 
plan to implement.