package bingogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@SuppressWarnings("unused")
public class BingoMain {

	final static int MAX_BINGO_NUMBER = 99;
	final static String BASE_PATH = "C:\\Users\\DjBos\\Desktop\\A_GITHUB\\dam\\programacio\\A3P1BingoGameProject\\";
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String matchPath;
		
		boolean[] balls = new boolean[MAX_BINGO_NUMBER + 1];
		
		int[] ballsPoolExtension = BingoMain.createBallsPoolExtension(MAX_BINGO_NUMBER);
				
		String [] players = null;
		
		String firstRowWinner = null;
		String bingoWinner = null;
		
		boolean rowAnounced = false, bingoAnounced = false;
		
		System.out.println("Introdueixi el nom del fitxer de partida:");
		matchPath = BASE_PATH + scan.nextLine();
		
		try {
			
			 players = BingoMain.loadPlayers(matchPath);
			 
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
		
		int[][][] cards = new int[players.length][][];
		
		try {
			
			for (int i = 0; i < cards.length; i++) {
				
				cards[i] = BingoMain.loadCard(matchPath, i+1);
				
			}	
			
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
		
		System.out.println("Premi INTRO per a treure una bola");

		while (!rowAnounced) {
			
			scan.nextLine();
			
			int currentBall;
			
			//CHOOSING BALL (COMMENT THE METHOD NOT NEEDED)
			//Method 1:
			//currentBall = BingoMain.chooseBall(balls);
			//Metod 2:
			currentBall = BingoMain.chooseBallExtension(ballsPoolExtension);
						
			System.out.println("----------------------------------------" +
							 "\n----------------------------------------");
			
			
			//SHOWING ANNOUNCED BALLS(COMMENT THE METHOD NOT NEEDED)
			//Method 1:
			//System.out.println("\nBOLES ANUNCIADES: \n" + announcedBalls(balls) + "\n");
			//Method 2:
			System.out.println("\nBOLES ANUNCIADES: \n" + announcedBallsExtension(ballsPoolExtension) + "\n");

			System.out.println("BOLA: " + currentBall);
			System.out.println();
			
			for (int i = 0; i < cards.length; i++) {
				
				System.out.println("Tarja nº " + (i+1) + " (" + players[i] + ")" + ":");
				System.out.println(BingoMain.cardToString(cards[i]));
				System.out.println();
				
				if (BingoMain.rowCompleted(cards[i], BingoMain.findNumRow(cards[i], currentBall))) {
					
					rowAnounced = true;
					firstRowWinner = players[i];
					
				}
				
			}
			
			if(!rowAnounced) {
				System.out.println("Premi INTRO per a treure una bola");
			}
			
		}
		
		System.out.println("\n___________________________________\n\nLinia cantada! Guanyador/a: " + firstRowWinner + "\n___________________________________\n\n");
		
		System.out.println("Premi INTRO per a treure una bola");
		
		while (!bingoAnounced) {
			
			scan.nextLine();
			
			int currentBall;
			
			//CHOOSING BALL (COMMENT THE METHOD NOT NEEDED)
			//Method 1:
			//currentBall = BingoMain.chooseBall(balls);
			//Metod 2:
			currentBall = BingoMain.chooseBallExtension(ballsPoolExtension);
			
			System.out.println("----------------------------------------" +
							 "\n----------------------------------------");
			
			//SHOWING ANNOUNCED BALLS(COMMENT THE METHOD NOT NEEDED)
			//Method 1:
			//System.out.println("\nBOLES ANUNCIADES: \n" + announcedBalls(balls) + "\n");
			//Method 2:
			System.out.println("\nBOLES ANUNCIADES: \n" + announcedBallsExtension(ballsPoolExtension) + "\n");

			System.out.println("BOLA: " + currentBall);
			System.out.println();
			
			for (int i = 0; i < cards.length; i++) {
				
				System.out.println("Tarja nº " + (i+1) + " (" + players[i] + ")" + ":");
				System.out.println(BingoMain.cardToString(cards[i]));
				System.out.println();
				
				BingoMain.findNumRow(cards[i], currentBall);
				
				if (BingoMain.cardCompleted(cards[i])) {
					
					bingoAnounced = true;
					bingoWinner = players[i];
					
				}
				
			}
			
			if (!bingoAnounced) {
				System.out.println("Premi INTRO per a treure una bola");
			}
			
		}
		
		System.out.println("\n___________________________________\n\nBingo cantat! Guanyador/a: " + bingoWinner + "\n___________________________________\n\n");
		
		try {
			
			BingoMain.updateStatistics(players, bingoWinner, firstRowWinner);
			
		} catch (IOException e) {
			System.out.println("No s'han pogut actualitzar les estadístiques (ERROR I/O)");;
		}
		
		scan.close();
		
	}

	private static String[] loadPlayers(String path) throws IOException {
		
		String [] players;
		
		int numOfPlayers;
		
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		bufferedReader.readLine();
		
		numOfPlayers = Integer.parseInt(bufferedReader.readLine());
		
		players = new String[numOfPlayers];
		
		bufferedReader.readLine();
		
		for (int i = 0; i < numOfPlayers; i++) {
			players[numOfPlayers - (i+1)] = bufferedReader.readLine();
		}
		
		bufferedReader.close();
		fileReader.close();
		
		return players;
	}

	private static int[][] loadCard(String path, int cardNum) throws IOException {
		
		int[][] card;
		
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String currentLine = bufferedReader.readLine();
				
		while(!currentLine.contentEquals("#Bingo cards size")) {
			
			currentLine = bufferedReader.readLine();
			
		}
		
		int[] size = new int[2];
		String[] sizeString = bufferedReader.readLine().split(" ");
		String[] numbersString = null;
		
		size[0] = Integer.parseInt(sizeString[0]);
		size[1] = Integer.parseInt(sizeString[1]);
		
		card = new int[size[0]][size[1]];
		
		while(!currentLine.contentEquals("#Bingo card " + cardNum)) {
			
			currentLine = bufferedReader.readLine();
			
		}
		
		for (int i = 0; i < size[0]; i++) {
						
			numbersString = bufferedReader.readLine().replace('@', '0').split("	");
			
			for (int j = 0; j < numbersString.length; j++) {
				
				card[i][j] = Integer.parseInt(numbersString[j]);
				
			}
			
		}
		
		bufferedReader.close();
		fileReader.close();
		
		return card;
	}
	
	/*
	private static void setNumbers() {
		
	}
	*/
	
	@SuppressWarnings("unused")
	private static int chooseBall(boolean[] balls) {
		
		int choosenBall;
		
		choosenBall = (int) Math.round(Math.random() * (balls.length - 2) + 1);
		
		while (balls[choosenBall]) {
			
			choosenBall = (int) Math.round(Math.random() * (balls.length - 2) + 1);
			
		}
		
		balls[choosenBall] = true;
		
		return choosenBall;
	}
	
	/**
	 * Will return -1 if num was not found.
	 */
	private static int findNumRow(int[][] card, int ballNum) {
		
		int row = -1;
		
		for (int i = 0; i < card.length; i++) {
			for (int j = 0; j < card[i].length; j++) {
				
				if(card[i][j] == ballNum) {
					
					card[i][j] = 0;
					row = i;
					
				}
				
			}
		}
		
		return row;
	}
	
	private static boolean rowCompleted(int[][] card, int row) {
		
		boolean found = false;
		int tmp = 0;
		
		if (row >= 0 && row < card.length) {
			
			for (int i = 0; i < card[0].length; i++) {
				
				tmp += card[row][i];
				
			}
			
			if (tmp == 0) {
				
				found = true;
				
			}
			
		}
		
		return found;
	}
	
	private static boolean cardCompleted(int[][] card) {
		
		boolean found = false;
		int tmp = 0;
		
		for (int i = 0; i < card.length; i++) {
			for (int j = 0; j < card[i].length; j++) {
				
				tmp += card[i][j];
				
			}				
		}
			
		if (tmp == 0) {
				
			found = true;
				
		}
			
		
		return found;
	}
	
	private static String cardToString(int[][] card) {
		
		String cardString = new String();
		
		for (int i = 0; i < card.length; i++) {
			for (int j = 0; j < card[i].length; j++) {
				
				cardString += card[i][j] + " ";
				
			}
			cardString += "\n";
		}
		
		return cardString;
	}
	
	private static String announcedBalls(boolean[] balls) {
		
		String announcedBalls = "[ ";
		int ballsCount = 0;
		
		for (int i = 0; i < balls.length; i++) {
			
			if (balls[i]) {
				
				if (ballsCount%8 == 0 && ballsCount != 0) {
					announcedBalls += "\n";
				}
				
				announcedBalls += i + " ";
				ballsCount++;
				
			}
			
		}
		
		announcedBalls += "]";
		
		return announcedBalls;
	}
	
	private static void updateStatistics(String[] players, String bingoWinner, String firstRowWinner) throws IOException {
		
		FileReader fileReader = new FileReader(BASE_PATH + "bingo_statistics.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		bufferedReader.readLine();
		int totalMatchCount = Integer.parseInt(bufferedReader.readLine());
		totalMatchCount++;
		
		bufferedReader.readLine();
		bufferedReader.readLine();
		int playerAmount = Integer.parseInt(bufferedReader.readLine());
		
		int[] playerMatchCount = new int[playerAmount];
		int[] playerRowCount = new int[playerAmount];
		int[] playerBingoCount = new int[playerAmount];
		
		String[] allPlayers = new String[playerAmount];

		bufferedReader.readLine();
		bufferedReader.readLine();
		
		for (int i = 0; i < playerMatchCount.length; i++) {
			
			String currentLine = bufferedReader.readLine();
			
			playerMatchCount[i] = Integer.parseInt(currentLine.split(" ")[1]);
			allPlayers[i] = currentLine.split(" ")[0];
			
			for (int j = 0; j < players.length; j++) {
				
				if (currentLine.split(" ")[0].equalsIgnoreCase(players[j])) {
					
					playerMatchCount[i]++;
					
				}
				
			}
			
		}
		
		bufferedReader.readLine();
		bufferedReader.readLine();
		
		for (int i = 0; i < playerRowCount.length; i++) {
			
			String currentLine = bufferedReader.readLine();
			
			playerRowCount[i] = Integer.parseInt(currentLine.split(" ")[1]);
			
			if (currentLine.split(" ")[0].equalsIgnoreCase(firstRowWinner)) {
				
				playerRowCount[i]++;
				
			}
			
		}

		bufferedReader.readLine();
		bufferedReader.readLine();
		
		for (int i = 0; i < playerBingoCount.length; i++) {
	
			String currentLine = bufferedReader.readLine();
			
			playerBingoCount[i] = Integer.parseInt(currentLine.split(" ")[1]);
	
			if (currentLine.split(" ")[0].equalsIgnoreCase(bingoWinner)) {
				
				playerBingoCount[i]++;
				
			}
			
		}
		
		bufferedReader.close();
		fileReader.close();
		
		FileWriter fileWriter = new FileWriter(BASE_PATH + "bingo_statistics.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.println("#Número de partides");
		printWriter.println(totalMatchCount);
		printWriter.println();
		printWriter.println("#Número de jugadors");
		printWriter.println(playerAmount);
		printWriter.println();
		printWriter.println("#Número de partides per jugador");
		
		for (int i = 0; i < allPlayers.length; i++) {
			
			printWriter.println(allPlayers[i] + " " + playerMatchCount[i]);
			
		}
		
		printWriter.println();
		printWriter.println("#Número de primeres línies per jugador");
		
		for (int i = 0; i < allPlayers.length; i++) {
			
			printWriter.println(allPlayers[i] + " " + playerRowCount[i]);
			
		}
		
		printWriter.println();
		printWriter.println("#Número de victòries per jugador");
		
		for (int i = 0; i < allPlayers.length; i++) {
			
			printWriter.println(allPlayers[i] + " " + playerBingoCount[i]);
			
		}
		
		printWriter.close();
		fileWriter.close();
		
	}
	
	private static int[] createBallsPoolExtension(int maxNum) {
		
		int[] ballsPool = new int [maxNum + 1];
		
		for (int i = 0; i < ballsPool.length; i++) {
			
			ballsPool[i] = i+1;
			
		}
		
		ballsPool[ballsPool.length - 1] = 0;
		
		//SHUFFELING AS MANY TIMES AS BALLS THERE IS
		for (int i = 0; i < ballsPool.length; i++) {
			
			int randPos1 = (int) (Math.round(Math.random() * (maxNum - 2)));
			int randPos2 = (int) (Math.round(Math.random() * (maxNum - 2)));
			
			int tmp = ballsPool[randPos1];
			ballsPool[randPos1] = ballsPool[randPos2];
			ballsPool[randPos2] = tmp;	
			
		}
		
		return ballsPool;
	}

	private static int chooseBallExtension(int[] ballsPool) {
		
		int choosenBall;
				
			choosenBall = ballsPool[(ballsPool[ballsPool.length-1])];
			ballsPool[(ballsPool.length - 1)]++;
		
		return choosenBall;
	}
	
	private static String announcedBallsExtension(int[] ballsPool) {
		
		String announcedBalls = "[ ";
		int ballsCount = 0;
		
		for (int i = 0; i < (ballsPool[ballsPool.length - 1]); i++) {
			
			if (ballsCount%8 == 0 && ballsCount != 0) {
				announcedBalls += "\n";
			}
				
			announcedBalls += ballsPool[i] + " ";
			ballsCount++;
				
		}
		
		announcedBalls += "]";
		
		return announcedBalls;
	}
	
}
