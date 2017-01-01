import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by mingdzhang on 12/30/16.
 */
public class PointSET {

    // construct an empty set of points
    public PointSET() {
        pointset = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return pointset.isEmpty();
    }
    // number of points in the set

    public int size() {
        return pointset.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("");
        pointset.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException("");
        return pointset.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p:pointset) {
            p.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect)   {
        if (rect == null) throw new NullPointerException("");
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D p:pointset) {
            if (rect.contains(p)) {
                stack.push(p);
            }
        }
        return stack;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("");
        Point2D nearest = null;
        double distance = Double.MAX_VALUE;
        for (Point2D point:pointset) {
            double temp = point.distanceSquaredTo(p);
            if (temp < distance) {
                distance = temp;
                nearest = point;
            }
        }
        return nearest;
    }

    private SET<Point2D> pointset;
    public static void main(String[] args) {

    }                  // unit testing of the methods (optional)
}
