//316418300
package sprites.collidables;

import animation.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;

import java.awt.Color;

/**
 * The sprites.collidables.Paddle class.
 * The sprites.collidables.Paddle is the player in the game.
 * It's a rectangle that is controlled by the keyboard's arrow keys, and moves according to the user's decision.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int boardWidth;
    private int boardHeight;
    private LevelInformation level;

    /**
     * The constructor of the paddle.
     * It creates a new paddle from rectangle's Properties, the connection of the keyboard to gui, speed,
     * the game which the paddle belongs to and it's color.
     *
     * @param keyboard the keyboard
     * @param paddle   a sprites.sprites.collidables.sprites.collidables.sprites.collidables.Block
     * @param color    a color.
     * @param level    the current level.
     */
    public Paddle(Rectangle paddle, KeyboardSensor keyboard, Color color, LevelInformation level) {
        this.paddle = paddle;
        this.color = color;
        this.keyboard = keyboard;
        this.level = level;
        this.boardWidth = 800;
        this.boardHeight = 600;
    }

    /**
     * The method gets the keyboard sensor.
     *
     * @return keyboard sensor.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * The method checks whether the user pressed the left arrow key.
     *
     * @return true or false.
     */
    public boolean isLeftKey() {
        return getKeyboard().isPressed(KeyboardSensor.LEFT_KEY);
    }

    /**
     * The method checks whether the user pressed the right arrow key.
     *
     * @return true or false.
     */
    public boolean isRightKey() {
        return getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY);
    }

    /**
     * The method defines the paddle move after pressing a key.
     * The move defined by 4 pixels left per key press.
     */
    public void moveLeft() {
        // the most narrow block in the board means to the blocks in the borders.
        double narrowRec = 10;
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        int paddleSpeed = this.level.paddleSpeed();
        x -= paddleSpeed;
        if (x - 6 < narrowRec) {
            // the paddle won't pass the left border block
            x = narrowRec;
        }
        Point newUpLeft = new Point(x, y);
        Rectangle newRec = new Rectangle(newUpLeft, (int) width, (int) height);
        this.paddle = newRec;

    }

    /**
     * The method defines the paddle move after pressing a key.
     * The move defined by 4 pixels right per key press.
     */
    public void moveRight() {
        // the most narrow block in the board means to the blocks in the borders.
        double narrowRec = 10;
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        double width = this.paddle.getWidth();
        double height = this.paddle.getHeight();
        int paddleSpeed = this.level.paddleSpeed();
        x += paddleSpeed;
        if (x + width + 6 > this.boardWidth - narrowRec) {
            // the paddle won't pass the right border block
            x = this.boardWidth - narrowRec - width;
        }
        Point newUpLeft = new Point(x, y);
        Rectangle newRec = new Rectangle(newUpLeft, (int) width, (int) height);
        this.paddle = newRec;
    }

    /**
     * The method notifies the paddle that it should make it's next move.
     */
    public void timePassed() {
        if (isLeftKey()) {
            moveLeft();
        }
        if (isRightKey()) {
            moveRight();
        }
    }

    /**
     * The method prints the paddle on a given surface.
     *
     * @param d is the surface the paddle needs to be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * @return this paddle, which the ball Collided with.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * The method calculates the speed of the ball according the ball's speed in the X and Y coordinates.
     *
     * @param v is the ball's velocity.
     * @return the speed of the ball.
     */
    public double getSpeed(Velocity v) {
        double x = v.getDx();
        double y = v.getDy();
        // calculating the speed by Pythagoras formula.
        double speed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return speed;
    }

    /**
     * The methods calculates the new speed of the ball after the collision with the paddle,
     * according the position of the collision point on the paddle.
     *
     * @param collisionPoint  is the point where the collision occurred.
     * @param currentVelocity is the ball's velocity before the hit.
     * @param hitter          the ball that's doing the hit.
     * @return the new ball's velocity after the collision with the paddle.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double section = this.paddle.getWidth() / 5;
        double speed;
        int angle = 0;
        if (collisionPoint.getX() >= section * 4 + this.paddle.getUpperLeft().getX()) {
            angle = 60;
        } else {
            if (collisionPoint.getX() >= section * 3 + this.paddle.getUpperLeft().getX()) {
                angle = 30;
            } else {
                if (collisionPoint.getX() >= section * 2 + this.paddle.getUpperLeft().getX()) {
                    return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
                } else {
                    if (collisionPoint.getX() >= section + this.paddle.getUpperLeft().getX()) {
                        angle = 330;
                        // collisionPoint's X coordinate is less than section.
                    } else {
                        angle = 300;
                    }
                }
            }
        }
        speed = getSpeed(currentVelocity);
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Add the paddle to a given game.
     *
     * @param g the game the paddle belongs to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}