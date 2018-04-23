package org.strieber.jRayTracer.math;

public class Color {
	public double red, green, blue;

	public Color() {
		red = 0;
		green = 0;
		blue = 0;
	}
	public Color(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public Color(double rgb[]) {
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
	}

	public Color add(Color color) {
		double newRed = red + color.red;
		double newGreen = green + color.green;
		double newBlue = blue + color.blue;			
		return (new Color(newRed, newGreen, newBlue));
	}
	public void addTo(Color color) {
		this.red += color.red;
		this.green += color.green;
		this.blue += color.blue;
	}
	// subtract returns the difference of this color and the specified color
	public Color subtract(Color color) {
		double newRed = red - color.red;
		double newGreen = green - color.green;
		double newBlue = blue - color.blue;
		return (new Color(newRed, newGreen, newBlue));
	}
	// subtracts the specified color from this color
	public void subtractFrom(Color color) {
		red -= color.red;
		green -= color.green;
		blue -= color.blue;
	}
	// returns the product of this color and the specified value
	public Color multiply(double value) {
		double newRed = red * value;
		double newGreen = green * value;
		double newBlue = blue * value;
		return (new Color(newRed, newGreen, newBlue));
	}
	// multiplies this color by the specified value
	public void multiplyBy(double value) {
		red *= value;
		green *= value;
		blue *= value;
	}
	// returns the quotient of the division of this color by the specified value
	public Color divide(double value) {
		double newRed = red / value;
		double newGreen = green / value;
		double newBlue = blue / value;
		return (new Color(newRed, newGreen, newBlue));
	}
	// divides this color by the specified value
	public void divideBy(double value) {
		red /= value;
		green /= value;
		blue /= value;
	}
	// returns the member-wise quotient of the division of this color by the specified color
	public Color divide(Color color) {
		double newRed = red / color.red;
		double newGreen = green / color.green;
		double newBlue = blue / color.blue;
		return (new Color(newRed, newGreen, newBlue));
	}
	// divides this vector member-wise by the specified color
	public void divideBy(Color color) {
		red /= color.red;
		green /= color.green;
		blue /= color.blue;
	}
	// convert to integer for BufferedImage
	public int convertToInt() {
		int colorInt =	Math.min(255, (int)Math.round(red * 255)) << 16 & 0xFF0000 |
						Math.min(255, (int)Math.round(green * 255)) << 8 & 0xFF00 |
						Math.min(255, (int)Math.round(blue * 255));
		return colorInt;
	}
	// return the x, y, and z values of this vector as an array of double
	public double[] getData() {
		double[] data = new double[3];
		data[0] = red;
		data[1] = green;
		data[2] = blue;
		return data;
	}
	// over-ride toString
	public String toString() {
		return "Color -- red: " + red + " green: " + green + " blue: " + blue;
	}
}