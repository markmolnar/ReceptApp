package inf.unideb.hu.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class wrapping information about food groups.
 * @author mark
 */
@Entity
public class IngredientGroup {
	/**
	 * The id of the ingredient group.
	 */
	@Id
	@Column(name = "INGREDIENTGROUPID")
	private int id;

	/**
	 * The name of the ingredient group.
	 */
	@Column(name = "INGREDIENTGROUPNAME", columnDefinition = "nvarchar2")
	private String name;

	/**
	 * The collection of ingredients that belong to the group.
	 */
	@OneToMany(mappedBy = "ingredientgroupid")
	private Collection<Ingredient> ingredients;

	/**
	 * Creates an ingredient group object.
	 */
	public IngredientGroup() {
		super();
	}

	/**
	 * Creates an ingredient group object.
	 * @param id the id of the ingredient group
	 * @param name the name of the ingredient group
	 */
	public IngredientGroup(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Creates an ingredient group object.
	 * @param id the id of the ingredient group
	 */
	public IngredientGroup(int id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id of the food group.
	 * @return the id of the food group
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name of the food group.
	 * @return the name of the food group
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the food group.
	 * @param name the name of the food group
	 */
	public void setName(String name) {
		this.name = name;
	}
}
