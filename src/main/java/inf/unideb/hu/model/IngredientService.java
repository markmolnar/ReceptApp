package inf.unideb.hu.model;

import java.util.List;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;

import inf.unideb.hu.util.JpaUtil;

/**
 * Service class for ingredient objects.
 * @author mark
 *
 */
public class IngredientService implements IngredientDao{

	/**
	 * Creating IngredientService object.
	 */
	public IngredientService() {
		super();
	}



	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getIngredientGroups()
	 */
	@Override
	public List<String> getIngredientGroups() {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<String> query = entitymanager.
			      createQuery("Select inggroup.name from IngredientGroup inggroup",String.class);
		List<String> list = query.getResultList();
		entitymanager.close();
		return list;
	}


	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getIngredientNames(java.lang.String)
	 */
	@Override
	public List<String> getIngredientNames(String name) {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<String> query = entitymanager.
			      createQuery("Select ing.name from IngredientGroup inggroup inner join Ingredient ing"
			      		+ " on inggroup.id=ing.ingredientgroupid where inggroup.name=:gname",String.class);
		query.setParameter("gname",name);
		List<String> list = query.getResultList();
		entitymanager.close();
		return list;
	}

	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getIngredientByName(java.lang.String)
	 */
	@Override
	public Ingredient getIngredientByName(String name) {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Ingredient> query = entitymanager.createQuery(
			      "SELECT i FROM Ingredient i where name=:pname", Ingredient.class);
		query.setParameter("pname",name);
		Ingredient ingredient = query.getSingleResult();
		entitymanager.close();
		return ingredient;
	}


	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getIngredientById(java.lang.Integer)
	 */
	@Override
	public Ingredient getIngredientById(Integer id) {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Ingredient> query = entitymanager.createQuery(
			      "SELECT i FROM Ingredient i where id=:pid", Ingredient.class);
		query.setParameter("pid",id);
		Ingredient ingredient = query.getSingleResult();
		entitymanager.close();
		return ingredient;
	}


	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getQuantityByFoodId(java.lang.Integer)
	 */
	@Override
	public List<Integer> getQuantityByFoodId(Integer id) {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Integer> query = entitymanager.createQuery(
			      "SELECT quantity FROM FoodItem where FoodId=:pfoodid",Integer.class);
		query.setParameter("pfoodid",id);
		List<Integer> quantitylist=query.getResultList();
		entitymanager.close();
		return quantitylist;
	}


	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.IngredientDao#getAllIngredients(java.lang.Integer)
	 */
	@Override
	public List<Ingredient> getAllIngredients(Integer id) {
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Ingredient> query = entitymanager.createQuery("SELECT i FROM Ingredient i where id in "
	    		+ "(select fi.IngredientId from FoodItem fi where fi.FoodId=:pid)",Ingredient.class);
	    query.setParameter("pid",id);
	    List<Ingredient> list=query.getResultList();
	    entitymanager.close();
	    return list;
	  }
}

