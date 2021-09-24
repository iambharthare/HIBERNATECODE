package in.co.rays.hiber5;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class testhiberfive {

	public static void main(String[] args) {
		// testAdd();
		// testDelete();
		// testUpdate();
		// testlistCriteria();
		// testGet();
		// testCriteriabyid();
		// testCriteriabysinglecolumn();
		// testCriteriabymulticolumn();
		testNamedquery();

	}

	public static void testNamedquery() {
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session session = sf.openSession();
		Query query = session.getNamedQuery("alluser");
		List<User> list = query.getResultList();
		for (User user : list) {
			System.out.println(user.getID());
			System.out.println(user.getFirstname());
			System.out.println(user.getLastname());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
		}

	}

	public static void testCriteriabymulticolumn() {
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session s = sf.openSession();
		User u = new User();

		CriteriaBuilder builder = s.getCriteriaBuilder();

		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<User> root = query.from(User.class);
		query.multiselect(root.get("ID"), root.get("Firstname"), root.get("Lastname"));

		Query q = s.createQuery(query);
		List<Object[]> user = q.getResultList();
		Iterator<Object[]> list = user.iterator();
		while (list.hasNext()) {
			Object[] objects = (Object[]) list.next();
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);

		}

	}

	public static void testCriteriabysinglecolumn() {
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session s = sf.openSession();
		User u = new User();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<User> root = query.from(User.class);
		query.multiselect(root.get("Firstname"));
		Query q = s.createQuery(query);
		List<String> user = q.getResultList();
		Iterator<String> list = user.iterator();

		while (list.hasNext()) {
			String string = (String) list.next();
			System.out.println(string);
		}

	}

	public static void testCriteriabyid() {
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session s = sf.openSession();
		User u = new User();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("ID"), 4));

		Query q = s.createQuery(query);
		List<User> user = q.getResultList();
		Iterator<User> list = user.iterator();
		while (list.hasNext()) {
			u = (User) list.next();
			System.out.println(u.getID());
			System.out.println(u.getFirstname());
			System.out.println(u.getLastname());
			System.out.println(u.getUsername());
			System.out.println(u.getPassword());

		}

	}

	public static void testGet() {
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session session = sf.openSession();
		User u = (User) session.get(User.class, 4);
		System.out.println(u.getID());
		System.out.println(u.getFirstname());
		System.out.println(u.getLastname());
		System.out.println(u.getUsername());
		System.out.println(u.getPassword());
		session.close();
		HiberUtil.shutdown();

	}

	public static void testlistCriteria() {
		// TODO Auto-generated method stub
		SessionFactory sf = HiberUtil.getSessionFactory();
		Session s = sf.openSession();
		User u = new User();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		Query q = s.createQuery(query);
		List<User> user = q.getResultList();
		Iterator<User> list = user.iterator();
		while (list.hasNext()) {
			u = (User) list.next();
			System.out.println(u.getID());
			System.out.println(u.getFirstname());
			System.out.println(u.getLastname());
			System.out.println(u.getUsername());
			System.out.println(u.getPassword());

		}

	}

	public static void testUpdate() {
		SessionFactory factory = HiberUtil.getSessionFactory();
		Session sf = factory.openSession();
		Transaction tx = null;
		try {
			tx = sf.beginTransaction();
			User u = new User();
			u.setFirstname("NARENDRA");
			u.setID(2);
			sf.update(u);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			System.out.println("UPDATION DONE");
			sf.close();
			HiberUtil.shutdown();
		}

	}

	public static void testDelete() {
		SessionFactory factory = HiberUtil.getSessionFactory();
		Session sf = factory.openSession();
		Transaction tx = null;
		try {
			tx = sf.beginTransaction();
			User u = new User();
			u.setID(3);
			sf.delete(u);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			System.out.println("DELETION DONE");
			sf.close();
			HiberUtil.shutdown();
		}

	}

	public static void testAdd() {
		SessionFactory factory = HiberUtil.getSessionFactory();
		Session sf = factory.openSession();
		Transaction tx = null;
		try {
			tx = sf.beginTransaction();
			User u = new User();
			u.setFirstname("VIKAS");
			u.setLastname("NARESH");
			u.setUsername("firstperson");
			u.setPassword(12345);
			sf.save(u);
			tx.commit();

		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			System.out.println("INSERTION DONE");
			sf.close();
			HiberUtil.shutdown();
		}

	}

}
