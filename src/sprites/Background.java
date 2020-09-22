//316418300
package sprites;

import biuoop.DrawSurface;
import interfaces.LevelInformation;
import interfaces.Sprite;

/**
 * The Background class.
 */
public class Background implements Sprite {
    private LevelInformation level;

    /**
     * The constructor for a new background of a given level.
     *
     * @param l the given level.
     */
    public Background(LevelInformation l) {
        this.level = l;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.level.setDraw(d);

    }

    @Override
    public void timePassed() {

    }
}
