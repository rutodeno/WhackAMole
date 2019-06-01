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
		return (molesLeft > 0) ? false:true; // all moles have been placed successfully
	}

	void whack(int x, int y) {
		if (moleGrid[x][y] == 'M' ) {
			moleGrid[x][y] = 'W'; // whacked the mole
			score++;
			attemptsLeft--;
			molesLeft--;
		} else {
			moleGrid[x][y] = 'O';
			attemptsLeft--;
		}
	}

	void printGridToUser() {
		char[][] userGrid = new char [14][14];
		System.arraycopy(moleGrid, 0, userGrid,0, moleGrid.length); // creating a copy of an array to print to user

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

				if (userGrid[i][j] == 'M') {
					userGrid[i][j] = '*';
				}
				System.out.print(String.format("%1$4s",userGrid[i][j] ));
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

	void molePlacer() {
		// generating random x and y mole positions
		Random rand = new Random();
		int xPos = 0;
		int yPos = 0;

		boolean allMolesPlaced = false;
		while (!allMolesPlaced) {
			xPos = rand.nextInt(10);
			yPos = rand.nextInt(10);
			allMolesPlaced = place(xPos, yPos);
		}
		System.out.println("All moles have been placed");
	}

	boolean inputValidator(int x, int y) { // check whether input is within desired range
		return (x >-1  && x < 10) && ( y > -1  && y < 10) ? true : false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Whack_A_Mole newGame = new Whack_A_Mole (0, 10);

		System.out.println("Welcome to Whack A Mole!! This is a 10 X 10 grid. You play the game"
				+"by entering your x and y location to Whack the mole");
		System.out.println("You have 50 attempts. Use them wisely to whack all 10 moles. "
				+"For the quiters, enter -1 and -1 to leave the game");
		System.out.println('\n');

		// calling molesPlacer to place the 10 moles on the grid
		newGame.molePlacer();
		newGame.printGrid();

		// user input
		int xInput = 0;
		int yInput = 0;
		boolean exitGame =false;
		boolean validInput = false;

		while (!validInput) { // Checking if our inputs are within the range selected
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter x component:  ");
			xInput = scanner.nextInt() -1; // to make our input range from 0 to 9. Remain within bounds of array
			System.out.print("Enter y component:  ");
			yInput = scanner.nextInt() -1; // to make our input range from 0 to 9

			if (xInput == -2 && yInput == -2) {
				System.out.print("Do you wish to exit the game?");
				System.out.print("Enter y to exit on n to continue playing: ");
				char userSelection = scanner.next().charAt(0);
				if (userSelection == 'y') {
					exitGame = true;
				}
			}

			validInput = newGame.inputValidator(xInput, yInput);
			if (!validInput) {
				System.out.println("Please check your inputs and try again");
			}
		}

		// Whack the mole
		newGame.whack(xInput, yInput);
		newGame.printGridToUser();

	}

}



/**
 * 0. Place all 10 moles randomly
 * 1. Print out printGridToUser
 * 2. Take user input
 * 3. printGridToUser again. if mole is whacked, show the W
 * 4. Keep doing this  until user gives up (-1,-1) or user runs out of attempts
 * */
