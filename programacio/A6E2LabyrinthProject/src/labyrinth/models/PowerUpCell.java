package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class PowerUpCell extends Cell {

	private PowerUp powerUp;
	
	public PowerUpCell() {
		
		super();
		this.type = CellType.POWERUP;
		this.powerUp = null;
		
	}
	
	public PowerUpCell(int row, int col) {
		
		super(row, col, CellType.POWERUP);
		this.powerUp = null;
		
	}
	
	public PowerUp getPowerUp() {
		
		if (this.powerUp == null) {
			
			this.powerUp = PowerUp.values()[(int) Math.round(Math.random() * (PowerUp.values().length - 1))];
			return this.powerUp;
			
		}else {
			
			return this.powerUp;		
		
		}
	}
	
	@Override
	public boolean traverse(Player p) {

		if (this.opened) {
			
			traverseMessage = "\nJa s'havia passat per aquesta cel·la.\n";
			return true;
			
		}else {
			
			p.addPowerUp(getPowerUp());
			traverseMessage = super.toString() + "\nHa concedit al jugador el poder " + powerUp + "\n";
			this.openCell();
			
			return true;
		}
		
	}
	
}
