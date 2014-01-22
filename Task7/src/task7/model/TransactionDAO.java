package task7.model;
import org.hibernate.Session;

import task7.databeans.TransactionBean;

public class TransactionDAO {
	public void insert(TransactionBean transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		session.save(transaction);

		session.getTransaction().commit();

	
	
}
}