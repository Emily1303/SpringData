import entities.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> allAddresses =  entityManager
                .createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10).getResultList();

        for (Address address : allAddresses) {
            System.out.printf("%s, %s - %d employees\n", address.getText(),
                    address.getTown().getName(),
                    address.getEmployees().size());
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
