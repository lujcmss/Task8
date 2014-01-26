package task7.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import task7.databeans.CustomerBean;

public class CustomerDAO {
	public void insert(CustomerBean customerBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(customerBean);
        session.getTransaction().commit();
	}
	public void update(CustomerBean customerBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(customerBean);
		session.getTransaction().commit();
	}
	
	public CustomerBean getCustomerByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from CustomerBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return null;
		
		CustomerBean customerBean = (CustomerBean) list.get(0);
		return customerBean;
	}
	
	public boolean hasCustomer(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from CustomerBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return false;
		return true;
	}
	
	public CustomerBean[] getAllCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from CustomerBean");
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return null;
		
		CustomerBean[] customerBeans = list.toArray(new CustomerBean[list.size()]);
		return customerBeans;
	}
}