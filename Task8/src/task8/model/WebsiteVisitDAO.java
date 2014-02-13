package task8.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;

import task8.databeans.WebsiteVisitBean;

public class WebsiteVisitDAO {
	public void insert(WebsiteVisitBean websiteVisitBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(websiteVisitBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(WebsiteVisitBean websiteVisitBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(websiteVisitBean);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public WebsiteVisitBean[] getVisitBefore(Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from WebsiteVisitBean where date >= :date");
		query.setParameter("date", date);
		List<?> list = (List<?>) query.list();
		WebsiteVisitBean[] websiteVisitBeans = list
				.toArray(new WebsiteVisitBean[list.size()]);
		session.close();

		return websiteVisitBeans;
	}
}