import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Grid extends JPanel implements ActionListener{
	private JPanel gridHolder = new JPanel();
	private int[] grid;
	public static final Grid game = new Grid();
	private int rowSize;
	private int columnSize;
	private int[] answer;
	private JButton[] answerButtons;
	private int[] input;
	private JButton[] inputButtons;
	private Options optionPane;
	
	public Grid() {
		super();
	}
	
	// Initializes the board that the player will edit
	public void initialize() {
		this.removeAll();
		this.setLayout(new GridLayout(rowSize, columnSize));
		buildBlankGrid();
		this.revalidate();
		this.repaint();
	}

	// Updates the blank board whenever the player clicks a new size
	public void update(Options optionPane) {
		this.optionPane = optionPane;
		this.rowSize = optionPane.getRowSize();
		this.columnSize = optionPane.getColumnSize();
		initialize();
	}
	
	// Builds the answer grid and stores the answer
	public void startGame() {
		buildAnswer();
		buildAnswerGrid();
	}
	
	// Disables all buttons
	public void shutDown() {
		for(JButton button : inputButtons) {
			button.setEnabled(false);
		}
	}
	
	// Adds the buttons to the frame 
	private void buildBlankGrid() {
		inputButtons = new JButton[rowSize * columnSize];
		for(int i = 0; i < rowSize * columnSize; i++) {
			JButton temp = new JButton();
			temp.setBackground(Color.WHITE);
			temp.addActionListener(this);
			temp.setVisible(true);
			this.add(temp);
			inputButtons[i] = temp;
		}
	}
	
	
	// Clears the board then adds the pattern the player has to memorize to the board
	private void buildAnswerGrid() {
		this.removeAll();
		answerButtons = new JButton[rowSize * columnSize];
		for(int i = 0; i < rowSize * columnSize; i++) {
			JButton temp = new JButton();
			temp.addActionListener(this);
			temp.setEnabled(false);
			if(answer[i] == 0) {
				temp.setBackground(Color.WHITE);
			}
			else {
				temp.setBackground(Color.BLUE);
			}
			temp.setVisible(true);
			answerButtons[i] = temp;
			this.add(temp);
		}
		this.repaint();
	}
	
	// Randomly selects what spaces should be blue and white.
	private void buildAnswer() {
		Random chance = new Random();
		answer = new int[rowSize * columnSize];
		input = new int[answer.length];
		for(int i = 0; i < answer.length; i++) {
			if(chance.nextInt(11) < 7) {
				answer[i] = 0;
			}
			else {
				answer[i] = 1;
			}
		}
	}
	
	public void buildInput() {
		for(int i = 0; i < answerButtons.length; i++) {
			if(inputButtons[i].getBackground() == Color.WHITE) {
				input[i] = 0;
			}
			else {
				input[i] = 1;
			}
		}
	}
	
	public Grid getGrid() {
		return game;
	}
	public int[] getAnswer() {
		return answer;
	}

	public int[] getInput() {
		return input;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getBackground() == Color.WHITE) {
			((JButton) e.getSource()).setBackground(Color.BLUE);
		}
		else {
			((JButton) e.getSource()).setBackground(Color.WHITE);
		}
	}

	public void setOptionPane(Options optionPane) {
		this.optionPane = optionPane;
		this.rowSize = optionPane.getRowSize();
		this.columnSize = optionPane.getColumnSize();
	}

	

}
