//316418300
package sprites.collidables;

import geometry.Point;
import interfaces.Collidable;

/**
 * The Collision info class.
 * It holds the information about the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * The constructor of a new Collision info.
     *
     * @param collisionPoint the collision point.
     * @param collidable     the colliadble object, which the ball hit at.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}