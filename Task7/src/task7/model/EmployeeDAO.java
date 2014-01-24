package task7.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import task7.databeans.CustomerBean;
import task7.databeans.EmployeeBean;

public class EmployeeDAO {
	public void insert(EmployeeBean employeeBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(employeeBean);
        session.getTransaction().commit();
	}
	public void update(EmployeeBean employeeBean) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(employeeBean);
		session.getTransaction().commit();
	}
	public EmployeeBean getEmployeeByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from EmployeeBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return null;
		EmployeeBean employeeBean = (EmployeeBean) list.get(0);
		return employeeBean;
	}
	
	public boolean hasEmployee(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from EmployeeBean where email = :email ");
		query.setParameter("email", email);
		List<?> list = (List<?>) query.list();
	  
		if (list.size() == 0) return false;
		return true;
	}
}