package main;

import java.util.*;

public class Solve
{
	public void breadthFirstSearch(String input, String goal)					//finds solution using BFS algorithm
	{
        Set<String> visited = new HashSet<String>();							//set to store visited states
        Queue<Node> queue = new LinkedList<Node>();								//queue for the Nodes/states
        String[] directions = {"up", "down", "left", "right"};					//the direction the zero can move
        
        Board board = new Board(input);											//making the root node in the tree
        Node node = new Node(board);

        int time = 0;
        int space = 0;
        
        while (!node.getBoardString().equals(goal))								//while the node is not equal to the goal
        {
            visited.add(node.getBoardString());									//add the node to visited set
            List<String> children = node.getChildren();							//list of all the possible children/successors of the node
            
            int d = -1;															//keeps track of which direction to go in
            
            for (String s : children)
            {
            	d++;
                if (!visited.contains(s))										//if the child node/state has not been seen before
                {
                	visited.add(s);												//add it to visited
                    Node childNode = new Node(new Board(s));
                    node.addChild(childNode);									//add the childNode to node
                    childNode.setAction(directions[d]);							//the direction it took to reach that node/state
                    childNode.setParent(node);									//sets the parent of the child node
                    childNode.setCost();										//sets the cost to reach the child node from node
                    queue.add(childNode);										//add the child node to queue
                }
            }
            
            if(queue.size() > space)											//sets the max size of queue to space
            	space = queue.size();
            
            node = queue.poll();												//removes node form queue and makes it node
            time++;																//counts the number of nodes popped off the queue
        }
        
        List<String> solution = solution(node);									//to print the solution
        node.setTotalCost();
        
        System.out.println("length: " + solution.size());
        System.out.println("cost: " + node.getTotalCost());
        System.out.println("time: " + time);
        System.out.println("space: " + space);
        
        System.out.println("solution: " + solution);
	}

	public void depthFirstSearch(String input, String goal)						//finds solution using DFS algorithm
	{
		Set<String> visited = new HashSet<String>();
        Stack<Node> stack = new Stack<Node>();									//uses a stack instead of a queue
        String[] directions = {"up", "down", "left", "right"};
        
        Board board = new Board(input);
        
        Node node = new Node(board);

        int time = 0;
        int space = 0;
        
        while (!node.getBoardString().equals(goal))
        {
        	visited.add(node.getBoardString());
        	List<String> children = node.getChildren();
        	
        	int d = -1;
        	
        	for (String s : children)
        	{
        		d++;
                if (!visited.contains(s))
                {
                	visited.add(s);
                    Node childNode = new Node(new Board(s));
                    node.addChild(childNode);
                    childNode.setAction(directions[d]);
                    childNode.setParent(node);
                    childNode.setCost();
                    stack.add(childNode);										//adds child to the stack
                }
        	}
        	
        	if(stack.size() > space)
            	space = stack.size();
        	node = stack.pop();													//pop node off stack
        	
        	time++;
        	
        }
        
        List<String> solution = solution(node);
        node.setTotalCost();
        
        System.out.println("length: " + solution.size());
        System.out.println("cost: " + node.getTotalCost());
        System.out.println("time: " + time);
        System.out.println("space: " + space);
        
        System.out.println("solution: " + solution);
	}
	
	public void uniformCostSearch(String input, String goal)					//finds solution using UCS algorithm
	{
		Set<String> visited = new HashSet<String>();
		CompareNode compareNode = new CompareNode();							//comparator to compare nodes
		PriorityQueue<Node> queue = new PriorityQueue<Node>(16, compareNode);	//priority queue to sort nodes
        String[] directions = {"up", "down", "left", "right"};
        
        Board board = new Board(input);
        
        Node node = new Node(board);

        int time = 0;
        int space = 0;
        
        while(!node.getBoardString().equals(goal))
        {
        	visited.add(node.getBoardString());
        	List<String> children = node.getChildren();
        	
        	int d = -1;
        	
        	for (String s : children)
        	{
        		d++;
                if (!visited.contains(s))
                {
                	visited.add(s);
                    Node childNode = new Node(new Board(s));
                    node.addChild(childNode);
                    childNode.setAction(directions[d]);
                    childNode.setParent(node);
                    childNode.setCost();
                    childNode.setTotalCost();									//sets the total cost to reach the child node
                    queue.add(childNode);
                }
        	}
        	if(queue.size() > space)
        		space = queue.size();
        	
        	node = queue.poll();
        	
        	time++;
        }
        
        List<String> solution = solution(node);
        node.setTotalCost();
        
        System.out.println("length: " + solution.size());
        System.out.println("cost: " + node.getTotalCost());
        System.out.println("time: " + time);
        System.out.println("space: " + space);
        
        System.out.println("solution: " + solution);
	}
	
