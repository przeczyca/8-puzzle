package main;

import java.util.*;

public class Node
{
	private Board board;
	private Node parent;
	private boolean hasParent;
	private ArrayList<Node> children;
	private String action;
	private int cost;
	private int totalCost;
	private int estCost;
	
	Node(Board start)										//creates a new node
	{
		board = start;
		children = new ArrayList<Node>();
	}
	
	public void setParent(Node p)							//sets the parent of the current node
	{
		parent = p;
		hasParent = true;
	}
	
	public Node getParent()									//returns the parent of the current node
	{
		return parent;
	}
	
	public boolean hasParent()								//returns true if current node has a parent
	{
		return hasParent;
	}
	
	public List<String> getChildren()						//returns all children for current node
	{
		List<String> children = new ArrayList<>();
		
		children.add(this.childUp().toString());
		children.add(this.childDown().toString());
		children.add(this.childLeft().toString());
		children.add(this.childRight().toString());
		
		return children;
	}
	
	public void addChild(Node child)						//adds the child to children list for current node
	{
		children.add(child);
	}
	
	public void setAction(String a)							//sets the action used to get to current node
	{
		action = a;
	}
	
	public String getAction()								//returns action used to get to current node
	{
		return action;
	}
	
	public void setCost()									//sets cost it took to get to current node from parent
	{
		if(hasParent)
		{
			String p = parent.board.toString();
			String c = board.toString();
			int cI = c.indexOf("0");
		
			cost = Integer.parseInt(p.substring(cI, cI+1));
		}
		
	}
	
	public int getCost()									//returns cost it took to get to current node from parent
	{
		return cost;
	}
	
	public void setTotalCost()								//sets total cost to current node from root
	{
		Node n = this;
		int total = 0;
		
		while(n.hasParent)
		{
			total = total + n.getCost();
			n = n.getParent();
		}
		
		totalCost = total;
	}
	
	public int getTotalCost()								//returns total cost to current node from root
	{
		return totalCost;
	}
	
	public void setEstCost(String h, String goal, String a)	//sets total cost to estimated cost to goal
	{
		if(h.equals("h1"))
		{
			estCost = Heuristic.heuristic1(board.toString(), goal);
		}
		else if(h.equals("h2"))
		{
			estCost = Heuristic.heuristic2(board.toString(), goal);
		}
		
		if(a.equals("aStar"))
			estCost = estCost + totalCost;
	}
	
	public int getEstCost()									//returns estimated cost to goal
	{
		return estCost;
	}
	
	public Board childUp()									//returns child board after moving up
	{
		Board newBoard = new Board(this.board.toString());	//copy of board
		
		try{
			newBoard.moveUp();								//tries to move up
		}
		catch(Exception e){
		}
		
		return newBoard;									//if it can't move up, returns copy of board
	}
	
	public Board childDown()
	{
		Board newBoard = new Board(this.board.toString());
		
		try{
			newBoard.moveDown();
		}
		catch(Exception e){
		}
		
		return newBoard;
		
	}
	
	public Board childLeft()
	{
		Board newBoard = new Board(this.board.toString());
		
		try{
			newBoard.moveLeft();
		}
		catch(Exception e){
		}
		
		return newBoard;
		
	}
	
	public Board childRight()
	{
		Board newBoard = new Board(this.board.toString());
		
		try{
			newBoard.moveRight();
		}
		catch(Exception e){
		}
		
		return newBoard;
	}
	
	public String getBoardString()							//returns values in board as string
	{
		return board.toString();
	}
	
	

}
