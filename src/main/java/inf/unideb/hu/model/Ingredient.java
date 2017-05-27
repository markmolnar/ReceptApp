package inf.unideb.hu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class wrapping information about an ingredient.
 * @author mark
 */
@Entity
public class Ingredient {

	/**
	 * The id of the ingredient.
	 */
	@Id
	@Column(name = "INGREDIENTID")
	private int id;

	/**
	 *The name of the ingredient. 
	 */
	@Column(name = "INGREDIENTNAME", nullable = false, columnDefinition = "nvarchar2")
	private String name;

	/**
	 * The id of the ingredient group.
	 */
	@ManyToOne
	@JoinColumn(name = "INGREDIENTGROUPID", nullable = false)
	private IngredientGroup ingredientgroupid;

	/**
	 * The energy content of the ingredient.
	 */
	@Column(columnDefinition = "FLOAT")
	private double energy;

	/**
	 * The fat content of the ingredient.
	 */
	@Column(columnDefinition = "FLOAT")
	private double totalfat;

	/**
	 * The protein content of the ingredient.
	 */
	@Column(columnDefinition = "FLOAT")
	private double protein;

	/**
	 * The carbohydrate content of the ingredient.
	 */
	@Column(columnDefinition = "FLOAT")
	private double carbohydrate;

	/**
	 * Creates an ingredient object.
	 */
	public Ingredient() {
		super();
	}

	/**
	 * Creates an ingredient object.
	 * @param id the id of the ingredient
	 */
	public Ingredient(int id) {
		super();
		this.id = id;
	}

	/**
	 * Creates an ingredient object.
	 * @param id the id of the ingredient
	 * @param energy the energy content of the ingredient
	 * @param totalfat the fat content of the ingredient
	 * @param protein the protein content of the ingredient
	 * @param carbohydrate the carbohydrate content of the ingredient
	 */
	public Ingredient(int id, double energy, double totalfat, double protein, double carbohydrate) {
		super();
		this.id = id;
		this.energy = energy;
		this.totalfat = totalfat;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
	}

	/**
	 * Gets the id of the ingredient object.
	 * @return the id of the ingredient
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name of the ingredient.
	 * @return the name of the ingredient
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the ingredient.
	 * @param name the name of the ingredient
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the group id of the the ingredient.
	 * @return the group id of the ingredient
	 */
	public IngredientGroup getIngredientGroup() {
		return ingredientgroupid;
	}

	/**
	 * Gets the energy content of the ingredient.
	 * @return the energy content of the ingredient
	 */
	public double getEnergy() {
		return energy;
	}

	/**
	 * Sets the energy value of the ingredient.
	 * @param energy the energy value of the ingredient
	 */
	public void setEnergy(double energy) {
		this.energy = energy;
	}

	/**
	 * Gets the fat content of the ingredient.
	 * @return the fat content of the ingredient
	 */
	public double getTotalfat() {
		return totalfat;
	}

	/**
	 * Sets the fat value of the ingredient.
	 * @param totalfat the fat value of the ingredient
	 */
	public void setTotalfat(double totalfat) {
		this.totalfat = totalfat;
	}

	/**
	 * Gets the protein value of the ingredient.
	 * @return the protein value of the ingredient
	 */
	public double getProtein() {
		return protein;
	}

	/**
	 * Sets the protein value of the ingredient.
	 * @param protein the protein value of the ingredient
	 */
	public void setProtein(double protein) {
		this.protein = protein;
	}

	/**
	 * Gets the carbohydrate value of the ingredient.
	 * @return the carbohydrate value of the ingredient
	 */
	public double getCarbohydrate() {
		return carbohydrate;
	}

	/**
	 * Sets the carbohydrate value of the ingredient.
	 * @param carbohydrate the carbohydrate value of the ingredient
	 */
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
			}
		if (obj == null) {
			return false;
			}
		if (getClass() != obj.getClass()) {
			return false;
			}
		Ingredient other = (Ingredient) obj;
		if (id != other.id) {
			return false;
			}
		return true;
	}
}
