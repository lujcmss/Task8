package task7.model;

import org.hibernate.Session;

import task7.databeans.FundBean;


public class FundDAO {
	public void insert(FundBean fund) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        session.save(fund);
        
        session.getTransaction().commit();
        HibernateUtil.shutdown();
	}
}
