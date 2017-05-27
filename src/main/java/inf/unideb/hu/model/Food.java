package inf.unideb.hu.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Class wrapping information about a food.
 * @author mark
 */
@Entity
public class Food {
	/**
	 * The id of the food object.
	 */
	@Id
	@GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq", sequenceName="SEQ_FOOD", allocationSize = 1) 
	@Column(name = "FOODID")
	private int id;

	/**
	 * The name of the food.
	 */
	@Column(name = "FOODNAME", nullable = false, columnDefinition = "nvarchar2")
	private String name;

	/**
	 * the byte array containing a picture about the food.
	 */
	@Lob
	@Column(name = "PICTURE", columnDefinition = "BLOB")
	private byte[] array;

	/**
	 * The description of the food's recipe.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "CLOB")
	 private String description;

	/**
	 * The preparation time of the food.
	 */
	@Column(name = "CREATIONPREPARATIONTIME", columnDefinition = "nvarchar2")
	private String time;

	/**
	 * The number of persons the food is recommended for.
	 */
	@Column(name = "PERSON")
	private int person;

	/**
	 * The energy content of the food.
	 */
	@Column(name = "ENERGY", columnDefinition = "float")
	private double energy;

	/**
	 * The fat content of the food.
	 */
	@Column(name = "TOTALFAT", columnDefinition = "float")
	private double totalfat;

	/**
	 * The protein content of the food.
	 */
	@Column(name = "PROTEIN", columnDefinition = "float")
	private double protein;

	/**
	 * carbohydrate content of the food.
	 */
	@Column(name = "CARBOHYDRATE", columnDefinition = "float")
	private double carbohydrate;

	/**
	 * The ingredients of the food stored as food items.
	 */
	@OneToMany(mappedBy = "food", orphanRemoval = true)
	private Collection<FoodItem> ingredients;


	/**
	 * Creates a Food object.
	 */
	public Food() {
		super();
	}

	/**
	 * Creates a food object.
	 * @param name the name of the food
	 * @param array the byte array containing the picture
	 * @param description the recipe description of the food
	 * @param time the preparation time of the food
	 * @param person the number of people the food is recommended for
	 */
	public Food(String name, byte[] array, String description, String time, int person) {
		super();
		this.name = name;
		this.array = array;
		//resize();
		this.description = description;
		this.time = time;
		this.person = person;
	}

	/**
	 * Creates a food object.
	 * @param name the name of the food
	 * @param array the byte array containing the picture
	 * @param description the recipe description of the food
	 * @param time the preparation time of the food
	 * @param person the number of people the food is recommended for
	 * @param energy the energy content of the food
	 * @param totalfat the fat content of the food
	 * @param protein the protein content of the food
	 * @param carbohydrate the carbohydrate content of the food
	 */
	public Food(String name, byte[] array, String description, String time, int person, double energy, double totalfat, double protein, double carbohydrate) {
		super();
		this.name = name;
		this.array = array;
		//resize();
		this.description = description;
		this.time = time;
		this.person = person;
		this.energy = energy;
		this.totalfat = totalfat;
		this.protein = protein;
		this.carbohydrate = carbohydrate;
	}

	/**
	 * Returns the id of the food object.
	 * @return the id of the object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the description of the food.
	 * @return the description of the food
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the food.
	 * @param description the description of the food
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the preparation time of the food.
	 * @return the preparation time of the food
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the preparation time of the food.
	 * @param time the preparation time of the food
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Returns the number of persons the food is recommended for.
	 * @return the number of persons the food is recommended for
	 */
	public int getPerson() {
		return person;
	}

	/**
	 * Sets the number of persons the food is recommended for.
	 * @param person the number of persons the food is recommended for
	 */
	public void setPerson(int person) {
		this.person = person;
	}

	/**
	 * Returns the energy content of the food.
	 * @return the energy content of the food
	 */
	public double getEnergy() {
		return energy;
	}

	/**
	 * Sets the energy content of the food.
	 * @param energy the energy content of the food
	 */
	public void setEnergy(double energy) {
		this.energy = energy;
	}

	/**
	 * Gets the fat content of the food.
	 * @return the fat content of the food
	 */
	public double getTotalfat() {
		return totalfat;
	}

	/**
	 * Sets the fat content of the food.
	 * @param totalfat the totalfat content of the food
	 */
	public void setTotalfat(double totalfat) {
		this.totalfat = totalfat;
	}

	/**
	 * Gets the protein content of the food.
	 * @return the protein content of the food
	 */
	public double getProtein() {
		return protein;
	}

	/**
	 * Sets the protein content of the food.
	 * @param protein the protein content of the food
	 */
	public void setProtein(double protein) {
		this.protein = protein;
	}
	
	/**
	 * Gets the carbohydrate content of the food.
	 * @return the carbohydrate content of the food
	 */
	public double getCarbohydrate() {
		return carbohydrate;
	}

	/**
	 * Sets the carbohydrate content of the food.
	 * @param carbohydrate the carbohydrate content of the food
	 */
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	/**
	 * Gets the name of the food.
	 * @return the name of the food
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the food.
	 * @param name the name of the food
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets a byte array,which contains the picture of the food.
	 * @return the byte array, which contains the picture of the food
	 */
	public byte[] getArray() {
		return array;
	}

	/**
	 * Sets the byte array, which contains the picture of the food.
	 * @param array the byte array, which contains the picure of the food
	 */
	public void setArray(byte[] array) {
		this.array = array;
	}
}
