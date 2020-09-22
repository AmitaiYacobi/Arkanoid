//316418300
package general;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.GameLevel;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

/**
 * The Game flow class.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter balls;

    /**
     * The constructor of a new Game flow, according to several given parameters.
     *
     * @param ar    the animation's runner.
     * @param ks    the animation's keyboard.
     * @param score the game's score counter
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = score;
    }

    /**
     * This method runs the levels that were set by the user.
     *
     * @param levels the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            while (level.getBallsCounter().getValue() > 0 && level.getBlocksCounter().getValue() > 0) {
                level.run();
                if (level.getBlocksCounter().getValue() == 0) {
                    //keep the ball counter to know if there are more balls.
                    this.balls = level.getBallsCounter();
                    //increase the score
                    this.score.increase(100);
                }
            }
            if (level.getBallsCounter().getValue() == 0) {
                //keep the ball counter to know if there are more balls.
                this.balls = level.getBallsCounter();
                break;
            }
        }
        // display the end screen.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.score, this.balls)));
    }
}
