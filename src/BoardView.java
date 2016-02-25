import javax.swing.JPanel;



/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b> instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class BoardView extends JPanel {

     // Data Fields
	private GameModel model;
	private GameController controller;


	/**
     * Constructor used for initializing the board. The action listener for
     * the board's DotButton is the game controller
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public BoardView(GameModel gameModel, GameController gameController) {
    	model = gameModel;
    	controller = gameController;
    }

 	/**
	 * update the status of the board's DotButton instances based on the current game model
	 */

    public void update(){

	}

}
