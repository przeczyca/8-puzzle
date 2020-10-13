package main;

public class Heuristic
{
	public static int heuristic1(String board, String goal)					//returns estimated cost as the number of tiles out of place
	{
		int estCost = 0;
		
		for(int i = 0; i < board.length(); i++)
		{
			if(board.charAt(i) != goal.charAt(i))
				estCost++;
		}
		
		return estCost;
	}
	
	public static int heuristic2(String b, String g)						//returns estimated cost as sum of Manhattan distance of each tile to goal
	{
		int estCost = 0;
		
		Board board = new Board(b);
		Board goal = new Board(g);
		
		for(int i = 0; i < 3; i++)
		{
			char c = b.charAt(i);																	//find the coordinates of a tile
			int[] boardC = board.coordinates(c);
			int[] goalC = goal.coordinates(c);
			
			estCost = estCost + Math.abs(boardC[0] - goalC[0]) + Math.abs(boardC[1] + goalC[1]);	//calculate Manhattan distance |a-c|+|c-d| given coordinates (a,b) and (c,d)
			
		}
		
		return estCost;
	}

}
