/**
 * Edward Skrod, ejs09f
 * COP3252
 * Assignment 3
 * @version 1.0, February 2014
 * @author Edward Skrod  ejs09f@my.fsu.edu 
 * 
 *
 */


public abstract class Player {

	private char token = ' ';
	private int playerNumber = 0;
		
	public Player (int num) 
	/**
	 * Player Constructor receives a Player Number as a parameter and constructs
	 * a Player.  Sets the token to be X for Player 1 or O for Player 2.
	 * All players, whether computer or human, have a token and a playerNumber
	 */
	{
		this.setPlayerNumber(num);
		if (this.getPlayerNumber() == 1)
			this.setToken('X'); 
		else
			this.setToken('O');
	}
	
	public void setToken(char t) {
		if (t == 'X' || t == 'O') {
			token = t;
		} else {
			throw new IllegalArgumentException("Token must be either an 'X' or an 'O'");
		}
	}
	
	public char getToken() {
		return token;
	}
	
	public void setPlayerNumber(int num) {
		if (num >=1 && num <= 2) {
			playerNumber = num;
		} else {
			throw new IllegalArgumentException("Player Number must be 1 or 2");
		}
	}
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public abstract void takeTurn(TicTacToeBoard board);
		
}
