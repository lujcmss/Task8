package task7.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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
		session.getTransaction().commit();

	}
	public void delete(FundPriceHistoryBean fundpricehistory)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(fundpricehistory);
		session.getTransaction().commit();


	}

	public FundPriceHistoryBean[] getAllHistory() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundPriceHistoryBean");
		List<?> list = (List<?>) query.list();
	  
		FundPriceHistoryBean[] fundHistoryBeans = list.toArray(new FundPriceHistoryBean[list.size()]);
		return fundHistoryBeans;
	}
	
	public long getPriceByFundAndDate(int fundId, Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from FundPriceHistoryBean where fundBean_fundId = :fundId and priceDate = :priceDate");
		query.setParameter("fundId", fundId);
		query.setParameter("priceDate", date);
		List<?> list = (List<?>) query.list();
	  
		if (list == null || list.size() == 0) return 0;
		FundPriceHistoryBean fundHistoryBean = (FundPriceHistoryBean) list.get(0);
		return fundHistoryBean.getPrice();		
	}
}




