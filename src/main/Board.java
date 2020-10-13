package main;

public class Board
{
	private char[][] board = new char[3][3];
	private int col;
	private int row;
	
	Board(String input)									//creates new Board as a 2D character array
	{
		char[] cInput = input.toCharArray();			//[[1, 2, 3],
														// [8, 0, 4],
		int c = 0;										// [7, 6, 5]]
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if (cInput[c] == '0')
				{
					col = i;
					row = j;
				}
				board[i][j] = cInput[c];
				c++;
			}
		}
	}
	
	public void moveUp()								//moves blank space up one space
	{
		if(col - 1 < 0)									//if top row, throw error
			throw new IllegalArgumentException();
		
		board[col][row] = board[col-1][row];			//value above blank space copied to blank space
		board[col-1][row] = '0';						//new blank space
		
		col--;											//coordinates are now col-1, row
	}
	
	public void moveDown()								//moves blank space down one space
	{
		if(col + 1 > 2)									//if bottom row, throw error
			throw new IllegalArgumentException();
		
		board[col][row] = board[col+1][row];			//value above blank space copied to blank space
		board[col+1][row] = '0';						//new blank space
		
		col++;											//coordinates are now col+1, row
	}
	
	public void moveLeft()								//moves blank space left one space
	{
		if(row - 1 < 0)									//if left row, throw error
			throw new IllegalArgumentException();
		
		board[col][row] = board[col][row-1];			//value above blank space copied to blank space
		board[col][row-1] = '0';						//new blank space
		
		row--;											//coordinates are now col, row-1
	}
	
	public void moveRight()								//moves blank space right one space
	{
		if(row + 1 > 2)									//if top row, throw error
			throw new IllegalArgumentException();
		
		board[col][row] = board[col][row+1];			//value above blank space copied to blank space
		board[col][row+1] = '0';						//new blank space
		
		row++;											//coordinates are now col, row+1
	}
	
	public int[] coordinates(char c)					//returns the coordinates of a value in the board
	{
		int[] coordinates = new int[2];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] == c)
				{
					coordinates[0] = i;
					coordinates[1] = j;
				}
			}
		}
		
		return coordinates;
	}
	
	public String showBoard()							//returns the board as a string
	{
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				buffer.append(board[i][j] + " ");
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	public String toString()							//returns values in board as string
	{
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				buffer.append(board[i][j]);
			}
		}
		return buffer.toString();
	}
	

}
