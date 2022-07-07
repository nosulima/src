package elements;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import renderer.ImageWriter;
import renderer.RayTracerBasic;

public class Camera {

	Point p0;
	Vector vUp;
	Vector vTo;
	Vector vRight;
	double width, height, distance;
	ImageWriter imageWriter;
	RayTracerBasic rayTracer;

	//constructor
	public Camera(Point p, Vector vt, Vector vp) {
		p0 = p;
		double temp = vp.dotProduct(vt);
		if(!Util.isZero(temp))//if they are not orthogonal
			throw new IllegalArgumentException("The vectors are not orthogonal");
		vUp = vp.normalize();
		vTo = vt.normalize();
		vRight = vp.crossProduct(vt).normalize().scale(-1);
	}
	
	//geters
	public Point getP0() { return p0; }
	public Vector getVUp() { return vUp; }
	public Vector getVTo() { return vTo; }
	public Vector getVRight() { return vRight; }
	public double getWidth() { return width; }
	public double getHeight() { return height; }
	public double getDistance() { return distance; }


	//seters and check if the values are valid
	public Camera setVpSize(double w, double h)
	{
		if (Util.isZero(w) || Util.isZero(h)) throw new IllegalArgumentException("width or height cannot be 0");
		width = w;
		height = h;
		return this;
	}
	public Camera setVpDistance(double d)
	{
		if (Util.isZero(d)) throw new IllegalArgumentException("distance cannot be 0");
		distance = d;
		return this;
	}
	public Camera setImageWriter(ImageWriter i)
	{
		imageWriter = i;
		return this;
	}
	public Camera setRayTracer(RayTracerBasic r)
	{
		rayTracer = r;
		return this;
	}
	
	//make a ray to the center of the pixel we get 
	public Ray ConstructRayThroughPixel(int nX, int nY, int j, int i)
	{
		//calculate the center view
		Point p = getCenterOfPixel(nX, nY, j, i);
		
		//make ray from p0 to the center
		return new Ray(p0,p.subtract(p0));
	}
	
	
	/*
	 * help function for find center of pixel
	 */
	public Point getCenterOfPixel(Point p,double Rx,double Ry, int i,int j,int nX,int nY) {
		
		double tempY = ((i - nY / 2.0) * Ry + Ry/2.0);
		double tempX = ((j - nX / 2.0) * Rx + Rx/2.0);
				
		if(!Util.isZero(tempX))
			   p = p.add(vRight.scale(tempX));
		
		if(!Util.isZero(tempY))
			   p = p.add(vUp.scale(-tempY));
		
		return p;
	}
	
	
	/*
	 * help for return the center:
	 * the func calculate the center of pixel and return it as Point3D
	 */
	public Point getCenterOfPixel(int nX, int nY, int j, int i)
	{
		//the center point of the center pixel
		Point Pc = p0.add(vTo.scale(distance));
		//width of pixel
		double Rx = width / nX;
		//height of pixel
		double Ry = height / nY;
		
		return getCenterOfPixel(Pc,Rx,Ry,i,j,nX,nY);
	}
	
	
	/*
	 * for mini project 1 - create ray throw the pixel in place ij
	 */
	public Ray ConstructRayThroughPixelGrid(int nX, int nY, int x, int y, int j, int i, int numbergrid) {
		
		//next lines for calculate point to ray :
		
		Point Pc = getCenterOfPixel(nX, nY, x, y); //center of pixel = new pc
	
//		//for debug:
//		return new Ray(p0,Pc.subtract(p0));

		//width of pixel
		double Rx = width / nX / numbergrid;
		//height of pixel
		double Ry = height / nY / numbergrid;

		//p is a point on the grid of the pixel 
		Point p = getCenterOfPixel(Pc,Rx,Ry,i,j,numbergrid,numbergrid);
		
		//make ray from p0 to the center			    
		return new Ray(p0,p.subtract(p0));

//		//for debug:
//		return ConstructRayThroughPixel(nX,nY,x,y);
	}
	
	
	
	//all next are for mini_project_2:
	
	public double getRx(int nX) { return width / nX; }
	public double getRy(int nY) { return width / nY; }
	
