/* 
 * @(#)HibernateMergeExample.java    Created on 2016年4月10日
 * Copyright (c) 2016. All rights reserved.
 */
package nd.esp.com.hibernate.example;

import nd.esp.com.hibernate.model.Employee;
import nd.esp.com.hibernate.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateMergeExample {
	public static void main(String[] args) {
		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("***********************************************");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee emp = (Employee) session.load(Employee.class, new Long(19));
		System.out.println("Employee object loaded. " + emp);
		tx.commit();
		System.out.println("***********************************************");
		// merge example - data already present in tables
		emp.setName("test1");
		Transaction tx8 = session.beginTransaction();
		Employee emp4 = (Employee) session.merge(emp);
		System.out.println(emp4 == emp); // returns false
		emp.setName("test2");
		emp4.setName("merge");
		System.out.println("15. Before committing merge transaction");
		tx8.commit();
		System.out.println("16. After committing merge transaction");
		System.out.println("***********************************************");
		// Close resources
		sessionFactory.close();
	}
}
