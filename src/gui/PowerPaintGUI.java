/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */
package gui;

import actions.Listeners;
import actions.ToolActions;
import tools.*;
import tools.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class that builds the user interface that the user draws with.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class PowerPaintGUI extends JFrame {

	/** A randomly generated serialization id. */
	private static final long serialVersionUID = 4826188794255200508L;
	/**
	 * A constant color that matches The University of Washington's color purple.
	 */
	private static final Color UW_PURPLE = new Color(51, 0, 111);
	/** A constant color that matches The University of Washington's color Gold. */
	private static final Color UW_GOLD = new Color(232, 211, 162);
	/**
	 * A variable for the a string so that checkstyle is pleased.
	 */
	private static final String LINE = "Line";

	/**
	 * A custom JPanel that the user draws on.
	 */
	private static DrawPanel myDrawPanel;

	//    /** The primary color selected by the user. */
	//    private static Color myPrimaryColor;
	//    /** The secondary color selected by the user. */
	//    private static Color mySecondaryColor;

	/**
	 * The colored icon for the primary color button.
	 */
	private static ColorIcon myPrimaryColorIcon;
	/**
	 * The colored icon for the secondary color button.
	 */
	private static ColorIcon mySecondaryColorIcon;

	/**
	 * The movable toolbar that holds buttons for the different drawing tools.
	 */
	private JToolBar myToolBar;

	/**
	 * The dropdown menubar at the top of the frame.
	 */
	private TopMenu myTopMenu;

	/**
	 * The button that allows the user to select a primary color.
	 */
	private JMenuItem myPrimaryColorButton;
	/**
	 * The button that allows the user to select a secondary color.
	 */
	private JMenuItem mySecondColorButton;

	/**
	 * The JSlider used to determine the thickness of the shape being drawn.
	 */
	private JSlider mySlider;

	/** The menuitem that clears the drawing area. */
	private JMenuItem myClearButon;

	/**
	 * A list of actions that the various tool buttons trigger.
	 */
	private List<ToolActions> myToolActions;

	/**
	 * The constructor that simply calls to the constructor and sets the title of the frame.
	 * The setup() method is then run from the PowerPointMain class to build the GUI.
	 */
	protected PowerPaintGUI() {
		super("Assignment 5");
	}

	/**
	 * A method that sets the tool in use.
	 *
	 * @param theTool that is in use.
	 */
	public static void setTool(final Tool theTool) {
		myDrawPanel.setTool(theTool);
	}

	/**
	 * Sets the color of icon for the primary color button.
	 *
	 * @param theColor the new color.
	 */
	public static void setPrimaryIconColor(final Color theColor) {
		myPrimaryColorIcon.setColor(theColor);
	}

	/**
	 * Sets the color of icon for the secondary color button.
	 *
	 * @param theColor the new color.
	 */
	public static void setSecondaryIconColor(final Color theColor) {
		mySecondaryColorIcon.setColor(theColor);
	}

	/**
	 * Sets the primary color for this class, as well as the draw panel.
	 *
	 * @param theColor the new color.
	 */
	public static void setPrimaryColor(final Color theColor) {
		myDrawPanel.setMyPrimaryColor(theColor);
	}

	/**
	 * Sets the secondary color for this class, as well as the draw panel.
	 *
	 * @param theColor the new color.
	 */
	public static void setSecondaryColor(final Color theColor) {
		myDrawPanel.setMySecondaryColor(theColor);
	}

	/**
	 * The method that builds the GUI.
	 */
	protected void setup() {

		myPrimaryColorIcon = new ColorIcon(UW_PURPLE);
		mySecondaryColorIcon = new ColorIcon(UW_GOLD);
		myDrawPanel = new DrawPanel();

		setupToolActions();

		setupToolBar();

		setupMenuBar();

		setupDisplay();

		addListeners();

	}

	/**
	 * This method sets up the components inside the frame.
	 */
	private void setupDisplay() {

		//Create the icon
		final ImageIcon frameIcon = new ImageIcon("./images/realCharlesBryan32.gif");
		final Image image = frameIcon.getImage();

		//setup Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Set the place the GUI pops up to the center of the screen instead of the corner
         * because its so annoying...this is for me...not for you...
         */
		setLocationRelativeTo(null);
		setIconImage(image);
		setJMenuBar(myTopMenu);
		add(myDrawPanel, BorderLayout.CENTER);
		add(myToolBar, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	/**
	 * This method creates a different ToolAction for each tool class.
	 */
	private void setupToolActions() {
		myToolActions = new ArrayList<ToolActions>();

		myToolActions.add(new ToolActions("Pencil",
				new ImageIcon("./images/pencil.gif"),
				new Pencil()));

		myToolActions.add(new ToolActions(LINE, new
				ImageIcon("./images/line.gif"),
				new Line()));

		myToolActions.add(new ToolActions("Rectangle",
				new ImageIcon("./images/rectangle.gif"),
				new Rectangle()));

		myToolActions.add(new ToolActions("Ellipse",
				new ImageIcon("./images/ellipse.gif"),
				new Ellipse()));

		myToolActions.add(new ToolActions("Eraser",
				new ImageIcon("./images/eraser.gif"),
				new Eraser()));


	}

	/**
	 * Creates the toolbar and adds buttons to it.
	 */
	private void setupToolBar() {
		myToolBar = new JToolBar();


		final ButtonGroup btngrp = new ButtonGroup();
		for (final ToolActions ta : myToolActions) {
			final JToggleButton tempToggle = new JToggleButton(ta);
			btngrp.add(tempToggle);
			myToolBar.add(tempToggle);
		}
	}

	/**
	 * Creates the menubar at the top of the frame.
	 */
	private void setupMenuBar() {
		myTopMenu = new TopMenu();

		//Setup Slider
		mySlider = myTopMenu.getSlider();

		final ButtonGroup btngrp = new ButtonGroup();

		for (final ToolActions ta : myToolActions) {
			final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(ta);
			btngrp.add(btn);
			myTopMenu.addTools(btn);
		}

		//Create color buttons
		myPrimaryColorButton = new JMenuItem("Primary Color...", myPrimaryColorIcon);
		myPrimaryColorButton.setMnemonic(KeyEvent.VK_P);
		mySecondColorButton = new JMenuItem("Secondary Color...", mySecondaryColorIcon);
		mySecondColorButton.setMnemonic(KeyEvent.VK_S);

		//Create clear button
		myClearButon = new JMenuItem("Clear");
		myClearButon.setEnabled(false);
		myClearButon.setMnemonic(KeyEvent.VK_C);

		myTopMenu.addOptions(myPrimaryColorButton);
		myTopMenu.addOptions(mySecondColorButton);
		myTopMenu.addOptions(new JSeparator());
		myTopMenu.addOptions(myClearButon);
	}

	/**
	 * Adds all of the action listeners for all of the components.
	 */
	private void addListeners() {
		myPrimaryColorButton.addActionListener(new Listeners());
		mySecondColorButton.addActionListener(new Listeners());
		myClearButon.addActionListener(new Listeners(myDrawPanel));
		mySlider.addChangeListener(new Listeners(myDrawPanel, mySlider));

		//Listens for property change to enable or disable the clear button
		myDrawPanel.addPropertyChangeListener(new Listeners(myClearButon));
	}


}
