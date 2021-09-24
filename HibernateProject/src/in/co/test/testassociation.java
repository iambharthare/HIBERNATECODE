package in.co.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.co.rays.association.AuctionItem;
import in.co.rays.association.Bid;

public class testassociation {

	public static void main(String[] args) {
		testone2many();

	}

	public static void testone2many() {
		AuctionItem item=new AuctionItem();
		item.setDescription("Watch");
		
		Bid bid1= new Bid();
		bid1.setAmount(45000);
		
		Bid bid2= new Bid();
		bid2.setAmount(78000);
		
		Bid bid3= new Bid();
		bid3.setAmount(62000);
		
		Set<Bid> bids= new HashSet<Bid>();
		bids.add(bid1);
		bids.add(bid2);
		bids.add(bid3);
		
		item.setBids(bids);
		
		SessionFactory factory= new Configuration().configure().buildSessionFactory();
		Session s= factory.openSession();
		Transaction tx= s.beginTransaction();
		
		s.save(item);
		
		tx.commit();
        s.close();		
		
		
		
	
	}

}
