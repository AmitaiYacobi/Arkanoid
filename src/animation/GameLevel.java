//316418300
package animation;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collections.GameEnvironment;
import collections.SpriteCollection;
import general.Counter;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.collidables.Block;
import sprites.collidables.Paddle;
import sprites.indicators.ScoreIndicator;

import java.awt.Color;
import java.util.List;


/**
 * The animation.Game class.
 * This class holds the sprites and the sprites.sprites.collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrack;
    private int boardWidth;
    private int boardHeight;
    private int narrowRect;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;


    /**
     * The constructor of a new game.
     *
     * @param level    the level to be display.
     * @param runner   the animation's runner.
     * @param keyboard the keyboard's animation.
     * @param score    the score counter.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        this.scoreTrack = new ScoreTrackingListener(score);
        this.boardWidth = 800;
        this.boardHeight = 600;
        this.narrowRect = 10;
        this.keyboard = keyboard;
        this.runner = runner;
    }

    /**
     * @return the width of the game's screen.
     */
    public int getWidth() {
        return this.boardWidth;
    }

    /**
     * @return the height of the game's screen.
     */
    public int getHeight() {
        return this.boardHeight;
    }

    /**
     * This method adds a given collidable to the game environment.
     *
     * @param c the given collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds a given sprite to the list of sprites.
     *
     * @param s the given sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Create the balls and add them to the game.
     */
    public void addBall() {
        double x = ((getWidth() / 2));
        double y = getHeight() - this.narrowRect - 20;
        int radius = 8;
        int numOfBalls = this.level.numberOfBalls();
        Ball[] balls = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            balls[i] = new Ball(x, y, 8, Color.WHITE, this.environment);
            this.ballsCounter.increase(1);
            balls[i].setVelocity(this.level.initialBallVelocities().get(i));
            balls[i].addToGame(this);
        }
    }

    /**
     * Create the blocks, which constitute the borders to the game, and add them to the game,
     * and also creates a  block that will be used as a 'death region' of the balls.
     */
    public void addBorders() {
        Block[] blocks = new Block[3];
        blocks[0] = new Block(new Point(10, 25), 800, 10, Color.GRAY);
        blocks[1] = new Block(new Point(0, 25), 10, 600, Color.GRAY);
        blocks[2] = new Block(new Point(790, 25), 10, 600, Color.GRAY);
        for (Block block : blocks) {
            block.addToGame(this);
        }
        Block deathRegion = new Block(new Point(0, 599), 800, 1, Color.GRAY);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.ballRemover);
    }

    /**
     * Create the score indicator, and add it to the game.
     */
    public void addScoreIndicator() {
        ScoreIndicator newIndicator = new ScoreIndicator(this.score, this.level);
        newIndicator.addToGame(this);
    }

    /**
     * Create the blocks, which have life points, and add them to the game.
     */
    public void addBlocks() {
        List<Block> blocks = this.level.blocks();
        for (Block block : blocks) {
            this.blocksCounter.increase(1);
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTrack);
        }
    }

    /**
     * Create the paddle and add the paddle to the game.
     */
    public void addPaddle() {
        int paddleWidth = this.level.paddleWidth();
        int paddleHeight = 15;
        Point p = new Point(getWidth() / 2 - paddleWidth / 2, getHeight() - this.narrowRect - paddleHeight);
        Rectangle paddle = new Rectangle(p, paddleWidth, paddleHeight);
        Paddle newPad = new Paddle(paddle, keyboard, Color.YELLOW, this.level);
        newPad.addToGame(this);
    }
    /**
     * Create the correct background and add it to the game.
     */
    public void addBackground() {
        Sprite background = this.level.getBackground();
        this.sprites.addSprite(background);
    }

    /**
     * The method initializes a new game: creates the Blocks, the paddle and the Balls, and adds them to the game.
     */
    public void initialize() {
        addBackground();
        addBall();
        addScoreIndicator();
        addBorders();
        addBlocks();
        addPaddle();

    }


    /**
     * The methods which starts the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * This method removes a given collidable from the game environment.
     *
     * @param c the given collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getList().remove(c);
    }

    /**
     * This method removes a given sprite from the game environment.
     *
     * @param s the given collidable.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getList().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if the player pressed the 'p' key on the keyboard, pause the game.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return the counter o f the balls.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * @return the counter of the blocks.
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }
}