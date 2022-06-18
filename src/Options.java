import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Options extends JPanel implements ActionListener {
	private JButton fiveButton = new JButton("5x5");
	private JButton sixButton = new JButton("6x6");
	private JButton sevenButton = new JButton("7x7");
	private JButton customButton = new JButton("Custom:");
	private JTextField row = new JTextField("Row");
	private JTextField column = new JTextField("Column");
	private JLabel timer = new JLabel("0");
	private JButton start = new JButton("Start");
	private int rowSize = 5;
	private int columnSize = 5;
	private int time = 15;
	private Grid grid;
	private Submit submission;
	private Timer showAnswer;
	private	Timer loseInput;
	private Timer answerDisplay;
	
	
	public Options() {
		drawOptions();
		showAnswer = new Timer(15000, allowInput);
		answerDisplay = new Timer(0, displayTimer);
		loseInput = new Timer(30000, ranOutOfTime);
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public int getColumnSize() {
		return columnSize;
	}
	ActionListener displayTimer = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(time <= 0) {
				time = 15;
			}
			timer = new JLabel(String.valueOf(time));
			drawOptions();
			revalidate();
			repaint();
			time--;
		}
	};
	ActionListener allowInput = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			grid.initialize();
			submission.gameStart();
		}
	};
	
	ActionListener ranOutOfTime = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			grid.buildInput();
			grid.shutDown();
			submission.disable();
			restart();
			JOptionPane.showMessageDialog(null, "You didn't hit submit in time :(", "Better Luck Next Time", JOptionPane.INFORMATION_MESSAGE);
		}
	};
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sixButton) {
			rowSize = 6;
			columnSize = 6;
		}
		else if(e.getSource() == fiveButton) {
			rowSize = 5;
			columnSize = 5;
		}
		else if (e.getSource() == sevenButton) {
			rowSize = 7;
			columnSize = 7;
		}
		else if (e.getSource() == customButton) {
			JFrame getSize = new JFrame("Enter Row and Column Sizes");
			getSize.setLayout(new GridLayout(3, 0));
			JButton submit = new JButton("Submit");
			getSize.add(new JLabel("Row: "));
			getSize.add(row);
			getSize.add(new JLabel("Column: "));
			getSize.add(column);
			getSize.setSize(200, 200);
			getSize.add(submit);
			submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
					rowSize = Integer.parseInt(row.getText());
					columnSize = Integer.parseInt(column.getText());
					grid.update();
					getSize.dispose();
					}
					catch (NumberFormatException n){
						JOptionPane.showMessageDialog(null,  "Please enter a number into the row and column text field.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			getSize.setVisible(true);
		}
		grid.update();
		if (e.getSource() == start) {
			changeEditable(false);
			grid.startGame();
			
			showAnswer.start();
			answerDisplay.start();
			loseInput.start();
			answerDisplay.setDelay(1000);
		}
	}
	
	private void changeEditable(boolean settingValue) {
		sixButton.setEnabled(settingValue);
		fiveButton.setEnabled(settingValue);
		sevenButton.setEnabled(settingValue);
		customButton.setEnabled(settingValue);
		row.setEditable(settingValue);
		column.setEditable(settingValue);
		start.setEnabled(settingValue);
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setSubmission(Submit submission) {
		this.submission = submission;
	}
	
	public void restart() {
		changeEditable(true);
		showAnswer.stop();
		loseInput.stop();
		answerDisplay.stop();
		time = 15;
		
	}
	
	private void drawOptions() {
		removeAll();
		setLayout(new GridLayout(3,0));
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		customButton.addActionListener(this);
		start.addActionListener(this);
		add(fiveButton);
		add(sixButton);
		add(sevenButton);
		add(customButton);
		add(start);
		add(timer);
		setVisible(true);
	}
}
