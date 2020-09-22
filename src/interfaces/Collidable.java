//316418300
package interfaces;

import geometry.Point;
import geometry.Rectangle;
import general.Velocity;
import sprites.Ball;

/**
 * The interface interfaces.Collidable.
 * The interface will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The method calculates the new velocity of the object, which we collided with it at collisionPoint with.
     * The calculation is based on the force the object inflicted on us.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the previous velocity of the object.
     * @param hitter          the ball that's doing the hit.
     * @return the object's new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}