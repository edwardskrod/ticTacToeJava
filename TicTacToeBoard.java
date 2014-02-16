/**
 * Edward Skrod, ejs09f
 * @version 1.0, February 2014
 * @author Edward Skrod  ejs09f@my.fsu.edu 
 * 
 * 
 * Class TicTacToeBoard is the blueprint for a tic-tac-toe board of 3 X 3 using
 * a two dimensional character array to store the characters, ' ', 
 * 'X' and 'O'
 * 
 *  
 *  selectPosition():  puts an X or O in the board position 
 * 		If the position has already been taken, the function prints an error
 * 
 * checkForWinner(): looking for three Xs or Os in a row, 
 * 		column or diagonal and returns true if found
 * 		We could speed this function up by passing the character token of the
 * 		Player that just went. 
 * 
 * This class also provides functions for the PlayerComputer
 *  to access, such as winningPosition(), availableCenter()
 *  and randomOpenPosition()
 *  
 *  winningPosition(char ch): returns the Board Position of the square needed to win
 *  for offensive and defensive purposes.  For offense, PlayerComputer should pass in
 *  it's own Player token (either 'X' or 'O').  For defense, PlayerComputer should pass
 *  in the other Player's token.
 *  
 *   availableCenter(): returns the Board position of the center tile (5) if it 
 *   has not been been taken
 *   
 *   randomOpenPosition(): returns a number 1-9 of a tile that has not been taken using
 *   a simple random number generator.
 *
 */

public class TicTacToeBoard {

	// Final Variables
	private final int ROWS = 3;
	private final int COLS = 3;
	private final String divider = "-----------";
	
	// Instance Variables
	private int turns = 1;	// Tracks the number of turns taken
	private boolean gameWon = false;

	//  Stores the X and O characters
	private char [][] board = new char[ROWS] [COLS]; 	 
	
	// Parallel char array to board[][].  Stores a boolean value for each position
	private boolean [][] positionUsed = new boolean [ROWS][COLS]; 
	
	// Constructor
	public TicTacToeBoard (  )
	{
		// Constructor to instantiate a new board
		this.clearBoard();
	}
	
