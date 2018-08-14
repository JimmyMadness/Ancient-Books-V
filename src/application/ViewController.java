package application;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;

import exceptions.LoadException;
import controller.GameController;
import events.ChangeViewEvent;
import events.ChangeViewListener;
import events.SkillsComboListener;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import model.Location;
import model.Save;
import model.ShopEntry;
import model.Items.Item;
import model.character.RpgClass;
import model.character.Sex;
import model.character.SkillsType;
import textfield.TextField20;
import textfield.TextFieldNum;

public class ViewController {
	
	private GameController gameController;
	private ChangeViewListener changeViewListener;
	private Location gameLocation;

	
	public ViewController(GameController gameController) {
		this.gameController = gameController;
	}
	
	@FXML
	private Label popupTitle;
	@FXML
	private Label popupLabel1;
	@FXML
	private Label popupLabel2;
	@FXML
	private Label popupLabel3;
	@FXML
	private Label popupLabel4;
	@FXML
	private Label popupLabel5;
	@FXML
	private Button popupButtonClose;
	
	//POPUP
	public void popup(String title, String label1, String label2, String label3, String label4, String label5) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("resources/Popup.fxml"));
		loader.setController(this);
		
		Stage popupStage;
		AnchorPane popupPane;
		Scene popupScene;
		
		try {
			popupPane = (AnchorPane)loader.load();
			popupScene = new Scene(popupPane);
			popupStage = new Stage();
			popupStage.initStyle(StageStyle.UNDECORATED);
			popupStage.setResizable(false);
			popupStage.setAlwaysOnTop(true);
			popupStage.initModality(Modality.APPLICATION_MODAL);
			popupStage.setScene(popupScene);
			popupStage.show();
			popupStage.requestFocus();
			popupStage.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
			
			popupTitle.setText(title);
			if (!label1.equals("")) {
				popupLabel1.setVisible(true);
				popupLabel1.setText(label1);
			}
			if (!label2.equals("")) {
				popupLabel2.setVisible(true);
				popupLabel2.setText(label2);
			}
			if (!label3.equals("")) {
				popupLabel3.setVisible(true);
				popupLabel3.setText(label3);
			}
			if (!label4.equals("")) {
				popupLabel4.setVisible(true);
				popupLabel4.setText(label4);
			}
			if (!label5.equals("")) {
				popupLabel5.setVisible(true);
				popupLabel5.setText(label5);
			}
			
			popupButtonClose.setOnMouseReleased(e->{
				popupStage.hide();
			});
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//MAIN MENU
	
	
	
	@FXML
	public void NewGameClick(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.NEWGAME));
		}
	}
	
	@FXML
	public void LoadGameClick(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.LOADGAME));
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
		DateTimeFormatter dateFormatter;
		dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
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
			labelDateNewGame1.setText(dateFormatter.format(save1.getTimeOfSave()));
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
			labelDateNewGame2.setText(dateFormatter.format(save2.getTimeOfSave()));
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
			labelDateNewGame1.setText(dateFormatter.format(save3.getTimeOfSave()));
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

	
	//LOAD GAME SCREEN
	//TODO: Loading doesn't work
	//TODO: change the logic for which the button loads, if a save is not present clicking on it should do nothing
	//right now it changes the save to an invalid one and then disables the button
	//just check if the save is valid immediately, and do nothing if it's not
	
	@FXML
	private Label labelDateLoadGame1;
	@FXML
	private Label labelDateLoadGame2;
	@FXML
	private Label labelDateLoadGame3;	
	@FXML
	private ImageView imageLoadGame1;
	@FXML
	private ImageView imageLoadGame2;
	@FXML
	private ImageView imageLoadGame3;
	@FXML
	private TextField20 textLoadGame1;
	@FXML
	private TextField20 textLoadGame2;
	@FXML
	private TextField20 textLoadGame3;
	@FXML
	private Button buttonLoadGameOk;
	
	public void initLoadGame()throws FileNotFoundException {
		DateTimeFormatter dateFormatter;
		dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		//button is disabled until you click on one of the save's textfield
		buttonLoadGameOk.setDisable(true);
		//try to load save1 to see if it's present
		if (gameController.preLoad("src/application/resources/saves/Save1.ser")) {
			//if it's present actually load it's content to display in the textbox
			Save save1;
			try {
			save1 = gameController.load("src/application/resources/saves/Save1.ser");
			//i save the name in the variable to retrieve it for when i click around
			save1Name = save1.getSaveName();
			//load the name in the textfield
			textLoadGame1.setText(save1Name);
			//load the date in the label
			labelDateLoadGame1.setText(dateFormatter.format(save1.getTimeOfSave()));
			//load the picture based on the location
			if (save1.getLocation() == Location.VILLAGE)
				imageLoadGame1.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageLoadGame1.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				//if preload says the save is fine but we still have an exception it means that the file is corrupt
				textLoadGame1.setText("Save Corrupt");
				labelDateLoadGame1.setText("xxxxxxxxxxxxx");
			}

		}
		if (gameController.preLoad("src/application/resources/saves/Save2.ser")) {
			Save save2;
			try {
			save2 = gameController.load("src/application/resources/saves/Save2.ser");
			save2Name = save2.getSaveName();
			textLoadGame2.setText(save2Name);
			labelDateLoadGame2.setText(dateFormatter.format(save2.getTimeOfSave()));
			if (save2.getLocation() == Location.VILLAGE)
				imageLoadGame2.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageLoadGame2.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				textLoadGame2.setText("Save Corrupt");
				labelDateLoadGame2.setText("xxxxxxxxxxxxx");
			}

		}
		if (gameController.preLoad("src/application/resources/saves/Save3.ser")) {
			Save save3;
			try {
			save3 = gameController.load("src/application/resources/saves/Save3.ser");
			save3Name = save3.getSaveName();
			textLoadGame3.setText(save3Name);
			labelDateLoadGame1.setText(dateFormatter.format(save3.getTimeOfSave()));
			if (save3.getLocation() == Location.VILLAGE)
				imageLoadGame3.setImage(new Image(new FileInputStream("src/application/resources/images/village.jpg")));
			else 
				imageLoadGame3.setImage(new Image(new FileInputStream("src/application/resources/images/testcave2.jpg")));
			}catch(LoadException e) {
				textLoadGame3.setText("Save Corrupt");
				labelDateLoadGame3.setText("xxxxxxxxxxxxx");
			}

		}
	}
	
	@FXML //clicking on the textFields
	public void textLoadGame1Clicked(Event e) {
		if (!textLoadGame1.getText().equals("Game 1")) {
			buttonLoadGameOk.setDisable(false);
			//if i click on a textField the selected Save becomes the one i clicked on
			selectedSave = "Save1";
			//it's border is the only one that becomes yellow
			textLoadGame1.setStyle("-fx-border-color: yellow");
			textLoadGame2.setStyle("-fx-border-color: black");
			textLoadGame3.setStyle("-fx-border-color: black");
		}
	}

	@FXML
	public void textLoadGame2Clicked(Event e) {
		if (!textLoadGame2.getText().equals("Game 2")) {
			buttonLoadGameOk.setDisable(false);
			selectedSave = "Save2";
			textLoadGame1.setStyle("-fx-border-color: black");
			textLoadGame2.setStyle("-fx-border-color: yellow");
			textLoadGame3.setStyle("-fx-border-color: black");
		}
	}

	@FXML
	public void textLoadGame3Clicked(Event e) {
		if (!textLoadGame3.getText().equals("Game 3")) {
			buttonLoadGameOk.setDisable(false);
			selectedSave = "Save3";
			textLoadGame1.setStyle("-fx-border-color: black");
			textLoadGame2.setStyle("-fx-border-color: black");
			textLoadGame3.setStyle("-fx-border-color: yellow");
		}
	}

	
	//from new game screen and load screen (possibly also options screen)
	@FXML
	public void toMainMenu(Event e) {
		if (changeViewListener !=null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.MAINMENU));
		}
	}
	
	//FROM NEW GAME SCREEN...
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
	
	//FROM LOAD SCREEN....
	@FXML
	public void loadGameClicked(Event e) throws LoadException {

		// based on the slot i selected i load the game
		// calling the load method with true sets teh controller save and character as
		// the ones loaded
		if (selectedSave.equals("Save1"))
			gameController.load("src/application/resources/saves/Save1.ser", true);
		if (selectedSave.equals("Save2"))
			gameController.load("src/application/resources/saves/Save2.ser", true);
		if (selectedSave.equals("Save3"))
			gameController.load("src/application/resources/saves/Save3.ser", true);

		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.VILLAGE));
		}
				
	}
	
	//
	//CHARACTER CREATION
	//
	
	private boolean newChCreation;
	//this one is to initialize the second page
	private boolean newChCreation2;
	
	@FXML
	private TextField20 textChCreationName;
	@FXML
	private TextFieldNum textChCreationAge;
	@FXML
	private RadioButton radioChCreationMale;
	@FXML
	private RadioButton radioChCreationFemale;
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
	private ListView<RpgClass> classList;
	
	public void initChCreation() {
		if (newChCreation) {
			newChCreation = false;
			classList.getItems().add(RpgClass.WARRIOR);
			classList.getItems().add(RpgClass.MAGE);
			classList.getItems().add(RpgClass.ROGUE);
			classList.getSelectionModel().select(0);
			//we add a listener for the change in selected class, so that we can change the character picture
			classList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
					try {
						if (newValue.toString().equals("Warrior")) {
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/warrior1.jpg")));
							selectedChPicture = "src/application/resources/images/warrior1.jpg";
						}
						if (newValue.toString().equals("Mage")) {
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/mage1.jpg")));
							selectedChPicture = "src/application/resources/images/mage1.jpg";
						}
						if (newValue.toString().equals("Rogue")) {
							imageChCreation.setImage(new Image(new FileInputStream("src/application/resources/images/rogue1.jpg")));
							selectedChPicture = "src/application/resources/images/rogue1.jpg";
						}
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
		if (classList.getSelectionModel().getSelectedItem().toString().equals("Warrior")) {
			chPicture1.setImage(new Image(new FileInputStream("src/application/resources/images/warrior1.jpg")));
			chPicture2.setImage(new Image(new FileInputStream("src/application/resources/images/warrior2.jpg")));
			chPicture3.setImage(new Image(new FileInputStream("src/application/resources/images/warrior3.jpg")));
			chPicture4.setImage(new Image(new FileInputStream("src/application/resources/images/warrior4.jpg")));
		}
		if (classList.getSelectionModel().getSelectedItem().toString().equals("Mage")) {
			chPicture1.setImage(new Image(new FileInputStream("src/application/resources/images/mage1.jpg")));
			chPicture2.setImage(new Image(new FileInputStream("src/application/resources/images/mage2.jpg")));
			chPicture3.setImage(new Image(new FileInputStream("src/application/resources/images/mage3.jpg")));
			chPicture4.setImage(new Image(new FileInputStream("src/application/resources/images/mage4.jpg")));
		}
		if (classList.getSelectionModel().getSelectedItem().toString().equals("Rogue")) {
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
	private String selectedChPicture = "src/application/resources/images/warrior1.jpg";
	
	@FXML
	public void selectChPicture(Event e) {
		if(e.getSource().equals(chPicture1)) {
			chPicturePane1.setStyle("-fx-border-color: yellow;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Warrior"))
				selectedChPicture = "src/application/resources/images/warrior1.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Mage"))
				selectedChPicture = "src/application/resources/images/mage1.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Rogue"))
				selectedChPicture = "src/application/resources/images/rogue1.jpg";
		}
		if(e.getSource().equals(chPicture2)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: yellow;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Warrior"))
				selectedChPicture = "src/application/resources/images/warrior2.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Mage"))
				selectedChPicture = "src/application/resources/images/mage2.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Rogue"))
				selectedChPicture = "src/application/resources/images/rogue2.jpg";
		}
		if(e.getSource().equals(chPicture3)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: yellow;");
			chPicturePane4.setStyle("-fx-border-color: white;");
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Warrior"))
				selectedChPicture = "src/application/resources/images/warrior3.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Mage"))
				selectedChPicture = "src/application/resources/images/mage3.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Rogue"))
				selectedChPicture = "src/application/resources/images/rogue3.jpg";
		}
		if(e.getSource().equals(chPicture4)) {
			chPicturePane1.setStyle("-fx-border-color: white;");
			chPicturePane2.setStyle("-fx-border-color: white;");
			chPicturePane3.setStyle("-fx-border-color: white;");
			chPicturePane4.setStyle("-fx-border-color: yellow;");
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Warrior"))
				selectedChPicture = "src/application/resources/images/warrior4.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Mage"))
				selectedChPicture = "src/application/resources/images/mage4.jpg";
			if (classList.getSelectionModel().getSelectedItem().toString().equals("Rogue"))
				selectedChPicture = "src/application/resources/images/rogue4.jpg";
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
			try {
				imageChCreation.setImage(new Image(new FileInputStream(selectedChPicture)));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
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
	
	@FXML
	public void confirmCharacterClicked(Event e) {
		String usedCharacteristicsPoints = "";
		if (Integer.parseInt(textChCreationAvaiable.getText())>0)
			usedCharacteristicsPoints = "Your hero misses some characteristics";
		String hasName = "";
		if(textChCreationName.getText().equals(""))
			hasName = "Your hero has no name...";
		String hasAge = "";
		if(textChCreationAge.getText().equals(""))
			hasAge = "your hero has no age...";
		String hasSkills = "";
		if (comboSkillsPrimary1.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		if (comboSkillsPrimary2.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		if (comboSkillsPrimary3.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		if (comboSkillsSecondary1.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		if (comboSkillsSecondary2.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		if (comboSkillsSecondary3.getSelectionModel().getSelectedItem().equals(SkillsType.NONE))
			hasSkills = "Your hero isn't fully skilled";
		
		if (!(usedCharacteristicsPoints.equals("") && hasName.equals("") && hasAge.equals("") && hasSkills.equals(""))) {
			popup("Cannot create this hero", usedCharacteristicsPoints, hasName, hasAge, hasSkills, "");
		}
		else {
			int str = Integer.parseInt(textChCreationStr.getText());
			int dex = Integer.parseInt(textChCreationDex.getText());
			int con = Integer.parseInt(textChCreationCon.getText());
			int intl = Integer.parseInt(textChCreationInt.getText());
			int cha = Integer.parseInt(textChCreationCha.getText());
			int lck = Integer.parseInt(textChCreationLck.getText());
			String name = textChCreationName.getText();
			Sex sex;
			if(radioChCreationFemale.isArmed())
				sex = Sex.FEMALE;
			else
				sex = Sex.MALE;
			int age = Integer.parseInt(textChCreationAge.getText());
			RpgClass rpgClass = classList.getSelectionModel().getSelectedItem();
			SkillsType[] primarySkills = new SkillsType[] {comboSkillsPrimary1.getSelectionModel().getSelectedItem(),
					comboSkillsPrimary2.getSelectionModel().getSelectedItem(),comboSkillsPrimary3.getSelectionModel().getSelectedItem()};
			SkillsType[] secondarySkills = new SkillsType[] {comboSkillsSecondary1.getSelectionModel().getSelectedItem(),
					comboSkillsSecondary2.getSelectionModel().getSelectedItem(),comboSkillsSecondary3.getSelectionModel().getSelectedItem()};
			
			gameController.setStartingCharacter(str, dex, con, intl, cha, lck, name, sex, age, selectedChPicture, rpgClass, primarySkills, secondarySkills);
			gameController.save();
			if (changeViewListener != null) {
				changeViewListener.onChangeView(new ChangeViewEvent(this, Views.VILLAGE));
			}
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
	
	//
	//VILLAGE
	//
	//i won't be using properties cause to do that i would have to refactor the whole character model
	//and by doing so i would lose it's serializability. So i have to work with manually changing everything each time
	//i refresh the page or something changes
	//for example i have to manually set the gold label each time the character's gold changes due to a transaction.
	@FXML
	private Label villageGoldLabel;
	@FXML
	private Label villageChGoldLabel;
	@FXML
	private Label villageTitleLabel;
	@FXML
	private Button villageTransactionButton;
	@FXML
	private Button villageItemsShownButton;
	@FXML
	private Label villageChNameLabel;
	@FXML
	private Label villageChClassLabel;
	@FXML
	private Label villageChHpLabel;
	@FXML
	private Label villageChXpLabel;
	
	@FXML
	private ImageView villageChImage;
	
	@FXML
	private TableView<ShopEntry> villageItemTable;
	@FXML
	private TableColumn<ShopEntry,String> villageItemList;
	@FXML
	private TableColumn<ShopEntry,Integer> villageQuantityList;
	@FXML
	private TableColumn<ShopEntry,Integer> villagePriceList;
	
	private boolean isShopShown;
	private boolean doIRefreshShop = true;
	
	public void initVillage() {
		//villageItemTable.getSelectionModel().setCellSelectionEnabled(true);
		isShopShown = true;
		villageQuantityList.setStyle( "-fx-alignment: CENTER;");
		villagePriceList.setStyle( "-fx-alignment: CENTER;");
		villageChGoldLabel.setText(String.valueOf(gameController.getCharacter().getGold()));
		if (doIRefreshShop) {	
			gameController.shopRefresh();
			doIRefreshShop = false;
		}
		List<ShopEntry> items = gameController.getShop().getShopEntries();
		//i show the shop's gold after it's been refreshed
		villageGoldLabel.setText(String.valueOf(gameController.getShop().getAvaiableGold()));
		ObservableList<ShopEntry> observableItems = FXCollections.observableArrayList(items);
		villageItemTable.setItems(observableItems);
		villageItemList.setCellValueFactory(new Callback<CellDataFeatures<ShopEntry, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<ShopEntry, String> entry) {
		         return entry.getValue().getItemName();
		     }
		  });
		
		villageQuantityList.setCellValueFactory(new Callback<CellDataFeatures<ShopEntry, Integer>, ObservableValue<Integer>>() {
		     public ObservableValue<Integer> call(CellDataFeatures<ShopEntry, Integer> entry) {
		         return entry.getValue().getItemQuantity().asObject();
		     }
		  });
		
		villagePriceList.setCellValueFactory(new Callback<CellDataFeatures<ShopEntry, Integer>, ObservableValue<Integer>>() {
		     public ObservableValue<Integer> call(CellDataFeatures<ShopEntry, Integer> entry) {
		         return entry.getValue().getItemPrice().asObject();
		     }
		  });
		
		//initializing character info
		villageChNameLabel.setText(gameController.getCharacter().getName());
		try {
			villageChImage.setImage(new Image(new FileInputStream(gameController.getCharacter().getCharacterPicture())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		villageChClassLabel.setText(gameController.getCharacter().getRpgClass() + " lvl " + gameController.getCharacter().getLvl());
		villageChHpLabel.setText(gameController.getCharacter().getCurrentHP() + "/" + gameController.getCharacter().getMaxHP() + " Hp");
		villageChXpLabel.setText(gameController.getCharacter().getXp() + " Xp");
	}
	
	@FXML
	public void transactionButtonClicked(Event e) {
		ShopEntry entryForTransaction = villageItemTable.getSelectionModel().getSelectedItem();
		if (entryForTransaction != null)
			if (isShopShown) {
				Item toBuy = gameController.getShop().getItems().keySet().stream().filter(
						item->item.getName().equals(entryForTransaction.getItemName().get())).findFirst().get(); 
				if (gameController.getShop().canSell(toBuy, gameController.getCharacter())) {
					gameController.getShop().sell(toBuy, gameController.getCharacter());
					entryForTransaction.getItemQuantity().setValue(entryForTransaction.getItemQuantity().get()-1);
					if (entryForTransaction.getItemQuantity().get() <= 0)
						villageItemTable.getItems().remove(entryForTransaction);
				}
			}
			else {
				Item toSell = gameController.getCharacter().getInventory().getItems().stream().filter(
						item->item.getName().equals(entryForTransaction.getItemName().get())).findFirst().get(); 
				if (gameController.getShop().canBuy(toSell)) {
					gameController.getShop().buy(toSell, gameController.getCharacter());
					entryForTransaction.getItemQuantity().setValue(entryForTransaction.getItemQuantity().get()-1);
					if (entryForTransaction.getItemQuantity().get() <= 0)
						villageItemTable.getItems().remove(entryForTransaction);
				}
				
			}
		villageChGoldLabel.setText(String.valueOf(gameController.getCharacter().getGold()));
		villageGoldLabel.setText(String.valueOf(gameController.getShop().getAvaiableGold()));
	}
	
	@FXML
	public void itemsShownChange(Event e) {	
		if (isShopShown) {
			List<ShopEntry> chItems = gameController.getChInventoryForShop();
			ObservableList<ShopEntry> observableChItems = FXCollections.observableArrayList(chItems);
			villageItemTable.setItems(observableChItems);
			villageTransactionButton.setText("Sell");
			villageItemsShownButton.setText("Show the shop's items");
			villageTitleLabel.setText("Your Inventory");
			isShopShown = false;
		}
		else {
			List<ShopEntry> items = gameController.getShop().getShopEntries();
			ObservableList<ShopEntry> observableItems = FXCollections.observableArrayList(items);
			villageItemTable.setItems(observableItems);
			villageTransactionButton.setText("Buy");
			villageItemsShownButton.setText("Show your items");
			villageTitleLabel.setText("Vendor's Items");
			isShopShown = true;
		}
	}
	
	@FXML
	public void toCharacterPage(Event e) {
		if (changeViewListener != null) {
			changeViewListener.onChangeView(new ChangeViewEvent(this, Views.CHARACTERPAGE));
		}
	}
	
	
	//CHARACHTER PAGE
	
	@FXML
	private Label chPageNameLabel;
	@FXML
	private Label chPageClassLabel;
	@FXML
	private Label chPageHpLabel;
	@FXML
	private Label chPageXpLabel;
	
	@FXML
	private TextField chPageStrText;
	@FXML
	private TextField chPageDexText;
	@FXML
	private TextField chPageConText;
	@FXML
	private TextField chPageIntText;
	@FXML
	private TextField chPageChaText;
	@FXML
	private TextField chPageLckText;
	
	@FXML
	private Label chPageSkill1Label;
	@FXML
	private Label chPageSkill2Label;
	@FXML
	private Label chPageSkill3Label;
	@FXML
	private Label chPageSkill4Label;
	@FXML
	private Label chPageSkill5Label;
	@FXML
	private Label chPageSkill6Label;
	@FXML
	private Label chPageSkill7Label;
	@FXML
	private Label chPageSkill8Label;
	@FXML
	private Label chPageSkill9Label;
	@FXML
	private Label chPageSkill10Label;
	@FXML
	private Label chPageSkill11Label;
	
	@FXML
	private TextField chPageSkill1Text;
	@FXML
	private TextField chPageSkill2Text;
	@FXML
	private TextField chPageSkill3Text;
	@FXML
	private TextField chPageSkill4Text;
	@FXML
	private TextField chPageSkill5Text;
	@FXML
	private TextField chPageSkill6Text;
	@FXML
	private TextField chPageSkill7Text;
	@FXML
	private TextField chPageSkill8Text;
	@FXML
	private TextField chPageSkill9Text;
	@FXML
	private TextField chPageSkill10Text;
	@FXML
	private TextField chPageSkill11Text;
	
	
	public void initCharacterPage() {
		chPageNameLabel.setText(gameController.getCharacter().getName());
		try {
			villageChImage.setImage(new Image(new FileInputStream(gameController.getCharacter().getCharacterPicture())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		chPageClassLabel.setText(gameController.getCharacter().getRpgClass() + " lvl " + gameController.getCharacter().getLvl());
		chPageHpLabel.setText(gameController.getCharacter().getCurrentHP() + "/" + gameController.getCharacter().getMaxHP() + " Hp");
		chPageXpLabel.setText(gameController.getCharacter().getXp() + " Xp");
		
		chPageStrText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getStr()));
		chPageDexText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getDex()));
		chPageConText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getCon()));
		chPageIntText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getIntl()));
		chPageChaText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getCha()));
		chPageLckText.setText(String.valueOf(gameController.getCharacter().getCharacteristics().getLck()));
		
		chPageSkill1Label.setText(gameController.getCharacter().getSkills().getPrimarySkills()[0].toString());
		chPageSkill2Label.setText(gameController.getCharacter().getSkills().getPrimarySkills()[1].toString());
		chPageSkill3Label.setText(gameController.getCharacter().getSkills().getPrimarySkills()[2].toString());
		chPageSkill4Label.setText(gameController.getCharacter().getSkills().getSecondarySkills()[0].toString());
		chPageSkill5Label.setText(gameController.getCharacter().getSkills().getSecondarySkills()[1].toString());
		chPageSkill6Label.setText(gameController.getCharacter().getSkills().getSecondarySkills()[2].toString());
		chPageSkill7Label.setText(gameController.getCharacter().getSkills().getMiscellaneousSkills()[0].toString());
		chPageSkill8Label.setText(gameController.getCharacter().getSkills().getMiscellaneousSkills()[1].toString());
		chPageSkill9Label.setText(gameController.getCharacter().getSkills().getMiscellaneousSkills()[2].toString());
		chPageSkill10Label.setText(gameController.getCharacter().getSkills().getMiscellaneousSkills()[3].toString());
		chPageSkill11Label.setText(gameController.getCharacter().getSkills().getMiscellaneousSkills()[4].toString());
		
		chPageSkill1Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(1)));
		chPageSkill2Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(2)));
		chPageSkill3Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(3)));
		chPageSkill4Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(4)));
		chPageSkill5Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(5)));
		chPageSkill6Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(6)));
		chPageSkill7Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(7)));
		chPageSkill8Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(8)));
		chPageSkill9Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(9)));
		chPageSkill10Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(10)));
		chPageSkill11Text.setText(String.valueOf(gameController.getCharacter().getSkills().getSkill(11)));
		
		
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
