package main;

import java.util.Comparator;

public class CompareNodeH implements Comparator<Node>
{
	public int compare(Node x, Node y)						//compares nodes based on their estimated cost to goal that was given by a heuristic
	{
		if(x.getEstCost() < y.getEstCost())
			return -1;
		
		else if(x.getEstCost() > y.getEstCost())
			return 1;
		
		else
			return 0;
	}
}