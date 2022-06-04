import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame{
	private Grid grid = new Grid();
	private Options options = new Options();
	private Submit submit;
	
	public Game() {
		grid.setOptionPane(options);
		options.setGrid(grid);
		grid.initialize();
		submit = new Submit();
		options.setSubmission(submit);
		submit.setGrid(grid);
		submit.setOptions(options);
		this.add(options, BorderLayout.EAST);
		this.add(submit, BorderLayout.SOUTH);
		this.add(grid);
		this.setSize(650, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, "Welcome to the Memory Game. Once you click start, you will have 15 seconds to memorize the pattern."
				+ " You will then have 15 seconds to recreate the pattern. Good luck and have fun!", "Welcome Welcome!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}
}
