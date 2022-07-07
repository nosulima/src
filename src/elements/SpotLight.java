package elements;
import static org.junit.Assert.*;

//import java.awt.Color;

import renderer.*;
import org.junit.Test;
import org.junit.experimental.max.MaxCore;

import primitives.*;


public class SpotLight extends PointLight{

	private Vector direction;

	//constructor
	public SpotLight(Color l, Point p, Vector v)
	{
		super(l, p);
		direction = v.normalize();
	}
	
	//The function calculate the color
    public Color getIntensity(Point p)
    {
    	Color temp = super.getIntensity(p);
    	return temp.scale(Math.max(0, direction.dotProduct(getL(p))));
    }

    //return vector of Lighting direction value
	public Vector getL(Point p) {
		 return super.getL(p);
	}
	
	//return the distance from the light to a point
	public double getDistance(Point point) {
		double d = super.getDistance(point);
		Vector l = getL(point);
		return l.dotProduct(direction) > 0 ? d : -d;
	}
}
