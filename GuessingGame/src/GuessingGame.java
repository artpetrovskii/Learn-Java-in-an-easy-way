import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessingGame extends JFrame {
	private JTextField txtGuess;
	private JLabel lblOutput;
	private int theNumber;
	public void checkGuess() {
		String guessText = txtGuess.getText();
		String message = "";
		try {
		int guess = Integer.parseInt(guessText);
		if(guess < theNumber) 
			message = guess + " is too low. Try again.";
		else if (guess > theNumber)
			message = guess + " is too high. Try again.";
		else {
			message = guess + " is correct. You win! Let's play again!";
			newGame();
		    }
		} catch(Exception ex) {
			message = "Enter a whole number between 1 and 100";
		} finally {
		lblOutput.setText(message);
		txtGuess.requestFocus();
		txtGuess.selectAll();
	    }
	}
	public void newGame() {
		theNumber = (int) (Math.random() * 100 + 1);
	}
	public GuessingGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Artemiy's Hi-Lo Guessing Game");
		getContentPane().setLayout(null);
		
		JLabel lblArtemiysHiloGuessing = new JLabel("Artemiy Petrovskiy's Hi-Lo Guessing Game");
		lblArtemiysHiloGuessing.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArtemiysHiloGuessing.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtemiysHiloGuessing.setBounds(10, 11, 414, 19);
		getContentPane().add(lblArtemiysHiloGuessing);
		
		JLabel lblGuessANumber = new JLabel("Guess a number between 1 and 100:");
		lblGuessANumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuessANumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessANumber.setBounds(63, 70, 261, 16);
		getContentPane().add(lblGuessANumber);
		
		txtGuess = new JTextField();
		txtGuess.setHorizontalAlignment(SwingConstants.LEFT);
		txtGuess.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGuess.setBounds(326, 68, 34, 20);
		getContentPane().add(txtGuess);
		txtGuess.setColumns(10);
		
		JButton btnGuess = new JButton("Guess!");
		txtGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		btnGuess.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGuess.setBounds(178, 126, 89, 23);
		getContentPane().add(btnGuess);
		
		lblOutput = new JLabel("Enter a number above and click Guess!");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOutput.setBounds(77, 192, 283, 19);
		getContentPane().add(lblOutput);
	}
	public static void main(String[] args) {
		GuessingGame theGame = new GuessingGame();
		theGame.newGame();
		theGame.setSize(new Dimension(450,300));
		theGame.setVisible(true);
	}
}