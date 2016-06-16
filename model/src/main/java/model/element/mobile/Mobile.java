package model.element.mobile;

import java.awt.Image;
import java.awt.Point;

import contract.IMobile;
import model.element.Element;
import model.element.Permeability;
import model.element.Sprite;

public abstract class Mobile extends Element implements IMobile {
	private Point position;

	public Mobile(final Sprite sprite) {
		super(sprite, Permeability.BLOCKING);
		this.position = new Point();
	}

	public int getX() {
		return this.position.x;
	}

	public int getY() {
		return this.position.y;
	}

	public Image getImage() {
		return this.getSprite().getImage();
	}

	protected void setX(final int x) {
		if ((x >= 0) && (x < this.getModel().getWidth())) {
			this.position.x = x;
			this.getModel().setMobileHasChanged();
		}
	}

	protected void setY(final int y) {
		if ((y >= 0) && (y < this.getModel().getHeight())) {
			this.position.y = y;
			this.getModel().setMobileHasChanged();
		}
	}

	public Point getPosition() {
		return this.position;
	}

	private boolean isMovePossible(final int x, final int y) {
		return (this.getModel().getElements(x, y).getPermeability() != Permeability.BLOCKING);
	}

	public boolean moveUp() {
		boolean bool = false;
		if (this.isMovePossible(this.getX(), this.getY() - 1)) {
			this.setY(this.getY() - 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveLeft() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() - 1, this.getY())) {
			this.setX(this.getX() - 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveDown() {
		boolean bool = false;
		if (this.isMovePossible(this.getX(), this.getY() + 1)) {
			this.setY(this.getY() + 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveRight() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() + 1, this.getY())) {
			this.setX(this.getX() + 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveUpLeft() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() - 1, this.getY() - 1)) {
			this.setX(this.getX() - 1);
			this.setY(this.getY() - 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveUpRight() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() + 1, this.getY() - 1)) {
			this.setX(this.getX() + 1);
			this.setY(this.getY() - 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveDownLeft() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() - 1, this.getY() + 1)) {
			this.setX(this.getX() - 1);
			this.setY(this.getY() + 1);
			bool = true;
		}
		return bool;
	}

	public boolean moveDownRight() {
		boolean bool = false;
		if (this.isMovePossible(this.getX() + 1, this.getY() + 1)) {
			this.setX(this.getX() + 1);
			this.setY(this.getY() + 1);
			bool = true;
		}
		return bool;
	}
}
