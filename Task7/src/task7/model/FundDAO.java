package task7.model;

import org.hibernate.Session;

import task7.databeans.CustomerBean;
import task7.databeans.FundBean;

public class FundDAO {
	public void insert(FundBean fund) {
		Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        
        session.save(fund);
        
        session.getTransaction().commit();
	}
	public void update(FundBean fund) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(fund);
		session.beginTransaction().commit();

	}
	public void delete(FundBean fund)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(fund);
		session.beginTransaction().commit();


	}


}
