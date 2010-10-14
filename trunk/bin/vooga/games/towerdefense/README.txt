Tower Defense
by Justin Goldsmith, Derek Zhou, Daniel Koverman

Game Description:
The object of Tower Defense is to maintain your self esteem for as long as 
possible. Self esteem is reduced when an enemy makes it all the way to the 
end of the path. The only way to stop enemies is to place towers to attack 
them as they travel along the path.

Tower are bought with money. The player starts out with a sum of money in 
order to place the first tower or towers. To accrue more money, the player 
must hit and kill enemies with 1 dollar distributed for each hit and another 
for a killing shot. To place a tower, the player must simply click where the 
tower should be placed. Towers are distinguished by both their range, which 
is displayed as you move your cursor, and their rate of fire. To cycle 
between the three currently available towers either click their icons in the 
overlay or hit the number 1,2, or 3 keys.

At any time the game can be paused by pressing 'p'. Once the player is 
overwhelmed, they are given the option to quit or go back to main menu 
and play again.

API Use:
Tower Defense makes use of the Resource, Game State, Player Contol, and
Overlay APIs. 

The Resource API simplifies the loading of images. Formerly, only 
the game class could load images which required passing the Game class around 
wherever an Image was required. Using the Resources class, images can be loaded 
and retrieved from anywhere in the Project. We elected to load all necessary images 
at the start of the game.

The Game State API simplifies the use of menus and pause screens, effectively working 
as an enhanced version of Playfield as well as a tracker for the current game state. 
The Game State API required some modification to work properly, thus the creation of 
the NonSetStateManager. Its simple extensibility allows it to also update Controlers 
from the Player Control API, making it a much more versitile tool.

The Player Control group simplified and automated input listening for the player. Though 
it had a high learning curve for proper use, once it is learned it allows for the 
automation of input response in a custom manner. The provided example controller classes 
only required slight modification to fit perfectly into this game. Ideally, the two 
controller classes which control the player in our game would be merged into one class. 
However due to time contraints we simply used two predefined controllers which is 
fucntional.

The Overlay API is used for automating the display of critical information to 
the user. It capabilities were extended to also allow for static overlays which are 
themselves interactable, in this case clickable overlays for each available tower.

Neither the Golden T not the class API collision detection was used. Both are non trivial 
to implement because they seem to be designed with the notion of constantly checking 
for collisions. However, our game could have used a simple, possibly static, collision 
manager which would determine if two sprites or bounding boxes overlapped. In lieu of 
this, we used distance calculations to determine which sprites were in range and to 
prevent towers from being placed on the path. The reason towers can overlap in placement 
is because use of either built in collision ability would have required the process wasting 
task of checking collision betweens the tower being placed and the other towers every 
update instead of just on clicks.

The Event API was not used because a need for an event system never arose which would have 
practical benefits outweighing the con of having to learn how to use the event system. 

The Level API was not used because it appears to have two functions which were not needed 
for this game. The first is the ability to load the initial sprites for a level which is 
unimportant for a game with a fixed view and most sprites generated over the course of the 
game. The second use seems to be to manage most of the game functionality, but the Game 
class was already doing that for us.

Code Commentary:
The use of reflection in the abstract Plaer class allows for a strong adherence to the 
Open/Closed principle when it comes to adding new Towers. If a new Tower class is created, 
one must simply assign the string of that class name to a new key on the number pad. Everything 
else will take of itself as long as the new class adheres to the template provided by the 
three existing kinds of Towers. Futhermore, the Tower class was designed to allow for extension 
in the case that a tower with nonstandard fucntionality was required, though we did not have 
time to make a non standard tower that might, for instance, slow down enemies. If more time 
were available, the Tower class would also be better designed to ensure that extensions of 
the Tower class adhered to the templates of the existing Tower subclasses. The overlay menu 
would also be altered to allow for adding and subtracting of tower buttons and information 
without having to copy/paste and edit similair segments of code with fixed String values.





