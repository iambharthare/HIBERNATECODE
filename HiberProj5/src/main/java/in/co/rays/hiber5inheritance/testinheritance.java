package in.co.rays.hiber5inheritance;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.co.rays.hiber5.HiberUtil;

public class testinheritance {

	public static void main(String[] args) throws Exception {
		testpayment();

	}

	public static void testpayment() throws Exception {
		CreditCard cc = new CreditCard();
		cc.setAmount(1000);
		cc.setCcTye("VISA");
		
		Cash c= new Cash();
		c.setAmount(6000);
		
		Cheque cheque = new Cheque();
		cheque.setAmount(56000);
		cheque.setCheqNo(45612);
		
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session session = sf.openSession();
		
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		
			session.save(cc);
			session.save(cheque);
			session.save(c);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		
		} finally {
			System.out.println("INHERITANCE DONE");
			session.close();
			HiberUtil.shutdown();
		}
		
	}

}
