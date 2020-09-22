//316418300
package sprites.indicators;

import animation.GameLevel;
import biuoop.DrawSurface;
import general.Counter;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The Score indicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter scores;
    private Rectangle rec;
    private LevelInformation level;

    /**
     * The constructor of a new score indicator.
     * It creates a new indicator for given parameters.
     *
     * @param scores the start number of score.
     * @param level  the current level of the game.
     */
    public ScoreIndicator(Counter scores, LevelInformation level) {
        this.scores = scores;
        this.level = level;
        this.rec = new Rectangle(new Point(0, 0), 800, 25);
    }

    /**
     * Prints the score's value on a given surface.
     *
     * @param surface the given surface.
     */
    private void displayScore(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        String space = "                            ";
        String text = "Score:  " + Integer.toString(this.scores.getValue()) + space + this.level.levelName();
        surface.drawText((int) (this.rec.getUpperLeft().getX() + this.rec.getWidth() / 2),
                (int) (this.rec.getUpperLeft().getY() + this.rec.getHeight() / 2 + 4), text, 15);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        displayScore(d);
    }


    @Override
    public void timePassed() {

    }

    /**
     * add this sprite to the game.
     *
     * @param g the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
