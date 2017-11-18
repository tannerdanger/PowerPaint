/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package tools;

import java.awt.geom.Point2D;

/**
 * Class that creates a tool that makes an eraser.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class Eraser extends Pencil {

	/**
	 * Basic constructor that simply calls to the super.
	 */
	public Eraser() {
		super();
	}

	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd   Point2D object representing the ending point of an object being drawn.
	 */
	protected Eraser(final Point2D theStart, final Point2D theEnd) {
		super(theStart, theEnd);

	}

}
