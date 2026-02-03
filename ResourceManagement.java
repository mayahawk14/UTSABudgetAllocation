package budgetdistribution;


import java.io.*;
import java.util.*;


/* ResourceManagement
 *
 * Stores the information needed to decide which items to purchase for the given budget and departments
 */
public class ResourceManagement {
	
  private PriorityQueue<Department> departmentPQ; /* priority queue of departments */
  private Double remainingBudget;                 /* the budget left after purchases are made (should be 0 after the constructor runs) */
  private Double budget;                          /* the total budget allocated */
  
  /* TO BE COMPLETED BY YOU
   * Fill in your name in the function below
   */  
  public static void printName( )
  {
    /* TODO : Fill in your name */
    System.out.println("This solution was completed by: Group 6 (Maya Hawkins, "
    		+ "Ashley Urbina, Melissa Chapa, Innocent Hakim, Cameron Ortiz)");
  }

  /* Constructor for a ResourceManagement object
   * TODO
   * Simulates the algorithm from the pdf to determine what items are purchased
   * for the given budget and department item lists.
   */
  public ResourceManagement(String fileNames[], Double budget) {
	  this.budget= budget;
	  this.remainingBudget= budget;
	  departmentPQ = new PriorityQueue<>();
	  
    /* Create a department for each file listed in fileNames */
	  for (int i = 0; i < fileNames.length; i++) {
		  try {
			  Department dep = new Department(fileNames[i]);
			  //adding to PQ
			  departmentPQ.add(dep);
		  } catch (FileNotFoundException e) {
			  System.out.println("Error: File not found for department: " + fileNames[i] + ". Ending program.");
			  System.exit(1);
		  }

	  }
	  
	  /* Simulate the algorithm for picking the items to purchase */
	  System.out.println("ITEMS PURCHASED");
	  System.out.println("----------------------------");
	  while (!departmentPQ.isEmpty() && remainingBudget > 0) {
		  Department dept = departmentPQ.poll();
		  
		  //move next item to itemsRemoved list if > than remaining budget
		  while (!dept.itemsDesired.isEmpty() && dept.itemsDesired.peek().price > remainingBudget) {
			  dept.itemsRemoved.add(dept.itemsDesired.poll());
		  }
		  //given scholarship = to $1000 or remaining budget if no items in department's itemDesired list
		  if (dept.itemsDesired.isEmpty()) {
			  double scholarship = Math.min(1000.0, remainingBudget);
			  if (scholarship > 0) {
				  dept.priority += scholarship;
				  remainingBudget -= scholarship;
				  
				  // Add scholarship to itemsReceived
				  dept.itemsReceived.add(new Item("SCHOLARSHIP", scholarship));
				  
				  /* Be sure to print the items out as you purchase them */
				  /* Here's the part of the code I used for printing prices as items */
				  String price = String.format("$%.2f", scholarship);
				  System.out.printf("Department of %-30s- %-30s- %s\n", dept.name, "SCHOLARSHIP", price);
			  }
			  
		  //purchasing next item that is desired by the department
		  } else {
			  Item item = dept.itemsDesired.poll();
			  dept.itemsReceived.add(item);
			  dept.priority += item.price;
			  remainingBudget -= item.price;
			  
			  String price = String.format("$%.2f", item.price);
			  System.out.printf("Department of %-30s- %-30s- %s\n", dept.name, item.name, price);
		  }
		  
		  //reinsert department to queue 
		  departmentPQ.add(dept);
	  }
  }
  
  /**
   * Prints a summary of all departments, including the total amount spent, 
   * percentage of the budget used, and lists of items received and not received.
   */
  public void printSummary() {
	  
	  // Loop through departments in the priority queue
	  while (!departmentPQ.isEmpty()) {
		  Department currentDep = departmentPQ.poll();
		  
		  System.out.println("\n\nDepartment: " + currentDep.name);
		  System.out.printf("Total Spent \t\t= $%.2f\n", currentDep.priority);
		  System.out.printf("Percent of Budget \t= %.2f%%\n", 
				  currentDep.priority / budget * 100);
		  System.out.println("----------------------------");
		  
		  // Print list of items that the department successfully received
		  System.out.println("ITEMS RECEIVED");
		  while (!currentDep.itemsReceived.isEmpty()) {
			  printItem(currentDep.itemsReceived.poll());
		  }
		  
		  // Print list of items that were not received (either removed or still 
		  // desired)
		  System.out.println("\nITEMS NOT RECEIVED");
		  while (!currentDep.itemsRemoved.isEmpty()) {
			  printItem(currentDep.itemsRemoved.poll());
		  }
		  while (!currentDep.itemsDesired.isEmpty()) {
			  printItem(currentDep.itemsDesired.poll());
		  }
	  }
  }   
  
  /**
   * Helper method to print a single item's name and price, formatted in two 
   * aligned columns.
   * 
   * @param currentItem the item to print
   */
  static private void printItem(Item currentItem) {
	  System.out.printf("%-30s - %-30s\n", currentItem.name, 
			  String.format("$%.2f", currentItem.price));
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
