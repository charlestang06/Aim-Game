import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.*;
import java.lang.Thread;

public class ProgressionGame implements Runnable {

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

	private double speed;
	private int diam;

	/**
	 * Constructor for game initializes the start-screen, mainframe, score,
	 * diameter counter, seconds past, and the game status
	 */
	public ProgressionGame(double s, int l, Dimension d) {
		status = true;
		speed = s;
		diam = 3;
		timer = null;
		level = l;
		dims = d;
	}

	/**
	 * Initializes the starter screen for the game
	 */
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
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {
					startFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(speed, level, dims);
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
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Continue")) {
					endFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(speed + 1, level + 1, dims);
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

		JButton play = new JButton("Try Again");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Try Again")) {
					failFrame.dispose();
					ProgressionGame newGame = new ProgressionGame(2, 1, dims);
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
		mainframe.setTitle("Progression Aim Game - Level " + level);
		mainframe.setSize(dims);
		Toolkit tkit = Toolkit.getDefaultToolkit();
		ImageIcon icon = new ImageIcon("src\\R_50x50.png");
		Image im1 = icon.getImage();
		mainframe.setCursor(tkit.createCustomCursor(im1, new Point(mainframe.getX(), mainframe.getY()), "cursor"));
		mainframe.setLocationRelativeTo(null);
		mainframe.setVisible(true);
		mainframe.setResizable(false);
	}

	/**
	 * @param tenths
	 *            of seconds to sleep for Sleeps the program/waits
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
		int diameter = (int) (50.0 / 3 * diam);
		status = true;

		// Seconds Label
		JLabel jlabel1 = new JLabel();
		timeLeft = 0;
		ActionListener counter = new ActionListener() {
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