package labyrinth.models;

import labyrinth.tools.CellType;

public abstract class Cell {
	
	protected int row, col;
	protected CellType type;
	protected boolean opened;
	protected String traverseMessage;
	
	public Cell() {
		
		this.row = 0;
		this.col = 0;
		this.type = null;
		this.opened = false;
		this.traverseMessage = "";
		
	}
	
	public Cell(int row, int col, CellType type) {
		
		this.row = row;
		this.col = col;
		this.type = type;
		this.opened = false;
		this.traverseMessage = "";
		
	}
	
	public CellType getType() {
		
		return this.type;
	}
	
	public String getTraverseMsg() {
		
		return this.traverseMessage;
	}
	
	public void openCell() {
		
		this.opened = true;
		
	}
	
	public boolean isOpened() {
		
		return opened;
	}
	
	public abstract boolean traverse(Player p);

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (col != other.col)
			return false;
		if (opened != other.opened)
			return false;
		if (row != other.row)
			return false;
		if (traverseMessage == null) {
			if (other.traverseMessage != null)
				return false;
		} else if (!traverseMessage.equals(other.traverseMessage))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Cell [" + row + ", " + col + "] Type: " + type;
	}

	
}
