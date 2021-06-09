package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point xy = new Point(x,y);
        Point best = points.get(0);
        for (Point p : points) {
            if (Point.distance(p,xy) < Point.distance(best,xy)) {
                best = p;
            }
        }
        return best;
    }


    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4

        System.out.println(ret);


        Point pa = new Point(2, 3);
        Point pz = new Point(4, 2);
        Point pb = new Point(4, 2);
        Point pc = new Point(4, 5);
        Point pd = new Point(3, 3);
        Point pe = new Point(1, 5);
        Point pf = new Point(4, 4);

        NaivePointSet nn2 = new NaivePointSet(List.of(pa, pz, pb, pc, pd, pe, pf));
        Point ret2 = nn2.nearest(0, 7);
        System.out.println(ret2);
    }
}
