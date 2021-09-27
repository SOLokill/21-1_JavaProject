package middleProject;

import java.awt.*;

public class Fish {

	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	int size;
	boolean up;
	boolean down;
	boolean left;
	boolean right;

	public Fish(Image image, int x, int y, int width, int height) {
		super();
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.size = width + height;
		
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public int getSize() {
		return size;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public Fish() {
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
