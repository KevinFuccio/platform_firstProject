package entities;

public abstract class Entity {
	protected float x,y;
	protected int width,heigh;
	public Entity(float x,float y ,int width,int heigh) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigh = heigh;
	}
}
