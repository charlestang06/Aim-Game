import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.*;
import java.lang.Thread;

public class Game implements Runnable {

	/**
	 * Instance variables
	 */
	private int highest_score;
	private int score;
	private int seconds;
	private boolean status;

	private JFrame startFrame;
	private JFrame mainframe;
	private JFrame endFrame;

	private double speed;
	private int rad;

	/**
	 * Constructor for game initializes the startscreen, mainframe, score counter,
	 * seconds past, and the game status
	 */
	public Game(int h, double s, int r) {
		highest_score = h;
		seconds = 0;
		status = true;
		speed = s;
		rad = r;
	}

	/**
	 * Initializes the starter screen for the game
	 */
	public void starterScreen() {
		// Initialize frame
		startFrame = new JFrame();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setTitle("Aim Game - Start Screen");
		startFrame.setSize(500, 500);

		// Initialize layout
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

		// speed slider
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

		// circle size slider
		JLabel headerLabel1 = new JLabel("Circle Size Slider", JLabel.CENTER);
		JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(2);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setValue((int) rad);
		slider1.setLabelTable(slider1.createStandardLabels(1));
		slider1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				rad = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel1);
		panel.add(slider1);
		panel.add(new JLabel(" "));

		// Play button
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {
					startFrame.dispose();
					Game newGame = new Game(highest_score, speed, rad);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

		startFrame.add(panel);
		startFrame.setVisible(true);
		startFrame.setResizable(false);

	}

	/**
	 * Initializes the endscreen after failure
	 */
	public void endScreen() {
		endFrame = new JFrame();
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.setTitle("Aim Game - End Screen");
		endFrame.setSize(500, 500);

		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Your score was " + score));
		panel.add(new JLabel("Highest Score: " + highest_score));

		// speed slider
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

		// circle size slider
		JLabel headerLabel1 = new JLabel("Circle Size Slider", JLabel.CENTER);
		JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(2);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setValue((int) rad);
		slider1.setLabelTable(slider1.createStandardLabels(1));
		slider1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				rad = ((JSlider) e.getSource()).getValue();
			}
		});
		panel.add(new JLabel(" "));
		panel.add(headerLabel1);
		panel.add(slider1);
		panel.add(new JLabel(" "));

		JButton play = new JButton("Play Again");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play Again")) {
					endFrame.dispose();
					Game newGame = new Game(highest_score, speed, rad);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		play.setPreferredSize(new Dimension(300, 50));
		panel.add(play);

		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
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
		endFrame.setVisible(true);
		endFrame.setResizable(false);
	}

	public void run() {
		createWindow();
		play();
	}

	/**
	 * Initializes the window to play the game
	 */
	public void createWindow() {
		mainframe = new JFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setTitle("Aim Game - Game");
		mainframe.setSize(500, 500);
		mainframe.setVisible(true);
		mainframe.setResizable(false);
	}

	/**
	 * @param tenths of seconds to sleep for Sleeps the program/waits
	 */
	public void wait(int tenth_of_seconds) {
		try {
			for (int i = 0; i < tenth_of_seconds; i++) {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * The main function of the program, initializes the game and creates circle
	 * objects through a while loop, checking for a mouse click each time
	 */
	public void play() {
		int radius = (int) (50.0 / 3 * rad);
		score = 0;
		status = true;
		while (status) {
			status = false;

			int randX = (int) (Math.random() * (500 - 2 * radius));
			int randY = (int) (Math.random() * (500 - 2 * radius));
			double centerX = randX + radius / Math.sqrt(2);
			double centerY = randY + radius / Math.sqrt(2);
			Circle c = new Circle(radius, randX, randY);

			// Mouse Listener
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					if (Math.abs(centerX - x) * Math.abs(centerX - x)
							+ Math.abs(centerY - y) * Math.abs(centerY - y) <= radius * radius) {
						score++;
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
		System.out.println(highest_score);
		mainframe.setVisible(false);
		mainframe.dispose();
		endScreen();

	}
}