import java.awt.*;
import javax.swing.*;

public class Circle extends JComponent {
	public int diameter;
	public int x;
	public int y;

	/**
	 * @param diameter of the circle
	 * @param x      coordinate of the center
	 * @param y      coordinate of the center constructs a circle of radius r, and
	 *               cent
	 */

	public Circle(int d, int x, int y) {
		this.diameter = d;
		this.x = x;
		this.y = y;
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
	public int getXCenter() {
		return x;
	}

	/**
	 * @return the y-center of the circle
	 */
	public int getYCenter() {
		return y;
	}

	/**
	 * Overrides the paintComponent method Draws a circle of diameter d from top-left point (x, y)
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(x, y, diameter, diameter);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(x, y, diameter, diameter);
	}
}