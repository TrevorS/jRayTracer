package org.strieber.jRayTracer.math;

public class Point3d implements Cloneable {
	private double x, y, z;
	
	public Point3d() {
		
	}
	public Point3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point3d(Point3d point3d) {
		this.x = point3d.getX();
		this.y = point3d.getY();
		this.z = point3d.getZ();
	}
	public Point3d(double[] point) {
		this.x = point[0];
		this.y = point[1];
		this.z = point[2];
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getX() {
		return x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public double getZ() {
		return z;
	}
	// clone method
	public Object clone() {
		return (new Point3d(this));
	}
	// add returns the sum of this point and the specified point object
	public Point3d add(Point3d point3d) {
		double newX = x + point3d.getX();
		double newY = y + point3d.getY();
		double newZ = z + point3d.getZ();
		return (new Point3d(newX, newY, newZ));
	}
	// add the specified point to this point
	public void addTo(Point3d point3d) {
		x += point3d.getX();
		y += point3d.getY();
		z += point3d.getZ();
	}
	// subtract returns the difference of this point and the specified point object
	public Point3d subtract(Point3d point3d) {
		double newX = x - point3d.getX();
		double newY = y - point3d.getY();
		double newZ = z - point3d.getZ();
		return (new Point3d(newX, newY, newZ));
	}
	// subtracts the specified point from this point
	public void subtractFrom(Point3d point3d) {
		x = x - point3d.getX();
		y = y - point3d.getY();
		z = z - point3d.getZ();
	}
	// returns the product of this point and the specified value
	public Point3d multiply(double value) {
		double newX = x * value;
		double newY = y * value;
		double newZ = z * value;
		return (new Point3d(newX, newY, newZ));
	}
	// multiplies this point by the specified value
	public void multiplyBy(double value) {
		x *= value;
		y *= value;
		z *= value;
	}
	// returns the quotient of the division of this point by the specified value
	public Point3d divide(double value) {
		double newX = x / value;
		double newY = y / value;
		double newZ = z / value;
		return (new Point3d(newX, newY, newZ));
	}
	// divides this point by the specified value
	public void divideBy(double value) {
		x /= value;
		y /= value;
		z /= value;
	}
	// returns the member-wise quotient of the division of this point by the specified point
	public Point3d divide(Point3d point) {
		double newX = x / point.getX();
		double newY = y / point.getY();
		double newZ = z / point.getZ();
		return (new Point3d(newX, newY, newZ));
	}
	// divides this point member-wise by the specified point
	public void divideBy(Point3d point) {
		x /= point.getX();
		y /= point.getY();
		z /= point.getZ();
	}
	// returns the opposite of this point
	public Point3d minus() {
		return (new Point3d(x * -1, y * -1, z * -1));
	}
	// returns true if and only if this point and the specified point are equal
	public boolean equals(Point3d point3d) {
		return (x == point3d.getX()) && (y == point3d.getY()) && (z == point3d.getZ());
	}
	// return the x, y, and z values of this point as an array of double
	public double[] getData() {
		double[] data = new double[3];
		data[0] = x;
		data[1] = y;
		data[2] = z;
		return data;
	}
	// convert to vector *** remove soon ***
	public Vector3d convertToVector3d() {
		Vector3d vector = new Vector3d(x, y, z);
		return vector;
	}
	public String toString() {
		return "Point3d(" + x + ", " + y + ", " + z + ")";
	}
}