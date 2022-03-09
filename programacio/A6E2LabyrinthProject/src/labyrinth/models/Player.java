package labyrinth.models;

import java.util.ArrayList;

import labyrinth.tools.PowerUp;

public class Player {
	
	private String name;
	private int row, col, labyrinthRows, labyrinthCols;
	private int[] powersUps = new int[4];
	/*
	 * 0 - Water Ballon
	 * 1 - Knife
	 * 2 - Boots
	 * 3 - Flute
	 */
	
	public Player() {
		
		this.name = "Visitant";
		this.col = -1;
		this.row = -1;
		this.labyrinthCols = 0;
		this.labyrinthRows = 0;
		this.powersUps = new int[] {0, 0, 0, 0};
		for (int i = 0; i < 3; i++) {
			addPowerUp();
		}
		
	}
	
	public Player(String name, int col, int row, int labyrinthCols, int labyrinthRows) {
		
		this.name = name;
		this.col = col;
		this.row = row;
		this.labyrinthCols = labyrinthCols;
		this.labyrinthRows = labyrinthRows;
		this.powersUps = new int[] {0, 0, 0, 0};
		for (int i = 0; i < 3; i++) {
			addPowerUp();
		}
		
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getRow() {
		
		return row;
	}
	
	public int getCol() {
		
		return col;
	}
	
	public boolean move (char mov) {
		
		if (!(this.col == -1 && this.row == -1)) {
			
			switch (mov) {
			case 'U':
				
				if (!topBound()) {
					
					this.row--;
					
				}else {
					return false;
				}
				
				break;

			case 'D':
				
				if (!downBound()) {
					
					this.row++;
					
				}else {
					return false;
				}
				
				break;
				
			case 'R':
				if (!rightBound()) {
					
					this.col++;
					
				}else {
					return false;
				}
				break;
				
			case 'L':
				if (!leftBound()) {
					this.col--;
				}else {
					return false;
				}
				break;
			default:
				
				return false;
				
			}
			
		}else {
			
			return false;
		}
		
	}
	
	public void setStartingCell(int row, int col) {
		
		if (this.col == -1 && this.row == -1) {
			
			this.row = row;
			this.col = col;
			
		}
		
	}
	
	public void teleport(int row, int col) {
		
		int stepCount = 0;
		while (stepCount < row && this.row < labyrinthRows) {
			
			this.row++;
			
		}
		
		stepCount = 0;
		
		while (stepCount < col && this.col < labyrinthCols) {
			this.col++;
		}
		
	}
	
	public void setLabyrinthSize(int labyrinthRows, int labyrinthCols) {
		
		if (this.labyrinthCols == 0 && this.labyrinthCols == 0) {
			
			this.labyrinthCols = labyrinthCols;
			this.labyrinthRows = labyrinthRows;
			
		}
		
	}
	
	public int getPowerUpQuantity(PowerUp powerUp) {
		
		return powersUps[powerUp.ordinal()];
		
	}
	
	public void addPowerUp() {
		
		this.powersUps[(int) Math.round(Math.random() * PowerUp.values().length)]++;
		
	}
	
	public void addPowerUp(PowerUp newPowerUp) {
		
		this.powersUps[newPowerUp.ordinal()]++;
		
	}
	
	
}
