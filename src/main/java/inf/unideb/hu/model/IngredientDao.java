package inf.unideb.hu.model;

import java.util.List;

/**
 * DAO interface for ingredients.
 * @author mark
 *
 */
public interface IngredientDao {

	/**
	 * Gets all ingredient groups.
	 * @return the list of all ingredient groups
	 */
	public List<String> getIngredientGroups();

	/**
	 * Gets all ingredient names that belong to the given ingredient group name.
	 * @param name the name of the ingredient group name
	 * @return list of ingredients belong to the group
	 */
	public List<String> getIngredientNames(String name);
	
	/**
	 * Gets ingredient by name.
	 * @param name the name of the ingredient
	 * @return the ingredient
	 */
	public Ingredient getIngredientByName(String name);
	
	/**
	 * Gets ingredient by id.
	 * @param id the id of the ingredient
	 * @return the ingredient
	 */
	public Ingredient getIngredientById(Integer id);

	/**
	 * Gets ingredient quantities by the given food id.
	 * @param id the id of the food
	 * @return list of quantities belong to the food
	 */
	public List<Integer> getQuantityByFoodId(Integer id);

	/**
	 * Gets all ingredients by the given food id.
	 * @param id the id of the food
	 * @return list of ingredients belong to the food
	 */
	public List<Ingredient> getAllIngredients(Integer id);
}
