package it.unical.object;

public class Collectable {

	private int logic_x;
	private int logic_y;
	boolean isCollected;

	public Collectable(int logic_x, int logic_y) {
		this.logic_x = logic_x;
		this.logic_y = logic_y;
		this.isCollected = isCollected;
	}
	
	public void setCollected() {
		this.isCollected=true;
	}
	
	public boolean isCollected() {
		return isCollected;
	}
	
	public int getLogic_x() {
		return logic_x;
	}
	public int getLogic_y() {
		return logic_y;
	}
	
	
}
