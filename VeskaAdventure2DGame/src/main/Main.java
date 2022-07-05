package main;

import javax.swing.JFrame;

public class Main  {

	public static void main(String[] args) {
		
	JFrame window = new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("Veska Adventure Game");
	
	
	GamePanel gamePanel = new GamePanel();
	window.add(gamePanel);
	window.pack(); // sized to fit to window subcomponents
	
	
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	
	
	
	gamePanel.startGamethread();
	
	}
}
