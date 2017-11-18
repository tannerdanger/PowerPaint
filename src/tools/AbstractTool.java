/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package tools;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

/**
 *
 * An Abstract super class for all of the tool objects to inherit from.
 *
 * @author Tanner Brown
 * @version 9 Nov 2017
 *
 */
public abstract class AbstractTool implements Tool {

	/** Point2D object representing the starting point of an object being drawn. */
	private Point2D myStartingPoint;

	/** Point2D object representing the ending point of an object being drawn. */
	private Point2D myEndPoint;


	/**
	 * Empty constructor.
	 */
	public AbstractTool() {
		//Empty.
	}

	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd Point2D object representing the ending point of an object being drawn.
	 */
	protected AbstractTool(final Point2D theStart, final Point2D theEnd) {

		//Ensures the values being passed aren't null values.
		myStartingPoint = Objects.requireNonNull(theStart);
		myEndPoint = Objects.requireNonNull(theEnd);

	}

	/** {@inheritDoc} */
	@Override
	public void setStartingPoint(final Point2D theStartingPoint) {

		//Ensures the values being passed aren't null values.
		myStartingPoint = Objects.requireNonNull(theStartingPoint);

	}

	/** {@inheritDoc} */
	@Override
	public Point2D getStartingPoint() {

		return myStartingPoint;
	}

	/** {@inheritDoc} */
	@Override
	public void setEndPoint(final Point2D theEndPoint) {

		//Ensures the values being passed aren't null values.
		myEndPoint = Objects.requireNonNull(theEndPoint);

	}

	/** {@inheritDoc} */
	@Override
	public Point2D getEndPoint() {

		return myEndPoint;
	}

	/** {@inheritDoc} */
	@Override
	public abstract Shape getShape();


}
