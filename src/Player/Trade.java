package Player;

import java.util.ArrayList;
import Card.ResourceCard;

//Trade class, where trading happens
//Programmed primarily by: David Lu
public class Trade {

	//Trade ratio
	private int ratio;
	
	//Constructor
	public Trade(int ratio)
	{
		this.ratio = ratio;
	}
	
	//Get ratio
	public int getRatio()
	{
		return ratio;
	}
	
	//Change ratio
	public void changeRatio(int ratio)
	{
		this.ratio = ratio;
	}
	
	//Trade at given ratio
	public ArrayList<ResourceCard> trade(ArrayList<ResourceCard> toTrade, ResourceCard card)
	{
		ArrayList<ResourceCard> result = new ArrayList<ResourceCard>();
		
		//While toTrade is valid
		while(toTrade != null && card != null && toTrade.size() >= ratio)
		{
			//Accumulate traded
			result.add(card);
			
			System.out.println("You have traded " + ratio + " " + toTrade.get(toTrade.size()-1) + " for a " + card + ".");
			
			//Remove from toTrade
			for(int i = 0; i < ratio; i++)
				toTrade.remove(0);
		}
		
		return result;
	}
}
