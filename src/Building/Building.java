package Building;

import javax.swing.*;
import Map.Location;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Card.ResourceCard;

//Building class, represents various types of buildings
//Programmed primarily by: Sayan Faraz
public abstract class Building {

	//Type of Building
	private buildingType type;
	
	//Location of building (done by mara, NOT SAYAN)
	protected Location location;
	
	//Resources required
	private ArrayList<ResourceCard> resources;
	
	//Victory points granted by this building
	private int victoryPoints;
	
	//Image
	BufferedImage img;
	
	//Constructor
	public Building(buildingType type, Location location, ArrayList<ResourceCard> resources)
	{
		this.type = type;
		this.location = location;
		this.resources = resources;
		
		//Get victoryPoints
		if(type == buildingType.SETTLEMENT)
			this.victoryPoints = 1;
		else if(type == buildingType.CITY)
			this.victoryPoints = 2;
		else
			this.victoryPoints = 0;
	}
	
	//Loads the image
	public abstract void loadImage();
	
	//Get type
	public buildingType getType()
	{
		return type;
	}
	
	//Get location
	public Location getLocation()
	{
		return location;
	}
	
	//Get resources
	public ArrayList<ResourceCard> getResources()
	{
		return resources;
	}
	
	//Returns victory points
	public int getVP()
	{
		return victoryPoints;
	}
	
	//Get image
	public BufferedImage getImage()
	{
		return img;
	}
}
