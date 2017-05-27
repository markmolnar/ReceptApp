package inf.unideb.hu.model;

import java.util.List;

/**
 * DAO interface for foods.
 * @author mark
 *
 */
public interface FoodDao {

	/**
	 * Returns all foods.
	 * @return list of foods
	 */
	public List<Food> getAllFoods();

	/**
	 * Returns a food by the given name.
	 * @param name the name of the food
	 * @return the food
	 */
	public Food getFoodByName(String name);

	/**
	 * Persists the food to the database.
	 * @param food the food which is saved
	 * @return the id of the saved food
	 */
	public int saveFood(Food food);

	/**
	 * Persists the food to the database.
	 * @param fooditem the fooditem which is saved
	 */
	public void saveFoodItem(FoodItem fooditem);

	/**
	 * Finds all of the foods by the given search conditions.
	 * @param name the name of the food
	 * @param carbomin minimum carbohydrate content
	 * @param carbomax maximum carbohydrate content
	 * @return the list of foods which fulfill the conditions
	 */
	List<Food> searchFood(String name, Integer carbomin, Integer carbomax);

	/**
	 * Removes the the food from the database.
	 * @param food the food which will be removed
	 */
	void removeFood(Food food);
}
