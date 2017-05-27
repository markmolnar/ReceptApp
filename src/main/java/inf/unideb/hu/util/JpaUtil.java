package inf.unideb.hu.util;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Util class for JPA EntityManagerFactory and EntityManager.
 * @author mark
 */
public class JpaUtil {

	/**
	 * The project's EntityManagerFactory initialized in static block.
	 */
	private  static final EntityManagerFactory emFactory;

	/**
	 * Logger of the JpaUtil class.
	 */
	private static final Logger LOGGER=Logger.getLogger(JpaUtil.class.getName());
	
	static {
		LOGGER.info("Creating the EntityManagerFactory");
		emFactory = Persistence.createEntityManagerFactory("ProbaService");
	}

	/**
	 * Gets the EntityManager.
	 * @return the EntityManager
	 */
	public static EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	/**
	 * Closes the EntityManagerFactory.
	 */
	public static void close() {
		LOGGER.info("Closing the EntityManagerFactory");
		emFactory.close();
	}

	/**
	 * Gets the EntityManagerFactory.
	 * @return the EntityManagerFactory
	 */
	public EntityManagerFactory getEntitymanagerFactory() {
		return emFactory;
	}
}
