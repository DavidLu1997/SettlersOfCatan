package Building;

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

//Settlement class, represents a settlement
//Programmed primarily by: Sayan Faraz
public class Settlement extends Building {

	//Constructor
	public Settlement(Location location, ArrayList<ResourceCard> resources)
	{
		super(buildingType.SETTLEMENT, location, resources);
		
		loadImage();
	}

	@Override
	public void loadImage() 
	{
    	//Load map
    	try 
    	{
			img = (BufferedImage) ImageIO.read(new File("src\\Building\\settlement.png"));
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
