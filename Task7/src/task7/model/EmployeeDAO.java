package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.EmployeeBean;

public class EmployeeDAO {
	public void insert(EmployeeBean employeeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        session.beginTransaction();
	        session.save(employeeBean);
	        session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}
	public void update(EmployeeBean employeeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(employeeBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}
	public EmployeeBean getEmployeeByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from EmployeeBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return null;
		EmployeeBean employeeBean = (EmployeeBean) list.get(0);

		session.close();
		return employeeBean;
	}
	
	public boolean hasEmployee(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from EmployeeBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();

		session.close();
		if (list.size() == 0) return false;
		return true;
	}
}