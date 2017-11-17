package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TopMenu extends JMenuBar {

	//thickness menu constants
	private static final int MIN_THICK = 0;
	private static final int MAX_THICK = 20;
	private static final int MAJOR_SPACE = 5;
	private static final int MINOR_SPACE = 1;
	private static final int THICK_DEFAULT = 10;
//	private static final Color UW_PURPLE = new Color(51, 0, 111);
//	private static final Color UW_GOLD = new Color(232, 211, 162);

	private JMenu myOptionsMenu;
	private JMenu myToolsMenu;
	private JMenu myHelpMenu;
	private JSlider mySlider;
	
	private int myThickness;

	private Color myPrimaryColor;

	private Color mySecondaryColor;

	private ColorIcon myPrimaryColorIcon;

	private ColorIcon mySecondaryColorIcon;


	protected TopMenu (){
		super();
		build();
	}

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

	private void buildAbout() {
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setMnemonic(KeyEvent.VK_A);
		myHelpMenu.add(aboutItem);

		aboutItem.addActionListener(e -> {
			JOptionPane.showMessageDialog(null,
					"Tanner Brown \nAutumn 2017\nTCSS 305 Assignment 5",
					"About", JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("./images/realCharlesBryan64.gif"));
		});
	}

	private void buildSlider() {

		JMenu thicknessMenu = new JMenu("Thickness");
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

	public int getMyThickness() {
		return myThickness;
	}

	public void setMyThickness(int myThickness) {
		myThickness = myThickness;
	}
	
	protected void addTools(JRadioButtonMenuItem theRadioButton){

		myToolsMenu.add(theRadioButton);
	}

	protected void addOptions(JComponent theMenuItem){

		myOptionsMenu.add(theMenuItem);

	}

	protected JSlider getSlider(){
		return mySlider;
	}
	
}
