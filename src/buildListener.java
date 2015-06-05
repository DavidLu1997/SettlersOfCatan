import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Building.Building;
import Building.City;
import Building.Road;
import Building.Settlement;
import Map.Location;
import Map.locationType;
import Player.Player;

//BuildListener class, used to implement interface for building buildings
//Programmed primarily by: David Lu

public class buildListener implements ActionListener {

	//Graphics
	private JFrame frame;
	
	//Player
	private Player p;
	
	//building to build
	private Building build;
	
	//Textfields
	private JTextField x;
	private JTextField y;
	
	//Location of building (a,b)
	private int a;
	private int b;
	
	//Size
	private final int width = 400;
	private final int length = 200;
	
	//Strings
	private final String settlement = new String("Settlement (Brick, Wood, Wheat, Wool)");
	private final String city = new String("City (Brick, Wood, 3 Stone, 3 Wheat, Wool)");
	private final String road = new String("Road (Brick, Wood)");
	
	//Constructor
	public buildListener(Player p)
	{
		this.p = p;
		
		//Initialize frame
		frame = new JFrame("Build");
		
		//Initialize pane
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		//Label
		JLabel label = new JLabel("Choose which building to build: ");
		pane.add(label);
		
		//Settlement
		JButton settlementB = new JButton(settlement);
		settlementB.setMnemonic(KeyEvent.VK_S);
		settlementB.setActionCommand("settlement");
		settlementB.addActionListener(this);
		
		//City
		JButton cityB = new JButton(city);
		cityB.setMnemonic(KeyEvent.VK_C);
		cityB.setActionCommand("city");
		cityB.addActionListener(this);
		
		//Road
		//JButton roadB = new JButton(road);
		//roadB.setMnemonic(KeyEvent.VK_R);
		//roadB.setActionCommand("road");
		//roadB.addActionListener(this);
		
		//Add to buttonPane
		pane.add(label);
		pane.add(settlementB);
		pane.add(cityB);
		//buttonPane.add(roadB);
		
		//Coordinate Pane
		JPanel coordPane = new JPanel();
		
		//X
		x = new JTextField("0", 2);
		coordPane.add(new JLabel("X: "));
		coordPane.add(x);
		
		//Y
		y = new JTextField("0", 2);
		coordPane.add(new JLabel("Y: "));
		coordPane.add(y);
		
		pane.add(coordPane);
		
		//Display frame
		frame.setContentPane(pane);
		frame.pack();
		frame.setSize(width, length);
		frame.setVisible(true);
		frame.setLocationRelativeTo(Game.frame);
		frame.setAlwaysOnTop(true);
	}
	
	//ActionListener
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		//Set to settlement
		case "settlement":
			this.build = new Settlement(new Location(a, b, locationType.VERTICE), Game.settlementR);
			break;
		//Set to road
		case "road":
			this.build = new Road(new Location(a, b, locationType.VERTICE), new Location(0, 0, locationType.VERTICE), Game.roadR);
			break;
		//Set to city
		case "city":
			this.build = new City(new Location(a, b, locationType.VERTICE), Game.cityR);
			break;
		}
		
		//Parse x
		if(x != null && !x.getText().isEmpty())
		{
			a = Integer.parseInt(x.getText());
		}
		
		//Parse y
		if(y != null && !y.getText().isEmpty())
		{
			b = Integer.parseInt(y.getText());
		}
		
		//Set location
		build.getLocation().setLocation(a, b);
		
		//Build and close frame
		build();
		Game.updateInfo(Game.currentPlayer);
		Game.drawMap();
		frame.dispose();
	}
	
	//Build phase for player p
	private void build()
	{
		//Build the building, checking for occupied
		if(!Game.map.check(build.getLocation()) && p.build(build))
		{
			//Add location to occupied
			Game.map.addOccupied(build.getLocation());
			
			//Print success
			System.out.println("You have built the building at " + a + ", " + b + "!");
		}
			
		else
		{
			//Print failure
			System.out.println("The building was not built at " + a + ", " + b + "!");
		}
	}
}
