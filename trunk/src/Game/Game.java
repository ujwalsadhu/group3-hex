package Game;

import Players.*;
import UI.BoardView;

/**
 * This class is a controller for the game. Please keep it simple and think
 * twice before writing something. Comments are always appreciated and don't
 * forget JavaDocs too. Cheers and good luck!
 *
 */
public class Game implements Runnable {
	
	private BoardView boardView;
	private Board board;
	
	private Player[] players;
	
	/**
	 * Represents the active player, the one that has the turn.
	 */
	private int playerTurn;
	
	/**
	 * Represents the number of the turn. 
	 */
	private int turn;
	
	/**
	 * Initializes a game and sets the board. (In future AIs will be specified
	 * here too.)
	 */
	public Game() {
		board = new Board(11);
	}

	/**
	 * Check whether there is a board and AI set up if there are any and start a
	 * game.
	 */
	public void run()
	{
		players = new Player[2];
		players[0] = new HumanPlayer();
		players[0].setGame(this);
		players[0].setPlayerId(PLAYER_ONE);
		players[1] = new HumanPlayer();
		players[1].setGame(this);
		players[1].setPlayerId(PLAYER_TWO);
		
		playerTurn = PLAYER_ONE;
		turn = 0;

		this.doTurn();
	}
	
	private void doTurn() {
		Player activePlayer = this.getPlayer(playerTurn);
		
		boardView.waitingForMove(activePlayer);
		//if (turn == 1)
			//
		int[] move = activePlayer.getNextMove();
				
		board.setPiece(move[0], move[1], activePlayer.getPlayerId());
		boardView.repaint();
		
		if (checkEnd())
		{
			// We will check for the end later
		}
		
		if (playerTurn == PLAYER_ONE)
			playerTurn = PLAYER_TWO;
		else if (playerTurn == PLAYER_TWO)
			playerTurn = PLAYER_ONE;
		
		turn++;
		this.doTurn();
	}
	
	/**
	 * Checks whether there is a path that connects two sides of the board. In
	 * that case the game ends. 
	 * @return true if there are, false if not.
	 */
	private boolean checkEnd()
	{
		return false;
	}
	
	private Player getPlayer(int playerId)
	{
		for (Player player : this.players) {
			if (player.getPlayerId() == playerId) {
				return player;
			}
		}
		return null;
	}
	
	public void setBoardView(BoardView boardView)
	{
		this.boardView = boardView;
		
		this.boardView.setGame(this);
		this.boardView.setBoard(board);

	}
	
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * A constant that represents player one
	 */
	static public final int PLAYER_ONE = 1;
	
	/**
	 * A constant the represents player two; 
	 */
	static public final int PLAYER_TWO = 2;
}