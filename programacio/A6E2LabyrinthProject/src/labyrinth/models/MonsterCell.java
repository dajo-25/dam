package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class MonsterCell extends Cell {
	
	private boolean monsterAsleep;
	
	public MonsterCell() {
		
		super();
		this.type = CellType.MONSTER;
		this.monsterAsleep = false;
		
	}
	
	public MonsterCell(int row, int col) {
		
		super(row, col, CellType.MONSTER);
		this.monsterAsleep = false;
		
	}
	
	private void tameBeast() {
		
		this.monsterAsleep = true;
		
	}
	
	@Override
	public boolean traverse(Player p) {

		if (this.opened) {
			
			traverseMessage = "\nJa s'havia passat per aquesta cel·la.\n";
			return true;
			
		}else {
			
			if (p.getPowerUpQuantity(PowerUp.FLUTE) > 0) {
				
				p.usePower(PowerUp.FLUTE);
				tameBeast();
				openCell();
				traverseMessage = super.toString() + "\nS'ha amansat la fera i el jugador pot continuar de puntetes.\n";
				
				return true;
			}else if (p.getPowerUpQuantity(PowerUp.LIVES) > 0) {
				
				p.usePower(PowerUp.LIVES);
				tameBeast();
				openCell();
				traverseMessage = super.toString() + "\nHas perdut una vida!.\n";
				p.setJustInjured(true);
				
				return true;
			}else {
				
				traverseMessage = super.toString() + "\nNo s'ha amansat la fera i el jugador és el sopar del monstre!!!\n";
				
				return false;
			}			
		}
		
	}
	
}
