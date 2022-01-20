package bingogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.PlainDocument;


public class BingoMain {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
				
		final int MAX_BINGO_NUMBER = 99;
		
		Scanner scan = new Scanner(System.in);
		String matchPath = new String("C:\\Users\\Usuario\\Desktop\\A_GITHUB\\dam\\programacio\\A3P1BingoGameProject\\");
		
		boolean[] balls = new boolean[MAX_BINGO_NUMBER + 1]; 
		String [] players = null;
		
		boolean rowAnounced = false, bingoAnounced = false;
		
		System.out.println("Introdueixi el nom del fitxer de partida:");
		matchPath += scan.nextLine();
		
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
				cards[i] = BingoMain.loadCard(matchPath, 1);
			}	
			
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
		
		while (!rowAnounced) {
			
			System.out.println("Premi INTRO per a treure una bola");
			scan.nextLine();
			
			int currentBall = BingoMain.chooseBall(balls);
			
			System.out.println("BOLA: " + currentBall);
			
			System.out.println();
			
			System.out.println(Arrays.toString(balls));
			for (int i = 0; i < cards.length; i++) {
				
				if (BingoMain.rowCompleted(cards[i], BingoMain.findNumRow(cards[i], currentBall))) {
					
					rowAnounced = true;
					
				}
				
			}
			
		}
		
		System.out.println("Linia cantada!\n\n\nASDFSEAIJFASÑLFIJESFAWEF\n\n\n");
				
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
			players[i] = bufferedReader.readLine();
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
	
	
	
}
