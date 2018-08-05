package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;

import events.ChangeViewEvent;
import events.ChangeViewListener;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ViewController {
	
private ChangeViewListener changeViewListener;
private GameLocation gameLocation;

	


	@FXML
	private Label labelBeginning1;
	@FXML
	private Label labelBeginning2;
	@FXML
	private Label labelBeginning3;
	@FXML
	private ListView<String> classList;
	@FXML 
	private ImageView chPicture1;
	@FXML 
	private ImageView chPicture2;
	@FXML 
	private ImageView chPicture3;
	@FXML 
	private ImageView chPicture4;
	@FXML
	private Pane chPicturePane1;
	@FXML
	private Pane chPicturePane2;
	@FXML
	private Pane chPicturePane3;
	@FXML
	private Pane chPicturePane4;
	
	public void initChCreation() {
		classList.getItems().add("Warrior");
		classList.getItems().add("Mage");
		classList.getItems().add("Rogue");
		classList.getSelectionModel().select(0);
		
	}
	
	public void initChangeChPicture() throws FileNotFoundException {
		if (classList.getSelectionModel().getSelectedItem().equals("Warrior")) {
			chPicture1.setImage(new Image(new FileInputStream("src/application/resources/images/warrior1.jpg")));
			chPicture2.setImage(new Image(new FileInputStream("src/application/resources/images/warrior2.jpg")));
			chPicture3.setImage(new Image(new FileInputStream("src/application/resources/images/warrior3.jpg")));
			chPicture4.setImage(new Image(new FileInputStream("src/application/resources/images/warrior4.jpg")));
		}
/*		if (classList.getSelectionModel().getSelectedItem().equals("Mage")) {
			chPicture1.setImage(new Image("mage1.jpg"));
			chPicture2.setImage(new Image("mage2.jpg"));
			chPicture3.setImage(new Image("mage3.jpg"));
			chPicture4.setImage(new Image("mage4.jpg"));
		}
		if (classList.getSelectionModel().getSelectedItem().equals("Warrior")) {
			chPicture1.setImage(new Image("rogue1.jpg"));
			chPicture2.setImage(new Image("rogue2.jpg"));
			chPicture3.setImage(new Image("rogue3.jpg"));
			chPicture4.setImage(new Image("rogue4.jpg"));
		}*/
	}

	@FXML
	public void selectChPicture(Event e) {
		if(e.getSource().equals(chPicture1)) {
			chPicturePane1.setStyle("-fx-border-color: yellow;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
		}
		if(e.getSource().equals(chPicture2)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: yellow;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
		}
		if(e.getSource().equals(chPicture3)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: yellow;");
			chPicturePane4.setStyle("-fx-border-color: white;");
		}
		if(e.getSource().equals(chPicture4)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: yellow;");
		}
			
	}
	
	public GameLocation getGameLocation() {
		return gameLocation;
	}

	public void setGameLocation(GameLocation gameLocation) {
		this.gameLocation = gameLocation;
	}

	@FXML
	public void closeChPicture(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CLOSECHANGECHPICTURE));
		}
	}

	
	@FXML
	public void ExitClick(Event e) {
		System.exit(0);
	}
	
	@FXML
	public void NewGameClick(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.NEWGAME));
		}
	}
	

	@FXML
	public void toMainMenu(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.MAINMENU));
		}
	}
	
	@FXML
	public void toBeginning(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.BEGINNING));
		}
	}
	
	@FXML
	public void toChangeChPicture(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHANGECHPICTURE));
		}
	}
	
	@FXML
	public void toChCreation(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHCREATION));
		}
	}
	
	@FXML
	public void toSkills(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.SKILLS));
		}
	}
	
	@FXML
	public void toVillage(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.VILLAGE));
		}
	}
	
	@FXML
	public void toDungeon(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.DUNGEON));
		}
	}
	
	@FXML
	public void toCharacterPage(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHARACTERPAGE));
		}
	}
	
	@FXML
	public void toInventory(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.INVENTORY));
		}
	}

	@FXML
	public void toGameView(Event e) {
		if (changeViewListener != null) {
			if (gameLocation == GameLocation.VILLAGE) 
				changeViewListener.onChangeView(new ChangeViewEvent(this, Views.VILLAGE));
			if (gameLocation == GameLocation.DUNGEON)
				changeViewListener.onChangeView(new ChangeViewEvent(this, Views.DUNGEON));
		}
	}
	
	public void setChangeViewListener(ChangeViewListener listener) {
		this.changeViewListener = listener;
	}

	public void startBeginningSequence() {
		FadeTransition ft1 = new FadeTransition(Duration.millis(50), labelBeginning1);
		ft1.setFromValue(0.0);
		ft1.setToValue(1.0);
		ft1.setCycleCount(1);
		FadeTransition ft2 = new FadeTransition(Duration.millis(50), labelBeginning2);
		ft2.setFromValue(0.0);
		ft2.setToValue(1.0);
		ft2.setCycleCount(1);
		FadeTransition ft3 = new FadeTransition(Duration.millis(50), labelBeginning3);
		ft3.setFromValue(0.0);
		ft3.setToValue(1.0);
		ft3.setCycleCount(1);

		FadeTransition ft4 = new FadeTransition(Duration.millis(50), labelBeginning1);
		ft4.setFromValue(1.0);
		ft4.setToValue(0.0);
		ft4.setCycleCount(1);       
		ft4.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                labelBeginning1.setText("He wasn't inspired by honor");
            }
        });
		FadeTransition ft5 = new FadeTransition(Duration.millis(50), labelBeginning2);
		ft5.setFromValue(1.0);
		ft5.setToValue(0.0);
		ft5.setCycleCount(1);        
		ft5.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                labelBeginning2.setText("nor he was inspired by virtue");
            }
        });
		FadeTransition ft6 = new FadeTransition(Duration.millis(50), labelBeginning3);
		ft6.setFromValue(1.0);
		ft6.setToValue(0.0);
		ft6.setCycleCount(1);        
		ft6.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                labelBeginning3.setText("he just wanted to slay goblins");
            }
        });
		
		SequentialTransition sequentialTransition = new SequentialTransition();
		sequentialTransition.getChildren().addAll(ft1,ft2,ft3,ft4,ft5,ft6);
		sequentialTransition.setCycleCount(2);
		sequentialTransition.setAutoReverse(false);
		sequentialTransition.play();
		sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
        		if (changeViewListener != null) {
        			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHCREATION));
        		}
            }
        });
		
	}




	
	
	

	
}
