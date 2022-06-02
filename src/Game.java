import java.awt.BorderLayout;

import javax.swing.JFrame;

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
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}
}
