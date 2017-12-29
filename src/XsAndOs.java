import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class XsAndOs implements ActionListener {

	
	private int size;
	private int size2;
	JFrame window;
	JButton[] button;
	boolean isOsTurn;
	boolean gameOver;
	
	public XsAndOs(int size) {
		this.size = size;
		size2 = size*size;
		window = new JFrame("X: Tic Tac Toe");
		window.setLayout(new GridLayout(size, size));
		button = new JButton[size2];
		for (int i = 0; i < size2; i++) {
			button[i] = new JButton();
			button[i].addActionListener(this);
			window.add(button[i]);
		}
		window.setSize(70*size, 70*size);
		window.setLocationByPlatform(true);
		window.setVisible(true);
	}
	
	private boolean isGameOver() {
		// Look for a win in each column
		for (int i = 0; i < size; i++) {
			String start = button[i].getText();
			if (start.length() == 0) {
				continue; // Disallow empty victory; go to next column
			}
			int j = i;
			for (; j < size2; j += size) {
				if (!button[j].getText().equals(start)) {
					break;
				}
			}
			if (j >= size2) {
				JOptionPane.showMessageDialog(window, "Game over! "+start+" won!");
				return true;
			}
		}
		// Look for a win in each row
		for (int i = 0; i < size2; i = i + size) {
			String start = button[i].getText();
			if (start.length() == 0) {
				continue; // Disallow empty victory; go to next column
			}
			int j = i;
			for (; j < i + size; j++) {
				if (!button[j].getText().equals(start)) {
					break;
				}
			}
			if (j == i + size) {
				JOptionPane.showMessageDialog(window, "Game over! "+start+" won!");
				return true;
			}
		}
		// TODO: consider other win conditions
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameOver) {
			// TODO message
		} else {
			int i = 0;
			for(; i < button.length; i++) {
				if (e.getSource() == button[i]) {
					break;
				}
			}
			if (button[i].getText().length() == 0) {
				button[i].setText(isOsTurn ? "O" : "X");
				if (isGameOver()) {
					gameOver = true;
				} else {
					isOsTurn = !isOsTurn;
					window.setTitle((isOsTurn ? "O" : "X")+": Tic Tac Toe");
				}
			} else {
				JOptionPane.showMessageDialog(window, "Someone already moved there!");
			}
		}
	}

	public static void main(String[] args) {
		XsAndOs xo = new XsAndOs(5);
	}

}
