package hellofx;

import java.io.File;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {

	private boolean addFunds = false;
	
    String path = new File("src/main/java/hellofx/slot-machine-sound-effect.mp3").toURI().toString();
    Media media = new Media(path);
    MediaPlayer mp = new MediaPlayer(media);
	
    @FXML
    private Button spin;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;
    
    @FXML
    private ImageView image4;

    @FXML
    private Text currWinText;
    
    @FXML
    private Text netWinText;
    
    @FXML
    private DialogPane flash;

    @FXML
    public void add5(ActionEvent event) {
    	String currWin = currWinText.getText();
    	String netWin  = netWinText.getText();
    	if (addFunds) {
    		currWin = "0.00";
    		currWinText.setFill(Color.BLACK);
    	}
    	double w = 500;
    	currWinText.setText(addMoney(currWin, w));
    	netWinText.setText(addMoney(currWin, -w));
    	this.addFunds = false;
    }
    
    @FXML
    public void add10(ActionEvent event) {
    	String currWin = currWinText.getText();
    	String netWin  = netWinText.getText();
    	if (addFunds) {
    		currWin = "0.00";
    		currWinText.setFill(Color.BLACK);
    	}
    	double w = 1000;
    	currWinText.setText(addMoney(currWin, w));
    	netWinText.setText(addMoney(currWin, -w));
    	this.addFunds = false;
    }
    
    @FXML
    public void add25(ActionEvent event) {
    	String currWin = currWinText.getText();
    	String netWin  = netWinText.getText();
    	if (addFunds) {
    		currWin = "0.00";
    		currWinText.setFill(Color.BLACK);
    	}
    	double w = 2500;
    	currWinText.setText(addMoney(currWin, w));
    	netWinText.setText(addMoney(currWin, -w));
    	this.addFunds = false;
    }
    
    @FXML
    void spinSlots(ActionEvent event) {
		flash.setVisible(false);
		String currWin = currWinText.getText();
		String netWin  = netWinText.getText();
		if (!addFunds) {
			if (Double.parseDouble(currWin)==0) {
				currWinText.setText("ADD $$$");
				currWinText.setFill(Color.RED);
				this.addFunds = true;
			
			} else {
				Model model = new Model();
				Random rand = new Random();
				int w;
				int index1 = rand.nextInt(4);
				int index2 = rand.nextInt(4);
				int index3 = rand.nextInt(4);
				int index4 = rand.nextInt(4);
				image1.setImage(model.getImage(index1));
				image2.setImage(model.getImage(index2));
				image3.setImage(model.getImage(index3));
				image4.setImage(model.getImage(index4));
				if ((index1==index2) && (index2==index3) && (index3==index4)) {
					flash.setVisible(true);
					mp.seek(new Duration(0));
					mp.play();
					w = 5*(4*4*4-1);
				} else {
					w = -5;
				}
				currWinText.setText(addMoney(currWin, w));
				netWinText.setText(addMoney(netWin, w));
			}
		}
    }
    
    public String addMoney(String x, double y) {
    	double a = Double.parseDouble(x)*100;
    	return String.format("%.2f",(a + y)*0.01);
    }

}

