
/**
 * @author Charles Tang
 * ProgressionGame.java creates a level-based Aim Game which 
 * allows the user to play levels of increasing difficulty */

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
import javax.swing.Timer;

public class ProgressionGame implements Runnable, Game {

	/**
	 * Instance variables
	 */
	private int score;
	private boolean status;
	private double timeLeft;
	private int level;

	private JFrame startFrame;
	private JFrame mainframe;
	private JFrame endFrame;
	private JFrame failFrame;
	private Timer timer;
	private Dimension dims;
	private String cursor_string;

	private double speed;
	private double diam;

	/**
	 * Constructor for game initializes the start-screen, mainframe, score,
	 * diameter counter, seconds past, and the game status
	 */
	public ProgressionGame(double s, int l, Dimension d, String cursor, double dim) {
		status = true;
		speed = s;
		diam = dim;
		timer = null;
		level = l;
		dims = d;
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
		startFrame.setTitle("Start Screen - Level " + level);
		startFrame.setSize(dims);

		// Initialize layout
		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Welcome to the Progression Aim Game."));
		panel.add(new JLabel("Click circles to gain score!"));
		panel.add(new JLabel("Make sure to click them quickly!"));
		panel.add(new JLabel("Once you complete a level, you will move onto the next!"));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));
		panel.add(new JLabel(" "));
		// Play button
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {
					startFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(speed, level, dims, cursor_string, diam);
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
	 * Initializes the end-screen after successful completion
	 */
	@Override
	public void endScreen() {
		endFrame = new JFrame();
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setTitle("Passed Level " + level);
		endFrame.setSize(dims);

		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("You passed Level " + level + "!"));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));
		panel.add(new JLabel(" "));

		JButton play = new JButton("Continue");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Continue")) {
					endFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(speed + 1, level + 1, dims, cursor_string,
							diam - 0.2);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

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
	 * Initializes the fail-screen after failure
	 */
	public void failScreen() {
		failFrame = new JFrame();
		failFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		failFrame.setTitle("Fail Screen - Level " + level);
		failFrame.setSize(dims);

		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("You failed Level " + level + "!"));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));
		panel.add(new JLabel(" "));

		// Try Again button
		JButton play = new JButton("Try Again");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Try Again")) {
					failFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(speed, level, dims, cursor_string, diam);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Exit")) {
					failFrame.dispose();
				}
			}
		});
		exit.setPreferredSize(new Dimension(300, 50));
		panel.add(exit);

		failFrame.add(panel);
		failFrame.setLocationRelativeTo(null);
		failFrame.setVisible(true);
		failFrame.setResizable(false);
	}

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
		mainframe.setTitle("Progression Aim Game - Level " + level);
		mainframe.setSize(dims);
		if (cursor_string.equals("Gun Cursor")) {
			Toolkit tkit = Toolkit.getDefaultToolkit();
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
		jlabel2.setText("Score: " + score + "/15");
		mainframe.getContentPane().add(jlabel2);
		mainframe.revalidate();
		mainframe.repaint();

		// Level Label
		JLabel jlabel3 = new JLabel();
		jlabel3.setVerticalAlignment(JLabel.TOP);
		jlabel3.setHorizontalAlignment(JLabel.CENTER);
		jlabel3.setText("Level " + level);
		mainframe.getContentPane().add(jlabel3);
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
						jlabel2.setText("Score: " + score + "/15");
						if (score < 15) {
							status = true;
						}
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
		timer.stop();
		mainframe.getContentPane().remove(jlabel1);
		mainframe.setVisible(false);
		mainframe.dispose();
		if (score >= 15) {
			endScreen();
		} else {
			failScreen();
		}
	}
}