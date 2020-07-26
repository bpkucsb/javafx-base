package slot_machine;

import java.io.File;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class models a slot machine with four slots
 * 
 * @author Brendan Keller
 *
 */
public class Model {
	
	private int currWin;     // current winnings
	private int moneySpent;  // money spent so far
	private int netWin;      // net winnings
	private int index1;      // position of slot 1
	private int index2;      // position of slot 2
	private int index3;      // position of slot 3
	private int index4;      // position of slot 4
	private final int HOUSE_ODDS = 5;    // odds in favor of the house
	
	/**
	 * Default constructor, set winnings to zero
	 * and slots to all different positions
	 */
	public Model() {
		this.currWin = 0;
		this.moneySpent  = 0;
		this.netWin = 0;
		this.index1 = 0;
		this.index2 = 1;
		this.index3 = 2;
		this.index4 = 3;
	}
	
	/**
	 * Setter method for current winnings
	 * @param currWin current winnings in integer format
	 */
	public void setCurrWin(int currWin) {
		this.currWin = currWin;
	}
	
	/**
	 * getter method for current winnings
	 * @return current winnings in integer format
	 */
	public int getCurrWin() {
		return currWin;
	}
	
	/**
	 * setter method for money spent so far
	 * @param moneySpent money spent in integer format
	 */
	public void setMoneySpent(int moneySpent) {
		this.moneySpent = moneySpent;
	}
	
	/**
	 * getter method for moeny spent so far
	 * @return money spent so far in integer format
	 */
	public int getMoneySpent() {
		return moneySpent;
	}
	
	/**
	 * getter method for net winnings
	 * @return net winnings in integer format
	 */
	public int getNetWin() {
		return this.currWin - this.moneySpent;
	}
	
	/**
	 * add money to slot machine
	 * @param money added money in integer format
	 */
	public void addMoney(int money) {
		this.currWin += money;
		this.moneySpent += money;
	}
	
	/**
	 * Spin the slot machine. If all slots are equal
	 * add to winnings and launch slot machine win
	 * sound. Otherwise subtract from current winnings
	 * 
	 * @return true if all slots match, false otherwise
	 */
	public boolean spin() {
		

		boolean match1, match2, match3;  // check if slots match
		boolean isWin;                   // is a win
		double win;                      // amount won

		// set slot indices at random
		Random rand = new Random();
		this.index1 = rand.nextInt(4);
		this.index2 = rand.nextInt(4);
		this.index3 = rand.nextInt(4);
		this.index4 = rand.nextInt(4);
		
		// check if all slots match
		match1 = (this.index1==this.index2);
		match2 = (this.index2==this.index3);
		match3 = (this.index3==this.index4);
		isWin = (match1 && match2 && match3);
	
		// computer amount won
		if (isWin) {
			win = 5*((4*4*4-1) - HOUSE_ODDS);
		} else {
			win = -5;
		}
		// increase current winnings
		this.currWin += win;
		return isWin;
	}
	
	/**
	 * get image to display as a function of slot number and index
	 * @param index number of the slot (must be 1,2,3 or 4)
	 * 
	 * @return image of club, heart, diamond or spade
	 */
	public Image getImage(int index) {
		Image image;
		int i;
		
		// find which slot we want the image from
		if (index==1) {
			i = this.index1;
		} else if (index==2) {
			i = this.index2;
		} else if (index==3) {
			i = this.index3;
		} else if (index==4) {
			i = this.index4;
		} else {
			throw new IndexOutOfBoundsException("Index " + index + " must be 1,2,3 or 4");
		}
		
		// retrieve correct image as a function of that slot's index
		if (i==0) {
			image = new Image("slot_machine/club.png");
		} else if (i==1) {
			image = new Image("slot_machine/diamond.png");
		} else if (i==2) {
			image = new Image("slot_machine/heart.png");
		} else {
			image = new Image("slot_machine/spade.png");
		}
		return image;	
	}
	
	/*
	 * Play slot win sound
	 */
	public MediaPlayer getWinSound() {
	    String path = new File("src/main/java/slot_machine/slot-machine-sound-effect.mp3").toURI().toString();
	    Media media = new Media(path);
	    MediaPlayer mp = new MediaPlayer(media);
	    return mp;
	}
}
