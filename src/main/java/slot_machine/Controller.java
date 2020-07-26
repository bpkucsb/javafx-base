package slot_machine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Controller for slot machine
 * 
 * @author Brendan keller
 *
 */
public class Controller {

	private boolean addFunds = false;             // if more funds need to be added
	private Model model = new Model();            // slot machine model
	private MediaPlayer mp = model.getWinSound(); // slot win sound
	
    @FXML
    private Button spin;       // spin the slots

    @FXML
    private ImageView image1;  // slot 1

    @FXML
    private ImageView image2;  // slot 2

    @FXML
    private ImageView image3;  // slot 3
    
    @FXML
    private ImageView image4;  // slot 4

    @FXML
    private Text currWinText;  // current winnings
    
    @FXML
    private Text netWinText;   // net winnings
    
    @FXML
    private DialogPane flash;  // flash box if slot win

    /**
     * Add money to the slot machine
     * @param money money to be added in integer format
     */
    public void addMoney(int money) {
    	// if was out of funds return text font to black
    	if (addFunds) {
    		currWinText.setFill(Color.BLACK);
    	}
    	// add money to slot machine model
    	model.addMoney(money);
    	// retrieve current and net winnings
		currWinText.setText(intToMoney(model.getCurrWin()));
		netWinText.setText(intToMoney(model.getNetWin()));
    	this.addFunds = false;
    }

    /**
     * add $5
     * @param event add $5 to slot machine on click
     */
    @FXML
    public void add5(ActionEvent event) {
    	addMoney(500);
    }
    
    /**
     * add $10
     * @param event add $10 to slot machine on click
     */
    @FXML
    public void add10(ActionEvent event) {
    	addMoney(1000);
    }
    
    /**
     * add $25
     * @param event add $25 to slot machine on click
     */
    @FXML
    public void add25(ActionEvent event) {
    	addMoney(2500);
    }
    
    /**
     * Spin the slot machine
     * @param event spin slots
     */
    @FXML
    void spinSlots(ActionEvent event) {
    	boolean isWin = false;      // is it a win
    	int currWin;                // current winnings
		flash.setVisible(false);    // hide win background if previously triggered
		// if not out of funds proceed
		if (!addFunds) {
			// check if enough funds and print message if yes
			currWin = moneyToInt(currWinText.getText());
			if (currWin==0) {
				currWinText.setText("ADD $$$");
				currWinText.setFill(Color.RED);
				this.addFunds = true;
			
			} else {
				// spin slot machine model and set appropriate images
				// for the slots
				isWin = model.spin();
				image1.setImage(model.getImage(1));
				image2.setImage(model.getImage(2));
				image3.setImage(model.getImage(3));
				image4.setImage(model.getImage(4));
				
				// launch win background and sound if it is a win
				if (isWin) {
					flash.setVisible(true);
					mp.seek(new Duration(0));
					mp.play();
				}
				
				// Update current and net winnings using slot machine model
				currWinText.setText(intToMoney(model.getCurrWin()));
				netWinText.setText(intToMoney(model.getNetWin()));
			}
		}
    }
    
    /**
     * Converts integer representation of money into string
     * 
     * @param moneyInt integer representation of money
     * @return string representation of money
     */
    public String intToMoney(int moneyInt) {
    	
    	String dollars = Integer.toString(Math.abs(moneyInt / 100));
    	String cents  = Integer.toString(Math.abs(moneyInt % 100));
    	if (cents.length()==1) {
    		cents = "0" + cents;
    	}
    	if (moneyInt < 0) {
    		dollars = "-" + dollars;
    	}
    	return dollars + "." + cents;
    }
    
    /**
     * Converts string representation of money to integer representation
     * @param money string representation of money
     * @return integer representation of money
     */
    public int moneyToInt(String money) {
    	return Integer.parseInt(money.replace(".",""));
    }

}

