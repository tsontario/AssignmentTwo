import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
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
	private int size;

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
    	size = model.getSize();
    	controller = gameController;
    	buttonRows = new JPanel[size];
    	setLayout(new GridLayout(size,1));  // Each row of buttons is only one JPanel
    	createButtonRows();
    }

    private void createButtonRows() {
    	
    	for (int i=0; i<buttonRows.length; i++) {
    		buttonRows[i] = new JPanel(new FlowLayout());
    		buttonRows[i].setBackground(Color.white);
    		
    		// Offsets Row if Row is Odd
    		if (i%2 == 1) {
    			buttonRows[i].setBorder(new EmptyBorder(0,40,0,0));
    		}
    		
    		// Populates Button Rows
    		for (int j=0; j<buttonRows.length; j++) {
    			
    			DotButton btn = new DotButton(i,j, model.getCurrentStatus(i, j));
    			btn.setBorder(new EmptyBorder(0,0,0,0));
    			buttonRows[i].add(btn);
    		}
    		
    		add(buttonRows[i]);
    	}
    	
    }
   
    
 	/**
	 * update the status of the board's DotButton instances based on the current game model
	 */

    public void update(){

	}

}
