package org.strieber.jRayTracer.tests;

import org.strieber.jRayTracer.math.Color;


public class TestColor {
	
	public static boolean testNoArgsConstructor() {
		boolean isSuccess = false;
		Color color = new Color();
		if (color.red == 0 && color.green == 0 && color.blue == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testXYZConstructor() {
		boolean isSuccess = false;
		Color color = new Color(1, 2, 3);
		if (color.red == 1 && color.green == 2 && color.blue == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testArrayConstructor() {
		boolean isSuccess = false;
		double[] test = {7, 8, 9};
		Color color = new Color(test);
		if (color.red == 7 && color.green == 8 && color.blue == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testAdd() {
		boolean isSuccess = false;
		Color colorToAdd = new Color(1, 2, 3);
		Color color = colorToAdd.add(colorToAdd);
		if (color.red == 2 && color.green == 4 && color.blue == 6) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testAddTo() {
		boolean isSuccess = false;
		Color colorToAdd = new Color(1, 2, 3);
		Color color = new Color(2, 3, 4);
		color.addTo(colorToAdd);
		if (color.red == 3 && color.green == 5 && color.blue == 7) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testSubtract() {
		boolean isSuccess = false;
		Color colorToSubtract = new Color(7, 8, 9);
		Color color = colorToSubtract.subtract(colorToSubtract);
		if (color.red == 0 && color.green == 0 && color.blue == 0) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testSubtractFrom() {
		boolean isSuccess1 = false;
		boolean isSuccess2 = false;
		Color colorToSubtract = new Color(1, 2, 3);
		Color color = new Color(2, 3, 4);
		color.subtractFrom(colorToSubtract);
		if (color.red == 1 && color.green == 1 && color.blue == 1) {
			isSuccess1 = true;
		}
		color = new Color(0, 0, 0);
		color.subtractFrom(colorToSubtract);
		if (color.red == -1 && color.green == -2 && color.blue == -3) {
			isSuccess2 = true;
		}
		return isSuccess1 && isSuccess2;
	}
	
	public static boolean testMultiply() {
		boolean isSuccess = false;
		double scalar = 2;
		Color colorToMultiply = new Color(2, 3, 4);
		Color color = colorToMultiply.multiply(scalar);
		if (color.red == 4 && color.green == 6 && color.blue == 8) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMultiplyBy() {
		boolean isSuccess = false;
		double scalar = 3;
		Color color = new Color(1, 2, 3);
		color.multiplyBy(scalar);
		if (color.red == 3 && color.green == 6 && color.blue == 9) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testDivide() {
		boolean isSuccess = false;
		double scalar = 2;
		Color colorToDivide = new Color(2, 4, 6);
		Color color = colorToDivide.divide(scalar);
		if (color.red == 1 && color.green == 2 && color.blue == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testDivideBy() {
		boolean isSuccess = false;
		double scalar = 2;
		Color color = new Color(4, 6, 8);
		color.divideBy(scalar);
		if (color.red == 2 && color.green == 3 && color.blue == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMemberWiseDivide() {
		boolean isSuccess = false;
		Color colorToDivideBy = new Color(2, 3, 4);
		Color color = colorToDivideBy.divide(colorToDivideBy);
		if (color.red == 1 && color.green == 1 && color.blue == 1) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testMemberWiseDivideBy() {
		boolean isSuccess = false;
		Color colorToDivideBy = new Color(1, 1, 1);
		Color color = new Color(2, 3, 4);
		color.divideBy(colorToDivideBy);
		if (color.red == 2 && color.green == 3 && color.blue == 4) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static boolean testEquals() {
		boolean isSuccess1 = false;
		Color color1 = new Color(2, 2, 2);
		if (color1.equals(color1)) {
			isSuccess1 = true;
		}
		boolean isSuccess2 = true;
		Color color2 = new Color(2, 3, 4);
		if (color1.equals(color2)) {
			isSuccess2 = false;
		}
		return isSuccess1 && isSuccess2;
	}
	
	public static boolean testGetData() {
		boolean isSuccess = false;
		Color color = new Color(1, 2, 3);
		double[] colorArray = color.getData();
		if (colorArray[0] == 1 && colorArray[1] == 2 && colorArray[2] == 3) {
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public static String testToString() {
		Color color = new Color(2, 3, 4);
		String string = color.toString();
		return string;
	}
	
	// test all Color functions
	public static void main(String[] args) {
		System.out.println("Testing Color");
		System.out.println("testNoArgsConstructor: " + testNoArgsConstructor());
		System.out.println("testXYZConstructor: " + testXYZConstructor());
		System.out.println("testArrayConstructor: " + testArrayConstructor());
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
		System.out.println("testEquals: " + testEquals());
		System.out.println("testGetData: " + testGetData());
		System.out.println("testToString: " + testToString());
	}
}