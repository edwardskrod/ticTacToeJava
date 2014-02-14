/**
 * Edward Skrod, ejs09f
 * COP3252
 * Assignment 3
 * @version 1.0, February 2014
 * @author Edward Skrod  ejs09f@my.fsu.edu  
 * 
 * PlayerHuman extends abstract class Player
 * Key differences:
 * 
 * takeTurn() instantiates a Scanner object and reads 
 * the Player input from stdin.  It performs error checking:
 * for when the board position has already been played
 * for when the number is not in the range 1-9
 *
 */
import java.util.Scanner;

public class PlayerHuman extends Player {
	
	Scanner playerInput = new Scanner(System.in);
	
	public PlayerHuman (int playerNumber) {
	
		// Constructor creates Human Player
		super(playerNumber);
		
	}
	
	public void takeTurn(TicTacToeBoard board) {
		
		int boardPosition = 0;
		
		// What does it mean for a player to take a turn?
		// It means that a player returns a position on the board.
		
		System.out.printf("%s %d, %s", "Player", this.getPlayerNumber(), "please enter a move (1 - 9): ");
		boardPosition =  playerInput.nextInt();
		System.out.println();
		
		// Error Checking
		while ((board.isPositionTaken( boardPosition ) == true) || 
				(boardPosition < 1 || boardPosition > 9 )) {
			
			if (boardPosition < 1 || boardPosition > 9 ) {
				System.out.printf("%s %d, %s", "Invalid number. Player", this.getPlayerNumber(), "please enter a move (1 - 9): ");
				
			} else {
				
				System.out.printf("%s %d, %s", "Position taken. Player", this.getPlayerNumber(),
					"please enter a move (1 - 9): ");
			}
			
			boardPosition =  playerInput.nextInt();
			System.out.println();	
			
		}
		
		// Take the turn
		board.selectPosition(boardPosition, this.getToken());	
		
		
	}  // end takeTurn()
}