package com.robolucha.shared;

import org.apache.log4j.Logger;

import com.robolucha.models.Bullet;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.runner.luchador.LuchadorRunner;

import io.swagger.client.model.MainSceneComponent;

public class Calc {

	private static Logger logger = Logger.getLogger(Calc.class);

	public static boolean insideTheMapLimits(int width, int height, double x, double y, int halfSize) {

		if (x - halfSize < 0 || y - halfSize < 0 || x + halfSize > width || y + halfSize > height) {

			return false;
		}

		return true;
	}

	public static double toRadian(double angle) {
		return angle * Math.PI / 180.0;
	}

	/**
	 * 
	 * @param myPosX
	 * @param myPosY
	 * @param myRadarAngle
	 * @param myRadarRangeAngle
	 * @param myRadarRadius
	 * @param posX
	 * @param posY
	 * @return double if greater than ZERO the point is found in the radar arc
	 */
	public static double detectEnemy(double myPosX, double myPosY, double myRadarAngle, double myRadarRangeAngle,
			double myRadarRadius, double posX, double posY) {

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("***** detectEnemy source(%s,%s,%s) radar(%s,%s) target(%s,%s)", myPosX, myPosY,
					myRadarAngle, myRadarRangeAngle, myRadarRadius, posX, posY));
		}

		double dirX = myPosX + Math.cos(Calc.toRadian(myRadarAngle));
		double dirY = myPosY + Math.sin(Calc.toRadian(myRadarAngle));

		double v1X = myPosX - dirX;
		double v1Y = myPosY - dirY;

		double v2X = myPosX - posX;
		double v2Y = myPosY - posY;

		double v1Mag = Math.sqrt(Math.pow(v1X, 2) + Math.pow(v1Y, 2));
		double v2Mag = Math.sqrt(Math.pow(v2X, 2) + Math.pow(v2Y, 2));

		v1X /= v1Mag;
		v1Y /= v1Mag;

		v2X /= v2Mag;
		v2Y /= v2Mag;

		double dot = (v1X * v2X) + (v1Y * v2Y);

		dot = Calc.roundTo4Decimals(dot);
		double dotThreshold = Calc.roundTo4Decimals(Math.cos(toRadian(myRadarRangeAngle / 2)));

		if (logger.isDebugEnabled()) {
			logger.debug("dot = " + dotThreshold);
			logger.debug("dotThreshold = " + dotThreshold);
		}

		if (dot >= dotThreshold) {
			double dist = Math.sqrt(Math.pow(myPosX - posX, 2) + Math.pow(myPosY - posY, 2));

			if (logger.isDebugEnabled()) {
				logger.debug("dist = " + dist);
			}

			if (dist < myRadarRadius) {
				if (logger.isDebugEnabled()) {
					logger.debug("colision on: " + myPosX + " dist: " + dist + " dot: " + dot);
				}
				return dot; // chance de colidir
			}
		}

		return -1;
	}

	/**
	 * check Luchador colision
	 * 
	 * @param x
	 * @param y
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean intersectRobot(double x, double y, LuchadorRunner source, LuchadorRunner target) {

		double radiusA = source.getSize() / 2.0;
		double radiusB = target.getSize() / 2.0;

		double dist = Math.sqrt(Math.pow(x - target.getState().getX(), 2) + Math.pow(y - target.getState().getY(), 2));

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("intersectRobot (%s,%s) source.size=%s, target(%s,%s)", x, y, source.getSize(),
					target.getState().getX(), target.getState().getY()));

			logger.debug(String.format("intersectRobot dist=%s, radius %s + %s = %s", dist, radiusA, radiusB,
					(radiusA + radiusB)));

		}

		return (dist < (radiusA + radiusB));
	}

	public static boolean intersectCirclewithSceneComponent(double x, double y, double radius, MainSceneComponent component) {

		// Finds the closest point from the rectangle to the center of the luchador
		double minX = Math.min(x, component.getX() + component.getWidth());
		double DeltaX = x - Math.max(component.getX(), minX);

		double minY = Math.min(y, component.getY() + component.getHeight());
		double DeltaY = y - Math.max(component.getY(), minY);

		// calculate the distance from the closest point and the luchador center
		double dist = Math.sqrt(Math.pow(DeltaX, 2) + Math.pow(DeltaY, 2));
		return dist < radius;
	}

	/**
	 * check colision using circle calculation
	 * 
	 * @param source
	 * @param bullet
	 * @return
	 */
	public static boolean intersectBullet(LuchadorRunner source, Bullet bullet) {

		LuchadorMatchState lutchador = source.getState();

		double radiusA = source.getSize() / 2;
		double radiusB = bullet.getSize() / 2;

		double dist = Math
				.sqrt(Math.pow(lutchador.getX() - bullet.getX(), 2) + Math.pow(lutchador.getY() - bullet.getY(), 2));

		return (dist < (radiusA + radiusB));
	}

	/**
	 * keep angle between 0 a 360
	 * 
	 * @param newAngle
	 * @return
	 */
	public static double fixAngle(double newAngle) {

		if (newAngle > 360) {
			newAngle = newAngle - 360;
		} else {
			if (newAngle < 0) {
				newAngle = 360 + newAngle;
			}
		}

		return newAngle;
	}

	/**
	 * rounder
	 * 
	 * @param fireCoolDown
	 * @param i
	 * @return
	 */
	public static double roundTo4Decimals(double input) {
		return (double) Math.round(input * 10000) / 10000;
	}

}
