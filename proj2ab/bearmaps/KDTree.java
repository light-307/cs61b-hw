package bearmaps;

import java.util.List;

public class KDTree implements PointSet {

    private Node root;

    public KDTree(List<Point> points) {
        for (Point pt : points) {
            root = insert(root, pt, 1);
        }
    }
    public Node insert(Node n, Point pt, int com_d) {
        if (n == null) {
            return new Node(pt,com_d);
        }
        if (compare(n,pt) == 1)
            n.left = insert(n.left, pt, -n.compare_d);
        else if (compare(n,pt) == -1)
            n.right = insert(n.right, pt, -n.compare_d);
        return n;
    }
    /** 在比较的维，如果pt<n，返回1；如果pt>=n，返回-1；如果两者各维完全相等，返回0 */
    public int compare(Node n, Point pt) {
        if (n.compare_d == 1) {
            if (pt.getX() < n.p.getX())
                return 1;
            else if (pt.getX() == n.p.getX() && pt.getY() == n.p.getY())
                return 0;
            else
                return -1;
        }
        else {
            if (pt.getY() < n.p.getY())
                return 1;
            else if (pt.getX() == n.p.getX() && pt.getY() == n.p.getY())
                return 0;
            else
                return -1;
        }
    }


    private class Node {
        private Point p;
        private int compare_d; //1：比较X轴坐标     -1：比较Y轴坐标
        private Node left;
        private Node right;

        public Node(Point pt, int com_d) {
            p = pt;
            compare_d = com_d;
        }
    }



    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        return nearest(root, goal, root).p;
    }
    public Node nearest(Node n, Point goal, Node best) {
        if (n == null)
            return best;

        Node goodSide;
        Node badSide;
        Point PrunePt;

        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        if (compare(n, goal) == 1) {
            goodSide = n.left;
            badSide = n.right;
        }
        else {
            goodSide = n.right;
            badSide = n.left;
        }

        best = nearest(goodSide, goal, best);

        if (n.compare_d == 1)
            PrunePt = new Point(n.p.getX(),goal.getY());
        else
            PrunePt = new Point(goal.getX(),n.p.getY());

        if (Point.distance(PrunePt,goal) < Point.distance(best.p, goal))
            best = nearest(badSide, goal, best);

        return best;
    }
}
