package task7.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import task7.databeans.TransactionBean;

public class TransactionDAO {
	public void insert(TransactionBean transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
	        session.beginTransaction();
	        session.save(transaction);
	        session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}
	
	public void update(TransactionBean transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.merge(transaction);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}
	
	/*
	public void delete(TransactionBean transaction) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(transaction);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
		} finally {
			session.close();
		}
	}
	*/
	
	public TransactionBean[] getTransactionsByCustomerId(int customerId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from TransactionBean where customerBean_customerId = :customerId");
		query.setParameter("customerId", customerId);
		List<?> list = (List<?>) query.list();
		TransactionBean[] transactionBeans = list.toArray(new TransactionBean[list.size()]);

		session.close();
		return transactionBeans;
	}
	
	public TransactionBean[] getByStatus(String status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from TransactionBean where status = :status");
		query.setParameter("status", status);
		List<?> list = (List<?>) query.list();
		TransactionBean[] transactionBeans = list.toArray(new TransactionBean[list.size()]);

		session.close();
		return transactionBeans;
	}	
}