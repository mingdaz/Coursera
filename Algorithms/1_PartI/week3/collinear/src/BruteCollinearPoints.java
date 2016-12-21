/**
 * Created by mingdzhang on 12/19/16.
 */
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points){    // finds all line segments containing 4 points
        if(points==null) throw new java.lang.NullPointerException("");
        int length = points.length;

        int i,j,k,l;
        for(i = 0; i < length; i++) {
            if (points[i] == null) throw new java.lang.NullPointerException("");
            for (j = i + 1; j < length; j++)
                if(points[i].compareTo(points[j])==0) throw new java.lang.IllegalArgumentException("");
        }

        _numberOfSegments = 0;
        _segments = new LinkedList<>();

        for(i = 0; i < length; i++){
            for(j = i + 1; j < length; j++){
                for(k = j + 1; k < length; k++){
                    for(l = k + 1; l < length; l++ ){
                        if(collinear(points[i],points[j],points[k],points[l]))
                            _numberOfSegments++;
                    }
                }
            }
        }

    }

    public int numberOfSegments() {        // the number of line segments
        return _numberOfSegments;
    }
    public LineSegment[] segments(){                // the line segments
        return _segments.toArray(new LineSegment[0]);
    }

    private boolean collinear(Point p, Point q, Point r, Point s){
        double k1 = p.slopeTo(q);
        double k2 = p.slopeTo(r);
        double k3 = p.slopeTo(s);
        if(k1 == k2 && k2 == k3){
            Point a[] = {p,q,r,s};
            java.util.Arrays.sort(a);
            _segments.add(new LineSegment(a[0],a[3]));
            return true;
        }
        else
            return false;
    }

    private int _numberOfSegments;
    private LinkedList<LineSegment> _segments;

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In("/Users/mingdzhang/Documents/coursera/Algorithms/1_PartI/week3/collinear/resource/input8.txt");
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}