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
	private int total = 0;
	
	public Submit(int[] answer, int[] input) {
		super();
		this.answer = answer;
		this.input = input;
		this.setLayout(new GridLayout(2, 0));
		submit.addActionListener(this);
		this.add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			total = answer.length;
			for(int i = 0; i < answer.length; i++) {
				if(answer[i] == input[i]) {
					correct++;
				}
			}
			score = new JLabel("Your accuracy is: " + correct/total);
			this.add(score);
		}
		repaint();
		
	}
}
