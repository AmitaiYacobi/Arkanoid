// 316418300
package geometry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The geometry.Rectangle class.
 * It describes an popular shape of game's sprites.
 */
public class Rectangle {
    private Point upLeft;
    private double width;
    private double height;
    private Line up;
    private Line down;
    private Line left;
    private Line right;

    /**
     * The constructor of a new geometry.Rectangle.
     * It creates a new rectangle with location, width and height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeft = upperLeft;
        this.width = width;
        this.height = height;
        Point upRight = new Point(this.upLeft.getX() + this.width, this.upLeft.getY());
        Point downRight = new Point(this.upLeft.getX() + this.width, this.upLeft.getY() + this.height);
        Point downLeft = new Point(this.upLeft.getX(), this.upLeft.getY() + this.height);
        this.up = new Line(this.getUpperLeft(), upRight);
        this.down = new Line(downLeft, downRight);
        this.right = new Line(upRight, downRight);
        this.left = new Line(this.getUpperLeft(), downLeft);
    }

    /**
     * Returns a (possibly empty) list of intersection points with a given line.
     *
     * @param line the line
     * @return the list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] recLines = new Line[4];
        List<Point> intersectionPoints = new ArrayList<Point>();
        // creates an array of the rectangle's sides.
        recLines[0] = this.up;
        recLines[1] = this.down;
        recLines[2] = this.left;
        recLines[3] = this.right;
        // checks if the given line intersects with the rectangle's sides.
        for (int i = 0; i < recLines.length; i++) {
            if (recLines[i].intersectionWith(line) != null) {
                intersectionPoints.add(recLines[i].intersectionWith(line));
            }
        }
        return intersectionPoints;
    }

    /**
     * @return the rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upLeft;
    }

    /**
     * @return the rectangle's up side.
     */
    public Line getUpperLine() {
        return this.up;
    }

    /**
     * @return the rectangle's down side.
     */
    public Line getLowerLine() {
        return this.down;
    }

    /**
     * @return the rectangle's left side.
     */
    public Line getLeftLine() {
        return this.left;
    }

    /**
     * @return the rectangle's right side.
     */
    public Line getRightLine() {
        return this.right;
    }
}