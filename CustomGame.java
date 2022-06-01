
/**
 * @author Charles Tang
 * CustomGame.java creates a Customizable Aim Game for the user to play
 * Creates the Java Swing GUI and playable interface 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomGame implements Runnable, Game {

	/**
	 * Instance variables
	 */
	private int highest_score;
	private int score;
	private boolean status;
	private double timeLeft;

	private JFrame startFrame;
	private JFrame mainframe;
	private JFrame endFrame;
	private Timer timer;
	private Dimension dims;
	private String cursor_string;

	private double speed;
	private int diam;

	/**
	 * Constructor for game initializes the start-screen, mainframe, score,
	 * diameter counter, seconds past, and the game status
	 */
	public CustomGame(int h, double s, int d, Dimension dim, String cursor) {
		highest_score = h;
		status = true;
		speed = s;
		diam = d;
		timer = null;
		dims = dim;
		cursor_string = cursor;
	}

	/**
	 * Initializes the starter screen for the game
	 */
	@Override
	public void starterScreen() {
		// Initialize frame
		startFrame = new JFrame();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setTitle("Aim Game - Start Screen");
		startFrame.setSize(dims);

		// Initialize Layout
		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Welcome to the Aim Game."));
		panel.add(new JLabel("Click circles to gain score!"));
		panel.add(new JLabel("Make sure to click them quickly!"));
		panel.add(new JLabel("Highest Score: " + highest_score));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));

		// Speed Slider
		JLabel headerLabel = new JLabel("Speed Slider", JLabel.CENTER);
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue((int) speed);
		slider.setLabelTable(slider.createStandardLabels(1));
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speed = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel);
		panel.add(slider);

		// Circle Size Slider
		JLabel headerLabel1 = new JLabel("Circle Size Slider", JLabel.CENTER);
		JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(2);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setValue(diam);
		slider1.setLabelTable(slider1.createStandardLabels(1));
		slider1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				diam = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel1);
		panel.add(slider1);
		panel.add(new JLabel(" "));

		// Play Button
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {
					startFrame.dispose();
					CustomGame newGame = new CustomGame(highest_score, speed, diam, dims, cursor_string);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

		startFrame.add(panel);
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
		startFrame.setResizable(false);

	}

	/**
	 * Initializes the end-screen after failure
	 */
	@Override
	public void endScreen() {
		endFrame = new JFrame();
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setTitle("Aim Game - End Screen");
		endFrame.setSize(dims);

		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Your score was " + score));
		panel.add(new JLabel("Highest Score: " + highest_score));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));

		// Speed Slider
		JLabel headerLabel = new JLabel("Speed Slider", JLabel.CENTER);
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue((int) speed);
		slider.setLabelTable(slider.createStandardLabels(1));
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				speed = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel);
		panel.add(slider);

		// Circle Size Slider
		JLabel headerLabel1 = new JLabel("Circle Size Slider", JLabel.CENTER);
		JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(2);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setValue(diam);
		slider1.setLabelTable(slider1.createStandardLabels(1));
		slider1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				diam = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel1);
		panel.add(slider1);
		panel.add(new JLabel(" "));

		// Play Again Button
		JButton play = new JButton("Play Again");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play Again")) {
					endFrame.dispose();
					CustomGame newGame = new CustomGame(highest_score, speed, diam, dims, cursor_string);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

		// Exit Button
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Exit")) {
					endFrame.dispose();
				}
			}
		});
		exit.setPreferredSize(new Dimension(300, 50));
		panel.add(exit);

		endFrame.add(panel);
		endFrame.setLocationRelativeTo(null);
		endFrame.setVisible(true);
		endFrame.setResizable(false);
	}

	/**
	 * Method implemented for Runnable interface Method is run when
	 * Thread.start() is called when instantiating a new game
	 */
	@Override
	public void run() {
		createWindow();
		play();
	}

	/**
	 * Initializes the window to play the game
	 */
	@Override
	public void createWindow() {
		mainframe = new JFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setTitle("Aim Game - Game");
		mainframe.setSize(dims);
		Toolkit tkit = Toolkit.getDefaultToolkit();
		if (cursor_string.equals("Gun Cursor")) {
			URL url = this.getClass().getResource("R_50x50.png");
			Image im1 = tkit.getImage(url);
			mainframe.setCursor(tkit.createCustomCursor(im1, new Point(mainframe.getX(), mainframe.getY()), "cursor"));
		}
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		mainframe.setResizable(false);
	}

	/**
	 * The main function of the program, initializes the game and creates circle
	 * objects through a while loop, checking for a mouse click each time
	 */
	@Override
	public void play() {
		int diameter = (int) (50.0 / 3 * diam);
		status = true;

		// Seconds Label
		JLabel jlabel1 = new JLabel();
		timeLeft = 0;
		ActionListener counter = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLeft += 1.2;
				jlabel1.setText("Seconds: " + (int) (timeLeft / 10));
				if (timeLeft >= 1000) {
					timer.stop();
				}
			}
		};
		timer = new Timer(100, counter);
		timer.setInitialDelay(0);
		jlabel1.setVerticalAlignment(JLabel.TOP);
		jlabel1.setHorizontalAlignment(JLabel.LEFT);
		mainframe.getContentPane().add(jlabel1);
		timer.start();
		mainframe.revalidate();
		mainframe.repaint();

		// Score Label
		JLabel jlabel2 = new JLabel();
		score = 0;
		jlabel2.setVerticalAlignment(JLabel.TOP);
		jlabel2.setHorizontalAlignment(JLabel.RIGHT);
		jlabel2.setText("Score: " + score);
		mainframe.getContentPane().add(jlabel2);

		mainframe.revalidate();
		mainframe.repaint();

		while (status) {
			status = false;

			int randX = (int) (Math.random() * (dims.getWidth() - 2 * diameter));
			int randY = (int) (Math.random() * (dims.getHeight() - 2 * diameter));
			double radius = diameter / 2.0;
			double centerX = randX + radius / 2.0;
			double centerY = randY + radius / 2.0;
			CircleCreator c = new CircleCreator(diameter, randX, randY);

			// Mouse Listener
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					if (Math.pow(Math.abs(centerX - x), 2) + +Math.pow(Math.abs(centerY - y), 2) <= radius * radius
							+ 1) {
						score++;
						jlabel2.setText("Score: " + score);
						status = true;
						mainframe.getContentPane().remove(c);
						mainframe.revalidate();
						mainframe.repaint();
					}

				}
			});
			mainframe.getContentPane().add(c);
			mainframe.setVisible(true);
			wait((int) (50.0 / speed));

		}
		highest_score = Math.max(score, highest_score);
		timer.stop();
		mainframe.getContentPane().remove(jlabel1);
		mainframe.setVisible(false);
		mainframe.dispose();

		endScreen();

	}
}