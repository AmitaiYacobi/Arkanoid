//316418300


import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import general.Counter;
import general.GameFlow;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.FinalFour;
import levels.Green3;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;


/**
 * The Ass6Game class.
 * It is implementation of the assignment's classes.
 * This is kind of final product.
 */
public class Ass6Game {
    /**
     * The main method of the class.
     * It initializes and runs the game according the user's choice.
     *
     * @param args the order of levels the user wants to run.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        List<LevelInformation> levels = new ArrayList<>();
        List<LevelInformation> argsLevels = new ArrayList<>();
        Counter score = new Counter();
        LevelInformation level1 = new DirectHit();
        LevelInformation level2 = new WideEasy();
        LevelInformation level3 = new Green3();
        LevelInformation level4 = new FinalFour();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        levels.add(level4);
        GameFlow game = new GameFlow(runner, keyboard, score);
        // run over the input arguments, and enter the levels to the list, which contains the chosen levels to run.
        for (int i = 0; i < args.length; i++) {
            try {
                if (Integer.parseInt(args[i]) == 1) {
                    argsLevels.add(level1);
                } else if (Integer.parseInt(args[i]) == 2) {
                    argsLevels.add(level2);
                } else if (Integer.parseInt(args[i]) == 3) {
                    argsLevels.add(level3);
                } else if (Integer.parseInt(args[i]) == 4) {
                    argsLevels.add(level4);
                }
                // if the argument isn't a number, ignore it.
            } catch (NumberFormatException e) {
                continue;
            }
        }
        // no valid arguments entered.
        if (!argsLevels.isEmpty()) {
            game.runLevels(argsLevels);
        } else {
            game.runLevels(levels);
        }
        gui.close();
    }
}