package labyrinth.models;

import labyrinth.tools.PowerUp;

public class Player {
	
	private final boolean GOD_MODE = true;
	
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
		initializeInitPower();
		
	}
	
	public Player(String name, int col, int row, int labyrinthCols, int labyrinthRows) {
		
		this.name = name;
		this.col = col;
		this.row = row;
		this.labyrinthCols = labyrinthCols;
		this.labyrinthRows = labyrinthRows;
		this.powersUps = new int[] {0, 0, 0, 0};
		initializeInitPower();
		
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
				
				if (this.row > 0) {
					
					this.row--;
					
				}else {
					return false;
				}
				
				break;

			case 'D':
				
				if (this.row < labyrinthRows - 1) {
					
					this.row++;
					
				}else {
					return false;
				}
				
				break;
				
			case 'R':
				if (this.col < labyrinthCols - 1) {
					
					this.col++;
					
				}else {
					return false;
				}
				break;
				
			case 'L':
				if (this.col > 0) {
					this.col--;
				}else {
					return false;
				}
				break;
			default:
				
				return false;
				
			}
			
			return true;
			
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
	
	public void teleport(int stepsHor, int stepsVer) {
		
		for (int i = 0; i < stepsHor; i++) {
			if (this.col + 1 < labyrinthCols) {
				this.col++;
			}
		}
		for (int i = 0; i < stepsVer; i++) {
			if (this.row + 1 < labyrinthRows) {
				this.row++;
			}
		}
		/*
		int stepCount = 0;
		while (stepCount < row && this.row < labyrinthRows) {
			
			this.row++;
			
		}
		
		stepCount = 0;
		
		while (stepCount < col && this.col < labyrinthCols) {
			this.col++;
		}
		*/
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
	
	public boolean usePower(PowerUp powerUp) {
		
		if (this.powersUps[powerUp.ordinal()] > 0) {
			
			this.powersUps[powerUp.ordinal()]--;
			return true;
			
		}else {
			
			return false;
		}
		
	}
	
	public void addPowerUp() {
		
		this.powersUps[(int) Math.round(Math.random() * (PowerUp.values().length-1))]++;
		
	}
	
	public void addPowerUp(PowerUp newPowerUp) {
		
		this.powersUps[newPowerUp.ordinal()]++;
		
	}
	
	public void initializeInitPower() {
		
		if (GOD_MODE) {
			
			for (int i = 0; i < 100; i++) {
				addPowerUp();
			}
			
		}else {
			
			for (int i = 0; i < 3; i++) {
				addPowerUp();
			}
			
		}

	}
	
	
}
