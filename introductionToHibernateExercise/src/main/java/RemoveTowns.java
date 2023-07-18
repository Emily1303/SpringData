import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        String givenTown = scanner.nextLine();

        Town townToDelete = entityManager.createQuery("FROM Town WHERE name = :town", Town.class)
                .setParameter("town", givenTown)
                .getSingleResult();

        List<Address> addresses = entityManager.createQuery("FROM Address WHERE town.name = :town", Address.class)
                .setParameter("town", givenTown)
                .getResultList();

        int addressesToDelete = addresses.size();
        for (Address address : addresses) {
            address.getEmployees().forEach(employee -> employee.setAddress(null));

            entityManager.remove(address);
        }

        entityManager.remove(townToDelete);

        if (addressesToDelete > 1) {
            System.out.printf("%d addresses in %s deleted", addressesToDelete, givenTown);
        } else {
            System.out.printf("1 address in %s deleted", givenTown);
        }

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
