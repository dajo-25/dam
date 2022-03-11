package labyrinth.models;

import labyrinth.tools.CellType;

public class NormalCell extends Cell {

	public NormalCell() {
		
		super();
		this.type = CellType.NORMAL;
		
	}
	
	public NormalCell(int row, int col) {
		
		super(row, col, CellType.NORMAL);
		
	}
	
	@Override
	public boolean traverse(Player p) {

		openCell();
		traverseMessage = super.toString() + "\nNo afecta al jugador.\n";
		
		return true;
	}

}
