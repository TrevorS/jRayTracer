package org.strieber.jRayTracer.tests;

import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class TestPoint3d {
	
	public static boolean testNoArgsConstructor() {
		boolean isSuccess = false;
		Point3d point = new Point3d();
		if (point.getX() == 0 && point.getY() == 0 && point.getZ() == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testXYZConstructor() {
		boolean isSuccess = false;
		Point3d point = new Point3d(1, 2, 3);
		if (point.getX() == 1 && point.getY() == 2 && point.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testPoint3dConstructor() {
		boolean isSuccess = false;
		Point3d testPoint = new Point3d(4, 5, 6);
		Point3d point = new Point3d(testPoint);
		if (point.getX() == 4 && point.getY() == 5 && point.getZ() == 6) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testArrayConstructor() {
		boolean isSuccess = false;
		double[] test = {7, 8, 9};
		Point3d point = new Point3d(test);
		if (point.getX() == 7 && point.getY() == 8 && point.getZ() == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testSetXYZ() {
		boolean isSuccess = false;
		Point3d point = new Point3d();
		point.setX(1);
		point.setY(2);
		point.setZ(3);
		if (point.getX() == 1 && point.getY() == 2 && point.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testGetXYZ() {
		boolean isSuccess = false;
		Point3d point = new Point3d(2, 3, 4);
		if (point.getX() == 2 && point.getY() == 3 && point.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testClone() {
		boolean isSuccess = false;
		Point3d pointToClone = new Point3d(3, 4, 5);
		Point3d point = (Point3d)pointToClone.clone();
		if (point.getX() == 3 && point.getY() == 4 && point.getZ() == 5) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testAdd() {
		boolean isSuccess = false;
		Point3d pointToAdd = new Point3d(1, 2, 3);
		Point3d point = pointToAdd.add(pointToAdd);
		if (point.getX() == 2 && point.getY() == 4 && point.getZ() == 6) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testAddTo() {
		boolean isSuccess = false;
		Point3d pointToAdd = new Point3d(1, 2, 3);
		Point3d point = new Point3d(2, 3, 4);
		point.addTo(pointToAdd);
		if (point.getX() == 3 && point.getY() == 5 && point.getZ() == 7) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testSubtract() {
		boolean isSuccess = false;
		Point3d pointToSubtract = new Point3d(7, 8, 9);
		Point3d point = pointToSubtract.subtract(pointToSubtract);
		if (point.getX() == 0 && point.getY() == 0 && point.getZ() == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testSubtractFrom() {
		boolean isSuccess1 = false;
		boolean isSuccess2 = false;
		Point3d pointToSubtract = new Point3d(1, 2, 3);
		Point3d point = new Point3d(2, 3, 4);
		point.subtractFrom(pointToSubtract);
		if (point.getX() == 1 && point.getY() == 1 && point.getZ() == 1) {
			isSuccess1 = true;
		}
		point = new Point3d(0, 0, 0);
		point.subtractFrom(pointToSubtract);
		if (point.getX() == -1 && point.getY() == -2 && point.getZ() == -3) {
			isSuccess2 = true;
		}
		return isSuccess1 && isSuccess2;
	}
	
	public static boolean testMultiply() {
		boolean isSuccess = false;
		double scalar = 2;
		Point3d pointToMultiply = new Point3d(2, 3, 4);
		Point3d point = pointToMultiply.multiply(scalar);
		if (point.getX() == 4 && point.getY() == 6 && point.getZ() == 8) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMultiplyBy() {
		boolean isSuccess = false;
		double scalar = 3;
		Point3d point = new Point3d(1, 2, 3);
		point.multiplyBy(scalar);
		if (point.getX() == 3 && point.getY() == 6 && point.getZ() == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testDivide() {
		boolean isSuccess = false;
		double scalar = 2;
		Point3d pointToDivide = new Point3d(2, 4, 6);
		Point3d point = pointToDivide.divide(scalar);
		if (point.getX() == 1 && point.getY() == 2 && point.getZ() == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testDivideBy() {
		boolean isSuccess = false;
		double scalar = 2;
		Point3d point = new Point3d(4, 6, 8);
		point.divideBy(scalar);
		if (point.getX() == 2 && point.getY() == 3 && point.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMemberWiseDivide() {
		boolean isSuccess = false;
		Point3d pointToDivideBy = new Point3d(2, 3, 4);
		Point3d point = pointToDivideBy.divide(pointToDivideBy);
		if (point.getX() == 1 && point.getY() == 1 && point.getZ() == 1) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMemberWiseDivideBy() {
		boolean isSuccess = false;
		Point3d pointToDivideBy = new Point3d(1, 1, 1);
		Point3d point = new Point3d(2, 3, 4);
		point.divideBy(pointToDivideBy);
		if (point.getX() == 2 && point.getY() == 3 && point.getZ() == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMinus() {
		boolean isSuccess = false;
		Point3d pointToMinus = new Point3d(2, 3, 4);
		Point3d point = pointToMinus.minus();
		if (point.getX() == -2 && point.getY() == -3 && point.getZ() == -4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testEquals() {
		boolean isSuccess1 = false;
		Point3d point1 = new Point3d(2, 2, 2);
		if (point1.equals(point1)) {
			isSuccess1 = true;
		}
		boolean isSuccess2 = true;
		Point3d point2 = new Point3d(2, 3, 4);
		if (point1.equals(point2)) {
			isSuccess2 = false;
		}
		return isSuccess1 && isSuccess2;
	}
	
	public static boolean testGetData() {
		boolean isSuccess = false;
		Point3d point = new Point3d(1, 2, 3);
		double[] pointArray = point.getData();
		if (pointArray[0] == 1 && pointArray[1] == 2 && pointArray[2] == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testConvertToVector3d() {
		boolean isSuccess = false;
		Point3d point = new Point3d(2, 3, 4);
		Vector3d vector = point.convertToVector3d();
		if (vector.getX() == point.getX() && vector.getY() == point.getY() && vector.getZ() == point.getZ()) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static String testToString() {
		Point3d point = new Point3d(2, 3, 4);
		String string = point.toString();
		return string;
	}
	
	// test all Point3d functions
	public static void main(String[] args) {
		System.out.println("Testing Point3d");
		System.out.println("testNoArgsConstructor: " + testNoArgsConstructor());
		System.out.println("testXYZConstructor: " + testXYZConstructor());
		System.out.println("testPoint3dConstructor: " + testPoint3dConstructor());
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
		System.out.println("testConvertToVector3d: " + testConvertToVector3d());
		System.out.println("testToString: " + testToString());
	}
}