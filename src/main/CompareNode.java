package main;

import java.util.*;

public class CompareNode implements Comparator<Node>
{
	public int compare(Node x, Node y)						//compares nodes based on total cost to get to node form root
	{
		if(x.getTotalCost() < y.getTotalCost())
			return -1;
		
		else if(x.getTotalCost() > y.getTotalCost())
			return 1;
		
		else
			return 0;
	}

}