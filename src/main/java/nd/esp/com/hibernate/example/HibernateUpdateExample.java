/* 
 * @(#)HibernateUpdateExample.java    Created on 2016年4月10日
 * Copyright (c) 2016. All rights reserved.
 */
package nd.esp.com.hibernate.example;

import nd.esp.com.hibernate.model.Employee;
import nd.esp.com.hibernate.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateUpdateExample {
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
		// update example
		emp.setName("Updated name");
		emp.getAddress().setCity("Bangalore");
		Transaction tx7 = session.beginTransaction();
		session.update(emp);
		emp.setName("Final updated name");
		System.out.println("13. Before committing update transaction");
		tx7.commit();
		System.out.println("14. After committing update transaction");
		System.out.println("***********************************************");
		// Close resources
		sessionFactory.close();
	}
}
