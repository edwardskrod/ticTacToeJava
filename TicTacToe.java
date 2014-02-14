/**
 * Edward Skrod, ejs09f
 * COP3252
 * Assignment 3
 * February 14, 2014
 * Tic-Tac-Toe
 * 
 * Task:  
 * Write a Tic-Tac-Toe game, which will be played and
 * drawn with text characters on the console. This assignment
 * is based on exercise 8.19, but the specifications here will
 * be more specific. 
 * 
 * 
 * @version 1.0, February 2014
 * @author Edward Skrod  ejs09f@my.fsu.edu 
 * 
 *
 */

public class TicTacToe {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		boolean commandLineError = false;
		
		// Create a new board
		TicTacToeBoard theBoard = new TicTacToeBoard();
		
		// Create an array of two players
		Player [] players = new Player [2];

		//For Testing
		//players[0] = new PlayerHuman(1);
		//players[0] = new PlayerComputer(1);
		//players[1] = new PlayerComputer(2);
		
		
		// COMMAND LINE ARGUMENTS
		if (args.length == 0) {
	
			players[0] = new PlayerHuman(1);
			players[1] = new PlayerHuman(2);
		}
		else if ((args.length == 1) && (args[0].equals("-c"))) {
		
			players[0] = new PlayerComputer(1);
			players[1] = new PlayerComputer(2);
		} 
		else if ((args.length == 2) && (args[0].equals("-c"))) {
			
			if (args[1].equals("1")) {
				players[0] = new PlayerComputer(1);
				players[1] = new PlayerHuman(2);
				
			}
			else if (args[1].equals("2")) {
				players[0] = new PlayerHuman(1);
				players[1] = new PlayerComputer(2);
			}
		} 
		else {
			System.err.println("Usage:  java TicTacToe [-c [1|2]]");
			commandLineError = true;
		}
	
		// Run the program if there were no command line errors
		
		if (commandLineError == false) {
			
			int currentPlayer = 1;

			// Print the initial board
			theBoard.printBoard();
	
			while (theBoard.isGameOver() != true) {
				
				//System.out.println("Move number: " + theBoard.getTurns()); // For Testing

				currentPlayer = (currentPlayer + 1) % players.length;		
	
				players[currentPlayer].takeTurn(theBoard);

				theBoard.printBoard();
				
			}
			
			if (theBoard.isGameWon() == true) {
				
				System.out.printf("%s %d has won!\n\n", "Game Over! Player", (currentPlayer + 1 ));
				
			} else {
				
				System.out.println("The game resulted in a tie!\n\n");
			}
		} // end if(commandLineError == false)
		
		
		System.out.println("Exiting TicTacToe.\n");
		
	} // end public static void main(String[] args)

} // end TicTacToe.java
