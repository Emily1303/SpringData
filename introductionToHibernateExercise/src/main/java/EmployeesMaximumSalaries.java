import javax.persistence.EntityManager;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Object[]> resultList = entityManager
                .createQuery("SELECT department.name, MAX(salary) FROM Employee " +
                                "GROUP BY department.name HAVING MAX(salary) NOT BETWEEN 30000 AND 70000",
                        Object[].class).getResultList();

        for (Object[] object : resultList) {
            System.out.printf("%s %s\n",
                    object[0], object[1]);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
