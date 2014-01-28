package task7.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.FundPriceHistoryBean;

public class FundPriceHistoryDAO {
	public void insert(FundPriceHistoryBean fundpricehistory) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(fundpricehistory);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}

	public void update(FundPriceHistoryBean fundpricehistory) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(fundpricehistory);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}

	}

	public FundPriceHistoryBean[] getAllHistory() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundPriceHistoryBean");
		List<?> list = (List<?>) query.list();
		FundPriceHistoryBean[] fundHistoryBeans = list
				.toArray(new FundPriceHistoryBean[list.size()]);

		session.close();
		return fundHistoryBeans;
	}

	public long getPriceByFundAndDate(int fundId, Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from FundPriceHistoryBean where fundBean_fundId = :fundId and priceDate = :priceDate");
		query.setParameter("fundId", fundId);
		query.setParameter("priceDate", date);
		List<?> list = (List<?>) query.list();

		if (list.size() == 0)
			return 0;
		FundPriceHistoryBean fundHistoryBean = (FundPriceHistoryBean) list
				.get(0);

		session.close();
		return fundHistoryBean.getPrice();
	}
}