// 316418300
package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import collections.GameEnvironment;
import general.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import sprites.collidables.CollisionInfo;

import java.awt.Color;


/**
 * this class defines a ball by a point and radius and also
 * defines borders of the movement of the ball in the
 * window and supports methods that implement this movement
 * this class implements interfaces.Sprite interface.
 */
public class Ball implements Sprite {
    private Point location;

    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment g;
    private int radius;


    /**
     * Creates a new sprites.Ball from given center, specified as (x,y), the radius and the color of the ball.
     *
     * @param x     the X coordinate.
     * @param y     the Y coordinate.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param game  the game environment.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment game) {
        this.location = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.g = game;
    }

    /**
     * @return the X coordinate.
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * @return the Y coordinate.
     */
    public int getY() {
        return (int) this.location.getY();
    }

    /**
     * @return the radius size of the ball.
     */
    public int getSize() {
        return this.radius;
    }



    /**
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }



    /**
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Sets the ball's velocity from a given velocity.
     *
     * @param vel the new velocity of the ball.
     */
    public void setVelocity(Velocity vel) {
        double dx = vel.getDx();
        double dy = vel.getDy();
        this.v = new Velocity(dx, dy);
    }


    /**
     * The method sets the ball's location by a given point.
     *
     * @param p the new location point.
     */
    private void setLocation(Point p) {
        this.location = new Point(p.getX(), p.getY());
    }

    /**
     * The method adds the ball to the game (by adding interfaces.Sprite interface).
     *
     * @param game a game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Draw the ball on the surface.
     *
     * @param surface the surface, the ball needs to be drown on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
    }

    @Override
    /**
     * The method calls to the move method and makes the ball move.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Move the ball to his next position, according to the ball's velocity.
     * In case the ball is about to hit a collidable like a block,
     * the ball's movement is changed to the other direction.
     */
    public void moveOneStep() {
        Point potential = this.getVelocity().applyToPoint(this.location);
        Point newLocation = null;
        Line trajectory = new Line(this.location, potential);
        // get the collision point between the ball and the block.
        CollisionInfo collision = this.g.getClosestCollision(trajectory);
        double radiusX = 0;
        double radiusY = 0;
        // if the ball is about to collide a block
        if (collision != null) {
            Point colPoint = collision.collisionPoint();
            Rectangle colRect = collision.collisionObject().getCollisionRectangle();
            // if the collision is on the left side of the block
            if (colRect.getLeftLine().isPointInLine(colPoint)) {
                radiusX = -1 * this.getSize();
            }
            // if the collision is on the right side of the block
            if (colRect.getRightLine().isPointInLine(colPoint)) {
                radiusX = this.getSize();
            }
            // if the collision is on the up side of the block
            if (colRect.getUpperLine().isPointInLine(colPoint)) {
                radiusY = -1 * this.getSize();
            }
            // if the collision is on the down side side of the block
            if (colRect.getLowerLine().isPointInLine(colPoint)) {
                radiusY = this.getSize();
            }
            // move the ball to the next location
            newLocation = new Point(colPoint.getX() + radiusX, colPoint.getY() + radiusY);
            setLocation(newLocation);
            this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);

        } else {
            this.location = this.v.applyToPoint(this.location);
        }
    }

    /**
     * Removes the block from a given game.
     *
     * @param game is the given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}


