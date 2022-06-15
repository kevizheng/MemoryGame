import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Options extends JPanel implements ActionListener {
	private JButton fiveButton = new JButton("5x5");
	private JButton sixButton = new JButton("6x6");
	private JButton sevenButton = new JButton("7x7");
	private JButton customButton = new JButton("Custom:");
	private JTextField row = new JTextField("Row");
	private JTextField column = new JTextField("Column");
	private JButton start = new JButton("Start");
	int rowSize = 5;
	int columnSize = 5;
	private Grid grid;
	private Submit submission;
	private Timer showAnswer = new Timer();
	private	Timer loseInput = new Timer();
	
	
	public Options() {
		super(new GridLayout(4,0));
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		customButton.addActionListener(this);
		start.addActionListener(this);
		add(fiveButton);
		add(sixButton);
		add(sevenButton);
		add(customButton);
		add(row);
		add(column);
		add(start);
		setVisible(true);
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public int getColumnSize() {
		return columnSize;
	}
	
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
			try {
				rowSize = Integer.parseInt(row.getText());
				columnSize = Integer.parseInt(column.getText());
			}
			catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(null,  "Please enter a number into the row and column text field.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		grid.update(this);
		if (e.getSource() == start) {
			changeEditable(false);
			grid.startGame();
			class allowInput extends TimerTask{
				@Override
				public void run() {
					grid.initialize();
					JOptionPane.showMessageDialog(null, "BEGIN!", "This is to annoy you", JOptionPane.INFORMATION_MESSAGE);
					submission.gameStart();
				}
			}
			TimerTask inputTime = new allowInput();
			showAnswer.schedule(inputTime, 15000);
			
			class ranOutOfTime extends TimerTask{
				@Override
				public void run() {
					grid.shutDown();
					JOptionPane.showMessageDialog(null, "You didn't hit submit in time :(", "Better Luck Next Time", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			loseInput = new Timer();
			TimerTask lose = new ranOutOfTime();
			loseInput.schedule(lose, 30000);
			
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
		loseInput.cancel();
	}
}
