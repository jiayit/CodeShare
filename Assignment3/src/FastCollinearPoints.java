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
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    private final LineSegment[] segments;
    private int num;
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        int size = points.length;
        num = 0;
        segments = new LineSegment[size * size];
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

        for (int i = 0; i < size; i++) {
            Point[] clone = new Point[size - 1];
            Point oriPoint = points[i];
            int n = 0;
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    clone[n++] = points[j];
                }
            }
            Arrays.sort(clone, oriPoint.slopeOrder());

            int count = 0;
            int index = 0;
            Point tempPoint = clone[0];
            Point max = clone[0];
            for (int j = 0; j < size - 1; j++) {
                if (oriPoint.slopeTo(clone[j]) == oriPoint.slopeTo(tempPoint)) {
                    count++;
                    if (clone[j].compareTo(max) > 0) {
                        max = clone[j];
                    }
                    if (count >= 3 && j == size -2) {
                        if (oriPoint.compareTo(clone[index]) <= 0) {
                            segments[num] = new LineSegment(oriPoint, 
                                    max);
                            num++;
                        }
                    }
                    continue;
                }
                else {
                    if (count >= 3) {
                        if (oriPoint.compareTo(clone[index]) <= 0) {
                            segments[num] = new LineSegment(oriPoint, 
                                    max);
                            num++;
                        }
                    }
                    count = 1;
                    index = j;
                    tempPoint = clone[j];
                    max = clone[j];
                }
            }

        }
    }    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return num;
    }       // the number of line segments
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
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