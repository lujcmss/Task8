package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.PositionBean;

public class PositionDAO {
	public void insert(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(position);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(position);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public PositionBean[] getByCustomerId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from PositionBean where customerBean_customerId = :customerId");
		query.setParameter("customerId", id);
		List<?> list = (List<?>) query.list();
		PositionBean[] positionBeans = list.toArray(new PositionBean[list
				.size()]);

		session.close();
		return positionBeans;
	}

	public PositionBean getByCustomerAndFund(int CustomerId, int fundId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from PositionBean where customerBean_customerId = :customerId and fundBean_fundId = :fundId");
		query.setParameter("customerId", CustomerId);
		query.setParameter("fundId", fundId);
		List<?> list = (List<?>) query.list();

		if (list.size() == 0)
			return null;
		PositionBean positionBean = (PositionBean) list.get(0);

		session.close();
		return positionBean;
	}
}