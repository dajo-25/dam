package labyrinth.models;

import labyrinth.tools.CellType;
import labyrinth.tools.PowerUp;

public class TeleportationCell extends Cell {

	private int rowTranslation, colTranslation;
	
	public TeleportationCell() {
		
		super();
		this.type = CellType.TELEPORTATION;
		this.rowTranslation = -1;
		this.colTranslation = -1;
		
	}
	
	public TeleportationCell(int row, int col) {
		
		super(row, col, CellType.TELEPORTATION);
		this.rowTranslation = -1;
		this.colTranslation = -1;
		
	}
	
	private int[] teleportation() {
		
		int[] values = new int[2];
		
		for (int i = 0; i < values.length; i++) {
			values[i] = (int) Math.round(Math.random() * 4);
		}
		
		return values;
		
	}
	
	@Override
	public boolean traverse(Player p) {

		traverseMessage = super.toString() + "\nEl jugador està viatjant...";
		
		int[] teleportPositions = teleportation();
		
		if (!opened) {
			openCell();
		}
		
		p.teleport(teleportPositions[0], teleportPositions[1]);
		
		
		return true;
	}
	
}
