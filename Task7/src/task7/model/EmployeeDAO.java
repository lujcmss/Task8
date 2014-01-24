package task7.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import task7.databeans.EmployeeBean;

public class EmployeeDAO {
	public boolean authenticate(String email, String password) {
		EmployeeBean employee = getEmployeeByemail(email);
		if (employee != null && employee.getEmail().equals(email)
				&& employee.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public EmployeeBean getEmployeeByemail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		EmployeeBean customer = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from EmployeeBean where employeeEmail='"
					+ email + "'");
			customer = (EmployeeBean) query.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	return employee;
	}

	public List<EmployeeBean> getListOfEmployees() {
		List<EmployeeBean> list = new ArrayList<EmployeeBean>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = session.createQuery("from EmployeeBean where employeeEmail= :email").list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}