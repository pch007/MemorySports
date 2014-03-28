package com.jaimerivera.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class StandardResponsePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected JLabel lblTemp;
	protected JButton btnNewButton;
	protected JTextField textField;
	protected JLabel lblNewLabel;
	protected JPanel panel;

	/**
	 * Create the panel.
	 */
	public StandardResponsePanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Input:");
		lblNewLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(lblNewLabel, BorderLayout.WEST);
		
		lblTemp = new JLabel("");
		lblTemp.setPreferredSize(new Dimension(35, 16));
		lblTemp.setMaximumSize(new Dimension(35, 16));
		lblTemp.setMinimumSize(new Dimension(35, 16));
		add(lblTemp, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					onSendClick();
				} else {
					super.keyPressed(e);
				}
			}
		});
		
		btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onSendClick();
			}
		});
		panel.add(btnNewButton, BorderLayout.EAST);
	}
	
	public abstract void onSendClick();
	
	
}
