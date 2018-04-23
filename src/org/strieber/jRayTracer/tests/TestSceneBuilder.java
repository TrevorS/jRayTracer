package org.strieber.jRayTracer.tests;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import org.strieber.jRayTracer.engine.Camera;
import org.strieber.jRayTracer.engine.RayTracer;
import org.strieber.jRayTracer.engine.Scene;
import org.strieber.jRayTracer.engine.SceneBuilder;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.primitives.Surface;

public class TestSceneBuilder {

	public static void main(String[] args) {
		
		int sceneSizeX = 640;
		int sceneSizeY = 480;
		
		
		SceneBuilder builder = SceneBuilder.getSceneBuilder();
		RayTracer rayTracer = RayTracer.getRayTracer();
		
		BufferedImage image = new BufferedImage(sceneSizeX, sceneSizeY, BufferedImage.TYPE_INT_RGB);
		BufferedImage results;
		Canvas canvas = new Canvas();
		
		// setup builder
		builder.cleanBuilder();
		builder.setSize(sceneSizeX, sceneSizeY);
		Color sceneAmbient = new Color(1, 1, 1);
		builder.setAmbientLight(sceneAmbient);
		builder.setBackgroundColor(new Color(0.5, 0.5, 1));
		builder.setSuperSampleWidth(1);
		
		// setup camera
		Camera camera = new Camera();
		camera.setEye(new Point3d(0, 0, 2));
		camera.setLookAt(new Point3d(0, 0, 0));
		camera.setUpDirection(new Vector3d(0, 1, 0));
		camera.setDirection(new Vector3d(0, 0, -1));
		camera.setScreenDist(1);
		camera.setScreenWidth(1.3);
		builder.setCamera(camera);
		
		Color diffuse = new Color(0.8, 0.8, 0.8);
		Color specular = new Color(0.6, 0.6, 0.8);
		Color ambient = new Color(0.1, 0.1, 0.1);
		Color emission = new Color(0, 0, 0);
		
		double shininess = 100;
		double reflectance = 0;
		
		// add sphere
		Point3d sphereCenter = new Point3d(0, 0, 0);
		double sphereRadius = 0.5;
		Surface surface = builder.generateSurface(diffuse, specular, ambient, emission, shininess, reflectance);
		builder.addSphere(sphereCenter, sphereRadius, surface);
		
		// add light
		builder.addDirectedLight(new Vector3d(0, -1, -1), new Color(1, 1, 1));	
		Scene scene = builder.getScene();
		results = rayTracer.draw(image, canvas, scene);
		
		System.out.println("results: " + results);
	}
}
