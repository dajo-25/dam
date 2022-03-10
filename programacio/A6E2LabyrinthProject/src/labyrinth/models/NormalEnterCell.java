package labyrinth.models;

import labyrinth.tools.CellType;

public class NormalEnterCell extends Cell {

	public NormalEnterCell() {
		
		super();
		this.type = CellType.NORMAL_ENTER;
		
	}
	
	public NormalEnterCell(int row, int col) {
		
		super(row, col, CellType.NORMAL_ENTER);
		
	}
	
	@Override
	public boolean traverse(Player p) {

		openCell();
		traverseMessage = super.toString() + "\nAccés al laberint.";
		
		return true;
	}
	
}
