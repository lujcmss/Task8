package task7.model;

import org.hibernate.Session;

import task7.databeans.CustomerBean;

public class CustomerDAO {
	public void insert(CustomerBean customer) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        session.save(customer);
        
        session.getTransaction().commit();
	}
}
