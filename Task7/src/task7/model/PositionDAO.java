package task7.model;

import org.hibernate.Session;

import task7.databeans.PositionBean;

public class PositionDAO {
	public void insert(PositionBean position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(position);

		session.getTransaction().commit();

	}
}