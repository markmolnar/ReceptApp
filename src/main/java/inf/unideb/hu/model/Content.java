package inf.unideb.hu.model;

import java.util.Map;


/**
 * Class for counting the nutritional value for the chosen food.
 * Energy,fat,protein and carbohydrate is counted.
 * @author mark
 */
public class Content {
	private final static int PER_HUNDRED=100;
	/**
	 * Counts the the total energy content by the ingredients and the quantity.
	 * @param map containing the ingredients and the quantities
	 * @return the total energy content of the food
	 */
	public double getTotalEnergy(Map<Ingredient, Integer> map) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getEnergy() / PER_HUNDRED) * entry.getValue();
		}
		return sum;
	}

	/**
	 * Counts energy content for one person by the ingredients and the quantity .
	 * @param map containing the ingredients and the quantities
	 * @param person the number of people
	 * @return the energy content of the food for a person
	 */
	public double getEnergyPerPerson(Map<Ingredient, Integer> map, int person) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getEnergy() / PER_HUNDRED) * entry.getValue();
		}
		return sum / (double) person;
	}

	/**
	 * Counts the total fat content by the ingredients and the quantity.
	 * @param map containing the ingredients and the quantities
	 * @return the total fat content of the food
	 */
	public double getTotalFat(Map<Ingredient, Integer> map) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getTotalfat() / PER_HUNDRED) * entry.getValue();
		}
		return sum;
	}

	/**
	 * Counts fat content for one person by the ingredients and the quantity .
	 * @param map containing the ingredients and the quantities
	 * @param person the number of people
	 * @return the fat content of the food for a person
	 */
	public double getFatPerPerson(Map<Ingredient, Integer> map, int person){
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getTotalfat() / PER_HUNDRED) * entry.getValue();
		}
		return sum / (double) person;
	}

	/**
	 * Counts the total protein content by the ingredients and the quantity.
	 * @param map containing the ingredients and the quantities
	 * @return the total protein content of the food
	 */
	public double getTotalProtein(Map<Ingredient, Integer> map) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getProtein() / PER_HUNDRED) * entry.getValue();
		}
		return sum;
	}

	/**
	 * Counts protein content for one person by the ingredients and the quantity .
	 * @param map containing the ingredients and the quantities
	 * @param person the number of people
	 * @return the protein content of the food for a person
	 */
	public double getProteinPerPerson(Map<Ingredient, Integer> map, int person) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getProtein() / PER_HUNDRED) * entry.getValue();
		}
		return sum / (double) person;
	}

	/**
	 * Counts the total protein content by the ingredients and the quantities.
	 * @param map containing the ingredients and the quantities
	 * @return the total carbohydrate content of the food
	 */
	public double getTotalCarbohydrate(Map<Ingredient, Integer> map) {
		double sum = 0;
		for (Map.Entry<Ingredient,Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getCarbohydrate() / PER_HUNDRED) * entry.getValue();
		}
		return sum;
	}

	/**
	 * Counts carbohydrate content for one person by the ingredients and the quantity .
	 * @param map containing the ingredients and the quantities
	 * @param person the number of people
	 * @return the carbohydrate content of the food for a person
	 */
	public double getCarbohydratePerPerson(Map<Ingredient, Integer> map, int person) {
		double sum = 0;
		for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {
			sum += (entry.getKey().getCarbohydrate() / PER_HUNDRED) * entry.getValue();
		}
		return sum / (double) person;
	}
}
