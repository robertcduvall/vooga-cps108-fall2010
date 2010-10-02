README file for the Event System
------------------------------------------------------------------------------
Author: Hao He, Meng Li, Cody Kolodziejzyk
Version: October 1st

------------------------------------------------------------------------------
This explains how to use our Event package to develop games. If you have any
problems with the package or how to use it, send an email to us.
<hao.he2@duke.edu>
<meng.li@duke.edu>
<cody.ko@gmail.com>

------------------------------------------------------------------------------

Description

  Our event system is a high level system that can be used as pipes to connect 
  all objects in a game. Applying our system, programmers only need to care
  about behaviors of the current object instead of using scattered if statements
  to take care of other objects who are dependent on it. In another word, it 
  isolates objects, thus makes the system maintainable and expandable.


A Simple Example

  Our package is extremely easy to use. Taking Pacman game for example,
  ghosts, enemies of pacman, are trying to catch pacman and end the game. In
  order to make the game more challenging, ghosts should always try to move 
  closer to pacman instead of wandering around randomly. In this case, we should
  make ghost listen to packman's movement and try to catch up with him. This is
  a simple example showing where and how our event system can be used.
  
  See the example demo @ examples/event


-------------------------------------------------------------------------------
Package Discription

-------------------------------------------------------------------------------
EventManager
 
   This class manages the behavior of game events. Its main purpose is to manage
   events, and inform listeners when the event they are listening to
   happens.This class should be instantiated at the very beginning of the game,
   and be shared by all objects who would like to use our event systems. It can
   register and remove listener. In addition, it can establish relationships
   between isolated objects.

  There are two simple methods in it, 
  - addEventListener()
  - removeEventListener()

  used for registering and removing event listener. There is another method
  - fireEvent()

  This method will notify every objects that are listening to one event if this event 
  occured.



IEvent

  This is the interface for every user defined event to implement. It has two 
  methods,
  - getSource()
  - getName()

  The first method will return object of the source, and the latter method will
  return the name of the event.



IEventListener

  This is the interface for every user defined listener to implement. It has only
  one method,
  - actionPerformed(IEvent e)
  
  When developers make their own listener, they need to change IEvent
  into the name of the specific event this listener listens to.