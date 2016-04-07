# SkipList
SkipList Data Structure
    /* It is a simple SkipList ADT implementation that allows users
     * to perform the basic operations of SkipList ADT.
     *
		 * As described below, users can read the input.txt file, save
		 * the current skip list stored after reading the input file, load it
		 * when it is necessary. Further, visualizing, searching, inserting and
		 * removing operations can be done on the same console. Their
		 * corresponding command integer values are below:
		 * 
		 ************************************************
		 * 	 Integer	*    Functionality			        	*
		 * 	 Command 	*								                  *
		 ************************************************
		 * 	    0	   	*    Exit 					              *
		 * 	    1    	*	   Read Input 		      				*
		 *      2	  	*	   Save 						          	*
		 *      3		  *	   Load 						         		*
		 * 	    4  		*	   Visualize 			      				*
		 *      5  		*	   Search 				         			*
		 *      6  		*	   Insert 				           		*
		 *      7  		*	   Remove					            	*
		 *      8  		*	   Enter your own command	     	*
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
		 * > read									              		  	*
		 * > save									              		  	*
		 * > load									              			  *
		 * > visualize						              			  *
		 * > search*key						              				*
		 * > insert*key						              				*
		 * > remove*key						              				*
		 * **********************************************
		 * 
		 * 
		 * For your own convenience, I suggest you to use my easy interface composed of
		 * integer inputs. However, I also implemented the input commands that we are expected 
		 * to implement and I already checked them, they work properly.
		 * 
		 */
