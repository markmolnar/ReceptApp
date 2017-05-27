package inf.unideb.hu.view;

import inf.unideb.hu.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller class of the menu view.
 * @author mark
 */
public class MenuViewController {

	/**
	 * Variable referencing to the main class.
	 */
	private Main mainApp;

	/**
	 * Button navigates to search view.
	 */
	@FXML
	private Button searchButton;

	/**
	 * Button navigates to recipe view.
	 */
	@FXML
	private Button recipeButton;

	/**
	 * Sets the mainApp variable.
	 * @param mainApp reference to mainApp
	 */
	public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

	/**
	 * Navigates to search view.
	 */
	@FXML
	private void handleSearch() {
		mainApp.showSearchView();
	}

	/**
	 * Navigates to recipe view.
	 */
	@FXML
	private void handleNewRecipe() {
		mainApp.showRecipeView();
	}
}
