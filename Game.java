import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Game implements Runnable {
	private int highest_score;
	private int score;
	private int curr_score;
	private int seconds;
	private boolean status;
	private JFrame startFrame;
	private JFrame mainframe;
	private Circle circle;

	/**
	 * Constructor for game
	 * initializes the startscreen, mainframe, score counter, seconds past, and the
	 * game status
	 */
	public Game(int h) {
		mainframe = new JFrame();
		highest_score = h;
		curr_score = 0;
		seconds = 0;
		status = true;
	}

	/**
	 * Initializes the starter screen for the game
	 */
	public void starterScreen() {
		startFrame = new JFrame();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setTitle("Aim Game - Start Screen");
		startFrame.setSize(500, 500);

		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Welcome to the Aim Game."));
		panel.add(new JLabel("Click circles to gain score!"));
		panel.add(new JLabel("Make sure to click them quickly!"));
		panel.add(new JLabel("Highest Score: " + highest_score));

		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {

					Game newGame = new Game(highest_score);
					Thread gamethread = new Thread(newGame);
					gamethread.start();
				}
			}
		});
		panel.add(play);

		startFrame.add(panel);
		startFrame.setVisible(true);
		startFrame.setResizable(false);

	}

	public void endScreen() {
		mainframe = new JFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setTitle("Aim Game - End Screen");
		mainframe.setSize(500, 500);
		// todo buttons + layout for the endscreen
		mainframe.setVisible(true);
		mainframe.setResizable(false);
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
	 * @param seconds to sleep for
	 *                Sleeps the program for however many seconds for the user to
	 *                click the circle
	 */
	public void wait(int seconds) {
		try {
			for (int i = 0; i < seconds; i++) {
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void play() {
		int radius = 50;
		score = 0;
		status = true;
		while (status) {
			status = false;

			int randX = (int) (Math.random() * (500 - 2 * radius));
			int randY = (int) (Math.random() * (500 - 2 * radius));
			int centerX = randX + radius;
			int centerY = randY + radius;
			Circle c = new Circle(radius, randX, randY);

			// Mouse Listener
			JPanel panel = new JPanel();
			panel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					// debug
					if (Math.abs(centerX - x) * Math.abs(centerX - x)
							+ Math.abs(centerY - y) * Math.abs(centerY - y) <= radius * radius) {
						score++;
						status = true;
					}

				}
			});
			mainframe.add(panel);
			mainframe.getContentPane().add(c);
			mainframe.setVisible(true);
			wait(2);

			mainframe.getContentPane().remove(c);
			mainframe.revalidate();
			mainframe.repaint();
		}
		highest_score = Math.max(score, highest_score);
		curr_score = score;
		System.out.println(highest_score);
	}
}