package keel.Algorithms.Decision_Trees.PUBLIC;

/**
 * 
 * File: Main.java
 * 
 * This is the main class of the algorithm.
 * It gets the configuration script, builds the decision tree model, and
 * classifies with it.
 * 
 * @author Written by Victoria Lopez Morales (University of Granada) 13/03/2009 
 * @version 0.1 
 * @since JDK1.5
 * 
 */

public class Main {
	
	/**
     * The classifier we are going to use
     */
	private static PUBLIC decisionTree;
	
	/** 
	 * The main method of the class
	 * 
	 * @param args Arguments of the program (a configuration script, generally)  
	 */
	public static void main (String args[]) {		
		if (args.length != 1){
			System.err.println("Error: You have to specify the parameters file");
		} else {
		    decisionTree = new PUBLIC(args[0]);
			decisionTree.execute();
		}
	} //end-method 
  
} //end-class