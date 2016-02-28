import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	/**
	 * Constructor used for initializing the controller. It creates the game's
	 * view and the game's model instances
	 * 
	 * @param size
	 *            the size of the board on which the game will be played
	 */
	public GameController(int size) {
		this.size = size;
	}

	/**
	 * Starts the game
	 */
	public void start() {
		model = new GameModel(size);
		view = new GameView(model, this);
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
				model.select(dotBtn.getRow(), dotBtn.getColumn());
				checkConditions();
				view.update();
				
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
	
	public void checkConditions() {
		if (isEncircled()) {
			// WIN GAME
		}
		else if (dotEscaped()) {
			// LOSE GAME
		}
		else {
			moveDot();
		}
	}

	public boolean isEncircled() {
		return false;
	}
	
	public boolean dotEscaped() {
		return false;
	}
	
	public void moveDot() {
		Point blueDot = model.getCurrentDot();
		Random r = new Random();
		
		LinkedList<Point> queue = new LinkedList<Point>();
		
	}
}
