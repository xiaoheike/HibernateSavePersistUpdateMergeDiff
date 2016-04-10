/* 
 * @(#)HibernatePersistExample.java    Created on 2016年4月10日
 * Copyright (c) 2016. All rights reserved.
 */
package nd.esp.com.hibernate.example;

import nd.esp.com.hibernate.model.Address;
import nd.esp.com.hibernate.model.Employee;
import nd.esp.com.hibernate.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernatePersistExample {
	public static void main(String[] args) {
		// Prep Work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("***********************************************");
		// persist example - with transaction
		Session session2 = sessionFactory.openSession();
		Transaction tx2 = session2.beginTransaction();
		Employee emp2 = getTestEmployee();
		session2.persist(emp2);
		System.out.println("Persist called");
		emp2.setName("Kumar"); // will be updated in database too
		System.out.println("Employee Name updated");
		System.out.println("8. Employee persist called with transaction, id=" + emp2.getId() + ", address id="
				+ emp2.getAddress().getId());
		tx2.commit();
		System.out.println("***********************************************");
		// Close resources
		sessionFactory.close();
	}

	public static Employee getTestEmployee() {
		Employee emp = new Employee();
		Address add = new Address();
		emp.setName("Test Emp");
		add.setCity("Test City");
		emp.setAddress(add);
		add.setEmployee(emp);
		return emp;
	}
}