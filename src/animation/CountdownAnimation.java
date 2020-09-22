//316418300
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collections.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * The Countdown animation.
 * An animation screen that comes before the level starts.
 * the screen counts down numbers from a chosen number to zero in order
 * to enable the player to get ready before the game.
 *
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int current;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * The constructor of a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the number that the count starts from.
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.current = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the calculation adds to countFrom + 1 for the 'Go!' string.
        biuoop.Sleeper sleeper = new Sleeper();
        long sleep = (long) ((numOfSeconds / (countFrom + 1)) * 1000);
        // this methods runs before the animation is displayed on the screen, so the sleeping has to occur after one
        // doOneFrame loop.
        if (this.current < this.countFrom) {
            sleeper.sleepFor(sleep);
        }
        this.gameScreen.drawAllOn(d);

        d.setColor(Color.WHITE);

        String text = Integer.toString(this.current);
        if (current > 0) {
            d.drawText((d.getWidth() / 2) - 10, (d.getHeight() / 2) + 8, text, 32);
        }
        // after displaying all the numbers.
        if (current == 0) {
            d.drawText((d.getWidth() / 2) - 25, (d.getHeight() / 2) + 8, "Go!", 32);

        }
        this.current--;
        if (current < -1) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}