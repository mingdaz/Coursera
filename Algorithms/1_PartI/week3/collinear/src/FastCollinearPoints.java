import java.util.Arrays;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by mingdzhang on 12/19/16.
 */
public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points){     // finds all line segments containing 4 or more points
        if (points == null) throw new java.lang.NullPointerException("");
        int i,j,start_ind;
        double slope,newslope;

        _numberOfSegments = 0;
        _segments = new LinkedList<LineSegment>();
        int length = points.length;
        Point[] A = points.clone();
        Arrays.sort(A);
        for(i=0;i<length-1;i++){
            if(A[i].compareTo(A[i+1])==0) throw new java.lang.IllegalArgumentException("");
        }
        if(length>=4) {
            for (i = 0; i < length; i++) {

                Arrays.sort(A);
                Arrays.sort(A, points[i].slopeOrder());

                for (j = 2, slope = points[i].slopeTo(A[1]), start_ind = 1; j < length; j++) {
                    newslope = points[i].slopeTo(A[j]);
                    if (Double.compare(newslope, slope) != 0) {
                        if (j - start_ind >= 3) {
                            if (points[i].compareTo(A[start_ind]) < 0) {
                                _numberOfSegments++;
                                _segments.add(new LineSegment(points[i], A[j - 1]));
                            }
                        }
                        start_ind = j;
                        slope = newslope;
                    }
                }

                if (length - start_ind >= 3) {
                    if (points[i].compareTo(A[start_ind]) < 0) {
                        _numberOfSegments++;
                        _segments.add(new LineSegment(points[i], A[length - 1]));
                    }
                }
            }
        }

    }
    public int numberOfSegments()   { // the number of line segments
        return _numberOfSegments;
    }
    public LineSegment[] segments(){     // the line segments
        return _segments.toArray(new LineSegment[0]);
    }
    private int _numberOfSegments;
    private LinkedList<LineSegment> _segments;

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In("/Users/mingdzhang/Documents/coursera/Algorithms/1_PartI/week3/collinear/resource/input1.txt");
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}