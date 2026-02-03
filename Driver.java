package proj;

import java.io.*;
import java.util.*;


public class Driver {

  public static void main(String[] args) throws FileNotFoundException {

    String fileNames[][] = {{
      "Department-ComputerScience.txt",
      "Department-Mathematics.txt",
      "Department-Chemistry.txt",
      "Department-PhysicsAndAstronomy.txt"},
      {
      "Department-ComputerScience.txt",
      "Department-Mathematics.txt",
      "Department-Chemistry.txt",
      "Department-PhysicsAndAstronomy.txt"},
      {
      "Department-DeptA.txt",
      "Department-DeptB.txt",
      "Department-DeptC.txt",
      "Department-DeptD.txt",
      "Department-DeptE.txt",
      "Department-DeptF.txt",
      "Department-DeptG.txt"}
      };
                                                

    System.out.println(  ); 
    ResourceManagement.printName( );
    System.out.println(  );

    //Call student solution for the test files:
    double budgets[] = {41850.00, 0.01, 27961.10};
    for( int i=0; i<3; i++ )
      testFile( fileNames[i], budgets[i] );
    
    System.out.println(  ); 
    ResourceManagement.printName( );
    System.out.println(  );
    
  }

  public static void testFile( String fileNames[], double budget ) throws FileNotFoundException {  
    String budgetString = String.format("$%.2f", budget );
    System.out.println("----------------------------TESTING with budget = " + budgetString + "----------------------------");
    System.out.println("");

    ResourceManagement rm = new ResourceManagement(fileNames, budget);
    rm.printSummary();  
  }


}
