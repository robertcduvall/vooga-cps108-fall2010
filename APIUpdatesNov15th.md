# Details #

**Factory**
Levels will now be represented using XML files. SpriteGroups and their constituent Sprites, CollisionGroups and Rules will now be supported by an XML-structured system. To create a Level, the game designer need only create a .xml file for each Level to be added to the Game. These XML files may be named anything, as long as the resources.xml file contains the appropriate String mappings. For example, if your first level is defined in an XML file called blueLevel.xml, your resource.xml file needs to contain the following entry within the 

&lt;Strings&gt;

 tag: 

&lt;String name="Level1" value="blueLevel.xml" /&gt;

 Follow the examples outlined in examples.factory for more details and contact us if you have questions. This new format will allow game designers to easily define new levels in a standardized, readable way. TutorialXML.xml is available to consult under examples.factory

  * The updated PlayField was written and now contains ways to add music and background to the PlayFField as variables. This PlayField is returned as a result of using our classes.
  * TileMaps have also been added: The user would simply need to create a text file, as shown in the examples folder: mapLevelExample. “Giant strings” that define a level are added to the XML file in a way that a text file is passed with the “map” to the XML file and then associations are given and from there a playfield is created. MapReader.java
  * Levels are now able to be loaded one at a time or all at once. LevelManager.java
  * Random Sprites can be generated and have a dedicated formatting in XML. 

&lt;SpawnedSprite&gt;


  * Specific sprites can be generated and user defined behavior with variable amount of arguments are permitted in XML. Look at the example for LevelParserExample.java and TutorialXML.xml

Contacts: Cameron McCallie, Bhawana Singh, Derek Zhou



**Runtime Level**
The runtime Level package is a rather new package among those in vooga.engine. Therefore I think it is beneficial for you to know about this package and its potentially useful functionalities.
As discussed in class today, previously the rule class is doing similar things with the event system. So we have abandoned the original rule and switched to using the event system for consensus.
Hence I have thought over the definition of rules. So rules inherently mean that they should not be changed  or violated, that's why they are called rules. They can only be enforced.
Hence the new rule is an interface has only an update method in it. It provides an abstraction for the update method, in case anyone wants to use it.
The package has a LevelField class which is a PlayField. It has three main functionalities: it checks the events that might be triggered, apply the rules, and check for switching level/game Over conditions.
The LevelField also has miscellanous methods that you can think of to help you modify the rules/conditions/events.
Another important point is that LevelField has all the functionality and purposes of VoogaPlayField. So you would either need to switch to using LevelField instead of VoogaPlayField, or I can change the name of LevelField to VoogaPlayField.
Email me if you have any questions/concerns/suggestions/expectations.
Thanks!

Contact: Jiaqi Yan

**Event System**
We changed a lot to the event system. We worked with Runtime Level group and the event system has been delegated to Runtime Level class. Since not many people are using our API, so the new change didn't break the code that much.

A little more details for our event class:
1.EventPool
All classes which implement IEventHandler interface need to be added to EventPool by addEvent() method. The checkEvents() method from EventPool should be called in the update() so that all the events will be checked automatically.
2.IEventHandler
actionPerformed() and isTriggered() are the only two methods in the interface. isTriggered() handles the condition that the event is based upon while actionPerformed() handles what happen after the event is fired.

I have updated a new demo which is in vooga.examples.event.demo2 package. It is very different from the previous event system and it is very easy to use. Please check it out if you needs to deal with game event in your own API. Our goal is to reduce your workload. Feel free to give me any suggestion which help us to improve our event system. Thanks.

Contact: Meng Li


**Overlay**
You'll be happy to hear that we, the Overlay team, made relatively few
changes to our core API this weekend. (If it ain't broke don't fix it,
right?) Here is what we did:
1. Removed OverlayManager- if you were using this, either use a
SpriteGroup instead, or use OverlayPanel to create a top/bottom bar of
Overlays
2. OverlayClock - read below
3. OverlayCreator - edited to make it designed better, look at the
documentation and examples for how to use.
4. OverlayTracker - now uses maps instead of just lists so every thing
can be gotten by a name
5. We have overhauled our documentation so the API should be much
easier to understand. In particular take a look at OverlayExample.java
if you are confused.

Since most teams were using Stats and Overlays already we don't think
we need to really "sell" you on our API, but just in case you're not
clear here is a summary of why the Overlay API is awesome.

