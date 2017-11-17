/*
 * TCSS 305 - Assignment 5 PowerPaint
 * Fall 2017
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * An interface to ensure proper methods are being used during tool
 * class design.
 *
 * @author Tanner Brown
 * @version 6 Nov 2017
 *
 */
public interface Tool {

	/**
	 * Setter for the starting point.
	 * @param theStartingPoint point2D for the starting location
	 */
	void setStartingPoint(Point2D theStartingPoint);

	/**
	 * Getter for the starting point.
	 * @return the starting point.
	 */
	Point2D getStartingPoint();

	/**
	 * Getter for the end point.
	 * @param theEndPoint point2D for the ending location
	 */
	void setEndPoint(Point2D theEndPoint);

	/**
	 * Getter for the ending point.
	 * @return the ending point.
	 */
	Point2D getEndPoint();

	/**
	 * Returns the shape of the current tool being used.
	 * @return the shape of the tool.
	 */
	Shape getShape();
}
