/*
--------------------------------------------
Battleship Game
Written by Kenny Phan - 40164827
December 4th 2020

PURPOSE:
This program uses arrays of object with an additional class to simulate the Battleship game between a human player and the computer on a single 8x8 grid.
The program will prompt the human to enter their six ships, four grenades, and rocket positions using coordinates: a letter with a digit.
On the other hand, the computer will generate its six ships, four grenades, and rocket positions at random. The program ensures that no ships
nor grenades can overlap. The human player and the computer will take turns choosing their rocket positions.
The game will continue to simulate until all of the human's ships or the computer's ships are all sunk.
The player who sunk all of the opponent's ships is declared the winner.

 -------------------------------------------
 */

import java.util.Random;
import java.util.Scanner;

public class Driver {
	
	//Create a 2-D array of 8x8 Battleship objects used to store ships and grenades
	private static Battleship[][] grid = new Battleship[8][8];
	
	public static void main(String[] args) {
		// Prompt user the welcome message
		System.out.println("--------------------------------");
		System.out.println(" Hello! Let's play BATTLESHIP!");
		System.out.println("--------------------------------");
		System.out.println();
		
		//Initialize each of the 64 Battleship objects
		for(int i = 0; i < Battleship.getNumRow(); i++) {
			for(int j = 0; j < Battleship.getNumCol(); j++) {
				grid[i][j] = new Battleship("none", "_", false); // (owner, type of element, position called)
			}
		}
		
		//Declare scanner object
		Scanner scanner = new Scanner(System.in);
		
		// Prompt player for 6 ships
		for (int i = 0; i < Battleship.getPlayerShips(); ) {
			System.out.print("Enter the coordinates of your ship #" + (i+1) + ": ");
			String input = scanner.nextLine();
			//Check the correctness of coordinate
			if (Battleship.check(input)) {
				grid[Battleship.getRow()-1][Battleship.getCol()-1].setOwner("human");
				grid[Battleship.getRow()-1][Battleship.getCol()-1].setType("s");
				i++;
			}
			
		}
		
		// Prompt player for 4 grenades
		for (int i = 0; i < Battleship.getPlayerGrenades(); ) {
			System.out.print("Enter the coordinates of your grenade #" + (i+1) + ": ");
			String input = scanner.nextLine();
			//Check the correctness of coordinate
			if (Battleship.check(input)) {
				grid[Battleship.getRow()-1][Battleship.getCol()-1].setOwner("human");
				grid[Battleship.getRow()-1][Battleship.getCol()-1].setType("g");
				i++;
			}
		}
		
		// Generate random ships for the computer
		Random rand = new Random();
		for (int i = 0; i < Battleship.getComputerShips(); ) {
			int x = rand.nextInt(8);
			int y = rand.nextInt(8);
			//Check the correctness of coordinate
			if(Battleship.checkCPU(x, y)) {
				grid[x][y].setOwner("computer");
				grid[x][y].setType("S");
				i++;
			}
			
		}
		
		// Generate random grenades for the computer
		for (int i = 0; i < Battleship.getComputerGrenades(); ) {
			int x = rand.nextInt(8);
			int y = rand.nextInt(8);
			//Check the correctness of coordinate
			if(Battleship.checkCPU(x, y)) {
				grid[x][y].setOwner("computer");
				grid[x][y].setType("G");
				i++;
			}
		
		}	
		
		// Prompt player that the game is starting
		System.out.println();
		System.out.println("OK, the computer placed its ships and grenades at random. Let's Play!");
		System.out.println();
		
		// Display the initial 8x8 grid
		System.out.println("  A  B  C  D  E  F  G  H");
		int temp = 1;
		for(int i = 0; i < Battleship.getNumRow(); i++) {
			System.out.print(temp + " ");
			for(int j = 0; j < Battleship.getNumCol(); j++) {
				if (grid[i][j].getPosition() == true) {
					System.out.print(grid[i][j] + "  ");
				} else {
					System.out.print("_" + "  ");
				}
			}
			temp++;
			System.out.println();
		}
			
		System.out.println();

		// While loop used to keep simulating the game until there is a winner
		while(true) {
			// Prompt rocket position from human player
			Battleship.playerAttack();
			checkPos(Battleship.getRow()-1,Battleship.getCol()-1, "human");

			// Display grid
			System.out.println("  A  B  C  D  E  F  G  H");
			int temp2 = 1;
			for(int i = 0; i < Battleship.getNumRow(); i++) {
				System.out.print(temp2 + " ");
				for(int j = 0; j < Battleship.getNumCol(); j++) {
					if (grid[i][j].getPosition() == true) {
						System.out.print(grid[i][j] + "  ");
					} else {
						System.out.print("_" + "  ");
					}
				}
				temp2++;
				System.out.println();
			}
			
			// Check if there is a winner
			System.out.println();
			String result = Battleship.checkWinner();
			if (result.length() > 0) {
				System.out.print(result);
				break;
			}
			
			// If the human hits a grenade, then make the computer attack an additional turn
			if (Battleship.isGrenadeHit() == true) {		
				//Generate a rocket position for the computer
				System.out.println();
				System.out.print("The computer attacked: ");
				System.out.println();
				int x = rand.nextInt(8);
				int y = rand.nextInt(8);
				checkPos(x, y, "computer");
				
				// Print grid
				System.out.println("  A  B  C  D  E  F  G  H");
				int temp3 = 1;
				for(int i = 0; i < Battleship.getNumRow(); i++) {
					System.out.print(temp3 + " ");
					for(int j = 0; j < Battleship.getNumCol(); j++) {
						if (grid[i][j].getPosition() == true) {
							System.out.print(grid[i][j] + "  ");
						} else {
							System.out.print("_" + "  ");
						}
					}
					temp3++;
					System.out.println();
				}
				Battleship.setGrenadeHit(false);
				
				// Check if there is a winner
				System.out.println();
				result = Battleship.checkWinner();
				if (result.length() > 0) {
					System.out.print(result);
					break;
				}
			}
			
			//Generate a rocket position for the computer
			System.out.println();
			System.out.print("The computer attacked: ");
			System.out.println();
			int x = rand.nextInt(8);
			int y = rand.nextInt(8);
			checkPos(x, y, "computer");
			
			// Display grid
			System.out.println("  A  B  C  D  E  F  G  H");
			int temp4 = 1;
			for(int i = 0; i < Battleship.getNumRow(); i++) {
				System.out.print(temp4 + " ");
				for(int j = 0; j < Battleship.getNumCol(); j++) {
					if (grid[i][j].getPosition() == true) {
						System.out.print(grid[i][j] + "  ");
					} else {
						System.out.print("_" + "  ");
					}
				}
				temp4++;
				System.out.println();
			}
			
			// Check if there is a winner
			System.out.println();
			result = Battleship.checkWinner();
			if (result.length() > 0) {
				System.out.print(result);
				break;
			}
			
			// If the computer hits a grenade, then make the human attack an additional turn
			if (Battleship.isGrenadeHit() == true) {
				Battleship.playerAttack();
				checkPos(Battleship.getRow()-1,Battleship.getCol()-1, "human");

				// Display grid
				System.out.println("  A  B  C  D  E  F  G  H");
				int temp5 = 1;
				for(int i = 0; i < Battleship.getNumRow(); i++) {
					System.out.print(temp5 + " ");
					for(int j = 0; j < Battleship.getNumCol(); j++) {
						if (grid[i][j].getPosition() == true) {
							System.out.print(grid[i][j] + "  ");
						} else {
							System.out.print("_" + "  ");
						}
					}
					temp5++;
					System.out.println();
				}
				Battleship.setGrenadeHit(false);
				
				// Check if there is a winner
				System.out.println();
				result = Battleship.checkWinner();
				if (result.length() > 0) {
					System.out.print(result);
					break;
				}
			}
			
		}
		
		System.out.println();
		
		// Display grid showing all ships and grenades once there is a winner
		System.out.println();
		System.out.println("  A  B  C  D  E  F  G  H");
		int temp6 = 1;
		for(int i = 0; i < Battleship.getNumRow(); i++) {
			System.out.print(temp6 + " ");
			for(int j = 0; j < Battleship.getNumCol(); j++) {
				if(grid[i][j].getType().equals("*")) {
					grid[i][j].setType("_");
				}
				System.out.print(grid[i][j] + "  ");
			}
			temp6++;
			System.out.println();
		}	
		
		//Prompt the user the end of the program
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("  Thank you for playing. Bye bye!");
		System.out.println("-------------------------------------");
		scanner.close();
		
	} //--------------------------END OF DRIVER CLASS-----------------------------------------------
	
	// Static method used to check the position of rockets
	public static void checkPos(int x, int y, String user) {
		
		if(grid[x][y].getPosition() == true) {
			System.out.println("---Position already called---");
			
		} else if(grid[x][y].getType().equals("s")) {
			grid[x][y].setPosition(true);
			System.out.println("---SHIP HIT!---");
			Battleship.setPlayerCount(Battleship.getPlayerCount() + 1);

		} else if (grid[x][y].getType().equals("g")) {
			grid[x][y].setPosition(true);
			System.out.println("---GRENADE HIT!---");
			Battleship.setGrenadeHit(true);
			
		} else if (grid[x][y].getType().equals("S")) {
			grid[x][y].setPosition(true);
			System.out.println("---SHIP HIT!---");
			Battleship.setComputerCount(Battleship.getComputerCount() + 1);
			
		} else if (grid[x][y].getType().equals("G")) {
			grid[x][y].setPosition(true);
			System.out.println("---GRENADE HIT!---");
			Battleship.setGrenadeHit(true);
			
		} else {
			grid[x][y].setType("*");
			grid[x][y].setPosition(true);
			System.out.println("---Nothing hit---");
		}
	}
	
	
}
