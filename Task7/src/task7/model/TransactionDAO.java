package task7.model;
import org.hibernate.Session;

import task7.databeans.FundBean;
import task7.databeans.TransactionBean;

public class TransactionDAO {
	public void insert(TransactionBean transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(transaction);

		session.getTransaction().commit();

	
	
}
	public void update(TransactionBean transaction) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.merge(transaction);
		session.beginTransaction().commit();

	}
	public void delete(TransactionBean transaction)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.delete(transaction);
		session.beginTransaction().commit();


	}
	
	
	
	
	
}