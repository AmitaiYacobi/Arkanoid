//316418300
package sprites.collidables;


import animation.GameLevel;
import biuoop.DrawSurface;
import general.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import interfaces.HitListener;
import interfaces.HitNotifier;
import sprites.Ball;

import java.util.ArrayList;
import java.util.List;


import java.awt.Color;

/**
 * The sprites.collidables.Block class.
 * It an object in the game, which has rectangle shape, and implements the colliadble interface also,
 * so block is going to be something we collide into.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rec;
    private Color color;


    /**
     * The constructor of a new ball.
     * it gets a Rectangle and a color.
     *
     * @param rec   a Rectangle.
     * @param color color.
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;

        this.hitListeners = new ArrayList<>();
    }

    /**
     * Another constructor.
     * It gets the rectangle's properties and the block's color.
     *
     * @param upLeft the up left
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(Point upLeft, int width, int height, Color color) {
        this(new Rectangle(upLeft, width, height), color);
    }

    @Override
    public void timePassed() {

    }

    /**
     * The method prints the block on a given surface.
     *
     * @param surface is the given surface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }


    /**
     * @return this block, which the ball collided with.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * get the color of  a block.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * The methods updates the block's hit points, and calculates the new velocity of the ball,
     * according the location of the collision point.
     *
     * @param collisionPoint  is the point where the collision occurred.
     * @param currentVelocity is the ball's velocity before the hit.
     * @param hitter          the ball that's doing the hit.
     * @return the new velocity of the ball.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        if (this.rec.getUpperLine().isPointInLine(collisionPoint)) {
            if (newDy > 0) {
                newDy *= -1;
            }
        }
        if (this.rec.getLowerLine().isPointInLine(collisionPoint)) {
            if (newDy < 0) {
                newDy *= -1;
            }
        }
        if (this.rec.getLeftLine().isPointInLine(collisionPoint)) {
            if (newDx > 0) {
                newDx *= -1;
            }
        }
        if (this.rec.getRightLine().isPointInLine(collisionPoint)) {
            if (newDx < 0) {
                newDx *= -1;
            }
        }
        this.notifyHit(hitter);
        return new Velocity(newDx, newDy);
    }

    /**
     * Removes the block from a given game.
     *
     * @param game is the given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add the block to a given game.
     *
     * @param g is the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Notifies all the listeners that the block is hit by a given ball.
     *
     * @param hitter is the given ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}