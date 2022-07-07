package elements;

import primitives.*;

/*
 * interface LightSource exercise a light source
 */
public interface LightSource {
	
	public Color getIntensity(Point p);
	public Vector getL(Point p);
	double getDistance(Point point);
}
