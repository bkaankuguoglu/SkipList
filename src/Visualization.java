import java.util.ArrayList;

public class Visualization {
	private static String[] visualisedString;
	
	/* visualize method is for visualization purposes and it prints out the skip list
	 * according to the form described in the project file. The only difference from the
	 * original file, I have used the genuine infinity symbol(∞) in my implementation. It 
	 * stores the string values for the lines of the skip list in the array called as
	 * "visualisedString".
	 */
	
	public static void visualize(SkipList sl){
		Node start = sl.getMinusInf();
		String[] vertical = new String[8];
		for (int i = 0; i < vertical.length; i++) {
			vertical[i] = "[-∞]-";
		}
		new ArrayList<String>();;
		Node leftBottom = null;
		while(start.getDown()!=null){
			start = start.getDown();
		}
		leftBottom = start.getNext();
		
		while(leftBottom.getKey()!=100) {
			leftBottom.updateVertical();
			for (int j = 0; j < 8; j++) {
				vertical[j] += leftBottom.getVerticalLines()[j];						
			}
			leftBottom = leftBottom.getNext();

		}
		for (int i = 0; i < vertical.length; i++) {
			vertical[i] += "-[+∞]";
		}
		
		for (int j = 0; j < vertical.length; j++) {
			System.out.println(vertical[j]);
		}
		setVisualisedString(vertical);
				
}

	/* It visualizes the search function on the console by putting *'s in the place of -'s
	 * being visited. It utilizes the index functionality of array data structure to handle
	 * the issues related to the printing. Since in my implementation every key holds a 4-digit
	 * space on the visualization, I thought that it would be wiser to put *'s as two by two to
	 * prevent any extra *s or any collisions.
	 */
	
	public static void visualizeSearch(SkipList sl, int key){
		String[] visualised = visualisedString;
		String line = "";
		int horizontalLength = visualised[0].length();
		Node current = sl.getMinusInf();
		int countSpaces = 0;
		int level = 0;
		int index = 6;

		while(current.getDown()!=null){
//			System.out.println("level =" + level);
//			System.out.println(current.getNext());

			while(current.getNext().getKey()<=key){

				countSpaces = countSpaces(sl, current.getKey(), current.getNext().getKey());
				current = current.getNext();	
				line += visualised[level].substring(0, index-2);
				
				for (int i = 0; i < countSpaces*2-1; i++) {
					index = index + 2 ;	
					line += "**"; 	
				}
				
				index = index + 2;	

				line += visualised[level].substring(index-4, horizontalLength);
				visualised[level] = line;
				line = "";
				}				
				level++;

				current = current.getDown();

		}
		
		for (int i = 0; i < visualised.length; i++) {
			System.out.println(visualised[i]);
		}
		visualised = null;
	}

	public static String[] getVisualisedString() {
		return visualisedString;
	}

	public static void setVisualisedString(String[] visualisedString) {
		Visualization.visualisedString = visualisedString;
	}
	
	/* This helper method helps visualizeSearch method find the number of *'s must be put
	 * between two nodes being visited. It basically goes down to the bottom line of the skip
	 * list and starts counting from the node on which it is on to the next element which is going
	 * to be visited. 
	 */
	
	public static int countSpaces(SkipList sl, int current, int key){
		Node start = sl.getMinusInf();
		int count = 0;
		while(start.getDown()!=null)
			start=start.getDown();

		while(start.getNext().getKey()<=key){
			start=start.getNext();
			if(start.getKey()>current)
			count++;

		}
		
		return count;
		
	}
}
