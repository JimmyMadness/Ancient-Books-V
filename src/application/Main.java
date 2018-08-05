package application;
	
import java.io.FileNotFoundException;

import events.ChangeViewEvent;
import events.ChangeViewListener;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;



public class Main extends Application implements ChangeViewListener{

	private ViewController viewController;
	private Stage primaryStage;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	
	private AnchorPane newGamePane;
	private Scene newGameScene;
	
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
	
	@Override
	public void start(Stage primaryStage) {
		viewController = new ViewController();
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
			
			loader = new FXMLLoader(Main.class.getResource("resources/CharacterSkills.fxml"));
			loader.setController(viewController);
			skillsPane = (AnchorPane)loader.load();
			skillsScene = new Scene(skillsPane);
			
			loader = new FXMLLoader(Main.class.getResource("resources/Village.fxml"));
			loader.setController(viewController);
			villagePane = (AnchorPane)loader.load();
			villageScene = new Scene(villagePane);
			
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
		chCreationPane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
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
	}
	
	private void showVillage() {
		primaryStage.setScene(villageScene);
		primaryStage.show();
		villagePane.requestFocus();
		villagePane.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);
	}	
	

	@Override
	public void onChangeView(ChangeViewEvent event) {
		if(event.getView().equals(Views.NEWGAME)) {
			showNewGame();
		}
		if(event.getView().equals(Views.MAINMENU)) {
			showMainMenu();
		}
		if(event.getView().equals(Views.BEGINNING)) {
			showBeginning();
		}
		if(event.getView().equals(Views.CHCREATION)) {
			showChCreation();
		}
		if(event.getView().equals(Views.CHANGECHPICTURE)) {
			showChangeChPicture();
		}
		if(event.getView().equals(Views.CLOSECHANGECHPICTURE)) {
			closeChangeChPicture();
		}
		if(event.getView().equals(Views.SKILLS)) {
			showSkills();
		}
		if(event.getView().equals(Views.VILLAGE)) {
			showVillage();
		}
	}









}
