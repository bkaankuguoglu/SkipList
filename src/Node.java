
public class Node {

	private Node up;
	private Node down;
	private Node next;
	private Node prev;
	private int key;
	public String[] verticalLines = new String[8];
	
	public Node(Node up, Node down, Node prev, Node next, int key) {
		this.up = up;
		this.down = down;
		this.next = next;
		this.prev = prev;
		this.key = key;
		this.verticalLines = new String[8];

	}

	public Node(int key) {
		this.up = null;
		this.down = null;
		this.next = null;
		this.prev = null;
		this.key = key;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return  "" + key + "";
	}

	/* It holds the visualization information for each node object for the visualization purposes.
	 * The array size of the mod, in this case it was 7 so I created an array size of 8, is created
	 * and filled with the information needed to print for each node. It puts "-XX-", XX implies two 
	 * digits of the number, if the node exists on that level. Otherwise, it just puts "----".
	 */
	
	public void updateVertical(){
		int minLevel = SkipList.findLevel(key);
		for (int i = 0; i <= minLevel; i++) {
			verticalLines[7-i] = "-" + Integer.toString(key) + "-"; // -DD-
		}
		for (int i = minLevel + 1; i < verticalLines.length; i++) { // ----
			verticalLines[7-i] = "----";
		}
	}

	public String[] getVerticalLines() {
		return verticalLines;
	}

	public void setVerticalLines(String[] verticalLines) {
		this.verticalLines = verticalLines;
	}
	
}

