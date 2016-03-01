import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;

/**
 * The class <b>GameController</b> is the controller of the game. It implements
 * the interface ActionListener to be called back when the player makes a move.
 * It computes the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameController implements ActionListener {

	// Data Fields
	private int size;
	private GameModel model;
	private GameView view;

	// Breadth-First-Search Data
	private Point[] targets; // The perimeter of the board
	private ArrayList<Point> blocked; // Selected points on baord

	/**
	 * Constructor used for initializing the controller. It creates the game's
	 * view and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {
		this.size = size;
		model = new GameModel(size);
		model.reset();
		view = new GameView(model, this);
		targets = createTargetPoints();

	}

	/**
	 * Starts the game
	 */
	public void start() {
		view.update();
		setBlockedList();
	}

	/**
	 * resets the game
	 */
	public void reset() {
		model.reset();
		view.update();
	}

	/**
	 * Callback used when the user clicks a button or one of the dots.
	 * Implements the logic of the game
	 *
	 * @param e
	 *            the ActionEvent
	 */

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof DotButton) {
			DotButton dotBtn = (DotButton) e.getSource();
			if (dotBtn.getType() == 0) {
				dotBtn.setType(1);

				int row = dotBtn.getRow();
				int col = dotBtn.getColumn();
				model.select(row, col);
				blocked.add(new Point(row, col));

				System.out.println(String.format("(%d, %d)", dotBtn.getRow(), dotBtn.getColumn()));
				// System.out.println(model.getCurrentDot()); // DEBUG BOARD
				// POSITIONS
				moveDot();
				view.update();

				for (int i = 0; i < blocked.size(); i++) {
					System.out.println(blocked.get(i));
				}

			}

		} else if (e.getSource() instanceof JButton) {
			JButton menuBtn = (JButton) e.getSource();
			if (menuBtn.getText().equals("Reset")) {
				reset();
			} else if (menuBtn.getText().equals("Quit")) {
				System.exit(0);
			}
		}
	}

	private Point[] createTargetPoints() {
		Point[] perimeter = new Point[(size - 1) * 4]; // Number of points on
														// perimeter of board of
														// any given size
		int index = 0; // Helper value to keep track of array

		// Generate array of all border Points.
		for (int i = 0; i < size; i++) {
			perimeter[index] = new Point(0, index);
			index++;
		}
		for (int i = 0; i < size; i++) {
			perimeter[index] = new Point(size - 1, i);
			index++;
		}

		for (int i = 1; i < size - 1; i++) {
			perimeter[index] = new Point(i, 0);
			index++;
		}

		for (int i = 1; i < size - 1; i++) {
			perimeter[index] = new Point(i, size - 1);
			index++;
		}

		// DEBUG
		// for (int i=0; i<perimeter.length; i++) {
		// System.out.println(perimeter[i]);
		// }
		return perimeter;
	}

	public void moveDot() {
		Point blueDot = model.getCurrentDot();
		// shortestPath(blueDot, targets, blocked);
		Random r = new Random();
		if (blueDot.getX() % 2 == 0) {
			switch (r.nextInt(6)) {
			case 0:
				model.setCurrentDot(blueDot.getX() + 1, blueDot.getY());
				break;
			case 1:
				model.setCurrentDot(blueDot.getX() + 1, blueDot.getY() - 1);
				break;
			case 2:
				model.setCurrentDot(blueDot.getX(), blueDot.getY() + 1);
				break;
			case 3:
				model.setCurrentDot(blueDot.getX(), blueDot.getY() - 1);
				break;
			case 4:
				model.setCurrentDot(blueDot.getX() - 1, blueDot.getY() - 1);
				break;
			case 5:
				model.setCurrentDot(blueDot.getX() - 1, blueDot.getY());
				break;
			}
		}
		else {
			switch (r.nextInt(6)) {
			case 0:
				model.setCurrentDot(blueDot.getX() + 1, blueDot.getY());
				break;
			case 1:
				model.setCurrentDot(blueDot.getX() + 1, blueDot.getY() + 1);
				break;
			case 2:
				model.setCurrentDot(blueDot.getX(), blueDot.getY() - 1);
				break;
			case 3:
				model.setCurrentDot(blueDot.getX(), blueDot.getY() + 1);
				break;
			case 4:
				model.setCurrentDot(blueDot.getX() - 1, blueDot.getY() + 1);
				break;
			case 5:
				model.setCurrentDot(blueDot.getX() - 1, blueDot.getY());
				break;
			}
		}
	}

	private void shortestPath(Point start, Point[] targets, ArrayList<Point> blocked) {
		LinkedList<Point> queue = new LinkedList<Point>();

		queue.addLast(start);

		while (!queue.isEmpty()) {
			Point c = queue.removeFirst();
			Point[] neighbours = getNeighbours(c);
			for (Point p : neighbours) {
				if (blocked.indexOf(p) == -1) {

				}
			}
		}
	}

	private Point[] getNeighbours(Point c) {

		Point[] neighbours = new Point[6];
		if (c.getX() % 2 == 0) {
			neighbours[0] = new Point(c.getX(), c.getY() + 1);
			neighbours[1] = new Point(c.getX(), c.getY() - 1);
			neighbours[2] = new Point(c.getX() + 1, c.getY());
			neighbours[3] = new Point(c.getX() + 1, c.getY() - 1);
			neighbours[4] = new Point(c.getX() - 1, c.getY());
			neighbours[5] = new Point(c.getX() - 1, c.getY() - 1);
		} else if (c.getX() % 2 == 1) {
			neighbours[0] = new Point(c.getX(), c.getY() + 1);
			neighbours[1] = new Point(c.getX(), c.getY() - 1);
			neighbours[2] = new Point(c.getX() + 1, c.getY());
			neighbours[3] = new Point(c.getX() + 1, c.getY() + 1);
			neighbours[4] = new Point(c.getX() - 1, c.getY());
			neighbours[5] = new Point(c.getX() - 1, c.getY() + 1);
		}
		return new Point[0];
	}

	private void setBlockedList() {
		blocked = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (model.getCurrentStatus(i, j) == 1) {
					blocked.add(new Point(i, j));
				}
			}
		}
	}
}
