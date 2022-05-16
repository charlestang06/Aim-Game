import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.*;
import java.lang.Thread;
import java.text.SimpleDateFormat;


public class Game implements Runnable {

	/**
	 * Instance variables
	 */
	private int highest_score;
	private int score;
	private int seconds;
	private boolean status;
	private double timeLeft;

	private JFrame startFrame;
	private JFrame mainframe;
	private JFrame endFrame;
	private Timer timer;

	private double speed;
	private int diam;

	/**
	 * Constructor for game initializes the startscreen, mainframe, score, diameter
	 * counter, seconds past, and the game status
	 */
	public Game(int h, double s, int d) {
		highest_score = h;
		seconds = 0;
		status = true;
		speed = s;
		diam = d;
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
		slider1.setValue((int) diam);
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

		// Play button
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play")) {
					startFrame.dispose();
					Game newGame = new Game(highest_score, speed, diam);
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
		slider1.setValue((int) diam);
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

		JButton play = new JButton("Play Again");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Play Again")) {
					endFrame.dispose();
					Game newGame = new Game(highest_score, speed, diam);
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
		Toolkit tkit=Toolkit.getDefaultToolkit();
		ImageIcon icon = new ImageIcon("src\\R_50x50.png");
		Image im1 = icon.getImage();
		mainframe.setCursor(tkit.createCustomCursor(im1 , new Point(mainframe.getX(), 
			      mainframe.getY()), "cursor"));
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
		score = 0;
		status = true;
		
		//Seconds Label
		JLabel jlabel1 = new JLabel();
		timeLeft = 0;
		ActionListener counter = new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	timeLeft += 1.2;
		        jlabel1.setText("Seconds: " + (int) (timeLeft / 10));
		        if(timeLeft >= 1000)
		        {
		            timer.stop();
		        }
		    }
		};
		timer = new Timer(100, counter);
		timer.setInitialDelay(0);
		jlabel1.setVerticalAlignment(JLabel.TOP);
		jlabel1.setHorizontalAlignment(JLabel.CENTER);
		
		// TODO: Fix bug where timer stops working when play again called

		mainframe.getContentPane().add(jlabel1);
		timer.start();
		
		while (status) {
			
			//TODO: implement score counter inside this loop 
		
			status = false;

			int randX = (int) (Math.random() * (500 - 2 * diameter));
			int randY = (int) (Math.random() * (500 - 2 * diameter));
			double radius = diameter / 2.0;
			double centerX = randX + radius / 2.0;
			double centerY = randY + radius / 2.0;
			Circle c = new Circle(diameter, randX, randY);

			// Mouse Listener
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int x = e.getX();
					int y = e.getY();
					if (Math.pow(Math.abs(centerX - x), 2) +
							+ Math.pow(Math.abs(centerY - y), 2) <= radius * radius + 1) {
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
		timer.stop();
		mainframe.getContentPane().remove(jlabel1);
		mainframe.setVisible(false);
		mainframe.dispose();
		
		endScreen();

	}
}