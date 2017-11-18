/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package tools;

import java.awt.*;

/**
 * This object stores the thickness, color, and shape of the object so
 * it can be displayed as a graphic on the drawing panel.
 *
 * @author Tanner Brown
 * @version 15 Nov 2017
 */
public class Drawing {

	/**
	 * The shape of the object.
	 */
	private final Shape myShape;

	/** Thickness of the shape's border.*/
	private final int myThick;

	/** The color of the object. */
	private final Color myColor;

	/**
	 * Constructor that sets the values of the Drawing object's fields.
	 *
	 * @param theThick Thickness of the shape's border.
	 * @param theColor The color of the object.
	 * @param theShape The shape of the object.
	 */
	public Drawing(final int theThick, final Color theColor, final Shape theShape) {

		myThick = theThick;

		myColor = theColor;

		myShape = theShape;

	}


	/**
	 * Getter for the object's shape.
	 * @return the shape.
	 */
	public Shape getMyShape() {
		return myShape;
	}

	/**
	 * Getter for the object's thickness.
	 * @return the thickness.
	 */
	public int getMyThick() {
		return myThick;
	}

	/**
	 * Getter for the object's color.
	 * @return the color.
	 */
	public Color getMyColor() {
		return myColor;
	}

}
