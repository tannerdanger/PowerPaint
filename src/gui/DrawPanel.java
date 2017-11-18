/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */
package gui;

import tools.Drawing;
import tools.Line;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that creates a custom JPanel that will be drawn on.
 *
 * @author Tanner
 * @version 16 Nov 2017
 */
public class DrawPanel extends JPanel {


	/** A generated serialization id. */
	private static final long serialVersionUID = 8716631924375666405L;


	//Defaults
	/** A constant color that matches The University of Washington's color purple. */
	private static final Color UW_PURPLE = new Color(51, 0, 111);
	/**
	 * A constant color that matches The University of Washington's color Gold.
	 */
	private static final Color UW_GOLD = new Color(232, 211, 162);

	/**
	 * The default thickness of the slider.
	 */
	private static final int DEFAULT_THICKNESS = 10;
	/** A point that is offscreen to start drawing. */
	private static final Point OFFSCREEN_POINT = new Point(-1000, -1000);
	/**
	 * Constant value for the default size of the Draw Panel.
	 */
	private static final Dimension PREFERRED_PANEL_SIZE = new Dimension(500, 300);
	/**
	 * Constant for the Clear button action behavior.
	 */
	private static final String CLEAR = "clear";
	/**
	 * Constant value for the right mouse button.
	 */
	private static final int RIGHT_CLICK = 3;
	/**
	 * A boolean that determines if the clear button is currently enabled.
	 */
	private final boolean myClearEnabled;

	//Colors
	/**
	 * Collections that holds all of the objects the user has drawn.
	 */
	protected List<Drawing> myDrawings;
	/**
	 * The primary color the user draws with.
	 */
	private Color myPrimaryColor;
	/**
	 * The secondary color the user draws with.
	 */
	private Color mySecondaryColor;
	/**
	 * The color that is active to being drawn on the canvas.
	 */
	private Color myActiveColor;

	//points
	/** The tool being drawn with. */
	private Tool myTool;
	/** The the starting point for the shape being drawn. */
	private Point myStartPoint;
	/** The the end point for the shape being drawn. */
	private Point myEndPoint;
	/** A boolean that determines if the mouse is being "drug". */
	private boolean myIsDragging;
	/** The value that represents how thick the shape is. */
	private int myThickness;


	/**
	 * A constructor that build the DrawPanel.
	 */
	protected DrawPanel() {
		super();
		buildPanel();

		myThickness = DEFAULT_THICKNESS;
		myStartPoint = OFFSCREEN_POINT;
		myTool = new Line(OFFSCREEN_POINT, OFFSCREEN_POINT);
		myIsDragging = false;
		myDrawings = new ArrayList<>();
		myClearEnabled = false;
		myActiveColor = myPrimaryColor;

		createListener();

	}

	/**
	 * A helper method that builds the panel based on default values.
	 */
	private void buildPanel() {
		setPreferredSize(PREFERRED_PANEL_SIZE);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setBackground(Color.WHITE);
		myPrimaryColor = UW_PURPLE;
		mySecondaryColor = UW_GOLD;
	}

	/**
	 * Creates listeners for the mouse actions.
	 */
	private void createListener() {
		addMouseMotionListener(new Draw());
		addMouseListener(new Draw());
	}

	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);

		final Graphics2D g2d = (Graphics2D) theGraphics;
		//set antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (final Drawing draw : myDrawings) {
			g2d.setStroke(new BasicStroke(draw.getMyThick()));
			g2d.setColor(draw.getMyColor());
			g2d.draw(draw.getMyShape());
		}
		//Draw temporary shape while user is drawing
		if (myIsDragging) {
			g2d.setStroke(new BasicStroke(myThickness));
			g2d.setColor(myActiveColor);
			g2d.draw(myTool.getShape());
		}
		firePropertyChange(CLEAR, myClearEnabled, !myDrawings.isEmpty());
	}

	/**
	 * Sets the tool.
	 *
	 * @param theTool the tool object
	 */
	public void setTool(final Tool theTool) {

		myTool = theTool;
	}

	/**
	 * Sets the primary color.
	 *
	 * @param theColor the new color.
	 */
	public void setMyPrimaryColor(final Color theColor) {
		myPrimaryColor = theColor;
	}

	/**
	 * Sets the secondary color.
	 *
	 * @param theColor the new color.
	 */
	public void setMySecondaryColor(final Color theColor) {
		mySecondaryColor = theColor;
	}

	/**
	 * Sets the thickness of the tool shape.
	 *
	 * @param theThickness of the shape, determined by the thickness slider.
	 */
	public void setThickness(final int theThickness) {
		myThickness = theThickness;
	}

	/**
	 * This method clears the canvas then disables the clear button.
	 */
	public void clearCanvas() {
		myDrawings.clear();
		repaint();
		firePropertyChange(CLEAR, null, false);
	}


	/**
	 * Inner class that determines behavior when the mouse is used in the DrawPanel.
	 *
	 * @author Tanner Brown
	 * @version 17 Nov 2017
	 *
	 */
	class Draw extends MouseAdapter {

		@Override
		public void mousePressed(final MouseEvent theEvent) {
			myTool.setStartingPoint(theEvent.getPoint());
			if ("Eraser".equals(myTool.getClass().getSimpleName())) {
				myActiveColor = Color.WHITE;
			} else if (theEvent.getButton() == RIGHT_CLICK) {
				myActiveColor = mySecondaryColor;
			} else {
				myActiveColor = myPrimaryColor;
			}

			myIsDragging = true;
			myStartPoint = theEvent.getPoint();
			myEndPoint = theEvent.getPoint();
			myTool.setStartingPoint(myStartPoint);
			myTool.setEndPoint(myEndPoint);
			repaint();

		}

		@Override
		public void mouseDragged(final MouseEvent theEvent) {
			myIsDragging = true;
			myEndPoint = theEvent.getPoint();
			myTool.setEndPoint(myEndPoint);
			repaint();
		}

		@Override
		public void mouseReleased(final MouseEvent theEvent) {
			myIsDragging = false;
			myEndPoint = theEvent.getPoint();
			myTool.setEndPoint(myEndPoint);
			myDrawings.add(new Drawing(myThickness, myActiveColor, myTool.getShape()));
			repaint();
			firePropertyChange(CLEAR, myClearEnabled, true);

		}
	}
}
