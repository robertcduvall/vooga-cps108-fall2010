CPS 108 - Fall '10
9/26/2010

OVERLAY ENGINE IN GREENFOOT
AUTHORS: Greenfoot #1 - Andrew Brown, Se-Gil Feldsott, Justin Goldsmith

NOTE: Our API does not compile in Eclipse because we have not been able to get the Greenfoot .jar file to work with Eclipse. However, it compiles
and runs fine in Greenfoot. Since our engine deals primarily with drawing pixels on the screen, the details are highly dependent on the specific
game engine being used. However, the overall structure should work universally with any game engine. 

This is a package based on the Greenfoot engine that provides a convenient
way for developers to display game information and statistics in real time.
The package includes several ways for which this information can be
displayed (see below for details).

To use the Overlay classes, the user should make each variable he wishes to display be of type Stat<T> or StatInt. The user should then pass, 
that variable to the constructor of the Overlay, and the Overlay class will use that stat to keep updating the score (etc).

CLASSES:

-OverlayBar-
This overlay displays a value as a gauge that increases and decreases
according to the current value.  It requires a Stat<Integer>, and the size of the bar grows and shrinks in real time.

-OverlayHelp-
This class contains helper methods that gathers information about text size.
This is NOT an Actor class.

-OverlayIcon-
This overlay displays a number of icons according to value being kept track
of.  The image used and the Stat<Integer> or StatInt that determines how the value
increases and decreases are passed in by the user upon instantiation of this
class.  This class extends OverlayString.

-OverlayStat-
This overlay displays a statistic or integer value.  It takes in a
Stat<T> or StatInt and displays the value based on the output
of the getStat() method.  This class extends OverlayString.

-OverlayStatImage-
This overlay statically shows an Greenfoot image.  It is used by the
OverlayIcon class to display each icon that is currently showing on the
screen.

-OverlayString-
This overlay is the base class for displaying a String with the option of
specifying a font and/or color.  This class is extended by OverlayIcon and
OverlayStat.

-OverlayManager-
This convenience class lets the user easily group Overlays and manipulate them all at once.
It can remove all the overlays, move them all a certain distance, or position them all relative to the same point.

-OverlayPanel-
This convenience class lets the user easily create a bar of Overlays across the top or bottom of the screen.

-Stat<T>-
This class is the variable type for all statistics.

-StatInt-
This is a convenience class that uses an int instead of the equivalent Stat<Integer>.

-StatKeeper-
This interface uses its getStat() method to return a generic value.


