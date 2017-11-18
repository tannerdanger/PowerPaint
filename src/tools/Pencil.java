/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package tools;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Class that creates a tool that makes a shape defined by the user's mouse movements.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class Pencil extends AbstractTool {

	/**
	 * A GeneralPath object.
	 */
	private GeneralPath myShape;

	/**
	 * Basic constructor that simply calls to the super.
	 */
	public Pencil() {
		super();
	}

	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd   Point2D object representing the ending point of an object being drawn.
	 */
	public Pencil(final Point2D theStart, final Point2D theEnd) {
		super(theStart, theEnd);
	}

	@Override
	public void setStartingPoint(final Point2D theStart) {
		myShape = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		myShape.moveTo(theStart.getX(), theStart.getY());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shape getShape() {
		myShape.lineTo(getEndPoint().getX(), getEndPoint().getY());
		return myShape;
	}
}
