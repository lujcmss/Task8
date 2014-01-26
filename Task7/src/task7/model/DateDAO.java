package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.DateBean;

public class DateDAO {
	public void insert(DateBean time) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction(); 
        session.save(time);
        session.getTransaction().commit();
	}
	public void update(DateBean time) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(time);
		session.getTransaction().commit();
	}
	
	public DateBean getDate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from DateBean");
		List<?> list = (List<?>) query.list();
		
		if (list.size() == 0) return null;
		
		DateBean dateBean = (DateBean) list.get(0);
		return dateBean;
	}
}
