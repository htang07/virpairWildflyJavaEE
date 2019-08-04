import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;




public class TestHarness {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeeDb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//Employee employee1 = new Employee("James","Smith","Engineer",1000);
		//em.persist(employee1);
		
		Query q = em.createQuery("select employee from Employee employee");
		List<Employee> results = q.getResultList();
		
		for (Employee employee : results) {
			System.out.println(employee);
		}
		
		
		tx.commit();
		em.close();

	}

}
