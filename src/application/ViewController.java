package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;


import exceptions.LoadException;
import controller.GameController;
import events.ChangeViewEvent;
import events.ChangeViewListener;
import events.SkillsComboListener;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Location;
import model.Save;
import model.character.SkillsType;
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

	
	//from new game screen and load screen (possibly also options screen)
	@FXML
	public void toMainMenu(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.MAINMENU));
		}
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
	
	//CHARACTER CREATION
	
	private boolean newChCreation;
	//this one is to initialize the second page
	private boolean newChCreation2;
	
	@FXML
	private TextField textChCreationAvaiable;
	@FXML
	private TextField textChCreationStr;
	@FXML
	private TextField textChCreationDex;
	@FXML
	private TextField textChCreationCon;
	@FXML
	private TextField textChCreationInt;
	@FXML
	private TextField textChCreationCha;
	@FXML
	private TextField textChCreationLck;
	@FXML
	private ImageView imageChCreation;
	
	@FXML
	private ListView<String> classList;
	public void initChCreation() {
		if (newChCreation) {
			newChCreation = false;
			classList.getItems().add("Warrior");
			classList.getItems().add("Mage");
			classList.getItems().add("Rogue");
			classList.getSelectionModel().select(0);
			//we add a listener for the change in selected class, so that we can change the character picture
			classList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
					try {
						if (newValue.toString().equals("Warrior"))
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/warrior1.jpg")));
						if (newValue.toString().equals("Mage"))
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/mage1.jpg")));
						if (newValue.toString().equals("Rogue"))
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/rogue1.jpg")));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
			});
			textChCreationStr.setText("50");
			textChCreationDex.setText("50");
			textChCreationCon.setText("50");
			textChCreationInt.setText("50");
			textChCreationCha.setText("50");
			textChCreationLck.setText("50");
			textChCreationAvaiable.setText("30");
		}
	}
	
	//CHARACTER CREATION: SET CHARACTERISTICS
	
	private void increaseCharacteristic(TextField textAvaiable, TextField textCharacteristic) {
		int avaiable = Integer.parseInt(textAvaiable.getText());
		int characteristic = Integer.parseInt(textCharacteristic.getText());
		if (avaiable >0) 
			if (characteristic <100) {
				characteristic++;
				avaiable--;
			}
		textCharacteristic.setText(String.valueOf(characteristic));
		textAvaiable.setText(String.valueOf(avaiable));
	}
	
	private void decreaseCharacteristic(TextField textAvaiable, TextField textCharacteristic, int lowerLimit) {
		int avaiable = Integer.parseInt(textAvaiable.getText());
		int characteristic = Integer.parseInt(textCharacteristic.getText());
		if (characteristic >lowerLimit) {
			characteristic--;
			avaiable++;
		}
		textCharacteristic.setText(String.valueOf(characteristic));
		textAvaiable.setText(String.valueOf(avaiable));
	}
	
	public void buttonChCreationUpStrClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationStr);
	}
	
	public void buttonChCreationDownStrClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationStr, 1);
	}
	
	public void buttonChCreationUpDexClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationDex);
	}
	
	public void buttonChCreationDownDexClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationDex, 1);
	}
	
	public void buttonChCreationUpConClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationCon);
	}
	
	public void buttonChCreationDownConClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationCon, 1);
	}
	
	public void buttonChCreationUpIntClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationInt);
	}
	
	public void buttonChCreationDownIntClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationInt, 1);
	}
	
	public void buttonChCreationUpChaClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationCha);
	}
	
	public void buttonChCreationDownChaClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationCha, 1);
	}
	
	public void buttonChCreationUpLckClicked(Event e) {
		increaseCharacteristic(textChCreationAvaiable, textChCreationLck);
	}
	
	public void buttonChCreationDownLckClicked(Event e) {
		decreaseCharacteristic(textChCreationAvaiable, textChCreationLck, 1);
	}
	
	//CHARACTER CREATION SET PICTURE
	
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
		if (classList.getSelectionModel().getSelectedItem().equals("Mage")) {
			chPicture1.setImage(new Image(new FileInputStream("src/application/resources/images/mage1.jpg")));
			chPicture2.setImage(new Image(new FileInputStream("src/application/resources/images/mage2.jpg")));
			chPicture3.setImage(new Image(new FileInputStream("src/application/resources/images/mage3.jpg")));
			chPicture4.setImage(new Image(new FileInputStream("src/application/resources/images/mage4.jpg")));
		}
		if (classList.getSelectionModel().getSelectedItem().equals("Rogue")) {
			chPicture1.setImage(new Image(new FileInputStream("src/application/resources/images/Rogue1.jpg")));
			chPicture2.setImage(new Image(new FileInputStream("src/application/resources/images/Rogue2.jpg")));
			chPicture3.setImage(new Image(new FileInputStream("src/application/resources/images/Rogue3.jpg")));
			chPicture4.setImage(new Image(new FileInputStream("src/application/resources/images/Rogue4.jpg")));
		}
	}

	@FXML
	public void toChangeChPicture(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHANGECHPICTURE));
		}
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
	private Image selectedChPicture;
	
	@FXML
	public void selectChPicture(Event e) {
		if(e.getSource().equals(chPicture1)) {
			chPicturePane1.setStyle("-fx-border-color: yellow;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			selectedChPicture = chPicture1.getImage();
		}
		if(e.getSource().equals(chPicture2)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: yellow;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			selectedChPicture = chPicture2.getImage();
		}
		if(e.getSource().equals(chPicture3)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: yellow;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			selectedChPicture = chPicture3.getImage();
		}
		if(e.getSource().equals(chPicture4)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: yellow;");
			selectedChPicture = chPicture4.getImage();
		}
			
	}
	
	@FXML
	public void closeChPicture(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CLOSECHANGECHPICTURE));
		}
	}
	
	@FXML
	public void closeChPictureSelected(Event e) {
		if (changeViewListener !=null) {
			imageChCreation.setImage(selectedChPicture);
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CLOSECHANGECHPICTURE));
		}
	}
	
	//goes to character creation page2
	@FXML
	public void toSkills(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.SKILLS));
		}
	}
	
	
	//CHARACTER CREATION PAGE 2
	
	//returns to the first page
	@FXML
	public void toChCreation(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHCREATION));
		}
	}
	
	
	private ObservableList<SkillsType> allSkills;
	
	@FXML
	private ComboBox<SkillsType> comboSkillsPrimary1;
	@FXML
	private ComboBox<SkillsType> comboSkillsPrimary2;
	@FXML
	private ComboBox<SkillsType> comboSkillsPrimary3;
	@FXML
	private ComboBox<SkillsType> comboSkillsSecondary1;
	@FXML
	private ComboBox<SkillsType> comboSkillsSecondary2;
	@FXML
	private ComboBox<SkillsType> comboSkillsSecondary3;
		
	private ObservableList<SkillsType> skillsForPrimary1;	
	private ObservableList<SkillsType> skillsForPrimary2;	
	private ObservableList<SkillsType> skillsForPrimary3;	
	private ObservableList<SkillsType> skillsForSecondary1;	
	private ObservableList<SkillsType> skillsForSecondary2;	
	private ObservableList<SkillsType> skillsForSecondary3;	
	
	private SkillsComboListener listenerForSkillChanges;
	
	public void initChCreation2() {
		if (newChCreation2) {
			newChCreation2 = false;
			allSkills = FXCollections.observableArrayList();
			for(SkillsType type : SkillsType.values()) {
				allSkills.add(type);
			}
			
			skillsForPrimary1 = FXCollections.observableArrayList();
			skillsForPrimary2 = FXCollections.observableArrayList();
			skillsForPrimary3 = FXCollections.observableArrayList();
			skillsForSecondary1 = FXCollections.observableArrayList();
			skillsForSecondary2 = FXCollections.observableArrayList();
			skillsForSecondary3 = FXCollections.observableArrayList();

			skillsForPrimary1.addAll(allSkills);
			skillsForPrimary2.addAll(allSkills);
			skillsForPrimary3.addAll(allSkills);
			skillsForSecondary1.addAll(allSkills);
			skillsForSecondary2.addAll(allSkills);
			skillsForSecondary3.addAll(allSkills);
					
			comboSkillsPrimary1.setItems(skillsForPrimary1);
			comboSkillsPrimary2.setItems(skillsForPrimary2);
			comboSkillsPrimary3.setItems(skillsForPrimary3);
			comboSkillsSecondary1.setItems(skillsForSecondary1);
			comboSkillsSecondary2.setItems(skillsForSecondary2);
			comboSkillsSecondary3.setItems(skillsForSecondary3);
			
			comboSkillsPrimary1.getSelectionModel().select(SkillsType.NONE);
			comboSkillsPrimary2.getSelectionModel().select(SkillsType.NONE);
			comboSkillsPrimary3.getSelectionModel().select(SkillsType.NONE);
			comboSkillsSecondary1.getSelectionModel().select(SkillsType.NONE);
			comboSkillsSecondary2.getSelectionModel().select(SkillsType.NONE);
			comboSkillsSecondary3.getSelectionModel().select(SkillsType.NONE);
			
			listenerForSkillChanges = new SkillsComboListener(comboSkillsPrimary1, comboSkillsPrimary2, comboSkillsPrimary3,
						comboSkillsSecondary1, comboSkillsSecondary2, comboSkillsSecondary3,
						skillsForPrimary1, skillsForPrimary2, skillsForPrimary3, skillsForSecondary1, skillsForSecondary2, skillsForSecondary3);
			
			
			comboSkillsPrimary1.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);
			comboSkillsPrimary2.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);
			comboSkillsPrimary3.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);
			comboSkillsSecondary1.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);
			comboSkillsSecondary2.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);
			comboSkillsSecondary3.getSelectionModel().selectedItemProperty().addListener(listenerForSkillChanges);	
			
		}
	}
	
	
	

	
	public Location getGameLocation() {
		return gameLocation;
	}

	public void setGameLocation(Location gameLocation) {
		this.gameLocation = gameLocation;
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

	
	//BEGINNING ANIMATION
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
        			//if i come to the character creation page from the beginning sequence then i reset it's contents
        			newChCreation = true;
        			newChCreation2 = true;
        			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHCREATION));
        		}
            }
        });
		
	}




	
	
	

	
}
