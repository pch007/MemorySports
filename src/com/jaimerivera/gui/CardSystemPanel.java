package com.jaimerivera.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jaimerivera.memory.MajorCodeSystem;
import com.jaimerivera.memory.spec.Card;
import com.jaimerivera.memory.spec.Card.Face;
import com.jaimerivera.memory.spec.Card.Suit;
import com.jaimerivera.memory.spec.CardSystem;

public class CardSystemPanel extends StandardResponsePanel {

	private static final long serialVersionUID = -817749148952494267L;
	private static BufferedImage CARD_SPRITE;
	
	static {
		try {
			CARD_SPRITE = ImageIO.read(new File("images/classic-playing-cards.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private static int CARD_WIDTH = CARD_SPRITE.getWidth() / Card.NUM_FACES;
	private static int CARD_HEIGHT = CARD_SPRITE.getHeight() / Card.NUM_SUITS;
	
	private CardSystem cSystem;
	private MajorCodeSystem mcMap;
	private JPanel panel;
	private Card current;
	private JLabel lblAwaitingResponse;
	private JPanel imagePanel;
	private JLabel lblNewLabel;

	public CardSystemPanel() {
		this.cSystem = null;
		this.mcMap = null;
		
		try {
			this.cSystem = new CardSystem(new File("preferences/card_system.txt"));
			this.mcMap = new MajorCodeSystem(new File("preferences/major_code_lvl2.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		this.setRandomCard();
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 200));
		panel.setMinimumSize(new Dimension(200, 200));
		panel.setMaximumSize(new Dimension(200, 200));
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		imagePanel = new JPanel()  {
			private static final long serialVersionUID = -4787780716859746911L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int x = this.getWidth() / 2 - CARD_WIDTH / 2;
				int y =this.getHeight() / 2 - CARD_HEIGHT / 2;
				g.drawImage(CardSystemPanel.getSubImage(current), x, y, this);
			}
		};

		panel.add(imagePanel);
		
		lblAwaitingResponse = new JLabel("Awaiting Response");
		lblAwaitingResponse.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAwaitingResponse);
		
		lblNewLabel = new JLabel("For each card, input its associated word.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.NORTH);
	}
	
	private void setRandomCard() {
		int faceIndex = (int)(Math.random() * Card.NUM_FACES);
		int suitIndex = (int)(Math.random() * Card.NUM_SUITS);
		this.current = Card.getCard(Face.values()[faceIndex], Suit.values()[suitIndex]);
	}
	
	@Override
	public void onSendClick() {
		if (this.current == null) {
			return;
		}
		
		String response = this.textField.getText();
		String answer = this.mcMap.get(this.cSystem.get(this.current));
		boolean correct = response.equals(answer);
		String responseText = "";
		
		if (correct) {
			responseText += "Correct!";
			this.setRandomCard();
			this.imagePanel.repaint();
		} else {
			responseText += "Wrong.";
		}
		
		this.lblAwaitingResponse.setText(responseText);
	}

	public static Image getSubImage(Card c) {
		int x = c.face.ordinal() * CARD_WIDTH;
		int y = c.suit.ordinal() * CARD_HEIGHT;
		return CARD_SPRITE.getSubimage(x, y, CARD_WIDTH, CARD_HEIGHT);
	}
	
	
}
