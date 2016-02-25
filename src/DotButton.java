import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * In the application <b>Circle the dot</b>, a <b>DotButton</b> is a specialized type of
 * <b>JButton</b> that represents a dot in the game. It uses different icons to
 * visually reflect its state: a blue icon if the blue dot is currently on this location
 * an orange icon is the dot has been selected and a grey icon otherwise.
 * 
 * The icon images are stored in a subdirectory ``data''. They are:
 * data/ball-0.png => grey icon
 * data/ball-1.png => orange icon
 * data/ball-2.png => blue icon
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {

	private final int AVAILABLE = 0;
	private final int SELECTED = 1;
	private final int DOT = 2;
	
	private static final ImageIcon[] icons = new ImageIcon[3];
	
    // ADD YOUR INSTANCE VARIABLES HERE
	private int row;
	private int column;
	private int type;
	private boolean selected;

    /**
     * Constructor used for initializing a cell of a specified type.
     * 
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param type
     *            specifies the type of this cell
     */

    public DotButton(int row, int column, int type) {
    	this.row = row;
    	this.column = column;
    	this.type = type;
    	selected = false;
    	setIcon(getImageIcon());
    }


    private void setIcon() {
    	
    }

    private ImageIcon getImageIcon() {
    	if (icons[type] == null) {
    		String imgPostFix = Integer.toString(type);
    		icons[type] = new ImageIcon("data/ball-" + type + ".png");
    	}
    	return icons[type];
    }
    
    private void setSelected() {
    	selected = true;
    }
    /**
     * Changes the cell type of this cell. The image is updated accordingly.
     * 
     * @param type
     *            the type to set
     */

    public void setType(int type) {
    	this.type = type;
    }

 
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
    	return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
    	return column;
    }
}
