# Introduction #

Friday's class notes on our API Revision - feel free to edit or add more details.


# Details #

CS108 API Revision - Class Notes 11/12/10

**Resources**
  * XML - these files can be verbose, will try to keep them as short as possible
  * Will provide a Randomizer
  * World Clock - its initiation should probably be placed in the Core
  * High Scores - database management / read- and write-capabilities

**Overlay**
  * Will make it easier to work with Overlays
  * OverlayManager - relic left over from Greenfoot - get rid of it if no objection
  * OverlayPanel will extend SpriteGroup directly
  * OverlayStatImage will probably be gotten rid of
  * OverlayCreator will be modified
  * For stats, a map will be used that has names as keys
  * Countdown clock - will be its own state, when done counting it will change to the GameState it’s been passed
  * Question: clickable Overlays? (will GameState have a solution for this?)

**GameState**
  * Will be fixing bug with SpriteGroup. Also will be able to create a GameState out of a PlayField
  * Cool feature called Layer - can have multiple GameStates active and will render them in order of their Layers
  * For example, open a menu while towerdefense is running, and towerdefense game will still be running behind it
  * Will be changing int indexing of GameStates
  * May make layer system use a stack
  * Will solidify GameStateManager’s position in the Core
  * Will be making GameOver, Pause, and Menu GameStates that can be extended
  * Moving towards model where what a GameState is is a self-contained module where the player is executing a specific interaction
  * GameStates will be abstract...deal with it
  * Will be building a generic button class
  * Not trying to reinvent the Level system - only when you fundamentally change the mode of interaction the player is experiencing will the GameState change
  * Don’t assign layered GameStates backgrounds...usually if your game disappears, this is why

**Factory**
  * Purpose is to read from file and give game (GameState) a PlayField
  * Work together with Resources group (XML parser)
  * Can we allow for both options aka a tag in XML for defining a tile-based level definition format that gives people freedom to put in “giant strings” that define a level
  * Have a way to group Resources from Resources group into “instantiation groups” (?)
  * Will need to load levels one at a time, when they are needed, to avoid running out of memory
  * Provide a way for random sprites to be generated at game start
  * Design goal: be able to swap out images to provide for a new look of game, aka Zombieland becomes Professorland just by changing the images
  * For Zombieland, generation of enemies is based on an algorithm, not a set of coordinates - would this be supported by the Rules group?
  * LevelFactory may provide for an initial number of enemies, then Rules group could take over from there
  * Will need to talk to Resources and GameEntitySprite regarding generation of Sprites and SpriteGroups
  * Extending Playfield would be worthwhile - include an OverlayTracker?

**Player Control**
  * Want to create an easy way to map a keystroke/mouseclick to a game action
  * Want to make ability to change controls during run time - work with Resouces group’s “Profile” concept?
  * Concentrate on documentation
  * Will separate into more clearly-defined packages
  * Will allow for multiple actions to be performed at the same time
  * Make preferences as general as possible - games could have a preferences page that details difficulty, number of enemies, etc. - this would be separate from the controls preferences

**Rules and Goals**
  * Fills gap between players and GameStates
  * Fill in game parameters that aren’t specific to every sprite - aka Rules
  * Common characteristics to each level but parameters can be different - for example, level of AI, bonus points, etc.
  * Goals - useful to determine when the level is over - need some way to determine whether a certain goal is achieved, then other parts of the game can determine what to do as a result of this goal being achieved
  * Rules themselves may be specified in a file that Factory could take care of, but the Rules group would be checked
  * Best situation - build a manager of goals and rules

**Player**
  * We provide: GameEntitySprite, which can use Control
  * GameEntitySprite can define a player as something that can be represented by multiple kinds of sprites
  * We put stats in player...use them!
  * Definitely do these things: rename GameEntitySprite to Core.Game.Sprite, and make a great example for everyone to follow

**Core**
  * Cool new interface to Game?
  * Help Factory team identify common elements between games
  * Need a Game XML file?
  * We will look through each Game's code and determine where there is repetition that could be extracted