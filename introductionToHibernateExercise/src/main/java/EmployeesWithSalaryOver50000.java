import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> filteredEmployees = entityManager.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        filteredEmployees.forEach(employee -> System.out.println(employee.getFirstName()));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
