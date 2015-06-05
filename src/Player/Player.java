package Player;

import java.awt.Color;
import java.util.ArrayList;

import Building.Building;
import Building.City;
import Building.Road;
import Building.Settlement;
import Card.Resource;
import Card.ResourceCard;

import javax.swing.*;

//Player class, represents what a player currently has
//Programmed primarily by: David Lu
public class Player {
	
	//Resources possessed by player
	private ArrayList<ResourceCard> resources;
	
	//Roads
	private ArrayList<Road> roads;
	
	//Settlements
	private ArrayList<Settlement> settlements;
	
	//Cities
	private ArrayList<City> cities;
	
	//Victory Points
	private int victoryPoints;
	
	//Bonus points from cards
	private int bonus;
	
	//Dice
	private Dice dice;
	
	//Trade
	private Trade trade;
	
	private Color color;
	
	//Default constructor
	public Player()
	{
		resources = new ArrayList<ResourceCard>();
		roads = new ArrayList<Road>();
		settlements = new ArrayList<Settlement>();
		cities = new ArrayList<City>();
		victoryPoints = 0;
		
		dice = new Dice();
		trade = new Trade(4);
		
		
	}
	
	//Full constructor
	public Player(ArrayList<ResourceCard> resources, ArrayList<Road> roads, ArrayList<Settlement> settlements, ArrayList<City> cities, int victoryPoints)
	{
		this.resources = resources;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.victoryPoints = victoryPoints;
		
		dice = new Dice();
		trade = new Trade(4);
	}
	
	//Roll dice
	public int roll()
	{
		return dice.roll() + dice.roll();
	}
	
	//Calculate victory points
	public int update()
	{
		victoryPoints = settlements.size() + cities.size() * 2 + bonus;
		return victoryPoints;
	}
	
	//Draw all possessions on the map
	public void draw(JPanel pane)
	{
		
	}
	
	//Get color
	public Color getColor()
	{
		return color;
	}
	
	//Set color
	public void setColor(Color c)
	{
		color = c;
	}
	
	//Getter for resources
	public ArrayList<ResourceCard> getResources()
	{
		return resources;
	}
	
	//Get number of a type of resource
	public int getResourceNumber(Resource r)
	{
		int count = 0;
		
		//Go through arrayList
		for(int i = 0; i < resources.size(); i++)
		{
			if(resources.get(i).getType() == r)
				count++;
		}
		
		return count;
	}
	
	//Adds a resource card
	public void addResource(ResourceCard r)
	{
		resources.add(r);
		System.out.println(r + " has been added.");
	}
	
	//Removes a resource, true if removed successfully
	public boolean removeResource(ResourceCard r)
	{
		System.out.println(r + " has been removed.");
		return resources.remove(r);
	}
	
	//Adds an arraylist of resourcecards
	public void addResource(ArrayList<ResourceCard> r)
	{
		resources.addAll(r);
	}
	
	//Removes an arraylist of resourcecards, true if all removed successfully
	public boolean removeResource(ArrayList<ResourceCard> r)
	{
		return resources.removeAll(r);
	}
	
	//Getter for roads
	public ArrayList<Road> getRoads()
	{
		return roads;
	}
	
	//Add a road
	public void addRoad(Road r)
	{
		roads.add(r);
	}
	
	//Builds a building, true if built
	public boolean build(Building b)
	{
		//As long as building is not null and location is not occupied, remove the resources and build
		if(b != null)
		{
			int i = 0;
			ArrayList<ResourceCard> temp = new ArrayList<ResourceCard>();
			
			//While haven't reached end and still can be removed
			while(i<b.getResources().size() && removeResource(b.getResources().get(i)))
			{
				temp.add(b.getResources().get(i));
				i++;
			}
			
			//If enough was removed
			if(i==b.getResources().size())
			{
			
				//Switches get building
				switch(b.getType())
				{
				case ROAD:
					roads.add((Road)b);
					break;
				case SETTLEMENT:
					settlements.add((Settlement)b);
					break;
				case CITY:
					cities.add((City)b);
					break;
				}
				
				return true;
			
			}
			
			//Otherwise, add it back in
			else
			{
				addResource(temp);
			}
		}
		
		return false;
	}
	
	//Getter for settlements
	public ArrayList<Settlement> getSettlements()
	{
		return settlements;
	}
	
	//Add a settlement
	public void addSettlement(Settlement s)
	{
		settlements.add(s);
	}
	
	//Getter for cities
	public ArrayList<City> getCities()
	{
		return cities;
	}
	
	//Add a city
	public void addCity(City c)
	{
		cities.add(c);
	}
	
	//Get trade
	public Trade getTrade()
	{
		return trade;
	}
}
