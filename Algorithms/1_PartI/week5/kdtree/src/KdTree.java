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
        root = put(root, p, XXX);
    }

    private Node put(Node x, Point2D p, boolean flag) {
        if (x == null) {
            size++;
            return new Node(p, null, null);
        }
        if (!x.p.equals(p)) {
            if ((flag == XXX && p.x() < x.p.x())
                    || (flag == YYY && p.y() < x.p.y())) {
                x.lb = put(x.lb, p, !flag);
            } else {
                x.rt = put(x.rt, p, !flag);
            }
        }
        if (x.lb != null || x.rt != null) {
            double xmin = x.xmin();
            double ymin = x.ymin();
            double xmax = x.xmax();
            double ymax = x.ymax();
            double x0 = xmin;
            double y0 = ymin;
            double x1 = xmax;
            double y1 = ymax;
            if (x.lb != null) {
                xmin = Double.min(xmin, x.lb.xmin());
                ymin = Double.min(ymin, x.lb.ymin());
                xmax = Double.max(xmax, x.lb.xmax());
                ymax = Double.max(ymax, x.lb.ymax());
            }
            if (x.rt != null) {
                xmin = Double.min(xmin, x.rt.xmin());
                ymin = Double.min(ymin, x.rt.ymin());
                xmax = Double.max(xmax, x.rt.xmax());
                ymax = Double.max(ymax, x.rt.ymax());
            }
            if (x0 != xmin || x1 != xmax || y0 != ymin || y1 != ymax) {
                x.rect = new RectHV(xmin, ymin, xmax, ymax);
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
        Double dist = Double.MAX_VALUE;
        return nearest(p, dist, root);
    }
    private Point2D nearest(Point2D p, Double dist, Node x) {
        if (x == null || x.distanceSquaredTo(p) > dist) return null;
        double temp = x.p.distanceSquaredTo(p);
        Point2D res = null;
        if (temp < dist) {
            dist = temp;
            res = x.p;
        }
        Point2D lb = nearest(p, dist, x.lb);
        if (lb != null) {
            dist = lb.distanceSquaredTo(p);
            res = lb;
        }
        Point2D rt = nearest(p, dist, x.rt);
        if (rt != null) {
            res = rt;
        }
        return res;
    }

    private SET<Point2D> pointset;

    private int size;
    private Node root;

    private static final boolean XXX = true;
    private static final boolean YYY = false;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        public Node(Point2D p, Node l, Node r) {
            this.p = p;
            this.lb = l;
            this.rt = r;
            this.rect = null;
        }
        public double xmin() {
            if (rect == null) return p.x();
            else return rect.xmin();
        }
        public double ymin() {
            if (rect == null) return p.y();
            else return rect.ymin();
        }
        public double xmax() {
            if (rect == null) return p.x();
            else return rect.xmax();
        }
        public double ymax() {
            if (rect == null) return p.y();
            else return rect.ymax();
        }
        public boolean intersects(RectHV rect) {
            if(this.rect == null) return rect.contains(p);
            else return this.rect.intersects(rect);
        }
        public double distanceSquaredTo(Point2D p) {
            if(rect == null) return this.p.distanceSquaredTo(p);
            else return rect.distanceSquaredTo(p);
        }
        public boolean contains(Point2D p){
            if(rect == null) return this.p.equals(p);
            else return rect.contains(p);
        }
    }

    public static void main(String[] args) {

    }                  // unit testing of the methods (optional)
}
