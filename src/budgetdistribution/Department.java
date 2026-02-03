package budgetdistribution;

import java.io.*;
import java.util.*;

	/* Department
	 *
	 * Stores the information associated with a Department at the university
	 */
	public class Department implements Comparable<Department> {
	  String name;                /* name of this department */
	  Double priority;            /* total money spent on this department */
	  Queue<Item> itemsDesired;   /* list of items this department wants */
	  Queue<Item> itemsReceived;  /* list of items this department received */
	  Queue<Item> itemsRemoved;   /* list of items that were skipped because they exceeded the remaining budget */
	
	  /* TODO
	   * Constructor to build a Department from the information in the given fileName
	   */
	  public Department( String fileName ) throws FileNotFoundException {
	    /* Open the fileName, create items based on the contents, and add those items to itemsDesired */
		  File file = new File(fileName);
		  Scanner scnr = new Scanner(file);
		  name = scnr.next();
		  priority = 0.0;
		  itemsDesired = new LinkedList<>();
		  itemsReceived = new LinkedList<>();
		  itemsRemoved = new LinkedList<>();
		  while (scnr.hasNext() == true) {
				Item n = new Item(scnr.next(), scnr.nextDouble());
				itemsDesired.add(n);	
		  }
	  }
	  
	  /*
	   * Compares the data in the given Department to the data in this Department
	   * Returns -1 if this Department comes first
	   * Returns 0 if these Departments have equal priority
	   * Returns 1 if the given Department comes first
	   *
	   * This function is to ensure the departments are sorted by the priority when put in the priority queue 
	   */
	  public int compareTo( Department dept ){
	    return this.priority.compareTo( dept.priority );
	  }

	  public boolean equals( Department dept ){
	    return this.name.compareTo( dept.name ) == 0;
	  }

	  @Override 
	  @SuppressWarnings("unchecked") //Suppresses warning for cast
	  public boolean equals(Object aThat) {
	    if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
	      return true;
	    if (!(aThat instanceof Department))
	      return false;
	    Department that = (Department)aThat;
	    return this.equals( that ); //Use above equals method
	  }
	  
	  @Override
	  public int hashCode() {
	    return name.hashCode(); /* use the hashCode for data stored in this name */
	  }

	  /* Debugging tool
	   * Converts this Department to a string
	   */	
	  @Override
	  public String toString() {
	    return "NAME: " + name + "\nPRIORITY: " + priority + "\nDESIRED: " + itemsDesired + "\nRECEIVED " + itemsReceived + "\nREMOVED " + itemsRemoved + "\n";
	  }
	}

