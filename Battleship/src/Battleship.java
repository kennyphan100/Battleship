
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
import java.util.Scanner;

public class Battleship {
	
	// Declare and initialize variables/attributes
	private final static int NUM_ROWS = 8;
	private final static int NUM_COLS = 8;
	private final static int PLAYER_SHIPS = 6;
	private final static int PLAYER_GRENADES = 4;
	private final static int COMPUTER_SHIPS = 6;
	private final static int COMPUTER_GRENADES = 4;
	private static int playerCount = 0;
	private static int computerCount = 0;
	private static int col;
	private static int row;
	private String owner;
	private String type;
	private boolean position;
	private static String[][] storage = new String[NUM_ROWS][NUM_COLS]; //array used to store the ships and grenades
	private static boolean grenadeHit = false;
	
	// Equals methods for objects
	public boolean equals(Battleship anotherPosition) {
		return this.type.equals(anotherPosition.type);
	}
	
	// Constructors
	public Battleship() {
		
	}
	
	public Battleship(String owner, String type, boolean position) {
		this.owner = owner;
		this.type = type;
		this.position = position;
	}
	
	// Check the correctness of placement of pieces for player
	public static boolean check(String input) {
		char letter = input.charAt(0); // extract the character from coordinate
		col = convert(letter); // convert letter to digit
		
		String num = input.substring(1, 2); // extract the number from coordinate
		row = Integer.parseInt(num); //convert the number into integer

		if( (((letter >= 'A' && letter <= 'H') || (letter >= 'a' && letter <= 'h') ) && (row > 0 && row < 9) && (storage[row-1][col-1] != "@")) ) {
			storage[row-1][col-1] = "@";
			return true;
		} else if (((letter >= 'A' && letter <= 'H') || (letter >= 'a' && letter < 'h')) && (row > 0 && row < 9) && storage[row-1][col-1].equals("@") ) {
			System.out.println("Sorry, coordinates already used. Try again ");
			return false;
		} else {
			System.out.println("Sorry, coordinates outside the grid. Try again.");
			return false;
		}
	}

	// Check the correctness of placement of pieces for computer
	public static boolean checkCPU(int x, int y) {
		if ((storage[x][y] != "@")) {
			storage[x][y] = "@";
			return true;
		} else {
			return false;
		}
	}

	// Prompt rocket position for the player
	public static void playerAttack() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your position of rocket: ");
		String position = scanner.nextLine();
		
		char character = position.charAt(0);
		col = convert(character);
		int number = Integer.parseInt(position.substring(1,2));
		row = number; 
		
		while (character < 'A' || (character > 'H' && character <'a') || character > 'h' || number < 1 || number > 8) {
			System.out.println("Invalid position. Try Again");
			System.out.print("Enter your position of rocket: ");
			position = scanner.nextLine();
			
			character = position.charAt(0);
			col = convert(character) ;
			number = Integer.parseInt(position.substring(1, 2));
			row = number; 
		}
	
	}
	
	// Method used to check for winner
	public static String checkWinner() {
		if(getComputerCount() == COMPUTER_SHIPS) {
			return "---CONGRATULATIONS! You Won!---";
		} else if (getPlayerCount() == PLAYER_SHIPS) {
			return "-----YOU LOST!-----";
		}
		return "";
	}
	
	// toString method
	public String toString() {
		return type;
	}
	
	// Getters and Setters
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPosition(boolean position) {
		this.position = position;
	}
	
	public boolean getPosition() {
		return position;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getType() {
		return type;
	}
	
	public static int getComputerShips() {
		return COMPUTER_SHIPS;
	}
	
	public static int getComputerGrenades() {
		return COMPUTER_GRENADES;
	}
	
	public static int getRow() {
		return row;
	}
	
	public static int getCol() {
		return col;
	}
	
	public static int getNumRow() {
		return NUM_ROWS;
	}
	
	public static int getNumCol() {
		return NUM_COLS;
	}
	
	public static int getPlayerShips() {
		return PLAYER_SHIPS;
	}

	public static int getPlayerGrenades() {
		return PLAYER_GRENADES;
	}
	
	public static int getPlayerCount() {
		return playerCount;
	}

	public static void setPlayerCount(int playerCount) {
		Battleship.playerCount = playerCount;
	}
	
	public static int getComputerCount() {
		return computerCount;
	}

	public static void setComputerCount(int computerCount) {
		Battleship.computerCount = computerCount;
	}
	
	public static boolean isGrenadeHit() {
		return grenadeHit;
	}

	public static void setGrenadeHit(boolean grenadeHit) {
		Battleship.grenadeHit = grenadeHit;
	}
	
	// Method used to convert a character into a digit
	private static int convert (char letter) {
		switch (letter) {
		case 'a': case 'A':
			return 1;
		case 'b' : case 'B':
			return 2;
		case 'c' : case 'C':
			return 3;
		case 'd' : case 'D':
			return 4;
		case 'e' : case 'E':
			return 5;
		case 'f' : case 'F':
			return 6;
		case 'g' : case 'G':
			return 7;
		case 'h' : case 'H':
			return 8;
		}
		return 0;
	}

}




