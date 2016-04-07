import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Here are some piece of code if you'd like to check if my data
		 * structures and their methods work. First, I started out by creating a
		 * SkipList instance named as "sl" and then I inserted some keys into
		 * it.
		 */

		// SkipList sl = new SkipList();
		// sl.insert(10);
		// sl.insert(15);
		// sl.insert(19);
		// sl.insert(22);
		// sl.insert(37);
		// sl.insert(45);
		// sl.insert(73);

		/*
		 * After creating a class to visualize the SkipList instances I have
		 * created, I wrote a class called Visualization. The method visualize,
		 * not surprisingly, visualizes the skip list instance on the console.
		 * These piece of code checks them but for the convenience of the
		 * reader, I commented out these codes, like all of the debug codes in
		 * the main class.
		 */

		// Visualization.visualize(sl);
		// Visualization.visualizeSearch(sl, 39);

		/*
		 * One of the other class, I wrote was the Operations and I also checked
		 * its functionality and how its methods works here in the main class.
		 */

		// Operations.readDatabase();
		// Visualization.visualize(Operations.getSkipList());
		// System.out.println(Operations.getSaveText());
		// Operations.saveDatabase();
		// Operations.loadDatabase();
		// Visualization.visualize(Operations.getSkipList());

		/*
		 * Lastly, I have checked my remove methods in the main class to see if
		 * it works. Then I tried to visualize and search on the skiplist after
		 * removing an element from it.
		 */

		// sl.remove(10);
		// Visualization.visualize(sl);
		// System.out.println(sl.skipSearch(10));

		/*
		 * Here is the main code that allows us to use the console as an input
		 * stream and apply the corresponding methods from an input on the
		 * console. As described below, users can read the input.txt file, save
		 * the current skip list stored after reading the input file, load it
		 * when it is necessary. Further, visualizing, searching, inserting and
		 * removing operations can be done on the same console. Their
		 * corresponding command integer values are below:
		 * 
		 ************************************************
		 * 	 Integer	*    Functionality				*
		 * 	 Command 	*								*
		 ************************************************
		 * 	    0		*    Exit 						*
		 * 	    1 		*	 Read Input 				*
		 *      2		*	 Save 						*
		 *      3		*	 Load 						*
		 * 	    4  		*	 Visualize 					*
		 *      5  		*	 Search 					*
		 *      6  		*	 Insert 					*
		 *      7  		*	 Remove						*
		 *      8  		*	 Enter your own command		*
		 ************************************************      
		 * 
		 * To start the program, firstly you need to read the input file to
		 * create the skip list instance. If an user tries to operate with other
		 * commands without initiating the skip list, it will give an error, so
		 * to speak. After you read the database by entering the input 1 on the
		 * console, everything else is pretty much straight forward. And also you
		 * need to call the method Visualize at least once before you visualizeSearch.
		 * 
		 * I implemented my own console commands to just create an interface can be
		 * used as easy as possible. I'm aware of the fact that we had to implement
		 * another type of input format for the console commands, so I also implemented
		 * them. After entering the value 8, you'll reach the menu that you can enter 
		 * your commands by typing them. These commands are:
		 * 
		 * **********************************************
		 * > read										*
		 * > save										*
		 * > load										*
		 * > visualize									*
		 * > search*key									*
		 * > insert*key									*
		 * > remove*key									*
		 * **********************************************
		 * 
		 * 
		 * For your own convenience, I suggest you to use my easy interface composed of
		 * integer inputs. However, I also implemented the input commands that we are expected 
		 * to implement and I already checked them, they work properly.
		 * 
		 */

		BufferedReader br = null;
		int exit = 1;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			while (exit == 1) {
				System.out.print("Enter Integer:" + 
									"\n0 - Exit" + 
									"\n1 - Read Input" + 
									"\n2 - Save" + 
									"\n3 - Load"+ 			
									"\n4 - Visualize" + 
									"\n5 - Search" + 
									"\n6 - Insert" + 
									"\n7 - Remove" +
									"\n8 - Enter your own command\n>");
				int i = Integer.parseInt(br.readLine());
				int k = 0;
				switch (i) {
				case 1:
					Operations.readDatabase();
					break;

				case 2:
					Operations.saveDatabase();
					break;

				case 3:
					Operations.loadDatabase();
					break;

				case 4:
					Visualization.visualize(Operations.getSkipList());
					break;

				case 5:
					System.out.print("Enter a key:\n");
					k = Integer.parseInt(br.readLine());
					Visualization.visualizeSearch(Operations.getSkipList(), k);
					break;

				case 6:
					System.out.print("Enter a key:\n");
					k = Integer.parseInt(br.readLine());
					Operations.getSkipList().insert(k);
					Operations.addToSaveText(k);
					Visualization.visualize(Operations.getSkipList());
					break;

				case 7:
					System.out.print("Enter a key:\n");
					k = Integer.parseInt(br.readLine());
					Operations.getSkipList().remove(k);
					Operations.removeFromText(k);
					Visualization.visualize(Operations.getSkipList());
					break;

				case 8:
					System.out.println("Commands can be used:" + 
										"\n- read" + 
										"\n- save" + 
										"\n- load"+ 			
										"\n- visualize" + 
										"\n- search*key" + 
										"\n- insert*key" + 
										"\n- remove*key\n>");					
					String s = br.readLine();
					String command = "";
					String[] arr = s.split("\\*");
					String[] arr2 = s.split("\\s");
					
					String fn = "";
					command=arr[0];

					if(arr.length>1){
						k = Integer.parseInt(arr[1]);
					}
					if(arr2.length>1){
						command = arr2[0];
						fn = arr2[1];
						System.out.println(fn);
					}
					switch (command) {
					case "insert":
						Operations.getSkipList().insert(k);
						Operations.addToSaveText(k);
						Visualization.visualize(Operations.getSkipList());
						break;
					case "remove":
						Operations.getSkipList().remove(k);
						Operations.removeFromText(k);
						Visualization.visualize(Operations.getSkipList());
						break;
						
					case "search":
						Visualization.visualizeSearch(Operations.getSkipList(), k);
						break;
					
					case "save":
						
						Operations.saveToFile(fn);

						break;
					case "load":
						Operations.loadFromFile(fn);

						break;
					case "visualize":
						Visualization.visualize(Operations.getSkipList());

						break;
						
					case "read":
						Operations.readDatabase();
						break;

					default:
						break;
					}
					
					break;

				case 0:
					exit = 0;
					break;

				default:
					break;
				}

			}
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid Format!");
		}

	}

}
