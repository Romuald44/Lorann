package model.element.mobile;

import java.awt.Point;
import contract.ActionOnLorann;
import contract.IActionOnLorann;
import model.Model;
import model.element.motionless.Land;

public class Demons extends Mobile implements IActionOnLorann {

	/**
	 * IBehavior behavior
	 */
	private IBehavior behavior;

	/**
	 * Instantiate Demons
	 * @param behavior
	 * @param x
	 * @param y
	 * @param model
	 */
	public Demons(IBehavior behavior, int x, int y, Model model) {
		super(behavior.getSprite());
		this.setModel(model);
		this.behavior = behavior;
		this.setThreadActive(true);
		try {
			this.setX(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.setY(y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get ActionOnLorann
	 * @return ActionOnLorann
	 */
	public ActionOnLorann getActionOnLorann() {
		return ActionOnLorann.KILL;
	}
	
	/**
	 * get Mobiles Answer
	 */
	private void getMobilesAnswer() {
		if(this.getModel().getLorann() != null) {
			if((this.getX() == this.getModel().getLorann().getX()) &&
					(this.getY() == this.getModel().getLorann().getY())) {
				this.getModel().setStateThreadFinish();
				this.getModel().setDeath(true);
			}
		}
	}

	/**
	 * loop run
	 */
	public void run() {
		boolean temp;
		while(this.getThreadActive()) {
			temp = false;
			while(!temp) {
				Point positionDemons = this.behavior.movement(this.getModel().getLorann(), new Point(this.getPosition()));
				try {
					if(this.getModel().getElements(positionDemons.x, positionDemons.y) instanceof Land) {
						this.setPosition(positionDemons);
						temp = true;
					} else if(this.getModel().getElements(positionDemons.x, this.getPosition().y) instanceof Land) {
						this.position.x = positionDemons.x;
						temp = true;
					} else if(this.getModel().getElements(this.getPosition().x, positionDemons.y) instanceof Land) {
						this.position.y = positionDemons.y;
						temp = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			this.getMobilesAnswer();
			this.getModel().setMobileHasChanged();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
