import java.util.ArrayList;

/**
 * The class <b>Point</b> is a simple helper class that stares a 2 dimensional
 * element on a grid
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class Point {

	// Data Fields
	private int x;
	private int y;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter method for the attribute x.
	 * 
	 * @return the value of the attribute x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter method for the attribute y.
	 * 
	 * @return the value of the attribute y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter for x and y.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public void reset(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean notIn(ArrayList<Point> arr) {
		for (int i=0; i<arr.size(); i++) {
			if (this.getX() == arr.get(i).getX() && this.getY() == arr.get(i).getY()) {
				return false;
			}
		}
		return true;
	}

	public boolean in(Point[] arr) {
		for (int i=0; i<arr.length; i++) {
			if (this.getX() == arr[i].getX() && this.getY() == arr[i].getY()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%d, %d", getX(), getY());
	}

	public boolean equals(Point other) {
		if (this.getX() == other.getX() && this.getY() == other.getY()) {
			return true;
		}
		return false;
	}
}
