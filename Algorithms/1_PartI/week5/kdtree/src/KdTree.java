import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;


/**
 * Created by mingdzhang on 12/30/16.
 */
public class KdTree {

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("");
        root = put(root, p, XXX, 0, 1, 0, 1);
    }

    private Node put(Node x, Point2D p, boolean flag, double xmin, double xmax, double ymin, double ymax) {
        if (x == null) {
            size++;
            return new Node(p, new RectHV(xmin, ymin, xmax, ymax));
        }
        if (!x.p.equals(p)) {

            if (flag == XXX) {
                if (p.x() < x.p.x()) {
                    x.lb = put(x.lb, p, !flag, xmin, x.p.x(), ymin, ymax);
                } else {
                    x.rt = put(x.rt, p, !flag, x.p.x(), xmax, ymin, ymax);
                }
            } else {
                if (p.y() < x.p.y()) {
                    x.lb = put(x.lb, p, !flag, xmin, xmax, ymin, x.p.y());
                } else {
                    x.rt = put(x.rt, p, !flag, xmin, xmax, x.p.y(), ymax);
                }
            }
        }
        return x;
    }
    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException("");
        return contains(root, p, XXX);

    }
    private boolean contains(Node x, Point2D p, boolean flag) {
        if (x == null) return false;
        if (x.p.equals(p)) return true;
        if (!x.contains(p)) return false;
        if ((flag == XXX && p.x() < x.p.x())
                    || (flag == YYY && p.y() < x.p.y())) {
            return contains(x.lb, p, !flag);
        } else {
            return contains(x.rt, p, !flag);
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, XXX, 0, 1, 0, 1);
    }

    private void draw(Node x, boolean flag, double xmin, double xmax, double ymin, double ymax) {
        if (x == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
        StdDraw.setPenRadius();
        if (flag == XXX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), ymin, x.p.x(), ymax);
            draw(x.lb, !flag, xmin, x.p.x(), ymin, ymax);
            draw(x.rt, !flag, x.p.x(), xmax, ymin, ymax);
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xmin, x.p.y(), xmax, x.p.y());
            draw(x.lb, !flag, xmin, xmax, ymin, x.p.y());
            draw(x.rt, !flag, xmin, xmax, x.p.y(), ymax);
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("");
        Stack<Point2D> S = new Stack<Point2D>();
        range(rect, root, S);
        return S;
    }
    private void range(RectHV rect, Node x, Stack<Point2D> S) {
        if(x == null || !x.intersects(rect)) return;
        if (rect.contains(x.p)) {
            S.push(x.p);
        }
        range(rect, x.lb, S);
        range(rect, x.rt, S);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("");
        dist = Double.MAX_VALUE;
        return nearest(p, root);
    }
    private Point2D nearest(Point2D p, Node x) {
        if (x == null || (x.rect != null && x.rect.distanceSquaredTo(p) > dist))
            return null;
        double temp = x.p.distanceSquaredTo(p);
        Point2D res = null;
        if (temp < dist) {
            dist = temp;
            res = x.p;
        }
        Point2D lb = nearest(p, x.lb);
        if (lb != null) {
            res = lb;
        }
        Point2D rt = nearest(p, x.rt);
        if (rt != null) {
            res = rt;
        }
        return res;
    }

    private SET<Point2D> pointset;

    private int size;
    private Node root;
    private double dist;

    private static final boolean XXX = true;
    private static final boolean YYY = false;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.lb = null;
            this.rt = null;
            this.rect = rect;
        }
        public boolean intersects(RectHV rect) {
            if(this.rect == null) return rect.contains(p);
            else return this.rect.intersects(rect);
        }

        public boolean contains(Point2D p) {
            if(rect == null) return this.p.equals(p);
            else return rect.contains(p);
        }
    }

    public static void main(String[] args) {

    }                  // unit testing of the methods (optional)
}
