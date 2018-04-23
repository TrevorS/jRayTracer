package org.strieber.jRayTracer.tests;

import org.strieber.jRayTracer.math.Vector3d;

public class TestVector3d {

	public static boolean testNoArgsConstructor() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d();
		if (vector.getX() == 0 && vector.getY() == 0 && vector.getZ() == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testXYZConstructor() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d(1, 2, 3);
		if (vector.getX() == 1 && vector.getY() == 2 && vector.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testVector3dConstructor() {
		boolean isSuccess = false;
		Vector3d testPoint = new Vector3d(4, 5, 6);
		Vector3d vector = new Vector3d(testPoint);
		if (vector.getX() == 4 && vector.getY() == 5 && vector.getZ() == 6) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testArrayConstructor() {
		boolean isSuccess = false;
		double[] test = {7, 8, 9};
		Vector3d vector = new Vector3d(test);
		if (vector.getX() == 7 && vector.getY() == 8 && vector.getZ() == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testSetXYZ() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d();
		vector.setX(1);
		vector.setY(2);
		vector.setZ(3);
		if (vector.getX() == 1 && vector.getY() == 2 && vector.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testGetXYZ() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d(2, 3, 4);
		if (vector.getX() == 2 && vector.getY() == 3 && vector.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testClone() {
		boolean isSuccess = false;
		Vector3d vectorToClone = new Vector3d(3, 4, 5);
		Vector3d vector = (Vector3d)vectorToClone.clone();
		if (vector.getX() == 3 && vector.getY() == 4 && vector.getZ() == 5) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testAdd() {
		boolean isSuccess = false;
		Vector3d vectorToAdd = new Vector3d(1, 2, 3);
		Vector3d vector = vectorToAdd.add(vectorToAdd);
		if (vector.getX() == 2 && vector.getY() == 4 && vector.getZ() == 6) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testAddTo() {
		boolean isSuccess = false;
		Vector3d vectorToAdd = new Vector3d(1, 2, 3);
		Vector3d vector = new Vector3d(2, 3, 4);
		vector.addTo(vectorToAdd);
		if (vector.getX() == 3 && vector.getY() == 5 && vector.getZ() == 7) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testSubtract() {
		boolean isSuccess = false;
		Vector3d vectorToSubtract = new Vector3d(7, 8, 9);
		Vector3d vector = vectorToSubtract.subtract(vectorToSubtract);
		if (vector.getX() == 0 && vector.getY() == 0 && vector.getZ() == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testSubtractFrom() {
		boolean isSuccess1 = false;
		boolean isSuccess2 = false;
		Vector3d vectorToSubtract = new Vector3d(1, 2, 3);
		Vector3d vector = new Vector3d(2, 3, 4);
		vector.subtractFrom(vectorToSubtract);
		if (vector.getX() == 1 && vector.getY() == 1 && vector.getZ() == 1) {
			isSuccess1 = true;
		}
		vector = new Vector3d(0, 0, 0);
		vector.subtractFrom(vectorToSubtract);
		if (vector.getX() == -1 && vector.getY() == -2 && vector.getZ() == -3) {
			isSuccess2 = true;
		}
		return isSuccess1 && isSuccess2;
	}

	public static boolean testMultiply() {
		boolean isSuccess = false;
		double scalar = 2;
		Vector3d vectorToMultiply = new Vector3d(2, 3, 4);
		Vector3d vector = vectorToMultiply.multiply(scalar);
		if (vector.getX() == 4 && vector.getY() == 6 && vector.getZ() == 8) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testMultiplyBy() {
		boolean isSuccess = false;
		double scalar = 3;
		Vector3d vector = new Vector3d(1, 2, 3);
		vector.multiplyBy(scalar);
		if (vector.getX() == 3 && vector.getY() == 6 && vector.getZ() == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testDivide() {
		boolean isSuccess = false;
		double scalar = 2;
		Vector3d vectorToDivide = new Vector3d(2, 4, 6);
		Vector3d vector = vectorToDivide.divide(scalar);
		if (vector.getX() == 1 && vector.getY() == 2 && vector.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testDivideBy() {
		boolean isSuccess = false;
		double scalar = 2;
		Vector3d vector = new Vector3d(4, 6, 8);
		vector.divideBy(scalar);
		if (vector.getX() == 2 && vector.getY() == 3 && vector.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testMemberWiseDivide() {
		boolean isSuccess = false;
		Vector3d vectorToDivideBy = new Vector3d(2, 3, 4);
		Vector3d vector = vectorToDivideBy.divide(vectorToDivideBy);
		if (vector.getX() == 1 && vector.getY() == 1 && vector.getZ() == 1) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testMemberWiseDivideBy() {
		boolean isSuccess = false;
		Vector3d vectorToDivideBy = new Vector3d(1, 1, 1);
		Vector3d vector = new Vector3d(2, 3, 4);
		vector.divideBy(vectorToDivideBy);
		if (vector.getX() == 2 && vector.getY() == 3 && vector.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testMinus() {
		boolean isSuccess = false;
		Vector3d vectorToMinus = new Vector3d(2, 3, 4);
		Vector3d vector = vectorToMinus.minus();
		if (vector.getX() == -2 && vector.getY() == -3 && vector.getZ() == -4) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static boolean testEquals() {
		boolean isSuccess1 = false;
		Vector3d vector1 = new Vector3d(2, 2, 2);
		if (vector1.equals(vector1)) {
			isSuccess1 = true;
		}
		boolean isSuccess2 = true;
		Vector3d vector2 = new Vector3d(2, 3, 4);
		if (vector1.equals(vector2)) {
			isSuccess2 = false;
		}
		return isSuccess1 && isSuccess2;
	}

	public static boolean testGetData() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d(1, 2, 3);
		double[] vectorArray = vector.getData();
		if (vectorArray[0] == 1 && vectorArray[1] == 2 && vectorArray[2] == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testCrossProduct() {
		boolean isSuccess = false;
		
		Vector3d vectorB = new Vector3d(1, 2, 3);
		double bX = vectorB.getX();
		double bY = vectorB.getY();
		double bZ = vectorB.getZ();
		
		Vector3d vectorC = new Vector3d(2, 3, 4);
		double cX = vectorC.getX();
		double cY = vectorC.getY();
		double cZ = vectorC.getZ();
		
		double aX = bY * cZ - bZ * cY;
		double aY = bZ * cX - bX * cZ;
		double aZ = bX * cY - bY * cX;
				
		Vector3d vector = vectorB.crossProduct(vectorC);
		
		if (vector.getX() == aX && vector.getY() == aY && vector.getZ() == aZ) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testDotProduct() {
		boolean isSuccess = false;
		
		Vector3d vectorB = new Vector3d(1, 2, 3);
		double bX = vectorB.getX();
		double bY = vectorB.getY();
		double bZ = vectorB.getZ();
		
		Vector3d vectorC = new Vector3d(2, 3, 4);
		double cX = vectorC.getX();
		double cY = vectorC.getY();
		double cZ = vectorC.getZ();
		
		double result = bX * cX + bY * cY + bZ * cZ;
		
		if (result == vectorB.dotProduct(vectorC)) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testLength() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d(2, 3, 4);
		double dotProduct = vector.getX() * vector.getX() + 
							vector.getY() * vector.getY() + 
							vector.getZ() * vector.getZ();
		double length = Math.sqrt(dotProduct);
		if (length == vector.length()) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testLengthSquared() {
		boolean isSuccess = false;
		Vector3d vector = new Vector3d(2, 3, 4);
		double dotProduct = vector.getX() * vector.getX() + 
							vector.getY() * vector.getY() + 
							vector.getZ() * vector.getZ();
		
		double length = Math.sqrt(dotProduct);
		
		length *= length;
		
		if (length == vector.lengthSquared()) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testNormalize() {
		boolean isSuccess = false;
		Vector3d vector1 = new Vector3d(2, 3, 4);
		Vector3d vector2 = (Vector3d) vector1.clone();
		double length = vector1.length();
		
		vector1.divideBy(length);
		vector2.normalize();
		
		
		if (vector1.equals(vector2)) {
			isSuccess = true;
		}
		return isSuccess;
	}

	public static String testToString() {
		Vector3d vector = new Vector3d(2, 3, 4);
		String string = vector.toString();
		return string;
	}

	// test all Vector3d functions
	public static void main(String[] args) {
		System.out.println("Testing Vector3d");
		System.out.println("testNoArgsConstructor: " + testNoArgsConstructor());
		System.out.println("testXYZConstructor: " + testXYZConstructor());
		System.out.println("testVector3dConstructor: " + testVector3dConstructor());
		System.out.println("testArrayConstructor: " + testArrayConstructor());
		System.out.println("testSetXYZ: " + testSetXYZ());
		System.out.println("testGetXYZ: " + testGetXYZ());
		System.out.println("testClone: " + testClone());
		System.out.println("testAdd: " + testAdd());
		System.out.println("testAddTo: " + testAddTo());
		System.out.println("testSubtract: " + testSubtract());
		System.out.println("testSubtractFrom: " + testSubtractFrom());
		System.out.println("testMultiply: " + testMultiply());
		System.out.println("testMultiplyBy:" + testMultiplyBy());
		System.out.println("testDivide: " + testDivide());
		System.out.println("testDivideBy: " + testDivideBy());
		System.out.println("testMemberWiseDivide: " + testMemberWiseDivide());
		System.out.println("testMemberWiseDivideBy: " + testMemberWiseDivideBy());
		System.out.println("testMinus: " + testMinus());
		System.out.println("testEquals: " + testEquals());
		System.out.println("testGetData: " + testGetData());
		System.out.println("testCrossProduct: " + testCrossProduct());
		System.out.println("testDotProduct: " + testDotProduct());
		System.out.println("testLength: " + testLength());
		System.out.println("testLengthSquared: " + testLengthSquared());
		System.out.println("testNormalize: " + testNormalize());
		System.out.println("testToString: " + testToString());
	}
}