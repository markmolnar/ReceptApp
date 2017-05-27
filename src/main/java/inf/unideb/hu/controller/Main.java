package inf.unideb.hu.controller;


import java.io.IOException;
import java.util.logging.Logger;
import inf.unideb.hu.util.JpaUtil;
import inf.unideb.hu.view.MenuViewController;
import inf.unideb.hu.view.RecipeViewController;
import inf.unideb.hu.view.SearchViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Entry class of the application.
 * @author mark
 */
public class Main extends Application {
	/**
	 * The primaryStage of the application. 
	 */
	private Stage primaryStage;
	
	/**
	 * The root view of of the application.
	 */
	private BorderPane rootView;
	
	/**
	 * The menu view of the application.
	 */
	private AnchorPane menuview;

	/**
	 * Responsible for the EntityManagerFactory.
	 */
	private JpaUtil jpautil = new JpaUtil();

	/**
	 * Logger of Main class.
	 */
	private static final Logger LOGGER=Logger.getLogger(Main.class.getName());

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public final void start(final Stage primaryStage) {
		LOGGER.info("Starting the application");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ReceptApp");
		initRootView(primaryStage);
	}


	 /**
	 * Sets the root view of the application.
	 * @param primaryStage the primaryStage of the application
	 */
	public void initRootView(Stage primaryStage)
	{
		 try {
			 	LOGGER.info("Loading the root view");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/inf/unideb/hu/view/RootView.fxml"));
	            rootView = (BorderPane) loader.load();
	            Scene scene = new Scene(rootView);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            showMenuView();
	        } catch (IOException e) {
	            //e.printStackTrace();
	            LOGGER.severe("Error initializing the root view.");
	            LOGGER.severe(e.getMessage());
	        }
	 
     }

	/**
	 * Displays the menu view of the application.
	 */
	public void showMenuView() {
		try {
				LOGGER.info("Loading the menu view");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/inf/unideb/hu/view/MenuView.fxml"));
	            menuview = (AnchorPane) loader.load();
	            rootView.setCenter(menuview);
	            MenuViewController controller = loader.getController();
	            controller.setMainApp(this);
	        } catch (IOException e) {
	            //e.printStackTrace();
	        	LOGGER.severe("Error initializing the menu view.");
	            LOGGER.severe(e.getMessage());
	        }
	}

	 /**
	 * Displays the search view where the foods can be searched.
	 */
	public void showSearchView() {
		try {
				LOGGER.info("Loading the search view");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/inf/unideb/hu/view/SearchView.fxml"));
	            AnchorPane searchview = (AnchorPane) loader.load();
	            //searchview.getStyleClass().add("search-view");
	            rootView.setCenter(searchview);
	            SearchViewController controller = loader.getController();
	            controller.setMainApp(this);
	        } catch (IOException e) {
	            //e.printStackTrace();
	        	LOGGER.severe("Error initializing the search view.");
	            LOGGER.severe(e.getMessage());
	        }
	}

	 /**
	 * Displays the recipe view where recipes can be added.
	 */
	public void showRecipeView() {
		try {
				LOGGER.info("Loading the recipe view");
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("/inf/unideb/hu/view/RecipeView.fxml"));
	            AnchorPane recipeview = (AnchorPane) loader.load();
	            
	            rootView.setCenter(recipeview);
	            RecipeViewController controller = loader.getController();
	            controller.setMainApp(this);
	        } catch (IOException e) {
	            //e.printStackTrace();
	        	LOGGER.severe("Error initializing the recipe view.");
	            LOGGER.severe(e.getMessage());
	        }
	}



	@Override
	 public void stop() {
		 LOGGER.info("Stoppping the application...");
		 JpaUtil.close();
	 }


	/**
	 * Main method of the application.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
