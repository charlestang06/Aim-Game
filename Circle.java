import java.awt.*;
import javax.swing.*;

public class Circle extends JComponent {
	public int radius;
	public int xCenter;
	public int yCenter;

	/**
	 * @param radius of the circle
	 * @param x      coordinate of the center
	 * @param y      coordinate of the center
	 *               constructs a cricle of radius r, and cent
	 *               */
	 
	public Circle(int radius, int x, int y) {
		this.radius = radius;
		this.xCenter = x;
		this.yCenter = y;
	}

	public int getRadius() {
		return radius;
	}

	public int getXCenter() {
		return xCenter;
	}

	public int getYCenter() {
		return yCenter;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(xCenter, yCenter, radius, radius);
		g2d.setColor(Color.BLUE);
		g2d.fillOval(xCenter, yCenter, radius, radius);
	}
}