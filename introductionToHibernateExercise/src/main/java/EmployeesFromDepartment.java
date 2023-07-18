import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> filteredEmployees = entityManager
                .createQuery("FROM Employee WHERE department.name = :dep ORDER BY salary, id", Employee.class)
                .setParameter("dep", "Research and Development")
                .getResultList();

        for (Employee employee : filteredEmployees) {
            System.out.printf("%s %s from %s - $%s\n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDepartment().getName(),
                    employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
