package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;
import task7.databeans.PositionBean;

public class PositionDAO {
	public void insert(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(position);

		session.getTransaction().commit();

	}

	public void update(PositionBean position) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(position);
		session.getTransaction().commit();

	}

	public void delete(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(position);
		session.getTransaction().commit();

	}
	
	public PositionBean[] getByCustomerId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from PositionBean where customerBean_customerId = :customerId");
		query.setParameter("customerId", id);
		List<?> list = (List<?>) query.list();
	  
		PositionBean[] positionBeans = list.toArray(new PositionBean[list.size()]);
		return positionBeans;
	}
}