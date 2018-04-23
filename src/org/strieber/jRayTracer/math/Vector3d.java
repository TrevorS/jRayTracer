package org.strieber.jRayTracer.math;

public class Vector3d implements Cloneable {
	private double x, y, z;

	// constructors
	public Vector3d() {
		this.x = this.y = this.z = 0;
	}
	public Vector3d(double x) {
		this.x = this.y = this.z = x;
	}
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3d(Vector3d vector3d) {
		this.x = vector3d.getX();
		this.y = vector3d.getY();
		this.z = vector3d.getZ();
	}
	public Vector3d(double[] vector) {
		this.x = vector[0];
		this.y = vector[1];
		this.z = vector[2];
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	// clone method
	public Object clone() {
		return (new Vector3d(this));
	}
	// add returns the sum of this vector and the specified vector object
	public Vector3d add(Vector3d vector3d) {
		double newX = x + vector3d.getX();
		double newY = y + vector3d.getY();
		double newZ = z + vector3d.getZ();
		return (new Vector3d(newX, newY, newZ));
	}
	// add the specified vector to this vector
	public void addTo(Vector3d vector3d) {
		x+= vector3d.getX();
		y+= vector3d.getY();
		z+= vector3d.getZ();
	}
	// subtract returns the difference of this vector and the specified vector object
	public Vector3d subtract(Vector3d vector3d) {
		double newX = x - vector3d.getX();
		double newY = y - vector3d.getY();
		double newZ = z - vector3d.getZ();
		return (new Vector3d(newX, newY, newZ));
	}
	// subtracts the specified vector from this vector
	public void subtractFrom(Vector3d vector3d) {
		x -= vector3d.getX();
		y -= vector3d.getY();
		z -= vector3d.getZ();
	}
	// returns the product of this vector and the specified value
	public Vector3d multiply(double value) {
		double newX = x * value;
		double newY = y * value;
		double newZ = z * value;
		return (new Vector3d(newX, newY, newZ));
	}
	// multiplies this vector by the specified value
	public void multiplyBy(double value) {
		x *= value;
		y *= value;
		z *= value;
	}
	// returns the cross product of this vector and the specified vector
	public Vector3d crossProduct(Vector3d vector3d) {
		double newX = y * vector3d.getZ() - z * vector3d.getY();
		double newY = z * vector3d.getX() - x * vector3d.getZ();
		double newZ = x * vector3d.getY() - y * vector3d.getX();
		return (new Vector3d(newX, newY, newZ));
	}
	// returns the dot product of this vector and the specified vector
	public double dotProduct(Vector3d vector3d) {
		double Xs = x * vector3d.getX();
		double Ys = y * vector3d.getY();
		double Zs = z * vector3d.getZ();
		return Xs + Ys + Zs;
	}
	// returns the quotient of the division of this vector by the specified value
	public Vector3d divide(double value) {
		double newX = x / value;
		double newY = y / value;
		double newZ = z / value;
		return (new Vector3d(newX, newY, newZ));
	}
	// divides this vector by the specified value
	public void divideBy(double value) {
		x /= value;
		y /= value;
		z /= value;
	}
	// returns the member-wise quotient of the division of this vector by the specified vector
	public Vector3d divide(Vector3d vector) {
		double newX = x / vector.getX();
		double newY = y / vector.getY();
		double newZ = z / vector.getZ();
		return (new Vector3d(newX, newY, newZ));
	}
	// divides this vector member-wise by the specified vector
	public void divideBy(Vector3d vector) {
		x /= vector.getX();
		y /= vector.getY();
		z /= vector.getZ();
	}
	// returns the opposite of this vector
	public Vector3d minus() {
		return (new Vector3d(x * -1, y * -1, z * -1));
	}
	// returns true if and only if this vector and the specified vector are equal
	public boolean equals(Vector3d vector3d) {
		return (x == vector3d.getX()) && (y == vector3d.getY()) && (z == vector3d.getZ());
	}
	// return the x, y, and z values of this vector as an array of double
	public double[] getData() {
		double[] data = new double[3];
		data[0] = x;
		data[1] = y;
		data[2] = z;
		return data;
	}
	// returns an integer hash code by adding x, y, and z and casting as an int
	public int hashCode() {
		return (int)(x + y + z);
	}
	// returns the length of this vector
	public double length() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	// returns the same as length of this vector
	public double magnitude() {
		return this.length(); 
	}
	// returns the length of this vector squared
	public double lengthSquared() {
		return this.length() * this.length();
	}
	// replace the elements of this vector with the elements of the candidate vector
	// if they are smaller
	public void minimize(Vector3d vector3d) {
		if (vector3d.getX() < x) {
			x = vector3d.getX();
		}
		if (vector3d.getY() < y) {
			y = vector3d.getY();
		}
		if (vector3d.getZ() < z) {
			z = vector3d.getZ();
		}
	}
	// replace the elements of this vector with the elements of the candidate vector
	// if they are larger
	public void maximize(Vector3d vector3d) {
		if (vector3d.getX() > x) {
			x = vector3d.getX();
		}
		if (vector3d.getY() > y) {
			y = vector3d.getY();
		}
		if (vector3d.getZ() > z) {
			z = vector3d.getZ();
		}
	}
	// normalize this vector by dividing by its magnitude 
	public void normalize() {
		this.divideBy(this.magnitude());
	}
	// return this vector normalized
	public Vector3d returnNormalized() {
		return (this.divide(this.magnitude()));
	}
	// return this vector reflected around a normal
	public Vector3d reflectVector(Vector3d normal) {
		double dotProduct = this.dotProduct(normal);
		
		Vector3d result = new Vector3d();
		result.setX(-1 * x + 2 * normal.getX() * dotProduct);
		result.setY(-1 * y + 2 * normal.getY() * dotProduct);
		result.setZ(-1 * z + 2 * normal.getZ() * dotProduct);
		
		return result;
	}
	// over-ride toString method
	public String toString() {
		return "Vector3d(" + x + ", " + y + ", " + z + ")";
	}
}