	public void bestFirstSearch(String input, String goal, String h)			//finds solution using GFS algorithm
	{
		Set<String> visited = new HashSet<String>();
		CompareNodeH compareNode = new CompareNodeH();							//comparator to compare nodes based on the estimated costs
		PriorityQueue<Node> queue = new PriorityQueue<Node>(16, compareNode);
		String[] directions = {"up", "down", "left", "right"};
		
		Board board = new Board(input);
		Node node = new Node(board);
		
		int time = 0;
		int space = 0;
		
		while(!node.getBoardString().equals(goal))
		{
			visited.add(node.getBoardString());
			List<String> children = node.getChildren();
			int d = -1;
			
			for(String s: children)
			{
				d++;
				if(!visited.contains(s))
				{
					visited.add(s);
					Node childNode = new Node(new Board(s));
					node.addChild(childNode);
					childNode.setAction(directions[d]);
					childNode.setParent(node);
					childNode.setCost();
					childNode.setTotalCost();
					childNode.setEstCost(h, goal, "");							//sets an estimated cost given a heuristic
					queue.add(childNode);
				}
			}
			if(queue.size()>space)
				space = queue.size();
			
			node = queue.poll();
			
			time++;
		}
		List<String> solution = solution(node);
		node.setTotalCost();
		
		System.out.println("length: " + solution.size());
		System.out.println("cost: " + node.getTotalCost());
		System.out.println("time: " + time);
		System.out.println("space: " + space);
		System.out.println("solution: " + solution);
		
	}
	
	public void aStar(String input, String goal, String h)						//finds solution using A* algorithm
	{
		Set<String> visited = new HashSet<String>();
		CompareNodeH nodeCompare = new CompareNodeH();
		PriorityQueue<Node> queue = new PriorityQueue<Node>(16, nodeCompare);
		String[] directions = {"up", "down", "left", "right"};
		
		Board board = new Board(input);
		Node node = new Node(board);
		
		int time = 0;
		int space = 0;
		
		while(!node.getBoardString().equals(goal))
		{
			visited.add(node.getBoardString());
			List<String> children = node.getChildren();
			int d = -1;
			
			for(String s: children)
			{
				d++;
				if(visited.contains(s))
					continue;
				visited.add(s);
				Node childNode = new Node(new Board(s));
				node.addChild(childNode);
				childNode.setAction(directions[d]);
				childNode.setParent(node);
				childNode.setCost();
				childNode.setTotalCost();
				childNode.setEstCost(h, goal, "aStar");							//sets estimated cost given a heuristic and it knows it's an A* algorithm
				queue.add(childNode);
			}
			if(queue.size()>space)
				space = queue.size();
			
			node = queue.poll();
			
			time++;
		}
		List<String> solution = solution(node);
		node.setTotalCost();
		
		System.out.println("length: " + solution.size());
		System.out.println("cost: " + node.getTotalCost());
		System.out.println("time: " + time);
		System.out.println("space: " + space);
		System.out.println("solution: " + solution);
	}
	
	public List<String> solution(Node node) {									//returns a list of moves required to solve the 8-puzzle
		Stack<String> stack = new Stack<String>();
		List<String> actions = new LinkedList<String>();
		
		while(node.hasParent())
		{
			stack.push(node.getAction());
			node = node.getParent();
		}
		
		while(!stack.isEmpty())
		{
			actions.add(stack.pop());
		}
		
		return actions;
	}

}
