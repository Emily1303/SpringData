import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {

    public static EntityManager createEntityManager() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("vehicles_hierarchy");

        return entityManagerFactory.createEntityManager();
    }
}
