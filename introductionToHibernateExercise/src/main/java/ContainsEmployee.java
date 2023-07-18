import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        String name = scanner.nextLine();

        List<Employee> employee = entityManager.
                createQuery("FROM Employee WHERE CONCAT_WS(' ', firstName, lastName) = :name", Employee.class)
                .setParameter("name", name)
                .getResultList();

        if (employee.isEmpty()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
