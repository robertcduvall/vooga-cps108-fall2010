package engine.overlay;

import greenfoot.*;
import java.util.*;

/**
 * The OverlayManager class lets you create a group of Overlays, which can then be manipulated. You translate all the overlays, absolutely
 * position them, or remove them all at once.
 * @author Andrew Brown
 */
public class OverlayManager {

        private Set<Overlay> myOverlays;
        private World myWorld;
        private int myX;
        private int myY;
        
        /**
         * Constructs an OverlayManager that uses the given x and y coordinate as the offset for all its Overlays.
         * @param world The world the OverlayManager will draw the Overlays in.
         * @param x The x offset of all the Overlays (you can think of it as the x coordinate of the top-left corner of the OverlayManager).
         * @param y The y offset of all the Overlays (you can think of it as the y coordinate of the top-left corner of the OverlayManager).
         */
        public OverlayManager(World world, int x, int y){
                myWorld = world;
                myX = x;
                myY = y;
                myOverlays = new HashSet<Overlay>();
        }
        
        /**
         * Add the given Overlay to OverlayManager's group. The x and y coordinates are the offset from the x and y coordinates of the OverlayManager.
         * @param overlay The overlay to add to the OverlayManager's group.
         * @param x The x coordinate of the overlay relative to the x coordinate of the OverlayManager.
         * @param y The y coordinate of the overlay relative to the y coordinate of the OverlayManager.
         */
        public void addOverlay(Overlay overlay, int x, int y){
                myOverlays.add(overlay);
                myWorld.addObject(overlay, myX+x, myY+y);
        }
        
        /**
         * Translates all the Overlays in the OverlayManager by the x and y amounts given.
         * @param x The amount to translate the Overlays on the x axis.
         * @param y The amount to translate the Overlays on the y axis.
         */
        public void translateOverlays(int x, int y){
                for(Overlay o : myOverlays){
                        o.setLocation(o.getX()+x,o.getY()+y);
                }
        }
        
        /**
         * Absolutely positions all the Overlays in the OverlayManager relative to the given x,y point.
         * @param x The x coordinate at which to anchor the OverlayManager.
         * @param y The y coordinate at which to anchor the OverlayManager.
         */
        public void setOverlayPosition(int x, int y){
                myX = x;
                myY = y;
        }
        
        /**
         * Removes all the Overlays in the OverlayManager from the World.
         */
        public void removeOverlays(){
                for(Overlay o : myOverlays){
                        myWorld.removeObject(o);
                }
        }
        
        /**
         * Returns all the Overlays in the OverlayManager.
         * @return Returns the Set of Overlays in the OverlayManager.
         */
        public Set<Overlay> getOverlays() {
                return myOverlays;
        }

        /**
         * Returns the World the OverlayManager draws the Overlays in.
         * @return The World that the OverlayManager draws the Overlays in.
         */
        public World getWorld() {
                return myWorld;
        }

}
