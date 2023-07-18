import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.Random;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        Random random = new Random();
        int randomNumber = random.nextInt(32);

        Town town = entityManager.createQuery("FROM Town WHERE id = :random", Town.class)
                .setParameter("random", randomNumber)
                .getSingleResult();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        newAddress.setTown(town);
        entityManager.persist(newAddress);

        String givenLastName = scanner.nextLine();
        Employee employee = entityManager.createQuery("FROM Employee WHERE lastName = :name", Employee.class)
                .setParameter("name", givenLastName)
                .getSingleResult();

        employee.setAddress(newAddress);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
