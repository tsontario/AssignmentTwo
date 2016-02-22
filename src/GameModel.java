import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. It
 * stores the followiung information: - the current location of the blue dot -
 * the state of all the dots on the board (available, selected or occupied by
 * the blue dot - the size of the board - the number of steps since the last
 * reset
 * 
 * The model provides all of this informations to the other classes trough
 * appropriate Getters. The controller can also update the model through
 * Setters. Finally, the model is also in charge of initializing the game
 * 
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {

	/**
	 * predefined values to capture the state of a point
	 */
	public static final int AVAILABLE = 0;
	public static final int SELECTED = 1;
	public static final int DOT = 2;

	public static final double PROBABILITY = 10;

	private static int size;

	// Data Fields
	private int[][] board;
	private int numSteps;
	private Point dotPosition;
	private Random rand;

	/**
	 * Constructor to initialize the model to a given size of board.
	 * 
	 * @param size
	 *            the size of the board
	 */
	public GameModel(int size) {
		// Initialize variables
		rand = new Random();
		numSteps = 0;
		GameModel.size = size;
		board = new int[size][size];
		
		// Position Dots
		reset();
		
	}

	/**
	 * Resets the model to (re)start a game. The previous game (if there is one)
	 * is cleared up . The blue dot is positioned as per instructions, and each
	 * dot of the board is either AVAILABLE, or SELECTED (with a probability
	 * 1/INITIAL_PROBA). The number of steps is reset.
	 */
	public void reset() {
		// Set Blue Dot
		int randX;
		int randY;
		if (size % 2 == 0) {
			randX = rand.nextInt(2) + (size / 2) - 1;
			randY = rand.nextInt(2) + (size / 2) - 1;
		} else {
			randX = rand.nextInt(3) + (size / 2) - 1;
			randY = rand.nextInt(3) + (size / 2) - 1;
		}
		setCurrentDot(randX, randY);

		// Set Selected Dots
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] != DOT) {
					if (rand.nextDouble() < 1/PROBABILITY) {
						board[i][j] = SELECTED;
					}
					else {
						board[i][j] = AVAILABLE;
					}
				}
			}
		}
	}

	/**
	 * Getter <b>class</b> method for the size of the game
	 * 
	 * @return the value of the attribute size
	 */
	public static int getSize() {
		return size;
	}

	/**
	 * returns the current status (AVAILABLE, SELECTED or DOT) of a given dot in
	 * the game
	 * 
	 * @param i
	 *            the x coordinate of the dot
	 * @param j
	 *            the y coordinate of the dot
	 * @return the status of the dot at location (i,j)
	 */
	public int getCurrentStatus(int i, int j) {
		return board[i][j];
	}

	/**
	 * Sets the status of the dot at coordinate (i,j) to SELECTED, and increases
	 * the number of steps by one
	 * 
	 * @param i
	 *            the x coordinate of the dot
	 * @param j
	 *            the y coordinate of the dot
	 */
	public void select(int i, int j) {
		numSteps++;
		board[i][j] = SELECTED;
	}

	/**
	 * Puts the blue dot at coordinate (i,j). Clears the previous location of
	 * the blue dot. If the i coordinate is "-1", it means that the blue dot
	 * exits the board (the player lost)
	 * 
	 * @param i
	 *            the new x coordinate of the blue dot
	 * @param j
	 *            the new y coordinate of the blue dot
	 */
	public void setCurrentDot(int i, int j) {
		if (dotPosition != null) {
			board[dotPosition.getX()][dotPosition.getY()] = AVAILABLE;
		}
		board[i][j] = DOT;
		dotPosition = new Point(i, j);
	}

	/**
	 * Getter method for the current blue dot
	 * 
	 * @return the location of the curent blue dot
	 */
	public Point getCurrentDot() {
		return dotPosition;
	}

	/**
	 * Getter method for the current number of steps
	 * 
	 * @return the current number of steps
	 */
	public int getNumberOfSteps() {
		return numSteps;
	}

	public static void main(String[] args) {
		GameModel model = new GameModel(8);
	}
}
