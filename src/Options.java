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
	private JButton threeButton = new JButton("3x3");
	private JButton fiveButton = new JButton("5x5");
	private JButton sevenButton = new JButton("7x7");
	private JButton customButton = new JButton("Custom:");
	private JTextField row = new JTextField("Row");
	private JTextField column = new JTextField("Column");
	private JButton start = new JButton("Start");
	int rowSize = 3;
	int columnSize = 3;
	private Grid grid;
	
	
	public Options() {
		super(new GridLayout(4,1));
		threeButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sevenButton.addActionListener(this);
		customButton.addActionListener(this);
		start.addActionListener(this);
		add(threeButton);
		add(fiveButton);
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
		if(e.getSource() == threeButton) {
			rowSize = 3;
			columnSize = 3;
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
				exception.printStackTrace();
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
				}
			}
			Timer showAnswer = new Timer();
			TimerTask inputTime = new allowInput();
			showAnswer.schedule(inputTime, 15000);
			
			class ranOutOfTime extends TimerTask{
				@Override
				public void run() {
					grid.shutDown();
					JOptionPane.showMessageDialog(null, "You didn't hit submit in time :(", "Better Luck Next Time", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			Timer loseInput = new Timer();
			TimerTask lose = new ranOutOfTime();
			loseInput.schedule(lose, 30000);
			
		}
	}
	
	private void changeEditable(boolean settingValue) {
		threeButton.setEnabled(settingValue);
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

	
}