	/**
	 * calculate the colors of 4 corners of pixel
	 */
	public List<Ray> colors4Corners(int nX, int nY, int col, int row) {
		List<Point> points = new LinkedList<>();
		List<Ray> rays = new LinkedList<>();
		
		Point pc = getCenterOfPixel(nX, nY, col, row); //center of pixel = new pc
		//width of pixel
		double Rx = width / nX;
		//height of pixel
		double Ry = height / nY;
		
		points.add(pc.add(vRight.scale(-Rx/2)).add(vUp.scale(Ry/2)));
		points.add(pc.add(vRight.scale(Rx/2)).add(vUp.scale(Ry/2)));
		points.add(pc.add(vRight.scale(-Rx/2)).add(vUp.scale(-Ry/2)));
		points.add(pc.add(vRight.scale(Rx/2)).add(vUp.scale(-Ry/2)));

		for(var p : points) rays.add(new Ray(p0, p.subtract(p0)));
		return rays;
	}
	
	/**
	 * calculate the colors of 5 centers on pixel
	 */
	public List<Ray> colors5Centers(Point pc, double Rx, double Ry) {
		List<Point> points = new LinkedList<>();
		List<Ray> rays = new LinkedList<>();
		
		points.add(pc.add(vUp.scale(Ry/2)));
		points.add(pc.add(vRight.scale(-Rx/2)));
		points.add(pc);
		points.add(pc.add(vRight.scale(Rx/2)));
		points.add(pc.add(vUp.scale(-Ry/2)));

		for(var p : points) rays.add(new Ray(p0, p.subtract(p0)));
		return rays;
	}
	int numbergrid = 0;
	public void setImpovement(int n)
	{
		numbergrid = n;
	}

	/*
	 * check if all the fields is set
	 * and paint the picture (geometries and background)
	 */


	public void renderImage()
	{
		if(this==null || imageWriter==null || rayTracer==null)
			throw new IllegalArgumentException("a field isnt set");

		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		//all lines
		for(int i = 0; i < nY; i++) //all rows
		{
			for(int j= 0;j < nX; j++) //all columns
			{
//				if(i==130 && j==130)
//					System.out.println("jj");

				if(numbergrid <= 1) {
					//find color of pixel
					Ray ray = ConstructRayThroughPixel(nX, nY, j, i);
					//RayTracerBasic rayTracer =  RayTracerBasic(scene);
					imageWriter.writePixel(j, i, rayTracer.traceRay(ray));
				}
				else
					//paint pixel
					imageWriter.writePixel(j, i, gridPixel(nX,nY,j,i));
			}
			System.out.println(i);
		}
	}



	/*
	 * mini project 1 - calculate average of colors from one pixel by numbergrid^2 rays
	 */
	private Color gridPixel(int nX,int nY,int x,int y) {
		Color sumColors = Color.BLACK;
		List<Ray> rays = new LinkedList<Ray>();

		for(int i = 0; i< numbergrid; i++) //all rows
		{
			for(int j = 0;j < numbergrid; j++) //all columns
			{
				Ray ray = ConstructRayThroughPixelGrid(nX, nY, x, y, j, i, numbergrid);

				rays.add(ray);
			}
		}
		//sumColors = sumColors.add(rayTracer.traceRay(ray));
		for(var ray : rays)
			sumColors = sumColors.add(rayTracer.traceRay(ray));

		return sumColors.reduce(rays.size());
	}



	/*
	 * paint the pixels of grid in "color"
	 */
	public void printGrid(int interval, Color color)
	{
		try {
			renderImage();
		}
		catch (Exception e) { return; }

		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		//all lines
		for(int i = 0; i< nY; i++)
		{
			for(int j= 0;j < nX; j++) //all columns
			{
				//interval is num of pixels in one square
				//color is color of grid
				//paint the grid
				if(j % interval==0 || i % interval == 0)
					imageWriter.writePixel(i, j, color);
			}
		}
	}


	public void writeToImage()
	{
		if(imageWriter==null) throw new IllegalArgumentException("there is no picture");
		imageWriter.writeToImage();
	}

}


