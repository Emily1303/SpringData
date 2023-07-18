import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();

        List<Employee> employees = entityManager.createQuery("FROM Employee", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            String nameLowerCase = employee.getFirstName().toLowerCase();
            if (nameLowerCase.startsWith(pattern.toLowerCase())) {
                System.out.printf("%s %s - %s - ($%s)\n",
                        employee.getFirstName(), employee.getLastName(),
                        employee.getJobTitle(), employee.getSalary());
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
