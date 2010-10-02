======================
Player/Item System API
======================

By: Aaron Choi, Adam Cue, Nick Hawthorne
Version 1.0
September 27th, 2010

To use the package, classes need to extend the Player class to create the
objects that will be used in the game. These objects are meant to be
controlled by the players of the game (or could be the players themselves
if they are not meant to have control of any objects on the screen). The Control
class needs to be made into 'Control' objects that are used by all the Players
and Items. These control schemes can be used to easily set what actions the
programmer wants the Player to perform. As of this version, the project still 
requires the Actor and Keyboard and Mouse packages from Greenfoot, and therefore
may not compile correctly in Eclipse. To change this, the Greenfoot methods will
need to be added and/or modified, which we will change if Greenfoot is not
chosen as the game engine for the class.

The most important methods that the programmer needs from the Control class
are setParams and addInput. The setParams method needs to be called every 
time before addInput if the new method has parameters that need to be filled
in. Both of these methods also allow for an unlimited amount of arguments for
the parameters of the method using Java's 'varag' ability. However, when used,
this may cause a few warnings upon compilation when a non-variable parameter
is set.

To create the Control method, the Java reflection package needed to be imported.
