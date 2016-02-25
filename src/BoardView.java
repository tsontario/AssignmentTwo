import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



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
	private JPanel[] buttonRows;

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
    	buttonRows = new JPanel[model.getSize()];
    	setLayout(new GridBagLayout());
    	createButtonRows();
    	setBackground(Color.ORANGE);
    	setOpaque(true);
    	
    	
    }

    private void createButtonRows() {
    	GridBagConstraints gbc = new GridBagConstraints();
    	
    	for (int i=0; i<buttonRows.length; i++) {
    		buttonRows[i] = new JPanel(new FlowLayout());
    		for (int j=0; j<buttonRows.length; j++) {
    			gbc.gridx = 0;
    			DotButton btn = new DotButton(i,j, model.getCurrentStatus(i, j));
    			buttonRows[i].add(btn);
    		}
    		if (i%2 == 1) {
//    			buttonRows[i].setBorder(new EmptyBorder(0,0,0,0));
    			gbc.gridx = 1;
    		}
			gbc.gridy = i;
    		add(buttonRows[i], gbc);
    	}
    	
    }
   
    
 	/**
	 * update the status of the board's DotButton instances based on the current game model
	 */

    public void update(){

	}

}
