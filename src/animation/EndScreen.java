//316418300
package animation;

import biuoop.DrawSurface;
import general.Counter;
import interfaces.Animation;

import java.awt.Color;

/**
 * The End screen class.
 * Shows after the game is over.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private Counter score;
    private Counter balls;

    /**
     * The constructor a new End screen.
     *
     * @param balls the balls counter.
     * @param score the score counter.
     */
    public EndScreen(Counter score, Counter balls) {
        this.score = score;
        this.balls = balls;
        this.stop = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String str1 = null;
        String str2;
        //if there are no balls left in the game
        if (this.balls.getValue() == 0) {
            str1 = "Game Over. Your score is " + Integer.toString(this.score.getValue());
        } else {
            str1 = "You Win! Your score is " + Integer.toString(this.score.getValue());
        }
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(180, d.getHeight() / 2, str1, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
