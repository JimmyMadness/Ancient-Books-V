package application;
	
import java.io.FileNotFoundException;

import controller.GameController;
import events.ChangeViewEvent;
import events.ChangeViewListener;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Location;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;



public class Main extends Application implements ChangeViewListener{

	private ViewController viewController;
	private GameController gameController;
	private Stage primaryStage;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	
	private AnchorPane newGamePane;
	private Scene newGameScene;
	
	private AnchorPane loadGamePane;
	private Scene loadGameScene;
	
	private AnchorPane beginningPane;
	private Scene beginningScene;
	
	private AnchorPane chCreationPane;
	private Scene chCreationScene;
	
	private Stage changeChPictureStage;
	private AnchorPane changeChPicturePane;
	private Scene changeChPictureScene;
	
	private AnchorPane skillsPane;
	private Scene skillsScene;
	
	private AnchorPane villagePane;
	private Scene villageScene;
	
	private AnchorPane dungeonPane;
	private Scene dungeonScene;
	
	private AnchorPane chPagePane;
	private Scene chPageScene;
	
	private AnchorPane inventoryPane;
	private Scene inventoryScene;
	
	@Override
	public void start(Stage primaryStage) {
		gameController = new GameController();
		viewController = new ViewController(gameController);
		viewController.setChangeViewListener(this);
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("resources/MainMenu.fxml"));
			loader.setController(viewController);
			mainPane = (AnchorPane)loader.load();
			mainScene = new Scene(mainPane);
//			mainScene.getStylesheets().add(getClass().getResource("resources/MainStyle.css").toExternalForm());
			
			loader = new FXMLLoader(Main.class.getResource("resources/NewGame.fxml"));
			loader.setController(viewController);
			newGamePane = (AnchorPane)loader.load();
			newGameScene = new Scene(newGamePane);
//			newGameScene.getStylesheets().add(getClass().getResource("resources/MainStyle.css").toExternalForm());
			
			loader = new FXMLLoader(Main.class.getResource("resources/LoadGame.fxml"));
			loader.setController(viewController);
			loadGamePane = (AnchorPane)loader.load();
			loadGameScene = new Scene(loadGamePane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/Beginning.fxml"));
			loader.setController(viewController);
			beginningPane = (AnchorPane)loader.load();
			beginningScene = new Scene(beginningPane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/CharacterCreation.fxml"));
			loader.setController(viewController);
			chCreationPane = (AnchorPane)loader.load();
			chCreationScene = new Scene(chCreationPane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/ChangeChPicture.fxml"));
			loader.setController(viewController);
			changeChPicturePane = (AnchorPane)loader.load();
			changeChPictureScene = new Scene(changeChPicturePane);
			changeChPictureStage = new Stage();
			changeChPictureStage.initStyle(StageStyle.UNDECORATED);
			changeChPictureStage.setResizable(false);
			changeChPictureStage.setAlwaysOnTop(true);
			changeChPictureStage.initModality(Modality.APPLICATION_MODAL);
			changeChPictureStage.setScene(changeChPictureScene);
			
			loader = new FXMLLoader(Main.class.getResource("resources/CharacterCreation2.fxml"));
			loader.setController(viewController);
			skillsPane = (AnchorPane)loader.load();
			skillsScene = new Scene(skillsPane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/Village.fxml"));
			loader.setController(viewController);
			villagePane = (AnchorPane)loader.load();
			villageScene = new Scene(villagePane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/Dungeon.fxml"));
			loader.setController(viewController);
			dungeonPane = (AnchorPane)loader.load();
			dungeonScene = new Scene(dungeonPane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/CharacterPage.fxml"));
			loader.setController(viewController);
			chPagePane = (AnchorPane)loader.load();
			chPageScene = new Scene(chPagePane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/Inventory.fxml"));
			loader.setController(viewController);
			inventoryPane = (AnchorPane)loader.load();
			inventoryScene = new Scene(inventoryPane);
			
			this.primaryStage = primaryStage;
			this.primaryStage.initStyle(StageStyle.UNIFIED);
			this.primaryStage.setResizable(false);
			this.primaryStage.setTitle("SKYROM");
			showMainMenu();

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		launch(args);
	}

	private void showMainMenu() {
		primaryStage.setScene(mainScene);
		primaryStage.show();
		mainPane.requestFocus();
		mainPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

	}

	private void showNewGame() {
		primaryStage.setScene(newGameScene);
		primaryStage.show();
		newGamePane.requestFocus();
		newGamePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		try {
			viewController.initNewGame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void showLoadGame() {
		primaryStage.setScene(loadGameScene);
		primaryStage.show();
		loadGamePane.requestFocus();
		loadGamePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		try {
			viewController.initLoadGame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	private void showBeginning() {
		primaryStage.setScene(beginningScene);
		primaryStage.show();
		beginningPane.requestFocus();
		beginningPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		viewController.startBeginningSequence();
	}
	
	private void showChCreation() {
		primaryStage.setScene(chCreationScene);
		primaryStage.show();
		chCreationPane.requestFocus();
		chCreationPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		viewController.initChCreation();
	}
	
	private void showChangeChPicture() {

		changeChPictureStage.show();
		changeChPicturePane.requestFocus();
		changeChPicturePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		try {
			viewController.initChangeChPicture();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private void closeChangeChPicture() {
		changeChPictureStage.hide();
		
	}
	
	private void showSkills() {
		primaryStage.setScene(skillsScene);
		primaryStage.show();
		skillsPane.requestFocus();
		skillsPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		viewController.initChCreation2();
	}
	
	private void showVillage() {
		primaryStage.setScene(villageScene);
		primaryStage.show();
		villagePane.requestFocus();
		villagePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		viewController.setGameLocation(Location.VILLAGE);
		viewController.initVillage();
	}	
	
	private void showDungeon() {
		primaryStage.setScene(dungeonScene);
		primaryStage.show();
		dungeonPane.requestFocus();
		dungeonPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);	
		viewController.setGameLocation(Location.DUNGEON);
	}
	
	private void showCharacterPage() {
		primaryStage.setScene(chPageScene);
		primaryStage.show();
		chPagePane.requestFocus();
		chPagePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		
	}
	
	private void showInventory() {
		primaryStage.setScene(inventoryScene);
		primaryStage.show();
		inventoryPane.requestFocus();
		inventoryPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
		
	}

	@Override
	public void onChangeView(ChangeViewEvent event) {

		switch (event.getView()) {
			case NEWGAME: showNewGame();
			break;
			case LOADGAME: showLoadGame();
			break;
			case MAINMENU: showMainMenu();
			break;
			case BEGINNING: showBeginning();
			break;
			case CHCREATION: showChCreation();
			break;
			case CHANGECHPICTURE: showChangeChPicture();
			break;
			case CLOSECHANGECHPICTURE: closeChangeChPicture();
			break;
			case SKILLS: showSkills();
			break;
			case VILLAGE: showVillage();
			break;
			case DUNGEON: showDungeon();
			break;
			case CHARACTERPAGE: showCharacterPage();
			break;
			case INVENTORY: showInventory();
			break;
		
		}
		
	}





}
