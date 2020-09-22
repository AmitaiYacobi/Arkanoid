//316418300
package listeners;

import animation.GameLevel;
import general.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.collidables.Block;

/**
 * The Block remover class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * The constructor of a new blocks remover.
     * It creates a blocks remover from several given parameters.
     *
     * @param game          the blocks remover's game.
     * @param removedBlocks the remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