The Overlay API allows you to easily display statistics within a game.
In order to use Overlays you need to store information within your
game as a Stat<?>. Simply create a Stat, of whatever object type you
desire, then pass it to an Overlay. The Overlay keeps track of the
Stat and automagically renders it on screen. All you have to do is
update the Stat based on your game logic.

We provide several different types of Overlays for your convenience. They are:
1. OverlayStat- just displays your Stat on the screen with some label.
2. OverlayString- Displays any String on the screen.  OverlayStat
extends this so you can change the font and stuff of the stat
3. OverlayBar- Displays your Stat as a bar that fills and shrinks
(think of a health bar or power-up bar)
4. OverlayIcon- Displays your Stat as a number of images (think of an
ammo clip that displays the number of bullets left)
5. OverlayPanel- Takes several Stats and organizes them into a
horizontal bar that can be displayed across the top or bottom of the
screen
6. OverlayClock- DIsplay's time.  This was not created by us, but the
creator just extended overlay String to make it.

It is really simple to extend any of our Overlays and make cool new
Overlays with them, like the OverlayClock. Any new overlay can be
easily added to the xml file.  All you have to do is add it to the
File overlays.properties, give it a constructor that takes a map of
attributes from the xml, and give it an OverlayTracker to handle its
stats. Look at the code in any of the overlay classes if you ever want
to do this.  They all have this type of constructor.

Let us know if you have any questions.

Contact: Andrew Brown, Justin Goldsmith, Se-Gil Feldsott

**Resources**
I have bolded the most critical changes.

The biggest change in the Resources API was the **update to XML files from CSV files**. For an example XML file, look in the example.resources.resourcesxmlexample package. The resources you want to load on start up should be in a file named "resources.xml" inside a resources package. The resources here will automatically be loaded when the game starts. The capabilities of Resources loaded through the XML document right now currently matches that of the old CSV method, but we are planning on introducing new optional attributes to tags that will allow more customizable resource behavior. Available Resources include:
Images - Single frames which are stored as BufferedImages
Animations - A collection of ordered images stored in a BufferedImage array
Sounds - Stored as the String filepath to the sound file
Strings, Integers, Doubles - Stored as Strings, Integers, and Doubles.
You shouldn't be hard coding any numbers or Strings into your code. Hard coded Strings and numbers you have set as **constants should be pulled out into xml files** and retrieved by Resources. This should make becoming more data driven relatively painless. This isn't a new feature of Resources, but it something people should be doing now that you know you can.

We have made it easier for you to **create your own databases** which can be altered and created at runtime. The DBHandler class allows for basic interactions with an SQLite database. It was designed to be accessible anywhere in the program and abstract away many of the details of dealing with a database. Currently, its functionality includes creating tables, fetching columns, and adding and deleting table rows. Different types of data can be stored in the database columns as dictated by the built-in data type constants in the Column class. As SQLite is loosely typed, it is usually fine to use the "NONE" data type for all columns.  Pulling data out of the database can be performed using generic types or (by default) as strings.

An example of how to use DBHandler is located in vooga.examples.resource.profile.  This example features a ProfileManager class that uses DBHandler to store user information.
Main provides a user interface that lets a user change a window's background color to that assigned to a particular user as well as change any user's built-in color.  These assignments can be seen to be persistent between executions of the example code.

Finally, Randomizer still exists if you care about repeatability in your randomness. If you don't care about repeatability, it works as a statically accessible random number generator. WorldClock is still around if you decide you want access to real world time. OverlayClock exists in widgets and is capable of displaying time in any format you like if you make a class implementing TimeReader, though 3 common ways of displaying time have been taken care of for you.

If you have any questions or comment let us know.

Contacts: Daniel Koverman, David Herzka, John Kline

**GameState**
This weekend we made all of the promised revisions, including better layer handling as well as reusable components for Buttons, MenuGameStates, PauseGameStates, and a class called BasicTextGameState which can function as an easy game over game state.  Below is the plan that we made for the past weekend, which includes all of the changes we made as a group.

Our State system provides great value to the game designer (and reduces code) by providing a
framework in which to develop different states of play, which are extendably mutable and flexible.

In our recent design meeting, we decided to make the following modifications to our game that
will be completed over the weekend:

