package inf.unideb.hu.view;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

import inf.unideb.hu.controller.Main;
import inf.unideb.hu.model.Content;
import inf.unideb.hu.model.Food;
import inf.unideb.hu.model.FoodItem;
import inf.unideb.hu.model.FoodService;
import inf.unideb.hu.model.Ingredient;
import inf.unideb.hu.model.IngredientService;
import inf.unideb.hu.util.FileConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Controller class of the RecipeView.
 * @author mark
 */
public class RecipeViewController {

	/**
	 * Variable referencing to the main class.
	 */
	private Main mainApp;

	/**
	 * File storing the picture of the food.
	 */
	File pictureFile;

	/**
	 * Button that returns to main menu.
	 */
	@FXML
	private Button backButton;

	/**
	 * Button for saving the recipe.
	 */
	@FXML
	private Button saveButton;

	/**
	 * TextField for giving the food's name.
	 */
	@FXML
	private TextField nameField;

	/**
	 * TextField for giving the food's preparation time.
	 */
	@FXML
	private TextField prepareTimeField;

	/**
	 * ChocieBox for time units(óra/perc).
	 */
	@FXML
	private ChoiceBox<String> Timeunits;

	/**
	 * Button for giving the picture file.
	 */
	@FXML
	private Button pictureButton;

	/**
	 * TextField for displaying the picture file's path.
	 */
	@FXML
	private TextField filepathField;

	/**
	 *TextArea for giving the description of the recipe.  
	 */
	@FXML
	private TextArea descriptionArea;

	/**
	 * ChoiceBox for giving the number of people the food is recommended for.
	 */
	@FXML
	private ChoiceBox<Integer> PersonNumber;

	/**
	 * ChoiceBox for giving the IngredientGroup.
	 */
	@FXML
	private ChoiceBox<String> IngredientGroupname;

	/**
	 * ChoiceBox for giving the Ingredient's name.
	 */
	@FXML
	private ChoiceBox<String> IngredientName;

	/**
	 * ChoiceBox for giving the ingredient quantity unit.
	 */
	@FXML
	private ChoiceBox<String> IngredientQuantityUnits;

	/**
	 * TextField for giving the ingredient's quantity.
	 */
	@FXML
	private TextField ingredientquantityField;

	/**
	 * Button for adding new ingredient. 
	 */
	@FXML
	private Button addButton;

	/**
	 * TableView for displaying the added ingredients.
	 */
	@FXML
	private TableView<Ingredient> ingredientTable;

	/**
	 * TableColumn for displaying the ingredient's name.
	 */
	@FXML
	private TableColumn<Ingredient, String> namecolumn;

	/**
	 * Variable storing if an ingredient already added.
	 */
	private boolean isThereIngredient;

	ClassLoader classLoader = getClass().getClassLoader();

	/**
	 * File converter to convert file to byte array. 
	 */
	FileConverter fileConverter;

	/**
	 * Map for collecting ingredients and quantities. 
	 */
	Map<Ingredient, Integer> ingredientAndQuantity = new LinkedHashMap<Ingredient, Integer>();

	/**
	 * ObservableList for collecting ingredients so they can be displayed.
	 */
	private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();

	/**
	 * List for collecting the ingredient names.
	 */
	private List<String> ingredientGroupNames;

	IngredientService ingredientservice;
	FoodService foodservice;
	
	/**
	 * Logger of the RecipeViewController class.
	 */
	private static final Logger LOGGER=Logger.getLogger(RecipeViewController.class.getName());
	
	/**
	 * The maximum size of the picture in bytes.
	 */
	private static final long MAX_SIZE=204800;

