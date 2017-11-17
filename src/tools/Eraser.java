package tools;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Eraser extends Pencil {

	public Eraser(){
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
	protected Eraser(Point2D theStart, Point2D theEnd, Color theColor) {
		super(theStart, theEnd, Color.WHITE);

	}

//	@Override
//	public Shape getShape() {
//		return new Line2D.Double(getStartingPoint(), getEndPoint());
//	}
}
