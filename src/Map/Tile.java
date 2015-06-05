package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import Card.ResourceCard;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Tile class, represents each individual tile
//Programmed primarily by: Mara Gagiu
public class Tile {
	
	//Type of tile
	private tileType type;
	
	//Number to roll
	private int number;
	
	//Resource produced
	private ResourceCard resource;
	
	//Whether the tile has a harbour or not
	private boolean hasHarbour;
	
	//Location
	private Location location;
	
	//Image
	private BufferedImage img = null;
	
	//Constructor
	public Tile(tileType type, int number, ResourceCard resource)
	{
		this.type = type;
		this.number = number;
		this.resource = resource;
		
		loadImage();
	}
	
	//Overloaded for harbour
	public Tile(tileType type, int number, ResourceCard resource, boolean hasHarbour)
	{
		this.type = type;
		this.number = number;
		this.resource = resource;
		this.hasHarbour = hasHarbour;
	}
	
	//toString
	public String toString()
	{
		switch(type)
		{
		case HILL:
			return "Hill " + location.toString();
		case PASTURE:
			return "Pasture " + location.toString();
		case MOUNTAIN:
			return "Mountain " + location.toString(); 
		case FIELD:
			return "Field " + location.toString();
		case FOREST:
			return "Forest " + location.toString(); 
		case DESERT:
			return "Desert " + location.toString();
		}
		
		return "";
	}
	
	//Load Image
	public void loadImage()
	{
		
		switch(type)
		{
		case HILL:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\brick.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
			
		case PASTURE:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\sheep.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
			
		case MOUNTAIN:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\stone.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
			
		case FIELD:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\wheat.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
			
		case FOREST:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\wood.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
			
		case DESERT:
			try
			{
				img = ImageIO.read(new File("src\\Map\\tiles\\desert.png"));
			} 
			catch (IOException e)
			{
				System.out.println("Invalid path.");
			}
			break;
		}
	}
	
	//Getter for image
	public BufferedImage getImage()
	{
		return img;
	}
	
	//Set location
	public void setLocation(int x, int y)
	{
		location = new Location(x, y, locationType.TILE);
	}
	
	//Get location
	public Location getLocation()
	{
		return location;
	}
	
	//Get type
	public tileType getType()
	{
		return type;
	}
	
	//GetNumber
	public int getNumber()
	{
		return number;
	}
	
	//GetResource
	public ResourceCard getResource()
	{
		return resource;
	}
	
	//Check for equal locations
	public boolean equals(Location l)
	{
		return this.location.equals(l);
	}
}
