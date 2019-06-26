package javaspringexamples.springJPA.JPAQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		// Creating entities
		Person person = new Person();
		person.setFirstName("Jabane");
		person.setLastName("KOLOBANE");
		Car car1 = new Car();
		car1.setModel("R4");
		Car car2 = new Car();
		car2.setModel("R6");
		// Add cars into person's collection
		person.getCars().add(car1);
		person.getCars().add(car2);
		// persisting entities
		entityManager.persist(person);

		// SELECT
		Query query = entityManager.createQuery("Select p from Person p");
		List<Person> l_p = query.getResultList();
		for (Person p : l_p)
			System.out.println(p);

		// UPDATE
		query = entityManager.createQuery("Update Person set firstName = ?");
		query.setParameter(1, "waaaaaaaaaaa");
		query.executeUpdate();

		transaction.commit();
		entityManager.close();
	}
}
