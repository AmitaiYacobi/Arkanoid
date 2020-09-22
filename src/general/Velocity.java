//316418300
package general;

import geometry.Point;

/**
 * The general Velocity class that specifies the change
 * in position on the `x` and the `y` axes
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * creates a new velocity from given dx and dy.
     *
     * @param dx the change of the X coordinate.
     * @param dy the change of the Y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * creates new velocity object from angle and speed velocity.
     *
     * @param angle the angle of velocity.
     * @param speed the speed of velocity.
     * @return the new velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle);
        double dx = Math.sin(radAngle) * speed;
        double dy = -1 * Math.cos(radAngle) * speed;
        return new Velocity(dx, dy);
    }


    /**
     * @return the change of the X coordinate.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the change of the Y coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point after adding the changes in the X and Y coordinates.
     *
     * @param p the previous position.
     * @return the new point.
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }
}
