package Player;

//Dice class, for rolling
//Programmed primarily by: David Lu
public class Dice {
	
	//Value of dice
	private int value;
	
	//Constructor
	public Dice()
	{
		value = roll();
	}
	
	//toString
	public String toString()
	{
		return value + "";
	}
	
	//Rolls, updates and returns value
	public int roll()
	{
		value = (int)(Math.random()*6+1);
		return value;
	}
	
	//Get current value of dice
	public int get()
	{
		return value;
	}
}
