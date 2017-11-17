package tools;

import java.awt.*;

public class Drawing {

	private final Shape myShape;

	private final int myThick;

	private final Color myColor;

	public Drawing(final int theThick, final Color theColor, final Shape theShape){

		myThick = theThick;

		myColor = theColor;

		myShape = theShape;

	}


	public Shape getMyShape() {
		return myShape;
	}

	public int getMyThick() {
		return myThick;
	}

	public Color getMyColor() {
		return myColor;
	}

}
