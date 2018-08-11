package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import exceptions.LoadException;
import controller.GameController;
import events.ChangeViewEvent;
import events.ChangeViewListener;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Location;
import model.Save;
import textfield.TextField20;

public class ViewController {
	
	private GameController gameController;
	private ChangeViewListener changeViewListener;
	private Location gameLocation;

	
	public ViewController(GameController gameController) {
		this.gameController = gameController;
	}
	
	//MAIN MENU
	
	@FXML
	public void NewGameClick(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.NEWGAME));
		}
	}
	
	@FXML
	public void ExitClick(Event e) {
		System.exit(0);
	}
	
	//NEW GAME SCREEN
	
	@FXML
	private Label labelDateNewGame1;
	@FXML
	private Label labelDateNewGame2;
	@FXML
	private Label labelDateNewGame3;	
	@FXML
	private ImageView imageNewGame1;
	@FXML
	private ImageView imageNewGame2;
	@FXML
	private ImageView imageNewGame3;
	@FXML
	private TextField20 textNewGame1;
	@FXML
	private TextField20 textNewGame2;
	@FXML
	private TextField20 textNewGame3;
	@FXML
	private Button buttonNewGameOk;
	
	private String save1Name = "";
	private String save2Name = "";
	private String save3Name = "";

	
	//initializes the new game screen, loading info about current saves
	public void initNewGame() throws FileNotFoundException {
		//button is disabled until you click on one of the save's textfield
		buttonNewGameOk.setDisable(true);
		//try to load save1 to see if it's present
		if (gameController.preLoad("src/application/resources/saves/Save1.ser")) {
			//if it's present actually load it's content to display in the textbox
			Save save1;
			try {
			save1 = gameController.load("src/application/resources/saves/Save1.ser");
			//i save the name in the variable to retrieve it for when i click around
			save1Name = save1.getSaveName();
			//load the name in the textfield
			textNewGame1.setText(save1Name);
			//load the date in the label
			labelDateNewGame1.setText(save1.getTimeOfSave().toString());
			//load the picture based on the location
			if (save1.getLocation() == Location.VILLAGE)
				imageNewGame1.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageNewGame1.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				//if preload says the save is fine but we still have an exception it means that the file is corrupt
				textNewGame1.setText("Save Corrupt");
				labelDateNewGame1.setText("xxxxxxxxxxxxx");
			}

		}
		if (gameController.preLoad("src/application/resources/saves/Save2.ser")) {
			Save save2;
			try {
			save2 = gameController.load("src/application/resources/saves/Save2.ser");
			save2Name = save2.getSaveName();
			textNewGame2.setText(save2Name);
			labelDateNewGame2.setText(save2.getTimeOfSave().toString());
			if (save2.getLocation() == Location.VILLAGE)
				imageNewGame2.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageNewGame2.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				textNewGame2.setText("Save Corrupt");
				labelDateNewGame2.setText("xxxxxxxxxxxxx");
			}

		}
		if (gameController.preLoad("src/application/resources/saves/Save3.ser")) {
			Save save3;
			try {
			save3 = gameController.load("src/application/resources/saves/Save3.ser");
			save3Name = save3.getSaveName();
			textNewGame3.setText(save3Name);
			labelDateNewGame1.setText(save3.getTimeOfSave().toString());
			if (save3.getLocation() == Location.VILLAGE)
				imageNewGame3.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageNewGame3.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				textNewGame3.setText("Save Corrupt");
				labelDateNewGame3.setText("xxxxxxxxxxxxx");
			}

		}
	}

	private String selectedSave;

	@FXML //clicking on the textFields
	public void textNewGame1Clicked(Event e) {
		//if i click on a textField the selected Save becomes the one i clicked on
		selectedSave = "Save1";
		//it's border is the only one that becomes yellow
		textNewGame1.setStyle("-fx-border-color: yellow");
		//i set the label to invisible, so that i don't see the eventual date while editing the savegame
		labelDateNewGame1.setVisible(false);
		textNewGame2.setStyle("-fx-border-color: black");
		//the other two textFields have their text restored.
		textNewGame2.setText(save2Name);
		labelDateNewGame2.setVisible(true);
		textNewGame3.setStyle("-fx-border-color: black");
		textNewGame3.setText(save3Name);
		labelDateNewGame3.setVisible(true);
		buttonNewGameOk.setDisable(false);
	}

	@FXML
	public void textNewGame2Clicked(Event e) {
		selectedSave = "Save2";
		textNewGame1.setStyle("-fx-border-color: black");
		textNewGame1.setText(save1Name);
		labelDateNewGame1.setVisible(true);
		textNewGame2.setStyle("-fx-border-color: yellow");
		labelDateNewGame2.setVisible(false);
		textNewGame3.setStyle("-fx-border-color: black");
		textNewGame3.setText(save3Name);
		labelDateNewGame3.setVisible(true);
		buttonNewGameOk.setDisable(false);
	}

	@FXML
	public void textNewGame3Clicked(Event e) {
		selectedSave = "Save3";
		textNewGame1.setStyle("-fx-border-color: black");
		textNewGame1.setText(save1Name);
		labelDateNewGame1.setVisible(true);
		textNewGame2.setStyle("-fx-border-color: black");
		textNewGame2.setText(save2Name);
		labelDateNewGame2.setVisible(true);
		textNewGame3.setStyle("-fx-border-color: yellow");
		labelDateNewGame3.setVisible(false);
		buttonNewGameOk.setDisable(false);
	}

	//from new game screen
	@FXML
	public void toBeginning(Event e) {
		//once i click ok and select my save i go to the beginning animation
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.BEGINNING));
			
			//based on the slot i selected i create a new game overwritting the old one 
			//NOTE: i do not give a warrning to the user if you are overWriting an old game
			if (selectedSave.equals("Save1"))
				gameController.newGame("src/application/resources/saves/Save1.ser", textNewGame1.getText());
			if (selectedSave.equals("Save2"))
				gameController.newGame("src/application/resources/saves/Save2.ser", textNewGame2.getText());
			if (selectedSave.equals("Save3"))
				gameController.newGame("src/application/resources/saves/Save3.ser", textNewGame3.getText());
		}
	}
	
	
	
	@FXML
	private ListView<String> classList;
	public void initChCreation() {
		classList.getItems().add("Warrior");
		classList.getItems().add("Mage");
		classList.getItems().add("Rogue");
		classList.getSelectionModel().select(0);
		
	}
	
	@FXML 
	private ImageView chPicture1;
	@FXML 
	private ImageView chPicture2;
	@FXML 
	private ImageView chPicture3;
	@FXML 
	private ImageView chPicture4;
	
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
	private Pane chPicturePane1;
	@FXML
	private Pane chPicturePane2;
	@FXML
	private Pane chPicturePane3;
	@FXML
	private Pane chPicturePane4;
	
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
	
	public Location getGameLocation() {
		return gameLocation;
	}

	public void setGameLocation(Location gameLocation) {
		this.gameLocation = gameLocation;
	}

	@FXML
	public void closeChPicture(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CLOSECHANGECHPICTURE));
		}
	}

	

	

	

	@FXML
	public void toMainMenu(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.MAINMENU));
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
			if (gameLocation == Location.VILLAGE) 
				changeViewListener.onChangeView(new ChangeViewEvent(this, Views.VILLAGE));
			if (gameLocation == Location.DUNGEON)
				changeViewListener.onChangeView(new ChangeViewEvent(this, Views.DUNGEON));
		}
	}
	
	public void setChangeViewListener(ChangeViewListener listener) {
		this.changeViewListener = listener;
	}

	@FXML
	private Label labelBeginning1;
	@FXML
	private Label labelBeginning2;
	@FXML
	private Label labelBeginning3;
	
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
