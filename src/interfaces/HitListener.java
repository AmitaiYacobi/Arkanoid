//316418300
package interfaces;

import sprites.Ball;
import sprites.collidables.Block;

/**
 * HitListener class.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}