package task7.model;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



import org.hibernate.mapping.Array;

import task7.databeans.CustomerBean;
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
	
	
	public TransactionBean[] getTransactionsPerUser(String email) {
		 System.out.println("Amount"+ email);
		Session session = HibernateUtil.getSessionFactory().openSession();
		 System.out.println("Amount"+ email);
		 Transaction tx = null;
		 TransactionBean[] user = null;
		 Integer id2 = getId(email);
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 Session session2 = HibernateUtil.getSessionFactory().openSession();
				Query query = session2.createQuery("from TransactionBean where customerBean_customerId =  :id ");
				query.setParameter("id", id2);
				List<TransactionBean> personList = query.list();
				 
				 TransactionBean[] a = new TransactionBean[personList.size()];
				 personList.toArray(a);
				 
				System.out.println("Data"+personList);
				
     	  
				user  = a;
				
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }
		 return user;
	}
	private int getId(String id) {
		 System.out.println(id);
		 int i = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 Session session2 = HibernateUtil.getSessionFactory().openSession();
				Query query = session2.createQuery("from CustomerBean where email=  :id ");
				query.setParameter("id", id);
				List <?>list = query.list();
				System.out.println("Data"+list);
				
     	  
				CustomerBean x = (CustomerBean)list.get(0);
				 i = x.getCustomerId();
				 System.out.println(x.getCustomerId());
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }
		
		 return i;
		
		
	}
	
	
	
	
	
}