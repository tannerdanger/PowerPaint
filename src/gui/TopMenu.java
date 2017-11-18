/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package gui;

import actions.Listeners;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Creates the MenuBar that is anchored at the top of the frame of the GUI.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class TopMenu extends JMenuBar {

	/** A randomly generated serialization id. */
	private static final long serialVersionUID = -4578820771934826470L;
	//thickness menu constants
	/** The minimum value of the slider. */
	private static final int MIN_THICK = 0;
	/** The max value of the slider. */
	private static final int MAX_THICK = 20;
	/** The spacing that shows number values on the slider. */
	private static final int MAJOR_SPACE = 5;
	/** The how far apart each tick mark is on the slider. */
	private static final int MINOR_SPACE = 1;
	/** The default value of the slider. */
	private static final int THICK_DEFAULT = 10;

	/** JMenu that contains the options components. */
	private JMenu myOptionsMenu;
	/** JMenu that contains the tools components. */
	private JMenu myToolsMenu;
	/** JMenu that contains the help components. */
	private JMenu myHelpMenu;
	/** Slider that sets the thickness value. */
	private JSlider mySlider;

	/**
	 * Constructor that calls to two helper methods.
	 */
	protected TopMenu() {
		super();
		build();
	}

	/**
	 * Builds the tool bar and sets the JMenu dropdown buttons.
	 */
	private void build() {

		myOptionsMenu = new JMenu("Options");
		myOptionsMenu.setMnemonic(KeyEvent.VK_O);

		myToolsMenu = new JMenu("Tools");
		myToolsMenu.setMnemonic(KeyEvent.VK_T);

		myHelpMenu = new JMenu("Help");
		myHelpMenu.setMnemonic(KeyEvent.VK_H);
		buildSlider();
		buildAbout();
		add(myOptionsMenu);
		add(myToolsMenu);
		add(myHelpMenu);

	}

	/**
	 * Builds the About button in the Help menu.
	 */
	private void buildAbout() {
		final JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		myHelpMenu.add(aboutItem);

		aboutItem.addActionListener(new Listeners());
	}

	/**
	 * Builds the thickness slider in the options menu.
	 */
	private void buildSlider() {

		final JMenu thicknessMenu = new JMenu("Thickness");
		thicknessMenu.setMnemonic(KeyEvent.VK_T);

		mySlider = new JSlider(MIN_THICK, MAX_THICK, THICK_DEFAULT);
		mySlider.setMajorTickSpacing(MAJOR_SPACE);
		mySlider.setMinorTickSpacing(MINOR_SPACE);
		mySlider.setPaintLabels(true);
		mySlider.setPaintTicks(true);

		myOptionsMenu.add(thicknessMenu);
		thicknessMenu.add(mySlider);
		myOptionsMenu.add(new JSeparator());
	}


	/**
	 * Adds a radiobutton to the Tool menu.
	 *
	 * @param theRadioButton to be added to the Tools menu.
	 */
	protected void addTools(final JRadioButtonMenuItem theRadioButton) {
		myToolsMenu.add(theRadioButton);
	}

	/**
	 * Adds a component to the options menu.
	 *
	 * @param theMenuItem to be added to the options menu.
	 */
	protected void addOptions(final JComponent theMenuItem) {
		myOptionsMenu.add(theMenuItem);
	}

	/**
	 * Method that allows the GUI to add the slider.
	 * @return the thickness slider.
	 */
	protected JSlider getSlider() {
		return mySlider;
	}

}
