package labyrinth.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import labyrinth.models.BombCell;
import labyrinth.models.Cell;
import labyrinth.models.MonsterCell;
import labyrinth.models.NormalCell;
import labyrinth.models.NormalEnterCell;
import labyrinth.models.NormalExitCell;
import labyrinth.models.Player;
import labyrinth.models.PowerUpCell;
import labyrinth.models.TeleportationCell;
import labyrinth.models.TrapCell;
import labyrinth.models.WellCell;

public class Labyrinth {

	Cell[][] labyrinth;
	Player player;
	int nrows, ncols, iniCellRow, iniCellCol;
	boolean gameEnded;
	String lastCellMessage;
	
	public Labyrinth() {
		
		this.labyrinth = null;
		this.player = null;
		this.nrows = 0;
		this.ncols = 0;
		this.iniCellCol = 0;
		this.iniCellRow = 0;
		this.gameEnded = true;
		this.lastCellMessage = "";
		
	}
	
	public boolean loadLabyrinth(String fileName) throws IOException {
		
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			return false;
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		bufferedReader.readLine();
		
		String[] size = bufferedReader.readLine().split(" ");
		
		this.nrows = Integer.parseInt(size[0]);
		this.ncols = Integer.parseInt(size[1]);
		
		bufferedReader.readLine();
		bufferedReader.readLine();
		
		this.labyrinth = new Cell[this.nrows][this.ncols];
		
		for (int i = 0; i < nrows; i++) {
			
			String[] cells = bufferedReader.readLine().split(" ");
			for (int j = 0; j < ncols; j++) {
				
				switch (Integer.parseInt(cells[j])) {
				case 0:
					
					labyrinth[i][j] = new NormalEnterCell(i, j);
					
					break;

				case 1:
					
					labyrinth[i][j] = new NormalExitCell(i, j);
					
					break;
					
				case 2:
					
					labyrinth[i][j] = new NormalCell(i, j);
					
					break;
					
				case 3:
					
					labyrinth[i][j] = new BombCell(i, j);
					
					break;
					
				case 4:
					
					labyrinth[i][j] = new TrapCell(i, j);
					
					break;
					
				case 5:
					
					labyrinth[i][j] = new WellCell(i, j);
					
					break;
					
				case 6:
					
					labyrinth[i][j] = new TeleportationCell(i, j);
					
					break;
					
				case 7:
					
					labyrinth[i][j] = new MonsterCell(i, j);
					
					break;
					
				case 8:
					
					labyrinth[i][j] = new PowerUpCell(i, j);
					
					break;
				default:
					break;
				}
				
			}
			
		}
		bufferedReader.close();
		
		return true;
	}
	
	public static boolean createLabyrinth(String fileName) throws IOException {
		
		FileWriter fileWriter;
		
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.write("#Labyrinth size (space separated)");
		
		int rows = (int) Math.round(Math.random() * 5 + 5);
		int cols = (int) Math.round(Math.random() * 5 + 5);
		
		bufferedWriter.write(rows + " " + cols + "\n\n#Labyrinth cells (tab separated)");
		
		for (int i = 0; i < rows; i++) {
			
			String currentLine = "";
			
			for (int j = 0; j < cols; j++) {
				
				currentLine += (int) Math.round(Math.random() * 8) + " ";
				
			}
			
			bufferedWriter.write(currentLine);
			currentLine = "";
			
		}
		
		bufferedWriter.close();
		return true;
	}
	
	
	
}
