package labyrinth.models;

import labyrinth.tools.CellType;

public class NormalExitCell extends Cell {

	public NormalExitCell() {
		
		super();
		this.type = CellType.NORMAL_EXIT;
		
	}
	
	public NormalExitCell(int row, int col) {
		
		super(row, col, CellType.NORMAL_EXIT);
		
	}
	
	@Override
	public boolean traverse(Player p) {

		openCell();
		traverseMessage = super.toString() + "\nSortida del laberint.\n";
		
		return true;
	}
	
}
