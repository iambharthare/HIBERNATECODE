package in.co.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.co.rays.Address;
import in.co.rays.Employee;

public class testone2one {

	public static void main(String[] args) {
		Address a= new Address();
		a.setAddressname("Indore");
		
		Employee e= new Employee();
		e.setName("RAM");
		
		a.setEmployee(e);
		e.setAddress(a);
		
		SessionFactory factory= new Configuration().configure().buildSessionFactory();
		Session s= factory.openSession();
		Transaction tx= s.beginTransaction();
		
		s.save(e);
		System.out.println("INSERTION DONE");
		tx.commit();
		s.close();
		
		
		

	}

}
