import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> departments = List.of("Engineering", "Tool Design", "Marketing", "Information Services");

        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE department.name IN :departments",
                        Employee.class)
                .setParameter("departments", departments)
                .getResultList();

        for (Employee employee : employees) {
            BigDecimal previousSalary = employee.getSalary();
            employee.setSalary(previousSalary.multiply(BigDecimal.valueOf(1.12)));
            entityManager.persist(employee);

            System.out.printf("%s %s ($%s)\n",
                    employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
