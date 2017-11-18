/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package tools;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Class that creates a tool that makes a rectangle shaped graphic.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class Rectangle extends AbstractTool {

	/**
	 * Basic constructor that simply calls to the super.
	 */
	public Rectangle() {
		super();
	}

	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd   Point2D object representing the ending point of an object being drawn.
	 */
	protected Rectangle(final Point2D theStart, final Point2D theEnd) {
		super(theStart, theEnd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shape getShape() {
		final Rectangle2D.Double shape = new Rectangle2D.Double();

		shape.setFrameFromDiagonal(getStartingPoint(), getEndPoint());

		return shape;
	}
}
