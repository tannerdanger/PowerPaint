/*
 * TCSS 305 - Assignment 5 PowerPaint
 * Fall 2017
 */

package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JColorChooser;

/**
 * Class that sets the icon color for the color... buttons in the menu bar.
 * @author Tanner Brown
 * @version 9 Nov 2017
 *
 */
public class ColorIcon implements Icon {

	/** Constant variable that sets the height of the icon to 14. */
	private static final int ICON_HEIGHT = 14;

	/** Constant variable that sets the width of the icon to 14. */
	private static final int ICON_WIDTH = 14;

	/** The icon's color. */
	private Color myIconColor;

	/**
	 * Constructor that creates an object based on the color given.
	 *
	 * @param theIconColor the color of the icon.
	 */
	public ColorIcon(final Color theIconColor) {

		myIconColor = theIconColor;
	}

	@Override
	public int getIconHeight() {
		return ICON_HEIGHT;
	}

	@Override
	public int getIconWidth() {
		return ICON_WIDTH;
	}

	@Override
	public void paintIcon(final Component theMenuItem, final Graphics theGraphics,
	                      final int theX, final int theY) {

		theGraphics.setColor(myIconColor);
		theGraphics.fillRect(theX, theY, ICON_WIDTH, ICON_HEIGHT);


	}

	/**
	 * Allows the user to choose a new color and sets it in icon as a new color.
	 * @return the new color.
	 */
	public Color chooseColor() {
		final Color newColor;
		newColor = JColorChooser.showDialog(null, "Choose a new color", myIconColor);
		myIconColor = newColor;

		return newColor;

	}

	/**
	 * Allows external sources to set the color of the icon.
	 * @param theColor the color to set the icon as.
	 */
	public void setColor(final Color theColor) {
		myIconColor = theColor;
	}

	/**
	 * Allows external sources to get the color of the icon.
	 * @return the icon's color.
	 */
	public Color getColor() {
		return myIconColor;
	}

}
