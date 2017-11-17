package tools;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ellipse extends AbstractTool {

	public Ellipse(){
		super();
	}
	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd   Point2D object representing the ending point of an object being drawn.
	 * @param theColor
	 */
	protected Ellipse(Point2D theStart, Point2D theEnd, Color theColor) {
		super(theStart, theEnd, theColor);
	}

	@Override
	public Shape getShape() {

		final Ellipse2D.Double shape = new Ellipse2D.Double();

		shape.setFrameFromDiagonal(getStartingPoint(), getEndPoint());

		return shape;
	}
}
