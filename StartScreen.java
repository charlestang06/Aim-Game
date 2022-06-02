
/**
 * @author Charles Tang
 * StartScreen.java creates the starter frame that allows 
 * the user to choose between the levels 
 * or customizable Aim Game
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen {
	/**
	 * Instance variables
	 */
	private JFrame startFrame;
	private String cursor_string;
	private Dimension dims;

	/**
	 * Constructor for StartScreen class
	 * 
	 * Initializes startFrame to a defaultJFrame
	 */
	public StartScreen() {
		startFrame = new JFrame();
		dims = new Dimension(500, 500);
		cursor_string = "Gun Cursor";
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
		startFrame.setSize(dims);

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
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Custom")) {
					startFrame.dispose();
					CustomGame g = new CustomGame(0, 5, 3, dims, cursor_string);
					g.starterScreen();
				}
			}
		});
		custom.setPreferredSize(new Dimension(300, 50));
		panel.add(custom);

		// Progression Levels Aim Game Button
		JButton prog = new JButton("Levels");
		prog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (cmd.equals("Levels")) {
					ProgressionGame g = new ProgressionGame(2, 1, dims, cursor_string, 4);
					startFrame.dispose();
					g.starterScreen();
				}
			}
		});
		prog.setPreferredSize(new Dimension(300, 50));
		panel.add(prog);

		panel.add(new JLabel(" "));

		// Custom cursor drop down menu
		JComboBox cursor = new JComboBox();
		cursor.addItem("Gun Cursor");
		cursor.addItem("Normal Cursor");
		cursor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cursor_string = ((JComboBox) e.getSource()).getSelectedItem().toString();
			}
		});

		panel.add(cursor);

		startFrame.add(panel);
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
		startFrame.setResizable(true);
	}

}