Leave Game States abstract. This was the subject of some debate, as we are certain people
will complain about this because, at a very superficial level, it will make their lives slightly
harder having to extend Game State. However, we believe that ultimately this is the correct
design decision and will allow for more modular design (and fun, small, classes!)
Initialize GameStateManager in core game. There was some discussion of making the
GameStateManager static, however we feel that there should be a specific instance of this
manager created. Programmers should definitely not have to worry about this for each
individual game, so we will tuck this task away in the core this weekend. We would like
to discuss with Professor Duvall and the class about the ultimate design wisdom of our
GameStateManager class, as we have a general wariness regarding any class with the
word “manager” in it.
Develop a better way of handling layers. Right now layer is simply passed as an int. This
is sloppy and we need to come up with a better solution over the weekend. We discussed
using a List or Stack (or some sort of ordered Collection) to rank GameStates. Obviously
all of these solutions have benefits and drawbacks, and we need to flesh them out over the
weekend (perhaps with input from other groups!). Whatever the ultimate solution, we can
do better than a layer int.
Develop GameOver, Menu, and Pause Game States as reusable components. Someone tried
to do this for us, but we believe we can do it better.
o GameOver should deactivate everything and perhaps give the user the ability to start over
o Menu game state should contain a list of Button objects and perhaps display them. If we
decide to get really fancy, we could allow different options for displaying (i.e. vertical
list vs. horizontal list). However, we certainly want to keep simplicity and potential
extendibility at the core of our efforts.
o Pause game state which pauses all game states and perhaps displays a default pause
message on the screen
Reusable Button extension. This extension will allow VOOGA-users to easily create
Buttons. There was some discussion as to whether this Button should be framed as an
interface or an extension of Sprite.

In addition, we respectfully ask that the Event system develop some way to communicate with the
state system. For example, this would enable a programmer to simply set a pause event, so that when that
event was triggered, the Game State Manager could be told to toggle to a paused game state. We believe
that the framework is already in place on our end for this sort of interaction, which will greatly simplify
transition between game states and allow our system to be much more effective.

Also, as requested by the level group, a GameState can now have more than just 1 playfield.

Let us know if you have any questions or concerns.
Contacts: Brian Simel, Vitor Olivier, Brent Sodman

**Player/Sprite**
Over the last few days, we have streamlined our API to make it easier to use. The new Sprite class, formerly named GameEntitySprite, has been moved to the vooga core package. This is the Sprite class that should be used from now on instead of the Golden-T built-in Sprite. To do this, only import statements must be changed to point to the vooga core Sprite instead of the Golden-T sprite.

The two key features of the enhanced Sprite class are as follows:
The option to switch between renderable images (using any image or animation to represent the Sprite on screen)
Any attributes in the form of a Stat (Sprite holds a map of names to Stats, which can be any object that Sprite needs to contain)


Our examples can be found in the vooga.examples.player package. The example is designed as a sample game that can be run to demonstrate the Sprite features. It contains the XML level file that gives an example of how to construct a Sprite with various attributes through the LevelParser. The PlayerEnemyCollision is a possible implementation of a collision between a player and an enemy. When the example game is run, you can use keys specified to switch the state of the player (from left to right). There is also an example that is not initialized from an XML file called GameExample. All the initialization should normally be done through the Level XML, but the other methods found in this example game are a good model of how to use the Sprite features.

The associated documentation has been updated and clarified to make Sprite easier to use.

At this time, we have decided to eliminate PlayerSprite and ItemSprite. All of the functionality that PlayerSprite provided has been moved up to Sprite. ItemSprite did not provide a great deal of functionality as we have reconsidered what an Item is--non-visible “Items” should be considered Rules, and visible Items can simply be represented with a Sprite. This should be a minor change for those groups that used these two classes; simply replace all references to PlayerSprite with Sprite. Also, if you did utilize the inventory (itemList) feature in PlayerSprite, we recommend you create an inventory class that can be passed into a Stat.

Contacts: Marcus Molchany, Jimmy Mu, Drew Sternesky

**Control**
Hey guys so just some updates on the Control API, we implemented the ability to accept Stats so that the parameters can be changed upon runtime.

This means that every group needs to change the call to the addInput method so that the parameter values are now Stats instead of different objects. That way when the stat is updated, the parameter will also change.

Also, multiple actions can be performed at the same time, hopefully without slowing down the frame rate too much. Lastly a key can now be mapped to multiple methods and have all the methods activate upon pressing the key.

In terms of deletion, we deleted the Controller and Controllable interfaces since they were unused and unnecessary anyway. I don't think any team actually used them so this shouldn't affect anybody.

Let us know if there's any problems encountered, or if there are any other concerns you want the Control API to address.

Contacts: Aaron Choi, Adam Cue, Nick Hawthorne