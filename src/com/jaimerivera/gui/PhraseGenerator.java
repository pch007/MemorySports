package com.jaimerivera.gui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.jaimerivera.memory.Phoneme;
import com.jaimerivera.memory.PhonemeTree;
import com.jaimerivera.memory.RestrictedPhonemeMap;
import com.jaimerivera.memory.std.MajorCodeMap;

public class PhraseGenerator extends StandardResponsePanel {

	private static final long serialVersionUID = -4050272205176069403L;

	private RestrictedPhonemeMap rpMap;
	private MajorCodeMap mcMap;
	private PhonemeTree tree;
	private JTextPane textPane;
	
	public PhraseGenerator() {
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel infoLabel = new JLabel("Enter a sequence of digits to generate a random phrase.");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(infoLabel, BorderLayout.SOUTH);
		
		textPane = new JTextPane();
		panel.add(textPane, BorderLayout.CENTER);
		
		try {
			mcMap = new MajorCodeMap(new File("preferences/major_code_map.txt"));
			Set<Phoneme> accepted = new HashSet<Phoneme>();
			
			for (int i = 0; i < 10; i++) {
				for (Phoneme phoneme : mcMap.getPhonemes(i + "")) {
					accepted.add(phoneme);
				}
			}
			
			rpMap = new RestrictedPhonemeMap(new File("ipa/ipa_dictionary.txt"), accepted);
			tree = new PhonemeTree();
			
			for (String word : rpMap.keys()) {
				tree.add(word, rpMap.getPhonemes(word));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	

	@Override
	public void onSendClick() {
		String input = this.textField.getText();
		
		if (isNumber(input)) {
			Phoneme[][] nestedPhonemes = new Phoneme[input.length()][];
			
			for (int i = 0; i < input.length(); i++) {
				nestedPhonemes[i] = mcMap.getPhonemes("" + input.charAt(i));
			}

			List<String> phrase = tree.getRandomPhrase(nestedPhonemes);
			StringBuilder out = new StringBuilder();
			
			for (String word : phrase) {
				out.append(word + " ");
			}
			
			this.textPane.setText(out.toString());
		} else {
			this.textPane.setText("Please enter only digits.");
		}
	}
	
	private boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}

}
