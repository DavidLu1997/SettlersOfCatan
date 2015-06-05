import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Building.Building;
import Player.Player;
import Card.Resource;
import Card.ResourceCard;

//TradeListener class, used to implement interface for trading
//Programmed primarily by: David Lu

public class tradeListener implements ActionListener {

	//Player
	private Player p;
	
	//ResourceCard p has
	private ResourceCard have;
	
	//ResourceCard p wants
	private ResourceCard want;
	
	//Number of resources trading with
	private int with;
	
	//Graphics
	private JFrame frame;
	private JTextField amount;
	
	//Size
	private final int width = 300;
	private final int length = 400;
	
	public tradeListener(Player p)
	{
		this.p = p;
		with = 0;
		
		have = Game.brick;
		want = Game.wood;
		
		//Initialize frame
		frame = new JFrame("Trade");
		
		//Initialize panes
		JPanel main = new JPanel();
		
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		//LEFT
		{
			//Label
			JLabel label = new JLabel("Choose which resource to trade with: ");
			
			//Brick
			JRadioButton brickLB = new JRadioButton("Brick " + "(" + p.getResourceNumber(Resource.BRICK) + ")");
			brickLB.setActionCommand("brickL");
			brickLB.addActionListener(this);
			
			//Wood
			JRadioButton woodLB = new JRadioButton("Wood " + "(" + p.getResourceNumber(Resource.WOOD) + ")");
			woodLB.setActionCommand("woodL");
			woodLB.addActionListener(this);
			
			//Stone
			JRadioButton stoneLB = new JRadioButton("Stone " + "(" + p.getResourceNumber(Resource.STONE) + ")");
			stoneLB.setActionCommand("stoneL");
			stoneLB.addActionListener(this);
			
			//Wheat
			JRadioButton wheatLB = new JRadioButton("Wheat " + "("  + p.getResourceNumber(Resource.WHEAT) + ")");
			wheatLB.setActionCommand("wheatL");
			wheatLB.addActionListener(this);
			
			//Sheep
			JRadioButton sheepLB = new JRadioButton("Sheep " + "("  + p.getResourceNumber(Resource.SHEEP) + ")");
			sheepLB.setActionCommand("sheepL");
			sheepLB.addActionListener(this);
			
			//Amount to trade
			JPanel numberPane = new JPanel();
			
			JLabel tradeAmount = new JLabel("Amount to trade with: ");
			amount = new JTextField("0", 2);
			amount.addActionListener(this);
			
			numberPane.add(tradeAmount);
			numberPane.add(amount);
			
			//Add everything to left
			left.add(label);
			left.add(brickLB);
			left.add(woodLB);
			left.add(stoneLB);
			left.add(wheatLB);
			left.add(sheepLB);
			left.add(numberPane);
		}
		
		//RIGHT
		{
			//Label
			JLabel label = new JLabel("Choose which resource to trade for: ");
			
			//Brick
			JRadioButton brickRB = new JRadioButton("Brick");
			brickRB.setActionCommand("brickR");
			brickRB.addActionListener(this);
			
			//Wood
			JRadioButton woodRB = new JRadioButton("Wood");
			woodRB.setActionCommand("woodR");
			woodRB.addActionListener(this);
			
			//Stone
			JRadioButton stoneRB = new JRadioButton("Stone");
			stoneRB.setActionCommand("stoneR");
			stoneRB.addActionListener(this);
			
			//Wheat
			JRadioButton wheatRB = new JRadioButton("Wheat");
			wheatRB.setActionCommand("wheatR");
			wheatRB.addActionListener(this);
			
			//Sheep
			JRadioButton sheepRB = new JRadioButton("Sheep");
			sheepRB.setActionCommand("sheepR");
			sheepRB.addActionListener(this);
			
			//Add everything to left
			left.add(label);
			left.add(brickRB);
			left.add(woodRB);
			left.add(stoneRB);
			left.add(wheatRB);
			left.add(sheepRB);
		}
		
		//Trade
		JButton done = new JButton("Trade");
		done.setMnemonic(KeyEvent.VK_T);
		done.setActionCommand("done");
		done.addActionListener(this);
		
		//Add to main pane
		main.add(left);
		main.add(right);
		main.add(done);
		main.setOpaque(true);
		
		//Display frame
		frame.setContentPane(main);
		frame.pack();
		frame.setSize(width, length);
		frame.setVisible(true);
		frame.setLocationRelativeTo(Game.frame);
		frame.setAlwaysOnTop(true);
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "brickL":
			have = Game.brick;
			break;
		case "woodL":
			have = Game.wood;
			break;
		case "stoneL":
			have = Game.stone;
			break;
		case "wheatL":
			have = Game.wheat;
			break;
		case "sheepL":
			have = Game.sheep;
			break;
		case "brickR":
			want = Game.brick;
			break;
		case "woodR":
			want = Game.wood;
			break;
		case "stoneR":
			want = Game.stone;
			break;
		case "wheatR":
			want = Game.wheat;
			break;
		case "sheepR":
			want = Game.sheep;
			break;
		case "done":
			if(!amount.getText().isEmpty())
			{
				with = Integer.parseInt(amount.getText());
			}
			trade();
			Game.updateInfo(Game.currentPlayer);
			frame.dispose();
			break;
		}
	}
	
	//Trading for player p
	private void trade()
	{
		ArrayList<ResourceCard> traded = new ArrayList<ResourceCard>();
		
		int numberToRemove = p.getTrade().getRatio() * (with / p.getTrade().getRatio());
		
		for(int i = 0; i < numberToRemove; i++)
		{
			traded.add(have);
			p.removeResource(have);
		}
		
		p.addResource(p.getTrade().trade(traded, want));
	}
}
