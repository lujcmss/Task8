package task7.model;

import org.hibernate.Session;

import task7.databeans.FundPriceHistoryBean;

public class FundPriceHistoryDAO {
	public void insert(FundPriceHistoryBean fundpricehistory) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(fundpricehistory);

		session.getTransaction().commit();

	}
}
