package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class BombCell extends Cell {

	private boolean bombActive;
	
	public BombCell() {
		
		super();
		this.type = CellType.BOMB;
		this.bombActive = true;
		
	}
	
	public BombCell(int row, int col) {
		
		super(row, col, CellType.BOMB);
		this.bombActive = true;
		
	}
	
	private void disableBomb() {
		
		this.bombActive = false;
		
	}
	
	@Override
	public boolean traverse(Player p) {

		if (this.opened) {
			
			traverseMessage = "\nJa s'havia passat per aquesta cel·la.\n";
			return true;
			
		}else {
			
			if (p.getPowerUpQuantity(PowerUp.WATER_BALLON) > 0) {
				
				p.usePower(PowerUp.WATER_BALLON);
				disableBomb();
				openCell();
				traverseMessage = super.toString() + "\nS'ha desactivat i el jugador s'ha salvat.\n";
				
				return true;
			}else {
				
				traverseMessage = super.toString() + "\nBADABUUUMMM!!!\n";
				
				return false;
			}			
		}
		
	}
	
}
