import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

 // ADD YOUR INSTANCE VARIABLES HERE
	private JPanel buttons;
	private JButton resetBtn;
	private JButton quitBtn;
	private BoardView boardView;
	private GameController gameController;
	private GameModel model;
 
    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
    	super("--- Dot! The Game! ---");
    	this.model = model;
    	this.gameController = gameController;
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	boardView = getBoardView();
    	
    	drawFrame();
    	pack();
    	setResizable(false);
    	setVisible(true);
    }

   /**
     * Getter method for the attribute board.
     * 
     * @return a reference to the BoardView instance
     */

    public BoardView getBoardView(){
    	return new BoardView(model, gameController);
    }
    
    private void drawFrame() {
    	
    	
    	buttons = new JPanel();
    	resetBtn = new JButton("Reset");
    	quitBtn = new JButton("Quit");
    	
    	resetBtn.addActionListener(gameController);
    	quitBtn.addActionListener(gameController);
    	
    	buttons.add(resetBtn);
    	buttons.add(quitBtn);
    	this.add(buttons, BorderLayout.SOUTH);
    	this.add(boardView, BorderLayout.CENTER);
    }

    public void update() {
    	boardView.update();
    }
}
