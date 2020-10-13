package main;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		System.out.println("8-Puzzle Solver");
		Scanner inputS = new Scanner(System.in);
		
		System.out.println("Enter order of numbers: \n");		//ex: "123456780"
		String input = inputS.nextLine();
		inputS.close();
		
		Board start = new Board(input);
		System.out.println("Starting position:");
		System.out.println(start.showBoard());
		
		Solve solve = new Solve();
		System.out.println("breadth first search:");
		solve.breadthFirstSearch(input, "123804765");
		System.out.println();
		
		System.out.println("depth first search:");
		solve.depthFirstSearch(input, "123804765");
		System.out.println();
		
		System.out.println("uniform cost seaerch:");
		solve.uniformCostSearch(input, "123804765");
		System.out.println();
		
		System.out.println("best first search:");
		solve.bestFirstSearch(input, "123804765", "h1");
		System.out.println();
		
		System.out.println("A* search heuristic 1:");
		solve.aStar(input, "123804765", "h1");
		System.out.println();
		
		System.out.println("A* search heuristic 2:");
		solve.aStar(input, "123804765", "h2");
		System.out.println();

	}

}
