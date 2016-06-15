package model.element.motionless;

import model.element.Permeability;
import model.element.Sprite;

public class BoneHoriz extends MotionlessElement {

	public BoneHoriz() {
		super(new Sprite("horizontal_bone.png"), Permeability.BLOCKING, '-');
	}

}