package Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import static java.util.Arrays.asList;

//Location class, stores the location of various items
//Programmed primarily by: Mara Gagiu
public class Location
{

    //Location in terms of x, y
    private int x, y;

    //Type of location
    private locationType type;

    //Adjacent locations
    private ArrayList <Location> adjacent;
    
    //Vertex coordinates for getAdjacent
    private final int[] verticesX = {0, 1, 2, 3, 4, 5, 6, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6};
    private final int[] verticesY = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5};
    

    //Constructor
    public Location (int x, int y, locationType type)
    {
		this.x = x;
		this.y = y;
		this.type = type;
		
		adjacent = new ArrayList<Location>();
		
		if(type == locationType.VERTICE)
			findAdjacent ();
    }


    //toString
    public String toString ()
    {
    	return "(" + this.x + ", " + this.y + ")";
    }


    //Get adjacent locations
    public ArrayList <Location> getAdjacent ()
    {
    	return adjacent;
    }
    
    //Get verticesX
    public int[] getVerticesX()
    {
    	return verticesX;
    }
    
    //Get verticesY
    public int[] getVerticesY()
    {
    	return verticesY;
    }
    
    //Get x
    public int getX()
    {
    	return x;
    }
    
    //Get y
    public int getY()
    {
    	return y;
    }
    
    //Get type of location
    public locationType getType()
    {
    	return type;
    }
    
    //Set location
    public void setLocation(int x, int y)
    {
    	this.x = x;
    	this.y = y;
    	
    	//Clear adjacent
    	adjacent.clear();
    	
    	//Refind adjacent
    	if(type == locationType.VERTICE)
			findAdjacent ();
    }


    //Get shortest distance to another location
    public int distance (Location l)
    {
		int distance;
		
		if (l == null) //check if location passed through parameters is null
		{
		    return -1;
		}
		
		else
		{
		    distance = ((int) ((Math.abs (this.x - l.x)) + (Math.abs (this.y - l.y)))); // distance = dx + dy
		    return distance;
	
		}

    }


    //Calculate adjacent locations, only for tiles adjacent to a vertice
    private void findAdjacent ()
    {
        //3D Array, tiles[x][y] refers to all tiles the vertex (x,y) is adjacent to 
        Point[][][] tiles =
        	{
        		//0
        		{
        			//0
        			{
        				new Point(0, 0)
        			},
        			
        			//1
        			{
        				new Point(0, 1)
        			},
        			
        			//2
        			{
        				new Point(0, 2)
        			},
        			
        			//3
        			{
        				new Point(0, 2)
        			},
        			
        			//4
        			{
        				new Point(0, 3)
        			},
        			
        			//5
        			{
        				new Point(0, 4)
        			}
        		},
        		
        		//1
        		{
        			//0
        			{
        				new Point(0, 0)
        			},
        			
        			//1
        			{
        				new Point(0, 0), new Point(0, 1)
        			},
        			
        			//2
        			{
        				new Point(0, 1), new Point(0, 2)
        			},
        			
        			//3
        			{
        				new Point(0, 2), new Point(0, 3)
        			},
        			
        			//4
        			{
        				new Point(0, 3), new Point(0, 4)
        			},
        			
        			//5
        			{
        				new Point(0, 4)
        			}
        		},
        		
        		//2
        		{
        			//0
        			{
        				new Point(0, 0), new Point(1, 0)
        			},
        			
        			//1
        			{
        				new Point(0, 0), new Point(0, 1), new Point(1, 1)
        			},
        			
        			//2
        			{
        				new Point(0, 1), new Point(0, 2), new Point(1, 2)
        			},
        			
        			//3
        			{
        				new Point(0, 2), new Point(0, 3), new Point(1, 2)
        			},
        			
        			//4
        			{
        				new Point(0, 3), new Point(0, 4), new Point(1, 3)
        			},
        			
        			//5
        			{
        				new Point(0, 4), new Point(1, 4)
        			}
        		},
        		
        		//3
        		{
        			//0
        			{
        				new Point(1, 0)
        			},
        			
        			//1
        			{
        				new Point(0, 0), new Point(1, 0), new Point(1, 1)
        			},
        			
        			//2
        			{
        				new Point(0, 1), new Point(1, 1), new Point(1, 2)
        			},
        			
        			//3
        			{
        				new Point(1, 2), new Point(0, 3), new Point(1, 3)
        			},
        			
        			//4
        			{
        				new Point(1, 3), new Point(0, 4), new Point(1, 4)
        			},
        			
        			//5
        			{
        				new Point(1, 4)
        			}
        		},
        		
        		//4
        		{
        			//0
        			{
        				new Point(1, 0), new Point(2, 0)
        			},
        			
        			//1
        			{
        				new Point(1, 0), new Point(1, 1), new Point(2, 1)
        			},
        			
        			//2
        			{
        				new Point(1, 1), new Point(1, 2), new Point(2, 2)
        			},
        			
        			//3
        			{
        				new Point(1, 2), new Point(2, 2), new Point(1, 3)
        			},
        			
        			//4
        			{
        				new Point(1, 3), new Point(2, 3), new Point(1, 4)
        			},
        			
        			//5
        			{
        				new Point(1, 4), new Point(2, 4)
        			}
        		},
        		
        		//5
        		{
        			//0
        			{
        				new Point(2, 0)
        			},
        			
        			//1
        			{
        				new Point(1, 0), new Point(2, 0), new Point(2, 1)
        			},
        			
        			//2
        			{
        				new Point(1, 1), new Point(2, 1), new Point(2, 2)
        			},
        			
        			//3
        			{
        				new Point(2, 2), new Point(1, 3), new Point(2, 3)
        			},
        			
        			//4
        			{
        				new Point(2, 3), new Point(1, 4), new Point(2, 4)
        			},
        			
        			//5
        			{
        				new Point(2, 4)
        			}
        		},
        		
        		//6
        		{
        			//0
        			{
        				new Point(2, 0)
        			},
        			
        			//1
        			{
        				new Point(2, 0), new Point(2, 1), new Point(3, 1)
        			},
        			
        			//2
        			{
        				new Point(2, 1), new Point(2, 2), new Point(3, 2)
        			},
        			
        			//3
        			{
        				new Point(2, 2), new Point(3, 2), new Point(2, 3)
        			},
        			
        			//4
        			{
        				new Point(2, 3), new Point(3, 3), new Point(2, 4)
        			},
        			
        			//5
        			{
        				new Point(2, 4)
        			}
        		},
        		
        		//7
        		{
        			//0
        			{
        				//DNE
        			},
        			
        			//1
        			{
        				new Point(2, 0), new Point(3, 1)
        			},
        			
        			//2
        			{
        				new Point(2, 1), new Point(3, 1), new Point(3, 2)
        			},
        			
        			//3
        			{
        				new Point(3, 2), new Point(2, 3), new Point(3, 3)
        			},
        			
        			//4
        			{
        				new Point(3, 3), new Point(2, 4)
        			},
        			
        			//5
        			{
        				//DNE
        			}
        		},
        		
        		//8
        		{
        			//0
        			{
        				//DNE
        			},
        			
        			//1
        			{
        				new Point(3, 1)
        			},
        			
        			//2
        			{
        				new Point(3, 1), new Point(3, 2), new Point(4, 2)
        			},
        			
        			//3
        			{
        				new Point(3, 2), new Point(4, 2), new Point(3, 3)
        			},
        			
        			//4
        			{
        				new Point(3, 3)
        			},
        			
        			//5
        			{
        				//DNE
        			}
        		},
        		
        		//9
        		{
        			//0
        			{
        				//DNE
        			},
        			
        			//1
        			{
        				//DNE
        			},
        			
        			//2
        			{
        				new Point(3, 1), new Point(4, 2)
        			},
        			
        			//3
        			{
        				new Point(4, 2), new Point(3, 3)
        			},
        			
        			//4
        			{
        				//DNE
        			},
        			
        			//5
        			{
        				//DNE
        			}
        		},
        		
        		//10
        		{
        			//0
        			{
        				//DNE
        			},
        			
        			//1
        			{
        				//DNE
        			},
        			
        			//2
        			{
        				new Point(4, 2)
        			},
        			
        			//3
        			{
        				new Point(4, 2)
        			},
        			
        			//4
        			{
        				//DNE
        			},
        			
        			//5
        			{
        				//DNE
        			}
        		}
        	};
    	
    	//Get adjacent for each one
    	for(int i = 0; i < tiles[this.x][this.y].length; i++)
    	{
    		adjacent.add(tiles[this.x][this.y][i].toLocation());
    		System.out.println(tiles[this.x][this.y][i].toLocation());
    	}
    }


    // checks if two locations are equal
    public boolean equals (Location other)
    {
    	return (other != null && other.x == this.x && other.y == this.y && other.type == this.type);
    }
}

//Point class, for tiles
class Point
{
	public int x, y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Location toLocation()
	{
		return new Location(x, y, locationType.TILE);
	}
}