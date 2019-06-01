package whack_a_mole;
import java.util.*;

public class Whack_A_Mole {
	// instance variables
	int score;
	int molesLeft;
	int attemptsLeft ;
	char[][] moleGrid = new char [14][14];

	Whack_A_Mole(int numAttempts, int gridDimension) {
		numAttempts = 0;
		gridDimension = 14;
		molesLeft = 10;
		attemptsLeft = 50;
		score = 0;
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	boolean place (int x, int y) {
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j <10; j++) {
				if ((i==x) && (j==y)) {
					moleGrid[i][j] = 'M';
					break;
				}
			}
		}

		molesLeft--;
		return (molesLeft == 0) ? true:false; // all moles have been placed successfully
	}
	
	void whack(int x, int y) {
		if (moleGrid[x][y] == 'M' ) {
			moleGrid[x][y] = 'W'; // whacked the mole
			score++;
			attemptsLeft--;
			molesLeft--;
		} else {
			attemptsLeft--;
		}
	}
	
	void printGridToUser() {
		for (int i = 0; i < 10 ; i++) {
			for (int j = 0; j < 10; j++) {
				
				char moles = moleGrid[i][j];
				if (moles == 'M') {
					System.out.print(moleGrid[i][j]);
				}
			}
			System.out.print('\n');
		}
	}
	
	void printGrid() { 
		int m = 1; 
		int n = 1;
		int count = 0;
		System.out.print("  ");
		for (int i = 0; i < 10; i++) {
			m = i + 1;
			count = i;
			for (int j = 0; j < 10; j++) {
				while (n <11) {
					System.out.print(String.format("%1$4s",n)); // horizontal numbers
					if (n == 10) {
						System.out.println(" ");
					}
					n++;
				} 
				if ( m > count) {
					System.out.print(String.format("%1$2s", m)); // the vertical numbers. String.format modifies the output
					count++;
				}
				
				System.out.print(String.format("%1$4s",moleGrid[i][j] ));
			}
			System.out.print('\n');
		}
	}
	
	static boolean inputValidator(int x, int y) { // check whether input is within desired range
		return (x >-1  && x < 10) && ( y > -1  && y < 10) ? true : false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Whack_A_Mole newGame = new Whack_A_Mole (0, 10);
		
		// generating random x and y mole positions
		Random rand = new Random();
		int xPos = rand.nextInt(10) + 1;
		int yPos = rand.nextInt(10) + 1;
		
		// user input
		int xInput = 0;
		int yInput = 0;
		boolean validInput = false;

		while (newGame.molesLeft > 9) {
			while (!validInput) {
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter x component:  ");
				xInput = scanner.nextInt() -1; // to make our input range from 0 to 9. Remain within bounds of array
				System.out.print("Enter y component:  ");
				yInput = scanner.nextInt() -1; // to make our input range from 0 to 9
				validInput = inputValidator(xInput, yInput);
				if (validInput) {
					System.out.println("xInput: "+xInput+" yInput: "+yInput);
				} else {
					System.out.println("Please check your inputs and try again");
				}
			}
			
			System.out.println("Welcome to Whack A Mole!! This is a 10 X 10 grid. You play the game"
					+"by entering your x and y location to Whack the mole");
			System.out.println("You have 50 attempts. Use them wisely to whack all 10 moles. "
					+"For the quiters, enter -1 and -1 to leave the game");
			System.out.println('\n');
			
			System.out.println("xPos: "+xPos+",yPos: "+yPos);
			newGame.place(xPos, yPos);
			newGame.printGrid(); 
			
		}
		
					
	}

}



/**
 * 0. Place all 10 moles randomly
 * 1. Print out printGridToUser
 * 2. Take user input
 * 3. printGridToUser again. if mole is whacked, show the W
 * 4. Keep doing this  until user gives up (-1,-1) or user runs out of attempts
 * */
