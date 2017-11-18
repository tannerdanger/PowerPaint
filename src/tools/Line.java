/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */


package tools;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Tool that is used to draw a straight line between two points.
 *
 * @author Tanner Brown
 * @version 9 Nov 2017
 *
 */
public class Line extends AbstractTool {
	/**
	 * Basic constructor that simply calls to the super.
	 */
	public Line() {
		super();
	}
	/**
	 * Instantiates the line tool and sends values to the parent class.
	 *
	 * @param theStart the the start point of the object being drawn.
	 * @param theEnd the the end point of the object being drawn.
	 */
	public Line(final Point2D theStart, final Point2D theEnd) {

		super(theStart, theEnd);
	}



	@Override
	public Shape getShape() {

		return new Line2D.Double(getStartingPoint(), getEndPoint());
	}

}
