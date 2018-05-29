import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel lives = new JLabel();
	JLabel dashes = new JLabel();
	JButton guessLetter = new JButton(); 
	
	String wordCount;
	
	ArrayList<String> wordListInit = new ArrayList<>();
	ArrayList<String> wordList = new ArrayList<>();
	
	ArrayList<String> usedLetters = new ArrayList<>();
	
	Random r = new Random();
	
	public static void main(String[] args) {
		Hangman h = new Hangman();
		
		h.wordCount = JOptionPane.showInputDialog("Enter the amount of words to use: ");
		
		h.panel.add(h.guessLetter);
		h.panel.add(h.dashes);
		h.panel.add(h.lives);
		h.frame.add(h.panel);
		h.frame.setVisible(true);
		h.frame.setSize(500, 500);
		h.guessLetter.addActionListener(h);
		h.guessLetter.setText("Guess Letter");
		h.setDashesText();
		h.setLivesText();
		
		ArrayList<String> wordList = new ArrayList<>();
		
		for (int i = 0; i < Integer.parseInt(h.wordCount); i++) {
			wordList.add(h.getWord());
		}
		
		System.out.println(wordList);
	}

	void setDashesText() {
		int wc = Integer.parseInt(wordCount);
		
		String dashesTemp = "";
		
		for (int i = 0; i < wc; i++) {
			dashesTemp += "_ ";
		}
		
		dashes.setText(dashesTemp);	
	}
	
	void setLivesText() {
		int wc = Integer.parseInt(wordCount);
		
		int livesTemp = wc * 2;
		
		lives.setText("You have" + livesTemp + " lives remaining.");
	}
	
	String getWord() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/dictionary.txt"));
			
			String line = br.readLine();
			while (line != null) {
				 wordListInit.add(line);
				 line = br.readLine();
			}
			
			int num = r.nextInt(3000);
			
			return wordListInit.get(num);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String letter = JOptionPane.showInputDialog("Enter a letter: ");
		
		usedLetters.add(letter);
		
		for (int i = 0; i < Integer.parseInt(wordCount); i++) {
			if (wordList.get(i).contains(letter)) {
				
			}
		}
	}
}
