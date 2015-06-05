package Card;

import javax.swing.*;

//ResourceCard class, represents Resource Cards
//Programmed primarily by: Sayan Faraz
public class ResourceCard extends Card {

	//Type of resource
	Resource type;
	
	//Final Strings
	private final String brick = new String("blah");
	private final String sheep = new String("blah");
	private final String stone = new String("blah");
	private final String wheat = new String("blah");
	private final String wood = new String("blah");
	
	public ResourceCard(Resource type)
	{
		switch(type)
		{
		case BRICK:
			name = "Brick";
			description = brick;
			break;
		case WOOD:
			name = "Wood";
			description = sheep;
			break;
		case STONE:
			name = "Stone";
			description = stone;
			break;
		case WHEAT:
			name = "Wheat";
			description = wheat;
			break;
		case SHEEP:
			name = "Sheep";
			description = wood;
			break;
		}
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}
	
	//Equals function
	public boolean equals(ResourceCard r)
	{
		return type == r.getType();
	}
	
	//Getter for type
	public Resource getType()
	{
		return type;
	}

	@Override
	public void draw(JPanel pane, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
