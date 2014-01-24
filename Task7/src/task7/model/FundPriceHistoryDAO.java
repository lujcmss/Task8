package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.FundBean;
import task7.databeans.FundPriceHistoryBean;

public class FundPriceHistoryDAO {
	public void insert(FundPriceHistoryBean fundpricehistory) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(fundpricehistory);

		session.getTransaction().commit();

	}

	public void update(FundPriceHistoryBean fundpricehistory) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(fundpricehistory);
		session.beginTransaction().commit();

	}
	public void delete(FundPriceHistoryBean fundpricehistory)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(fundpricehistory);
		session.beginTransaction().commit();


	}

	public FundPriceHistoryBean[] getAllHistory() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundPriceHistoryBean");
		List<?> list = (List<?>) query.list();
	  
		FundPriceHistoryBean[] fundHistoryBeans = list.toArray(new FundPriceHistoryBean[list.size()]);
		return fundHistoryBeans;
	}
}




