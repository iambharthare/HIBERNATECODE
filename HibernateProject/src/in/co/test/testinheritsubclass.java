package in.co.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.co.rays.inheritsubclass.Cash1;
import in.co.rays.inheritsubclass.Cheque1;
import in.co.rays.inheritsubclass.CreditCard1;

public class testinheritsubclass {

	public static void main(String[] args) {
		testinherit2();

	}

	public static void testinherit2() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		CreditCard1 ccd = new CreditCard1();
		ccd.setAmount(15000);
		ccd.setCctype("mastercard");
		
		Cheque1 cq= new Cheque1();
		cq.setChequeno(12345);
		cq.setAmount(50000);
		cq.setBankname("RBI");
		
		Cash1 ch= new Cash1();
		ch.setAmount(10000);
		
		session.save(ccd);
		session.save(cq);
		session.save(ch);
		
		Transaction tx= session.beginTransaction();
		
		tx.commit();
		session.close();
		
		
		
	}

}
