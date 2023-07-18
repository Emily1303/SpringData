import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.Scanner;

public class GetEmployeesWithProject {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);

        int givenId = Integer.parseInt(scanner.nextLine());
        Employee employee = entityManager.createQuery("FROM Employee WHERE id = :id", Employee.class)
                .setParameter("id", givenId)
                .getSingleResult();

        System.out.printf("%s %s - %s\n", employee.getFirstName(), employee.getLastName(),
                employee.getJobTitle());

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf("     %s\n", project.getName()));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
