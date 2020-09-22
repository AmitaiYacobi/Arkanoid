//316418300
package geometry;

import java.util.List;

/**
 * the class defines a line by two points and supports
 * some actions on lines like checking if two lines
 * have an intersecting point and finding it.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * the constructor. gets two points as parameters
     * and initializes a line by those points.
     *
     * @param start the point which the line starts from
     * @param end   the point which the line ends at.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * another constructor which gets four doubles a parameters and
     * initializes a line by them.
     *
     * @param x1 the 'X' component of the start point
     * @param y1 the 'Y' component of the start point
     * @param x2 the 'X' component of the end point
     * @param y2 the 'Y' component of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1),new Point(x2, y2));
    }

    // Return the length of the line

    /**
     * finds the length of a line by using the distance method from
     * point class.
     *
     * @return the length of a line.
     */
    public double length() {
        double length = start.distance(end);
        return length;
    }


    /**
     * returns the start point of the line.
     *
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * returns the end point of the line.
     *
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * returns the slope of this line by using the formula
     * of calculating slopes. ((y1-y2)/(x1-x2))
     *
     * @return the slope of this line.
     */
    public double slope1() {

        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * returns the slope of the other line by using the formula
     * of calculating slopes.
     *
     * @param other the other line
     * @return the slope of the other line.
     */
    public double slope2(Line other) {
        return (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
    }

    /**
     * returns the 'b' component of the direct equation: y = ax + b (of this line),
     * which is the intersection with the 'y' axis.
     *
     * @return the intersection with the 'y' axis.
     */
    public double thisLineIntersection() {
        // the equation: b =  y - ax
        return this.start.getY() - (slope1() * this.start.getX());
    }

    /**
     * returns the 'd' component of the direct equation: y = cx + d (of the other line),
     * which is the intersection with the 'y' axis.
     *
     * @param other the other line
     * @return the intersection with the 'y' axis.
     */
    public double otherLineIntersection(Line other) {
        // the equation: d =  y - cx
        return other.start.getY() - (slope2(other) * other.start.getX());
    }

    /**
     * assistant method. checks if a given point is on a given line.
     *
     * @param p the given point.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isPointInLine(Point p) {
        double epsilon = Math.pow(10, -12);
        if (this.start.distance(p) + this.end.distance(p) - this.length() < epsilon) {
            return true;
        }
        return false;
    }

    /**
     * assistant method. checks if two lines with same equation have an intersection point
     * that can be in the start or in the end of one of the lines
     *
     * @param other another line to check with.
     * @return true if there is intersection point and false otherwise.
     */
    public boolean sameEquationIntersection(Line other) {
        //check if the point in the start or in the end of one of the lines are equals
        return other.start.equals(this.end) || this.start.equals(other.end);
    }


    /**
     * checks if one of the lines is vertical to the 'x' axis.
     *
     * @param other another line to check with.
     * @return true if vertical, false otherwise.
     */
    public boolean isLineVerticalToX(Line other) {
        return other.start.getX() - other.end.getX() == 0;
    }

    /**
     * assistant method.  finds the intersection point in case that two lines
     * have the same equation and returns it.
     * if there isn't an intersection point it returns null.
     *
     * @param other the other line to check with.
     * @return the intersection point of those lines.
     */

    public Point sameEquationPoint(Line other) {
        // if those lines have an intersection point.
        if (sameEquationIntersection(other)) {
            // if start point of the other line equals to the end point of this.
            if (other.start.equals(this.end)) {
                return this.end;
                //if start point of this line equals to the end point of the other
            } else if (this.start.equals(other.end)) {
                return other.end;
            }
            // if both of the lines are vertical to 'x' axis and also have the same 'x' value
        } else if (isLineVerticalToX(this) && isLineVerticalToX(other)
                && this.start.getX() == other.start.getX()) {
            if (other.start.equals(this.end)) {
                return this.end;
                //if start point of this line equals to the end point of the other
            } else if (this.start.equals(other.end)) {
                return other.end;
            }
        }
        // if there is no intersection.
        return null;
    }

    /**
     * assistant method finds an intersection point between two lines
     * in case that one of the lines is vertical to the 'x' axis.
     *
     * @param other another line to check with.
     * @return the intersection point.
     */
    public Point verticalToXPoint(Line other) {
        double x;
        double y;
        // if this line is vertical to the 'x' axis
        if (this.start.getX() - this.end.getX() == 0) {
            x = this.start.getX();
            //positioning the 'x' value in the equation cx + d
            y = (slope2(other) * x) + otherLineIntersection(other);
            //initializes the intersection point.
            Point vertical = new Point(x, y);
            //checks if the point is on the lines
            if (isPointInLine(vertical) && other.isPointInLine(vertical)) {
                return vertical;
            } else {
                //return null if the point isn't on the lines.
                return null;
            }
            //if the other line is vertical to the 'x' axis
        } else if (other.start.getX() - other.end.getX() == 0) {
            x = other.start.getX();
            //positioning the 'x' value in the equation ax + b
            y = (slope1() * x) + thisLineIntersection();
            Point vertical = new Point(x, y);
            if (isPointInLine(vertical) && other.isPointInLine(vertical)) {
                return vertical;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * assistant method. finds the intersection point between two lines with no special case
     *
     * @param other another line to check with
     * @return the intersection point
     */
    public Point regularIntersectionPoint(Line other) {
        double b = thisLineIntersection();
        double d = otherLineIntersection(other);
        // gets from the equalization: ax + b = cx + d
        double x = (d - b) / (slope1() - slope2(other));
        // gets from positioning the 'x' value in one of the equations
        double y = (slope1() * x) + b;
        Point p = new Point(x, y);
        //checks if the point is on the lines.
        if (isPointInLine(p) && other.isPointInLine(p)) {
            return p;
        }
        //if it isn't return null
        return null;
    }


    /**
     * the method checks if two lines have an intersection point by calling
     * the methods above that treat the different cases.
     *
     * @param other another line to check with.
     * @return returns true if there is intersection and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // if both of the lines are vertical to 'x' axis
        if (isLineVerticalToX(this) && isLineVerticalToX(other)) {
            // if one of the lines are a point
            if (this.start.equals(this.end)) {
                return other.isPointInLine(this.start);
            }
            if (other.start.equals(other.end)) {
                return this.isPointInLine(other.start);
            }
            // return true if they have the same equation and  have an intersection point, false otherwise
            return sameEquationIntersection(other);
        }
        // if one of the lines is vertical to 'x' axis
        if (isLineVerticalToX(other) || isLineVerticalToX(this)) {
            // if one of the lines are a point
            if (this.start.equals(this.end)) {
                return other.isPointInLine(this.start);
            }
            if (other.start.equals(other.end)) {
                return this.isPointInLine(other.start);
            }
            return verticalToXPoint(other) != null;
        }
        // if they have the same direct equation
        if (slope1() == slope2(other) && thisLineIntersection() == otherLineIntersection(other)) {
            return sameEquationIntersection(other);
        }
        // if the lines are matching
        if (slope1() == slope2(other) && thisLineIntersection() != otherLineIntersection(other)) {
            return false;
        }

        // the other classic cases
        return regularIntersectionPoint(other) != null;
    }


    /**
     * the method uses the methods above to find the intersection point
     * between two lines and return the point.
     *
     * @param other another line two check with.
     * @return returns the intersection point, and null if there isn't.
     */
    public Point intersectionWith(Line other) {
        // if there is intersection
        if (isIntersecting(other)) {
            // case of same equation of the lines
            if (sameEquationIntersection(other)) {
                return sameEquationPoint(other);
                //case of vertical to 'x' axis
            } else if (isLineVerticalToX(other) || isLineVerticalToX(this)) {
                return verticalToXPoint(other);
            } else {
                return regularIntersectionPoint(other);
            }
        }
        return null;
    }

    /**
     * The method calculates the closest intersection of a given rectangle with a start point of a line.
     * If this line does not intersect with the rectangle, it returns null.
     * Otherwise, it returns the closest intersection point to the start of the line.
     *
     * @param rect the given rectangle.
     * @return the closest intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double min = 0;
        boolean flag = false;
        Point closest = null;
        List<Point> intersection = rect.intersectionPoints(this);
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        for (int i = 0; i < intersection.size(); i++) {
            if (!flag) {
                min = this.start().distance(intersection.get(i));
                closest = rect.intersectionPoints(this).get(i);
                flag = true;
            } else {
                if (this.start().distance(intersection.get(i)) < min) {
                    min = this.start().distance(intersection.get(i));
                    closest = rect.intersectionPoints(this).get(i);
                }
            }
        }
        return closest;
    }
}