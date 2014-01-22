package task7.model;

import org.hibernate.Session;

import task7.databeans.FundBean;
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
		session.beginTransaction().commit();

	}

	public void delete(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(position);
		session.beginTransaction().commit();

	}
}