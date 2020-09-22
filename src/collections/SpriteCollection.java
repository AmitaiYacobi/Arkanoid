//316418300
package collections;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The interfaces.Sprite collection class is the collection of all the game objects that can be drawn to the screen.
 */
public class SpriteCollection {
    private List<Sprite> collection;

    /**
     * The constructor of collections.SpriteCollection.
     * It initializes the array list, which will contain the game's sprites.
     */
    public SpriteCollection() {
        this.collection = new ArrayList<Sprite>();
    }

    /**
     * Add sprite methods.
     * It adds the given sprite to the array list.
     *
     * @param s the give sprite.
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * @return this sprites list.
     */
    public List<Sprite> getList() {
        return this.collection;
    }


    /**
     * The method charges to notify all the sprites in the array list to make their next move.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.collection.size(); i++) {
            this.collection.get(i).timePassed();
        }
    }

    /**
     * The method charges to notify all the sprites in the array list to be dorwn on the given surface.
     *
     * @param d the given surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprites : this.collection) {
            sprites.drawOn(d);
        }
    }
}