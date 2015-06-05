//Settlers of Catan
//By Sayan Faraz, Mara Gagiu, David Lu
//Version 0.02

//Game class, ties everything together
//Programmed primarily by: David Lu


import Building.*;
import Card.*;
import Map.*;
import Map.Map;
import Player.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Game {
	
	//Version
	static final double version = 1.06;
	
	//Scanner
	public static Scanner input = new Scanner(System.in);
	
	//Listener
	static Listener action = null;
	
	//Graphics
	static JFrame frame;
	static JPanel pane;
	static JPanel mapPane;
	static JPanel infoPane;
	static JPanel actionPane;
	
	//Size
	static final int width = 800;
	static final int length = 800;
	
	//ActionPane Buttons
	static JButton rollB;
	static JButton buildB;
	static JButton tradeB;
	static JButton endTurnB;
	
	//ActionPane strings
	static final String roll = new String("Roll");
	static final String build = new String("Build");
	static final String trade = new String("Trade");
	static final String endTurn = new String("End Turn");
	
	//InfoPane strings
	static final String instructions = new String("Instructions");
	
	//Resource
	static ResourceCard brick = new ResourceCard(Resource.BRICK);
	static ResourceCard wood = new ResourceCard(Resource.WOOD);
	static ResourceCard stone = new ResourceCard(Resource.STONE);
	static ResourceCard wheat = new ResourceCard(Resource.WHEAT);
	static ResourceCard sheep = new ResourceCard(Resource.SHEEP);
	
	//Starting Resources
	static final ArrayList<ResourceCard> starting = new ArrayList<ResourceCard>( Arrays.asList(brick, brick, wood, wood, wheat, wheat, sheep, sheep) );
	
	//Map
	static Map map;
	
	//Tiles
	static final int hills = 3;
	static final int pastures = 4;
	static final int mountains = 3;
	static final int fields = 5;
	static final int forests = 3;
	static final int deserts = 1;
	
	//Buildings
	static City city;
	static Settlement settlement;
	static Road road;
	
	//Resources for buildings
	static ArrayList<ResourceCard> roadR = new ArrayList<ResourceCard>();
	static ArrayList<ResourceCard> settlementR = new ArrayList<ResourceCard>();
	static ArrayList<ResourceCard> cityR = new ArrayList<ResourceCard>();
			
	
	//Locations
	static final int[] locationX = {0, 1, 2, 0, 1, 2 ,3, 0, 1, 2, 3, 4, 0, 1, 2, 3, 0, 1, 2};
	static final int[] locationY = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2 ,2 ,2, 3, 3, 3, 3, 4, 4, 4};
	
	//Player
	static Player[] player = new Player[4];
	
	//Player counts
	static int currentPlayer = 0;
	static final int maxPlayers = 4;
	
	//Main function
	public static void main(String[] args)
	{
		init();
		draw();
	}	
	
	//Increments current player
	public static void incrementPlayer()
	{
		currentPlayer++;
		
		//If reached end
		if(currentPlayer == maxPlayers)
			currentPlayer = 0;
	}
	
	//Scramble map
	public static void scrambleMap()
	{
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Robber robber = null;
		
		//Print percentage done
		System.out.println("Scrambling map...0%");
		
		//Hill
		for(int i = 0; i < hills; i++)
		{
			tiles.add(new Tile(tileType.HILL, (int)(Math.random()*13+2), brick));
			System.out.println("Hill, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Scrambling map...10%");
		
		//Pasture
		for(int i = 0; i < pastures; i++)
		{
			tiles.add(new Tile(tileType.PASTURE, (int)(Math.random()*13+2), sheep));
			System.out.println("Pasture, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Scrambling map...20%");
		
		//Mountain
		for(int i = 0; i < mountains; i++)
		{
			tiles.add(new Tile(tileType.MOUNTAIN, (int)(Math.random()*13+2), stone));
			System.out.println("Mountain, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Scrambling map...30%");
		
		//Field
		for(int i = 0; i < fields; i++)
		{
			tiles.add(new Tile(tileType.FIELD, (int)(Math.random()*13+2), wheat));
			System.out.println("Field, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Scrambling map...40%");
		
		//Forest
		for(int i = 0; i < forests; i++)
		{
			tiles.add(new Tile(tileType.FOREST, (int)(Math.random()*13+2), wood));
			System.out.println("Forest, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Scrambling map...50%");
		
		//Desert
		for(int i = 0; i < deserts; i++)
		{
			tiles.add(new Tile(tileType.DESERT, 7, null));
			System.out.println("Desert, " + tiles.get(tiles.size()-1).getNumber());
		}
		
		//Print percentage done
		System.out.println("Shuffling map...70%");
		
		//Shuffle the tiles
		Collections.shuffle(tiles);
		
		//Print percentage done
		System.out.println("Assigning locations...80%");
		
		//Assign locations
		for(int i = 0; i < tiles.size(); i++)
		{
			tiles.get(i).setLocation(locationX[i], locationY[i]);
		}
		
		//Print percentage done
		System.out.println("Assigning robber...95%");
		
		//Assign robber
		for(int i = 0; i < tiles.size() && robber == null; i++)
		{
			if(tiles.get(i).getType() == tileType.DESERT)
				robber = new Robber(i);
		}
		
		//Print finished
		System.out.println("Finished initializing map...100%");
		
		//Initialize map
		map = new Map(tiles, robber);
	}
	
	//Player initialization
	public static void initPlayer()
	{
		for(int i = 0; i < player.length; i++)
		{
			System.out.println("Initializing player " + (i+1));
			player[i] = new Player();
			player[i].addResource(starting);
		}
		
		//Set colors
		player[0].setColor(Color.RED);
		player[1].setColor(Color.WHITE);
		player[2].setColor(Color.YELLOW);
		player[3].setColor(Color.PINK);
	}
	
	//Initialize buildings
	public static void initBuilding()
	{
		//Road
		roadR.add(brick);
		roadR.add(wood);
		
		//Settlement
		settlementR.add(brick);
		settlementR.add(wood);
		settlementR.add(wheat);
		settlementR.add(sheep);
		
		//City
		cityR.add(wheat);
		cityR.add(wheat);
		cityR.add(stone);
		cityR.add(stone);
		cityR.add(stone);
		
		//Initializing
		city = new City(new Location(0, 0, locationType.VERTICE), cityR);
		settlement = new Settlement(new Location(0, 0, locationType.VERTICE), settlementR);
		road = new Road(new Location(0, 0, locationType.VERTICE), new Location(0, 0, locationType.VERTICE), roadR);
	}
	
	//Initialize settlement
	public static void initSettlement()
	{
		//DNE
	}
	
	//Initialization function
	public static void init()
	{
		System.out.println("Loading map...");
		
		scrambleMap();
		
		System.out.println("Initializing buildings...");
		initBuilding();
		
		System.out.println("Initializing players...");
		initPlayer();
		
		System.out.println("Getting initial setup...");
		initSettlement();
		
		System.out.println("Getting ActionListener...");
		action = new Listener();
	}
	
	//Draw function, draws everything
	public static void draw()
	{
		//Initialize frame
		frame = new JFrame("Redneck Spies of Catan V" + version);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Initialize pane using BoxLayout
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		//Action, prints actions to be carried out by player
		actionPane = new JPanel();
		
		//InfoPane
		infoPane = new JPanel();
		
		//Draws the map
		mapPane = new MapPanel(map, player);
		
		//Add panes to pane and add pane to frame
		pane.add(mapPane);
		pane.add(actionPane);
		pane.add(infoPane);
		pane.setOpaque(true);
		frame.setContentPane(pane);
		
		//Roll
		rollB = new JButton(roll);
		rollB.setMnemonic(KeyEvent.VK_R);
		rollB.setActionCommand("roll");
		rollB.addActionListener(action);
		
		//Build
		buildB = new JButton(build);
		buildB.setMnemonic(KeyEvent.VK_B);
		buildB.setActionCommand("build");
		buildB.addActionListener(action);
		
		//Trade
		tradeB = new JButton(trade);
		tradeB.setMnemonic(KeyEvent.VK_T);
		tradeB.setActionCommand("trade");
		tradeB.addActionListener(action);
		
		//End Turn
		endTurnB = new JButton(endTurn);
		endTurnB.setMnemonic(KeyEvent.VK_E);
		endTurnB.setActionCommand("end");
		endTurnB.addActionListener(action);
		
		//Add all to action pane
		actionPane.add(rollB);
		actionPane.add(buildB);
		actionPane.add(tradeB);
		actionPane.add(endTurnB);
	
		//Info, prints out all resources
		updateInfo(currentPlayer);
		
		//Pack and show frame
		frame.pack();
		frame.setSize(width, length);
		frame.setVisible(true);
		frame.setLocationRelativeTo (null); 
	}
	
	//Only draws map
	public static void drawMap()
	{
		mapPane.validate();
		mapPane.repaint();
	}
	
	//Updates infoPane for player n
	public static void updateInfo(int n)
	{
		
		JLabel info = new JLabel("Player " + (n+1) + ":");
		
		//Load brickIcon
		JLabel brickIcon = null;
		
		brickIcon = new JLabel(new ImageIcon("src\\icons\\brick.jpg", "Brick(s)"));
			
		//Load woodIcon
		JLabel woodIcon = null;
		
		woodIcon = new JLabel(new ImageIcon("src\\icons\\wood.jpg", "Wood"));
			
		//Load stoneIcon
		JLabel stoneIcon = null;

		stoneIcon = new JLabel(new ImageIcon("src\\icons\\stone.jpg", "Stone"));
			
		//Load wheatIcon
		JLabel wheatIcon = null;
		
		wheatIcon = new JLabel(new ImageIcon("src\\icons\\wheat.jpg", "Wheat"));
			
		//Load sheepIcon
		JLabel sheepIcon = null;

		sheepIcon = new JLabel(new ImageIcon("src\\icons\\sheep.jpg", "Wool"));
		
		//Clear infoPane
		infoPane.removeAll();
		
		//Add all to pane
		infoPane.add(info);
		
		infoPane.add(brickIcon);
		infoPane.add(new JLabel("" + player[n].getResourceNumber(Resource.BRICK)));
		
		infoPane.add(woodIcon);
		infoPane.add(new JLabel("" + player[n].getResourceNumber(Resource.WOOD)));
			
		infoPane.add(stoneIcon);
		infoPane.add(new JLabel("" + player[n].getResourceNumber(Resource.STONE)));
		
		infoPane.add(wheatIcon);
		infoPane.add(new JLabel("" + player[n].getResourceNumber(Resource.WHEAT)));
		
		infoPane.add(sheepIcon);
		infoPane.add(new JLabel("" + player[n].getResourceNumber(Resource.SHEEP)));
		
		//Instructions button
		JButton instructionsB = new JButton("Instructions");
		instructionsB.setActionCommand("FAQ");
		instructionsB.setMnemonic(KeyEvent.VK_S);
		instructionsB.addActionListener(action);
		
		infoPane.add(instructionsB);
		
		frame.setVisible(true);
	}
	
	//Gets a list of ResourceCards given a list of harvestable locations and the number rolled
	public static ArrayList<ResourceCard> harvest(ArrayList<Location> adjacent, int number)
	{
		ArrayList<ResourceCard> harvest = new ArrayList<ResourceCard>();
		
		//For each possible location
		for(int i = 0; i < adjacent.size(); i++)
		{
			System.out.println("Adjacent: " + adjacent.get(i).toString());
			
			//Find corresponding tile
			for(int j = 0; j < map.getTiles().size(); j++)
			{
				//Check if tile is corresponding tile, and that number of tile is equal to number rolled
				if( map.getTiles().get(j).equals(adjacent.get(i)) && map.getTiles().get(j).getNumber() == number)
				{
					//Add to harvest
					harvest.add(map.getTiles().get(j).getResource());
					
					//Print
					System.out.println("You have harvested 1 " + harvest.get(harvest.size()-1).toString());
				}
			}
		}
		
		return harvest;
	}
	
	//Collects resources for a particular player
	public static void collect(int n)
	{
		//Roll dice
		int roll = player[n].roll();
		
		System.out.println("You have rolled a " + roll); 
		
		//Cities
		for(int i = 0; i < player[n].getCities().size(); i++)
		{
			//Twice, since cities get double
			player[n].addResource(harvest(player[n].getCities().get(i).getAdjacent(), roll));
			player[n].addResource(harvest(player[n].getCities().get(i).getAdjacent(), roll));
		}
		
		//Settlements
		for(int i = 0; i < player[n].getSettlements().size(); i++)
		{
			player[n].addResource(harvest(player[n].getSettlements().get(i).getAdjacent(), roll));
		}
		
		updateInfo(n);
	}

	
	//Print out what a player has
	public static void printPossesions(Player p)
	{
		//Resources
		int[] resources = {0, 0, 0, 0, 0};
		
		for(int i = 0; i < p.getResources().size(); i++)
		{
			switch(p.getResources().get(i).getType())
			{
			case BRICK:
				resources[0]++;
				break;
			case WOOD:
				resources[1]++;
				break;
			case STONE:
				resources[2]++;
				break;
			case WHEAT:
				resources[3]++;
				break;
			case SHEEP:
				resources[4]++;
				break;
			}
		}
		
		System.out.println("You have:");
		System.out.println("\t" + resources[0] + " brick(s)");
		System.out.println("\t" + resources[1] + " wood(s)");
		System.out.println("\t" + resources[2] + " stone(s)");
		System.out.println("\t" + resources[3] + " wheat");
		System.out.println("\t" + resources[4] + " sheep");
		
		//Development cards
		
		//NO IDEA
		
		System.out.println("You have " + p.update() + " victory points.");
	}
}
