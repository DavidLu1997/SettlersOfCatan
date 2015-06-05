package Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Player.Player;

//Map panel used exclusively to draw map
//Programmed primarily by: David Lu
public class MapPanel extends JPanel
{
	//Map Image
	private BufferedImage map;
	
	//Map
	private Map m;
	
	//Player array
	private Player[] p;
	
	//Road adjustment factor
	private final int factor = 25;
	
	//Constructor
	public MapPanel(Map m, Player[] p)
	{
		this.m = m;
		this.p = p;
		
		//Load map
		try 
		{
			map = (BufferedImage) ImageIO.read(new File("src\\Map\\board.png"));
		}
		catch (IOException e) 
		{
			System.out.println("Invalid path.");
		}
		
		//Set size
		Dimension size = new Dimension(map.getWidth(null), map.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}
	
	//Overriding paint
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
  	
		//Map itself
	    g.drawImage(map, 0, 0, Color.CYAN, null);
  	
	    //Tiles
	    for(int i = 0; i < m.getTiles().size(); i++)
	    {
	    	g.drawImage(m.getTiles().get(i).getImage(), m.getTileX(i), m.getTileY(i), null);
	    }
	    
	    //Players
	    for(int i = 0; i < p.length; i++)
	    {
	    	//Print each settlement
	    	for(int j = 0; j < p[i].getSettlements().size(); j++)
	    	{
	    		int x=0, y=0;
	    		
	    		//For each vertex, get corresponding x, y coordinate
	    		for(int k = 0, l = 0; k < p[i].getSettlements().get(j).getLocation().getVerticesX().length && l < p[i].getSettlements().get(j).getLocation().getVerticesY().length; k++, l++)
	    		{
	    			if(p[i].getSettlements().get(j).getLocation().getVerticesX()[k] == p[i].getSettlements().get(j).getLocation().getX() &&
	    					p[i].getSettlements().get(j).getLocation().getVerticesY()[l] == p[i].getSettlements().get(j).getLocation().getY())
	    			{
	    				x = k;
	    				y = l;
	    				break;
	    			}
	    		}
	    		
	    		//print
	    		g.drawImage(p[i].getSettlements().get(j).getImage(), m.getVertexX(x) - factor, m.getVertexY(y) - factor, p[i].getColor(), null);
	    	}
	    	
	    	//Print each city
	    	for(int j = 0; j < p[i].getCities().size(); j++)
	    	{
	    		int x=0, y=0;
	    		
	    		//For each vertex, get corresponding x, y coordinate
	    		for(int k = 0, l = 0; k < p[i].getCities().get(j).getLocation().getVerticesX().length && l < p[i].getCities().get(j).getLocation().getVerticesY().length; k++, l++)
	    		{
	    			if(p[i].getCities().get(j).getLocation().getVerticesX()[k] == p[i].getCities().get(j).getLocation().getX() &&
	    					p[i].getCities().get(j).getLocation().getVerticesY()[l] == p[i].getCities().get(j).getLocation().getY())
	    			{
	    				x = k;
	    				y = l;
	    				break;
	    			}
	    		}
	    		
	    		//print
	    		g.drawImage(p[i].getCities().get(j).getImage(), m.getVertexX(x) - factor, m.getVertexY(y) - factor, p[i].getColor(), null);
	    	}
	    	
	    	//Print each road
	    	for(int j = 0; j < p[i].getRoads().size(); j++)
	    	{
	    		int x=0, y=0;
	    		
	    		//For each vertex, get corresponding x, y coordinate
	    		for(int k = 0, l = 0; k < p[i].getRoads().get(j).getLocation().getVerticesX().length && l < p[i].getRoads().get(j).getLocation().getVerticesY().length; k++, l++)
	    		{
	    			if(p[i].getRoads().get(j).getLocation().getVerticesX()[k] == p[i].getRoads().get(j).getLocation().getX() &&
	    					p[i].getRoads().get(j).getLocation().getVerticesY()[l] == p[i].getRoads().get(j).getLocation().getY())
	    			{
	    				x = k;
	    				y = l;
	    				break;
	    			}
	    		}
	    		
	    		int x1=0, y1=0;
	    		
	    		//For each vertex, get corresponding x, y coordinate
	    		for(int k = 0, l = 0; k < p[i].getRoads().get(j).getLocation2().getVerticesX().length && l < p[i].getRoads().get(j).getLocation2().getVerticesY().length; k++, l++)
	    		{
	    			if(p[i].getRoads().get(j).getLocation2().getVerticesX()[k] == p[i].getRoads().get(j).getLocation2().getX() &&
	    					p[i].getRoads().get(j).getLocation2().getVerticesY()[l] == p[i].getRoads().get(j).getLocation2().getY())
	    			{
	    				x1 = k;
	    				y1 = l;
	    				break;
	    			}
	    		}
	    		
	    		//print
	    		g.setColor(p[i].getColor());
	    		g.drawLine(x-50, y-50, x1-50, y1-50);
	    	}
	    }
	}
}