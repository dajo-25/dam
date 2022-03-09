package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class WellCell extends Cell {

	private boolean jumped;
	
	public WellCell() {
		
		super();
		this.type = CellType.WELL;
		this.jumped = false;
		
	}
	
	public WellCell(int row, int col) {
		
		super(row, col, CellType.WELL);
		this.jumped = false;
		
	}
	
	private void jumpOver() {
		
		this.jumped = true;
		
	}
	
	@Override
	public boolean traverse(Player p) {

		if (this.opened) {
			
			traverseMessage = "\nJa s'havia passat per aquesta cel·la.";
			return true;
			
		}else {
			
			if (p.getPowerUpQuantity(PowerUp.JUMPER_BOOTS) > 0) {
				
				p.usePower(PowerUp.JUMPER_BOOTS);
				jumpOver();
				openCell();
				traverseMessage = super.toString() + "\nS'ha pogut saltar per sobre i el jugador pot continuar.";
				
				return true;
			}else {
				
				traverseMessage = super.toString() + "\nNo s'ha pogut saltar per sobre i el jugador ha caigut i ha quedat atrapat!!!";
				
				return false;
			}			
		}
		
	}
	
}
