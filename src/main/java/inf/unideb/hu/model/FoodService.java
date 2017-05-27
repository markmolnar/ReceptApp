package inf.unideb.hu.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import inf.unideb.hu.util.JpaUtil;

/**
 * Service class for food objects.
 * @author mark
 *
 */
public class FoodService implements FoodDao{

	/**
	 * Creating FoodService object.
	 */
	public FoodService() {
		super();
	}


	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#getAllFoods()
	 */
	@Override
	public List<Food> getAllFoods() {
		//EntityManager entitymanager = emfactory.createEntityManager( );
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Food> query = entitymanager.createQuery("SELECT f FROM Food f",Food.class);
		List<Food> list=query.getResultList();
		entitymanager.close();
		return list;
		}
	
	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#getFoodByName(java.lang.String)
	 */
	@Override
	public Food getFoodByName(String name)
	{
		//EntityManager entitymanager = emfactory.createEntityManager( );
		EntityManager entitymanager =JpaUtil.getEntityManager();
		TypedQuery<Food> query = entitymanager.createQuery(
			      "SELECT f FROM Food f where name=:pname", Food.class);
		query.setParameter("pname",name);
		Food food = query.getSingleResult();
		entitymanager.close();
		return food;
	}

	/**
	 * Searches food by the given name but not strictly.
	 * @param name the name of the food
	 * @return all foods that has a name like the parameter
	 */
	public List<Food> searchFoodByName(String name)
	{
		//EntityManager entitymanager = emfactory.createEntityManager( );
		EntityManager entitymanager = JpaUtil.getEntityManager();
		TypedQuery<Food> query = entitymanager.createQuery(
			      "SELECT f FROM Food f where name like ( :pname )", Food.class);
		query.setParameter("pname","%"+name+"%");
		List<Food> list = query.getResultList();
		entitymanager.close();
		return list;
	}
	
	 /* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#saveFood(inf.unideb.hu.model.Food)
	 */
	@Override
	 @Transactional
	 public int saveFood(Food food)
	 {
		 //EntityManager entitymanager = emfactory.createEntityManager( );
		 EntityManager entitymanager = JpaUtil.getEntityManager();
		 entitymanager.getTransaction().begin();
		 entitymanager.persist(food);
		 //entitymanager.flush();
		 entitymanager.getTransaction().commit();
		 entitymanager.close();
		 return food.getId();
	 }


	 /* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#saveFoodItem(inf.unideb.hu.model.FoodItem)
	 */
	@Override
	 @Transactional
	 public void saveFoodItem(FoodItem fooditem)
	 {
		 //EntityManager entitymanager = emfactory.createEntityManager( );
		 EntityManager entitymanager = JpaUtil.getEntityManager();
		 entitymanager.getTransaction().begin();
		 entitymanager.persist(fooditem);
		 entitymanager.getTransaction().commit();
		 entitymanager.close();
	 }

	/* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#removeFood(inf.unideb.hu.model.Food)
	 */
	@Override
	@Transactional
	 public void removeFood(Food food)
	 {
		 //EntityManager entitymanager = emfactory.createEntityManager( );
		 EntityManager entitymanager = JpaUtil.getEntityManager();
		 entitymanager.getTransaction().begin();
		 entitymanager.remove(entitymanager.merge(food));
		 entitymanager.getTransaction().commit();
		 entitymanager.close();
	 }
	
	 /* (non-Javadoc)
	 * @see inf.unideb.hu.model.FoodDao#searchFood(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	 public List<Food> searchFood(String name,Integer min,Integer max)
		{
			//EntityManager entitymanager = emfactory.createEntityManager( );
			EntityManager entitymanager = JpaUtil.getEntityManager();
			CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
			CriteriaQuery<Food> cq = cb.createQuery(Food.class);
			Root<Food> root = cq.from(Food.class);
			cq.select(root)
			.where(
					cb.like(root.<String>get("name"),"%"+name+"%"),
					cb.between(root.get("carbohydrate"), min, max)
					);
			TypedQuery<Food> query = entitymanager.createQuery(cq);
			List<Food> list = query.getResultList();
			entitymanager.close();
			return list;
		}
}
