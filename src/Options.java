import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Options extends JPanel implements ActionListener {
	private JButton threeButton = new JButton("3x3");
	private JButton fiveButton = new JButton("5x5");
	private JButton sevenButton = new JButton("7x7");
	private JButton customButton = new JButton("Custom:");
	private JTextField row = new JTextField("Row");
	private JTextField column = new JTextField("Column");
	int rowSize = 3;
	int columnSize = 3;
	private Grid grid;
	
	
	public Options() {
		super(new GridLayout(4,0));
		threeButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sevenButton.addActionListener(this);
		customButton.addActionListener(this);
		add(threeButton);
		add(fiveButton);
		add(sevenButton);
		add(customButton);
		add(row);
		add(column);
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
			
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	
}
