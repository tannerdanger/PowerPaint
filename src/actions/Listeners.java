/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */
package actions;

import gui.DrawPanel;
import gui.PowerPaintGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class creates listeners and defines their behavior for all of the necessary components
 * of the PowerPaint program.
 *
 * @author Tanner Brown
 * @version 15 Nov 2017
 */
public class Listeners extends AbstractAction
		implements ChangeListener, PropertyChangeListener {

	/**
	 * An ID for serialization.
	 */
	private static final long serialVersionUID = -9218726655392777259L;

	/**
	 * A constant used in the substring.
	 */
	private static final int SUBSTRING_0 = 0;
	/**
	 * A constant used in the substring.
	 */
	private static final int SUBSTRING_3 = 3;

	/**
	 * The drawing panel object passed to this class for use with the listeners.
	 */
	private DrawPanel myPanel;
	/**
	 * A color that is used with a left click.
	 */
	private Color myPrimaryColor;
	/**
	 * A color that is used with a right click.
	 */
	private Color mySecondaryColor;
	/**
	 * A JComponent that the program modifies through events.
	 */
	private JComponent myComponent;


	/**
	 * An empty constructor for listeners that don't need to pass components.
	 */
	public Listeners() {
		super();
	}

	/**
	 * A constructor that accepts the DrawPanel and a JComponent.
	 *
	 * @param thePanel     A DrawPanel object being passed to be modified.
	 * @param theComponent A component being passed to be modified.
	 */
	public Listeners(final DrawPanel thePanel, final JComponent theComponent) {
		super();
		myPanel = thePanel;
		myComponent = theComponent;
	}

	/**
	 * A constructor that just accepts a JComponent.
	 *
	 * @param theComponent the component to be modified.
	 */
	public Listeners(final JComponent theComponent) {
		super();
		myComponent = theComponent;
	}

	/**
	 * This method gets called when a bound property is changed.
	 *
	 * @param theEvent A PropertyChangeEvent object describing the event source
	 *                 and the property that has changed.
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent theEvent) {
		if ("clear".equals(theEvent.getPropertyName())) {
			myComponent.setEnabled((Boolean) theEvent.getNewValue());
		}
	}


	@Override
	public void stateChanged(final ChangeEvent theEvent) {
		final String sourceName = theEvent.getSource().getClass().getSimpleName();


		if ("JSlider".equals(sourceName)) {
			final JSlider slider = (JSlider) myComponent;
			final int value = slider.getValue();
			myPanel.setThickness(value);
		}
	}


	/**
	 * Invoked when an action occurs.
	 *
	 * @param theEvent the event that causes an action.
	 */
	@Override
	public void actionPerformed(final ActionEvent theEvent) {

		String actionCommand = theEvent.getActionCommand();
	    /*
         * Created substring to shorten the action command name. I don't
         * know how to do this other than to create a "magic number".
         */
		actionCommand = actionCommand.substring(SUBSTRING_0, SUBSTRING_3);


		//About action listener
		if ("Abo".equals(actionCommand)) {
			JOptionPane.showMessageDialog(null,
					"Tanner Brown \nAutumn 2017\nTCSS 305 Assignment 5",
					"About", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("./images/realCharlesBryan64.gif"));
		}

		//Primary Color action
		if ("Pri".equals(actionCommand)) {
			myPrimaryColor = JColorChooser.showDialog(null, "Choose a new Primary Color",
					myPrimaryColor);
			PowerPaintGUI.setPrimaryIconColor(myPrimaryColor);
			PowerPaintGUI.setPrimaryColor(myPrimaryColor);
		}

		//Secondary Color action
		if ("Sec".equals(actionCommand)) {
			mySecondaryColor = JColorChooser.showDialog(null, "Choose a new Secondary Color",
					myPrimaryColor);
			PowerPaintGUI.setSecondaryIconColor(mySecondaryColor);
			PowerPaintGUI.setSecondaryColor(mySecondaryColor);
		}
		//Clear action
		if ("Cle".equals(actionCommand)) {
			final DrawPanel tempPanel = (DrawPanel) myComponent;
			tempPanel.clearCanvas();
		}
	}


}
