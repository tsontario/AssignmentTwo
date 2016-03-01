import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b>
 * instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class BoardView extends JPanel {

	// Data Fields
	private GameModel model;
	private GameController controller;
	private JPanel[] buttonRows;
	private DotButton[][] dotButtons;
	private int size;

	/**
	 * Constructor used for initializing the board. The action listener for the
	 * board's DotButton is the game controller
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
		dotButtons = new DotButton[size][size];
		setLayout(new GridLayout(size, 1)); // Each row of buttons is only one
											// JPanel
		createButtonRows();
		
	}

	private void createButtonRows() {

		for (int i = 0; i < buttonRows.length; i++) {
			buttonRows[i] = new JPanel(new FlowLayout());
			buttonRows[i].setBackground(Color.white);

			// Offsets Row if Row is Odd
			if (i % 2 == 1) {
				buttonRows[i].setBorder(new EmptyBorder(0, 45, 0, 0));
			}

			// Populates Button Rows
			for (int j = 0; j < buttonRows.length; j++) {

				DotButton btn = new DotButton(i, j, model.getCurrentStatus(i, j));
				btn.setBorder(new EmptyBorder(0, 0, 0, 0));

				btn.addActionListener(controller);
				buttonRows[i].add(btn);

				dotButtons[i][j] = btn;
			}

			add(buttonRows[i]);
		}

	}

	/**
	 * update the status of the board's DotButton instances based on the current
	 * game model
	 */

	public void update() {
		for (int i = 0; i < size; i++) {

			// Get all components of each JPanel row of buttons
			Component[] components = buttonRows[i].getComponents();

			// Get each button, update its status with the model, reset its
			// icon.
			for (int j = 0; j < size; j++) {
				DotButton dotBtn = (DotButton) components[j];
				dotBtn.setType(model.getCurrentStatus(i, j));
				dotBtn.update();
			}
		}
	}
}
