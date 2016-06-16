package model;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import contract.IModel;
import model.element.mobile.Demons;
import model.element.mobile.Lorann;
import model.element.mobile.Mobile;
import model.element.mobile.MonsterFour;
import model.element.mobile.MonsterOne;
import model.element.mobile.MonsterThree;
import model.element.mobile.MonsterTwo;
import model.element.motionless.MotionlessElement;
import model.element.motionless.MotionlessElements;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model extends Observable implements IModel {
	
	public MotionlessElement elements[][];
	public final ArrayList<Mobile> mobiles;
	private int width;
	private int	height;
	private Map map;

	/**
	 * Instantiates a new model.
	 */
	public Model() {
		this.mobiles = new ArrayList<Mobile>();
	}
	
	public Lorann getLorann() {
		for(int i = 0; i < this.mobiles.size(); i++) {
			if(this.mobiles.get(i) instanceof Lorann) {
				return (Lorann)this.mobiles.get(i);
			}
		}
		return null;
	}
	
	public void moveUp() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveUp()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveDown() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveDown()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveLeft() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveLeft()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveRight() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveRight()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveUpLeft() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveUpLeft()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveUpRight() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveUpRight()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveDownLeft() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveDownLeft()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void moveDownRight() {
		Point lastPosition = this.getLorann().getPosition();
		if(this.getLorann().moveDownRight()) {
			this.getLorann().setLastPosition(lastPosition);
		}
	}
	
	public void launchFireball() {
		if(!this.getLorann().getFireball().getActive()) {
			this.getLorann().launchFireball();
		}
		else {
			this.getLorann().deviateFireball();
		}
	}
	
	public Map getMap() {
		return this.map;
	}
	
	private void addElement(final MotionlessElement element, final int x, final int y) {
		this.elements[x][y] = element;
		if (element != null) {
			element.setModel(this);
		}
		this.setChanged();
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public MotionlessElement getElements(final int x, final int y) {
		if ((x < 0) || (y < 0) || (x >= this.getWidth()) || (y >= this.getHeight())) {
			return null;
		}
		return this.elements[x][y];
	}
	
	public ArrayList<Mobile> getMobiles() {
		return this.mobiles;
	}
	
	public void addMobile(final Mobile mobile) {
		this.mobiles.add(mobile);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void loadMap(final int id) {
		try {
			final DAOMap Map = new DAOMap(DBConnection.getInstance().getConnection());
			map = Map.find(id);
			String Maptostring = map.getMap();
			this.width = map.getWidth();
			this.height = map.getHeight();
			int numLine = 0;
			this.elements = new MotionlessElement[width][height];
			
			for (int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					System.out.print(Maptostring.charAt(numLine+x));
					switch(Maptostring.charAt(numLine+x)) {
					case 'l':
						this.mobiles.add(new Lorann(x, y, this));
						this.addElement(MotionlessElements.LAND, x, y);
						break;
					case '1':
						this.addMobile(new Demons(new MonsterOne(), x, y, this));
						this.addElement(MotionlessElements.LAND, x, y);
						break;
					case '2':
						this.mobiles.add(new Demons(new MonsterTwo(), x, y, this));
						this.addElement(MotionlessElements.LAND, x, y);
						break;
					case '3':
						this.mobiles.add(new Demons(new MonsterThree(), x, y, this));
						this.addElement(MotionlessElements.LAND, x, y);
						break;
					case '4':
						this.mobiles.add(new Demons(new MonsterFour(), x, y, this));
						this.addElement(MotionlessElements.LAND, x, y);
						break;
					default:
						this.addElement(MotionlessElements.getFromSymbol(Maptostring.charAt(numLine+x)), x, y);
						break;
					}
				}
				numLine+=20;
				System.out.println("");
			    //System.out.println(numLine);
			}
			this.setChanged();
			
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setMobileHasChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getObservable()
	 */
	public Observable getObservable() {
		return this;
	}
}
