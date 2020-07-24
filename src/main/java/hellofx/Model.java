package hellofx;

import java.util.Random;

import javafx.scene.image.Image;

public class Model {
	
	private int index;
	private Image image;
	
	public Model(int index) {
		this.image = getImage(index);
		this.index = index;
	}
	
	public Model() {
		this(0);
	}
	
	public int getIndex() {
		return this.getIndex();
	}
	
	public Image getImage(int index) {
		Image image;
		if (index==0) {
			image = new Image("hellofx/club.png");
		} else if (index==1) {
			image = new Image("hellofx/diamond.png");
		} else if (index==2) {
			image = new Image("hellofx/heart.png");
		} else {
			image = new Image("hellofx/spade.png");
		}
		return image;	
	}

}
