/*************************************************************************
 * Name: Fernando Nellmeldin
 * Email: fernando.nellmeldin@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Arrays;
public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        int num = that.y - this.y;
        int den = that.x - this.x;
        double slope = 0.0;
        if ((num == 0) && (den == 0)) slope = Double.NEGATIVE_INFINITY;
        if ((num == 0) && (den != 0)) slope = 0.0;
        if ((num != 0) && (den == 0)) slope = Double.POSITIVE_INFINITY;
        if ((num != 0) && (den != 0)) slope = ((double) num) / ((double) den);

        return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return  1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return  1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    private class BySlope implements Comparator<Point> {
        public int compare (Point a, Point b) {
            double slope_a = slopeTo(a);
            double slope_b = slopeTo(b);
            if (slope_a < slope_b)  return -1;
            if (slope_a > slope_b)  return  1;
            return 0;
        }
    }

    // unit test
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();

        Point [] puntos = new Point[N];
        int i = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            puntos[i] = p;
            i++;
        }
        Point pivote = puntos[0];
        Arrays.sort(puntos, pivote.SLOPE_ORDER);
        for (int j = 0; j < puntos.length; j++) {
            StdOut.println(puntos[j].toString());
        }
    }
}
