package inf.unideb.hu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Class connecting the {@link inf.unideb.hu.model.Food} and the {@link inf.unideb.hu.model.Ingredient} classes and storing the ingredient quantity.
 * @author mark
 */
@Entity
public class FoodItem {
	/**
	 * The id of the food item.
	 */
	@Id
	@GeneratedValue(generator = "Seq")
    @SequenceGenerator(name = "Seq", sequenceName = "SEQ_FOODITEM", allocationSize = 1)
	private int FoodItemId;

	/**
	 * The id of the food.
	 */
	@Column(nullable = false)
	private int FoodId;

	/**
	 * The id of the ingredient.
	 */
	@Column(nullable = false)
	private int IngredientId;

	/**
	 * The quantity of the ingredient used for the food.
	 */
	private int quantity;
	
	/**
	 * The food item.
	 */
	@ManyToOne
	@JoinColumn(name = "FoodId", referencedColumnName = "FOODID", insertable = false, updatable = false)
	private Food food;



	/**
	 * Creates a food item object.
	 */
	public FoodItem() {
		super();
	}


	/**
	 * Creates a food item object.
	 * @param foodId the id of the food
	 * @param ingredientId the id of the ingredient
	 * @param quantity the quantity of the ingredient used for the food
	 */
	public FoodItem(int foodId, int ingredientId, int quantity) {
		super();
		FoodId = foodId;
		IngredientId = ingredientId;
		this.quantity = quantity;
	}


	/**
	 * Returns the FoodId of the fooditem.
	 * @return the FoodId
	 */
	public int getFoodId() {
		return FoodId;
	}

	/**
	 * Sets the FoodId of the fooditem.
	 * @param foodId the FoodId of the fooditem
	 */
	public void setFoodId(int foodId) {
		FoodId = foodId;
	}

	/**
	 * Gets the IngredientId of the fooditem.
	 * @return the IngredientId of the fooditem
	 */
	public int getIngredientId() {
		return IngredientId;
	}

	/**
	 * Sets the IngredientId of the fooditem.
	 * @param ingredientId the IgredientId of the fooditem
	 */
	public void setIngredientId(int ingredientId) {
		IngredientId = ingredientId;
	}

	/**
	 * Gets the quantity value of the fooditem.
	 * @return the quantity of the fooditem
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantiy value of the ingredient represented in the fooditem.
	 * @param quantity the quantity value of the ingredient represented in the fooditem
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}
