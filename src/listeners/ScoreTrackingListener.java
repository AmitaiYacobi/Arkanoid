//316418300
package listeners;

import general.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.collidables.Block;

/**
 * The Score tracking listener class.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * The constructor of a new score tracking.
     * It creates a score tracking from given start number of the score.
     *
     * @param scoreCounter the given start number of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
