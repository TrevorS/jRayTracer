package org.strieber.jRayTracer.engine;

import org.strieber.jRayTracer.math.Point3d;
import org.strieber.jRayTracer.math.Vector3d;

public class Camera {
	
	private Point3d eye;
	private Point3d lookAt;
	private Vector3d upDirection;
	private Vector3d rightDirection;
	private Vector3d viewplaneUp;
	private Vector3d direction;

	private double screenDist = 1;
	private double screenWidth = 2;
	
	public Camera() {
		eye = new Point3d(0, 0, 0);
		lookAt = new Point3d(0, 0, 0);
		upDirection = new Vector3d(0, 0, 0);
		
		// set direction
		direction = lookAt.subtract(eye).convertToVector3d();
		// set upDirection & rightDirection
		rightDirection = upDirection.crossProduct(direction);
		rightDirection.normalize();
		rightDirection.multiplyBy(-1);
				
		viewplaneUp = rightDirection.crossProduct(direction);
		viewplaneUp.normalize();
	}
	
	public Camera(Point3d eye, Point3d lookAt, Vector3d upDirection, Vector3d direction) {
		this.eye = eye;
		this.lookAt = lookAt;
		this.upDirection = upDirection;
		this.direction = direction;
		// set upDirection & rightDirection
		rightDirection = upDirection.crossProduct(direction);
		rightDirection.normalize();
		rightDirection.multiplyBy(-1);
				
		viewplaneUp = rightDirection.crossProduct(direction);
		viewplaneUp.normalize();
	}
	
	public Camera(Point3d eye, Point3d lookAt, Vector3d upDirection) {
		this.eye = eye;
		this.lookAt = lookAt;
		this.upDirection = upDirection;
		// set direction
		direction = lookAt.subtract(eye).convertToVector3d();
		// set upDirection & rightDirection
		rightDirection = upDirection.crossProduct(direction);
		rightDirection.normalize();
		rightDirection.multiplyBy(-1);
				
		viewplaneUp = rightDirection.crossProduct(direction);
		viewplaneUp.normalize();
	}
	
	public Point3d getEye() {
		return eye;
	}
	public void setEye(Point3d eye) {
		this.eye = eye;
	}
	public Point3d getLookAt() {
		return lookAt;
	}
	public void setLookAt(Point3d lookAt) {
		this.lookAt = lookAt;
	}
	public Vector3d getUpDirection() {
		return upDirection;
	}
	public void setUpDirection(Vector3d upDirection) {
		this.upDirection = upDirection;
	}
	public Vector3d getRightDirection() {
		return rightDirection;
	}
	public void setRightDirection(Vector3d rightDirection) {
		this.rightDirection = rightDirection;
	}
	public Vector3d getViewplaneUp() {
		return viewplaneUp;
	}
	public void setViewplaneUp(Vector3d viewplaneUp) {
		this.viewplaneUp = viewplaneUp;
	}
	public Vector3d getDirection() {
		return direction;
	}
	public void setDirection(Vector3d direction) {
		this.direction = direction;
	}
	public double getScreenDist() {
		return screenDist;
	}
	public void setScreenDist(double screenDist) {
		this.screenDist = screenDist;
	}
	public double getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}
}