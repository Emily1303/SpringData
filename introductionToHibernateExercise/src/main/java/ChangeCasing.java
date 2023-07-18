import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;

public class ChangeCasing {

    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Town> allTowns = entityManager.createQuery("FROM Town", Town.class).getResultList();

        for (Town town : allTowns) {
            if (town.getName().length() > 5) {
                entityManager.detach(town);
            } else {
                town.setName(town.getName().toUpperCase());
                entityManager.persist(town);
            }
        }

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
