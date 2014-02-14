/**
 * Edward Skrod, ejs09f
 * COP3252
 * Assignment 3
 * @version 1.0, February 2014
 * @author Edward Skrod  ejs09f@my.fsu.edu 
 * 
 * PlayerComputer extends the abstract class Player
 * 
 *  Key differences:
 *  This class has a oponnentToken varible which stores the opposite toke.  
 *  This is used when checking for defensive moves in TicTacToeBoard.winningPosition(char token).
 * 
 * 
 * 	takeTurn() creates an automatic move that adheres to the following rules:
 * 1 - If a winning move is available, take it.
 * 2 - If the opponent is threatening a winning play, block it.
 *     Note that if the opponent has two winning plays, only one can be blocked).
 * 3 - If the center square is available, take it
 * 4 - Else choose randomly between any remaining squares
 * 
 *  generateRandomOpenPosition()  generates a random number between 1 - 9, checks to make sure
 *  it has not already been played, and plays it.
 */

import java.util.Random;

public class PlayerComputer extends Player {
	
	private char opponentToken;
	// Set opponentToken to check for defensive 
	// moves in TicTacToeBoard.winningPosition()

	public PlayerComputer (int playerNumber) {
		// Constructor creates a Computer Player
		super(playerNumber);
		this.setOpponentToken();
	}
	
	public void setOpponentToken() 
	/**  Set Opponent Token returns the opposite token
	 * from that of the players.  
	 * We need to know the opponent Token so we can check 
	 * the board for offensive and defensive moves
	 */
	{
		if (this.getToken() == 'X') {
			opponentToken = 'O';
		} else {
			opponentToken = 'X';
		}
	}

	public char getOpponentToken() {
		return opponentToken;
	}
	
	public void takeTurn(TicTacToeBoard board) 
	/** 
	 * 	takeTurn() creates a move that adheres to the following rules:
	 * 1 - If a winning move is available, take it.
	 * 2 - If the opponent is threatening a winning play, block it.
	 *     Note that if the opponent has two winning plays, only one can be blocked).
	 * 3 - If the center square is available, take it
	 * 4 - Else choose randomly between any remaining squares
	 */
	
	{
		// This could be made faster  by only performing the first two checks
		// after board.getTurns() > 3.  There has to be at least three moves before a 
		// fourth move can win the game
		
		// Offense.  Look for winning move and take it if available
		int boardPosition = board.winningPosition(this.getToken());
		boolean positionFound = false;
		
		if (boardPosition != 0) {
			
			positionFound = true;
			//System.out.println("Winning Position Chosen\n");  // For Testing

		}
		
		if (positionFound == false) { 
		
			// Defense.  Pass the opponent's token into TicTacToeBoard.winningPosition 
			// and check for a winning move
			
			boardPosition = board.winningPosition(this.getOpponentToken());
		
			if (boardPosition != 0) {
			
				positionFound = true;
				// System.out.println("Defensive Position Chosen\n");  // For testing

			}	
		}
			
		if (positionFound == false) { 	
			
			// Check if Center is taken.  If not, set boardPosition to Center (5)
			if (board.isCenter() == false) {
				boardPosition = 5;
			    positionFound = true;
			}
		}
		
		if (positionFound == false) {
	
			boardPosition = this.generateRandomOpenPosition(board);
			//System.out.println("Random Method Chosen\n");  // For testing
		}
		
		System.out.printf("%s %d %s %d\n\n", "Player", this.getPlayerNumber(),
				"(computer) chooses position", boardPosition);
		
			// Take the turn
		board.selectPosition(boardPosition, this.getToken());
	}

	
	public int generateRandomOpenPosition(TicTacToeBoard board) 
	/**
	 * Generates a random number between 1 - 9, then checks to make 
	 * sure the position is not taken.   If the position IS taken, 
	 * position is reset to 0 and another random number is chosen.
	 */
	
	{
		int position = 0;
		Random rnd = new Random();
		
		while (position == 0) {
			
			position = rnd.nextInt(9) + 1;
			//System.out.println("Random Number: " + position + "\n");
			switch(position) {
			
			case 1:  if (board.isPositionTaken(1) == true)
						{  position = 0;	}
					 break;
					
			case 2:	if (board.isPositionTaken(2) == true)
						{  position = 0;	}
			 		break;
					
			case 3:	if (board.isPositionTaken(3) == true)
						{  position = 0;	}
			 		break;
					
			case 4:  if (board.isPositionTaken(4) == true)
						{  position = 0;	}
			 		break;
					
			case 5:	if (board.isPositionTaken(5) == true)
						{  position = 0;	}
			 		break;
					
			case 6:	if (board.isPositionTaken(6) == true)
						{  position = 0;	}
			 		break;			
			
			case 7:  if (board.isPositionTaken(7) == true) 
						{  position = 0;	}
					break;
			
			case 8:	if (board.isPositionTaken(8) == true)
						{ position = 0; }
					break;
			
			case 9:	if (board.isPositionTaken(9) == true)
						{ position = 0;	}
					break;	
					
			default: System.out.println("Error: Random number generated outside bounds\n" +
						" in TicTacToeBoard.java, randomOpenPositions()");
					 break;
			} // end switch
		} // end while
			
		return position;
	} // end randomOpenPosition
	

}
	