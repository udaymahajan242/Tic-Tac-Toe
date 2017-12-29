import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe implements ActionListener {
	boolean[] array = new boolean[100]; // a 10 by 10 array
	private JFrame window;
	private JButton[] positions;
	private int size = 3;
	private int size2 = size*size;
	private boolean isOsTurn;
	private boolean gameOver;
	
	public void init() {
		window = new JFrame("X: Tic Tac Toe");
		window.setSize(500, 500);
		window.setLocationByPlatform(true);
		window.setLayout(new GridLayout(size,size));
		positions = new JButton[size2];
		for (int i = 0; i < size2; i++) {
			JButton position = new JButton();
			position.addActionListener(this);
			positions[i] = position;
			window.add(position);
		}
		window.setVisible(true);
	}
	
	public boolean isGameOver() {
		// Check for vertical win
		for (int i = 0; i < size; i++) {
			String start = positions[i].getText();
			if (start.length() == 0) {
				continue; // No win if empty
			}
			int j = i;
			for (; j < size2; j += size) {
				if (!positions[j].getText().equals(start))
					break;
			}
			if (j >= size2) {
				gameOver = true;
				JOptionPane.showMessageDialog(window, "Game over! Win for "+start);
				return true;
			}
		}
		// Check for horizontal win
		for (int i = 0; i < size2; i += size) {
			String start = positions[i].getText();
			if (start.length() == 0) {
				continue; // No win if empty
			}
			int j = i;
			for (; j < i + size; j++) {
				if (!positions[j].getText().equals(start))
					break;
			}
			if (j == i + size) {
				gameOver = true;
				JOptionPane.showMessageDialog(window, "Game over! Win for "+start);
				return true;
			}
		}
		// TODO Check for diagonal win
		{
			int j = 0;
			String start = positions[j].getText();
			if (start.length() != 0) {
				for (; j < size2; j += size+1) {
					if (!positions[j].getText().equals(start))
						break;
				}
				if (j >= size2) {
					gameOver = true;
					JOptionPane.showMessageDialog(window, "Game over! Win for "+start);
					return true;
				}
			}
		}
		{
			int j = size-1;
			String start = positions[j].getText();
			if (start.length() != 0) {
				for (; j < size2-1; j += size-1) {
					if (!positions[j].getText().equals(start))
						break;
				}
				if (j >= size2-1) {
					gameOver = true;
					JOptionPane.showMessageDialog(window, "Game over! Win for "+start);
					return true;
				}
			}
		}
		// TODO Check for no more moves
		// Default: game isn't over
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameOver) {
			JOptionPane.showMessageDialog(window, "Game over! No more moves!");	
		} else {
			int i = 0;
			for (; i < size2; i++) {
				if (e.getSource() == positions[i]) {
					break;
				}
			}
			if (positions[i].getText().equals("")) {
				positions[i].setText(isOsTurn ? "O" : "X");
				if (!isGameOver()) {
					isOsTurn = !isOsTurn;
					window.setTitle((isOsTurn ? "O" : "X")+": Tic Tac Toe");
				}
			} else {
				JOptionPane.showMessageDialog(window, "You already moved there!");
			}
		}		
	}

	public static boolean isLastColumnAllTrue(boolean[] a) {
	    int n = (int)Math.sqrt(a.length);
	    for (int i = n-1; i < a.length; i += n) {
	        if (!a[i]) return false;
	    }
	    return true;
	}
	
	
	
	public static void main
	
	(String[] args) {
		TicTacToe tictactoe = new TicTacToe();
		tictactoe.init();
		
		boolean[] truearray = {false, false, true, false, false, true, false, false, true};

		boolean[] falsearray1 = {false, false, true, false, false, false, false, false, true};

		boolean[] falsearray2 = {true, false, false, true, false, false, true, false, true};
	}

}
