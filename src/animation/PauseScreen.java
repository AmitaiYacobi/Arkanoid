//316418300
package animation;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * The Pause screen class.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * The constructor of a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String str = "Paused -- press space to continue";
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        d.drawText(180, d.getHeight() / 2, str, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
