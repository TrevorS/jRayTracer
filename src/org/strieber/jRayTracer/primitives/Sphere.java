package org.strieber.jRayTracer.primitives;

import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class Sphere extends Primitive {
	private double size;
	
	public Sphere() {
		
	}
	
	public Sphere(Point3d position, double size, Surface surface) {
		this.position = position;
		this.size = size;
		this.surface = surface;
	}

	public void setSize(double size) {
		this.size = size;
	}
	public double getSize() {
		return size;
	}
	
	public double intersect(Ray ray) {
		// intersection formulas from:
		// http://www.amazon.com/Object-Oriented-Ray-Tracing-Nicholas-Wilt/dp/0471304158
		// checked out from Lane Library
		
		Vector3d L = position.subtract(ray.getStart()).convertToVector3d();
		Vector3d V = ray.getDirection();
		
		double tCA = L.dotProduct(V);
		
		if (tCA < 0) {
			// camera is inside sphere or sphere center lies behind ray
			// miss
			return Double.POSITIVE_INFINITY;
		}
		
		double LSquared = L.dotProduct(L);
		double dSquared = LSquared - (tCA * tCA);
		double radiusSquared = size * size;
		
		if (dSquared > radiusSquared) {
			// miss
			return Double.POSITIVE_INFINITY;
		}
		
		double tHC = Math.sqrt(radiusSquared - dSquared);
		
		if (L.dotProduct(L) < LSquared) {
			// ray originated in sphere (intersection is exit point)
			return tCA + tHC;
		}
		else {
			// ray originated outside the sphere (intersection is the entrance point)
			return tCA - tHC;
		}
	}
	
	@Override
	public Vector3d getNormal(Point3d point) {
		Vector3d normal = point.subtract(position).convertToVector3d();
		normal.normalize();
		return normal;
	}

	public String toString() {
		return "Sphere / position: " + position + " size: " + size;
	}
}