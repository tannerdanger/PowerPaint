package tools;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Objects;

public class Pencil extends AbstractTool {

	private GeneralPath myShape;

	public Pencil(){
		super();
		//myShape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
	}
	/**
	 * Constructor for all of the tool subclasses. Instantiates the starting and ending
	 * point of the object being drawn.
	 *
	 * @param theStart Point2D object representing the starting point of an object being drawn.
	 * @param theEnd   Point2D object representing the ending point of an object being drawn.
	 * @param theColor
	 */
	public Pencil(Point2D theStart, Point2D theEnd, Color theColor) {
		super(theStart, theEnd, theColor);
		//myShape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
	}

	@Override
	public void setStartingPoint(final Point2D theStart){
		//super.setStartingPoint(theStart);
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
