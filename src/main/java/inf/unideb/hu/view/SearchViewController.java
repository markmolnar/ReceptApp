/**
 * View classes and files of the ReceptApp.
 * @author mark
 */
package inf.unideb.hu.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import inf.unideb.hu.controller.Main;
import inf.unideb.hu.model.Food;
import inf.unideb.hu.model.FoodService;
import inf.unideb.hu.util.FileConverter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class of the SearchView.
 * @author mark
 */
public class SearchViewController {

	/**
	 * Variable referencing to the main class.
	 */
	private Main mainApp;

	/**
	 * File converter for converting byte array to ImageView.
	 */
	private FileConverter fileconverter;

	/**
	 * Button that returns to main menu.
	 */
	@FXML
	private Button backButton;

	/**
	 * Button that deletes the selected food.
	 */
	@FXML
	private Button deleteButton;

	/**
	 * TableView displaying the foods.
	 */
	@FXML
	private TableView<Food> foodTable;

	/**
	 * TableColumn displaying the food's image.
	 */
	@FXML
	private TableColumn<Food,ImageView> imagecolumn;

	/**
	 * TableColumn displaying the food's name.
	 */
	@FXML
	private TableColumn<Food,String>namecolumn;

	/**
	 * Button searching for food by the given conditions.
	 */
	@FXML
	private Button searchbutton;

	/**
	 * TextField for giving the food's name.
	 */
	@FXML
	private TextField nameField;

	/**
	 * TextField for giving the minimum carbohydrate value.
	 */
	@FXML
	private TextField minCarboField;

	/**
	 * TextField for giving the maximum carbohydrate value.
	 */
	@FXML
	private TextField maxCarboField;

	FoodService foodservice;
	
	/**
	 * List of foods.
	 */
	private List<Food> foodList = new ArrayList<Food>();
	
	/**
	 * ObservableList of foods so they can be displayed.
	 */
	private ObservableList<Food> observalbeFoodList=FXCollections.observableArrayList();
	
	/**
	 * Logger of the SearchViewController class.
	 */
	private static final Logger LOGGER=Logger.getLogger(SearchViewController.class.getName());

	/**
	 * Sets the mainApp variable.
	 * @param mainApp reference to mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		}

	@FXML
	private void initialize() {
		fileconverter = new FileConverter();
		foodservice = new FoodService();
		foodList = foodservice.getAllFoods();
	    observalbeFoodList = FXCollections.observableArrayList(foodList);
	    foodTable.setItems(observalbeFoodList);
	    imagecolumn.setCellValueFactory(cellData-> new SimpleObjectProperty<ImageView>(fileconverter.ArrayToFile(cellData.getValue().getArray())));
	    namecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
	    /*foodTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
	    showRecipeDialog(newValue));*/
	    foodTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            showRecipeDialog(newValue);
	            }
	        });
	}
	/**
	 * Searches by the given conditions.
	 */
	@FXML
	private void handleSearch() {
		if(nameField.getText() == null || nameField.getText().length() == 0) {
			nameField.setText("");
		}
		if(minCarboField.getText() == null || minCarboField.getText().length() == 0 || StringUtils.isNumeric(minCarboField.getText()) == false) {
			minCarboField.setText("0");
		}
		if(maxCarboField.getText() == null || maxCarboField.getText().length() == 0 || StringUtils.isNumeric(maxCarboField.getText()) == false) {
			maxCarboField.setText("10000");
		}
		foodList = foodservice.searchFood(nameField.getText(), Integer.parseInt(minCarboField.getText()), Integer.parseInt(maxCarboField.getText()));
		observalbeFoodList = FXCollections.observableArrayList(foodList);
		//foodTable.getItems().setAll(observalbeFoodList);
		foodTable.setItems(observalbeFoodList);
	}


	/**
	 * Returns to the main menu.
	 */
	@FXML
	private void handleBack() {
		mainApp.showMenuView();
	}

	/**
	 * Deletes the selected food.
	 */
	@FXML
	private void handleDelete() {
		if(foodTable.getSelectionModel().getSelectedItem() != null){
			foodservice.removeFood(foodTable.getSelectionModel().getSelectedItem());
			LOGGER.info("Food deleted");
			mainApp.showSearchView();
		}
	}


	/**
	 * Displays the recipeinfoView. Displays information about the chosen food..
	 * @param food the chosen food
	 */
	public void showRecipeDialog(Food food) {
		try {	
			LOGGER.info("Loading the recipe info view");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecipeinfoView.fxml"));
			AnchorPane page = loader.load();
			page.setStyle("-fx-background-color: #F0E68C");

			Stage dialogStage = new Stage();
			dialogStage.setTitle(food.getName());
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(page);

			dialogStage.setScene(scene);
			RecipeinfoViewController controller = loader.getController();
			controller.setFood(food);
			dialogStage.showAndWait();
			LOGGER.info("Closing recipedialog");
			} catch (IOException e) {
				//e.printStackTrace();
				LOGGER.severe("Error initializing the RecipeInfoView");
				LOGGER.severe(e.getMessage());
	        }
	    }
}
