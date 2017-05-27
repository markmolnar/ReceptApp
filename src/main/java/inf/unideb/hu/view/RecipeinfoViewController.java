package inf.unideb.hu.view;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import inf.unideb.hu.model.Food;
import inf.unideb.hu.model.Ingredient;
import inf.unideb.hu.model.IngredientService;
import inf.unideb.hu.util.FileConverter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller class of the RecipeinfoView.
 * @author mark
 */
public class RecipeinfoViewController {

	/**
	 * ImageView displaying the image of the food.
	 */
	@FXML
	private ImageView image;

	/**
	 * TextArea displaying the description of the food.
	 */
	@FXML
	private TextArea descriptionArea;

	/**
	 * TextField displaying the preparation time.
	 */
	@FXML
	private TextField timeField;

	/**
	 * TextField displaying the number of people the food is recommended for.
	 */
	@FXML
	private TextField personField;

	/**
	 * TextField displaying the energy content of the food.
	 */
	@FXML
	private TextField energyField;

	/**
	 * TextField displaying the fat content of the food.
	 */
	@FXML
	private TextField totalfatField;

	/**
	 * TextField displaying the protein content of the food.
	 */
	@FXML
	private TextField proteinField;

	/**
	 * TextField displaying the carbohydrate content of the food.
	 */
	@FXML
	private TextField carbohydrateField;

	/**
	 * TextField displaying the ingredients and the quantities.
	 */
	@FXML
	private TextArea ingredientText;

	/**
	 * File converter to convert byte array to image in this view.
	 */
	FileConverter fileconverter;

	/**
	 * Ingredient service class.
	 */
	IngredientService ingredientservice;

	/**
	 * List for collecting the ingredients of the food.
	 */
	List<Ingredient> ingredients;

	/**
	 * List for collecting the quantities of the ingredients.
	 */
	List<Integer> quantities;

	/**
	 * Logger of the RecipeinfoViewController class.
	 */
	private static final Logger LOGGER=Logger.getLogger(RecipeinfoViewController.class.getName());
	
	@FXML
    private void initialize() {
		ingredientservice = new IngredientService();
		ingredients = new ArrayList<Ingredient>();
		quantities = new ArrayList<Integer>();
		fileconverter = new FileConverter();
    }

	/**
	 * Sets the food that's information is displayed on the RecipeInfoView.
	 * @param food the displayed food
	 */
	public void setFood(Food food) {

		LOGGER.info("Setting the food.");
		ingredients = ingredientservice.getAllIngredients(food.getId());
		quantities = ingredientservice.getQuantityByFoodId(food.getId());
		image.setImage((fileconverter.ArrayToFile(food.getArray()).getImage()));
		descriptionArea.setText(food.getDescription());
		timeField.setText(food.getTime());
		personField.setText(new Integer(food.getPerson()).toString());
		energyField.setText(Double.toString(food.getEnergy()));
		totalfatField.setText(Double.toString(food.getTotalfat()));
		proteinField.setText(Double.toString(food.getProtein()));
		carbohydrateField.setText(Double.toString(food.getCarbohydrate()));

		ingredientText.setText("Hozzávalók:\n");
		for(int i = 0; i < ingredients.size(); i++) {
			ingredientText.appendText(ingredients.get(i).getName() + "   ");
			ingredientText.appendText(quantities.get(i) + " gramm" + "\n");
		}
	}
}
