package org.strieber.jRayTracer.engine;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import org.strieber.jRayTracer.lights.Light;
import org.strieber.jRayTracer.math.Color;
import org.strieber.jRayTracer.math.Intersection;
import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;
import org.strieber.jRayTracer.primitives.Primitive;
import org.strieber.jRayTracer.primitives.Ray;
import org.strieber.jRayTracer.primitives.Surface;


public class RayTracer {

	public final double EPSILON = 0.00000001F;
	public final int MAX_RECURSION_DEPTH = 8;

	// Singleton class
	private static RayTracer rayTracer;
	
	// globals for camera & scene
	Scene scene;
	Point3d eye;
	Point3d lookAt;
	Vector3d upDirection;
	Vector3d rightDirection;
	Vector3d viewplaneUp;
	// ray global
	Vector3d direction;
	// scene and sample info
	double screenDist;
	double pixelWidth;
	double pixelHeight;
	int superSampleWidth;

	private RayTracer() {
		
	}
	
	// provide access to one SceneBuilder
	public synchronized static RayTracer getRayTracer() {
		if (rayTracer == null) {
			rayTracer = new RayTracer();
		}
		return rayTracer;
	}

	// Disable cloning
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public Ray constructRayThroughPixel(int x, int y, double sampleXOffset, double sampleYOffset) {
		Ray ray = new Ray(eye, direction, screenDist);
		Point3d endPoint = ray.getEndPoint();

		double upOffset = -1 * (y - (scene.getSizeY() / 2) - (sampleYOffset / superSampleWidth)) * pixelHeight;
		double rightOffset = (x - (scene.getSizeX() / 2) + (sampleXOffset / superSampleWidth)) * pixelWidth;

		endPoint.setX(endPoint.getX() + (viewplaneUp.getX() * upOffset));
		endPoint.setY(endPoint.getY() + (viewplaneUp.getY() * upOffset));
		endPoint.setZ(endPoint.getZ() + (viewplaneUp.getZ() * upOffset));

		endPoint.setX(endPoint.getX() + (rightDirection.getX() * rightOffset));
		endPoint.setY(endPoint.getY() + (rightDirection.getY() * rightOffset));
		endPoint.setZ(endPoint.getZ() + (rightDirection.getZ() * rightOffset));

		ray.setDirection(endPoint.subtract(eye).convertToVector3d());
		ray.normalize();

		return ray;
	}
	


	public Intersection findIntersection(Ray ray, Primitive ignorePrimitive) {
		double minDistance = Double.POSITIVE_INFINITY;
		Primitive minPrimitive = null;

		for (Primitive primitive : scene.getPrimitiveList()) {
			double t = primitive.intersect(ray);

			if (t < minDistance && t > EPSILON && primitive != ignorePrimitive) {
				minPrimitive = primitive;
				minDistance = t;
			}
		}
		return new Intersection(minDistance, minPrimitive);
	}
	
	

