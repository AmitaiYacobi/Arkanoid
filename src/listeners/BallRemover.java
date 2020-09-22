//316418300
package listeners;

import animation.GameLevel;
import general.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.collidables.Block;

/**
 * The Ball remover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * The constructor of a new Ball remover.
     * It creates a ball remover from several given parameters.
     *
     * @param game         the ball remover's game.
     * @param removedBalls the remaining balls in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the Ball that's doing the hitting.
     */

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);

    }
}