	public void clearBoard() {
		// Populate the boar with null characters
		// Populate the positionUsed with false
		for (int row  = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				board[row][col] = ' ';
				positionUsed[row][col] = false;
			}
		}
	}
	
	// Getters
	public int getTurns() {
		return turns;
	}
	
	public void incrementTurns() {
		++turns;
	}
	
	public boolean isGameWon() 
	// Returns true if game has been won
	{
		return gameWon;
	}
	
	private void setPosition(int row, int col, char playerToken)
	// Function used by selectPosition()
	// Increments turn
	{
		
		this.board[row][col] = playerToken;
		this.positionUsed[row][col] = true;
		this.incrementTurns();
	}		
	
	public void selectPosition(int position, char playerToken) 
	{
		// Select the position
		switch (position){
		
			case 1:		this.setPosition(0,0, playerToken);
						break;
						
			case 2:		this.setPosition(0,1, playerToken);
						break;
				
			case 3:  	this.setPosition(0,2, playerToken);
						break;		
						
			case 4:		this.setPosition(1,0, playerToken);
						break;
			
			case 5: 	this.setPosition(1,1, playerToken);
						break;
			
			case 6: 	this.setPosition(1,2, playerToken);
						break;							
						
			case 7:		this.setPosition(2,0, playerToken);
						break;
			
			case 8:		this.setPosition(2,1, playerToken);
						break;
			
			case 9: 	this.setPosition(2,2, playerToken);
						break;						
						
			default: 	error("Error: TicTacToeBoard.selectPosition()\nPosition selected out of range 1 - 9.");
						System.out.println("Position selected: " + position + "\n");
						break;
			
						
		}  // end switch
	}
	
	public boolean isPositionTaken(int position) 
	/**
	 * Returns true if position has been taken
	 */
	{
		switch(position) {
		
		case 1:  if (this.positionUsed[0][0] == true) {
					return true;
				}
				break;
				
		case 2:	if (this.positionUsed[0][1] == true) {
					return true;
				}
				break;
				
		case 3:	if (this.positionUsed[0][2] == true) {
					return true;
				}
				break;
				
		case 4:  if (this.positionUsed[1][0] == true) {
					return true;
				}
				break;
				
		case 5:	if (this.positionUsed[1][1] == true) {
					return true;
				}
				break;
				
		case 6:	if (this.positionUsed[1][2] == true) {
					return true;
				}
				break;			
		
		case 7:  if (this.positionUsed[2][0] == true) {
					return true;
				}
				break;
		
		case 8:	if (this.positionUsed[2][1] == true) {
					return true;
				}
				break;
		
		case 9:	if (this.positionUsed[2][2] == true) {
					return true;
				}
				break;	
				
		default: break;
		} // end switch
		
		return false;		
		
	}

		
	public boolean isGameOver() 
	/** Two ways for a Game to be over
	 * 
	 * 1 - reached 9 total turns.
	 * 2 - A Player won the game
	 * No need to check for GameOver() prior to the 5th turn.
	 */
	{
		if (this.getTurns() < 5) {
			//System.out.println("No need to check if Game is over:  Turn " + this.getTurns());
			return false;
		}
		else {
			//System.out.println("Checking for Game Over: Turn: " + this.getTurns());
			return this.isGameOver(this.getTurns());
		}

	}
	
	private boolean isGameOver(int turn) 
	{
		if (checkForWinner(turn) == true)	
			return true;
		else if (this.getTurns() > 9) {
			return true;
		} else 
			return false;
	}
	
	private boolean checkForWinner(int turn)
	/**
	 * This function checks for a winner by looking for three tokens
	 *  in a row, column or diagonal and returns true if found.
	 *  It is the user's responsibility to specify which token (X or O) it is looking for.
	 *  The function checks for a winner after each turn has been completed and therefore only needs
	 *  to check for a certain token.
	 */
	{
		char token;

		if (turn % 2 == 0) {
			token = 'X';
		} else {
			token = 'O';
		}
		
		//System.out.println("Checking for three " + token + "'s"); // For testing
		// Player n Wins
	     /* -----------------------  CHECK ROWS ------------------------ */
		
			if ((this.board[0][0] == token) && (this.board[0][1] == token)
					&& (this.board[0][2] == token))
			{
				gameWon = true;
			}
			else if ((this.board[1][0] == token) && (this.board[1][1] == token)
			        && (this.board[1][2] == token))
			{
				gameWon = true;
			}
			else if ((this.board[2][0] == token) && (this.board[2][1] == token)
			                     && (this.board[2][2] == token))
			{
				gameWon = true;
			}
			
		/*--------------------------CHECK COLUMNS--------------------------*/
			else if ((this.board[0][0] == token) && (this.board[1][0] == token)
			        && (this.board[2][0] == token))
			{
				gameWon = true;
			}
			else if ((this.board[0][1] == token) && (this.board[1][1] == token)
			        && (this.board[2][1] == token))
			{
				gameWon = true;
			}
			else if ((this.board[0][2] == token) && (this.board[1][2] == token)
			                     && (this.board[2][2] == token))
			{
				gameWon = true;
			}
			
			/*-------------------------CHECK DIAGONALS-------------------------*/
			else if ((this.board[0][0] == token) && (this.board[1][1] == token)
			                     && (this.board[2][2] == token))
			{
				gameWon = true;
			}
			else if ((this.board[2][0] == token) && (this.board[1][1] == token)
			                     && (this.board[0][2] == token))
			{
				gameWon = true;
			}
		
			// No Winner Yet
		    else {
		        gameWon = false;		
		    }
			
		return gameWon;
	}
	
	public boolean isCenter() 
	/**
	 * Return true if  Center is taken
	 * 
	 */	
	{
		
		if (this.isPositionTaken(5) == true) {
			return true;
		}
		else
			return false;
	} // end isCenter()
	
	public boolean isCorner() 
	/**
	 * Return true if one of the corners is taken
	 * 
	 */
	{

		if (this.isPositionTaken(1) == true) {
			return true;
		} else if (this.isPositionTaken(3) == true) {
			return true;
		} else if (this.isPositionTaken(7) == true) {
			return true;
		} else if (this.isPositionTaken(9) == true) {
			return true;
		} else {
			return false;
		}
	} // end isCorner
	
	public boolean isEdge() 
	/**
	 * Return true if one of the edges is taken
	 * 
	 */
	{
		if (this.isPositionTaken(2) == false) {
			return true;
		} else if (this.isPositionTaken(4) == false) {
			return true;
		} else if (this.isPositionTaken(6) == false) {
			return true;
		} else if (this.isPositionTaken(8) == false) {
			return true;
		} else {
			return false;
		}
	} // end isEdge()
	
	
	public int winningPosition (char token)
	// Determines whether there is a winningPosition on the board.
	// A winning position is defined as the third in two consecutive positions
	// either horizontally, vertically or diagonally.
	{
		
		/*--------------------------CHECK ROWS------------------------------*/
	    
	    // Check Row 1, Column 1 and 2 for Xs.
	     if ((this.board[0][0] == token)               // Position 0,0 is X
	        && (this.board[0][1] == token)            // Position 0,1 is X
	        && (this.positionUsed[0][2] == false))     // Position 0,2 has not been used
	    {
	        return 3;
	    }
	    
	    // Check Row 2, Column 1 and 2 for Xs.
	    else if ((this.board[1][0] == token)
	             && (this.board[1][1] == token)
	             && (this.positionUsed[1][2] == false))
	    {
	        return 6;
	    }
	    
	    // Check Row 3, Column 1 and 2 for Xs.
	    // Put O in Row 3, Col. 3
	    else if ((this.board[2][0] == token)
	             && (this.board[2][1] == token)
	             && (this.positionUsed[2][2] == false))
	    {
	       return 9;
	    }
	    
	    // Check Row 1, Column 2 and 3
	    // Put O in Row 1, Column 1
	    else if ((this.board[0][1] == token)
	             && (this.board[0][2] == token)
	             && (this.positionUsed[0][0] == false))
	    {
		       return 1;

	    }
	    
	    // Check Row 2, Column 2 and 3
	    // Put O in Row 2, Column 1
	    else if ((this.board[1][1] == token)
	             && (this.board[1][2] == token)
	             && (this.positionUsed[1][0] == false))
	    {
		       return 4;

	    }
	    
	    // Check Row 3, Column 2 and 3
	    // Put O in Row 3, Column 1
	    else if ((this.board[2][1] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[2][0] == false))
	    {
	        return 7;
	    }
	    
	    // Check Row 1, Column 1 and 3
	    // Put O in Row 1, Column 2
	    else if ((this.board[0][0] == token)
	             && (this.board[0][2] == token)
	             && (this.positionUsed[0][1] == false))
	    {
	        return 2;

	    }
	    
	    // Check Row 2, Column 1 and 3
	    // Put O in Row 2, Column 2
	    // This check is redundant given else statement below
	    else if ((this.board[1][0] == token)
	             && (this.board[1][2] == token)
	             && (this.positionUsed[1][1] == false))
	    {
	       return 5;
	    }
	    
	    // Check Row 3, Column 1 and 3
	    // Put O in Row 3, Column 2
	    else if ((this.board[2][0] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[2][1] == false))
	    {
	        return 8;
	    }
	    
	    /*--------------------------CHECK COLUMNS--------------------------*/
	    // Check Column 1, Rows 1 and 2
	    // Put O in Column 1, Row 3
	    else if ((this.board[0][0] == token)
	             && (this.board[1][0] == token)
	             && (this.positionUsed[2][0] == false))
	    {
	        return 7;
	    }
	    
	    // Check Column 2, Rows 1 and 2
	    // Put O in Column 2, Row 3
	    else if ((this.board[0][1] == token)
	             && (this.board[1][1] == token)
	             && (this.positionUsed[2][1] == false))
	    {
	        return 8;
	    }
	    
	    // Check Column 3, Rows 1 and 2
	    // Put O in Column 3, Row 3
	    else if ((this.board[0][2] == token)
	             && (this.board[1][2] == token)
	             && (this.positionUsed[2][2] == false))
	    {
	        return 9;
	    }
	    
	    // Check Column 1, Rows 2 and 3
	    // Put O in Column 1, Row 1
	    else if ((this.board[1][0] == token)
	             && (this.board[2][0] == token)
	             && (this.positionUsed[0][0] == false))
	    {
	        return 1;
	    }
	    
	    // Check Column 2, Rows 2 and 3
	    // Put O in Column 2, Row 1
	    else if ((this.board[1][1] == token)
	             && (this.board[2][1] == token)
	             && (this.positionUsed[0][1] == false))
	    {
	        return 2;
	    }
	    
	    // Check Column 3, Rows 2 and 3
	    // Put O in Column 3, Row 1
	    else if ((this.board[1][2] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[0][2] == false))
	    {
	        return 3;
	    }
	    
	    // Check Column 1, Rows 1 and 3
	    // Put O in Column 1, Row 2
	    else if ((this.board[0][0] == token)
	             && (this.board[2][0] == token)
	             && (this.positionUsed[1][0] == false))
	    {
	        return 4;
	    }
	    
	    // Check Column 2, Rows 1 and 3
	    // Put O in Column 2, Row 2
	    // Redundant due to else statement below
	    else if ((this.board[0][1] == token)
	             && (this.board[2][1] == token)
	             && (this.positionUsed[1][1] == false))
	    {
	        return 5;
	    }
	    
	    // Check Column 3, Rows 1 and 3
	    // Put O in Column 3, Row 2
	    else if ((this.board[0][2] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[1][2] == false))
	    {
	        return 6;
	    }
	    
	    /*-------------------------CHECK DIAGONALS-------------------------*/
	    // Check Row 1, Column 1 & Row 2, Column 2
	    // Put O in Row 3, Column 3
	    else if ((this.board[0][0] == token)
	             && (this.board[1][1] == token)
	             && (this.positionUsed[2][2] == false))
	    {
	        return 9;
	    }
	    
	    // Check Row 2, Column 2 & Row 3, Column 3
	    // Put O in Row 1, Column 1
	    else if ((this.board[1][1] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[0][0] == false))
	    {
	        return 1;
	    }
	    
	    // Check Row 1, Column 1 & Row 3, Column 3
	    // Put O in Row 2, Column 2
	    else if ((this.board[0][0] == token)
	             && (this.board[2][2] == token)
	             && (this.positionUsed[1][1] == false))
	    {
	        return 5;
	    }
	    
	    // Check Row 1, Column 3 & Row 2, Column 2
	    // Put O in Row 3, Column 1
	    else if ((this.board[0][2] == token)
	             && (this.board[1][1] == token)
	             && (this.positionUsed[2][0] == false))
	    {
	        return 7;
	    }
	    
	    // Check Row 3, Column 1 & Row 2, Column 2
	    // Put O in Row 1, Column 3
	    else if ((this.board[2][0] == token)
	             && (this.board[1][1] == token)
	             && (this.positionUsed[0][2] == false))
	    {
	        return 3;
	    }
		
	    else return 0;
	}
	
	public void printBoard ()
	/**   printBoard - Game Board and Positions
	 *   Game Board:                Positions:
   	 *		|   |                    1 | 2 | 3 
	 *	 -----------                -----------
     *	    |   |                    4 | 5 | 6 
	 *	 -----------                -----------
   	 *		|   |                    7 | 8 | 9 
	 */
	{
		
		System.out.printf("%s\t\t\t%s", "Game Board:", "Positions:\n");
		System.out.println();
		System.out.printf(" %c | %c | %c \t\t\t %c | %c | %c\n",
				this.board[0][0],this.board[0][1],this.board[0][2],'1','2','3' );
		System.out.printf("%s\t\t\t%s\n", divider, divider);
		System.out.printf(" %c | %c | %c \t\t\t %c | %c | %c\n",
				this.board[1][0],this.board[1][1],this.board[1][2],'4','5','6' );
		System.out.printf("%s\t\t\t%s\n", divider, divider);
		System.out.printf(" %c | %c | %c \t\t\t %c | %c | %c\n\n",
				this.board[2][0],this.board[2][1],this.board[2][2],'7','8','9' );		
		
	}
	
	private static void error(String err) {
		System.err.printf("%s\n\n", err);
	}
	
	
}
