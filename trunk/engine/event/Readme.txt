README file for the Event System
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
  closer to pacman instead of wandering around randomly. We can make it
  happen by firstly building a simple PacmanMoveEvent that implements our 
  Event interface, which takes Pacman's (x, y) positions, and creating a 
  PacmanMoveListener interface to listen to this specific event. We then make 
  both pacman and ghosts extends our super class AbstractEventManager, 
  which is used for registering, removing and firing events. The final step is to 
  make ghost implement PacmanMoveListener, and write a short piece of code 
  in the action method such that ghost will do something interesting when pacman 
  moves. Programmers can also choose either register the listener in ghost's 
  construction method if he wanted ghosts always move according to pacman, 
  or they can also register the listener anywhere in the main game if they wanted 
  ghosts to follow pacman at a specific time.


-------------------------------------------------------------------------------
Package Discription

-------------------------------------------------------------------------------
AbstractEventManager
 
  This is the super class for all objects to extend. It is abstract so that it cannot
  be instantiated. It has one static field mapRepository that keeps all listeners.
  We think it's fine to make a static field because our event system is really high
  level, and other objects need to register their listeners at one place in order to 
  make it easy to manage. 

  There are two simple methods in it, 
  - addMyEventListener()
  - removeMyEventListener()

  used for registering and removing event listener. There is another method
  - fireEvent()

  This method will notify every objects that are listening to one event if this event 
  occured.



MyEvent

  This is the interface for every user defined event to implement. It has two 
  methods,
  - getSource()
  - getName()

  The first method will return object of the source, and the latter method will
  return the name of the event.



MyEventListener

  This is the interface for every user defined listener to implement. It has only
  one method,
  - actionPerformed(MyEvent e)
  
  When developers make their own listener, they need to change MyEvent
  into the name of the specific event this listener listens to.