package task8.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import task8.databeans.UserBean;

public class UserDAO {
	public void insert(UserBean userBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(userBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(UserBean userBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(userBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public UserBean getCustomerByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from CustomerBean where username = :username ");
		query.setParameter("username", username);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return null;
		UserBean userBean = (UserBean) list.get(0);
		return userBean;
	}

	public UserBean[] getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from UserBean");
		List<?> list = (List<?>) query.list();
		UserBean[] userBeans = list.toArray(new UserBean[list
				.size()]);
		session.close();

		return userBeans;
	}
}