// 316418300
package geometry;

/**
 * the class defines a geometry.Point by to doubles x and y
 * the class supports getting the distance between to points
 * and also if two points are equal.
 */
public class Point {
    private double x;
    private double y;

    /**
     * the constructor. gets two parameters which assembles
     * a point - the 'X' component and the 'Y' component and
     * initializes a point.
     *
     * @param x the 'X' coordinate of the point.
     * @param y the 'Y' coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * returns the distance between two points.
     *
     * @param other point that the method calculates the
     *              the distance between it to this point.
     * @return returns the distance.
     */
    public double distance(Point other) {
        // using the equation ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)) for calculating the distance
        double distance = Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
        return distance;
    }


    /**
     * checks if two points are equal.
     *
     * @param other point that the method check equality with this point.
     * @return true if equals false otherwise.
     */
    public boolean equals(Point other) {

        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * returns the 'X' coordinate of the point.
     *
     * @return the 'X' coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the 'Y' coordinate of the point.
     *
     * @return the 'Y' coordinate of the point.
     */
    public double getY() {
        return this.y;
    }
}