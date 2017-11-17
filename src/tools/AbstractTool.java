/**
 *
 */
package tools;

import java.awt.Color;
import java.awt.Shape;
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

	private Color myColor;

	//public tool for creating objects
	public AbstractTool(){

	}

	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd Point2D object representing the ending point of an object being drawn.
	 */
	protected AbstractTool(final Point2D theStart, final Point2D theEnd, Color theColor) {
		//myStartingPoint = theStart;
		//myEndPoint = theEnd;
		myStartingPoint = Objects.requireNonNull(theStart);
		myEndPoint = Objects.requireNonNull(theEnd);
		myColor = theColor;
	}

	/** {@inheritDoc} */
	@Override
	public void setStartingPoint(final Point2D theStartingPoint) {

		//myStartingPoint = theStartingPoint;
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

		//myEndPoint = theStartingPoint;
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

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor(Color theColor) {
		this.myColor = theColor;
	}


	public String getName(){

		return this.getClass().getSimpleName();
	}

	public Double getStartX(){

		return myStartingPoint.getX();
	}

//	public Double getStartY(){			}

}
