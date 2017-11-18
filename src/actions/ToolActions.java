/*
 * TCSS 305 - PowerPaint
 * Fall 2017
 */

package actions;

import gui.PowerPaintGUI;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


/**
 * This class defines all of the action behavior for all of the different tool classes.
 *
 * @author Tanner Brown
 * @version 17 Nov 2017
 */
public class ToolActions extends AbstractAction {

	/**
	 * A generated serialization id.
	 */
	private static final long serialVersionUID = 252517443792511725L;

	/**
	 * A tool object.
	 */
	private final Tool myTool;

	/**
	 * Constructor that build the tool.
	 *
	 * @param theName the name of the tool.
	 * @param theIcon the icon for the tool button.
	 * @param theTool the tool being used.
	 */
	public ToolActions(final String theName, final Icon theIcon, final Tool theTool) {
		super(theName);

		//put small icon into the action
		putValue(Action.SMALL_ICON, theIcon);

		makeLargeIcon(theIcon);

		//Make the mnemonic key the first char in the name
		if ("Eraser".equals(theName)) {
			putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('a'));
		} else {
			putValue(Action.MNEMONIC_KEY,
					KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
		}

		//Create tooltip
		putValue(Action.SHORT_DESCRIPTION, "A " + theName + " Tool ");

		// coordinate button selection and ensure Line tool is selected by default.
		if ("Line".equals(theName)) {
			putValue(Action.SELECTED_KEY, true);
		} else {
			putValue(Action.SELECTED_KEY, false);
		}
		myTool = theTool;
	}

	/**
	 * A method that makes a larger icon that looks better than stretching a small icon.
	 *
	 * @param theIcon the small version of the icon.
	 * @return a larger icon.
	 */
	private ImageIcon makeLargeIcon(final Icon theIcon) {
		//Assign larger icon
		final ImageIcon icon = (ImageIcon) theIcon;
		final Image largeImage = icon.getImage().getScaledInstance(15, -1, Image.SCALE_SMOOTH);
		final ImageIcon largeIcon = new ImageIcon(largeImage);
		putValue(Action.LARGE_ICON_KEY, largeIcon);

		return largeIcon;
	}

	/**
	 * Invoked when an action occurs.
	 *
	 * @param theEvent the event that performed.
	 */
	@Override
	public void actionPerformed(final ActionEvent theEvent) {
		PowerPaintGUI.setTool(myTool);
	}
}
