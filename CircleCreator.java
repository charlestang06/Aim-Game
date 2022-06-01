
/**
 * @author Charles Tang
 * Circle.java creates Circles for the user to click on 
 * during the game and paints them onto the canvas 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class CircleCreator extends JComponent {
	private static final long serialVersionUID = 8015978078280349192L;
	/**
	 * Instance variables
	 */
	public int diameter;
	public int x;
	public int y;
	private Color[] colors;
	private int rand;

	/**
	 * @param diameter
	 *            of the circle
	 * @param x
	 *            coordinate of the center
	 * @param y
	 *            coordinate of the center constructs a circle of radius r, and
	 *            cent
	 */

	public CircleCreator(int d, int x, int y) {
		this.diameter = d;
		this.x = x;
		this.y = y;
		colors = new Color[] { Color.MAGENTA, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
		rand = (int) (Math.random() * colors.length);
	}

	/**
	 * @return diameter of the circle
	 */
	public int getDiameter() {
		return diameter;
	}

	/**
	 * @return the x-center of the circle
	 */
	public int getXCoord() {
		return x;
	}

	/**
	 * @return the y-center of the circle
	 */
	public int getYCoord() {
		return y;
	}

	/**
	 * Overrides the paintComponent method Draws a circle of diameter d from
	 * top-left point (x, y)
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(x, y, diameter, diameter);
		g2d.setColor(colors[rand]);
		g2d.fillOval(x, y, diameter, diameter);
	}

	/**
	 * Override default toString method
	 */
	@Override
	public String toString() {
		return "Circle of radius " + (diameter / 2.0) + " located at point (" + (x + diameter / 2.0) + ", "
				+ (y + diameter / 2.0) + ")";
	}
}