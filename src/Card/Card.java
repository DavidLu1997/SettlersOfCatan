package Card;

import javax.swing.*;

//Card class, generic class for all cards
//Programmed primarily by: Sayan Faraz
public abstract class Card {

	//Name
	String name;
	
	//Description
	String description;
	
	//Default constructor
	public Card()
	{
		
	}
	
	//Constructor
	public Card(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	//toString
	public abstract String toString();
	
	//Draw
	public abstract void draw(JPanel pane, int x, int y);
}
