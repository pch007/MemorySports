package com.jaimerivera.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jaimerivera.memory.std.MajorCodeSystem;

public class MajorCodeSystemPanel extends StandardResponsePanel {

	private static final long serialVersionUID = 1L;
	
	private MajorCodeSystem mcSystem;
	private List<Integer> numbers;
	private JLabel lblNewLabel;
	private JLabel lblAwaitingResponse;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public MajorCodeSystemPanel() {
		lblTemp.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			this.mcSystem = new MajorCodeSystem(new File("preferences/major_code_lvl2.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		this.lblTemp.setText("For each number, input its associated word.");
		this.numbers = new ArrayList<Integer>(this.mcSystem.getTypeA());
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblNewLabel = new JLabel("" + this.getRandomNumber());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		lblAwaitingResponse = new JLabel("Awaiting Response");
		lblAwaitingResponse.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAwaitingResponse);
	}

	private int getRandomNumber() {
		int randomIndex = (int)(Math.random() * this.numbers.size());
		int randomValue = this.numbers.get(randomIndex);
		return randomValue;
	}
	
	@Override
	public void onSendClick() {
		String response = this.textField.getText();
		String answer = this.mcSystem.get(Integer.parseInt(this.lblNewLabel.getText()));
		boolean correct = response.equalsIgnoreCase(answer);
		String responseLabel = "";
		
		if (correct) {
			responseLabel += "Correct!";
			this.lblNewLabel.setText("" + this.getRandomNumber());
		} else {
			responseLabel += "Wrong.";
		}
		
		this.lblAwaitingResponse.setText(responseLabel);
	}

}
