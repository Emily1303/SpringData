import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class FindTheLatest10Projects {
    public static void main(String[] args) {

        EntityManager entityManager = Utils.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf("Project name: %s\n" +
                        "       Project Description: %s\n" +
                        "       Project Start Date:%s\n" +
                        "       Project End Date: %s\n",
                        project.getName(),
                        project.getDescription(),
                        project.getStartDate(),
                        project.getEndDate()));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
