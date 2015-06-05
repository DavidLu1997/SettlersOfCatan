package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

//Map class, represents the map
//Programmed primarily by: Mara Gagiu
public class Map
{

    //Tiles
    private ArrayList < Tile > tiles;

    //Robber
    private Robber robber;
    
    //Tile locations on map
    private final int[] tileX = {205, 319, 433, 152, 266, 378, 492, 103, 208, 322, 436, 550, 153, 267, 381, 495, 209, 323, 437};
    private final int[] tileY = {47, 47, 47, 142, 142, 142, 142, 240, 240, 240, 240, 240, 335, 335, 335, 335, 430, 430, 430};
    
    //Vertex locations on map
    private final int[] vertexX = {205, 262, 319, 377, 433, 490, 546, 152, 208, 266, 318, 379, 433, 492, 547, 606, 104, 156, 211, 266, 323, 378, 436, 492, 549, 606, 664, 104, 156, 211, 266, 323, 378, 436, 492, 549, 606, 664, 153, 209, 267, 300, 381, 437, 494, 552, 610, 209, 266, 323, 380, 438, 494, 552};
    private final int[] vertexY = {78, 48, 78, 48, 78, 48, 78, 176, 146, 176, 146, 176, 146, 176, 146, 176, 274, 242, 274, 242, 274, 242, 274, 242, 274, 242, 274, 338, 366, 338, 366, 338, 366, 338, 366, 338, 366, 338, 366, 338, 432, 464, 432, 464, 432, 464, 432, 464, 432, 528, 558, 528, 558, 528, 558, 528};
    
    //Occupied vertices
    private ArrayList<Location> vertices = new ArrayList<Location>();
    
    //Occupied edges
    private ArrayList<Location> edges = new ArrayList<Location>();

    //Default constructor
    public Map ()
    {
    	
    }


    //Full constructor
    public Map (ArrayList <Tile> tiles, Robber robber)
    {
		this.tiles = tiles;
		this.robber = robber;
    }
    
    //Getter for robber
    public Robber getRobber()
    {
    	return robber;
    }

    //Getter for tiles
    public ArrayList<Tile> getTiles()
    {
    	return tiles;
    }
    
    //Getter for tile printing location x
    public int getTileX(int n)
    {
    	return tileX[n];
    }
    
    //Getter for tile printing location y
    public int getTileY(int n)
    {
    	return tileY[n];
    }
    
    //Getter for vertex printing location x
    public int getVertexX(int n)
    {
    	return vertexX[n];
    }
    
    //Getter for vertex printing location y
    public int getVertexY(int n)
    {
    	return vertexY[n];
    }

    //Adds a location to its respective occupied arraylist
    public void addOccupied(Location l)
    {
    	if(l.getType() == locationType.VERTICE)
    		vertices.add(l);
    	else
    		edges.add(l);
    }
    
    //Checks if an location is already occupied
    public boolean check(Location l)
    {
    	if(l.getType() == locationType.VERTICE)
    	{
    		for(int i = 0; i < vertices.size(); i++)
    			if(vertices.get(i).equals(l))
    				return true;
    	}
    	else
    	{
    		for(int i = 0; i < edges.size(); i++)
    			if(edges.get(i).equals(l))
    				return true;
    	}
    	
    	return false;
    }
}

