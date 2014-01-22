package task7.model;

	
	import org.hibernate.Session;


	import task7.databeans.EmployeeBean;

	public class EmployeeDAO {
		public void insert(EmployeeBean employee) {
			Session session = HibernateUtil.getSessionFactory().openSession();

	        session.beginTransaction();
	        
	        session.save(employee);
	        
	        session.getTransaction().commit();
		}
	}


