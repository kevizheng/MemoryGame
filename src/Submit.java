import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Submit extends JPanel implements ActionListener{
	private JButton submit = new JButton("Submit");
	private JLabel score;
	private int[] answer;
	private int[] input;
	private int correct = 0;
	private Grid grid;
	private Options option;
	
	public Submit() {
		super();
		this.setLayout(new GridLayout(2, 0));
		submit.addActionListener(this);
		submit.setEnabled(false);
		this.add(submit);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			grid.buildInput();
			this.answer = grid.getAnswer();
			this.input = grid.getInput();
			for(int i = 0; i < answer.length; i++) {
				if(answer[i] == input[i]) {
					correct++;
				}
			}
			score = new JLabel("Your accuracy is: " + (correct * 1.0)/answer.length * 100 + "%");
			this.add(score);
			correct = 0;
			grid.shutDown();
			submit.setEnabled(false);
			option.allowRestart();
		}
		this.revalidate();
		this.repaint();
	}
	
	public void gameStart() {
		this.removeAll();
		this.setLayout(new GridLayout(2, 0));
		this.add(submit);
		submit.setEnabled(true);
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public void setOptions(Options option) {
		this.option = option;
	}
}
