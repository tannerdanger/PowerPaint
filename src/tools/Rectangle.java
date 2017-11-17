package tools;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractTool {

	public Rectangle(){
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
	protected Rectangle(Point2D theStart, Point2D theEnd, Color theColor) {
		super(theStart, theEnd, theColor);
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
