package inf.unideb.hu.tests;

import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import inf.unideb.hu.model.Content;
import inf.unideb.hu.model.Ingredient;

public class TestContent {
	Content testcontent=new Content();
	Ingredient ingredientA=new Ingredient(1001,200,200,200,200);
	Ingredient ingredientB=new Ingredient(1002,200,200,200,200);
	Ingredient ingredientC=new Ingredient(1003,200,200,200,200);
	Integer testquantityA=new Integer(100);
	Integer testquantityB=new Integer(100);
	Integer testquantityC=new Integer(100);
	Map<Ingredient,Integer> testIngredientAndQuantity=new LinkedHashMap<Ingredient,Integer>();
	
	@Test
	public void testCalculateEnergy() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double energysum=testcontent.getTotalEnergy(testIngredientAndQuantity);
	      Assert.assertEquals(600, energysum,0.00001);
	   }
	@Test
	public void testCalculateFat() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double fatsum=testcontent.getTotalFat(testIngredientAndQuantity);
	      Assert.assertEquals(600, fatsum,0.00001);
	   }
	
	@Test
	public void testCalculateProtein() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double proteinsum=testcontent.getTotalProtein(testIngredientAndQuantity);
	      Assert.assertEquals(600, proteinsum,0.00001);
	   }
	@Test
	public void testCalculateCarbohydrate() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double carbohydratesum=testcontent.getTotalCarbohydrate(testIngredientAndQuantity);
	      Assert.assertEquals(600, carbohydratesum,0.00001);
	   }

	@Test
	public void testCalculateEnergyPerPerson() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double energysum=testcontent.getEnergyPerPerson(testIngredientAndQuantity,2);
	      Assert.assertEquals(300, energysum,0.00001);
	   }

	@Test
	public void testCalculateFatPerPerson() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double energysum=testcontent.getFatPerPerson(testIngredientAndQuantity,2);
	      Assert.assertEquals(300, energysum,0.00001);
	   }

	@Test
	public void testCalculateProteinPerPerson() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double energysum=testcontent.getProteinPerPerson(testIngredientAndQuantity,2);
	      Assert.assertEquals(300, energysum,0.00001);
	   }

	@Test
	public void testCalculateCarbohydratePerPerson() {
	      testIngredientAndQuantity.put(ingredientA,testquantityA);
	      testIngredientAndQuantity.put(ingredientB,testquantityB);
	      testIngredientAndQuantity.put(ingredientC,testquantityC);
	      double carbohydratesum=testcontent.getCarbohydratePerPerson(testIngredientAndQuantity,2);
	      Assert.assertEquals(300, carbohydratesum,0.00001);
	   }
}
