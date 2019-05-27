package whack_a_mole;
import java.util.*;

public class Whack_A_Mole {
	// instance variables
	int score = 0;
	int molesLeft = 10;
	int attemptsLeft = 50;
	char[][] moleGrid = new char [10][10];

	Whack_A_Mole(int numAttempts, int gridDimension) {
		numAttempts = 50;
		gridDimension = 10;	
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	boolean place (int x, int y) {
		moleGrid[x][y] = 'M';
		molesLeft--;
		return true;
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
					System.out.println(moleGrid[i][j]);
				}
			}
			System.out.println('\n');
		}
	}
	
	void printGrid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println(moleGrid[i][j]);
			}
		}
		System.out.println('\n');
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();
		
		int[] xArray = new int [10];
		int[] yArray = new int [10];
		
		for (int i = 0; i < 10 ; i++) {
			xArray[i] = rand.nextInt(10) + 1;
			yArray[i] = rand.nextInt(10) + 1;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.print("{x,y}: {");
			System.out.print(xArray[i]);
			System.out.print(",");
			System.out.print(yArray[i]);
			System.out.println("}");
		}
		

	}

}
