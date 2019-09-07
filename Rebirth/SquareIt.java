//import scanner for use with user inputs
import java.util.Scanner;

public class SquareIt {

	//beginning of main
	public static void main(String args[]) {
		
		//initialize the variable "input" that will hold the input int
		int input;
		
		//initialize the scanner tool and create received object
		//Scanner received = new Scanner(System.in);
		//assign input to input
		//input = received.nextInt();
		//verify input with user
		//System.out.println("You entered integer " + input);
	
		//temporary
		input = 2;
		
		//create squaring object
		Square squareOfTwo = new Square();
		squareOfTwo.compute(input);
		//received.close();
		
	}
	
}
