package task7.model;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;


import task7.databeans.CustomerBean;

public class CustomerDAO {

	public boolean authenticate(String email, String password) {
	 CustomerBean customer = getCustomerByemail(email);		
		 if(customer!=null && customer.getEmail().equals(email) && customer.getPassword().equals(password)){
			 return true;
		 }else{
			 return false;
		 }
	}
	public CustomerBean getCustomerByemail(String email) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;
		 CustomerBean customer = null;
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 Query query = session.createQuery("from User where userId='"+email+"'");
			customer = (CustomerBean)query.uniqueResult();
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }
		 return customer;
	}

	public List<CustomerBean> getListOfCustomers(){
		 List<CustomerBean> list = new ArrayList<CustomerBean>();
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction tx = null;	
		 try {
			 tx = session.getTransaction();
			 tx.begin();
			 list = session.createQuery("from getCustomer").list();					
			 tx.commit();
		 } catch (Exception e) {
			 if (tx != null) {
				 tx.rollback();
			 }
			 e.printStackTrace();
		 } finally {
			 session.close();
		 }
		 return list;
	}
	
	}