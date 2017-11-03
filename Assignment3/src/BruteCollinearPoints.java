/******************************************************************************
 *  Name:    Jiayi 
 *  NetID:   jiayi
 *  Precept: P03
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Pattern Recognition
 ******************************************************************************/
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
    private final LineSegment[] segments;
    private int num;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        segments = new LineSegment[points.length * points.length];
        num = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (points[j] == null) {
                    throw new IllegalArgumentException();
                }
                if (i != j && points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {

                for (int m = j + 1; m < points.length; m++) {

                    for (int n = m + 1; n < points.length; n++) {
                        Point max = points[i];
                        Point min = points[i];

                        double d1 = points[i].slopeTo(points[j]);
                        double d2 = points[j].slopeTo(points[m]);
                        double d3 = points[m].slopeTo(points[n]);

                        if ((d1 == d2) && (d2 == d3)) {
                            if (points[j].compareTo(max) >= 0) {
                                max = points[j];
                            }
                            if (points[j].compareTo(min) < 0) {
                                min = points[j];
                            }
                            if (points[m].compareTo(max) >= 0) {
                                max = points[m];
                            }
                            if (points[m].compareTo(min) < 0) {
                                min = points[m];
                            }
                            if (points[n].compareTo(max) >= 0) {
                                max = points[n];
                            }
                            if (points[n].compareTo(min) < 0) {
                                min = points[n];
                            }
                            segments[num] = new LineSegment(min, max);
                            //                            StdOut.println(min);
                            //                            StdOut.println(max);
                            num++;
                        }
                    }
                }
            }
        }
    }    // finds all line segments containing 4 points
    public int numberOfSegments() {
        return num;
    }        // the number of line segments
    public LineSegment[] segments() {
        LineSegment[] seg = new LineSegment[num];
        for (int i = 0; i < num; i++) {
            seg[i] = segments[i];
        }
        return seg;
    }               // the line segments
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        StdOut.println(n);
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
        StdDraw.setPenRadius(0.01);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.num);
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                StdOut.println(segment);
                segment.draw();
            }
        }
        StdDraw.show();
    }
}