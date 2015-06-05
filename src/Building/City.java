package Building;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import Card.ResourceCard;
import Map.Location;
import Map.locationType;

//City class, represents a city
//Programmed primarily by: Sayan Faraz
public class City extends Building {

	public City(Location location, ArrayList<ResourceCard> resources)
	{
		super(buildingType.CITY, location, resources);
		
		loadImage();
	}

	@Override
	public void loadImage()
	{	
    	//Load map
    	try 
    	{
			img = (BufferedImage) ImageIO.read(new File("src\\Building\\city.png"));
		}
    	catch (IOException e) 
		{
			System.out.println("Invalid path.");
		}
	}
	
	//Set location
	public void setLocation(int x, int y)
	{
		this.location = new Location(x, y, locationType.VERTICE);
	}

	//Get adjacent tiles
	public ArrayList<Location> getAdjacent()
	{
		return this.getLocation().getAdjacent();
	}
}
