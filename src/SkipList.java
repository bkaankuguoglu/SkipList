
public class SkipList {
	private int height;
	private int size;
	private static int maxCapacity = 128;
	private static int mod = (int) Math.ceil(Math.log(maxCapacity) / Math.log(2));

	private Node minusInf; // top minus inf
	private Node plusInf; // top plus inf

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		SkipList.maxCapacity = maxCapacity;
	}

	public SkipList() {
		this.height = 0;
		this.size = 0;
		this.minusInf = new Node(9);
		this.plusInf = new Node(100);
		minusInf.setNext(plusInf);
		plusInf.setPrev(minusInf);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node getMinusInf() {
		return minusInf;
	}

	public void setMinusInf(Node minusInf) {
		this.minusInf = minusInf;
	}

	public Node getPlusInf() {
		return plusInf;
	}

	public void setPlusInf(Node plusInf) {
		this.plusInf = plusInf;
	}

	/*
	 * skipSearch method looks up the key given as input in the skip list and it
	 * return the key if the key is in the list. Otherwise, it returns the
	 * greatest element less than the input key
	 */

	public Node skipSearch(int key) {
		Node current = minusInf;
		int level = findLevel(key);
		while (current.getDown() != null) {
			if (current.getNext().getKey() <= key) {
				current = current.getNext();
			} else {
				current = current.getDown();
			}
		}
		/*
		 * if(current.getKey()==key) System.out.println("Node is found"); else
		 * System.out.println("Node is not found");
		 */

		return current;

	}

	/*
	 * Finds the level of the key on which the key must be placed. It uses the
	 * fuction given in the project file.
	 */

	public static int findLevel(int key) {
		int h = Math.floorMod(key, mod);
		return h;
	}

	/*
	 * First looks up the node to start from the lowest level with the help of
	 * skipSearch, then it checks the if the level of the input key is higher
	 * than the actual height of the skip list. Simply, it starts to insert the
	 * given key to each level of the skip list until it reaches to the top
	 * level of the key. If the level of the input key is higher than the height
	 * of the skip list it adds some empty lines of list on top of the highest
	 * level accordingly.
	 */

	public void insert(int key) {
		Node startNode = skipSearch(key);
		int level = findLevel(key);
		if (key == startNode.getKey()) {
			System.out.println("The key " + key + " is already in the list!");
		} else {


				Node inserted = insertAfterAboveBefore(startNode, null, key);
				size++;

				if (level <= height) {
					for (int i = 0; i <= level; i++) {
						while (startNode.getUp() == null) {
							if (startNode.getPrev() != null)
								startNode = startNode.getPrev();
						}

						startNode = startNode.getUp();
						inserted = insertAfterAboveBefore(startNode, inserted, key);

					}
				} else {
					Node newHead = null;
					Node newTail = null;

					/*
					 * This piece of code adds empty lines on top of the highest
					 * level.
					 */

					for (int i = height; i <= level; i++) {
						newHead = new Node(9);
						newTail = new Node(100);
						newHead.setNext(newTail);
						newTail.setPrev(newHead);

						newHead.setDown(minusInf);
						newTail.setDown(plusInf);

						minusInf.setUp(newHead);
						plusInf.setUp(newTail);

						minusInf = newHead;
						plusInf = newTail;
					}

					for (int i = 0; i <= level; i++) {
						while (startNode.getUp() == null) {
							if (startNode.getPrev() != null)
								startNode = startNode.getPrev();
						}

						startNode = startNode.getUp();

						inserted = insertAfterAboveBefore(startNode, inserted, key);

					}

					height = level;

				}
			}
		}
	

	/*
	 * insertAfterAboveBefore is an helper method that simply handles the
	 * operations in the node level. It inserts the node between two keys and
	 * returns the inserted node.
	 */

	private Node insertAfterAboveBefore(Node after, Node above, int key) {
		// TODO Auto-generated method stub
		Node insertedNode = new Node(key);
		insertedNode.setDown(above);
		insertedNode.setPrev(after);
		insertedNode.setNext(after.getNext());
		after.getNext().setPrev(insertedNode);
		after.setNext(insertedNode);
		if (above != null)
			above.setUp(insertedNode);
		return insertedNode;
	}

	/*
	 * Not that functional, only for debugging purpose. The methods for
	 * visualization of the skip list is written in the Visualization class.
	 */

	public String toString() {
		Node start = minusInf;
		Node current = null;
		String line = "";
		String s = "";

		while (start.getDown() != null) {
			current = start.getDown();
			line += "\n[-∞]-";
			while (current.getNext().getKey() != 100) {
				current = current.getNext();
				line += "-" + current + "-";
			}
			line += "-[+∞]";
			start = start.getDown();

		}
		s += line;

		return s;
	}

	/*
	 * Insert method for the load function. Unlike the generic insert method, it
	 * takes a level of the input key and the starting node as an input. Since
	 * it only inserts the keys into the skip list with respect to their levels,
	 * it does not do any search operation, but simply add keys one by one.
	 */

	public void insert(int key, int level, Node startNode) {
		Node inserted = insertAfterAboveBefore(startNode, null, key);
		size++;

		if (level <= height) {
			for (int i = 0; i < level; i++) {
				while (startNode.getUp() == null) {
					if (startNode.getPrev() != null)
						startNode = startNode.getPrev();
				}

				startNode = startNode.getUp();
				inserted = insertAfterAboveBefore(startNode, inserted, key);

			}
		} else {
			Node newHead = null;
			Node newTail = null;

			for (int i = height; i <= level; i++) {
				newHead = new Node(9);
				newTail = new Node(100);
				newHead.setNext(newTail);
				newTail.setPrev(newHead);

				newHead.setDown(minusInf);
				newTail.setDown(plusInf);

				minusInf.setUp(newHead);
				plusInf.setUp(newTail);

				minusInf = newHead;
				plusInf = newTail;
			}

			for (int i = 0; i <= level; i++) {
				while (startNode.getUp() == null) {
					if (startNode.getPrev() != null)
						startNode = startNode.getPrev();
				}

				startNode = startNode.getUp();

				inserted = insertAfterAboveBefore(startNode, inserted, key);

			}
			height = level;
		}
	}

	/*
	 * Here is the remove method to be used for the removal of a key from the
	 * skip list. It checks if the key exists in the skip list, then it removes
	 * it if it is there.
	 */

	public void remove(int key) {
		Node startNode = skipSearch(key);
		int level = findLevel(key);
		if (key != startNode.getKey()) {
			System.out.println("Node is not found in the list. Therefore it cannot be removed.");
		} else {
			for (int i = 0; i <= level; i++) {
				startNode.getPrev().setNext(startNode.getNext());
				startNode.getNext().setPrev(startNode.getPrev());
				if (startNode.getDown() != null)
					startNode.setDown(null);
				startNode = startNode.getUp();
			}
		}
	}
}
