import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen {
	/**
	 * Instance variables
	 */
	private JFrame startFrame;

	/**
	 * Constructor for StartScreen class
	 * 
	 * Initializes startFrame to a defaultJFrame
	 */
	public StartScreen() {
		startFrame = new JFrame();
	}

	/**
	 * Create a window that allows the user to choose between the Progression or
	 * Custom Aim Game
	 */
	public void start() {
		// Initialize frame
		startFrame = new JFrame();
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setTitle("Start Screen");
		startFrame.setSize(500, 500);

		// Initialize layout
		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout();
		layout.setHgap(1000);
		layout.setVgap(10);
		panel.setLayout(layout);
		panel.add(new JLabel("Welcome to the Aim Game."));
		panel.add(new JLabel("Choose a version of the game to play."));
		panel.setBackground(new Color((float) 255 / 255, (float) 226 / 255, (float) 174 / 255));
		panel.add(new JLabel(" "));

		// Custom button
		JButton custom = new JButton("Custom");
		custom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Custom")) {
					startFrame.dispose();
					Game g = new Game(0, 5, 3);
					g.starterScreen();
				}
			}
		});
		custom.setPreferredSize(new Dimension(300, 50));
		panel.add(custom);

		JButton prog = new JButton("Levels");
		prog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Levels")) {
					startFrame.dispose();
					ProgressionGame g = new ProgressionGame(2, 1);
					g.starterScreen();
				}
			}
		});
		prog.setPreferredSize(new Dimension(300, 50));
		panel.add(prog);

		startFrame.add(panel);
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
		startFrame.setResizable(false);
	}

}