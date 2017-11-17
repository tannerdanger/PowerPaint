package gui;

import tools.*;
import tools.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PowerPaintGUI extends JFrame {

	//	ToolBar myToolBar;
	JToolBar myToolBar;

	TopMenu myTopMenu;

	DrawPanel myDrawPanel;

	//Tool myTool;

	private int myThickness;

	private Color myPrimaryColor;

	private Color mySecondaryColor;

	private ColorIcon myPrimaryColorIcon;

	private ColorIcon mySecondaryColorIcon;

	private static final Color UW_PURPLE = new Color(51, 0, 111);
	private static final Color UW_GOLD = new Color(232, 211, 162);

	private JMenuItem myClearButon;


	private List<ToolActions> myToolActions;

	protected PowerPaintGUI(){
		super("Assignment 5");
	}

	protected void setup(){

		myPrimaryColorIcon = new ColorIcon(UW_PURPLE);
		mySecondaryColorIcon = new ColorIcon(UW_GOLD);
		myDrawPanel = new DrawPanel();

		setupToolActions();

		setupToolBar();

		setupMenuBar();

		setupDisplay();


		//Listens for property change to enable or disable the clear button
		myDrawPanel.addPropertyChangeListener(e -> {
			if("clear".equals(e.getPropertyName())) {
				myClearButon.setEnabled((Boolean) e.getNewValue());
			}
		});
	}


	private void setupDisplay() {




		//Create the icon
		ImageIcon frameIcon = new ImageIcon("./images/realCharlesBryan32.gif");
		final Image image = frameIcon.getImage();

		//setup Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(image);
		setJMenuBar(myTopMenu);
		add(myDrawPanel, BorderLayout.CENTER);
		add(myToolBar, BorderLayout.SOUTH);
		pack();
		setVisible(true);




	}

	private void setupToolActions() {
		myToolActions = new ArrayList<ToolActions>();

		myToolActions.add(new ToolActions("Pencil", new ImageIcon("./images/pencil.gif"), new Pencil()));

		myToolActions.add(new ToolActions("Line", new ImageIcon("./images/line.gif"), new Line()));

		myToolActions.add(new ToolActions("Rectangle", new ImageIcon("./images/rectangle.gif"), new Rectangle()));

		myToolActions.add(new ToolActions("Ellipse", new ImageIcon("./images/ellipse.gif"), new Ellipse()));

		myToolActions.add(new ToolActions("Eraser", new ImageIcon("./images/eraser.gif"), new Eraser()));



	}

	private void setupToolBar() {
		myToolBar = new JToolBar();


		final ButtonGroup btngrp = new ButtonGroup();
		for(final ToolActions ta : myToolActions){
			final JToggleButton tempToggle = new JToggleButton(ta);
			btngrp.add(tempToggle);
			//myToolBar.add(tempToggle);
			if("Line".equals(tempToggle.getText())){

			}else{

			}
			myToolBar.add(tempToggle);
		}
	}

	private void setupMenuBar() {
		myTopMenu = new TopMenu();

		//Setup Slider
		JSlider slider = myTopMenu.getSlider();

		//Todo: Ensure value is within range.
		slider.addChangeListener( e -> {
			myThickness = slider.getValue();
			myDrawPanel.setThickness(myThickness);
		});

		final ButtonGroup btngrp = new ButtonGroup();

		for (final ToolActions ta : myToolActions) {
			final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(ta);
			btngrp.add(btn);
			myTopMenu.addTools(btn);
		}

		//Create color buttons
		JMenuItem primaryColorButton = new JMenuItem("Primary Color...", myPrimaryColorIcon);
		primaryColorButton.setMnemonic(KeyEvent.VK_P);
		JMenuItem secondColorButton = new JMenuItem("Secondary Color...", mySecondaryColorIcon);
		secondColorButton.setMnemonic(KeyEvent.VK_S);

		//Create clear button
		myClearButon = new JMenuItem("Clear");
		myClearButon.setEnabled(false);
		myClearButon.setMnemonic(KeyEvent.VK_C);

		myTopMenu.addOptions(primaryColorButton);
		myTopMenu.addOptions(secondColorButton);
		myTopMenu.addOptions(new JSeparator());
		myTopMenu.addOptions(myClearButon);
		//clearButon.setEnabled(true);


		primaryColorButton.addActionListener(e -> {

			myPrimaryColor = JColorChooser.showDialog(null, "Choose a new Color", myPrimaryColor);
			myPrimaryColorIcon.setColor(myPrimaryColor);
			myDrawPanel.setMyPrimaryColor(myPrimaryColor);
		});

		secondColorButton.addActionListener(e -> {

			mySecondaryColor = JColorChooser.showDialog(null, "Choose a new Color", myPrimaryColor);
			mySecondaryColorIcon.setColor(mySecondaryColor);
			myDrawPanel.setMySecondaryColor(mySecondaryColor);
		});


		//clearButon.addPropertyChangeListener(PropertyChangeListener listener);

		myClearButon.addActionListener(e -> {
			myDrawPanel.clear();
		});

	}


	private class ToolActions extends AbstractAction{

		private final Tool myTool;

		ToolActions(final String theName, final Icon theIcon, final Tool theTool) {

			super(theName);

			//put small icon into the action
			putValue(Action.SMALL_ICON, theIcon);

			//Assign larger icon
			final ImageIcon icon = (ImageIcon) theIcon;
			final Image largeImage = icon.getImage().getScaledInstance(15, -1, Image.SCALE_SMOOTH);
			final ImageIcon largeIcon = new ImageIcon(largeImage);
			putValue(Action.LARGE_ICON_KEY, largeIcon);

			//Make the mnemonic key the first char in the name
			if ("Eraser".equals(theName)) {
				putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar('a'));
			} else{
				putValue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
			}
			//Create tooltip
			putValue(Action.SHORT_DESCRIPTION, "A " + theName + " Tool ");

			// coordinate button selection
			//putValue(Action.SELECTED_KEY, true);
			if("Line".equals(theName)){
				putValue(Action.SELECTED_KEY, true);
			}else{
				putValue(Action.SELECTED_KEY, false);
			}

			myTool = theTool;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			myDrawPanel.setTool(myTool);
		}

	}


}
