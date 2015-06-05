import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import Building.Building;
import Card.ResourceCard;
import Player.Player;

//Listener class, used to implement turns
//Programmed primarily by: David Lu
public class Listener implements ActionListener{
	
	//JFrame
	JFrame frame;
	
	private final String faq = new String("You are a redneck from Louisiana trapped on an island with three other rednecks from Texas, Georgia and Alabama. You have played the game Settlers of Catan before, and your fellow rednecks have agreed to attempt to survive based on the game itself. However the island is deficient in several aspects, and it is up to you to find what is possible and what is not. You know that the game can be won if you reach 10 victory points, but you don’t know how. Once you have reached 10 victory points, you will have obtained dominance over all other rednecks on the island and obtain the right to ride the lone pickup truck off the island.");
	
	public Listener()
	{
		frame = new JFrame();
	}
	
	//Action Performed
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		//To roll for resources, disabled after first roll
		case "roll":
			Game.collect(Game.currentPlayer);
			Game.rollB.setEnabled(false);
			break;
		//If build, open build window
		case "build":
			buildListener build = new buildListener(Game.player[Game.currentPlayer]);
			update();
			break;
		//If trade, open trade window
		case "trade":
			tradeListener trade = new tradeListener(Game.player[Game.currentPlayer]);
			update();
			break;
		//If faq, open up faq
		case "FAQ":
			faq();
			break;
		//If end turn, increment current player and draw map
		case "end":
			Game.incrementPlayer();
			update();
			Game.rollB.setEnabled(true);
			break;
		//If game over, kill all frames and exit
		case "GG":
			frame.dispose();
			Game.frame.dispose();
			System.exit(0);
			break;
		}
	}
	
	//Updates map for next player
	private void update()
	{
		//Redraw map and update player info
		Game.drawMap();
		
		//If game has been won
		if(Game.player[Game.currentPlayer].update() >= 10)
			win(Game.currentPlayer);
			
		//Update player info
		Game.updateInfo(Game.currentPlayer);
		
		//Redraw frame
		SwingUtilities.updateComponentTreeUI(Game.frame);
	}
	
	//Prints who won the game and closes everything
	private void win(int player)
	{
		//Initialize frame
		frame.dispose();
		frame = new JFrame("GOOD GAME!");
		
		//Label and button
		JLabel label = new JLabel("Good game, player " + player + " has won the game.");
		JButton button = new JButton("GG");
		button.setActionCommand("GG");
		button.addActionListener(this);
		
		//Add to frame
		frame.add(label);
		frame.add(button);
		
		//Show frame
		frame.pack();
		frame.setSize(200, 100);
		frame.setVisible(true);
		frame.setLocationRelativeTo(Game.frame);
		frame.setAlwaysOnTop(true);
	}
	
	//Print faq
	private void faq()
	{
		//Initialize frame
		frame.dispose();
		frame = new JFrame("Instructions");
		
		//JTextArea
		JTextArea text = new JTextArea(faq, 10, 40);
		JScrollPane scrollPane = new JScrollPane(text);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		//So it looks nice
		JPanel pane = new JPanel();
		pane.add(text);
		
		//Add to frame
		frame.setContentPane(pane);
		
		//Show frame
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(Game.frame);
		frame.setAlwaysOnTop(true);
	}
}
