package proj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class InputFile {
	

	public static void main(String args[]) throws FileNotFoundException {
		
		File file = new File("Department-ComputerScience.txt");
		Scanner scnr = new Scanner(file);
		String department = scnr.next();
		System.out.println(department + "\n");
		while (scnr.hasNext() == true) {
			System.out.println(scnr.next() + " " + scnr.nextDouble() + "\n");
		}
	}
}



