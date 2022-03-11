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
import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

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
		
		fileReader = new FileReader("games/" +fileName + ".txt");
		
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
					iniCellCol = j;
					iniCellRow = i;
					
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
	
	public static boolean createLabyrinth(String fileName, int xSize, int ySize) throws IOException {
		
		FileWriter fileWriter;
		
		fileWriter = new FileWriter("games/" + fileName + ".txt");
		
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.write("#Labyrinth size (space separated)\n");
		
		int rows = ySize;
		int cols = xSize;
		
		bufferedWriter.write(rows + " " + cols + "\n\n#Labyrinth cells (tab separated)\n");
		
		int enterCol = 0;
		int enterRow = (int) Math.round(Math.random() * rows);
		int exitCol = cols - 1;
		int exitRow = (int) Math.round(Math.random() * rows) - 1;
		
		for (int i = 0; i < rows; i++) {
			
			String currentLine = "";
			
			for (int j = 0; j < cols; j++) {
								 
				if (j == enterCol && i == enterRow) {
					
					currentLine += 0 + " ";
					
				}else if (j == exitCol && i == exitRow) {
					
					currentLine += 1 + " ";
					
				}else {
					
					currentLine += (int) Math.round(Math.random() * 6 + 2) + " ";
					
				}
				
				
			}
			
			bufferedWriter.write(currentLine + "\n");
			currentLine = "";
			
		}
		
		bufferedWriter.close();
		return true;
	}
	
	public boolean startGame(String playerName) {
		
		if (labyrinth != null) {
			gameEnded = false;
			player = new Player(playerName, iniCellCol, iniCellRow, ncols, nrows);
			return true;
			
		}else {
			return false;
		}
		
	}
	
	public CellType getPlayerCellType() {
		
		if (!gameEnded) {
			
			return labyrinth[this.player.getRow()][this.player.getCol()].getType();
			
		}else {
			
			return null;
		}
		
	}
	
	public CellType movePlayer(char mov) {
		
		if (!gameEnded) {
			
			if (player.move(mov)) {
				
				return getPlayerCellType();
				
			}else {
				return null;
			}
			
		}else {
			
			return null;
		}
		
	}

	public boolean traverseCell() {
		
		boolean output = false;
		
		if(labyrinth[this.player.getRow()][this.player.getCol()].traverse(player)) {
			
			
			output = true;
		}else {
			
			gameEnded = true;
			output = false;
			
		}
		
		if (labyrinth[this.player.getRow()][this.player.getCol()].getType() == CellType.NORMAL_EXIT) {
			output = true;
			gameEnded = true;
			
		}
		
		return output;
	}
	
	public String getLastCellMessage() {
		
		return lastCellMessage;
		
	}
	
	public boolean isGameEnded() {
		
		return gameEnded;
	}

	@Override
	public String toString() {
		
		String output = "\n";
		
		output += labyrinth[this.player.getRow()][this.player.getCol()].getTraverseMsg();
		
		output += " ";
		for (int i = 0; i < ncols; i++) {
			
			output += " =========";
			
		}
		
		output += "\n";
		
		for (int i = 0; i < nrows; i++) {
			output += "|";
			for (int j = 0; j < ncols; j++) {
				
				if (this.player.getCol() == j && this.player.getRow() == i) {
					output += "|    O    ";
				}else if (labyrinth[i][j].getType() == CellType.NORMAL_EXIT) {
					output += "|    ~    ";
				}else if (labyrinth[i][j].getType() == CellType.TELEPORTATION) {
					if (labyrinth[i][j].isOpened()) {
						output += "|    ·    ";
					}else{
						output += "|    #    ";
					}
				}else {
					
					if (labyrinth[i][j].isOpened()) {
						output += "|         ";
					}else{
						//output += "|    " + labyrinth[i][j].getType().ordinal() + "    ";
						output += "|    #    ";
					}
				}
				
				
			}
			output += "||\n";
		}
		
		output += " ";
		for (int i = 0; i < ncols; i++) {
			
			output += " =========";
			
		}
		
		output += "\n\nEstat del jugador " + player.getName() + ":" 
				+ "\n   PowerUp " + PowerUp.values()[0] + ": " + player.getPowerUpQuantity(PowerUp.values()[0])
				+ "\n   PowerUp " + PowerUp.values()[1] + ": " + player.getPowerUpQuantity(PowerUp.values()[1])
				+ "\n   PowerUp " + PowerUp.values()[2] + ": " + player.getPowerUpQuantity(PowerUp.values()[2])
				+ "\n   PowerUp " + PowerUp.values()[3] + ": " + player.getPowerUpQuantity(PowerUp.values()[3]);
		
		if (!gameEnded) {
			output += "\n\nMoviment (U, D, L, R):";
		}
		
		
		return output;
	}
	
	
	
}