	public Color getColor(Ray ray, Intersection intersection, int recursionDepth) {
		// first check recursion depths, if exceeded, then bail
		if (recursionDepth > MAX_RECURSION_DEPTH) {
			return new Color(0, 0, 0);
		}
		// grab primitive
		Primitive primitive = intersection.getPrimitive();
		// if there is no primitive in the intersection, we missed, return background color
		if (primitive == null) {
			return scene.getBackgroundColor();
		}
		// set up objects
		Surface surface = primitive.getSurface();
		Color color = new Color();
		Color specular = surface.getSpecular();
		// stretch ray to point of intersection
		ray.setMagnitude(intersection.getDistance());
		Point3d pointOfIntersection = ray.getEndPoint();
		// find the color at the ray intersection
		Color diffuse = primitive.getColorAt(pointOfIntersection);
		// calculate the normal
		ray.setMagnitude(intersection.getDistance() - 1);
		Vector3d normal = primitive.getNormal(pointOfIntersection);

		// fire rays towards each Light, see if they are visible
		for (Light light: scene.getLightList()) {
			Vector3d vectorToLight = light.getVectorToLight(pointOfIntersection);
			Ray rayToLight = new Ray(pointOfIntersection, vectorToLight, 1);
			rayToLight.normalize();

			double distanceToBlockingPrimitive = findIntersection(rayToLight, null).getDistance();
			double distanceToLight = light.getPosition().subtract(pointOfIntersection).convertToVector3d().length();

			boolean lightVisible = distanceToBlockingPrimitive <= EPSILON || 
			distanceToBlockingPrimitive >= distanceToLight;

			if (lightVisible) {
				// find the amount of light hitting the primitive
				Color amountOfLightAtIntersection = light.getAmountOfLight(pointOfIntersection);
				// the amount of light that is visible is determined by the normal
				double visibleDiffuseLight = vectorToLight.dotProduct(normal);
				// check diffuse lighting
				if (visibleDiffuseLight > 0) {
					// diffuse
					color.red += diffuse.red * amountOfLightAtIntersection.red * visibleDiffuseLight;
					color.green += diffuse.green * amountOfLightAtIntersection.green * visibleDiffuseLight;
					color.blue += diffuse.blue * amountOfLightAtIntersection.blue * visibleDiffuseLight;
				}

				// specular lighting

				// find the reflection around the normal
				Vector3d reflectedVectorToLight = vectorToLight.reflectVector(normal);
				reflectedVectorToLight.normalize();

				double visibleSpecularLight = reflectedVectorToLight.dotProduct(ray.getDirection());

				if (visibleSpecularLight > 0) {
					visibleSpecularLight = Math.pow(Math.abs(visibleSpecularLight), surface.getShininess());
					// add in specular lighting
					color.red += specular.red * amountOfLightAtIntersection.red * visibleSpecularLight;
					color.green += specular.green * amountOfLightAtIntersection.green * visibleSpecularLight;
					color.blue += specular.blue * amountOfLightAtIntersection.blue * visibleSpecularLight;
				}
			}
		}

		Color sceneAmbient = scene.getAmbientLight();
		Color surfaceAmbient = surface.getAmbient();

		color.red += sceneAmbient.red * surfaceAmbient.red;
		color.green += sceneAmbient.green * surfaceAmbient.green;
		color.blue += sceneAmbient.blue * surfaceAmbient.blue;

		Color surfaceEmission = surface.getEmission();

		color.red += surfaceEmission.red;
		color.green += surfaceEmission.green;
		color.blue += surfaceEmission.blue;

		// fire reflection ray
		Vector3d oppositeVector = ray.getDirection().minus();
		Vector3d reflectionDirection = oppositeVector.reflectVector(normal);
		Ray reflectionRay = new Ray(pointOfIntersection, reflectionDirection, 1);
		reflectionRay.normalize();

		Intersection reflectionIntersection = findIntersection(reflectionRay, null);
		Color reflectionColor = getColor(reflectionRay, reflectionIntersection, recursionDepth + 1);

		color.red += reflectionColor.red * surface.getReflectance();
		color.green += reflectionColor.green * surface.getReflectance();
		color.blue += reflectionColor.blue * surface.getReflectance();

		return color;
	}
	
	public BufferedImage draw(BufferedImage image, Canvas canvas, Scene myScene) {
		// takes canvas for possible real-time rendering and displaying
		// currently not used.
		scene = myScene;
		Camera camera = scene.getCamera();
		eye = camera.getEye();
		lookAt = camera.getLookAt();
		screenDist = camera.getScreenDist();
		pixelWidth = camera.getScreenWidth() / scene.getSizeY();
		pixelHeight = scene.getSizeX() / scene.getSizeY() * pixelWidth;
		upDirection = camera.getUpDirection();
		rightDirection = camera.getRightDirection();
		superSampleWidth = scene.getSuperSampleWidth();
		viewplaneUp = camera.getViewplaneUp();
		direction = camera.getDirection();

		// Graphics2D g2 = (Graphics2D) canvas.getGraphics();

		// scan pixels
		for (int y = 0; y < scene.getSizeY(); ++y) {
			for (int x = 0; x < scene.getSizeX(); ++x) {
				// start sampling
				int hits = 0;
				Color color = new Color();
				for (int k = 0; k < superSampleWidth; k++) {
					for (int l = 0; l < superSampleWidth; l++) {
						Color sampleColor = null;

						// create ray
						Ray ray = constructRayThroughPixel(x, y, k, l);

						// find intersecting primitive
						Intersection intersection = findIntersection(ray, null);

						// if we hit something, grab its color
						if (intersection.getPrimitive() != null) {
                            hits++;
                            sampleColor = getColor(ray, intersection, 1);
                            color = color.add(sampleColor);
                            
                            ray.setMagnitude(intersection.getDistance());
						}
					}
				}

				// if no samples hit, use background color
				if (hits == 0) {
					color = scene.getBackgroundColor();
				}
				else {
					// average the samples
					color.multiplyBy(1.0 / hits);
				}
				// write to image here
				
				image.setRGB(x, y, color.convertToInt());
			}
		}
		return image;
	}
}