package model.element.mobile;

import java.awt.Point;
import java.util.Random;

import model.Model;
import model.element.Sprite;
import model.element.motionless.Gate;
import model.element.motionless.Land;
import model.element.motionless.Purse;

public class MonsterFour implements IBehavior {
	
	private Sprite sprite;

	public MonsterFour() {
		this.sprite = new Sprite("monster_4.png");
	}
	
	public Point movement(Lorann lorann, Point demons) {
		int rand = (int)(Math.random() * 4) + 1; 
		switch(rand){
		case 1:
			demons.x = demons.x + 1;
			return demons;
		case 2:
			demons.x = demons.x - 1;
			return demons;
		case 3:
			demons.y = demons.y + 1;
			return demons;
		case 4:
			demons.y = demons.y - 1;
			return demons;
		default :
			return demons;
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	
}
