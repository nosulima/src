package primitives;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * @author Efrat Kugman
 * /**
 *  * "Half-straight - all the points on the line that are on one side of the point given on the line called the beginning / beginning / beginning of the fund):
 */
public class Ray {

    private final Point startPoint;
    private final Vector directionVector;
    private static final double DELTA = 0.1;

    public Ray(Point startPoint, Vector directionVector) {
        this.startPoint = startPoint;
        this.directionVector = directionVector.normalize();
    }

    public Ray(Point startPoint, Vector directionVector, Vector n) {
        Vector move = n.scale(n.dotProduct(directionVector) > 0 ? DELTA : - DELTA);
        this.startPoint = startPoint.add(move);
        this.directionVector = directionVector.normalize();
    }

    public Point getPoint(double t){
        return startPoint.add(directionVector.scale(t));
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Vector getDirectionVector(){
        return directionVector;
    }

    public String toString(){
        return "Ray: {" +
                startPoint.toString()       +
                directionVector.toString()  +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return startPoint.equals(ray.startPoint) && directionVector.equals(ray.directionVector);
    }

    public Point findClosestPoint(List<Point> listOfPoints){
        return listOfPoints == null || listOfPoints.isEmpty() ? null
                : findClosestGeoPoint(listOfPoints.stream().map(p->new GeoPoint(null, p)).toList()).point;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> listOfPoints){
        if(listOfPoints==null) return null;

        GeoPoint result = null;

        double closestDistane = Double.MAX_VALUE;

        for (GeoPoint gp :listOfPoints){
            double tmp = gp.point.distance(startPoint);
            if(tmp <= closestDistane){
                closestDistane = tmp;
                result = gp;
            }
        }
        return result;

    }

}
