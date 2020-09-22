//316418300
package collections;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import sprites.collidables.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The animation.Game environment class.
 * This class holds a collection of objects a sprites.Ball can collides with.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * The constructor of the game environment.
     * It initializes game environment's array list, which is the list of objects a sprites.Ball can collides with.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * @return the collidabble's list.
     */
    public List<Collidable> getList() {
        return this.collidables;
    }

    /**
     * Add a given objects a sprites.Ball can collides with to the game environment's collection.
     *
     * @param c the given object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The method checks all the ball's collisions with the collidable objects,
     * and return the info about the closest one.
     * The returned info is the collision point and the object that the ball Collided with.
     * If the ball didn't collide with any of the sprite in this collection, return null.
     *
     * @param trajectory the movement of the ball.
     * @return the information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collision = null;
        CollisionInfo colInfo = null;
        double minLength = 0;
        double currentLength = 0;
        boolean flag = false;
        // In the loop, we search the ball's collisions with the colliadable objects.
        for (Collidable c : this.collidables) {
            collision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            // if there is a collision
            if (collision != null) {
                // calculates the distance the ball made from his start position to the collision point.
                currentLength = collision.distance(trajectory.start());
                if (!flag) {
                    minLength = currentLength;
                    colInfo = new CollisionInfo(collision, c);
                    flag = true;
                } else {
                    // the current collision is closer than the previous closest collision found until now.
                    if (currentLength < minLength) {
                        minLength = collision.distance(trajectory.start());
                        colInfo = new CollisionInfo(collision, c);
                    }
                }
            }
        }
        return colInfo;
    }


}