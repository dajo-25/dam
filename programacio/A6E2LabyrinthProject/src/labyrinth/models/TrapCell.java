package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class TrapCell extends Cell {

	private boolean ropeCut;
	
	public TrapCell() {
		
		super();
		this.type = CellType.TRAP;
		this.ropeCut = false;
		
	}
	
	public TrapCell(int row, int col) {
		
		super(row, col, CellType.TRAP);
		this.ropeCut = false;
		
	}
	
	private void cutRope() {
		
		this.ropeCut = true;
		
	}
	
	@Override
	public boolean traverse(Player p) {

		if (this.opened) {
			
			traverseMessage = "\nJa s'havia passat per aquesta cel·la.\n";
			return true;
			
		}else {
			
			if (p.getPowerUpQuantity(PowerUp.KNIFE) > 0) {
				
				p.usePower(PowerUp.KNIFE);
				cutRope();
				openCell();
				traverseMessage = super.toString() + "\nS'ha pogut tallar la corda i el jugador pot escapar\n";
				
				return true;
			}else {
				
				traverseMessage = super.toString() + "\nNo s'ha pogut tallar la corda i el jugador queda penjat cap per avall!!!\n";
				
				return false;
			}			
		}
		
	}
	
}
