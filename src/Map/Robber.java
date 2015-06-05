package Map;

import javax.swing.*;

//Robber class, represents the Robber
//Programmed primarily by: Mara Gagiu
public class Robber {

	//Location
	private int location;
	
	//Constructor
	//Should be placed on desert
	public Robber(int location)
	{
		this.location = location;
		//draw();
	}
	
	//Draws robber
	public void draw(JPanel pane)
	{
		
	}
	
	//Moves robber to new location
	public void move(int location)
	{
		this.location = location;
		//draw();
	}
}
