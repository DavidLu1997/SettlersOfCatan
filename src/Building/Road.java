package Building;

import java.util.ArrayList;
import javax.swing.*;
import Card.ResourceCard;
import Map.Location;
import Map.locationType;

//Road class, subset of building
//Programmed primarily by: Sayan Faraz
public class Road extends Building {
	
	Location location2;

	//Constructor
	public Road(Location location, Location location2, ArrayList<ResourceCard> resources) 
	{
		super(buildingType.ROAD, location, resources);
		
		this.location2 = location2;
	}
	
	//Get location2
	public Location getLocation2()
	{
		return location2;
	}

	@Override
	public void loadImage() {
		// TODO Auto-generated method stub
		
	}
}
