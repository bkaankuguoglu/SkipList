import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Operations {
	private static SkipList skipList;
	private static String saveText;
	private static ArrayList<String> elements;

	
	public static SkipList getSkipList() {
		return skipList;
	}

	public static void setSkipList(SkipList skipList) {
		Operations.skipList = skipList;
	}

	/* It reads the input string values from the file "input.txt" and creates the skip list with
	 * the keys given as input in the txt file. On the second line of the input.txt, the number
	 * implies the maximum capacity that the skip list can hold.
	 */
	
	public static void readDatabase(){
		skipList = new SkipList();
		setSaveText("");
		BufferedReader bufferedReader = null;
		try {
			String sCurrentLine;
			bufferedReader = new BufferedReader(new FileReader("input.txt"));
			elements = new ArrayList<String>();
			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				System.out.println("\n" + sCurrentLine);
			    String[] tokens = sCurrentLine.split("\\s+");
			    if(tokens.length>1){
					for (int i = 0; i < tokens.length; i++) {
						elements.add(tokens[i]);
					}			    	
				}else{
					int maxCap = Integer.parseInt(tokens[0]);
					skipList.setMaxCapacity(maxCap);
				}
			Collections.sort(elements);
			}
			int key = 0;
			for (int i = 0; i < elements.size(); i++) {
				key = Integer.parseInt(elements.get(i));
				skipList.insert(key);
				saveText += key + " " + SkipList.findLevel(key) + "\n";
			}
			
			
			System.out.println("\nThe input text is read successfully. \n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	/* Saves the database with the corresponding keys given in input.txt file for later use.
	 * If we want to implement a save method to store the current skip list instance, it's not
	 * a rocket science and it can be done easily by just adding a piece of code that inserts the
	 * new keys into the String field called "saveText' and sorts it using Collections utilities.
	 */
	
	public static void saveDatabase(){
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("list1.txt"));
			bufferedWriter.write(saveText);	
			System.out.println("\nThe skip list is saved to the file 'list1.txt'.\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		
	
	}
	
	/* Loads the skip list from the file that was created by the save method and it creates a skip list
	 * out of it. It only takes height and the key of the nodes and since these nodes are in order when they
	 * are stored, it does not need to search on the skip list or find their corresponding levels. It just 
	 * inserts keys one by one from the file called "list1.txt" in order.
	 * 
	 */
	
	public static void loadDatabase(){
		skipList = new SkipList();
		BufferedReader bufferedReader = null;
		Node startNode = skipList.getMinusInf();
		while(startNode.getDown()!=null)
			startNode = startNode.getDown();
		try {
			String sCurrentLine;
			bufferedReader = new BufferedReader(new FileReader("list1.txt"));
			ArrayList<String> elements = new ArrayList<String>();
			ArrayList<String> height = new ArrayList<String>();
			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				System.out.println(sCurrentLine);
			    String[] tokens = sCurrentLine.split("\\s+");
				elements.add(tokens[0]);
				height.add(tokens[1]);
							    	
			}
			int key = 0;
			int level = 0;
			for (int i = 0; i < elements.size(); i++) {
				key = Integer.parseInt(elements.get(i));
				level = Integer.parseInt(height.get(i));				
				skipList.insert(key, level, startNode);
				startNode=startNode.getNext();
			}
			
			
			System.out.println("\nThe skip list is loaded from the file in which it is stored.\n");
			
		
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	

	public static String getSaveText() {
		return saveText;
	}

	public static void setSaveText(String saveText) {
		Operations.saveText = saveText;
	}
	
	/* After any insertion, in order to save the next element in the skip list we have to add it to the add
	 * saveText. Whenever "insert" is called on the main console, this method adds the element to the text
	 * to be saved.
	 */
	
	public static void addToSaveText (int key){
		int k = 0;
		elements.add(Integer.toString(key));
		Collections.sort(elements);
		saveText = "";
		for (int i = 0; i < elements.size(); i++) {
			k = Integer.parseInt(elements.get(i));
			saveText += k + " " + SkipList.findLevel(k) + "\n";
		}		
	}
	
	public static void removeFromText (int key){
		int k = 0;
		elements.remove(Integer.toString(key));
		Collections.sort(elements);
		saveText = "";
		for (int i = 0; i < elements.size(); i++) {
			k = Integer.parseInt(elements.get(i));
			saveText += k + " " + SkipList.findLevel(k) + "\n";
		}		
	}
	
	public static void saveToFile(String filename){
		BufferedWriter bufferedWriter = null;
		File file = null;
		try {
			file = new File(filename);
			if(file.exists()){
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(saveText);	
			}else{
				file.createNewFile();
				bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(saveText);	
				
			}
			System.out.println("\nThe skip list is saved to the file " + filename + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		
	
	}
	
	public static void loadFromFile(String filename){
		skipList = new SkipList();
		BufferedReader bufferedReader = null;
		Node startNode = skipList.getMinusInf();
		while(startNode.getDown()!=null)
			startNode = startNode.getDown();
		try {
			String sCurrentLine;
			bufferedReader = new BufferedReader(new FileReader(filename));
			ArrayList<String> elements = new ArrayList<String>();
			ArrayList<String> height = new ArrayList<String>();
			while ((sCurrentLine = bufferedReader.readLine()) != null) {
				System.out.println(sCurrentLine);
			    String[] tokens = sCurrentLine.split("\\s+");
				elements.add(tokens[0]);
				height.add(tokens[1]);
							    	
			}
			int key = 0;
			int level = 0;
			for (int i = 0; i < elements.size(); i++) {
				key = Integer.parseInt(elements.get(i));
				level = Integer.parseInt(height.get(i));				
				skipList.insert(key, level, startNode);
				startNode=startNode.getNext();
			}
			
			
			System.out.println("\nThe skip list is loaded from the file "+ filename +"\n");
			
		
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
	

