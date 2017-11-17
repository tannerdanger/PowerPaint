package gui;

import tools.Drawing;
import tools.Line;
import tools.Pencil;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel{

	//Defaults
	private static final int DEFAULT_THICKNESS = 10;
	private static final Point OFFSCREEN_POINT = new Point(-1000, -1000);

	//Colors
	private Color myPrimaryColor;
	private Color mySecondaryColor;

	private Color myActiveColor;

	private Tool myTool;

	//points
	private Point myStartPoint;
	private Point myEndPoint;

	private boolean myIsDragging;
	private int myThickness;

	private static final Color UW_PURPLE = new Color(51, 0, 111);
	private static final Color UW_GOLD = new Color(232, 211, 162);

	/** Constant value for the default size of the Draw Panel. */
	private static final Dimension PREFERRED_PANEL_SIZE = new Dimension(500, 300);

	/** Collections that holds all of the objects the user has drawn. */
	protected List<Drawing> myDrawings;

	protected boolean myClearEnabled;

	protected DrawPanel(){

		super();
		setPreferredSize(PREFERRED_PANEL_SIZE);
		setBackground(Color.WHITE);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		myThickness = DEFAULT_THICKNESS;
		myPrimaryColor = UW_PURPLE;
		mySecondaryColor = UW_GOLD;
		myActiveColor = myPrimaryColor;

		myTool = new Line(OFFSCREEN_POINT, OFFSCREEN_POINT, myPrimaryColor);
		myIsDragging = false;
		myDrawings = new ArrayList<>();
		myStartPoint = OFFSCREEN_POINT;
		createListener();
		myClearEnabled = false;

	}

	private void createListener() {
		addMouseMotionListener(new Draw());
		addMouseListener(new Draw());
		/*
		addMouseListener(new MouseAdapter() {


			@Override
			public void mousePressed(final MouseEvent theEvent) {
				myTool.setStartingPoint(myStartPoint);
				if("Eraser".equals(myTool.getClass().getSimpleName())) {
					myActiveColor = Color.WHITE;
				}else if(theEvent.getButton() == 3){
					myActiveColor = mySecondaryColor;
				}else{
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
				repaint();

			}
		});
		*/
	}

	class Draw extends MouseAdapter{


		@Override
		public void mousePressed(final MouseEvent theEvent) {
			myTool.setStartingPoint(myStartPoint);
			if("Eraser".equals(myTool.getClass().getSimpleName())) {
				myActiveColor = Color.WHITE;
			}else if(theEvent.getButton() == 3){
				myActiveColor = mySecondaryColor;
			}else{
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
			firePropertyChange("clear", myClearEnabled, true);



		}



	}

	@Override
	public void paintComponent(final Graphics theGraphics) {

		super.paintComponent(theGraphics);

		final Graphics2D g2d = (Graphics2D) theGraphics;

		//for better graphics display
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);




		for(final Drawing draw : myDrawings) {
			g2d.setStroke(new BasicStroke(draw.getMyThick()));
			g2d.setColor(draw.getMyColor());
			g2d.draw(draw.getMyShape());
		}

		//Draw temporary shape while user is drawing
		if(myIsDragging){
			g2d.setStroke(new BasicStroke(myThickness));
			g2d.setColor(myActiveColor);
			g2d.draw(myTool.getShape());
		}


	}

	public void setTool(final Tool theTool){

		myTool = theTool;
	}

	public void setMyPrimaryColor(Color theColor) {
		myPrimaryColor = theColor;
	}

	public void setMySecondaryColor(Color theColor) {
		mySecondaryColor = theColor;
	}

	public void setThickness(final int theThickness){
		myThickness = theThickness;
	}

	public void clear(){
		myDrawings.clear();
		repaint();
		firePropertyChange("clear", null, false);
	}

}
