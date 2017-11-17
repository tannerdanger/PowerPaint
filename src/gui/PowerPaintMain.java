/*
 * TCSS 305 - Assignment 5 PowerPaint
 * Fall 2017
 */

package gui;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class runs the PowerPaint program and launches the GUI.
 *
 * @author Tanner Brown
 * @version 8 Nov 2017
 *
 */
public final class PowerPaintMain {

	/**
	 * private constructor to prevent unwanted instantiation.
	 */
	private PowerPaintMain() {
		System.out.println("What black magic did you use to get here??");
		throw new IllegalStateException();
	}

	/**
	 * Method that sets the look and feel and starts the GUI.
	 * @param theArgs external arguments
	 */
	public static void main(final String[] theArgs) {

		//Set look at feel, shamelessly stolen from provided code.
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (final UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (final IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (final InstantiationException ex) {
			ex.printStackTrace();
		} catch (final ClassNotFoundException ex) {
			ex.printStackTrace();
		}
        /* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				new PowerPaintGUI().setup();
			}
		});
	}
}
