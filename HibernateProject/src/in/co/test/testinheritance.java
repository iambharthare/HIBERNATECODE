package in.co.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.co.rays.inheritance.Cash;
import in.co.rays.inheritance.Cheque;
import in.co.rays.inheritance.CreditCard;

public class testinheritance {

	public static void main(String[] args) {
		testtableperclass();

	}

	public static void testtableperclass() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		CreditCard ccd = new CreditCard();
		ccd.setAmount(15000);
		ccd.setCctype("mastercard");
		
		Cheque cq= new Cheque();
		cq.setChequeno(12345);
		cq.setAmount(50000);
		cq.setBankname("RBI");
		
		Cash ch= new Cash();
		ch.setAmount(10000);
		
		session.save(ccd);
		session.save(cq);
		session.save(ch);
		
		Transaction tx= session.beginTransaction();
		
		tx.commit();
		session.close();
		
		
	}

}
