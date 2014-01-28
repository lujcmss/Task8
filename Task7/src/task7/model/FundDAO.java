package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.FundBean;

public class FundDAO {
	public void insert(FundBean fund) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(fund);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(FundBean fund) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(fund);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public FundBean getFundById(int fundId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from FundBean where fundId = :fundId ");
		query.setParameter("fundId", fundId);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return null;
		FundBean fundBean = (FundBean) list.get(0);
		return fundBean;
	}

	public FundBean getFundByName(String fundName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundBean where name = :name ");
		query.setParameter("name", fundName);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return null;
		FundBean fundBean = (FundBean) list.get(0);
		return fundBean;
	}

	public FundBean getFundByTicker(String symbol) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from FundBean where symbol = :symbol ");
		query.setParameter("symbol", symbol);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return null;
		FundBean fundBean = (FundBean) list.get(0);
		return fundBean;
	}

	public boolean hasFund(String fundName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundBean where name = :name ");
		query.setParameter("name", fundName);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return false;
		return true;
	}

	public boolean hasTicker(String symbol) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from FundBean where symbol = :symbol ");
		query.setParameter("symbol", symbol);
		List<?> list = (List<?>) query.list();
		session.close();

		if (list.size() == 0)
			return false;
		return true;
	}

	public FundBean[] getAllFunds() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundBean");
		List<?> list = (List<?>) query.list();
		FundBean[] fundBeans = list.toArray(new FundBean[list.size()]);
		session.close();
		
		return fundBeans;
	}
}