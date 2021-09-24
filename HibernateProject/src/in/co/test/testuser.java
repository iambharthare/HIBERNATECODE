package in.co.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import in.co.rays.User;

public class testuser {

	public static void main(String[] args) throws Exception {
		// testadd();
		// testUpdate();
		// testdelete();
		// testget();
		// testlist();
		// testarray();
		// testCriteria();
		// testRestrictions();
		// testProjections();
		// testmultipleRestrictions();
		// testsecondlevelcache();
		// testauthenticate();
		// testNamedQuery();
		testmerge();

	}

	public static void testmerge() {
		User u = new User();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		u = (User) session.get(User.class, 1);

		session.close();

		User u1 = new User();
		Session s2 = factory.openSession();
		u1 = (User) s2.get(User.class, 1);
		Transaction tx = s2.beginTransaction();
		u.setFname("RAVI");
		s2.merge(u);

		tx.commit();
		s2.close();

	}

	public static void testNamedQuery() {
		User u = new User();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.getNamedQuery("allUser");

		List l = q.list();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			u = (User) it.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUsername());
			System.out.println(u.getPwd());

		}

	}

	public static void testauthenticate() throws Exception {
		User u = new User();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User where username = ? and pwd = ?");
		q.setString(0, "beinghuman");
		q.setString(1, "123 v");

		List list = q.list();
		if (list.size() == 1) {
			u = (User) list.get(0);
			System.out.println(u.getFname());

		} else {
			throw new Exception("login.invalid.User");
		}
		session.close();
		return;
	}

	public static void testsecondlevelcache() {
		// User u= new User();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User u = (User) session.get(User.class, 2);

		System.out.println("DATA GET DONE");
		System.out.println(u.getId());
		System.out.println(u.getFname());
		System.out.println(u.getLname());
		System.out.println(u.getUsername());
		System.out.println(u.getPwd());

		session.clear();

		User u1 = (User) session.get(User.class, 3);
		System.out.println("DATA GET DONE");
		System.out.println(u1.getId());
		System.out.println(u1.getFname());
		System.out.println(u1.getLname());
		System.out.println(u1.getUsername());
		System.out.println(u1.getPwd());

		session.close();

	}

	public static void testmultipleRestrictions() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.or(Restrictions.like("lname", "khan"), Restrictions.eq("pwd", "12345")));
		List l = crit.list();
		Iterator it = l.iterator();
		while (it.hasNext()) {
			User u = (User) it.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUsername());
			System.out.println(u.getPwd());

		}

	}

	public static void testProjections() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Criteria crit = session.createCriteria(User.class);

		ProjectionList p = Projections.projectionList();
		/*
		 * p.add(Projections.property("fname")); p.add(Projections.property("lname"));
		 * p.add(Projections.property("username"));
		 */
		// p.add(Projections.rowCount());
		p.add(Projections.groupProperty("fname"));

		crit.setProjection(p);
		List l = crit.list();
		Iterator it = l.iterator();

		Object arr;
		while (it.hasNext()) {
			arr = (Object) it.next();

			System.out.println(arr);
			/*
			 * System.out.println(arr[1]); System.out.println(arr[2]);
			 */

		}

	}

	public static void testRestrictions() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		// Query q = session.createQuery("from User");
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.like("lname", "khan"));
		crit.add(Restrictions.eq("pwd", "12345"));
		List l = crit.list();
		Iterator it = l.iterator();
		// Object[] arr;

		while (it.hasNext()) {
			// Typcast for single data
			/*
			 * User u = (User) it.next(); System.out.println(u.getId());
			 * System.out.println(u.getFname()); System.out.println(u.getLname());
			 * System.out.println(u.getUsername()); System.out.println(u.getPwd());
			 */

			// array for list or multiple data
			User u = (User) it.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUsername());
			System.out.println(u.getPwd());

		}

	}

	public static void testCriteria() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query q = session.createQuery("from User");
		Criteria crit = session.createCriteria(User.class);
		List l = crit.list();

		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User u = (User) it.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUsername());
			System.out.println(u.getPwd());

		}

	}

	public static void testarray() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query q = session.createQuery("select u.id,u.fname from User u");
		List l = q.list();
		Iterator it = l.iterator();
		Object[] ar;

		while (it.hasNext()) {
			ar = (Object[]) it.next();
			System.out.println(ar[0]);
			System.out.println(ar[1]);

		}

	}

	public static void testlist() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Query q = session.createQuery("from User");

		List l = q.list();
		Iterator it = l.iterator();

		while (it.hasNext()) {
			User u = (User) it.next();
			System.out.println(u.getId());
			System.out.println(u.getFname());
			System.out.println(u.getLname());
			System.out.println(u.getUsername());
			System.out.println(u.getPwd());

		}

	}

	public static void testget() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		User u = (User) session.get(User.class, 2);
		System.out.println("DATA GET DONE");
		System.out.println(u.getId());
		System.out.println(u.getFname());
		System.out.println(u.getLname());
		System.out.println(u.getUsername());
		System.out.println(u.getPwd());

		session.close();

	}

	public static void testdelete() {
		User u = new User();
		u.setId(3);

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.delete(u);
		System.out.println("DELETION DONE");

		tx.commit();
		session.close();

	}

	public static void testUpdate() {
		User u = new User();
		u.setId(3);
		u.setFname("JAY");
		u.setLname("SHAH");
		u.setUsername("jayshah");
		u.setPwd("12345");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.update(u);
		System.out.println("UPDATION DONE");

		tx.commit();
		session.close();

	}

	public static void testadd() {
		// TODO Auto-generated method stub
		User u = new User();
		u.setFname("SHUBH");
		u.setLname("SINGH");
		u.setUsername("shubhsingh");
		u.setPwd("12345");

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		session.save(u);
		System.out.println("INSERTION DONE");

		tx.commit();
		session.close();

	}

}
