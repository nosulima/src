package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.gray;

public class finalTest {

	@Test
/*	public void final_test()
	{
		Camera camera = new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0));
		camera.setVpDistance(1000).setVpSize(200, 200);
		
		Scene scene = new Scene("Test scene");
		scene.setBackground(new Color(java.awt.Color.LIGHT_GRAY));
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.geometries.add(
				new Sphere(new Point3D(0, 0, 50),50) //
					.setEmmission(new Color(java.awt.Color.BLUE)) //
					.setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(100).setKT(0.3)), //
				new Sphere(new Point3D(0, 0, 50),25) //
					.setEmmission(new Color(java.awt.Color.RED)) //
					.setMaterial(new Material().setKD(0.4).setKS(0.3).setShininess(100)), //
				new Triangle(new Point3D(1500, 1500, 1500), new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)) //
					.setEmmission(new Color(java.awt.Color.GRAY)) //
					.setMaterial(new Material().setKR(1)) //
		);
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, -50, 0), new Vector(0, 0, 1)) //
					.setkL(4E-5).setkQ(2E-7));
		scene.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));
			
		
		Render render = new Render()//
				.setImageWriter(new ImageWriter("final test", 600, 600)) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}*/
	public void myTeddyBear(){
		Scene scene = new Scene("test");
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
		camera.setVpDistance(1500);
		camera.setVpSize(200,250);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);


		Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));

		double i=15, j=3;
		for(int x=-90, y=140; x<0; x+=j, y-=i, i+=1.5, j+=0.2)
		{
			scene.lights.add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getVTo()).setkC(1).setkL(0.005).setkL(0.0005));
		}
		i=15;
		j=-3;
		for(int x=90, y=140; x>0; x+=j, y-=i, i+=1.5, j-=0.2)
		{
			scene.lights.add(new SpotLight(new Color(400, 300, 300),new Point(x, y, 150),camera.getVTo()).setkC(1).setkL(0.005).setkQ(0.0005));
		}

		//DUBI
		scene.geometries.add(

				new Sphere(new Point(4, 8 ,18),25).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(26, -11, 18),8).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(-14,-12, 18),8).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(-21,16,18),8).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(27,16,18),8).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(3,39,18),18).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(11,43,35.71),2.2).setEmmission(new Color(BLACK)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0).setKR(0)),
				new Sphere(new Point(-4,43,35.94),2.2).setEmmission(new Color(BLACK)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0).setKR(0)),
				//nose		//new Triangle(new Pnt3D(0.2,36.6,37.6),new Point3D(5.7,36.85,37.70),new Point3D(3.33,31.8,36.65)).setEmission(new Color(java.awt.Color.gray)).setMaterial(new Material().setkD(0.3).setkS(0.7).setnShininess(100).setkT(0).setkR(0)),
				new Sphere(new Point(2.699,37.57,37.94),2.5).setEmmission(new Color(gray)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0).setKR(0)),
				new Sphere(new Point(-8,58,18),7).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0)),
				new Sphere(new Point(14,58,18),7).setEmmission(new Color(77,38,0)).setMaterial(new Material().setKD(0.3).setKS(0.7).setShininess(100).setKT(0.5).setKR(0))
		);

		scene.geometries.add(plane.setEmmission(new Color(255,153,153)).setMaterial(new Material() .setKD(0.5).setKS(0.5).setShininess(1200).setKT(0).setKR(0)));
		/////////////mirror
		scene.geometries.add(new Plane(new Point(0, -20, 0), new Vector(0, -40, 0)).setEmmission(new Color(0,40,60)).setMaterial(new Material() .setKR(1)));



		DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
		SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point(4,8,18), new Vector(0,0, 1));//1, 4E-4, 2E-5,;
		PointLight point_light = new PointLight(new Color(400, 300, 300), new Point(0,145,50));
		//on mirror

		ImageWriter imageWriter = new ImageWriter("myTeddyBear1_Before", 600, 500);
		scene.lights.add(point_light.setkC(1).setkL(0.05).setkL(0.00005));
		scene.lights.add(direction_light);
		scene.lights.add(spot_light.setkC(1).setkL(4E-4).setkL(2E-5));


		//Render render = new Render() //
		camera.setImageWriter(imageWriter) //
				//.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));
		camera.setImpovement(9);

		camera.renderImage();
		camera.writeToImage();
	}
	
}