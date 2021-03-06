import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

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
	String dashesTemp;
	String currentWord;

	ArrayList<String> wordListInit = new ArrayList<>();
	Stack<String> wordList = new Stack<>();
	ArrayList<String> correctList = new ArrayList<>();
	ArrayList<JLabel> labelList = new ArrayList<>();
	ArrayList<Boolean> correctTF = new ArrayList<>();
	ArrayList<String> usedLetters = new ArrayList<>();

	Random r = new Random();

	int lastHashLocation = 0;
	int livesCount;

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
		h.setLivesText();

		//h.wordList = new ArrayList<>();

		int wordCount = 0;

		while (wordCount <= 0 || wordCount >= 3000) {
			try {
				wordCount = Integer.parseInt(h.wordCount);
				System.out.println(wordCount);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter something real bro");
			}
		}

		for (int i = 0; i < wordCount; i++) {
			h.wordList.add(h.getWord());
		}

		// System.out.println(wordList);

		h.setDashesText();
	}

	void setDashesText() {
		correctTF.clear();
		correctList.clear();
		int length = 0;
		
		currentWord = wordList.pop();
		
		length = currentWord.length();
		
		System.out.println(currentWord);

		System.out.println(length);

		for (int i = 0; i < length; i++) {
			labelList.add(new JLabel());
		}
		for (int i = 0; i < length; i++) {
			panel.add(labelList.get(i));
		}
		for (int i = 0; i < length; i++) {
			labelList.get(i).setText("_");
		}
	}

	void setLivesText() {
		int wc = Integer.parseInt(wordCount);

		int livesTemp = wc * 2;

		lives.setText("You have " + livesTemp + " lives remaining.");
		livesCount = livesTemp;
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
		boolean cont = false;
		boolean isCorrect = false;
		String letter = JOptionPane.showInputDialog("Enter a letter: ");

		usedLetters.add(letter);

		
		char[] letterList = currentWord.toCharArray();
		for (int i = 0; i < letterList.length; i++) {
			if (letter.equals("" + letterList[i])) {
				isCorrect = true;
				correctList.add(letter);
				System.out.println("in if");
				labelList.get(i).setText("" + letterList[i]);
			}
		}
		if (!isCorrect) {
			System.out.println("in else");
			livesCount--;
			lives.setText("You have " + livesCount + " lives remaining.");
			if (livesCount <= 0) {
				JOptionPane.showMessageDialog(null, "You failed.");
				System.exit(0);
			}
		}
		if (currentWord.length() == correctList.size()) {
			cont = true;
		}
		if (cont) {
			try {
				setDashesText();
			}
			catch (EmptyStackException e1) {
				JOptionPane.showMessageDialog(null, "You've complete this really bad game. I don't recomend you play again!");
				System.exit(0);
			}
		}
	}
}
