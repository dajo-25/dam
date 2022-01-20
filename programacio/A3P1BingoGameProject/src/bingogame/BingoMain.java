package bingogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class BingoMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String matchPath = new String("C:\\Users\\DjBos\\eclipse-workspace\\A3P1BingoGameProject\\");
		
		System.out.println("Introdueixi el nom del fitxer de partida:");
		matchPath += scan.nextLine();
		
		try {
			String [] players = BingoMain.loadPlayers(matchPath);
		} catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}
		
		
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
		
		while(!currentLine.contentEquals("#Bingo card size")) {
			
			bufferedReader.readLine();
			
		}
		
		int[] size = new int[2];
		String[] sizeString = bufferedReader.readLine().split(" ");
		
		size[0] = Integer.parseInt(sizeString[0]);
		size[1] = Integer.parseInt(sizeString[1]);
		
		card = new int[size[0]][size[1]];
		
		while(!currentLine.contentEquals("#Bingo card " + cardNum)) {
			
			bufferedReader.readLine();
			
		}
		
		
		
		return card;
	}
	
}
