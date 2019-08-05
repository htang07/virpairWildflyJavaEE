import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.virtualpairprogrammers.employeemanagement.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Test1 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testJpa() {
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
		em.close(); // must explicitly close entitymanager; it doesn't support java 8 try resource construct
	}

}