	 @FXML
	 private void initialize() {
		 fileConverter = new FileConverter();
		 isThereIngredient = false;
		 Timeunits.setItems(FXCollections.observableArrayList("perc", "óra"));
		 PersonNumber.setItems(FXCollections.observableArrayList(1, 2, 3, 4));
		 pictureButton.setId("add-picture-button");
		 filepathField.setId("file-path-field");

		 IngredientGroupname.getSelectionModel()
		    .selectedItemProperty()
		    .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue)-> handleGroupChange());

		 IngredientQuantityUnits.getItems().add("gramm");
		 ingredientTable.setItems(ingredientList);
		 namecolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		 ingredientservice = new IngredientService();
		 foodservice = new FoodService();
		 ingredientGroupNames = ingredientservice.getIngredientGroups();
		 IngredientGroupname.setItems(FXCollections.observableArrayList(ingredientGroupNames));
	 }

	/**
	 * Sets the mainApp variable.
	 * @param mainApp reference to mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Displays a FileChooser window so the Users can select the picture file. 
	 */
	@FXML
	private void handlePictureselect() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
		pictureFile = fileChooser.showOpenDialog(new Stage());

		if(pictureFile != null) {
			filepathField.setStyle("-fx-text-fill:black;");
			filepathField.setText(pictureFile.getPath());
		}
	}

	/**
	 * Returns to the main menu.
	 */
	@FXML
	private void handleBack() {
		mainApp.showMenuView();
	}

	/**
	 * Saves the given food if it is in correct form.
	 */
	@FXML
	private void handleSave() {
		if(isInputValid()) {
			Content countcontent = new Content();
			Food tempfood = new Food();
			tempfood.setName(nameField.getText());
			tempfood.setArray(fileConverter.FileToArray(pictureFile));
			tempfood.setDescription(descriptionArea.getText());
			tempfood.setTime(prepareTimeField.getText() + " " + Timeunits.getValue());
			tempfood.setPerson(PersonNumber.getValue().intValue());
			tempfood.setEnergy(countcontent.getTotalEnergy(ingredientAndQuantity));
			tempfood.setTotalfat(countcontent.getTotalFat(ingredientAndQuantity));
			tempfood.setProtein(countcontent.getTotalProtein(ingredientAndQuantity));
			tempfood.setCarbohydrate(countcontent.getTotalCarbohydrate(ingredientAndQuantity));
			int id = foodservice.saveFood(tempfood);

			for (Map.Entry<Ingredient, Integer> entry : ingredientAndQuantity.entrySet()) {
				FoodItem fooditem = new FoodItem(id, entry.getKey().getId(), entry.getValue());
				foodservice.saveFoodItem(fooditem);
			}
			LOGGER.info("Food saved.");
			mainApp.showMenuView();
		}
	}

	/**
	 * Sets the ingredients by the chosen ingredient group.
	 */
	@FXML
	private void handleGroupChange(){
		List<String> iname = ingredientservice.getIngredientNames(IngredientGroupname.getValue());
		IngredientName.setItems(FXCollections.observableArrayList(iname));
	}


	/**
	 * Stores the ingredient-quantity pair and adds new ingredient to the table.
	 */
	@FXML
	private void handleadd(){
		if(isIngredientInputvalid()){
			isThereIngredient = true;
			Ingredient ingredient = new Ingredient();
			ingredient = ingredientservice.getIngredientByName(IngredientName.getValue());
			ingredientList.add(ingredient);
			ingredientAndQuantity.put(ingredient, Integer.parseInt(ingredientquantityField.getText()));
			LOGGER.info("Ingredient added.");
		}
	}

	/**
	 * Checks if the given ingredient information is in appreciate form.
	 * @return true if the given ingredient information is in appreciate form. Otherwise it is false.
	 */
	private boolean isIngredientInputvalid() {
		String errormessage = "";
		if(IngredientGroupname.getValue() == null || IngredientGroupname.getValue().length() == 0) {
			errormessage += "Hozzávaló típus megadása kötelező\n";
		}
		if(IngredientName.getValue() == null || IngredientName.getValue().length() == 0) {
			errormessage += "Hozzávaló nevének megadása kötelező\n";
		}
		if(ingredientquantityField.getText() == null || ingredientquantityField.getText().length() == 0) {
			errormessage += "Mennyiség megadása kötelező\n";
		}
		if(StringUtils.isNumeric(ingredientquantityField.getText()) == false) {
			errormessage += "Nem legális karakter használata az elkészítési időnél\n";
		}
		if(IngredientQuantityUnits.getValue() == null || IngredientQuantityUnits.getValue().length() == 0) {
			errormessage += "Mértékegység megadása kötelező";
		}
		for(int i = 0; i < ingredientAndQuantity.size(); i++) {
			if(ingredientAndQuantity.containsKey(ingredientservice.getIngredientByName(IngredientName.getValue()))) {
						errormessage += "Ez a hozzávaló már szerepel";
					}
		}
		 if (errormessage.length() == 0) {
	            return true;
	        } else {
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Érvénytelen recept");
	            alert.setHeaderText("Hiba az adatok kitöltésekor");
	            
	            LOGGER.info(errormessage);
	            alert.setContentText(errormessage);
	            alert.showAndWait();
	            return false;
	        }
	}

	/**
	 * Checks if the given food information is in appreciate form.
	 * @return true if the given food information is in appreciate form. Otherwise it is false.
	 */
	private boolean isInputValid() {
		String errormessage = "";
		if(nameField.getText() == null||nameField.getText().length() == 0) {
			errormessage += "Név megadása kötelező\n";
		}
		if(pictureFile == null) {
			errormessage += "Kép megadása kötelező\n";
		}
		if(prepareTimeField.getText() == null||prepareTimeField.getText().length() == 0) {
			errormessage += "Elkészítési idő megadása kötelező\n";
		}
		if(StringUtils.isNumeric(prepareTimeField.getText()) == false) {
			errormessage += "Nem legális karakter használata az elkészítési időnél\n";
		}
		if(Timeunits.getValue() == null) {
			errormessage += "Időegység megadása kötelező\n";
		}
		if(PersonNumber.getValue() == null) {
			errormessage += "Adag megadása kötelező\n";
		}
		if(descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
			errormessage += "Recept megadása kötelező\n";
		}
		if(new File(filepathField.getText()).length() > MAX_SIZE) {
			errormessage += "Kép mérete nagyobb mint 200 kilobyte";
		}
		if(isThereIngredient == false) {
			errormessage += "Hozzávaló megadása kötelező";
		}
		 if (errormessage.length() == 0) {
	            return true;
	        } else {
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Kitöltési hiba");
	            alert.setHeaderText("Hiba az adatok kitöltésekor");
	            LOGGER.info(errormessage);
	            alert.setContentText(errormessage);
	            alert.showAndWait();
	            return false;
	        }
	}
}
